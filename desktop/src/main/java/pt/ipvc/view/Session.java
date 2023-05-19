package pt.ipvc.view;

import pt.ipvc.dal.UsersEntity;

public class Session {
    private static Session instance;
    private UsersEntity currentUser;

    private Session(){}

    public static Session getInstance(){
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }

    public UsersEntity getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(UsersEntity currentUser){
        this.currentUser = currentUser;
    }
}
