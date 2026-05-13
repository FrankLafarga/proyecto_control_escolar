package controllers;

import models.AlumnosModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AlumnosController {

    private AlumnosModel model;

  //variables para ver alumno
  	private String nombre;
  	private String matricula;
  	private String semestre;
  	private String grupo;
  	private double promedio;
  	private String carrera;
  	
  	private String fecha;
  	private String correo;
  	private String genero;
  	private String telefono;
  	
  	
    public AlumnosController() {
        model = new AlumnosModel();
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        List<Object[]> datos = model.obtenerDatosTabla();

        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
    
    public void verAlumno(String matriculaAlumno) {

        Object[] alumno = model.verAlumno(matriculaAlumno);

        if(alumno != null) {

            nombre = alumno[0].toString();
            matricula = alumno[1].toString();
            semestre = alumno[2].toString();
            grupo = alumno[3].toString();
            promedio = Double.parseDouble(alumno[4].toString());
            carrera = alumno[5].toString();

            fecha = alumno[6].toString();
            correo = alumno[7].toString();
            genero = alumno[8].toString();
            telefono = alumno[9].toString();        }
    }
    
    public String getNombre() {
    	return nombre;
    }

    public String getMatricula() {
    	return matricula;
    }

    public String getSemestre() {
    	return semestre;
    }

    public String getGrupo() {
    	return grupo;
    }

    public double getPromedio() {
    	return promedio;
    }

    public String getCarrera() {
    	return carrera;
    }

    public String getFecha() {
    	return fecha;
    }

    public String getCorreo() {
    	return correo;
    }

    public String getGenero() {
    	return genero;
    }

    public String getTelefono() {
        return telefono;
    }
    
    
    
}