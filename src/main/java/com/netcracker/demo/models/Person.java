package com.netcracker.demo.models;


import javax.persistence.*;

@Entity
@Table (name = "mat")
public class Person {


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
   @Column (name = "name")
    private String name;
   @Column(name = "email")
    private String email;
   @Column(name = "age")
    private int age;

    public Person(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
