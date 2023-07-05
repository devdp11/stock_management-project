package pt.ipvc.models;

import javax.validation.constraints.NotBlank;

public class HomeModel {
    @NotBlank
    private String description;
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
