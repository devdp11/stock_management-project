package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.OrdersBLL;
import pt.ipvc.bll.StockBLL;
import pt.ipvc.bll.UsersBLL;
import pt.ipvc.dal.Orders;
import pt.ipvc.dal.Stock;
import pt.ipvc.dal.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class orderEditController {
    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private TextField orderPriceTextField;
    @FXML
    private TextField orderQuantityTextField;
    @FXML
    private DatePicker dateStartPicker;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private ComboBox<String> clientComboBox;

    private Orders order;

    public void setOrder(Orders order) {
        this.order = order;

        List<Stock> stockList = StockBLL.index();
        ObservableList<String> tStock = FXCollections.observableArrayList();
        for (Stock stock : stockList) {
            tStock.add(stock.getDescription());
        }
        productComboBox.setItems(tStock);

        Stock stock = order.getStockByIdStock();
        if (stock != null) {
            productComboBox.setValue(stock.getDescription());
        } else {
            productComboBox.setValue(""); // ou defina um valor padrão adequado
        }

        orderPriceTextField.setText(String.valueOf(order.getOrderPrice()));
        orderQuantityTextField.setText(String.valueOf(order.getOrderQuantity()));
        dateStartPicker.setValue(LocalDate.parse(order.getDateStart()));

        List<String> states = Arrays.asList("Preparing", "Sent", "Received");
        ObservableList<String> tState = FXCollections.observableArrayList(states);
        stateComboBox.setItems(tState);
        stateComboBox.setValue(order.getState());

        orderPriceTextField.setEditable(false);
        orderQuantityTextField.setEditable(false);

        List<Users> userList = UsersBLL.getByRole("client");
        ObservableList<String> tUser = FXCollections.observableArrayList();
        for (Users user : userList) {
            tUser.add(user.getName());
        }
        clientComboBox.setItems(tUser);

        int clientId = order.getIdClient();
        for (Users user : userList) {
            if (user.getId() == clientId) {
                clientComboBox.setValue(user.getName());
                break;
            }
        }
    }

    @FXML
    public void onEditOrderButtonClick(ActionEvent event) throws IOException {
        if (order != null) {
            int clientId = getSelectedClientId();

            if (clientId != 0) { // Check if a valid client ID is selected
                int stockId = getSelectedStockId();

                if (stockId != 0) { // Check if a valid stock ID is selected
                    order.setIdStock(stockId);
                    order.setOrderPrice(Double.parseDouble(orderPriceTextField.getText()));
                    order.setOrderQuantity(Integer.parseInt(orderQuantityTextField.getText()));
                    order.setDateStart(String.valueOf(dateStartPicker.getValue()));
                    order.setState(stateComboBox.getValue());
                    order.setIdClient(clientId);

                    OrdersBLL.update(order);

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
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Invalid Stock Selection!", continueButtonType);
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
                ButtonType continueButtonType = new ButtonType("Try Again", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Invalid Client Selection!", continueButtonType);
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

    private int getSelectedStockId() {
        String selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
        List<Stock> stockList = StockBLL.index();
        for (Stock stock : stockList) {
            if (stock.getDescription().equals(selectedProduct)) {
                return stock.getId();
            }
        }
        return 0;
    }


    private int getSelectedClientId() {
        String selectedClient = clientComboBox.getSelectionModel().getSelectedItem();
        List<Users> clients = UsersBLL.getByRole("client");
        for (Users client : clients) {
            if (client.getName().equals(selectedClient)) {
                return client.getId();
            }
        }
        return 0;
    }

}
