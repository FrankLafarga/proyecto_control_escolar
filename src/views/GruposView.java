package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.GruposController;

public class GruposView extends JPanel {

    public GruposView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JTextField txtBusqueda = new JTextField();

        JPanel panel_16 = new JPanel(new BorderLayout());
        panel_16.setOpaque(false);
        panel_16.setPreferredSize(new Dimension(10, 50));
        add(panel_16, BorderLayout.NORTH);

        JPanel panel_17 = new JPanel(new BorderLayout());
        panel_17.setOpaque(false);
        panel_16.add(panel_17, BorderLayout.CENTER);

        JButton btnAgregar = new JButton("+ Agregar grupo");
        btnAgregar.setPreferredSize(new Dimension(250, 45));
        btnAgregar.setBorder(new LineBorder(Color.WHITE, 1, true));
        btnAgregar.setFocusable(false);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnAgregar.setBackground(new Color(14, 48, 170));
        panel_17.add(btnAgregar, BorderLayout.EAST);

        panel_17.add(txtBusqueda, BorderLayout.CENTER);
        
        JPanel panel_18 = new JPanel();
        panel_18.setOpaque(false);
        panel_18.setPreferredSize(new Dimension(15, 0));
        panel_16.add(panel_18, BorderLayout.EAST);

        JPanel panel_19 = new JPanel();
        panel_19.setOpaque(false);
        panel_19.setPreferredSize(new Dimension(15, 0));
        panel_16.add(panel_19, BorderLayout.WEST);
      
        JPanel panel_ESPACIADOR = new JPanel(new BorderLayout());
        panel_ESPACIADOR.setOpaque(false);
        panel_ESPACIADOR.setPreferredSize(new Dimension(15,0));
        add(panel_ESPACIADOR, BorderLayout.WEST);

        JPanel panel_ESPACIADOR2 = new JPanel(new BorderLayout());
        panel_ESPACIADOR2.setOpaque(false);
        panel_ESPACIADOR2.setPreferredSize(new Dimension(15,0));
        add(panel_ESPACIADOR2, BorderLayout.EAST);

        JPanel panel_ESPACIADOR3 = new JPanel(new BorderLayout());
        panel_ESPACIADOR3.setOpaque(false);
        panel_ESPACIADOR3.setPreferredSize(new Dimension(0,20));
        add(panel_ESPACIADOR3, BorderLayout.SOUTH);

        JPanel panel_tabla = new JPanel(new BorderLayout());
        panel_tabla.setBackground(Color.WHITE);
        add(panel_tabla, BorderLayout.CENTER);
        
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int r,int c){return false;}
        };

        modelo.addColumn("Nombre");
        modelo.addColumn("Turno");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Alumnos");
        modelo.addColumn("Docentes");
        modelo.addColumn("Acciones");

        new GruposController().cargarTabla(modelo);

        for(int i=0;i<modelo.getRowCount();i++){
            modelo.setValueAt("", i, 5);
        }

        JTable tabla = new JTable(modelo);
        tabla.getColumn("Acciones").setCellRenderer(new PanelBotones());
        configurarTabla(tabla);

        JScrollPane scroll = new JScrollPane(tabla);
        panel_tabla.add(scroll, BorderLayout.CENTER);
        tabla.setFillsViewportHeight(true);
        scroll.getViewport().setBackground(Color.WHITE);

        SwingUtilities.invokeLater(() -> ajustarColumnas(tabla, scroll, new int[]{20,15,15,15,20,15}));
    }

    private void configurarTabla(JTable tabla){
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(new Color(60,60,60));
        tabla.setGridColor(new Color(235,235,235));

        tabla.getTableHeader().setBackground(Color.WHITE);
        tabla.getTableHeader().setForeground(new Color(14,48,170));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);

        DefaultTableCellRenderer c = new DefaultTableCellRenderer();
        c.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i=0;i<tabla.getColumnCount();i++){
            if(!tabla.getColumnName(i).equals("Acciones")){
                tabla.getColumnModel().getColumn(i).setCellRenderer(c);
            }
        }
    }

    private void ajustarColumnas(JTable tabla, JScrollPane scroll, int[] p){
        int w = scroll.getViewport().getWidth();
        for(int i=0;i<tabla.getColumnCount();i++){
            tabla.getColumnModel().getColumn(i).setPreferredWidth((w*p[i])/100);
        }
    }
}