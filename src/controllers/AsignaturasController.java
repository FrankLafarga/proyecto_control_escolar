package controllers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.AsignaturasModel;


public class AsignaturasController {
	private String nombre;
	private int semestre;
	private int creditos;
	private String grupo;
	private String docente;
	
	private AsignaturasModel model;

    public AsignaturasController() {
        model = new AsignaturasModel();
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        List<Object[]> datos = model.obtenerDatosTabla();

        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
    
    public void verAsignatura(String clave) {

        Object[] datos = model.verAsignatura(clave);

        if(datos != null) {

            nombre = (String) datos[0];
            semestre = (int) datos[2];
            creditos = (int) datos[3];
            grupo = (String) datos[4];
            docente = (String) datos[5];
        }
    }
    
    public boolean addAsignatura(
            String nombre,
            String clave,
            int semestre,
            int creditos
    ) {

        return model.addAsignatura(
                nombre,
                clave,
                semestre,
                creditos
        );
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getCreditos() {
        return creditos;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getDocente() {
        return docente;
    }
}
