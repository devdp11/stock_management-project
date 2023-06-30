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
        if (!descriptionTextField.getText().isEmpty() && !quantityTextField.getText().isEmpty() && !dateTextField.getText().isEmpty() && !supplierComboBox.getSelectionModel().isEmpty() && !managerComboBox.getSelectionModel().isEmpty()){
            boolean descriptionNotExist = SeedsBLL.checkDescription(descriptionTextField.getText());
            if (!descriptionNotExist){
                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Description already not valid | Edit the one that exists!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == continueButtonType) {
                    alert.close();
                }
            }
            else if (descriptionNotExist){
                Seeds newSeed = new Seeds();
                newSeed.setDescription(descriptionTextField.getText());
                newSeed.setQuantityRequested(Integer.parseInt(String.valueOf(quantityTextField)));

                String supplier = supplierComboBox.getSelectionModel().getSelectedItem();
                Users UserS = UsersBLL.getByRole(supplier);
                newSeed.setIdSupplier(UserS.getId());

                SeedsBLL.create(newSeed);


                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Created the user sucessfully!", continueButtonType);
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
        System.out.println("Cancel Button was Clicked");

    }
}
