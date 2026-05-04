package controllers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.AsignaturasModel;


public class AsignaturasController {
	
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
}
