package org.example.Challenge5.dao;

import jdk.nashorn.internal.scripts.JO;
import org.example.Challenge5.connection.PilotConnection;
import org.example.Challenge5.daointerfaces.IPilotDAO;
import org.example.Challenge5.models.Pilot;
import org.example.Challenge5.models.Team;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PilotDAO implements IPilotDAO {
    @Override
    public List<Pilot> getAllPilots() {
        List<Pilot> pilots = new ArrayList<Pilot>();
        try {
            Connection conn = PilotConnection.getConnection();
            String sql = "select * from piloto join escuderia on piloto.cod_escuderia = escuderia.cod_escuderia";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                pilots.add(new Pilot(
                        rs.getInt("id_piloto"),
                        rs.getString("piloto_nombre"),
                        rs.getFloat("piloto_millas"),
                        rs.getInt("piloto_combustible"),
                        new Team(
                                rs.getInt("escuderia.cod_escuderia"),
                                rs.getString("nombre"),
                                rs.getString("patrocinador"),
                                rs.getInt("carreras_ganadas"),
                                rs.getString("fecha_ingreso")
                        )
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pilots;
    }

    @Override
    public Pilot getPilotById(Pilot pilot) {
        Pilot pilots = null;
        try {
            Connection conn = PilotConnection.getConnection();
            String sql = "select * from piloto join escuderia on piloto.cod_escuderia = escuderia.cod_escuderia where id_piloto = ?";
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pilot.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            pilots = new Pilot(
                    rs.getInt("id_piloto"),
                    rs.getString("piloto_nombre"),
                    rs.getFloat("piloto_millas"),
                    rs.getInt("piloto_combustible"),
                    new Team(
                            rs.getInt("escuderia.cod_escuderia"),
                            rs.getString("nombre"),
                            rs.getString("patrocinador"),
                            rs.getInt("carreras_ganadas"),
                            rs.getString("fecha_ingreso")
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pilots;
    }

    @Override
    public void editPilot(Pilot pilot) {
        try {
            Connection conn = PilotConnection.getConnection();
            String sql = "update piloto set piloto_nombre = ?, piloto_millas = ?, piloto_combustible = ?, cod_escuderia = ? where id_piloto = ?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pilot.getName());
            stmt.setFloat(2, pilot.getMile());
            stmt.setInt(3, pilot.getFuel());
            stmt.setInt(4, pilot.getTeam().getId());
            stmt.setInt(5, pilot.getId());
            int row = stmt.executeUpdate();
            if(row > 0) JOptionPane.showMessageDialog(null,"Piloto Correctamente editado");
            else JOptionPane.showMessageDialog(null,"Piloto no encontrado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePilot(Pilot pilot) {
        try {
            Connection conn = PilotConnection.getConnection();
            String sql = "delete from piloto where id_piloto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pilot.getId());
            int row = stmt.executeUpdate();
            if(row > 0) JOptionPane.showMessageDialog(null,"Piloto Eliminado");
            else JOptionPane.showMessageDialog(null,"Piloto no encontrado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPilot(Pilot pilot) {
        try {
            Connection conn = PilotConnection.getConnection();
            String sql = "insert into piloto (id_piloto, piloto_nombre, piloto_millas, piloto_combustible, cod_escuderia) values (?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pilot.getId());
            stmt.setString(2, pilot.getName());
            stmt.setFloat(3, pilot.getMile());
            stmt.setInt(4, pilot.getFuel());
            stmt.setInt(5, pilot.getTeam().getId());
            int row = stmt.executeUpdate();
            if(row > 0) JOptionPane.showMessageDialog(null,"Piloto insertado");
            else JOptionPane.showMessageDialog(null,"Hubo un error");
        } catch (SQLException e) {
            Logger.getGlobal().log(Level.WARNING, e.getMessage());
            JOptionPane.showMessageDialog(null,"Piloto ya registrado");
        }
    }
}
