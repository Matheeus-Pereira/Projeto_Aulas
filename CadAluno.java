package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class CadAluno {
    
    @FXML
    private TextField nomeAluno;
    
    @FXML
    private Label Resultado;
    
    @FXML
    private void Menu() throws IOException {
        App.setRoot("ControleGeral");
    }
    Conexao banco = new Conexao();

    @FXML
    private void Cad_aluno() throws IOException {
        String nome = nomeAluno.getText();
        Resultado.setText(banco.InsertAluno(nome));
        //Resultado.setText("Nome digitado: " + nome);
    }
}
