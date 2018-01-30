package com.netcracker.demo.models;

import java.util.Objects;

public class CarEntityTO {

    private long id;

    private String name;

    private long typeId;

    private String number;

    private String model;

    private String color;

    private String type;

    public CarEntityTO() {
    }

    public CarEntityTO(long id, String name, long typeId, String number, String model, String color, String type) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.number = number;
        this.model = model;
        this.color = color;
        this.type = type;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntityTO that = (CarEntityTO) o;
        return getId() == that.getId() &&
                getTypeId() == that.getTypeId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getModel(), that.getModel()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getTypeId(), getNumber(), getModel(), getColor(), getType());
    }

    @Override
    public String toString() {
        return CarEntityTO.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
