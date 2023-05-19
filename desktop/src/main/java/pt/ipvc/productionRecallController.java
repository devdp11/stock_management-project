package pt.ipvc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
public class productionRecallController{
        @FXML
        public void onRecallButtonClick(ActionEvent event){
            System.out.println("Add Button was Clicked");
        }
        @FXML
        public void onCancelButtonClick(ActionEvent event){
            System.out.println("Cancel Button was Clicked");

        }
}
