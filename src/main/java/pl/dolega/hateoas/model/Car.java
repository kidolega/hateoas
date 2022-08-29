package pl.dolega.hateoas.model;

import org.springframework.hateoas.RepresentationModel;

public class Car extends RepresentationModel {

    private long carId;
    private String brand;
    private String model;
    private Color color;

    public Car() {
    }

    public Car(long carId, String brand, String model, Color color) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
