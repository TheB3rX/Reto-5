package org.example.Challenge5.dao;

import org.example.Challenge5.connection.PilotConnection;
import org.example.Challenge5.daointerfaces.ITeamDAO;
import org.example.Challenge5.models.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO implements ITeamDAO {

    @Override
    public List<Team> getAllTeam() {
        List<Team> teams = new ArrayList<>();
        try {
            Connection conn = PilotConnection.getConnection();
            String sql = "select * from escuderia";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                teams.add(new Team(
                        rs.getInt("cod_escuderia"),
                        rs.getString("nombre"),
                        rs.getString("patrocinador"),
                        rs.getInt("carreras_ganadas"),
                        rs.getString("fecha_ingreso")
                ));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }
}
