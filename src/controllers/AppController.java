package controllers;

import views.AppView;

public class AppController {
	
	AuthController auth=new AuthController();

	private AppView app;

	public void cerrarSesion(AppView ventana) {
		app=ventana;
		auth.logout();
		app.dispose();
		auth.login();
	}
}
