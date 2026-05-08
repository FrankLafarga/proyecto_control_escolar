package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import controllers.AlumnosController;

public class AlumnosView extends JPanel {
	private AppView app;
	private JTable tabla;

    public AlumnosView(AppView app) {
    	this.app = app;
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

        JButton btnAgregar = new JButton("+ Agregar alumno");
        btnAgregar.setPreferredSize(new Dimension(250, 45));
        btnAgregar.setBorder(new LineBorder(Color.WHITE, 1, true));
        btnAgregar.setFocusable(false);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnAgregar.setBackground(new Color(14, 48, 170));
        btnAgregar.addActionListener(e ->{
        	agregarAlumno();
        });
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
        panel_ESPACIADOR.setPreferredSize(new Dimension(15,0));
        add(panel_ESPACIADOR, BorderLayout.WEST);

        JPanel panel_ESPACIADOR2 = new JPanel(new BorderLayout());
        panel_ESPACIADOR2.setPreferredSize(new Dimension(15,0));
        add(panel_ESPACIADOR2, BorderLayout.EAST);

        JPanel panel_ESPACIADOR3 = new JPanel(new BorderLayout());
        panel_ESPACIADOR3.setPreferredSize(new Dimension(0,20));
        add(panel_ESPACIADOR3, BorderLayout.SOUTH);

        JPanel panel_tabla = new JPanel(new BorderLayout());
        panel_tabla.setBackground(Color.WHITE);
        add(panel_tabla, BorderLayout.CENTER);

        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int r, int c) { 
            	return c == 6; 
            
            }
        };

        modelo.addColumn("Matrícula");
        modelo.addColumn("Nombre Completo");
        modelo.addColumn("Grupo");
        modelo.addColumn("Semestre");
        modelo.addColumn("Promedio");
        modelo.addColumn("Estatus");
        modelo.addColumn("Acciones");

        new AlumnosController().cargarTabla(modelo);

        tabla = new JTable(modelo);
        tabla.getColumn("Acciones").setCellRenderer(new PanelBotones());
        configurarTabla(tabla);

        JScrollPane scroll = new JScrollPane(tabla);
        panel_tabla.add(scroll, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> ajustarColumnas(tabla, scroll, new int[]{12,20,12,12,12,12,20}));
        
        tabla.getColumn("Acciones").setCellEditor(
        	    new PanelBotonesEditor(tabla, new AccionesTabla() {

        	        @Override
        	        public void ver(int fila) {
        	            verAlumno(fila);
        	        }
        	        @Override
        	        public void editar(int fila) {
        	            editarAlumno(fila);
        	        }
        	        @Override
        	        public void eliminar(int fila) {
        	        	eliminarAlumno(fila);
        	        }
        	    })
        	);
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
    
    public void verAlumno(int fila) {

        JPanel detalle = new JPanel(new BorderLayout());
        detalle.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("DETALLE DEL ALUMNO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel info = new JLabel("Alumno seleccionado en fila: " + fila);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        info.setHorizontalAlignment(SwingConstants.CENTER);

        JButton volver = new JButton("VOLVER");
        volver.setPreferredSize(new Dimension(250, 45));
        volver.setBorder(new LineBorder(Color.WHITE, 1, true));
        volver.setFocusable(false);
        volver.setForeground(Color.WHITE);
        volver.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        volver.setBackground(new Color(14, 48, 170));

        volver.addActionListener(e -> app.cambiarVista(new AlumnosView(app), "Alumnos", "Gestion integral de alumnos en el sistema"));

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(volver);

        detalle.add(titulo, BorderLayout.NORTH);
        detalle.add(info, BorderLayout.CENTER);
        detalle.add(panelBtn, BorderLayout.SOUTH);

        app.cambiarVista(detalle, "Alumno", "Detalle del alumno seleccionado");
    }

    public void editarAlumno(int fila) {

        JPanel detalle = new JPanel(new BorderLayout());
        detalle.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("EDITAR ALUMNO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel info = new JLabel("Editando alumno en fila: " + fila);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        info.setHorizontalAlignment(SwingConstants.CENTER);

        JButton volver = new JButton("VOLVER");
        volver.setPreferredSize(new Dimension(250, 45));
        volver.setBorder(new LineBorder(Color.WHITE, 1, true));
        volver.setFocusable(false);
        volver.setForeground(Color.WHITE);
        volver.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        volver.setBackground(new Color(14, 48, 170));

        volver.addActionListener(e -> app.cambiarVista(new AlumnosView(app), "Alumnos", "Gestion integral de alumnos en el sistema"));

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(volver);

        detalle.add(titulo, BorderLayout.NORTH);
        detalle.add(info, BorderLayout.CENTER);
        detalle.add(panelBtn, BorderLayout.SOUTH);

        app.cambiarVista(detalle, "Alumno", "Editar alumno seleccionado");
    }

    public void eliminarAlumno(int fila) {

		String nombreAlumno = tabla.getValueAt(fila, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar el alumno " + nombreAlumno + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
        	JOptionPane.showMessageDialog(
        			null,
        			"Se eliminó el alumno: " + nombreAlumno,
        			"Alumno eliminado",
        			JOptionPane.INFORMATION_MESSAGE
        			
        			);
        	
        } else if (confirm == JOptionPane.NO_OPTION) {
        	System.out.println("Accion cancelada");
        	
        }
    }
    
    public void agregarAlumno() {

        JPanel detalle = new JPanel(new BorderLayout());
        detalle.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("CREAR ALUMNO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel info = new JLabel("Formulario para crear un nuevo alumno");
        info.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        info.setHorizontalAlignment(SwingConstants.CENTER);

        JButton volver = new JButton("VOLVER");
        volver.setPreferredSize(new Dimension(250, 45));
        volver.setBorder(new LineBorder(Color.WHITE, 1, true));
        volver.setFocusable(false);
        volver.setForeground(Color.WHITE);
        volver.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        volver.setBackground(new Color(14, 48, 170));

        volver.addActionListener(e -> app.cambiarVista(new AlumnosView(app), "Alumnos", "Gestion integral de alumnos en el sistema"));

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(volver);

        detalle.add(titulo, BorderLayout.NORTH);
        detalle.add(info, BorderLayout.CENTER);
        detalle.add(panelBtn, BorderLayout.SOUTH);

        app.cambiarVista(detalle, "Alumno", "Crear alumno");
    }
}