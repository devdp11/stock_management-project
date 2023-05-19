package pt.ipvc.bll;
import pt.ipvc.dal.Stock;
import pt.ipvc.database.Database;

import java.util.List;

public class StockBLL {

    public static List<Stock> index() {
        return Database.query("stock.index").getResultList();
    }

    public static Stock get(Long id) {
        return Database.find(Stock.class, id);
    }

    public static void create(Stock entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(Stock entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        Stock entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("stock.count").getSingleResult()).intValue();
    }
}
