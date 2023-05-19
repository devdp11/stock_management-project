package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "seeds", schema = "public", catalog = "vegetable-managment-company")
public class SeedsEntity {
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

        SeedsEntity that = (SeedsEntity) o;

        if (id != that.id) return false;
        if (idSupplier != that.idSupplier) return false;
        if (idManager != that.idManager) return false;
        if (quantityRequested != that.quantityRequested) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

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
}
