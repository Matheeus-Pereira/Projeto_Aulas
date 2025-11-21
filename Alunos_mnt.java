package com.mycompany.mavenproject1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class Alunos_mnt {

    @FXML
    private TableView<Provas> TabProvas;
    @FXML
    private TableColumn<Provas, String> NomeProva;
    @FXML
    private TableView<Aluno> TabAlunos;
    @FXML
    private TableColumn<Aluno, String> NomeAluno;

    private Conexao banco = new Conexao();

//    private List<Provas> boletim = new ArrayList<>();
//    private List<Aluno> studantes = new ArrayList<>();
    ObservableList<Provas> boletim = FXCollections.observableArrayList();
    ObservableList<Aluno> studantes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        TabProvas.setItems(boletim);
        NomeProva.setCellValueFactory(new PropertyValueFactory<>("prova"));
        TabAlunos.setItems(studantes);
        NomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        banco.CarrgarProvas(boletim);
        banco.AlunosGeral(studantes);
    }

    @FXML
    private void removeProva() {
        Provas selecionada = TabProvas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exclusão de prova");
        alert.setHeaderText("Tem certeza que deseja excluir a prova? \ntodas as notas associadas serão excluidas!!");
        alert.setContentText("Isso não poderá ser desfeito.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // usuário clicou OK
            selecionada.excluiProva();
            boletim.removeIf(p -> p.getId() == selecionada.getId());
            TabProvas.refresh();

        } else {
            // usuário cancelou
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exclusão cancelada");
            alerta.setHeaderText(null);
            alerta.setContentText("Exclusão cancelada!!");
            alerta.showAndWait();
        }
    }

    @FXML
    private void removeAluno() {
        Aluno selecionado = TabAlunos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exclusão de Aluno");
        alert.setHeaderText("Tem certeza que deseja excluir o aluno? \ntodas as informações associadas serão excluidas!!");
        alert.setContentText("Isso não poderá ser desfeito.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            selecionado.excluiAluno();
            studantes.removeIf(s -> s.getId() == selecionado.getId());
            TabAlunos.refresh();
        }
    }

    @FXML
    private void alteraProva() {
        Provas selecionada = TabProvas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            return;
        }
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Alteração de Prova");
        dialogo.setHeaderText("Informe o novo titulo da Prova " + selecionada.getProva() + ":");
        dialogo.setContentText("Nome:");

        Optional<String> resultado = dialogo.showAndWait();

        if (resultado.isPresent()) {
            String nm = resultado.get();
            selecionada.setProva(nm);
            TabProvas.refresh();
        }

    }

    @FXML
    private void alteraAluno() {
        Aluno selecionado = TabAlunos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            return;
        }
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Alteração de Aluno");
        dialogo.setHeaderText("Informe o novo nome do aluno " + selecionado.getNome() + ":");
        dialogo.setContentText("Nome:");

        Optional<String> resultado = dialogo.showAndWait();

        if (resultado.isPresent()) {
            String nm = resultado.get();
            selecionado.setNome(nm);
            TabAlunos.refresh();
        }

    }
}
