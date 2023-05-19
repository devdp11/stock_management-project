package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "stock", schema = "public", catalog = "vegetable-managment-company")
public class StockEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "produced_quantity")
    private int producedQuantity;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "id_production")
    private int idProduction;
    @Basic
    @Column(name = "id_storage")
    private int idStorage;

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

    public int getProducedQuantity() {
        return producedQuantity;
    }

    public void setProducedQuantity(int producedQuantity) {
        this.producedQuantity = producedQuantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(int idProduction) {
        this.idProduction = idProduction;
    }

    public int getIdStorage() {
        return idStorage;
    }

    public void setIdStorage(int idStorage) {
        this.idStorage = idStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockEntity that = (StockEntity) o;

        if (id != that.id) return false;
        if (producedQuantity != that.producedQuantity) return false;
        if (idProduction != that.idProduction) return false;
        if (idStorage != that.idStorage) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + producedQuantity;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + idProduction;
        result = 31 * result + idStorage;
        return result;
    }
}
