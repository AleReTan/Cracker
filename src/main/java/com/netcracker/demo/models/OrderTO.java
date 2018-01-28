package com.netcracker.demo.models;


import java.time.LocalDate;
import java.util.Objects;


public class OrderTO {


    private long id;

    private String firstName;

    private String lastName;

    private String address;

    private String mobPhone;

    private String modelCar;

    private String color;

    private String numberAuto;

    private String drivers;

    private LocalDate timeOrder;
    private LocalDate timeOutOrder;

    private String rate;

    private double costOrder;



    public OrderTO() {
        id = 0;
    }


    public OrderTO(long id, String firstName, String lastName, String address, String mobPhone, String modelCar, String color, String numberAuto, String drivers, LocalDate timeOrder, LocalDate timeOutOrder, String rate, double costOrder) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobPhone = mobPhone;
        this.modelCar = modelCar;
        this.color = color;
        this.numberAuto = numberAuto;
        this.drivers = drivers;
        this.timeOrder = timeOrder;
        this.timeOutOrder = timeOutOrder;
        this.rate = rate;
        this.costOrder = costOrder;
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

    public String getDrivers() { return drivers; }

    public void setDrivers(String drivers) { this.drivers = drivers; }

    public LocalDate getTimeOrder() { return timeOrder; }

    public void setTimeOrder(LocalDate timeOrder) { this.timeOrder = timeOrder; }

    public LocalDate getTimeOutOrder() { return timeOutOrder; }

    public void setTimeOutOrder(LocalDate timeOutOrder) { this.timeOutOrder = timeOutOrder; }

    public String getRate() { return rate; }

    public void setRate(String rate) { this.rate = rate; }

    public double getCostOrder() { return costOrder; }

    public void setCostOrder(double costOrder) { this.costOrder = costOrder; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderTO)) return false;
        OrderTO order = (OrderTO) o;
        return id == order.id &&
                rate == order.rate &&
                Double.compare(order.costOrder, costOrder) == 0 &&
                Objects.equals(firstName, order.firstName) &&
                Objects.equals(lastName, order.lastName) &&
                Objects.equals(address, order.address) &&
                Objects.equals(mobPhone, order.mobPhone) &&
                Objects.equals(modelCar, order.modelCar) &&
                Objects.equals(color, order.color) &&
                Objects.equals(numberAuto, order.numberAuto) &&
                Objects.equals(drivers, order.drivers) &&
                Objects.equals(timeOrder, order.timeOrder) &&
                Objects.equals(timeOutOrder, order.timeOutOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, mobPhone, modelCar, color, numberAuto, drivers, timeOrder, timeOutOrder, rate, costOrder);
    }

    @Override
    public String toString() {
        return "OrderTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", modelCar='" + modelCar + '\'' +
                ", color='" + color + '\'' +
                ", numberAuto='" + numberAuto + '\'' +
                ", drivers='" + drivers + '\'' +
                ", timeOrder=" + timeOrder +
                ", timeOutOrder=" + timeOutOrder +
                ", rate=" + rate +
                ", costOrder=" + costOrder +
                '}';
    }

}
