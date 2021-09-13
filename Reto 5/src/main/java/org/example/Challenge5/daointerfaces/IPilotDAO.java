package org.example.Challenge5.daointerfaces;

import org.example.Challenge5.models.Pilot;

import java.util.List;

public interface IPilotDAO {
    List<Pilot> getAllPilots();
    Pilot getPilotById(Pilot pilot);
    void editPilot(Pilot pilot);
    void deletePilot(Pilot pilot);
    void insertPilot(Pilot pilot);
}