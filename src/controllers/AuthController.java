package controllers;
import models.AuthModel;
import views.AppView;
import views.AuthView;

public class AuthController {

    private AuthModel model;
    private AuthView view;
    private AppView app=new AppView();
    

    public AuthController() {
    	model= new AuthModel();
    	view= new AuthView();
    }
    
    public void login() {
    	view.login();
    }

    public boolean autenticar(String usuario, String password) {
        boolean valido = model.validarUsuario(usuario, password);

        if (valido) {            
        	view.mostrarMensaje("Login correcto");          
        	app.inicio();
            return true;
        } else { return false; 
        	}
    }
    
    public void logout() {
        //usuarioActual = null;
    }
    
    
}