package pt.ipvc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Users;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class loginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if(email.isEmpty() || password.isEmpty()){
            ButtonType returnButtonType = new ButtonType("Return", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You have to fill the email & password fields in order to log in!", returnButtonType);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStyleClass().add("alert");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == returnButtonType) {
                alert.close();
            }
        } else if(UsersBLL.checkLogin(email, password)){
            Users currentUser = UsersBLL.getLogin(email, password);
            if (currentUser.getIdRole() == 1 || currentUser.getIdRole() == 2 /*|| users.getRoleByIdRole().getDescription().equals("admin") || users.getRoleByIdRole().getDescription().equals("manager")*/) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();
                homeController controller = loader.getController();
                controller.setCurrentUser(currentUser);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                ButtonType returnButtonType = new ButtonType("Return", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The user cannot acess this application!", returnButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == returnButtonType) {
                    alert.close();
                }
            }
        }
        else {
            ButtonType returnButtonType = new ButtonType("Return", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "That user does not exist!", returnButtonType);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            DialogPane alertDialog = alert.getDialogPane();
            alertDialog.getStyleClass().add("alert");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == returnButtonType) {
                alert.close();
            }
        }
    }
}