
import java.util.ArrayList;
import java.util.Scanner;

public class Notas extends Fclt {

    private String prova;
    private int id;


    private ArrayList<Aluno> alunos = new ArrayList<>();
    private double[] valorNotas;
    Scanner sc = new Scanner(System.in);
    private Conexao banco = new Conexao();

    public Notas(String prova) {
        banco.InsertProva(prova);
        this.prova = prova;
        this.id=banco.selectIDprova(prova);
    }

    public Notas(int id) {
        this.prova = banco.selectNomeprova(id);
        this.id = id;
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
        this.prova = prova;
    }

    public ArrayList<Aluno> getAlunos() {
        banco.ALunosProva(alunos, this.id);
        return alunos;
    }


    private void registraNotas() {
        this.valorNotas = new double[alunos.size()];
        for (int i = 0; i < valorNotas.length; i++) {
            valorNotas[i] = alunos.get(i).getNota();
            banco.InsertNotas(this.id, alunos.get(i).getId(), valorNotas[i]);
        }
    }

    public void boletim() {
        registraNotas();
        banco.ListaNotas(this.id);
    }

    public void listarnotas() {
        banco.ListaNotas(this.id);
    }
    public void VinculaAlunos(int aluno){
        banco.vinculaAluno(aluno, this.id);
    }
}
