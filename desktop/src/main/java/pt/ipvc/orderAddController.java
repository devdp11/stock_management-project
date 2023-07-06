package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipvc.bll.*;
import pt.ipvc.dal.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class orderAddController {

    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private TextField orderPriceTextField;
    @FXML
    private TextField orderQuantityTextField;
    @FXML
    private DatePicker dateStartPicker;
    @FXML
    private DatePicker dateEndPicker;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private ComboBox<String> clientComboBox;
    @FXML
    private Label quantityLabel;

    private ObservableList<Stock> stockList;

    @FXML
    private void initialize() {
        List<Stock> stocks = StockBLL.index();
        ObservableList<String> tStock = FXCollections.observableArrayList();
        for (Stock st : stocks) {
            tStock.add(st.getDescription());
        }
        productComboBox.setItems(tStock);

        List<String> states = Arrays.asList("Preparing", "Sent", "Received");
        ObservableList<String> tState = FXCollections.observableArrayList(states);
        stateComboBox.setItems(tState);

        List<Users> clients = (List<Users>) UsersBLL.getByRole("client");
        ObservableList<String> clientNames = FXCollections.observableArrayList();
        for (Users client : clients) {
            clientNames.add(client.getName());
        }
        clientComboBox.setItems(clientNames);
    }

    @FXML
    public void onAddOrderButtonClick(ActionEvent event) {
        Orders newOrder = new Orders();

        String selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
        Stock stock = StockBLL.getbydescription(selectedProduct);
        if (stock != null) {
            newOrder.setIdStock(stock.getId());

            try {
                double orderQuantity = Double.parseDouble(orderQuantityTextField.getText());
                double productPrice = stock.getPrice();
                double orderPrice = orderQuantity * productPrice;

                newOrder.setOrderPrice(orderPrice);
                newOrder.setOrderQuantity((int) orderQuantity);

                LocalDate localDate = dateStartPicker.getValue();
                Date sqlDate = Date.valueOf(localDate);
                newOrder.setDateStart(sqlDate.toString());

                newOrder.setState(stateComboBox.getValue());

                String client = clientComboBox.getSelectionModel().getSelectedItem();
                List<Users> clients = UsersBLL.getByRole("client");

                Users userS = null;
                for (Users clientUser : clients) {
                    if (clientUser.getName().equals(client)) {
                        userS = clientUser;
                        break;
                    }
                }

                if (userS != null) {
                    newOrder.setIdClient(userS.getId());
                    OrdersBLL.create(newOrder);

                    ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Added an order successfully!", continueButtonType);
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
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Client not found!", continueButtonType);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    DialogPane alertDialog = alert.getDialogPane();
                    alertDialog.getStyleClass().add("alert");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                ButtonType continueButtonType = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Invalid quantity format!", continueButtonType);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                DialogPane alertDialog = alert.getDialogPane();
                alertDialog.getStyleClass().add("alert");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }

    @FXML
    public void onProductComboBoxSelected(ActionEvent event) {
        String selectedProduct = String.valueOf(productComboBox.getSelectionModel().getSelectedItem());
        Stock stock = StockBLL.getbydescription(selectedProduct);
        int quantityStocked = stock.getProducedQuantity();
        quantityLabel.setText("Quantidade atual do produto: " + quantityStocked);
    }
}
