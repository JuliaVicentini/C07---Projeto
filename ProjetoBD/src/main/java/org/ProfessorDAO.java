package org;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO extends ConnectionDAO {

    public boolean inserirProfessor(Professor professor) {
        connectToDB();
        String sql = "INSERT INTO Professor (nome, titulacao, email, data_contratacao) VALUES(?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, professor.getNome());
            pst.setString(2, professor.getTitulacao());
            pst.setString(3, professor.getEmail());
            pst.setString(4, professor.getDataContratacao());
            pst.execute(); // Como o AutoCommit é true, não precisa de commit manual.
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
            return false;
        } finally {
            // ... fechar recursos
        }
    }

    public boolean atualizarProfessor(Professor professor) {
        connectToDB();
        String sql = "UPDATE Professor SET nome=?, titulacao=?, email=?, data_contratacao=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, professor.getNome());
            pst.setString(2, professor.getTitulacao());
            pst.setString(3, professor.getEmail());
            pst.setString(4, professor.getDataContratacao());
            pst.setInt(5, professor.getId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public boolean deletarProfessor(int id) {
        connectToDB();
        String sql = "DELETE FROM Professor WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public List<Professor> selecionarProfessores() {
        List<Professor> professores = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Professor";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getString("nome"),
                        rs.getString("titulacao"),
                        rs.getString("email"),
                        rs.getString("data_contratacao")
                );
                professor.setId(rs.getInt("id"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professores: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return professores;
    }

    public Professor selecionarProfessorPorId(int id) {
        Professor professor = null;
        connectToDB();
        String sql = "SELECT * FROM Professor WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                professor = new Professor(
                        rs.getString("nome"),
                        rs.getString("titulacao"),
                        rs.getString("email"),
                        rs.getString("data_contratacao")
                );
                professor.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return professor;
    }
}