package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.ProductionBLL;
import pt.ipvc.bll.SeedsBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Production;
import pt.ipvc.dal.Seeds;
import pt.ipvc.dal.Users;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class productionScheduleController {

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

    @FXML
    private void initialize() {
        List<Seeds> seeds = SeedsBLL.index();
        ObservableList<String> tSeed = FXCollections.observableArrayList();
        for (Seeds ss : seeds) {
            tSeed.add(ss.getDescription());
        }
        seedsComboBox.setItems(tSeed);

        List<String> states = Arrays.asList("In preparation", "Growing up", "Ready to recall");
        ObservableList<String> tState = FXCollections.observableArrayList(states);
        stateComboBox.setItems(tState);
    }

    @FXML
    public void onScheduleButtonClick(ActionEvent event) {
        Production newProduction = new Production();
        newProduction.setDescription(descriptionTextField.getText());

        // Obter o ID da semente selecionada em vez da descrição
        String selectedSeed = seedsComboBox.getSelectionModel().getSelectedItem();
        Seeds seed = SeedsBLL.getbydescription(selectedSeed);
        if (seed != null) {
            newProduction.setIdSeeds(seed.getId());
        }

        newProduction.setWantedQuantity(Integer.valueOf(wantedQuantityTextField.getText()));
        newProduction.setSeedsQuantity(Integer.parseInt(seedsQuantityTextField.getText()));

        LocalDate localDate = datePicker.getValue();
        Date sqlDate = Date.valueOf(localDate);
        newProduction.setData(sqlDate.toString());

        newProduction.setState(stateComboBox.getValue());

        ProductionBLL.create(newProduction);

        ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Scheduled a production successfully!", continueButtonType);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStyleClass().add("alert");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == continueButtonType) {
            alert.close();
        }
    }

    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        System.out.println("Cancel Button was Clicked");
    }

    @FXML
    public void onSeedsComboBoxSelected(ActionEvent event) {
        String selectedSeed = seedsComboBox.getSelectionModel().getSelectedItem();
        Seeds seed = SeedsBLL.getbydescription(selectedSeed);
        int quantityRequested = seed.getQuantityRequested();
        quantityLabel.setText("Quantidade atual da semente: " + quantityRequested);
    }
}
