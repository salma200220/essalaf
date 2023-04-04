package com.example.esalaf;
import com.exemple.model.Credit;
import com.exemple.model.CreditDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreditController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField credi;
    @FXML
    private TableView<Credit> mytab;

    @FXML
    private TableColumn<Credit, Long> col_id;

    @FXML
    private TableColumn<Credit, String> col_nom;

    @FXML
    private TableColumn<Credit, String> col_credit;

    @FXML
    private TextField idCredit;
    @FXML
    private Button cancelButton;
    @FXML
    private Button searchField;
    public CreditController() {
    }
    public void OnGoHomeClick() throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");

    }
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    int index = -1;
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = mytab.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        Credit selectedCredit = mytab.getSelectionModel().getSelectedItem();
        idCredit.setText(String.valueOf(selectedCredit.getId_credit()));
        nom.setText(selectedCredit.getNom());
        credi.setText(String.valueOf(selectedCredit.getCredi()));
    }
    @FXML
    public void onDeleteButtonClick() {
        try {
            CreditDAO credao;
            credao = new CreditDAO();


            int myIndex = mytab.getSelectionModel().getSelectedIndex();

            int idValue = Integer.parseInt(String.valueOf(mytab.getItems().get(myIndex).getId_credit()));
            Credit cre = new Credit(idValue, nom.getText(), credi.getText());

            credao.delete(cre);
            idCredit.setText("");
            nom.setText("");
            credi.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }


    public void onSaveButtonClick() {
        Credit cre = new Credit(0 , nom.getText() , credi.getText());

        try {
            CreditDAO credao = new CreditDAO();

            credao.save(cre);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();

    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Credit,Long>("id_credit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Credit,String>("nom"));

        col_credit.setCellValueFactory(new PropertyValueFactory<Credit,String>("credi"));


        mytab.setItems(getDataCredits());
    }
    public static ObservableList<Credit> getDataCredits(){

        CreditDAO credao = null;

        ObservableList<Credit> listfx = FXCollections.observableArrayList();

        try {
            credao = new CreditDAO();
            for(Credit ettemp : credao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }
    /*@FXML
    protected void onSearchButtonClick() {
        try {
            CreditDAO creditDAO = new CreditDAO();
            String query = searchField.getText();
            List<Credit> result = creditDAO.search(query);
            ObservableList<Credit> data = FXCollections.observableArrayList(result);
            mytab.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    @FXML
    protected void onUpdateButtonClick() {
        try {
            CreditDAO creditDAO = new CreditDAO();

            int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                // Aucun credit n'a été sélectionné, rien à faire
                return;
            }

            Credit selectedCredit = mytab.getSelectionModel().getSelectedItem();
            int id = selectedCredit.getId_credit();
            String nom = this.nom.getText();
            String credi = this.credi.getText();

            // Met à jour l'objet client avec les nouvelles valeurs
            selectedCredit.setNom(nom);
            selectedCredit.setCredi(credi);

            // Met à jour le client dans la base de données
            creditDAO.update(selectedCredit);

            // Réinitialise les champs de texte
            this.idCredit.setText("");
            this.nom.setText("");
            this.credi.setText("");

            // Met à jour la table
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
}
