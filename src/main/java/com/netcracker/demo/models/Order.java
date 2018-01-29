package com.netcracker.demo.models;


import javax.persistence.*;
import java.util.Objects;

public class Order {

    private long id;

    private String firstName;

    private String lastName;

    private String address;

    private String mobPhone;

    private String modelCar;

    private String color;

    private String numberAuto;

    public Order() {
        id = 0;
    }

    public Order(long id, String firstName, String lastName, String address, String mobPhone, String modelCar, String color, String numberAuto, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobPhone = mobPhone;
        this.modelCar = modelCar;
        this.color = color;
        this.numberAuto = numberAuto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumberAuto() {
        return numberAuto;
    }

    public void setNumberAuto(String numberAuto) {
        this.numberAuto = numberAuto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(firstName, order.firstName) &&
                Objects.equals(lastName, order.lastName) &&
                Objects.equals(address, order.address) &&
                Objects.equals(mobPhone, order.mobPhone) &&
                Objects.equals(modelCar, order.modelCar) &&
                Objects.equals(color, order.color) &&
                Objects.equals(numberAuto, order.numberAuto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, mobPhone, modelCar, color, numberAuto);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", modelCar='" + modelCar + '\'' +
                ", color='" + color + '\'' +
                ", numberAuto='" + numberAuto + '\'' +
                '}';
    }

}
