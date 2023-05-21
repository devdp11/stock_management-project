package pt.ipvc.bll;


import pt.ipvc.dal.Users;
import pt.ipvc.database.Database;

import java.security.PublicKey;
import java.util.List;

public class UsersBLL {

    public static List<Users> index() {
        return Database.query("users.index").getResultList();
    }

    public static Users get(Long id) {
        return Database.find(Users.class, id);
    }

    public static Users getByPhone(String phone) {
        List<Users>users = Database.query("user.getByPhone").setParameter("phone", phone).getResultList();
        return users.isEmpty()? null:users.get(0);
    }
    public static Users getByEmail(String email){
        List<Users>users = Database.query("user.getByEmail").setParameter("email", email).getResultList();
        return users.isEmpty()? null:users.get(0);
    }


    public static void create(Users entity) {
        Database.beginTransaction();
        Database.insert(entity);
        Database.commitTransaction();
    }

    public static void update(Users entity) {
        Database.beginTransaction();
        Database.update(entity);
        Database.commitTransaction();
    }

    public static void remove(Long id) {
        Users entity = get(id);

        Database.beginTransaction();
        Database.delete(entity);
        Database.commitTransaction();
    }


    public static int count() {
        return ((Long) Database.query("users.count").getSingleResult()).intValue();
    }
    public static boolean checkPhone(String phone){
        Users users = getByPhone(phone);
        return users == null && phone.length() == 9;
    }
    public static boolean checkEmail(String email){
        Users users = getByEmail(email);
        return users == null;
    }
}
