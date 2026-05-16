package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AsignaturasModel {
    
    private static final String URL = "jdbc:mysql://localhost:3306/educadex";
    private static final String USER = "root";
    private static final String PASS = "educadex2026";

    public List<Object[]> obtenerDatosTabla() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
        	    SELECT 
        	        a.clave,
        	        a.nombre,
        	        a.semestre,
        	        a.creditos,
        	        IFNULL(g.nombre, 'Sin grupo') AS grupo,
        	        IFNULL(
        	            CONCAT(
        	                d.nombre,' ',
        	                d.apellido_paterno,' ',
        	                d.apellido_materno
        	            ),
        	            'Sin docente'
        	        ) AS docente
        	    FROM ASIGNATURAS a
        	    LEFT JOIN GRUPO_ASIGNATURA ga 
        	        ON a.id_asignatura = ga.id_asignatura
        	    LEFT JOIN GRUPOS g 
        	        ON ga.id_grupo = g.id_grupo
        	    LEFT JOIN DOCENTES d 
        	        ON ga.id_docente = d.id_docente
        	""";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("clave"),
                    rs.getString("nombre"),
                    rs.getInt("semestre"),
                    rs.getInt("creditos"),
                    rs.getString("grupo"),
                    rs.getString("docente")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public Object[] verAsignatura(String clave) {

        Object[] asignatura = null;

        String sql = """
        	    SELECT 
        	        a.nombre,
        	        a.clave,
        	        a.semestre,
        	        a.creditos,
        	        IFNULL(g.nombre, 'Sin grupo') AS grupo,
        	        IFNULL(
        	            CONCAT(
        	                d.nombre,' ',
        	                d.apellido_paterno,' ',
        	                d.apellido_materno
        	            ),
        	            'Sin docente'
        	        ) AS docente
        	    FROM ASIGNATURAS a
        	    LEFT JOIN GRUPO_ASIGNATURA ga 
        	        ON a.id_asignatura = ga.id_asignatura
        	    LEFT JOIN GRUPOS g 
        	        ON ga.id_grupo = g.id_grupo
        	    LEFT JOIN DOCENTES d 
        	        ON ga.id_docente = d.id_docente
        	    WHERE a.clave=?
        	""";

        try(
            Connection con = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps = con.prepareStatement(sql)
        ){

            ps.setString(1, clave);

            try(ResultSet rs=ps.executeQuery()){

                if(rs.next()){

                    asignatura = new Object[]{

                        rs.getString("nombre"),
                        rs.getString("clave"),
                        rs.getInt("semestre"),
                        rs.getInt("creditos"),
                        rs.getString("grupo"),
                        rs.getString("docente")
                    };
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return asignatura;
    }
    
    public boolean addAsignatura(
            String nombre,
            String clave,
            int semestre,
            int creditos
    ) {

        String query = """
                INSERT INTO ASIGNATURAS(
                    nombre,
                    clave,
                    semestre,
                    creditos
                )
                VALUES(?,?,?,?)
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

            ps.setString(1, nombre);
            ps.setString(2, clave);
            ps.setInt(3, semestre);
            ps.setInt(4, creditos);

            int rowsAffected = ps.executeUpdate();

            ps.close();
            conn.close();

            return rowsAffected > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}