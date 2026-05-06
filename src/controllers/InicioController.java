package controllers;

import models.InicioModel;
import views.InicioView;

public class InicioController {

    private InicioModel model;
    private InicioView view;

    public InicioController( InicioView view) {
    	model=new InicioModel();
        this.view = view;
        cargarDatos();
    }

    public void cargarDatos() {
    	view.setLblAlumnos(model.getAlumnos());
    	view.setLblDocentes(model.getDocentes());
    	view.setLblGrupos(model.getGrupos());
    	view.setLblAsignaturas(model.getAsignaturas());
    }
}