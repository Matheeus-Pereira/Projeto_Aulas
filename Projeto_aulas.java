package projeto_aulas;

import java.util.Scanner;
import java.util.ArrayList;

public class Projeto_aulas extends Fclt {

    public static Scanner sc = new Scanner(System.in);
    public static Aluno studante[];
    public static ArrayList<Notas> boletim = new ArrayList<Notas>();

    public static void menu() {
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
        }

    }

    public static void voltar() {
        escreve("Voltar[1]");
        int volt = sc.nextInt();
        if (volt == 1) {
            menu();
        }
    }

    public static void cadAluno() {
        escreve("Quantos alunos serão cadastrados?");
        int quant = sc.nextInt();
        studante = new Aluno[quant];
        for (int i = 0; i < quant; i++) {
            escreve("Digite o nome do aluno " + (i + 1) + ": ");
            String nome = sc.next();

            studante[i] = new Aluno(nome, i);
            //é necessário buscar  a numeração do aluno no banco depois, então não vai ter numeração 
        }
        voltar();
    }

    public static void consAluno() {
        if (studante == null || studante.length == 0) {
            escreve("Nenhum aluno foi cadastrado ainda!");
            voltar();
            return;
            //essa valdação tem que ocorrer no banco depois
        }
        for (int i = 0; i < studante.length; i++) {

            studante[i].apresentar();
            //provavel que se mantenha assim e a função do aluno consulte no banco
        }
        voltar();
    }

    public static void ListaAlunos() {
        for (int i = 0; i < studante.length; i++) {

            escreve(studante[i].getNome());
        }

    }

    public static void ListaProvas() {
        for (int i = 0; i < boletim.size(); i++) {
            escreve("Prova: " + boletim.get(i).getProva());
        }
    }

    public static void cadBoletim() {

        escreve("Titulo da prova:");
        String ttl = sc.next(); //para setar a prova

        escreve("Quantos alunos fizeram a prova?");//para setar a prova
        int qtdnt = sc.nextInt();//quantidade de vezes que será informado o aluno

        Notas novaProva = new Notas(ttl, qtdnt);//prova setada titulo quant de alunos e quantas provas vai ter
        //na maioria dos casos todos os alunos fazem a prova, devo adaptar a função pra isso, esse é o caso onde escolho qual aluno 

        escreve("quais alunos fizeram a prova?(escreva exatamente o nome)");
        ListaAlunos();//mostra os alunos pra não ter erro
        sc.nextLine();//limpa 

        for (int i = 0; i < qtdnt; i++) {
            escreve("Nome do aluno " + (i + 1) + ": ");
            String nome = sc.nextLine();
            Aluno alunoEncontrado = null;//cada vez q o loop iniciar esse aluno vai estar nulo, pra ver se tem um aluno com aquele nome

            for (Aluno s : studante) {
                if (s.getNome().equalsIgnoreCase(nome)) {
                    alunoEncontrado = s;
                    break;
                }

            }

            if (alunoEncontrado != null) {//compara o nome recebido e o nome do aluno, se for nulo o if anterior não deu certo
                novaProva.setAlunos(i, alunoEncontrado);
                escreve("Aluno '" + nome + "' adicionado ao boletim.");
            } else {
                escreve("Aluno '" + nome + "' não encontrado na lista!");
            }

        }
        boletim.add(novaProva);
        escreve("Boletim '" + ttl + "' cadastrado com sucesso!");
        escreve("\nDeseja cadastrar um novo Boletim?\n[1]Sim\n[2]não");
        int opc = sc.nextInt();
        switch (opc) {
            case 1:
                cadBoletim();
            case 2:
                menu();
        }
    }

    public static void cadNota() {
        escreve("Informe qual prova é a nota");
        ListaProvas();
        String nmprova = sc.next();//até aqui eu quero saber qual a prova
        Notas provaSelecionada = null;
        for (Notas prova : boletim) {
            if (prova.getProva().equalsIgnoreCase(nmprova)) {
                provaSelecionada = prova;
                break;
            }
        }
        if (provaSelecionada == null) {
            escreve("Não foi possivel encontrar a prova, Verifique!");
            cadNota();
            return;
        }

        escreve("De qual aluno é a nota?");
        ListaAlunos();

        String nmAluno = sc.next();//até aqui eu quero saber qual aluno vai receber a nota
        Aluno alunoSelecionado = null;
        for (Aluno nome : studante) {
            if (nome.getNome().equalsIgnoreCase(nmAluno)) {
                alunoSelecionado = nome;
                break;
            }
        }
        if (alunoSelecionado == null) {
            escreve("Não foi possivel encontrar o aluno, Verifique!");
            return;
        }

        escreve("qual a nota do aluno " + alunoSelecionado.getNome() + " ?");
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
                return;
        }
        //aqui preciso relacionar o aluno com a sua nota, devo começar com banco de dados ?
    }

    public static void main(String[] args) {
        //definir um menu onde se pesquisa ou define os alunos 
        //cada aluno vai ter quatro notas, a primeira versão pode funcionar só ao cadastrar um aluno
        menu();

        //testando cadBoletim
        /*      studante = new Aluno[3];
        studante[0] = new Aluno("matheus");
        studante[1] = new Aluno("Jorge");
        studante[2] = new Aluno("Marcos");
        cadBoletim();
         */
    }

}
