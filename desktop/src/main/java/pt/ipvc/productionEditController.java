package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.*;
import pt.ipvc.dal.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class productionEditController {
    @FXML
    private TextField descriptionTextField;
    @FXML
    private ComboBox<String> seedsComboBox;
    @FXML
    private TextField wantedQuantityTextField;
    @FXML
    private TextField seedsQuantityTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private Label quantityLabel;

    private Production production;

    public void setProduction(Production production) {
        this.production = production;

        List<Seeds> seedsList = SeedsBLL.index();
        ObservableList<String> tSeeds = FXCollections.observableArrayList();
        for (Seeds seeds : seedsList) {
            tSeeds.add(seeds.getDescription());
        }
        seedsComboBox.setItems(tSeeds);

        Seeds selectedSeed = production.getSeedByIdSeed();
        if (selectedSeed != null) {
            seedsComboBox.setValue(selectedSeed.getDescription());

            int quantityRequested = selectedSeed.getQuantityRequested();
            quantityLabel.setText("Seed Quantity in stock: " + quantityRequested);
        } else {
            seedsComboBox.setValue("");
            quantityLabel.setText("");
        }

        descriptionTextField.setText(String.valueOf(production.getDescription()));
        wantedQuantityTextField.setText(String.valueOf(production.getWantedQuantity()));
        seedsQuantityTextField.setText(String.valueOf(production.getSeedsQuantity()));
        datePicker.setValue(LocalDate.parse(production.getData()));

        descriptionTextField.setEditable(false);
        wantedQuantityTextField.setEditable(false);
        seedsQuantityTextField.setEditable(false);
        datePicker.setDisable(true);
        seedsComboBox.setDisable(true);
        if (production.getState().equals("Ready to recall")){
            List<String> states = Arrays.asList("Already recalled");
            ObservableList<String> tState = FXCollections.observableArrayList(states);
            stateComboBox.setItems(tState);
            stateComboBox.setValue(production.getState());
        } else {
            List<String> states = Arrays.asList("In preparation", "Growing up", "Ready to recall");
            ObservableList<String> tState = FXCollections.observableArrayList(states);
            stateComboBox.setItems(tState);
            stateComboBox.setValue(production.getState());
        }
    }

    @FXML
    public void onEditStateButtonClick(ActionEvent event) throws IOException {
        if (production != null) {
            production.setState(stateComboBox.getValue());

            ProductionBLL.update(production);

            ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Edited production state successfully!", continueButtonType);
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Invalid Production Selection!", continueButtonType);
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
    public void onSeedsComboBoxSelected(ActionEvent event) {
        // no longer needed
    }
}
