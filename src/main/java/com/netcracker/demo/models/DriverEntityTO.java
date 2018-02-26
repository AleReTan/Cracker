package com.netcracker.demo.models;

import java.util.Objects;


public class DriverEntityTO {

    private long id;

    private String name;

    private long typeId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String driverGeoData;

    private long carId;

    public DriverEntityTO() {
    }

    public DriverEntityTO(long id, String name, long typeId, String firstName, String lastName, String phoneNumber, String driverGeoData, long carId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.driverGeoData = driverGeoData;
        this.carId = carId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverGeoData() {
        return driverGeoData;
    }

    public void setDriverGeoData(String driverGeoData) {
        this.driverGeoData = driverGeoData;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverEntityTO that = (DriverEntityTO) o;
        return getId() == that.getId() &&
                getTypeId() == that.getTypeId() &&
                getCarId() == that.getCarId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getDriverGeoData(), that.getDriverGeoData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getTypeId(), getFirstName(), getLastName(), getPhoneNumber(), getDriverGeoData(), getCarId());
    }

    @Override
    public String toString() {
        return DriverEntityTO.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", driverGeoData='" + driverGeoData + '\'' +
                ", carId=" + carId;
    }
}
