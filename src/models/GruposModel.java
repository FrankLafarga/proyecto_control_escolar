package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class GruposModel {
    
    private static final String URL = "jdbc:mysql://localhost:3306/educadex";
    private static final String USER = "root";
    private static final String PASS = "educadex2026";

    private int idGrupo;
    private String nombre;
    private String turno;
    private int capacidad;
    private int docentes;
    private String asignaturas;
    private String nombresDocentes;

   
    public void obtenerGrupo(String nom) {

        String sql = """
            SELECT 
                g.id_grupo,
                g.nombre,
                g.turno,
                g.capacidad,
                COUNT(DISTINCT ga.id_docente) AS docentes,
                
        		GROUP_CONCAT(
    		      DISTINCT a.nombre
    		      SEPARATOR ', '
    			) AS asignaturas,
    			
        		GROUP_CONCAT(
    		     DISTINCT CONCAT(
        		    d.nombre,' ',
        		    d.apellido_paterno,' ',
        		    d.apellido_materno
    		     )
	 			 SEPARATOR ', '
        		) AS nombres_docentes
                
            FROM GRUPOS g
            LEFT JOIN GRUPO_ASIGNATURA ga 
            ON g.id_grupo = ga.id_grupo
            
            LEFT JOIN ASIGNATURAS a
    		ON ga.id_asignatura = a.id_asignatura

    		LEFT JOIN DOCENTES d
    		ON ga.id_docente = d.id_docente
    		
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
                asignaturas = rs.getString("asignaturas");
                nombresDocentes = rs.getString("nombres_docentes");
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
    
    public ArrayList<String> obtenerAsignaturas() {

        ArrayList<String> asignaturas = new ArrayList<>();

        String query = """
                SELECT nombre
                FROM ASIGNATURAS
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

                asignaturas.add(
                        rs.getString("nombre")
                );
            }

            rs.close();
            ps.close();
            conn.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return asignaturas;
    }
    
    public boolean addGrupo(
            String nombre,
            String turno,
            int capacidad,

            String asignatura1,
            String docente1,

            String asignatura2,
            String docente2,

            String asignatura3,
            String docente3,

            String asignatura4,
            String docente4,

            String asignatura5,
            String docente5,

            String asignatura6,
            String docente6
    ) {

        String queryGrupo = """
                INSERT INTO GRUPOS(
                    nombre,
                    turno,
                    capacidad
                )
                VALUES(?,?,?)
                """;

        String queryAsignatura = """
                SELECT id_asignatura
                FROM ASIGNATURAS
                WHERE nombre = ?
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

        String queryGA = """
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

            conn.setAutoCommit(false);

            String[] asignaturas = {
                    asignatura1,
                    asignatura2,
                    asignatura3,
                    asignatura4,
                    asignatura5,
                    asignatura6
            };

            String[] docentes = {
                    docente1,
                    docente2,
                    docente3,
                    docente4,
                    docente5,
                    docente6
            };

            Set<String> materiasUsadas = new HashSet<>();
            Set<String> docentesUsados = new HashSet<>();

            for(int i = 0; i < 6; i++) {

                if(
                    asignaturas[i].equals("Sin selección")
                    || docentes[i].equals("Sin selección")
                ) {
                    continue;
                }

                if(materiasUsadas.contains(asignaturas[i])) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error, hay asignaturas repetidas."
                    );

                    conn.rollback();

                    return false;
                }

                if(docentesUsados.contains(docentes[i])) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error, hay docentes repetidos."
                    );

                    conn.rollback();

                    return false;
                }

                materiasUsadas.add(asignaturas[i]);
                docentesUsados.add(docentes[i]);
            }

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

                ResultSet rsGrupo =
                        psGrupo.getGeneratedKeys();

                int idGrupo = 0;

                if(rsGrupo.next()) {
                    idGrupo = rsGrupo.getInt(1);
                }

                for(int i = 0; i < 6; i++) {

                    if(
                        asignaturas[i].equals("Sin selección")
                        || docentes[i].equals("Sin selección")
                    ) {
                        continue;
                    }

                    Integer idAsignatura = null;
                    Integer idDocente = null;

                    PreparedStatement psAsignatura =
                            conn.prepareStatement(queryAsignatura);

                    psAsignatura.setString(1, asignaturas[i]);

                    ResultSet rsAsignatura =
                            psAsignatura.executeQuery();

                    if(rsAsignatura.next()) {

                        idAsignatura =
                                rsAsignatura.getInt(
                                        "id_asignatura"
                                );
                    }

                    rsAsignatura.close();
                    psAsignatura.close();

                    PreparedStatement psDocente =
                            conn.prepareStatement(queryDocente);

                    psDocente.setString(1, docentes[i]);

                    ResultSet rsDocente =
                            psDocente.executeQuery();

                    if(rsDocente.next()) {

                        idDocente =
                                rsDocente.getInt(
                                        "id_docente"
                                );
                    }

                    rsDocente.close();
                    psDocente.close();

                    if(idAsignatura == null || idDocente == null) {
                        continue;
                    }

                    PreparedStatement psGA =
                            conn.prepareStatement(queryGA);

                    psGA.setInt(1, idGrupo);
                    psGA.setInt(2, idAsignatura);
                    psGA.setInt(3, idDocente);

                    psGA.executeUpdate();

                    psGA.close();
                }

                rsGrupo.close();
                psGrupo.close();

                conn.commit();

                return true;
            }

        } catch(Exception e) {

            try {

                if(conn != null) {
                    conn.rollback();
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Error, algunos datos están duplicados, intente de nuevo."
            );

        } finally {

            try {

                if(conn != null) {

                    conn.setAutoCommit(true);
                    conn.close();
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
	
	public boolean eliminarGrupo(String nombreGrupo) {
	
	    String queryEliminar = """
	            DELETE FROM GRUPOS
	            WHERE nombre = ?
	            """;
	
	    Connection conn = null;
	
	    try {
	
	        Class.forName("com.mysql.cj.jdbc.Driver");
	
	        conn = DriverManager.getConnection(
	                URL,
	                USER,
	                PASS
	        );
	
	        PreparedStatement ps =
	                conn.prepareStatement(queryEliminar);
	
	        ps.setString(1, nombreGrupo);
	
	        int rows = ps.executeUpdate();
	
	        ps.close();
	        conn.close();
	
	        return rows > 0;
	
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
    
    public String getAsignaturas() {
        return asignaturas;
    }

    public String getNombresDocentes() {
        return nombresDocentes;
    }

    public void setDocentes(int docentes) {
        this.docentes = docentes;
    }

}