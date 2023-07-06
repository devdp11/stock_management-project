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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipvc.bll.ProductionBLL;
import pt.ipvc.bll.SeedsBLL;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.bll.StorageBLL;
import pt.ipvc.dal.Production;
import pt.ipvc.dal.Seeds;
import pt.ipvc.dal.Stock;
import pt.ipvc.dal.Storage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.*;
import java.util.stream.Collectors;

public class productController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField descriptionFilter;
    @FXML
    private TableView<Stock> dataView;
    @FXML
    private TableColumn<Stock, String> idBatchColumn;
    @FXML
    private TableColumn<Stock, String> descriptionBatchColumn;
    @FXML
    private TableColumn<Stock, String> quantityBatchColumn;
    @FXML
    private TableColumn<Stock, String> priceBatchColumn;
    @FXML
    private TableColumn<Stock, String> dateBatchColumn;
    @FXML
    private TableColumn<Stock, String> productionBatchColumn;
    @FXML
    private TableColumn<Stock, String> storageBatchColumn;

    public void initialize() {
        ObservableList<String> tStock = FXCollections.observableArrayList();

        List<Stock> stocks = StockBLL.index();
        Collections.sort(stocks, Comparator.comparingInt(stock -> stock.getId()));
        ObservableList<Stock> data = FXCollections.observableArrayList(stocks);

        dataView.setItems(data);
        idBatchColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getId())));

        descriptionBatchColumn.setCellValueFactory(d -> {
            Stock stock = StockBLL.get(d.getValue().getId());
            return new SimpleStringProperty(stock.getDescription());
        });
        quantityBatchColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getProducedQuantity())));
        priceBatchColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getPrice())));
        dateBatchColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getDate())));
        productionBatchColumn.setCellValueFactory(d -> {
            Production production = ProductionBLL.get(d.getValue().getIdProduction());
            return new SimpleStringProperty(production.getDescription());
        });
        storageBatchColumn.setCellValueFactory(d -> {
            Storage storage = StorageBLL.get(d.getValue().getIdStorage());
            return new SimpleStringProperty(storage.getName());
        });
        dataView.setItems(data);
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
    public void onSeedsButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("seeds.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void onProductionButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("production.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void filterProducts(KeyEvent event) {
        String filter = descriptionFilter.getText().toLowerCase();
        List<Stock> stocks = StockBLL.index();
        List<Stock> filteredProducts = stocks.stream()
                .filter(stock -> stock.getDescription().toLowerCase().contains(filter))
                .collect(Collectors.toList());
        updateDataView(filteredProducts);
    }
    private void updateDataView(List<Stock> stocks) {
        Collections.sort(stocks, Comparator.comparingInt(stock -> stock.getId()));
        ObservableList<Stock> data = FXCollections.observableArrayList(stocks);
        dataView.setItems(data);
        dataView.refresh();
    }
}
