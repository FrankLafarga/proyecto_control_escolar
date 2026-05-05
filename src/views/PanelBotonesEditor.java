package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class PanelBotonesEditor extends AbstractCellEditor implements TableCellEditor {

    JPanel panel;
    JTable table;
    int fila;
    AccionesTabla acciones;

    public PanelBotonesEditor(JTable table, AccionesTabla acciones) {
        this.table = table;
        this.acciones = acciones;

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JButton ver = crearBoton("/resources/eye.png");
        JButton editar = crearBoton("/resources/square-pen.png");
        JButton eliminar = crearBoton("/resources/trash.png");

        ver.addActionListener(e -> {
            fireEditingStopped();
            acciones.ver(fila);
        });

        editar.addActionListener(e -> {
            fireEditingStopped();
            acciones.editar(fila);
        });

        eliminar.addActionListener(e -> {
            fireEditingStopped();
            acciones.eliminar(fila);
        });

        panel.add(ver);
        panel.add(editar);
        panel.add(eliminar);
    }

    private JButton crearBoton(String ruta) {
        JButton btn = new JButton("");
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        btn.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        btn.setPreferredSize(new Dimension(25, 25));
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        return btn;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.fila = row;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }
}