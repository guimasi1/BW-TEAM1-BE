package org.example.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID card;
    private String name;
    private String surname;
    private int age;
    @OneToOne(mappedBy = "user")
    private Pass pass;
    @OneToMany(mappedBy = "user")
    @Column(name = "ticket_list")
    private List<Ticket> ticketList;

    public User() {
    }

    public User(String name, String surname, int age, Pass pass, List<Ticket> ticketList) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.pass = pass;
        this.ticketList = ticketList;
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public UUID getCard() {
        return card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public String toString() {
        return "User{" +
                "card=" + card +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", pass=" + pass +
               // ", ticketList=" + ticketList +
                '}';
    }
}
