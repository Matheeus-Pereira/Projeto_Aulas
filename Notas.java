package projeto_aulas;

import java.util.Scanner;

public class Notas extends Fclt {

    private String prova;

    private Aluno[] alunos;
    private double[] valorNotas;
    Scanner sc = new Scanner(System.in);

    public Notas(String prova, int quantAlunos) {
        this.alunos = new Aluno[quantAlunos];
        this.prova = prova;

    }

    public String getProva() {
        return prova;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public Aluno[] getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno[] alunos) {
        this.alunos = alunos;
    }

    public void setAlunos(int pos, Aluno aluno) {
        if (pos >= 0 && pos < this.alunos.length) {
            this.alunos[pos] = aluno;
        } else {
            escreve("Posição inválida para adicionar aluno!");
        }
    }

    private void registraNotas() {
        this.valorNotas = new double[alunos.length];
        for (int i = 0; i < valorNotas.length; i++) {
            valorNotas[i] = alunos[i].getNota();
        }
    }

    public void boletim() {
        registraNotas();
        for (int i = 0; i < alunos.length; i++) {
            escreve("Aluno " + alunos[i].getNome() + " Nota: " + valorNotas[i]);
        }
    }

    /*
    public void menu() {
        escreve("==========MENU==========");
        escreve("Calcular Media[1]");
        escreve("Definir notas [2]");
        escreve("Remover [2]");
        escreve("Adicionar [3]");
        escreve("Voltar [4]");
        int resp = sc.nextInt();
        switch (resp) {
            case 1:
                escreve("Quantidade atual de alunos: "
                );
                return;
            case 2:
                escreve("Informe a quantidade de alunos a ser removida:");
                //a -= sc.nextInt();
                return;
            case 3:
                escreve("Informe a quantidade de alunos a ser Adicionada:");
                //a += sc.nextInt();
                return;
            case 4:
                menu();
                break;
            default:
                escreve("informe a numeração correnpondente!!");
                return;
        }

    }*/
}
