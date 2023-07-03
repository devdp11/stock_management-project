package pt.ipvc.bll;

import pt.ipvc.dal.Production;
import pt.ipvc.dal.Storage;
import pt.ipvc.database.Database;

import java.util.List;

public class ProductionBLL {

    public static List<Production> index() {
        return Database.query("productions.index").getResultList();
    }

    public static Production getById(int id) {
        return Database.find(Production.class, id);
    }

    public static Production get(Integer id) {
        return Database.find(Production.class, id);
    }

    public static void create(Production entity) {
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

    public static void update(Production entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(int id) {
        Production entity = getById(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("production.count").getSingleResult()).intValue();
    }
}
