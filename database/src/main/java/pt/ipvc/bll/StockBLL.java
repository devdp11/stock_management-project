package pt.ipvc.bll;


import pt.ipvc.dal.StockEntity;
import pt.ipvc.database.Database;

import java.util.List;

public class StockBLL {

    public static List<StockEntity> index() {
        return Database.query("stock.index").getResultList();
    }

    public static StockEntity get(Long id) {
        return Database.find(StockEntity.class, id);
    }

    public static void create(StockEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(StockEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        StockEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("stock.count").getSingleResult()).intValue();
    }
}
