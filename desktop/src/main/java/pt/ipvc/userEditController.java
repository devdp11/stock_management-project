package pt.ipvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipvc.bll.RoleBLL;
import pt.ipvc.dal.Role;
import pt.ipvc.dal.Users;

import java.util.List;

public class userEditController {

    private Users user;
    public void setUser(Users users) {
        this.user = users;

        List<Role> roleList = RoleBLL.index();

        ObservableList<String> tUser = FXCollections.observableArrayList();

        for (Role role : roleList) {
            tUser.add(role.getDescription());
        }


    }
    @FXML
    public void onConfirmButtonClick(ActionEvent event){
    }
    @FXML
    public void onCancelButtonClick(ActionEvent event){
    }
}
