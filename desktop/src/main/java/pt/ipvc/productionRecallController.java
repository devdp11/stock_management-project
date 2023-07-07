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
import javafx.stage.Stage;

import javafx.stage.Modality;
import javafx.util.StringConverter;
import pt.ipvc.bll.*;
import pt.ipvc.dal.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class productionRecallController {

    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField priceTextField;

    @FXML
    private TextField producedQuantityTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<Storage> storageComboBox;

    @FXML
    public void initialize() {
        List<Storage> storages = StorageBLL.index();
        ObservableList<Storage> storageList = FXCollections.observableArrayList(storages);
        storageComboBox.setItems(storageList);
        storageComboBox.setConverter(new StringConverter<Storage>() {
            @Override
            public String toString(Storage storage) {
                return storage.getName();
            }
            @Override
            public Storage fromString(String string) {
                return null;
            }
        });
    }

    private Production production;
    private Stock stock;

    public void setProduction(Production production) {
        this.production = production;

        ObservableList<String> tProduction = FXCollections.observableArrayList();

        descriptionTextField.setText(production.getDescription());

        Production retrievedProduction = ProductionBLL.getById(production.getId());
        if (retrievedProduction != null) {
            descriptionTextField.setText(retrievedProduction.getDescription());
            producedQuantityTextField.setText(String.valueOf(retrievedProduction.getWantedQuantity()));
            datePicker.setValue(LocalDate.parse(retrievedProduction.getData()));

            stock = new Stock();
            stock.setIdProduction(retrievedProduction.getId());
            stock.setDescription(retrievedProduction.getDescription());

            storageComboBox.setValue(getSelectedStorageById(stock.getIdStorage()));
        }
    }


    @FXML
    public void onRecallButtonClick(ActionEvent event) throws IOException {
        if (production != null) {
            stock = new Stock();
            stock.setDescription(descriptionTextField.getText());
            stock.setProducedQuantity(Integer.parseInt(producedQuantityTextField.getText()));
            stock.setPrice(Double.parseDouble(priceTextField.getText()));
            stock.setDate(String.valueOf(datePicker.getValue()));
            stock.setIdProduction(production.getId());

            Storage selectedStorage = storageComboBox.getValue();

            if (selectedStorage != null) {
                stock.setIdStorage(selectedStorage.getId());

                StockBLL.create(stock);

                production.setState("Already recalled");

                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Recalled successfully!", continueButtonType);
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Storage not found!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }

    private Storage getSelectedStorageById(int id) {
        List<Storage> storages = StorageBLL.index();
        for (Storage storage : storages) {
            if (storage.getId() == id) {
                return storage;
            }
        }
        return null;
    }
}
