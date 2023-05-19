package pt.ipvc.bll;

import org.hibernate.criterion.Order;
import pt.ipvc.dal.OrdersEntity;
import pt.ipvc.database.Database;

import java.util.List;


public class OrdersBLL {

    public static List<OrdersEntity> index() {
        return Database.query("orders.index").getResultList();
    }

    public static OrdersEntity get(Long id) {
        return Database.find(OrdersEntity.class, id);
    }

    public static void create(OrdersEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(OrdersEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        OrdersEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("orders.count").getSingleResult()).intValue();
    }
}
