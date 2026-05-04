package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GruposModel {
    
    private static final String URL = "jdbc:mysql://localhost:3306/educadex";
    private static final String USER = "root";
    private static final String PASS = "educadex2026";
    
    public List<Object[]> obtenerDatosTabla() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT 
                g.nombre,
                g.turno,
                g.capacidad,
                COUNT(DISTINCT a.id_alumno) AS alumnos,
                COUNT(DISTINCT ga.id_docente) AS docentes
            FROM GRUPOS g
            LEFT JOIN ALUMNOS a ON g.id_grupo = a.id_grupo
            LEFT JOIN GRUPO_ASIGNATURA ga ON g.id_grupo = ga.id_grupo
            GROUP BY g.id_grupo, g.nombre, g.turno, g.capacidad
        """;

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("nombre"),
                    rs.getString("turno"),
                    rs.getInt("capacidad"),
                    rs.getInt("alumnos"),
                    rs.getInt("docentes")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}