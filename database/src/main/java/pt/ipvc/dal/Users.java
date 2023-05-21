package pt.ipvc.dal;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.getByPhone", query = "Select users from Users users where users.phone LIKE :phone"),
        @NamedQuery(name = "user.getByEmail", query = "select users from Users users where users.email  LIKE :email"),
        @NamedQuery(name = "user.getLogin", query = "SELECT users FROM Users users WHERE users.email LIKE :email AND users.password LIKE :password"),
        //@NamedQuery(name = "user.getByRole", query = "select users from Users users where users.idRole LIKE :roleByIdRole"),
})
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "door")
    private String door;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "id_role")
    private int idRole;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Orders> ordersById;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Production> productionsById;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Seeds> seedsById;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Seeds> seedsById_0;
    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id", insertable = false, nullable = false, updatable = false)
    private Role roleByIdRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (idRole != users.idRole) return false;
        if (name != null ? !name.equals(users.name) : users.name != null) return false;
        if (phone != null ? !phone.equals(users.phone) : users.phone != null) return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (street != null ? !street.equals(users.street) : users.street != null) return false;
        if (door != null ? !door.equals(users.door) : users.door != null) return false;
        if (location != null ? !location.equals(users.location) : users.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (door != null ? door.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + idRole;
        return result;
    }

    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    public Collection<Production> getProductionsById() {
        return productionsById;
    }

    public void setProductionsById(Collection<Production> productionsById) {
        this.productionsById = productionsById;
    }

    public Collection<Seeds> getSeedsById() {
        return seedsById;
    }

    public void setSeedsById(Collection<Seeds> seedsById) {
        this.seedsById = seedsById;
    }

    public Collection<Seeds> getSeedsById_0() {
        return seedsById_0;
    }

    public void setSeedsById_0(Collection<Seeds> seedsById_0) {
        this.seedsById_0 = seedsById_0;
    }

    public Role getRoleByIdRole() {
        return roleByIdRole;
    }

    public void setRoleByIdRole(Role roleByIdRole) {
        this.roleByIdRole = roleByIdRole;
    }
}
