package pt.ipvc.models;

import javax.validation.constraints.NotBlank;

public class HomeModel {
    @NotBlank
    private String description;
    @NotBlank
    private Double price;
    @NotBlank
    private int producedQuantity;
    @NotBlank
    private String date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
