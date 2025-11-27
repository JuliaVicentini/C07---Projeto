package org;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ProfessorDAO professorDAO = new ProfessorDAO();
    private static CursoDAO cursoDAO = new CursoDAO();
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private static AlunoDAO alunoDAO = new AlunoDAO();
    private static MatriculaDAO matriculaDAO = new MatriculaDAO();
    private static RelatorioDAO relatorioDAO = new RelatorioDAO();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n=== SISTEMA FACULDADE ===");
            System.out.println("1. Gerenciar Professores");
            System.out.println("2. Gerenciar Cursos");
            System.out.println("3. Gerenciar Disciplinas");
            System.out.println("4. Gerenciar Alunos");
            System.out.println("5. Gerenciar Matrículas");
            System.out.println("6. Relatórios (JOINs)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuProfessores();
                    break;
                case 2:
                    menuCursos();
                    break;
                case 3:
                    menuDisciplinas();
                    break;
                case 4:
                    menuAlunos();
                    break;
                case 5:
                    menuMatriculas();
                    break;
                case 6:
                    menuRelatorios();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ========== MENU PROFESSORES ==========
    private static void menuProfessores() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR PROFESSORES ===");
            System.out.println("1. Listar Professores");
            System.out.println("2. Inserir Professor");
            System.out.println("3. Atualizar Professor");
            System.out.println("4. Deletar Professor");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarProfessores();
                    break;
                case 2:
                    inserirProfessor();
                    break;
                case 3:
                    atualizarProfessor();
                    break;
                case 4:
                    deletarProfessor();
                    break;
            }
        } while (opcao != 0);
    }

    private static void listarProfessores() {
        List<Professor> professores = professorDAO.selecionarProfessores();
        System.out.println("\n=== LISTA DE PROFESSORES ===");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor encontrado.");
        } else {
            for (Professor p : professores) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() +
                        " | Titulação: " + p.getTitulacao() + " | Email: " + p.getEmail());
            }
        }
    }

    private static void inserirProfessor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Titulação: ");
        String titulacao = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Data Contratação (YYYY-MM-DD): ");
        String data = scanner.nextLine();

        Professor professor = new Professor(nome, titulacao, email, data);
        if (professorDAO.inserirProfessor(professor)) {
            System.out.println("Professor inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir professor!");
        }
    }

    private static void atualizarProfessor() {
        System.out.print("ID do professor a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Professor professor = professorDAO.selecionarProfessorPorId(id);
        if (professor == null) {
            System.out.println("Professor não encontrado!");
            return;
        }

        System.out.print("Novo nome (" + professor.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) professor.setNome(nome);

        System.out.print("Nova titulação (" + professor.getTitulacao() + "): ");
        String titulacao = scanner.nextLine();
        if (!titulacao.isEmpty()) professor.setTitulacao(titulacao);

        System.out.print("Novo email (" + professor.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) professor.setEmail(email);

        System.out.print("Nova data contratação (" + professor.getDataContratacao() + "): ");
        String data = scanner.nextLine();
        if (!data.isEmpty()) professor.setDataContratacao(data);

        if (professorDAO.atualizarProfessor(professor)) {
            System.out.println("Professor atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar professor!");
        }
    }

    private static void deletarProfessor() {
        System.out.print("ID do professor a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (professorDAO.deletarProfessor(id)) {
            System.out.println("Professor deletado com sucesso!");
        } else {
            System.out.println("Erro ao deletar professor!");
        }
    }

    // ========== MENU CURSOS ==========
    private static void menuCursos() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR CURSOS ===");
            System.out.println("1. Listar Cursos");
            System.out.println("2. Inserir Curso");
            System.out.println("3. Atualizar Curso");
            System.out.println("4. Deletar Curso");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarCursos();
                    break;
                case 2:
                    inserirCurso();
                    break;
                case 3:
                    atualizarCurso();
                    break;
                case 4:
                    deletarCurso();
                    break;
            }
        } while (opcao != 0);
    }

    private static void listarCursos() {
        List<Curso> cursos = cursoDAO.selecionarCursos();
        System.out.println("\n=== LISTA DE CURSOS ===");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado.");
        } else {
            for (Curso c : cursos) {
                System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() +
                        " | Duração: " + c.getDuracaoMeses() + " meses | Descrição: " + c.getDescricao());
            }
        }
    }

    private static void inserirCurso() {
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Duração (meses): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID do professor coordenador: ");
        int professorId = scanner.nextInt();
        scanner.nextLine();

        Curso curso = new Curso(nome, descricao, duracao, professorId);
        if (cursoDAO.inserirCurso(curso)) {
            System.out.println("Curso inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir curso!");
        }
    }

    private static void atualizarCurso() {
        System.out.print("ID do curso a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Curso curso = cursoDAO.selecionarCursoPorId(id);
        if (curso == null) {
            System.out.println("Curso não encontrado!");
            return;
        }

        System.out.print("Novo nome (" + curso.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) curso.setNome(nome);

        System.out.print("Nova descrição (" + curso.getDescricao() + "): ");
        String descricao = scanner.nextLine();
        if (!descricao.isEmpty()) curso.setDescricao(descricao);

        System.out.print("Nova duração (" + curso.getDuracaoMeses() + "): ");
        String duracaoStr = scanner.nextLine();
        if (!duracaoStr.isEmpty()) curso.setDuracaoMeses(Integer.parseInt(duracaoStr));

        System.out.print("Novo ID do professor coordenador (" + curso.getProfessorCoordenadorId() + "): ");
        String professorStr = scanner.nextLine();
        if (!professorStr.isEmpty()) curso.setProfessorCoordenadorId(Integer.parseInt(professorStr));

        if (cursoDAO.atualizarCurso(curso)) {
            System.out.println("Curso atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar curso!");
        }
    }

    private static void deletarCurso() {
        System.out.print("ID do curso a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (cursoDAO.deletarCurso(id)) {
            System.out.println("Curso deletado com sucesso!");
        } else {
            System.out.println("Erro ao deletar curso!");
        }
    }

    // ========== MENU DISCIPLINAS ==========
    private static void menuDisciplinas() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR DISCIPLINAS ===");
            System.out.println("1. Listar Disciplinas");
            System.out.println("2. Inserir Disciplina");
            System.out.println("3. Atualizar Disciplina");
            System.out.println("4. Deletar Disciplina");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarDisciplinas();
                    break;
                case 2:
                    inserirDisciplina();
                    break;
                case 3:
                    atualizarDisciplina();
                    break;
                case 4:
                    deletarDisciplina();
                    break;
            }
        } while (opcao != 0);
    }

    private static void listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaDAO.selecionarDisciplinas();
        System.out.println("\n=== LISTA DE DISCIPLINAS ===");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina encontrada.");
        } else {
            for (Disciplina d : disciplinas) {
                System.out.println("ID: " + d.getId() + " | Código: " + d.getCodigo() +
                        " | Nome: " + d.getNome() + " | Carga: " + d.getCargaHoraria() + "h");
            }
        }
    }

    private static void inserirDisciplina() {
        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Carga horária: ");
        int carga = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID do curso: ");
        int cursoId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID do professor: ");
        int professorId = scanner.nextInt();
        scanner.nextLine();

        Disciplina disciplina = new Disciplina(codigo, nome, carga, cursoId, professorId);
        if (disciplinaDAO.inserirDisciplina(disciplina)) {
            System.out.println("Disciplina inserida com sucesso!");
        } else {
            System.out.println("Erro ao inserir disciplina!");
        }
    }

    private static void atualizarDisciplina() {
        System.out.print("ID da disciplina a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Disciplina disciplina = disciplinaDAO.selecionarDisciplinaPorId(id);
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        System.out.print("Novo código (" + disciplina.getCodigo() + "): ");
        String codigo = scanner.nextLine();
        if (!codigo.isEmpty()) disciplina.setCodigo(codigo);

        System.out.print("Novo nome (" + disciplina.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) disciplina.setNome(nome);

        System.out.print("Nova carga horária (" + disciplina.getCargaHoraria() + "): ");
        String cargaStr = scanner.nextLine();
        if (!cargaStr.isEmpty()) disciplina.setCargaHoraria(Integer.parseInt(cargaStr));

        System.out.print("Novo ID do curso (" + disciplina.getCursoId() + "): ");
        String cursoStr = scanner.nextLine();
        if (!cursoStr.isEmpty()) disciplina.setCursoId(Integer.parseInt(cursoStr));

        System.out.print("Novo ID do professor (" + disciplina.getProfessorId() + "): ");
        String professorStr = scanner.nextLine();
        if (!professorStr.isEmpty()) disciplina.setProfessorId(Integer.parseInt(professorStr));

        if (disciplinaDAO.atualizarDisciplina(disciplina)) {
            System.out.println("Disciplina atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar disciplina!");
        }
    }

    private static void deletarDisciplina() {
        System.out.print("ID da disciplina a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (disciplinaDAO.deletarDisciplina(id)) {
            System.out.println("Disciplina deletada com sucesso!");
        } else {
            System.out.println("Erro ao deletar disciplina!");
        }
    }

    // ========== MENU ALUNOS ==========
    private static void menuAlunos() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR ALUNOS ===");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Inserir Aluno");
            System.out.println("3. Atualizar Aluno");
            System.out.println("4. Deletar Aluno");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarAlunos();
                    break;
                case 2:
                    inserirAluno();
                    break;
                case 3:
                    atualizarAluno();
                    break;
                case 4:
                    deletarAluno();
                    break;
            }
        } while (opcao != 0);
    }

    private static void listarAlunos() {
        List<Aluno> alunos = alunoDAO.selecionarAlunos();
        System.out.println("\n=== LISTA DE ALUNOS ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
        } else {
            for (Aluno a : alunos) {
                System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() +
                        " | CPF: " + a.getCpf() + " | Email: " + a.getEmail());
            }
        }
    }

    private static void inserirAluno() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data Nascimento (YYYY-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("ID do curso: ");
        int cursoId = scanner.nextInt();
        scanner.nextLine();

        Aluno aluno = new Aluno(nome, cpf, data, email, cursoId);
        if (alunoDAO.inserirAluno(aluno)) {
            System.out.println("Aluno inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir aluno!");
        }
    }

    private static void atualizarAluno() {
        System.out.print("ID do aluno a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aluno aluno = alunoDAO.selecionarAlunoPorId(id);
        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.print("Novo nome (" + aluno.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) aluno.setNome(nome);

        System.out.print("Novo CPF (" + aluno.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty()) aluno.setCpf(cpf);

        System.out.print("Nova data nascimento (" + aluno.getDataNascimento() + "): ");
        String data = scanner.nextLine();
        if (!data.isEmpty()) aluno.setDataNascimento(data);

        System.out.print("Novo email (" + aluno.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) aluno.setEmail(email);

        System.out.print("Novo ID do curso (" + aluno.getCursoId() + "): ");
        String cursoStr = scanner.nextLine();
        if (!cursoStr.isEmpty()) aluno.setCursoId(Integer.parseInt(cursoStr));

        if (alunoDAO.atualizarAluno(aluno)) {
            System.out.println("Aluno atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar aluno!");
        }
    }

    private static void deletarAluno() {
        System.out.print("ID do aluno a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (alunoDAO.deletarAluno(id)) {
            System.out.println("Aluno deletado com sucesso!");
        } else {
            System.out.println("Erro ao deletar aluno!");
        }
    }

    // ========== MENU MATRÍCULAS ==========
    private static void menuMatriculas() {
        int opcao;

        do {
            System.out.println("\n=== GERENCIAR MATRÍCULAS ===");
            System.out.println("1. Listar Matrículas");
            System.out.println("2. Inserir Matrícula");
            System.out.println("3. Atualizar Matrícula");
            System.out.println("4. Deletar Matrícula");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarMatriculas();
                    break;
                case 2:
                    inserirMatricula();
                    break;
                case 3:
                    atualizarMatricula();
                    break;
                case 4:
                    deletarMatricula();
                    break;
            }
        } while (opcao != 0);
    }

    private static void listarMatriculas() {
        List<Matricula> matriculas = matriculaDAO.selecionarMatriculas();
        System.out.println("\n=== LISTA DE MATRÍCULAS ===");
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula encontrada.");
        } else {
            for (Matricula m : matriculas) {
                System.out.println("ID: " + m.getId() + " | Aluno ID: " + m.getAlunoId() +
                        " | Disciplina ID: " + m.getDisciplinaId() + " | Situação: " + m.getSituacao());
            }
        }
    }

    private static void inserirMatricula() {
        System.out.print("ID do aluno: ");
        int alunoId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID da disciplina: ");
        int disciplinaId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Situação (matriculado/trancado/cancelado/concluido): ");
        String situacao = scanner.nextLine();

        Matricula matricula = new Matricula(alunoId, disciplinaId, situacao);
        if (matriculaDAO.inserirMatricula(matricula)) {
            System.out.println("Matrícula inserida com sucesso!");
        } else {
            System.out.println("Erro ao inserir matrícula!");
        }
    }

    private static void atualizarMatricula() {
        System.out.print("ID da matrícula a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Matricula matricula = matriculaDAO.selecionarMatriculaPorId(id);
        if (matricula == null) {
            System.out.println("Matrícula não encontrada!");
            return;
        }

        System.out.print("Novo ID do aluno (" + matricula.getAlunoId() + "): ");
        String alunoStr = scanner.nextLine();
        if (!alunoStr.isEmpty()) matricula.setAlunoId(Integer.parseInt(alunoStr));

        System.out.print("Novo ID da disciplina (" + matricula.getDisciplinaId() + "): ");
        String disciplinaStr = scanner.nextLine();
        if (!disciplinaStr.isEmpty()) matricula.setDisciplinaId(Integer.parseInt(disciplinaStr));

        System.out.print("Nova situação (" + matricula.getSituacao() + "): ");
        String situacao = scanner.nextLine();
        if (!situacao.isEmpty()) matricula.setSituacao(situacao);

        if (matriculaDAO.atualizarMatricula(matricula)) {
            System.out.println("Matrícula atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar matrícula!");
        }
    }

    private static void deletarMatricula() {
        System.out.print("ID da matrícula a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (matriculaDAO.deletarMatricula(id)) {
            System.out.println("Matrícula deletada com sucesso!");
        } else {
            System.out.println("Erro ao deletar matrícula!");
        }
    }

    // ========== MENU RELATÓRIOS (JOINs) ==========
    private static void menuRelatorios() {
        int opcao;

        do {
            System.out.println("\n=== RELATÓRIOS (JOINs) ===");
            System.out.println("1. JOINs SEM Tabelas Intermediárias");
            System.out.println("2. JOINs COM Tabelas Intermediárias");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuJoinsSemIntermediarias();
                    break;
                case 2:
                    menuJoinsComIntermediarias();
                    break;
            }
        } while (opcao != 0);
    }

    private static void menuJoinsSemIntermediarias() {
        int opcao;

        do {
            System.out.println("\n=== JOINs SEM TABELAS INTERMEDIÁRIAS ===");
            System.out.println("1. Professores e Cursos que Coordenam");
            System.out.println("2. Disciplinas e seus Professores");
            System.out.println("3. Alunos e seus Cursos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    mostrarProfessoresCursos();
                    break;
                case 2:
                    mostrarDisciplinasProfessores();
                    break;
                case 3:
                    mostrarAlunosCursos();
                    break;
            }
        } while (opcao != 0);
    }

    private static void menuJoinsComIntermediarias() {
        int opcao;

        do {
            System.out.println("\n=== JOINs COM TABELAS INTERMEDIÁRIAS ===");
            System.out.println("1. Matrículas Completas (Aluno→Disciplina→Curso)");
            System.out.println("2. Disciplinas com Curso e Coordenador");
            System.out.println("3. Alunos com Disciplinas e Professores");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    mostrarMatriculasCompletas();
                    break;
                case 2:
                    mostrarDisciplinasComCoordenador();
                    break;
                case 3:
                    mostrarAlunosDisciplinasProfessores();
                    break;
            }
        } while (opcao != 0);
    }

    private static void mostrarProfessoresCursos() {
        System.out.println("\n=== PROFESSORES E CURSOS QUE COORDENAM ===");
        List<String> resultados = relatorioDAO.getProfessoresCursos();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (String resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }

    private static void mostrarDisciplinasProfessores() {
        System.out.println("\n=== DISCIPLINAS E SEUS PROFESSORES ===");
        List<String> resultados = relatorioDAO.getDisciplinasProfessores();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (String resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }

    private static void mostrarAlunosCursos() {
        System.out.println("\n=== ALUNOS E SEUS CURSOS ===");
        List<String> resultados = relatorioDAO.getAlunosCursos();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (String resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }

    private static void mostrarMatriculasCompletas() {
        System.out.println("\n=== MATRÍCULAS COMPLETAS ===");
        List<String> resultados = relatorioDAO.getMatriculasCompletas();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (String resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }

    private static void mostrarDisciplinasComCoordenador() {
        System.out.println("\n=== DISCIPLINAS COM CURSO E COORDENADOR ===");
        List<String> resultados = relatorioDAO.getDisciplinasComCoordenador();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (String resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }

    private static void mostrarAlunosDisciplinasProfessores() {
        System.out.println("\n=== ALUNOS COM DISCIPLINAS E PROFESSORES ===");
        List<String> resultados = relatorioDAO.getAlunosDisciplinasProfessores();
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
        } else {
            for (String resultado : resultados) {
                System.out.println(resultado);
            }
        }
    }
}