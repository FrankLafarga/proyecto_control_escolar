package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InicioModel {

    private int contar(String tabla) {

        String sql = "SELECT COUNT(*) AS total FROM " + tabla;

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/educadex",
                "root",
                "educadex2026");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getAlumnos() {
        return contar("ALUMNOS");
    }

    public int getDocentes() {
        return contar("DOCENTES");
    }

    public int getGrupos() {
        return contar("GRUPOS");
    }

    public int getAsignaturas() {
        return contar("ASIGNATURAS");
    }
}