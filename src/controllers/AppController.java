package controllers;

public class AppController {

    private AuthController auth;

    public AppController(AuthController auth) {
        this.auth = auth;
    }

    public void cerrarSesion() {
        auth.logout();
    }
}
