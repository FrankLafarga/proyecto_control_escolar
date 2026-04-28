package application;

import controllers.AuthController;

public class Main {

	public static void main(String[] args) {
        //AuthView view = new AuthView();
        AuthController controller = new AuthController();
        controller.login();
        
        //view.login(); 
	}

}