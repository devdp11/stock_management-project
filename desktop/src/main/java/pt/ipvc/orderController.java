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
import javafx.stage.Stage;

import javafx.stage.Modality;
import pt.ipvc.bll.*;
import pt.ipvc.dal.*;

import java.io.IOException;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

public class orderController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Orders> dataView;
    @FXML
    private TableColumn<Orders, String> productOrderColumn;
    @FXML
    private TableColumn<Orders, String> clientOrderColumn;
    @FXML
    private TableColumn<Orders, String> priceOrderColumn;
    @FXML
    private TableColumn<Orders, String> quantityOrderColumn;
    @FXML
    private TableColumn<Orders, String> dateStartOrderColum;
    @FXML
    private TableColumn<Orders, String> dateEndOrderColum;
    @FXML
    private TableColumn<Orders, String> stateOrderColumn;
    @FXML
    private ComboBox<String> stateFilter;
    @FXML
    private TextField productFilter;

    public void initialize() {
        ObservableList<String> tOrder1 = FXCollections.observableArrayList(
                "All", "Preparing", "Sent", "Received");

        stateFilter.setItems(tOrder1);
        ObservableList<String> tOrder = FXCollections.observableArrayList();

        List<Orders> orders = OrdersBLL.index();
        Collections.sort(orders, Comparator.comparingInt(orders1 -> orders1.getId()));
        ObservableList<Orders> data = FXCollections.observableArrayList(orders);

        dataView.setItems(data);
        productOrderColumn.setCellValueFactory(d -> {
            Stock stock = StockBLL.get((d.getValue().getIdStock()));
            return new SimpleStringProperty(stock.getDescription());
        });
        clientOrderColumn.setCellValueFactory(d -> {
            Users users = UsersBLL.get((d.getValue().getIdClient()));
            return new SimpleStringProperty(users.getName());
        });
        priceOrderColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getOrderPrice())));
        quantityOrderColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getOrderQuantity())));
        dateStartOrderColum.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getDateStart())));
        stateOrderColumn.setCellValueFactory(d -> new SimpleStringProperty(String.valueOf(d.getValue().getState())));
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
    public void onProductsButtonClick(ActionEvent event) throws IOException{
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
    public void onAddOrderButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order_add.fxml"));
        Parent root = fxmlLoader.load();
        Scene popupScene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Adding Order..");
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void onEditOrderButtonClick(ActionEvent event) throws IOException {
        Orders selectedOrder = dataView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("order_edit.fxml"));
            Parent parent = loader.load();
            orderEditController controller = loader.getController();
            controller.setOrder(dataView.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(parent);
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setTitle("Edit User");
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            dataView.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit Order");
            alert.setHeaderText("You must select one order to edit");

            ButtonType okButton = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);

            alert.getButtonTypes().setAll(okButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == okButton) {
                alert.close();
            }
        }
    }
    public void onListTotalBilledButtonClick(ActionEvent event) throws IOException {

    }

    private void updateDataView(List<Orders> orders) {
        Collections.sort(orders, Comparator.comparingInt(orders1 -> orders1.getId()));
        ObservableList<Orders> data = FXCollections.observableArrayList(orders);
        dataView.setItems(data);
        dataView.refresh();
    }
    @FXML
    private void onStateFilterSelected(ActionEvent event) {
        String selectedState = stateFilter.getSelectionModel().getSelectedItem();

        if (selectedState.equals("All")) {
            List<Orders> allOrders = OrdersBLL.index();
            updateDataView(allOrders);
        } else {
            List<Orders> filteredOrder = filterOrderByState(selectedState);
            updateDataView(filteredOrder);
        }
    }

    private List<Orders> filterOrderByState(String selectedState) {
        List<Orders> allOrders = OrdersBLL.index();
        List<Orders> filteredOrder = new ArrayList<>();

        for (Orders orders : allOrders) {
            if (orders.getState().equals(selectedState)) {
                filteredOrder.add(orders);
            }
        }
        return filteredOrder;
    }

    @FXML
    public void filterOrders(KeyEvent event) {
        String filter = productFilter.getText().toLowerCase();
        List<Orders> orders = OrdersBLL.index();
        List<Orders> filteredOrders = orders.stream()
                .filter(orders1 -> orders1.getStockByIdStock().getDescription().toLowerCase().contains(filter))
                .collect(Collectors.toList());
        updateDataView(filteredOrders);
    }
}
