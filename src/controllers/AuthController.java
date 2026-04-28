package controllers;
import models.AuthModel;
import views.AuthView;

public class AuthController {

    private AuthModel model;
    private AuthView view;

    public AuthController(AuthModel model, AuthView view) {
        this.model = model;
        this.view = view;
    }

    public boolean login(String usuario, String password) {
        boolean valido = model.validarUsuario(usuario, password);

        if (valido) {            
        	view.mostrarMensaje("Login correcto");          
        	//view.inicio();
            return true;
        } else { return false; 
        	}
    }
}