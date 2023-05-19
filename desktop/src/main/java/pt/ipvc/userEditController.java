package pt.ipvc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class userEditController {

    @FXML
    public void onConfirmButtonClick(ActionEvent event){
        System.out.println("Add Button was Clicked");
    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){
        System.out.println("Cancel Button was Clicked");

    }
}
