package pt.ipvc.bll;
import pt.ipvc.dal.Orders;
import pt.ipvc.dal.Stock;
import pt.ipvc.database.Database;

import java.util.List;


public class OrdersBLL {

    public static List<Orders> index() {
        return Database.query("orders.index").getResultList();
    }

    public static Orders get(Long id) {
        return Database.find(Orders.class, id);
    }

    public static void create(Orders entity) {
        Database.beginTransaction();

        try {
            Database.insert(entity);
            Database.commitTransaction();
            Database.getEntityManager().clear();
        } catch (Exception e) {
            Database.rollbackTransaction();
            throw e;
        }
    }

    public static void update(Orders entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        Orders entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("Orders.count").getSingleResult()).intValue();
    }
}
