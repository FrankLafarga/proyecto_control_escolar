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
        	        g.id_grupo,
        	        g.nombre,
        	        g.turno,
        	        g.semestre,
        	        g.capacidad,
        	        CONCAT(d.nombre, ' ', d.apellido_paterno, ' ', d.apellido_materno) AS docente
        	    FROM GRUPOS g
        	    INNER JOIN DOCENTES d ON g.id_docente = d.id_docente
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
                    rs.getInt("semestre"),
                    rs.getInt("capacidad"),
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;

    }
}
