package com.example.esalaf;

import com.exemple.model.Produit;
import com.exemple.model.ProduitDAO;
import com.exemple.model.Produit;
import com.exemple.model.ProduitDAO;
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
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField prix;


    @FXML
    private TableView<Produit> mytab;

    @FXML
    private TableColumn<Produit, Long> col_id;

    @FXML
    private TableColumn<Produit, String> col_nom;
    @FXML
    private TableColumn<Produit, String> col_prix;
    @FXML
    private TextField idProduit ;
    @FXML
    private Button cancelButton ;
    public ProduitController() {
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
        Produit selectedProduit = mytab.getSelectionModel().getSelectedItem();
        idProduit.setText(String.valueOf(selectedProduit.getId_produit()));
        nom.setText(selectedProduit.getNom());
        prix.setText(String.valueOf(selectedProduit.getPrix()));
    }
    @FXML
    protected void onDeleteButtonClick(){

        try {
            ProduitDAO prodao;
            prodao = new ProduitDAO();


            int myIndex = mytab.getSelectionModel().getSelectedIndex();

            int idValue = Integer.parseInt(String.valueOf(mytab.getItems().get(myIndex).getId_produit()));
            Produit pro = new Produit(idValue, nom.getText(), prix.getText());

            prodao.delete(pro);
            idProduit.setText("");
            nom.setText("");
            prix.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }


    @FXML
    protected void onSaveButtonClick(){

        Produit pro = new Produit(0 , nom.getText() , prix.getText());

        try {
            ProduitDAO prodao = new ProduitDAO();

            prodao.save(pro);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();

    }




    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit,Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));

        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));


        mytab.setItems(getDataProduits());
    }

    public static ObservableList<Produit> getDataProduits(){

        ProduitDAO prodao = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            prodao = new ProduitDAO();
            for(Produit ettemp : prodao.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }
    @FXML
    protected void onUpdateButtonClick() {
        try {
            ProduitDAO produitDAO = new ProduitDAO();

            int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                // Aucun client n'a été sélectionné, rien à faire
                return;
            }

            Produit selectedProduit = mytab.getSelectionModel().getSelectedItem();
            int id = selectedProduit.getId_produit();
            String nom = this.nom.getText();
            String prix = this.prix.getText();

            // Met à jour l'objet client avec les nouvelles valeurs
            selectedProduit.setNom(nom);
            selectedProduit.setPrix(prix);

            // Met à jour le client dans la base de données
            produitDAO.update(selectedProduit);

            // Réinitialise les champs de texte
            this.idProduit.setText("");
            this.nom.setText("");
            this.prix.setText("");

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
