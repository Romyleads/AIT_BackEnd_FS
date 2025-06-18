package ait.car.dto;

import java.util.List;

public class CarDto {
    private String manufacturer;
    private int year;
    private List<String> models;

    public CarDto() {


    }


    public CarDto(String manufacturer, int year, List<String> models) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.models = models;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getYear() {
        return year;
    }

    public List<String> getModels() {
        return models;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                ", models=" + models +
                '}';
    }
}
