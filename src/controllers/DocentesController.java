package controllers;

import models.DocentesModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DocentesController {
	private String nombre;
	private String clave;
	private String correo;
	private String telefono;
	private String fecha;
	private String grado;
	private String area;
	private String estatus;
	private String avatar;
    private DocentesModel model;

    public DocentesController() {
        model = new DocentesModel();
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        List<Object[]> datos = model.obtenerDatosTabla();

        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
    
    public void verDocente(String clave) {

        Object[] datos = model.verDocente(clave);

        if(datos != null) {

            nombre = (String) datos[0];
            this.clave = (String) datos[1];
            correo = (String) datos[2];
            telefono = (String) datos[3];
            fecha = (String) datos[4];
            grado = (String) datos[5];
            area = (String) datos[6];
            estatus = (String) datos[7];
            avatar = (String) datos[8];
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public String getGrado() {
        return grado;
    }

    public String getArea() {
        return area;
    }

    public String getEstatus() {
        return estatus;
    }

    public String getAvatar() {
        return avatar;
    }
    
    
    
    
    
}