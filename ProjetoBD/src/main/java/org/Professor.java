package org;

public class Professor {
    private int id;
    private String nome;
    private String titulacao;
    private String email;
    private String dataContratacao;

    public Professor() {}

    public Professor(String nome, String titulacao, String email, String dataContratacao) {
        this.nome = nome;
        this.titulacao = titulacao;
        this.email = email;
        this.dataContratacao = dataContratacao;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTitulacao() { return titulacao; }
    public void setTitulacao(String titulacao) { this.titulacao = titulacao; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(String dataContratacao) { this.dataContratacao = dataContratacao; }
}