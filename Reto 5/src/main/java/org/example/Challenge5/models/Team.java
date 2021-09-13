package org.example.Challenge5.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Team {
    private int id;
    private String name;
    private String sponsor;
    private int wonRace;
    private LocalDate date;

    public Team(int id, String name, String sponsor, int wonRace, String date) {
        this.id = id;
        this.name = name;
        this.sponsor = sponsor;
        this.wonRace = wonRace;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public int getWonRace() {
        return wonRace;
    }

    public void setWonRace(int wonRace) {
        this.wonRace = wonRace;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return id+ "-" +name;
    }
}
