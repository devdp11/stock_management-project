package pt.ipvc.bll;
import pt.ipvc.dal.Stock;
import pt.ipvc.database.Database;

import java.util.List;

public class StockBLL {

    public static List<Stock> index() {
        return Database.query("stocks.index").getResultList();
    }

    public static Stock get(int id) {
        return Database.find(Stock.class, id);
    }

    public static Stock getbydescription(String description){
        List<Stock>stocks = Database.query("stock.getbydescription").setParameter("description", description).getResultList();
        return stocks.isEmpty()? null:stocks.get(0);
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

    public static void remove(Integer id) {
        Stock entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("stock.count").getSingleResult()).intValue();
    }
}
