package pt.ipvc.bll;


import pt.ipvc.dal.Storage;
import pt.ipvc.database.Database;

import java.util.List;

public class StorageBLL {

    public static List<Storage> index() {
        return Database.query("storage.index").getResultList();
    }

    public static Storage get(Integer id) {
        return Database.find(Storage.class, id);
    }

    public static void create(Storage entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(Storage entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Integer id) {
        Storage entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("storage.count").getSingleResult()).intValue();
    }
}
