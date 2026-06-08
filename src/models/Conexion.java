package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static final String URL  = "jdbc:mysql://sql.freedb.tech:3306/freedb_Rxynd1sr";
	private static final String USER = "u_SFIRUf";
	private static final String PASS = "dcwwrhL6f8ZE";

    public static Connection getConnection() {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL,USER,PASS);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}

/* 
private static final String URL ="jdbc:mysql://localhost:3306/educadex";
private static final String USER ="root";
private static final String PASS ="educadex2026";

*/