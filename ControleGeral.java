package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControleGeral {

    boolean JAluno = false;

    @FXML
    private void cadAluno() throws IOException {
        if (JAluno) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadAluno.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Cadastro de Aluno");
        stage.setScene(new Scene(root));
        stage.show();

        stage.setOnCloseRequest(e -> JAluno = false);
        JAluno = true;
        //App.setRoot("CadAluno");
    } //nesse padrão posso ir atribuindo aos botões no fxml

    boolean CADprova = false;

    @FXML
    private void CadProva() throws IOException {
        if (CADprova) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadProva.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Cadastrar prova");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnCloseRequest(e -> CADprova = false);
        CADprova = true;
        //App.setRoot("CadProva");
    }
    boolean mntaln = false;

    @FXML
    private void MNTaluno() throws IOException {
        if (mntaln) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Alunos_mnt.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Manutenção");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnCloseRequest(e -> mntaln = false);
        mntaln = true;
//        App.setRoot("CadNota");
    }

    boolean CADnota = false;

    @FXML
    private void Cadnota() throws IOException {
        if (CADnota) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CadNota.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Cadastrar nota");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnCloseRequest(e -> CADnota = false);
        CADnota = true;
        //App.setRoot("CadNota");
    }
}
