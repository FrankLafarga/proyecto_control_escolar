package application;

import controllers.AuthController;
import views.AppView;

public class EducadexMain {

	public static void main(String[] args) {
        AuthController controller = new AuthController();
        controller.login();
        
		//AppView app = new AppView();		
		//app.ventana();
        
	
	}

}