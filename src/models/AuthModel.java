package models;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean validarUsuario(String user,String password) {
		
		if(user.equals("admin") ) {
			if(password.equals("12345")) {
				return true; 
				
			}else {return false;}
		}else {return false;}	 
	}

}



