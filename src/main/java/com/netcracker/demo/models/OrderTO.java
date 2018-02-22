package com.netcracker.demo.models;


import java.time.LocalDate;
import java.util.Objects;


public class OrderTO {


    private long id;

    private String name;

    private long typeId;

    private String clientFirstName;

    private String clientLastName;

    private String clientPhoneNumber;

    private String address;
    private long driverId;

    private String orderCost;

    private String geoData;

    private String orderStartTime;

    private String orderEndTime;

    private String statusOrder;


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

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(String orderCost) {
        this.orderCost = orderCost;
    }

    public String getGeoData() {
        return geoData;
    }

    public void setGeoData(String geoData) {
        this.geoData = geoData;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public OrderTO() {
    }

    public OrderTO(long id, String name, long typeid, String clientFirstName, String clientLastName, String clientPhoneNumber, String address, long driverId, String orderCost, String geoData, String orderStartTime, String orderEndTime, String statusOrder) {
        this.id = id;
        this.name = name;
        this.typeId = typeid;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.address = address;
        this.driverId = driverId;
        this.orderCost = orderCost;
        this.geoData = geoData;
        this.orderStartTime = orderStartTime;
        this.orderEndTime = orderEndTime;
        this.statusOrder = statusOrder;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderTO)) return false;
        OrderTO orderTO = (OrderTO) o;
        return id == orderTO.id &&
                typeId == orderTO.typeId &&
                driverId == orderTO.driverId &&
                Objects.equals(name, orderTO.name) &&
                Objects.equals(clientFirstName, orderTO.clientFirstName) &&
                Objects.equals(clientLastName, orderTO.clientLastName) &&
                Objects.equals(clientPhoneNumber, orderTO.clientPhoneNumber) &&
                Objects.equals(address, orderTO.address) &&
                Objects.equals(orderCost, orderTO.orderCost) &&
                Objects.equals(geoData, orderTO.geoData) &&
                Objects.equals(orderStartTime, orderTO.orderStartTime) &&
                Objects.equals(orderEndTime, orderTO.orderEndTime) &&
                Objects.equals(statusOrder, orderTO.statusOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, typeId, clientFirstName, clientLastName, clientPhoneNumber, address, driverId, orderCost, geoData, orderStartTime, orderEndTime, statusOrder);
    }

    @Override
    public String toString() {
        return "OrderTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", driverId=" + driverId +
                ", orderCost='" + orderCost + '\'' +
                ", geoData='" + geoData + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderEndTime='" + orderEndTime + '\'' +
                ", statusOrder='" + statusOrder + '\'' +
                '}';
    }

}
