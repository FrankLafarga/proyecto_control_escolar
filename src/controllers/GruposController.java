package controllers;

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

}
