package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.GruposModel;

public class GruposController {
	
	private GruposModel model;

    public GruposController() {
        model = new GruposModel();
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        List<Object[]> datos = model.obtenerDatosTabla();

        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    public void cargarGrupo(String nom) {
        model.obtenerGrupo(nom);
    }
    
    public ArrayList<String> obtenerDocentes() {

        return model.obtenerDocentes();
    }
    
    public ArrayList<String> obtenerAsignaturas() {

        return model.obtenerAsignaturas();
    }
    
    public boolean addGrupo(
            String nombre,
            String turno,
            int capacidad,

            String asignatura1,
            String docente1,

            String asignatura2,
            String docente2,

            String asignatura3,
            String docente3,

            String asignatura4,
            String docente4
    ) {

        return model.addGrupo(

                nombre,
                turno,
                capacidad,

                asignatura1,
                docente1,

                asignatura2,
                docente2,

                asignatura3,
                docente3,

                asignatura4,
                docente4
        );
    }

    public int getIdGrupo() {
        return model.getIdGrupo();
    }

    public String getNombre() {
        return model.getNombre();
    }

    public String getTurno() {
        return model.getTurno();
    }

    public int getCapacidad() {
        return model.getCapacidad();
    }

    public int getDocentes() {
        return model.getDocentes();
    }

    public void setIdGrupo(int idGrupo) {
        model.setIdGrupo(idGrupo);
    }

    public void setNombre(String nombre) {
        model.setNombre(nombre);
    }

    public void setTurno(String turno) {
        model.setTurno(turno);
    }

    public void setCapacidad(int capacidad) {
        model.setCapacidad(capacidad);
    }

    public void setDocentes(int docentes) {
        model.setDocentes(docentes);
    }
}