
import java.util.ArrayList;
import java.util.Scanner;

public class Aulas extends Fclt {

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Aluno> studante = new ArrayList<>();
    public static ArrayList<Notas> boletim = new ArrayList<>();
    private static Conexao banco = new Conexao();

    public static void menu() {
        banco.CarrgarAlunos(studante);
        banco.CarrgarProvas(boletim);
        int opc;
        escreve("====MENU====");
        escreve("Cadastrar aluno[1]");
        escreve("Consultar aluno[2]");
        escreve("Cadastrar prova[3]");
        escreve("Informar nota[4]");
        opc = sc.nextInt();
        escreve("====MENU====");
        switch (opc) {
            case 1:
                cadAluno();
                break;
            case 2:
                consAluno();
                break;
            case 3:
                cadBoletim();
                break;
            case 4:
                cadNota();
                break;
            default:
                escreve("opcçao inválida!");
                menu();
                break;
        }

    }

    public static void cadAluno() {
        int resp = 0;

        escreve("Digite o nome do aluno: ");
        String nome = sc.next();
        Aluno NvAluno = new Aluno(nome);
        studante.add(NvAluno);
        escreve("Cadastrar outro aluno?\nSim[1]\nNão[2]");
        resp = sc.nextInt();
        switch (resp) {
            case 1:
                cadAluno();
                break;
            case 2:
                menu();
                break;
            default:
                escreve("Opção inválida!");
                break;
        }
        return;
    }

    public static void consAluno() {
        if (banco.ConfereAlunos() == false) {
            escreve("Nenhum aluno foi cadastrado ainda!");
            cadAluno();
            return;

        }
        banco.CarrgarAlunos(studante);
        banco.CarrgarProvas(boletim);
        for (int i = 0; i < studante.size(); i++) {
            studante.get(i).apresentar();
        }
        sc.nextLine();
        menu();
        return;
    }

    public static void cadBoletim() {

        escreve("Titulo da prova:");
        String ttl = sc.next(); //para setar a prova
        sc.nextLine();
        escreve("Quantos alunos fizeram a prova?");
        int qtdnt = sc.nextInt();//quantidade de vezes que será informado o aluno

        Notas novaProva = new Notas(ttl);//prova setada titulo quant de alunos e quantas provas vai ter
        //na maioria dos casos todos os alunos fazem a prova, devo adaptar a função pra isso, esse é o caso onde escolho qual aluno 

        escreve("quais alunos fizeram a prova?(informe a ID)");
        //escreve("Id da prova: "+novaProva.getId());
        novaProva.getAlunos();//mostra os alunos pra não ter erro
        sc.nextLine();//limpa 
        banco.CarrgarAlunos(studante);
        //  escreve("estudantes carregados: " + studante.size());
        listaAlunos(novaProva.getAlunos());
        for (int i = 0; i < qtdnt; i++) {
            escreve("ID do aluno " + (i + 1) + ": ");
            int id = sc.nextInt();
            Aluno alunoEncontrado = null;//cada vez q o loop iniciar esse aluno vai estar nulo, pra ver se tem um aluno com aquele nome

            for (Aluno s : studante) {
                if (s.getId() == id) {
                    alunoEncontrado = s;
                    novaProva.VinculaAlunos(alunoEncontrado.getId());
                    break;
                }

            }

            if (alunoEncontrado != null) {//compara o nome recebido e o nome do aluno, se for nulo o if anterior não deu certo
                escreve("Aluno '" + alunoEncontrado.getNome() + "' adicionado ao boletim.");
            } else {
                escreve("Aluno com o id:'" + id + "' não encontrado Verifique!");
                return;
            }

        }
        boletim.add(novaProva);
        escreve("Boletim '" + ttl + "' cadastrado com sucesso!");
        escreve("\nDeseja cadastrar um novo Boletim?\n[1]Sim\n[2]não");
        int opc = sc.nextInt();
        switch (opc) {
            case 1:
                cadBoletim();
                break;
            case 2:
                menu();
                break;
            default:
                escreve("opção inválida!");
                cadBoletim();
                break;
        }
    }

    private static void listaAlunos(ArrayList<Aluno> alunos) {
        for (Aluno a : alunos) {
            a.apresentar();
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void cadNota() {
        escreve("Informe o id da prova que vai ter a nota");
        banco.Listarprovas();
        int id_prova = sc.nextInt();//até aqui eu quero saber qual a prova
        Notas provaSelecionada = null;
        banco.CarrgarProvas(boletim);
        for (Notas prova : boletim) {
            if (prova.getId() == id_prova) {
                provaSelecionada = prova;
            }

        }
        if (provaSelecionada == null) {
            escreve("Não foi possivel encontrar a prova, Verifique!");
            cadNota();
            return;
        }

        listaAlunos(provaSelecionada.getAlunos());
        escreve("insira o id do aluno que vai receber a nota");

        int id_aluno = sc.nextInt();//até aqui eu quero saber qual aluno vai receber a nota
        Aluno alunoSelecionado = null;
        for (Aluno a : studante) {
            if (a.getId() == id_aluno) {
                alunoSelecionado = a;
                break;
            }
        }
        if (alunoSelecionado == null) {
            escreve("Não foi possivel encontrar o aluno, Verifique!");
            cadNota();
            return;
        }

        escreve("Qual a nota do aluno " + alunoSelecionado.getNome() + " ?");
        double notaAplicada = sc.nextDouble();
        alunoSelecionado.setNota(notaAplicada);
        provaSelecionada.boletim();
        escreve("Deseja cadastrar outra nota?[1]sim[2]não");
        int opc = sc.nextInt();
        switch (opc) {
            case 1:
                cadNota();
                break;
            case 2:
                menu();
                break;
            default:
                escreve("opção inválida!");
                cadNota();
                break;
        }
        //aqui preciso relacionar o aluno com a sua nota, devo começar com banco de dados ?
    }

    public static void main(String[] args) {
        //definir um menu onde se pesquisa ou define os alunos 
        //cada aluno vai ter quatro notas, a primeira versão pode funcionar só ao cadastrar um aluno
        menu();
        //  banco.CarrgarAlunos(studante);
        //  for (Aluno alino : studante) {
        //            escreve(alino.getId());
        //        }

        //testando cadBoletim
        /*      studante = new Aluno[3];
        studante[0] = new Aluno("matheus");
        studante[1] = new Aluno("Jorge");
        studante[2] = new Aluno("Marcos");
        cadBoletim();
         */
 /* Notas provaTeste = new Notas(7);
        
        banco.CarrgarAlunos(studante);
        listaAlunos(studante);*/
    }

}
