package org;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO extends ConnectionDAO {

    public boolean inserirDisciplina(Disciplina disciplina) {
        connectToDB();
        String sql = "INSERT INTO Disciplina (codigo, nome, carga_horaria, curso_id, professor_id) VALUES(?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, disciplina.getCodigo());
            pst.setString(2, disciplina.getNome());
            pst.setInt(3, disciplina.getCargaHoraria());
            pst.setInt(4, disciplina.getCursoId());
            pst.setInt(5, disciplina.getProfessorId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir disciplina: " + e.getMessage());
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

    public boolean atualizarDisciplina(Disciplina disciplina) {
        connectToDB();
        String sql = "UPDATE Disciplina SET codigo=?, nome=?, carga_horaria=?, curso_id=?, professor_id=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, disciplina.getCodigo());
            pst.setString(2, disciplina.getNome());
            pst.setInt(3, disciplina.getCargaHoraria());
            pst.setInt(4, disciplina.getCursoId());
            pst.setInt(5, disciplina.getProfessorId());
            pst.setInt(6, disciplina.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disciplina: " + e.getMessage());
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

    public boolean deletarDisciplina(int id) {
        connectToDB();
        String sql = "DELETE FROM Disciplina WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar disciplina: " + e.getMessage());
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

    public List<Disciplina> selecionarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Disciplina";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Disciplina disciplina = new Disciplina(
                        rs.getString("codigo"),
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getInt("curso_id"),
                        rs.getInt("professor_id")
                );
                disciplina.setId(rs.getInt("id"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplinas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return disciplinas;
    }

    public Disciplina selecionarDisciplinaPorId(int id) {
        Disciplina disciplina = null;
        connectToDB();
        String sql = "SELECT * FROM Disciplina WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                disciplina = new Disciplina(
                        rs.getString("codigo"),
                        rs.getString("nome"),
                        rs.getInt("carga_horaria"),
                        rs.getInt("curso_id"),
                        rs.getInt("professor_id")
                );
                disciplina.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar disciplina: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return disciplina;
    }
}