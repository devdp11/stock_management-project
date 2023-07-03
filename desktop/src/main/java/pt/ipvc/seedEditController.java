package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.OrdersBLL;
import pt.ipvc.bll.SeedsBLL;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Orders;
import pt.ipvc.dal.Seeds;
import pt.ipvc.dal.Stock;
import pt.ipvc.dal.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class seedEditController {
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> supplierComboBox;
    public Seeds seed;

    public void setSeed(Seeds seed) {
        this.seed = seed;

        descriptionTextField.setText(String.valueOf(seed.getDescription()));
        quantityTextField.setText(String.valueOf(seed.getQuantityRequested()));
        datePicker.setValue(LocalDate.parse(seed.getDate()));

        List<Users> userList = UsersBLL.getByRole("supplier");
        ObservableList<String> tUser = FXCollections.observableArrayList();
        for (Users user : userList) {
            tUser.add(user.getName());
        }
        supplierComboBox.setItems(tUser);

        int clientId = seed.getIdSupplier();
        for (Users user : userList) {
            if (user.getId() == clientId) {
                supplierComboBox.setValue(user.getName());
                break;
            }
        }
    }

    @FXML
    public void onRequestButtonClick(ActionEvent event) throws IOException {
        if (seed != null) {
            int SupplierId = getSelectedSupplierId();

            if (SupplierId != 0) { // Check if a valid client ID is selected
                seed.setDescription(descriptionTextField.getText());
                seed.setQuantityRequested(Integer.parseInt(quantityTextField.getText()));
                seed.setDate(String.valueOf(datePicker.getValue()));
                seed.setIdSupplier(SupplierId);

                SeedsBLL.update(seed);

                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Edited the order successfully!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == continueButtonType) {
                    alert.close();
                }

            } else {
                ButtonType continueButtonType = new ButtonType("Try Again", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Invalid Supplier Selection!", continueButtonType);
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

    private int getSelectedSupplierId() {
        String selectedSupplier = supplierComboBox.getSelectionModel().getSelectedItem();
        List<Users> suppliers = UsersBLL.getByRole("supplier");
        for (Users client : suppliers) {
            if (client.getName().equals(selectedSupplier)) {
                return client.getId();
            }
        }
        return 0;
    }

}
