package org;

public class Matricula {
    private int id;
    private int alunoId;
    private int disciplinaId;
    private String dataMatricula;
    private String situacao;

    public Matricula() {}

    public Matricula(int alunoId, int disciplinaId, String situacao) {
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.situacao = situacao;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }
    public int getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(int disciplinaId) { this.disciplinaId = disciplinaId; }
    public String getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(String dataMatricula) { this.dataMatricula = dataMatricula; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
}