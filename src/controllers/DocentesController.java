package controllers;

import models.DocentesModel;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import java.awt.Image;
import java.util.List;

public class DocentesController {
	private String nombre_completo;
	private String clave;
	private String correo;
	private String telefono;
	private String fecha;
	private String grado;
	private String area;
	private String estatus;
	private String avatar;
    private DocentesModel model;
	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	
	private ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/resources/activo-icon.png"));
    private	Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(136,26,Image.SCALE_SMOOTH);
    private	ImageIcon iconoActivo = new ImageIcon(imagenEscalada);
    
    private ImageIcon iconoinOriginal = new ImageIcon(getClass().getResource("/resources/inactivo-icon.png"));
    private	Image imageninEscalada = iconoinOriginal.getImage().getScaledInstance(136,26,Image.SCALE_SMOOTH);
    private	ImageIcon iconoInactivo = new ImageIcon(imageninEscalada);
    
    public DocentesController() {
        model = new DocentesModel();
    }

    public void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        List<Object[]> datos = model.obtenerDatosTabla();

        for (Object[] fila : datos) {
        	if(fila[3].equals("ACTIVO")) {
           	 fila[3]=iconoActivo;
           	}
        	else if(fila[3].equals("INACTIVO")) {
        		fila[3]=iconoInactivo;
        	}
            modeloTabla.addRow(fila);
        }
    }
    
    public void verDocente(String clave) {

        Object[] datos = model.verDocente(clave);

        if(datos != null) {

        	nombre_completo = (String) datos[0];
            this.clave = (String) datos[1];
            correo = (String) datos[2];
            telefono = (String) datos[3];
            fecha = (String) datos[4];
            grado = (String) datos[5];
            area = (String) datos[6];
            estatus = (String) datos[7];
            avatar = (String) datos[8];
            nombre = (String) datos[9];
            apellidoPat = (String) datos[10];
            apellidoMat = (String) datos[11];
        }
    }
    
    public boolean addDocente(
    		String clave,
    		String nombre,
    		String apellidoPat,
    		String apellidoMat,
    		String correo,
    		String telefono,
    		String fecha,
    		String grado,
    		String area,
    		String estatus
    ) {
    	
    	boolean flag = model.make(
    			clave,
    			nombre,
    			apellidoPat,
    			apellidoMat,
    			correo,
    			telefono,
    			fecha,
    			grado,
    			area,
    			estatus
    	);
    	
    	return flag;
    }
    
    public boolean eliminarDocente(String clave) {

        return model.eliminarDocente(clave);
    }

    public String getNombre_completo() {
        return nombre_completo;
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

	public String getNombre() {
		return nombre;
	}

	public String getApellidoPat() {
		return apellidoPat;
	}

	public String getApellidoMat() {
		return apellidoMat;
	}
	public ImageIcon getImgEstatus() {
    	if(estatus.equals("ACTIVO")) {
            return iconoActivo;

        }
       	else if(estatus.equals("INACTIVO")) {
            return iconoInactivo;
       	}
		return null;
    }
    
    
    
    
}