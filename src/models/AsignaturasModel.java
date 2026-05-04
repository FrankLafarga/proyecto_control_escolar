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
        	        g.nombre AS grupo,
        	        CONCAT(d.nombre, ' ', d.apellido_paterno, ' ', d.apellido_materno) AS docente
        	    FROM ASIGNATURAS a
        	    INNER JOIN GRUPOS g ON a.id_grupo = g.id_grupo
        	    INNER JOIN DOCENTES d ON a.id_docente = d.id_docente
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
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
