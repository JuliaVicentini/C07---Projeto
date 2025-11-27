package org;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO extends ConnectionDAO {

    // JOINs SEM tabelas intermediárias
    public List<String> getProfessoresCursos() {
        List<String> resultados = new ArrayList<>();
        connectToDB();
        String sql = "SELECT p.nome as professor, c.nome as curso " +
                "FROM Professor p JOIN Curso c ON p.id = c.professor_coordenador_id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultado = "Professor: " + rs.getString("professor") +
                        " | Curso: " + rs.getString("curso");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatório: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultados;
    }

    public List<String> getDisciplinasProfessores() {
        List<String> resultados = new ArrayList<>();
        connectToDB();
        String sql = "SELECT d.nome as disciplina, p.nome as professor " +
                "FROM Disciplina d JOIN Professor p ON d.professor_id = p.id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultado = "Disciplina: " + rs.getString("disciplina") +
                        " | Professor: " + rs.getString("professor");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatório: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultados;
    }

    public List<String> getAlunosCursos() {
        List<String> resultados = new ArrayList<>();
        connectToDB();
        String sql = "SELECT a.nome as aluno, c.nome as curso " +
                "FROM Aluno a JOIN Curso c ON a.curso_id = c.id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultado = "Aluno: " + rs.getString("aluno") +
                        " | Curso: " + rs.getString("curso");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatório: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultados;
    }

    // JOINs COM tabelas intermediárias
    public List<String> getMatriculasCompletas() {
        List<String> resultados = new ArrayList<>();
        connectToDB();
        String sql = "SELECT a.nome as aluno, d.nome as disciplina, c.nome as curso, m.situacao " +
                "FROM Matricula m " +
                "JOIN Aluno a ON m.aluno_id = a.id " +
                "JOIN Disciplina d ON m.disciplina_id = d.id " +
                "JOIN Curso c ON d.curso_id = c.id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultado = "Aluno: " + rs.getString("aluno") +
                        " | Disciplina: " + rs.getString("disciplina") +
                        " | Curso: " + rs.getString("curso") +
                        " | Situação: " + rs.getString("situacao");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatório: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultados;
    }

    public List<String> getDisciplinasComCoordenador() {
        List<String> resultados = new ArrayList<>();
        connectToDB();
        String sql = "SELECT d.nome as disciplina, c.nome as curso, p.nome as coordenador " +
                "FROM Disciplina d " +
                "JOIN Curso c ON d.curso_id = c.id " +
                "JOIN Professor p ON c.professor_coordenador_id = p.id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultado = "Disciplina: " + rs.getString("disciplina") +
                        " | Curso: " + rs.getString("curso") +
                        " | Coordenador: " + rs.getString("coordenador");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatório: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultados;
    }

    public List<String> getAlunosDisciplinasProfessores() {
        List<String> resultados = new ArrayList<>();
        connectToDB();
        String sql = "SELECT a.nome as aluno, d.nome as disciplina, p.nome as professor, m.situacao " +
                "FROM Matricula m " +
                "JOIN Aluno a ON m.aluno_id = a.id " +
                "JOIN Disciplina d ON m.disciplina_id = d.id " +
                "JOIN Professor p ON d.professor_id = p.id";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String resultado = "Aluno: " + rs.getString("aluno") +
                        " | Disciplina: " + rs.getString("disciplina") +
                        " | Professor: " + rs.getString("professor") +
                        " | Situação: " + rs.getString("situacao");
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            System.out.println("Erro no relatório: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return resultados;
    }
}