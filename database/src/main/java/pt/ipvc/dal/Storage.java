package pt.ipvc.dal;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@NamedQueries({
})
public class Storage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "door")
    private String door;
    @Basic
    @Column(name = "location")
    private String location;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Stock> stocksById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        if (street != null ? !street.equals(storage.street) : storage.street != null) return false;
        if (door != null ? !door.equals(storage.door) : storage.door != null) return false;
        if (location != null ? !location.equals(storage.location) : storage.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (door != null ? door.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    public Collection<Stock> getStocksById() {
        return stocksById;
    }

    public void setStocksById(Collection<Stock> stocksById) {
        this.stocksById = stocksById;
    }
}
