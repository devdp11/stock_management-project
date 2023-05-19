package pt.ipvc;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.UsersEntity;
import pt.ipvc.view.Session;

import java.util.*;


public class userController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<UsersEntity> dataView;
    @FXML
    private TableColumn<UsersEntity, Integer> numUserColumn;
    @FXML
    private TableColumn<UsersEntity, String> nameUserColumn;
    @FXML
    private TableColumn<UsersEntity, String> phoneUserColumn;
    @FXML
    private TableColumn<UsersEntity, String> emailUserColumn;
    @FXML
    private TableColumn<UsersEntity, String> passwordUserColumn;
    @FXML
    private TableColumn<UsersEntity, String> roleUserColumn;
    @FXML
    private TextField searchBar;
    private final UsersEntity currentUser = Session.getInstance().getCurrentUser();

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
        System.out.println("Click 2");
    }
}