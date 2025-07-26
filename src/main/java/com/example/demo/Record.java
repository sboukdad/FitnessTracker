package com.example.demo;

import jakarta.persistence.*;

/**
 * Customer Plain Old Java Object (POJO).
 */
@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // <- this is the primary key

    private String route;
    private double miles;
    private String date;
    private Boolean completed;


    /**
     * Protected Constructor, used by JPA.
     */
    protected Record() {
    }

    /**
     * Public Constructor, used to create new Objects.
     */
    public Record(String route, double miles, String date) {
        this.route = route;
        this.miles = miles;
        this.date = date;
    }

    /**
     * get miles of record
     * @return miles
     */
    public double getMiles() {
        return miles;
    }

    /**
     * get route
     * @return route of record
     */
    public String getRoute() {
        return route;
    }

    /**
     * get date
     * @return date of record
     */
    public String getDate() {
        return date;
    }

    /**
     * get key ID
     * @return id of record
     */
    public Long getId() {
        return id;
    }

    /**
     * set the route
     * @param route of record
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * set date
     * @param date of record
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * toString method to display contents of a Record
     * @return
     */
    @Override
    public String toString() {
        return String.format(
                "Record[route=%s, miles='%s', project='%s']",
                miles, route, date);
    }
}
