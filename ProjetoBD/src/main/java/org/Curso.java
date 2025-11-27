package org;

public class Curso {
    private int id;
    private String nome;
    private String descricao;
    private int duracaoMeses;
    private int professorCoordenadorId;

    public Curso() {}

    public Curso(String nome, String descricao, int duracaoMeses, int professorCoordenadorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoMeses = duracaoMeses;
        this.professorCoordenadorId = professorCoordenadorId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getDuracaoMeses() { return duracaoMeses; }
    public void setDuracaoMeses(int duracaoMeses) { this.duracaoMeses = duracaoMeses; }
    public int getProfessorCoordenadorId() { return professorCoordenadorId; }
    public void setProfessorCoordenadorId(int professorCoordenadorId) { this.professorCoordenadorId = professorCoordenadorId; }
}