package pt.ipvc.dal;

import jakarta.persistence.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (description != null ? !description.equals(role.description) : role.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
