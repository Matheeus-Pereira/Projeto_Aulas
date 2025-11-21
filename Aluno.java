package com.mycompany.mavenproject1;

public class Aluno extends Fclt {

    private String nome;
    private int id;
    private double nota;
    private Conexao banco = new Conexao();

    public Aluno(String nome, int numero) {
        this.nome = nome;
        this.id = numero;
    }

    public Aluno(String nome) {
        this.nome = nome;
        banco.InsertAluno(nome);
        this.id = banco.selectNumeroAluno(nome);

    }

    public double getNota() {
        return nota;
    }

    public void setNota(int prova) {
        this.nota = banco.selectNota(prova, this.id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        banco.nomeAluno(nome, this.id);
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public void setNumero(int numero) {
        this.id = numero;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void excluiAluno() {
        escreve("id do aluno:" + this.id);
        banco.excluiAluno(this.id);
    }

}
