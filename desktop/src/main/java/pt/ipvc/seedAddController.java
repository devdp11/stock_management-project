package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.RoleBLL;
import pt.ipvc.bll.SeedsBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Role;
import pt.ipvc.dal.Seeds;
import pt.ipvc.dal.Users;

import java.util.List;
import java.util.Optional;

public class seedAddController {

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField dateTextField;

    @FXML
    private ComboBox<String> supplierComboBox;
    @FXML
    private ComboBox<String> managerComboBox;

    @FXML
    private void initialize(){
    }

    @FXML
    public void onRequestButtonClick(ActionEvent event){


    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){
        System.out.println("Cancel Button was Clicked");

    }
}
