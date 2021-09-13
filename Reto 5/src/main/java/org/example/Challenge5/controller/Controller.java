package org.example.Challenge5.controller;

import org.example.Challenge5.dao.PilotDAO;
import org.example.Challenge5.dao.TeamDAO;
import org.example.Challenge5.models.Pilot;
import org.example.Challenge5.models.Team;

import java.util.List;

public class Controller {
    public static List<Pilot> getPilotos() {
        return new PilotDAO().getAllPilots();
    }

    public static List<Team> getAllTeam() {
        return new TeamDAO().getAllTeam();
    }

    public static void insertPilot(Pilot pilot) {
        new PilotDAO().insertPilot(pilot);
    }

    public static void deletePilot(Pilot pilot) {
        new PilotDAO().deletePilot(pilot);
    }
}
