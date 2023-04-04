package com.example.esalaf;

import com.exemple.model.BaseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController {
    public void SignUpController(){

    }

    @FXML
    public Button button_login;
    @FXML
    public Button login;
    @FXML
    public Label wronglogin;
    @FXML
    public TextField tf_username;
    @FXML
    public TextField tf_phone;

    public void userLogIn(ActionEvent event) throws IOException {
        Stage stage = (Stage) button_login.getScene().getWindow();
        checkLogin(stage);
    }
    public void Onlogin() throws IOException {
        Main m = new Main();
        m.changeScene("login.fxml");

    }

    private void checkLogin(Stage stage) throws IOException {
        Main m =new Main();
        if (tf_username.getText().toString().equals("salma") && tf_phone.getText().toString().equals("123")){
            wronglogin.setText("inscrit avec succes!");

        }
        else if(tf_username.getText().isEmpty() && tf_phone.getText().isEmpty()){
            wronglogin.setText("entrer vos informations");
        }
    }
    private void checKKLogin(Stage stage) throws IOException {
        Main m =new Main();
        if (tf_username.getText().toString().equals("salma") && tf_phone.getText().toString().equals("123")){
            wronglogin.setText("inscrit avec succes!");

        }
        else if(tf_username.getText().isEmpty() && tf_phone.getText().isEmpty()){
            wronglogin.setText("entrer vos information");
        }
    }
}

