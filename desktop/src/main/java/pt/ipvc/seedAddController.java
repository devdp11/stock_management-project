package pt.ipvc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class seedAddController {
    @FXML
    public void onRequestButtonClick(ActionEvent event){
        System.out.println("Request Button was Clicked");
    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){
        System.out.println("Cancel Button was Clicked");

    }
}
