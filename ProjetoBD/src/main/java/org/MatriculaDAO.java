package org;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO extends ConnectionDAO {

    public boolean inserirMatricula(Matricula matricula) {
        connectToDB();
        String sql = "INSERT INTO Matricula (aluno_id, disciplina_id, situacao) VALUES(?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, matricula.getAlunoId());
            pst.setInt(2, matricula.getDisciplinaId());
            pst.setString(3, matricula.getSituacao());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir matrícula: " + e.getMessage());
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

    public boolean atualizarMatricula(Matricula matricula) {
        connectToDB();
        String sql = "UPDATE Matricula SET aluno_id=?, disciplina_id=?, situacao=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, matricula.getAlunoId());
            pst.setInt(2, matricula.getDisciplinaId());
            pst.setString(3, matricula.getSituacao());
            pst.setInt(4, matricula.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar matrícula: " + e.getMessage());
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

    public boolean deletarMatricula(int id) {
        connectToDB();
        String sql = "DELETE FROM Matricula WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar matrícula: " + e.getMessage());
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

    public List<Matricula> selecionarMatriculas() {
        List<Matricula> matriculas = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Matricula";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Matricula matricula = new Matricula(
                        rs.getInt("aluno_id"),
                        rs.getInt("disciplina_id"),
                        rs.getString("situacao")
                );
                matricula.setId(rs.getInt("id"));
                matricula.setDataMatricula(rs.getString("data_matricula"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar matrículas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return matriculas;
    }

    public Matricula selecionarMatriculaPorId(int id) {
        Matricula matricula = null;
        connectToDB();
        String sql = "SELECT * FROM Matricula WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                matricula = new Matricula(
                        rs.getInt("aluno_id"),
                        rs.getInt("disciplina_id"),
                        rs.getString("situacao")
                );
                matricula.setId(rs.getInt("id"));
                matricula.setDataMatricula(rs.getString("data_matricula"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar matrícula: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return matricula;
    }
}