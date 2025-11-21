package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class CadProva {

    @FXML
    private Label Resultado;
    @FXML
    private TextField NMprova;
    @FXML
    private ListView<Aluno> ListaAlunos;

    private Conexao banco = new Conexao();

    @FXML
    public void initialize() {

        List<Aluno> studantes = new ArrayList<>();
        banco.AlunosGeral(studantes);
        //buscar no banco e utilizar os objetos para cadastrar no banco
        ListaAlunos.setItems(FXCollections.observableArrayList(studantes));
        ListaAlunos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ListaAlunos.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Aluno>) change -> {
            attLabel();
        });
    }
    List<Aluno> selecionados = new ArrayList<Aluno>();

    private void attLabel() {
        selecionados = ListaAlunos.getSelectionModel().getSelectedItems();
        if (selecionados.isEmpty()) {
            Resultado.setText("Utilize CRTL para selecionar multiplos Alunos e Shift para selecionar um intervalo!");
        } else {
            String nomes = selecionados.stream().map(Aluno::getNome).collect(Collectors.joining(", "));
            Resultado.setText(nomes);
            //fazer uma lista de alunos e vincular eles com  a prova
        }

    }

    @FXML
    private void RegistraProva() {
        if (NMprova.getText() == null || NMprova.getText().trim().isEmpty()) {
            Resultado.setText("Informe o nome da prova a ser cadastrada!!");
            return;
        }
        if (selecionados.isEmpty()) {
            Resultado.setText("Selecione quais alunos vão fazer a prova!!");
            return;
        }

        Notas novaprova = new Notas(NMprova.getText());
        for (Aluno a : selecionados) {
            novaprova.VinculaAlunos(a.getId());
        }
        boolean vrfVinculo = true;
        for (Aluno a : selecionados) {
            boolean situacaoAluno = banco.VinculoProvaAluno(a.getId());
            if (situacaoAluno == false) {
                vrfVinculo = false;
                return;
            }
        }
        if (vrfVinculo == false) {
            Resultado.setText("Ocorreu um problema ao vincular os alunos, Verifique!");
        }else{
            Resultado.setText("Prova cadastrada com sucesso!");
        }
            

    }

}
