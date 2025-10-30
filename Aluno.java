
public class Aluno extends Fclt{
    
    private String nome;
    private int id;
    private double nota;
    private Conexao banco= new Conexao();

    public Aluno(String nome, int numero) {
        this.nome = nome;
        this.id = numero;
    }

    public Aluno(String nome) {
        this.nome = nome;
        banco.InsertAluno(nome);
        this.id=banco.selectNumeroAluno(nome);
        
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

    public int getId() {
        return this.id;
    }

    public void setNumero(int numero) {
        this.id = numero;
    }

    public void apresentar() {
        escreve("Id do aluno "+this.id+" nome do aluno "+this.nome);
    }

    public void CalculaMedia(double t[]) {
        double media = 0;
        for (int i = 0; i < t.length; i++) {
            media += t[i];
        }
        escreve("A media das notas e: " + media / t.length);
    }
}
