package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = "stock.getbydescription", query = "SELECT stock from Stock stock WHERE stock.description LIKE :description"),
        @NamedQuery(name = "stocks.index", query = "SELECT stock FROM Stock stock"),
        @NamedQuery(name = "stocks.getById", query = "SELECT stock FROM Stock stock WHERE stock.id = :idStock"),
})
public class Stock {
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
    private String date;
    @Basic
    @Column(name = "id_production")
    private int idProduction;
    @Basic
    @Column(name = "id_storage")
    private int idStorage;
    @Basic
    @Column(name = "deleted_on")
    private Timestamp deletedOn;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Orders> ordersById;
    @ManyToOne
    @JoinColumn(name = "id_production", referencedColumnName = "id", insertable = false, nullable = false, updatable = false)
    private Production productionByIdProduction;
    @ManyToOne
    @JoinColumn(name = "id_storage", referencedColumnName = "id", insertable = false, nullable = false, updatable = false)
    private Storage storageByIdStorage;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

        Stock stock = (Stock) o;

        if (id != stock.id) return false;
        if (producedQuantity != stock.producedQuantity) return false;
        if (idProduction != stock.idProduction) return false;
        if (idStorage != stock.idStorage) return false;
        if (description != null ? !description.equals(stock.description) : stock.description != null) return false;
        if (date != null ? !date.equals(stock.date) : stock.date != null) return false;

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

    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    public Production getProductionByIdProduction() {
        return productionByIdProduction;
    }

    public void setProductionByIdProduction(Production productionByIdProduction) {
        this.productionByIdProduction = productionByIdProduction;
    }

    public Storage getStorageByIdStorage() {
        return storageByIdStorage;
    }

    public void setStorageByIdStorage(Storage storageByIdStorage) {
        this.storageByIdStorage = storageByIdStorage;
    }
}