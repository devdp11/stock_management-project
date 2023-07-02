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
import pt.ipvc.bll.ProductionBLL;
import pt.ipvc.bll.SeedsBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Production;
import pt.ipvc.dal.Seeds;
import pt.ipvc.dal.Users;

import java.io.IOException;
import java.util.*;

public class productionController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Production> dataView;
    @FXML
    private TableColumn<Production, String> seedProductionColumn;
    @FXML
    private TableColumn<Production, String> descriptionProductionColumn;
    @FXML
    private TableColumn<Production, String> quantityProductionColumn;
    @FXML
    private TableColumn<Production, String> seedsQuantityProductionColumn;
    @FXML
    private TableColumn<Production, String> dateProductionColumn;
    @FXML
    private TableColumn<Production, String> stateProductionColumn;

    @FXML
    private ComboBox<String> stateFilter;
    @FXML
    public void initialize() {
        ObservableList<String> tProduction = FXCollections.observableArrayList(
                "All", "In preparation", "Growing up", "Ready to recall");

        stateFilter.setItems(tProduction);

        List<Production> productions = ProductionBLL.index();
        Collections.sort(productions, Comparator.comparingInt(production -> production.getId()));
        ObservableList<Production> data = FXCollections.observableArrayList(productions);

        dataView.setItems(data);
        seedProductionColumn.setCellValueFactory(d -> {
            Seeds seed = SeedsBLL.get(d.getValue().getIdSeeds());
            return new SimpleStringProperty(seed.getDescription());
        });
        descriptionProductionColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getDescription())));
        quantityProductionColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getWantedQuantity())));
        seedsQuantityProductionColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getSeedsQuantity())));
        dateProductionColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getData())));
        stateProductionColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getState())));
    }

    @FXML
    public void onHomeButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onUsersButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onProductsButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onOrdersButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("order.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onLogoutButtonClick(ActionEvent event) throws IOException{
        ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the application?", continueButtonType, cancelButtonType);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        DialogPane alertDialog = alert.getDialogPane();
        alertDialog.getStyleClass().add("alert");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == continueButtonType) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login_example.fxml")));
            Scene loginScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.show();
        }
    }
    @FXML
    public void onScheduleButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("production_schedule.fxml"));
        Parent root = fxmlLoader.load();
        Scene popupScene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Scheduling Production..");
        popupStage.setResizable(false);
        popupStage.show();
    }
    @FXML
    public void onRecallButtonClick(ActionEvent event) throws IOException {
        Production selectedProduction = dataView.getSelectionModel().getSelectedItem();
        if (selectedProduction != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("production_recall.fxml"));
            Parent parent = loader.load();
            productionRecallController controller = loader.getController();
            controller.setProduction(dataView.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(parent);
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setTitle("Recall Production");
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            dataView.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Recall Production");
            alert.setHeaderText("You must select one Produciton to Recall");

            ButtonType okButton = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);

            alert.getButtonTypes().setAll(okButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == okButton) {
                alert.close();
            }
        }
    }
    @FXML
    public void onEditButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("production_edit.fxml"));
        Parent root = fxmlLoader.load();
        Scene popupScene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Recalling Production..");
        popupStage.setResizable(false);
        popupStage.show();
    }
    private void updateDataView(List<Production> productions) {
        Collections.sort(productions, Comparator.comparingInt(production -> production.getId()));
        ObservableList<Production> data = FXCollections.observableArrayList(productions);
        dataView.setItems(data);
        dataView.refresh();
    }

    @FXML
    private void onStateFilterSelected(ActionEvent event) {
        String selectedState = stateFilter.getSelectionModel().getSelectedItem();

        if (selectedState.equals("All")) {
            List<Production> allProductions = ProductionBLL.index();
            updateDataView(allProductions);
        } else {
            List<Production> filteredProduction = filterProductionByState(selectedState);
            updateDataView(filteredProduction);
        }
    }

    private List<Production> filterProductionByState(String selectedState) {
        List<Production> allProductions = ProductionBLL.index();
        List<Production> filteredProduction = new ArrayList<>();

        for (Production production : allProductions) {
            if (production.getState().equals(selectedState)) {
                filteredProduction.add(production);
            }
        }

        return filteredProduction;
    }

}
