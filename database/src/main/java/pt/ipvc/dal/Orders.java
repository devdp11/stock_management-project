package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@NamedQueries({
})
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_stock")
    private int idStock;
    @Basic
    @Column(name = "id_client")
    private int idClient;
    @Basic
    @Column(name = "order_price")
    private double orderPrice;
    @Basic
    @Column(name = "order_quantity")
    private int orderQuantity;
    @Basic
    @Column(name = "date_start")
    private Date dateStart;
    @Basic
    @Column(name = "date_end")
    private Date dateEnd;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "deleted_on")
    private Timestamp deletedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (idStock != orders.idStock) return false;
        if (idClient != orders.idClient) return false;
        if (Double.compare(orders.orderPrice, orderPrice) != 0) return false;
        if (orderQuantity != orders.orderQuantity) return false;
        if (dateStart != null ? !dateStart.equals(orders.dateStart) : orders.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(orders.dateEnd) : orders.dateEnd != null) return false;
        if (state != null ? !state.equals(orders.state) : orders.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + idStock;
        result = 31 * result + idClient;
        temp = Double.doubleToLongBits(orderPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + orderQuantity;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}