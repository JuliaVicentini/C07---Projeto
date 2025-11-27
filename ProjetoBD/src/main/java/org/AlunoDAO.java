package org;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends ConnectionDAO {

    public boolean inserirAluno(Aluno aluno) {
        connectToDB();
        String sql = "INSERT INTO Aluno (nome, cpf, data_nascimento, email, curso_id) VALUES(?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setString(2, aluno.getCpf());
            pst.setString(3, aluno.getDataNascimento());
            pst.setString(4, aluno.getEmail());
            pst.setInt(5, aluno.getCursoId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
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

    public boolean atualizarAluno(Aluno aluno) {
        connectToDB();
        String sql = "UPDATE Aluno SET nome=?, cpf=?, data_nascimento=?, email=?, curso_id=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setString(2, aluno.getCpf());
            pst.setString(3, aluno.getDataNascimento());
            pst.setString(4, aluno.getEmail());
            pst.setInt(5, aluno.getCursoId());
            pst.setInt(6, aluno.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
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

    public boolean deletarAluno(int id) {
        connectToDB();
        String sql = "DELETE FROM Aluno WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
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

    public List<Aluno> selecionarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Aluno";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("email"),
                        rs.getInt("curso_id")
                );
                aluno.setId(rs.getInt("id"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return alunos;
    }

    public Aluno selecionarAlunoPorId(int id) {
        Aluno aluno = null;
        connectToDB();
        String sql = "SELECT * FROM Aluno WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                aluno = new Aluno(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("data_nascimento"),
                        rs.getString("email"),
                        rs.getInt("curso_id")
                );
                aluno.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return aluno;
    }
}