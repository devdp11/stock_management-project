package pt.ipvc.bll;

import pt.ipvc.dal.RoleEntity;
import pt.ipvc.database.Database;

import java.util.List;

public class RoleBLL {

    public static List<RoleEntity> index() {
        return Database.query("role.index").getResultList();
    }

    public static RoleEntity get(int id) {
        return Database.find(RoleEntity.class, id);
    }

    public static void create(RoleEntity entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(RoleEntity entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(int id) {
        RoleEntity entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }

    public static RoleEntity getbydescription(String description){
        return (RoleEntity) Database.query("role.getbydescription")
                .setParameter("description", description)
                .getResultStream().findFirst().orElse(null);


    }

    public static int count() {
        return ((Long) Database.query("role.count").getSingleResult()).intValue();
    }
}

