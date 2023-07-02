package pt.ipvc.bll;
import pt.ipvc.dal.Role;
import pt.ipvc.database.Database;

import java.util.List;

public class RoleBLL {

    public static List<Role> index() {
        return Database.query("role.index").getResultList();
    }

    public static Role get(int id) {
        return Database.find(Role.class, id);
    }

    public static void create(Role entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(Role entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(int id) {
        Role entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static Role getbydescription(String description){
        return (Role) Database.query("role.getbydescription")
                .setParameter("description", description)
                .getResultStream().findFirst().orElse(null);
    }

    public static Role getById(int id) {
        return Database.find(Role.class, id);
    }

    public static int count() {
        return ((Long) Database.query("role.count").getSingleResult()).intValue();
    }
}

