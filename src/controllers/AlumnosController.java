package controllers;

import models.AlumnosModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AlumnosController {

    private AlumnosModel model;

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
}