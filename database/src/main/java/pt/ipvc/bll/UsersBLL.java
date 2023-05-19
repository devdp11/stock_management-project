package pt.ipvc.bll;

import pt.ipvc.dal.UsersEntity;
import pt.ipvc.database.Database;

import java.util.List;

public class UsersBLL {

    public static List<UsersEntity> index() {
        return Database.query("user.index").getResultList();
    }

    public static UsersEntity get(Long id) {
        return Database.find(UsersEntity.class, id);
    }

    public static void create(UsersEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(UsersEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        UsersEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("users.count").getSingleResult()).intValue();
    }
}
