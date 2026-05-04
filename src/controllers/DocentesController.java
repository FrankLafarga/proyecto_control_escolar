package controllers;

import models.DocentesModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DocentesController {

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
}