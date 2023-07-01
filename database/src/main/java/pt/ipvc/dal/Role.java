package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "role.getbydescription", query = "SELECT role FROM Role role WHERE role.description LIKE :description"),
        @NamedQuery(name = "role.index", query = "SELECT role from Role role"),
})
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "deleted_on")
    private Timestamp deletedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Timestamp deletedOn) {
        this.deletedOn = deletedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(description, role.description) && Objects.equals(deletedOn, role.deletedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, deletedOn);
    }
}
