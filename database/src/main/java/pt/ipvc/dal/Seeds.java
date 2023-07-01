package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "seeds.getbydescription", query = "SELECT seeds from Seeds seeds WHERE seeds.description LIKE :description"),
        @NamedQuery(name = "seeds.index", query = "SELECT seeds from Seeds seeds"),
})
public class Seeds {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_supplier")
    private int idSupplier;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "quantity_requested")
    private int quantityRequested;
    @Basic
    @Column(name = "date")
    private String date;
    @Basic
    @Column(name = "deleted_on")
    private Timestamp deletedOn;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Production> productionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityRequested() {
        return quantityRequested;
    }

    public void setQuantityRequested(int quantityRequested) {
        this.quantityRequested = quantityRequested;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

        Seeds seeds = (Seeds) o;

        if (id != seeds.id) return false;
        if (idSupplier != seeds.idSupplier) return false;
        if (quantityRequested != seeds.quantityRequested) return false;
        if (description != null ? !description.equals(seeds.description) : seeds.description != null) return false;
        if (date != null ? !date.equals(seeds.date) : seeds.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idSupplier;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + quantityRequested;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public Collection<Production> getProductionsById() {
        return productionsById;
    }

    public void setProductionsById(Collection<Production> productionsById) {
        this.productionsById = productionsById;
    }
}
