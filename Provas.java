package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;

public class Provas {

    private Conexao banco = new Conexao();

    private int id;
    private String prova;
    private List<Aluno> aluno = new ArrayList<>();

    public Provas(int id, String prova) {
        this.id = id;
        this.prova = prova;
        banco.CarrgarAlunos(this.aluno, id);
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Provas{" + "id=" + id + ", prova=" + prova + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProva() {
        return prova;
    }

    public void setProva(String prova) {
        banco.nomeProva(prova, this.id);
        this.prova = prova;
    }
    public void excluiProva(){
        //chamar o banco para excluir a prova
        banco.excluiProva(this.id);
    }

}
