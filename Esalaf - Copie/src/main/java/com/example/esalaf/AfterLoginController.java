package com.example.esalaf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AfterLoginController {
    @FXML
    private Button button_logout;
    @FXML
    private Button button_client;
    @FXML
    private Button button_produit;
    @FXML
    private Button button_credit;
    @FXML
    private Button cancelButton;
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void userLogOut(ActionEvent event) throws IOException{
        Stage stage = (Stage) button_logout.getScene().getWindow();
        Main m = new Main();
        m.changeScene("login.fxml");
    }

    public void entrerClient(ActionEvent event) throws IOException{
        Stage stage = (Stage) button_client.getScene().getWindow();
        Main m = new Main();
        m.changeScene("client.fxml");
    }
    public void entrerProduit(ActionEvent event) throws IOException{
        Stage stage = (Stage) button_client.getScene().getWindow();
        Main m = new Main();
        m.changeScene("produit.fxml");
    }
    public void entrerCredit(ActionEvent event) throws IOException{
        Stage stage = (Stage) button_credit.getScene().getWindow();
        Main m = new Main();
        m.changeScene("credit.fxml");
    }
}
