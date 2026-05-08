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

    private int idGrupo;
    private String nombre;
    private String turno;
    private int capacidad;
    private int docentes;

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDocentes() {
        return docentes;
    }

    public void setDocentes(int docentes) {
        this.docentes = docentes;
    }

    public void obtenerGrupo(String nom) {

        String sql = """
            SELECT 
                g.id_grupo,
                g.nombre,
                g.turno,
                g.capacidad,
                COUNT(DISTINCT ga.id_docente) AS docentes
            FROM GRUPOS g
            LEFT JOIN GRUPO_ASIGNATURA ga 
            ON g.id_grupo = ga.id_grupo
            WHERE g.nombre = ?
            GROUP BY g.id_grupo, g.nombre, g.turno, g.capacidad
        """;

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, nom);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                idGrupo = rs.getInt("id_grupo");
                nombre = rs.getString("nombre");
                turno = rs.getString("turno");
                capacidad = rs.getInt("capacidad");
                docentes = rs.getInt("docentes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            LEFT JOIN ALUMNOS a 
            ON g.id_grupo = a.id_grupo
            LEFT JOIN GRUPO_ASIGNATURA ga 
            ON g.id_grupo = ga.id_grupo
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