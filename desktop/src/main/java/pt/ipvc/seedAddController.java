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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import java.sql.Date;
import java.time.LocalDate;


public class seedAddController {

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> supplierComboBox;


    @FXML
    private void initialize() {
        List<Users> suppliers = (List<Users>) UsersBLL.getByRole("supplier");
        ObservableList<String> supplierNames = FXCollections.observableArrayList();
        for (Users supplier : suppliers) {
            supplierNames.add(supplier.getName());
        }
        supplierComboBox.setItems(supplierNames);
    }

    @FXML
    public void onRequestButtonClick(ActionEvent event) {
        if (!descriptionTextField.getText().isEmpty() && !quantityTextField.getText().isEmpty() && datePicker.getValue() != null && !supplierComboBox.getSelectionModel().isEmpty()) {

            Seeds newSeed = new Seeds();
                newSeed.setDescription(descriptionTextField.getText());
                newSeed.setQuantityRequested(Integer.parseInt(quantityTextField.getText()));

                LocalDate localDate = datePicker.getValue();
                Date sqlDate = Date.valueOf(localDate);

                newSeed.setDate(sqlDate.toString());

                String supplier = supplierComboBox.getSelectionModel().getSelectedItem();
                List<Users> suppliers = UsersBLL.getByRole("supplier");

                Users userS = null;
                for (Users supplierUser : suppliers) {
                    if (supplierUser.getName().equals(supplier)) {
                        userS = supplierUser;
                        break;
                    }
                }

                if (userS != null) {
                    newSeed.setIdSupplier(userS.getId());
                    SeedsBLL.create(newSeed);

                    ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Requested the seed successfully!", continueButtonType);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    DialogPane alertDialog = alert.getDialogPane();
                    alertDialog.getStyleClass().add("alert");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == continueButtonType) {
                        alert.close();
                    }
                } else {
                    ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supplier not found!", continueButtonType);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    DialogPane alertDialog = alert.getDialogPane();
                    alertDialog.getStyleClass().add("alert");
                    Optional<ButtonType> result = alert.showAndWait();
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
}
