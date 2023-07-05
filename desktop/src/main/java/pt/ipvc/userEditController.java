package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.RoleBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Role;
import pt.ipvc.dal.Users;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class userEditController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField doorTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private ComboBox<String> roleComboBox;
    private Users user;
    public void setUser(Users users) {
        this.user = users;

        List<Role> roleList = RoleBLL.index();

        ObservableList<String> tUser = FXCollections.observableArrayList();

        Role role1 = user.getRoleByIdRole();
        if (role1 != null) {
            roleComboBox.setValue(role1.getDescription());
        } else {
            roleComboBox.setValue("");
        }

        for (Role role : roleList) {
            tUser.add(role.getDescription());
        }

        nameTextField.setText(users.getName());
        phoneTextField.setText(String.valueOf(users.getPhone()));
        emailTextField.setText(users.getEmail());
        passwordTextField.setText(users.getPassword());
        roleComboBox.setItems(tUser);
        //roleComboBox.setValue(this.user.getRoleByIdRole().getDescription());
        streetTextField.setText(users.getStreet() != null ? users.getStreet() : "");
        doorTextField.setText(users.getDoor() != null ? users.getDoor() : "");
        locationTextField.setText(users.getLocation() != null ? users.getLocation() : "");
    }
    @FXML
    public void onConfirmButtonClick(ActionEvent event)throws IOException {
        if (user != null) {

            String roleUser = roleComboBox.getSelectionModel().getSelectedItem();
            Role role_user = RoleBLL.getbydescription(roleUser);

                user.setName(nameTextField.getText());
                user.setPhone(String.valueOf(Integer.parseInt(phoneTextField.getText())));
                user.setEmail(emailTextField.getText());
                user.setPassword(passwordTextField.getText());
                user.setRoleByIdRole(role_user);
                user.setStreet(streetTextField.getText());
                user.setDoor(doorTextField.getText());
                user.setLocation(locationTextField.getText());


                UsersBLL.update(user);

                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Edited the user sucessfully!", continueButtonType);
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
