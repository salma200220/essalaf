package com.example.esalaf;

import com.exemple.model.Client;
import com.exemple.model.ClientDAO;
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

public class HelloController implements Initializable {
    @FXML
    public Button ok;
    @FXML
    public Button cancelButton;
    @FXML
    public Button delete ;

    @FXML
    private TextField nom;

    @FXML
    private TextField tele;

    @FXML
    private TextField idClient ;
    @FXML
    private TextField searchField;

    @FXML
    private TableView<Client> mytab;

    @FXML
    private TableColumn<Client, Long> col_id;

    @FXML
    private TableColumn<Client, String> col_nom;

    @FXML
    private TableColumn<Client, String> col_tele;


    @FXML
    protected void onSaveButtonClick(){

        Client cli = new Client(0, nom.getText() , tele.getText());

        try {
            ClientDAO clidao = new ClientDAO();
            clidao.save(cli);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        UpdateTable();

    }
    int index = -1;
    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = mytab.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        Client selectedClient = mytab.getSelectionModel().getSelectedItem();
        idClient.setText(String.valueOf(selectedClient.getId_client()));
        nom.setText(selectedClient.getNom());
        tele.setText(String.valueOf(selectedClient.getTelephone()));
    }

    //Delete methode
    @FXML
    protected void onDeleteButtonClick(){

        try {
            ClientDAO clidao;
            clidao = new ClientDAO();


            int myIndex = mytab.getSelectionModel().getSelectedIndex();

            int idValue = Integer.parseInt(String.valueOf(mytab.getItems().get(myIndex).getId_client()));
            Client cli = new Client(idValue, nom.getText(), tele.getText());

            clidao.delete(cli);
            idClient.setText("");
            nom.setText("");
            tele.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }


//Delete methode Ends here



    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        col_tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));


        mytab.setItems(getDataClients());
    }

    public static ObservableList<Client> getDataClients(){

        ClientDAO clidao;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clidao = new ClientDAO();
            listfx.addAll(clidao.getAll());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }


public void OnGoHomeClick() throws IOException {
        Main m = new Main();
        m.changeScene("afterLogin.fxml");

        }
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void onUpdateButtonClick(ActionEvent event) {
        try {
            ClientDAO clientDAO = new ClientDAO();

            int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                // Aucun client n'a été sélectionné, rien à faire
                return;
            }

            Client selectedClient = mytab.getSelectionModel().getSelectedItem();
            int id = selectedClient.getId_client();
            String nom = this.nom.getText();
            String telephone = this.tele.getText();

            // Met à jour l'objet client avec les nouvelles valeurs
            selectedClient.setNom(nom);
            selectedClient.setTelephone(telephone);

            // Met à jour le client dans la base de données
            clientDAO.update(selectedClient);

            // Réinitialise les champs de texte
            this.idClient.setText("");
            this.nom.setText("");
            this.tele.setText("");

            // Met à jour la table
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onSearchButtonClick() {
        try {
            String searchText = this.searchField.getText();
            ClientDAO clientDAO = new ClientDAO();
            List<Client> matchingClients = clientDAO.search(searchText);
            mytab.setItems(FXCollections.observableArrayList(matchingClients));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}