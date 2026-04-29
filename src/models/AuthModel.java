package models;

public class AuthModel {

	public AuthModel() {
		
	}
	
	public boolean validarUsuario(String user,String password) {
		
		if(user.equals("") ) {
			if(password.equals("")) {
				return true; 
				
			}else {return false;}
		}else {return false;}	 
	}

}



