package pt.ipvc;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


import pt.ipvc.bll.RoleBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Role;
import pt.ipvc.dal.Users;

import java.util.*;


public class userController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Users> dataView;
    @FXML
    private TableColumn<Users, String> nameUserColumn;
    @FXML
    private TableColumn<Users, String> phoneUserColumn;
    @FXML
    private TableColumn<Users, String> emailUserColumn;
    @FXML
    private TableColumn<Users, String> passwordUserColumn;
    @FXML
    private TableColumn<Users, String> roleUserColumn;
    @FXML
    private TextField searchBar;

    /*@FXML
    private void initialize(){
        List<Users> users = UsersBLL.index();
        Collections.sort(users, Comparator.comparingInt(user -> user.getId()));
        ObservableList<Users> data = FXCollections.observableArrayList(users);

        dataView.setItems(data);
        nameUserColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        phoneUserColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getPhone())));
        emailUserColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getEmail())));
        passwordUserColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getPassword())));
        roleUserColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getRoleByIdRole().getDescription())));
    } */
    @FXML
    public void onHomeButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onUsersButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onProductsButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onOrdersButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("order.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        System.out.println();
    }
    @FXML
    public void onAddUserButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_add.fxml"));
        Parent root = fxmlLoader.load();
        Scene popupScene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Adding User..");
        popupStage.setResizable(false);
        popupStage.show();
    }
    @FXML
    public void onEditUserButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user_edit.fxml"));
        Parent root = fxmlLoader.load();
        Scene popupScene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Editing User..");
        popupStage.setResizable(false);
        popupStage.show();
    }
    @FXML
    public void onDeleteUserButtonClick(ActionEvent event) throws IOException {
      //  Users selectedUtilizador = dataView.getSelectionModel().getSelectedItem();
      //  UsersBLL.remove(selectedUtilizador.getId());

    }
}