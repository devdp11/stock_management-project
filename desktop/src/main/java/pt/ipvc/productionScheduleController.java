package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipvc.bll.SeedsBLL;
import pt.ipvc.dal.Seeds;

import java.util.Arrays;
import java.util.List;

public class productionScheduleController {

    @FXML
    private ComboBox<String> seedsComboBox;
    @FXML
    private TextField wantedQuantityTextField;
    @FXML
    private TextField seedsQuantityTextField;
    @FXML
    private TextField dateTextField;
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
        System.out.println("Schedule Button was Clicked");
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