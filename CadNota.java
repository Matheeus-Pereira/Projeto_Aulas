package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CadNota {

    @FXML
    private Label Resultado;
    @FXML
    private TextField Nota;
    @FXML
    private TextField ALNSelecionado;
    @FXML
    private TextArea ListaAlunos;

    @FXML
    private TableView<Provas> ProvasAlunos;
    @FXML
    private TableColumn<Provas, Integer> ProvaID;
    @FXML
    private TableColumn<Provas, String> ListaProvas;


    /*  public List<String> lista1 = List.of(
            "Matheus", "Jorje");
    public List<String> lista2 = List.of(
            "julia");*/
    private Conexao banco = new Conexao();
    public List<Provas> boletim = new ArrayList<>();

    @FXML
    public void initialize() {
        banco.CarrgarProvas(boletim);//carrega as provas do banco

        ObservableList<Provas> ProvaAaluno = FXCollections.observableArrayList(boletim);
        ProvasAlunos.setItems(ProvaAaluno);
        ProvaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ListaProvas.setCellValueFactory(new PropertyValueFactory<>("prova"));

//revisar codigo e inserir notas dos alunos
        ProvasAlunos.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                int idSelecionado = newValue.getId();
                mostrarAlunosPorId(idSelecionado);
            }
        });

    }

    private void mostrarAlunosPorId(int id) {

        for (int i = 0; i < boletim.size(); i++) {
            if (boletim.get(i).getId() == id) {
                List<Aluno> alunos = boletim.get(i).getAluno();
                // System.out.println(prv.getAluno());
                //String nomes = String.join("\n", alunos.get(i).getNome()); // cada aluno em uma linha
                StringBuilder nomes = new StringBuilder();
                for (Aluno aln : alunos) {
                    nomes.append(aln.getNome() + "\n");
                }
                ListaAlunos.setText(nomes.toString());
                break;
            }
        }
    }

    @FXML
    private void ClicouAluno(MouseEvent click) {
        String selecionado = ListaAlunos.getSelectedText();
        if (selecionado != null && !selecionado.isEmpty()) {
            ALNSelecionado.setText(selecionado.trim());
        }
    }

    @FXML
    private void ColetaNota() {
        Aluno alunoSelecionado = null;
        Provas selecionada = ProvasAlunos.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            Resultado.setText("Selecione uma prova primeiro");
            return;
        }

        int idprova = selecionada.getId();
        String nmprova = selecionada.getProva();
        String nmaluno = ALNSelecionado.getText();
        if (nmaluno == null || nmaluno.isBlank()) {
            Resultado.setText("Selecione um Aluno!");
            return;
        }
        int notaSelecionada;
        try {
            notaSelecionada = Integer.parseInt(Nota.getText());
        } catch (NumberFormatException e) {
            Resultado.setText("Nota inváida!\n Informe apenas numeros!");
            return;
        }
        if (notaSelecionada < 0 || notaSelecionada > 10) {
            Resultado.setText("Nota fora do padrão !!!\nInforme uma nota de 0 a 10!");
            return;
        }
        List<Aluno> alns = selecionada.getAluno();
        for (Aluno a : alns) {
            if (a.getNome().equals(nmaluno)) {
                alunoSelecionado = a;
                break;
            }
        }
      
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Atenção!");
        alerta.setContentText(banco.InsertNotas(idprova, alunoSelecionado.getId(), notaSelecionada));
        
//ajustar para que o id do aluno seja informado corretamente
//falta cadastrar dentro do banco com as inormações que tenho
    }
}
