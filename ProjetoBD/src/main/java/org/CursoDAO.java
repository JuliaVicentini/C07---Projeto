package org;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO extends ConnectionDAO {

    public boolean inserirCurso(Curso curso) {
        connectToDB();
        String sql = "INSERT INTO Curso (nome, descricao, duracao_meses, professor_coordenador_id) VALUES(?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, curso.getNome());
            pst.setString(2, curso.getDescricao());
            pst.setInt(3, curso.getDuracaoMeses());
            pst.setInt(4, curso.getProfessorCoordenadorId());
            int rowsAffected = pst.executeUpdate(); // Use executeUpdate para INSERT
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir curso: " + e.getMessage());
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

    public boolean atualizarCurso(Curso curso) {
        connectToDB();
        String sql = "UPDATE Curso SET nome=?, descricao=?, duracao_meses=?, professor_coordenador_id=? WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, curso.getNome());
            pst.setString(2, curso.getDescricao());
            pst.setInt(3, curso.getDuracaoMeses());
            pst.setInt(4, curso.getProfessorCoordenadorId());
            pst.setInt(5, curso.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
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

    public boolean deletarCurso(int id) {
        connectToDB();
        String sql = "DELETE FROM Curso WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar curso: " + e.getMessage());
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

    public List<Curso> selecionarCursos() {
        List<Curso> cursos = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Curso";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("duracao_meses"),
                        rs.getInt("professor_coordenador_id")
                );
                curso.setId(rs.getInt("id"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cursos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return cursos;
    }

    public Curso selecionarCursoPorId(int id) {
        Curso curso = null;
        connectToDB();
        String sql = "SELECT * FROM Curso WHERE id=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                curso = new Curso(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("duracao_meses"),
                        rs.getInt("professor_coordenador_id")
                );
                curso.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar curso: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return curso;
    }
}