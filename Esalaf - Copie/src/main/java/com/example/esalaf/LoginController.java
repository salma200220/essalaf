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

public class LoginController {
    public LoginController(){

    }

    @FXML
    public Button button_login;
    @FXML
    public Button signup;
    @FXML
    public Label wronglogin;
    @FXML
    public TextField tf_username;
    @FXML
    public TextField tf_phone;

    public void OnGoHomeClick() throws IOException {
        Main m = new Main();
        m.changeScene("sign up.fxml");

    }
    public void userLogIn(ActionEvent event) throws IOException {
        Stage stage = (Stage) button_login.getScene().getWindow();
        checkLogin(stage);
    }

    private void checkLogin(Stage stage) throws IOException {
        Main m =new Main();
        if (tf_username.getText().toString().equals("salma") && tf_phone.getText().toString().equals("123")){
            wronglogin.setText("inscrit avec succes!");
            m.changeScene("afterLogin.fxml");
        }
        else if(tf_username.getText().isEmpty() && tf_phone.getText().isEmpty()){
            wronglogin.setText("entrer vos informations");
        }
        else {
            wronglogin.setText("information incorrect");
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(actionEvent -> login());

    }

    public void login(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection con = BaseDAO.getConnection();
        try {
            st = con.prepareStatement("SELECT * FROM users WHERE USERNAME =? AND PHONE = ?");
            st.setString(1,tf_username.getText());
            st.setString(2,tf_phone.getText());
            rs = st.executeQuery();
            if (rs.next()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Login SuccessFully", ButtonType.OK);
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Login Error", ButtonType.OK);
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



