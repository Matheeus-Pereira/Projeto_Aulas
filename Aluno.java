package projeto_aulas;

public class Aluno extends Fclt {

    private String nome;
    private int numero;
    private double nota;

    public Aluno(String nome, int numero) {
        this.nome = nome;
        this.numero = numero + 1;
    }

    public Aluno(String nome) {
        this.nome = nome;
        this.numero = numero + 1;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void apresentar() {
        escreve("Nome do aluno " + this.nome + " numero do aluno " + this.numero);
    }

    public void CalculaMedia(double t[]) {
        double media = 0;
        for (int i = 0; i < t.length; i++) {
            media += t[i];
        }
        escreve("A media das notas e: " + media / t.length);
    }
}
