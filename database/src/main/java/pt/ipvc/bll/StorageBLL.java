package pt.ipvc.bll;

import pt.ipvc.dal.StorageEntity;
import pt.ipvc.database.Database;

import java.util.List;

public class StorageBLL {

    public static List<StorageEntity> index() {
        return Database.query("storage.index").getResultList();
    }

    public static StorageEntity get(Long id) {
        return Database.find(StorageEntity.class, id);
    }

    public static void create(StorageEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(StorageEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        StorageEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("storage.count").getSingleResult()).intValue();
    }
}
