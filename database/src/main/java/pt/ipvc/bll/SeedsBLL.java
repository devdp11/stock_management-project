package pt.ipvc.bll;

import pt.ipvc.dal.SeedsEntity;
import pt.ipvc.database.Database;

import java.util.List;

public class SeedsBLL {

    public static List<SeedsEntity> index() {
        return Database.query("seeds.index").getResultList();
    }

    public static SeedsEntity get(Long id) {
        return Database.find(SeedsEntity.class, id);
    }

    public static void create(SeedsEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(SeedsEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        SeedsEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("seeds.count").getSingleResult()).intValue();
    }
}

