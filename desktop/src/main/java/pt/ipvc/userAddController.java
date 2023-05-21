package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pt.ipvc.bll.RoleBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Role;
import pt.ipvc.dal.Users;

import java.nio.channels.Pipe;
import java.util.List;

public class userAddController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Label label;

    @FXML
    private void selectRole(ActionEvent event){

    }

    @FXML
    private void initialize(){
        List<Role> roles = RoleBLL.index();
        ObservableList<String> tUser = FXCollections.observableArrayList();
        for (Role tp: roles){
            tUser.add(tp.getDescription());
        }
        roleComboBox.setItems(tUser);
    }
    @FXML
    public void onAddButtonClick(ActionEvent event){
        if (!usernameTextField.getText().isEmpty()){
            boolean phoneNotExist = UsersBLL.checkPhone(phoneTextField.getText());
            boolean emailNotExist = UsersBLL.checkEmail(emailTextField.getText());
            if (!phoneNotExist){
                label.setText("Phone existente");
                }
            else if (!emailNotExist){
                label.setText("Email existente");
            }
            else if (phoneNotExist && emailNotExist){
                Users newUser = new Users();
                newUser.setName(usernameTextField.getText());
                newUser.setPhone(phoneTextField.getText());
                newUser.setEmail(emailTextField.getText());
                newUser.setPassword(passwordTextField.getText());
                String role = roleComboBox.getSelectionModel().getSelectedItem();
                Role tRole = RoleBLL.getbydescription(role);
                newUser.setIdRole(tRole.getId()); ;
                UsersBLL.create(newUser);
                label.setText("Created User");
            }

        } else {
            label.setText("É necessario Inserir os dados");
        }

    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){
        System.out.println("Cancel Button was Clicked");

    }
}
