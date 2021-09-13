package org.example.Challenge5.models;

public class Pilot {
    private int id;
    private String name;
    private float mile;
    private int fuel;
    private Team team;


    public Pilot(int id) {
        this.id = id;
    }

    public Pilot(int id, String name, float mile, int fuel, Team team) {
        this.id = id;
        this.name = name;
        this.mile = mile;
        this.fuel = fuel;
        this.team = team;
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

    public float getMile() {
        return mile;
    }

    public void setMile(float mile) {
        this.mile = mile;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
