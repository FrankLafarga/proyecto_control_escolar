package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthModel {

	public UserModel validarUsuario(String user, String password) {

	    String sql = "SELECT id_usuario, username, rol FROM usuarios WHERE username = ? AND password_hash = ? AND estatus='ACTIVO'";

	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/educadex","root","educadex2026");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, user);
	        stmt.setString(2, password);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return new UserModel(
	                rs.getInt("id_usuario"),
	                rs.getString("username"),
	                rs.getString("rol")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}