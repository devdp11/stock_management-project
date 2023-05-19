package pt.ipvc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class productionScheduleController {
    @FXML
    public void onScheduleButtonClick(ActionEvent event){
        System.out.println("Schedule Button was Clicked");
    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){
        System.out.println("Cancel Button was Clicked");

    }
}
