package controllers;

import models.AlumnosModel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.awt.Image;
import java.util.List;

public class AlumnosController {

    private AlumnosModel model;

    private String nombres;
    private String apellidoPat;
    private String apellidoMat;

    private String nombre_completo;
    private String matricula;
    private String semestre;
    private String grupo;
    private double promedio;
    private String carrera;

    private String fecha;
    private String correo;
    private String genero;
    private String telefono;
    private String estatus;
    private ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/resources/activo-icon.png"));
    private	Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(136,26,Image.SCALE_SMOOTH);
    private	ImageIcon iconoActivo = new ImageIcon(imagenEscalada);

    private ImageIcon iconoinOriginal = new ImageIcon(getClass().getResource("/resources/inactivo-icon.png"));
    private	Image imageninEscalada = iconoinOriginal.getImage().getScaledInstance(136,26,Image.SCALE_SMOOTH);
    private	ImageIcon iconoInactivo = new ImageIcon(imageninEscalada);
    
    public AlumnosController() {
        model = new AlumnosModel();
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        List<Object[]> datos = model.obtenerDatosTabla();

        for (Object[] fila : datos) {
        	if(fila[5].equals("ACTIVO")) {
              	 fila[5]=iconoActivo;
              	}
           	else if(fila[5].equals("INACTIVO")) {
           		fila[5]=iconoInactivo;
           	}
            modeloTabla.addRow(fila);
        }
    }

    public void verAlumno(String matriculaAlumno) {

        Object[] alumno = model.verAlumno(matriculaAlumno);

        if(alumno != null) {

            nombres = alumno[0].toString();
            apellidoPat = alumno[1].toString();
            apellidoMat = alumno[2].toString();

            nombre_completo = alumno[3].toString();
            matricula = alumno[4].toString();
            semestre = alumno[5].toString();
            grupo = alumno[6].toString();
            promedio = Double.parseDouble(alumno[7].toString());
            carrera = alumno[8].toString();

            fecha = alumno[9].toString();
            correo = alumno[10].toString();
            genero = alumno[11].toString();
            telefono = alumno[12].toString();
            estatus = alumno[13].toString();
        }
    }
    
    public boolean addAlumno(
            String matricula,
            int semestre,
            String carrera,
            String genero,
            String nombre,
            String apellidoPat,
            String apellidoMat,
            String correo,
            String telefono,
            String fecha,
            double promedio,
            String estatus,
            Integer grupo
    ) {

        return model.addAlumno(
                matricula,
                semestre,
                carrera,
                genero,
                nombre,
                apellidoPat,
                apellidoMat,
                correo,
                telefono,
                fecha,
                promedio,
                estatus,
                grupo
        );
    }
    
    public boolean eliminarAlumno(String matricula) {

        return model.eliminarAlumno(matricula);
    }
    
    public boolean updateAlumno(
            String matriculaOriginal,
            String matricula,
            int semestre,
            String carrera,
            String genero,
            String nombre,
            String apellidoPat,
            String apellidoMat,
            String correo,
            String telefono,
            String fecha,
            double promedio,
            String estatus,
            Integer grupo
    ) {

        return model.updateAlumno(
                matriculaOriginal,
                matricula,
                semestre,
                carrera,
                genero,
                nombre,
                apellidoPat,
                apellidoMat,
                correo,
                telefono,
                fecha,
                promedio,
                estatus,
                grupo
        );
    }
    
    public List<String> obtenerGrupos(){
        return model.obtenerGrupos();
    }
    
    public List<Object[]> obtenerAlumnosPorGrupo(int idGrupo){
        return model.obtenerAlumnosPorGrupo(idGrupo);
    }
    
    public boolean existeMatricula(String matricula){
        return model.existeMatricula(matricula);
    }    
    
    public int obtenerIdGrupo(String nombre){
        return model.obtenerIdGrupo(nombre);
    }

    public String getNombres() {
        return nombres;
    }
    
    public String getApellidoPat() {
        return apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public String getNombre_completo() {
        return nombre_completo;
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
    
    public String getEstatus() {
        return estatus;
    }

}