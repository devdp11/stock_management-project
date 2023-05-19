package pt.ipvc.bll;

import pt.ipvc.dal.ProductionEntity;
import pt.ipvc.database.Database;

import java.util.List;

public class ProductionBLL {

    public static List<ProductionEntity> index() {
        return Database.query("production.index").getResultList();
    }

    public static ProductionEntity get(Long id) {
        return Database.find(ProductionEntity.class, id);
    }

    public static void create(ProductionEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(ProductionEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        ProductionEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("production.count").getSingleResult()).intValue();
    }
}