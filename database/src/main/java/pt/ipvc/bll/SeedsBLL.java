package pt.ipvc.bll;
import pt.ipvc.dal.Seeds;
import pt.ipvc.database.Database;

import java.util.List;

public class SeedsBLL {

    public static List<Seeds> index() {
        return Database.query("seeds.index").getResultList();
    }

    public static Seeds get(Long id) {
        return Database.find(Seeds.class, id);
    }

    public static Seeds getbydescription(String description){
        List<Seeds>seeds = Database.query("seeds.getbydescription").setParameter("description", description).getResultList();
        return seeds.isEmpty()? null:seeds.get(0);
    }

    public static void create(Seeds entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(Seeds entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        Seeds entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static int count() {
        return ((Long) Database.query("Seeds.count").getSingleResult()).intValue();
    }

    public static boolean checkDescription(String description){
        Seeds seeds = getbydescription(description);
        return seeds == null;
    }

    public static Seeds get(int id) {
        return Database.find(Seeds.class, id);
    }
}

