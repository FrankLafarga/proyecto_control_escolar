package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocentesModel {
	
    private static final String URL = "jdbc:mysql://localhost:3306/educadex";
    private static final String USER = "root";
    private static final String PASS = "educadex2026";

    public List<Object[]> obtenerDatosTabla() {

        List<Object[]> lista = new ArrayList<>();

        String sql = """
        	    SELECT 
        	        d.clave,
        	        CONCAT(d.nombre, ' ', d.apellido_paterno, ' ', d.apellido_materno) AS nombre_completo,
        	        d.area_estudios AS especialidad,
        	        d.estatus
        	    FROM DOCENTES d
        	""";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("clave"),
                    rs.getString("nombre_completo"),
                    rs.getString("especialidad"),
                    rs.getString("estatus"),
                    ""
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public Object[] verDocente(String clave) {

        Object[] docente = null;

        String sql = """
            SELECT
                CONCAT(
                    nombre,' ',
                    apellido_paterno,' ',
                    apellido_materno
                ) AS nombre_completo,
                clave,
                correo,
                telefono,
                fecha_nacimiento,
                grado_estudios,
                area_estudios,
                estatus,
                avatar,
                nombre,
                apellido_paterno,
                apellido_materno
            FROM DOCENTES
            WHERE clave=?
        """;

        try(
            Connection con=DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps=con.prepareStatement(sql)
        ){

            ps.setString(1, clave);

            try(ResultSet rs=ps.executeQuery()){

                if(rs.next()){

                    docente = new Object[]{

                        rs.getString("nombre_completo"),
                        rs.getString("clave"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("grado_estudios"),
                        rs.getString("area_estudios"),
                        rs.getString("estatus"),
                        rs.getString("avatar"),
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno")
                    };
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return docente;
    } 
    
    public boolean make(
    		String clave,
    		String nombre,
    		String apellidoPat,
    		String apellidoMat,
    		String correo,
    		String telefono,
    		String fecha,
    		String grado,
    		String area,
    		String estatus
    ) {
    	
    	String query = """
    			INSERT INTO DOCENTES(
    				clave,
    				nombre,
    				apellido_paterno,
    				apellido_materno,
    				correo,
    				telefono,
    				fecha_nacimiento,
    				grado_estudios,
    				area_estudios,
    				estatus
    			)
    			VALUES(?,?,?,?,?,?,?,?,?,?)
    			""";
    	
    	Connection conn = null;
    	
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(
    				"jdbc:mysql://localhost:3306/educadex",
    				"root",
    				"educadex2026"
    		);
    		
    		PreparedStatement ps = conn.prepareStatement(query);
    		
    		ps.setString(1, clave);
    		ps.setString(2, nombre);
    		ps.setString(3, apellidoPat);
    		ps.setString(4, apellidoMat);
    		ps.setString(5, correo);
    		ps.setString(6, telefono);
    		ps.setString(7, fecha);
    		ps.setString(8, grado);
    		ps.setString(9, area);
    		ps.setString(10, estatus);
    		
    		int rowsAffected = ps.executeUpdate();
    		
    		if(rowsAffected > 0) {
    			
    			ps.close();
    			conn.close();
    			
    			return true;
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return false;
    }
}