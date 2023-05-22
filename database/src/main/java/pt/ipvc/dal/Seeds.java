package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "seed.getbydescription", query = "SELECT seeds from Seeds seeds WHERE seeds.description LIKE :description"),
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
    @Column(name = "id_manager")
    private int idManager;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "quantity_requested")
    private int quantityRequested;
    @Basic
    @Column(name = "date")
    private Date date;
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

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seeds seeds = (Seeds) o;

        if (id != seeds.id) return false;
        if (idSupplier != seeds.idSupplier) return false;
        if (idManager != seeds.idManager) return false;
        if (quantityRequested != seeds.quantityRequested) return false;
        if (description != null ? !description.equals(seeds.description) : seeds.description != null) return false;
        if (date != null ? !date.equals(seeds.date) : seeds.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idSupplier;
        result = 31 * result + idManager;
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
