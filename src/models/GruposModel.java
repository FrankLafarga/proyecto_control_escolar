package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    
    public ArrayList<String> obtenerDocentes() {

        ArrayList<String> docentes = new ArrayList<>();

        String query = """
                SELECT nombre, apellido_paterno, apellido_materno
                FROM DOCENTES
                """;

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASS
            );

            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                String nombreCompleto =
                        rs.getString("nombre") + " "
                        + rs.getString("apellido_paterno") + " "
                        + rs.getString("apellido_materno");

                docentes.add(nombreCompleto);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return docentes;
    }
    
    public boolean addGrupo(
            String nombre,
            String turno,
            int capacidad,
            String docente1,
            String docente2,
            String docente3,
            String docente4
    ) {

        String queryGrupo = """
                INSERT INTO GRUPOS(
                    nombre,
                    turno,
                    capacidad
                )
                VALUES(?,?,?)
                """;

        String queryDocente = """
                SELECT id_docente
                FROM DOCENTES
                WHERE CONCAT(
                    nombre,' ',
                    apellido_paterno,' ',
                    apellido_materno
                ) = ?
                """;

        String queryGrupoAsignatura = """
                INSERT INTO GRUPO_ASIGNATURA(
                    id_grupo,
                    id_asignatura,
                    id_docente
                )
                VALUES(?,?,?)
                """;

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASS
            );

            PreparedStatement psGrupo =
                    conn.prepareStatement(
                            queryGrupo,
                            Statement.RETURN_GENERATED_KEYS
                    );

            psGrupo.setString(1, nombre);
            psGrupo.setString(2, turno);
            psGrupo.setInt(3, capacidad);

            int rows = psGrupo.executeUpdate();

            if(rows > 0) {

                ResultSet rsGrupo = psGrupo.getGeneratedKeys();

                int idGrupo = 0;

                if(rsGrupo.next()) {
                    idGrupo = rsGrupo.getInt(1);
                }

                String[] docentes = {
                        docente1,
                        docente2,
                        docente3,
                        docente4
                };

                for(int i = 0; i < docentes.length; i++) {

                    PreparedStatement psDocente =
                            conn.prepareStatement(queryDocente);

                    psDocente.setString(1, docentes[i]);

                    ResultSet rsDocente = psDocente.executeQuery();

                    int idDocente = 0;

                    if(rsDocente.next()) {
                        idDocente = rsDocente.getInt("id_docente");
                    }

                    PreparedStatement psGA =
                            conn.prepareStatement(queryGrupoAsignatura);

                    psGA.setInt(1, idGrupo);
                    psGA.setInt(2, i + 1);
                    psGA.setInt(3, idDocente);

                    psGA.executeUpdate();

                    rsDocente.close();
                    psDocente.close();
                    psGA.close();
                }

                rsGrupo.close();
                psGrupo.close();
                conn.close();

                return true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
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

}