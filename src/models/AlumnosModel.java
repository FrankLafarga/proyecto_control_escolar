package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnosModel {

    private static final String URL = "jdbc:mysql://localhost:3306/educadex";
    private static final String USER = "root";
    private static final String PASS = "educadex2026";

    public List<Object[]> obtenerDatosTabla() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT 
                a.matricula,
                CONCAT(a.nombre, ' ', a.apellido_paterno, ' ', a.apellido_materno) AS nombre_completo,
                g.nombre AS grupo,
                a.semestre,
                a.promedio,
                a.estatus
            FROM ALUMNOS a
            LEFT JOIN GRUPOS g ON a.id_grupo = g.id_grupo
        """;

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("matricula"),
                    rs.getString("nombre_completo"),
                    rs.getString("grupo"),
                    rs.getInt("semestre"),
                    rs.getDouble("promedio"),
                    rs.getString("estatus"),
                    ""
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public Object[] verAlumno(String matricula) {

        Object[] alumno = null;

        String sql = """
            SELECT 
                CONCAT(a.nombre, ' ', a.apellido_paterno, ' ', a.apellido_materno) AS nombre_completo,
                a.matricula,
                a.semestre,
                g.nombre AS grupo,
                a.promedio,
                a.carrera,
                a.fecha_nacimiento,
                a.correo,
                a.genero,
                a.telefono
            FROM ALUMNOS a
            LEFT JOIN GRUPOS g ON a.id_grupo = g.id_grupo
            WHERE a.matricula = ?
        """;

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, matricula);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    alumno = new Object[] {
                        rs.getString("nombre_completo"),
                        rs.getString("matricula"),
                        rs.getString("semestre"),
                        rs.getString("grupo"),
                        rs.getDouble("promedio"),
                        rs.getString("carrera"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("correo"),
                        rs.getString("genero"),
                        rs.getString("telefono")
                    };
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alumno;
    }
    
    
    
}