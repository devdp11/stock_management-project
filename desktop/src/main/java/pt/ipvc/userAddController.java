package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import pt.ipvc.bll.RoleBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Role;
import pt.ipvc.dal.Users;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

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
        if (!usernameTextField.getText().isEmpty() && !phoneTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !roleComboBox.getSelectionModel().isEmpty()){
            boolean phoneNotExist = UsersBLL.checkPhone(phoneTextField.getText());
            boolean emailNotExist = UsersBLL.checkEmail(emailTextField.getText());
            if (!phoneNotExist){
                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Phone number not valid!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == continueButtonType) {
                    alert.close();
                }
            }
            else if (!emailNotExist){
                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Email not valid!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == continueButtonType) {
                    alert.close();
                }
            } else if (phoneNotExist && emailNotExist){
                Users newUser = new Users();
                newUser.setName(usernameTextField.getText());
                newUser.setPhone(phoneTextField.getText());
                newUser.setEmail(emailTextField.getText());
                newUser.setPassword(passwordTextField.getText());
                String role = roleComboBox.getSelectionModel().getSelectedItem();
                Role tRole = RoleBLL.getbydescription(role);
                newUser.setIdRole(tRole.getId()); ;
                UsersBLL.create(newUser);

                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Added the user sucessfully!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == continueButtonType) {
                    alert.close();
                }
            }
        } else {
            ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have to input information in the fields!", continueButtonType);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStyleClass().add("alert");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == continueButtonType) {
                alert.close();
            }
        }

    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){

    }
}
