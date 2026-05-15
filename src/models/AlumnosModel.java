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
                a.nombre,
                a.apellido_paterno,
                a.apellido_materno,
                CONCAT(a.nombre, ' ', a.apellido_paterno, ' ', a.apellido_materno) AS nombre_completo,
                a.matricula,
                a.semestre,
                g.nombre AS grupo,
                a.promedio,
                a.carrera,
                a.fecha_nacimiento,
                a.correo,
                a.genero,
                a.telefono,
                a.estatus
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
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("nombre_completo"),
                        rs.getString("matricula"),
                        rs.getString("semestre"),
                        rs.getString("grupo"),
                        rs.getDouble("promedio"),
                        rs.getString("carrera"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("correo"),
                        rs.getString("genero"),
                        rs.getString("telefono"),
                        rs.getString("estatus")
                    };
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alumno;
    }
    
	public boolean addAlumno(
	            String matricula,
	            int semestre,
	            String carrera,
	            String genero,
	            String nombre,
	            String apellidoPat,
	            String apellidoMat,
	            String correo,
	            String telefono,
	            String fecha,
	            double promedio,
	            String estatus,
	            int grupo
	    ) {
	
	        String query = """
	                INSERT INTO ALUMNOS(
	                    matricula,
	                    semestre,
	                    carrera,
	                    genero,
	                    nombre,
	                    apellido_paterno,
	                    apellido_materno,
	                    correo,
	                    telefono,
	                    fecha_nacimiento,
	                    promedio,
	                    estatus,
	                    id_grupo
	                )
	                VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)
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
	
	            ps.setString(1, matricula);
	            ps.setInt(2, semestre);
	            ps.setString(3, carrera);
	            ps.setString(4, genero);
	            ps.setString(5, nombre);
	            ps.setString(6, apellidoPat);
	            ps.setString(7, apellidoMat);
	            ps.setString(8, correo);
	            ps.setString(9, telefono);
	            ps.setDate(10, java.sql.Date.valueOf(fecha));
	            ps.setDouble(11, promedio);
	            ps.setString(12, estatus);
	            ps.setInt(13, grupo);
	
	            int rowsAffected = ps.executeUpdate();
	
	            if(rowsAffected > 0) {
	
	                ps.close();
	                conn.close();
	
	                return true;
	            }
	
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	
	        return false;
    }
	 
	public boolean updateAlumno(
		        String matriculaOriginal,
		        String matricula,
		        int semestre,
		        String carrera,
		        String genero,
		        String nombre,
		        String apellidoPat,
		        String apellidoMat,
		        String correo,
		        String telefono,
		        String fecha,
		        double promedio,
		        String estatus,
		        int grupo
		) {

		    String query = """
		            UPDATE ALUMNOS SET
		                matricula = ?,
		                semestre = ?,
		                carrera = ?,
		                genero = ?,
		                nombre = ?,
		                apellido_paterno = ?,
		                apellido_materno = ?,
		                correo = ?,
		                telefono = ?,
		                fecha_nacimiento = ?,
		                promedio = ?,
		                estatus = ?,
		                id_grupo = ?
		            WHERE matricula = ?
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

		        ps.setString(1, matricula);
		        ps.setInt(2, semestre);
		        ps.setString(3, carrera);
		        ps.setString(4, genero);
		        ps.setString(5, nombre);
		        ps.setString(6, apellidoPat);
		        ps.setString(7, apellidoMat);
		        ps.setString(8, correo);
		        ps.setString(9, telefono);
		        ps.setDate(10, java.sql.Date.valueOf(fecha));
		        ps.setDouble(11, promedio);
		        ps.setString(12, estatus);
		        ps.setInt(13, grupo);

		        ps.setString(14, matriculaOriginal);

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