package org.example.Challenge5.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class PilotConnection {
    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/mafia";
        try {
            con = DriverManager.getConnection(url, "root", "reto5");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

}
