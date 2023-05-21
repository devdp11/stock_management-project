package pt.ipvc.dal;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@NamedQueries({
})
public class Production {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_seeds")
    private int idSeeds;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "wanted_quantity")
    private Integer wantedQuantity;
    @Basic
    @Column(name = "seeds_quantity")
    private int seedsQuantity;
    @Basic
    @Column(name = "data")
    private Date data;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "id_manager")
    private int idManager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSeeds() {
        return idSeeds;
    }

    public void setIdSeeds(int idSeeds) {
        this.idSeeds = idSeeds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWantedQuantity() {
        return wantedQuantity;
    }

    public void setWantedQuantity(Integer wantedQuantity) {
        this.wantedQuantity = wantedQuantity;
    }

    public int getSeedsQuantity() {
        return seedsQuantity;
    }

    public void setSeedsQuantity(int seedsQuantity) {
        this.seedsQuantity = seedsQuantity;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (id != that.id) return false;
        if (idSeeds != that.idSeeds) return false;
        if (seedsQuantity != that.seedsQuantity) return false;
        if (idManager != that.idManager) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (wantedQuantity != null ? !wantedQuantity.equals(that.wantedQuantity) : that.wantedQuantity != null)
            return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idSeeds;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (wantedQuantity != null ? wantedQuantity.hashCode() : 0);
        result = 31 * result + seedsQuantity;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + idManager;
        return result;
    }
}
