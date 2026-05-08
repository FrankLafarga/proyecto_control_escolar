package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

import constructor_ventanas.App;
import controllers.GruposController;

public class GruposView extends JPanel {
	private AppView app;
	private JTable tabla;
	 Color azul_principal = new Color(14, 48, 170);

    public GruposView(AppView app) {
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

        JButton btnAgregar = new JButton("+ Agregar grupo");
        btnAgregar.setPreferredSize(new Dimension(250, 45));
        btnAgregar.setBorder(new LineBorder(Color.WHITE, 1, true));
        btnAgregar.setFocusable(false);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnAgregar.setBackground(new Color(14, 48, 170));
        btnAgregar.addActionListener(e ->{
        	agregarGrupo();
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
            public boolean isCellEditable(int r,int c){
            	return c == 5; // cambiar en cada view a la coluiman view
            }
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

        tabla = new JTable(modelo);
        tabla.getColumn("Acciones").setCellRenderer(new PanelBotones());
        configurarTabla(tabla);

        JScrollPane scroll = new JScrollPane(tabla);
        panel_tabla.add(scroll, BorderLayout.CENTER);
        tabla.setFillsViewportHeight(true);
        scroll.getViewport().setBackground(Color.WHITE);

        SwingUtilities.invokeLater(() -> ajustarColumnas(tabla, scroll, new int[]{20,15,15,15,20,15}));
        
        tabla.getColumn("Acciones").setCellEditor(
        	    new PanelBotonesEditor(tabla, new AccionesTabla() {

        	        @Override
        	        public void ver(int fila) {
        	            verGrupos(fila);
        	        }
        	        @Override
        	        public void editar(int fila) {
        	            editarGrupo(fila);
        	        }
        	        @Override
        	        public void eliminar(int fila) {
        	        	eliminarGrupo(fila);
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
    
     public void verGrupos(int fila) {
    	 

	    JPanel contenedor = new JPanel(new BorderLayout());
	    contenedor.setBackground(Color.WHITE);
	
	    JPanel panelSuperior = new JPanel(new BorderLayout());
	    panelSuperior.setBackground(new Color(240,240,240));
	    panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	
	    JButton volver = new JButton("Volver");
	    volver.setIcon(new ImageIcon(App.class.getResource("/resources/flecha16}.png")));
	    volver.setBorder(null);
	    volver.setFocusable(false);
	    volver.setBorderPainted(false);
	    volver.setContentAreaFilled(false);	    
	    volver.setPreferredSize(new Dimension(200, 40));
	    volver.setForeground(new Color(0,0,0));
	    volver.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	    volver.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		volver.setIcon(new ImageIcon(App.class.getResource("/resources/flecha16Azul.png")));
	    		volver.setForeground(azul_principal);
	    	}

	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		volver.setIcon(new ImageIcon(App.class.getResource("/resources/flecha16}.png")));
	    		volver.setForeground(new Color(0, 0, 0));
	    	}
	    });	    	
	    volver.addActionListener(e ->
	        app.cambiarVista(new GruposView(app),
	        "Grupos",
	        "Gestion integral de grupos en el sistema")
	    );
	
	    panelSuperior.add(volver, BorderLayout.EAST);
	
	    JPanel centroWrapper = new JPanel(new BorderLayout());
	    centroWrapper.setBackground(new Color(240,240,240));
	    centroWrapper.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
	
	    JPanel tarjeta = new JPanel();
	    tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
	    tarjeta.setBackground(Color.WHITE);
	    tarjeta.setBorder(BorderFactory.createCompoundBorder(
	        new LineBorder(new Color(200,200,200), 2, true),
	        BorderFactory.createEmptyBorder(20, 30, 20, 30)
	    ));
	    
	    GruposController controller = new GruposController();
	    
	    String nom = tabla.getValueAt(fila, 0).toString();

	    controller.cargarGrupo(nom);

	    JLabel nombre = new JLabel("Grupo: " + controller.getNombre());
	    nombre.setFont(new Font("Segoe UI", Font.BOLD, 26));
	    nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
	    nombre.setHorizontalAlignment(SwingConstants.LEFT);

	    JLabel turno = new JLabel(
	        "Turno: " + controller.getTurno()
	    );

	    JLabel capacidad = new JLabel(
	        "Capacidad: " + controller.getCapacidad()
	    );

	    JLabel docentes = new JLabel(
	        "Docentes: " + controller.getDocentes()
	    );
	    
	    JLabel idGrupo = new JLabel(
	    	    "ID Grupo: " + controller.getIdGrupo()
	    	);
	
	    JLabel[] datos = {idGrupo, turno, capacidad, docentes};
	
	    for(JLabel l : datos){
	        l.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	        l.setAlignmentX(Component.LEFT_ALIGNMENT);
	    }
	    
	    JPanel filaNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    filaNombre.setBackground(Color.WHITE);
	    filaNombre.add(nombre);
	
	    tarjeta.add(filaNombre);
	    tarjeta.add(Box.createVerticalStrut(10));
	
	    for(JLabel l : datos){
	
	        JPanel filaDato = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        filaDato.setBackground(Color.WHITE);
	
	        l.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	
	        filaDato.add(l);
	
	        tarjeta.add(filaDato);
	    }
	
	    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    panelBoton.setBackground(Color.WHITE);
	
	    JButton pdf = new JButton(
	    		"<html>" +
	    	    "<span style='color:black; font-family:Segoe UI; font-size:16px;'>Imprimir </span>" +
	    	    "<span style='color:red; font-family:Segoe UI; font-size:16px;'><b>PDF</b></span>" +
	    	    "</html>");
	    pdf.setPreferredSize(new Dimension(180, 40));
	    pdf.setFocusable(false);
	    pdf.setContentAreaFilled(false);
	
	    panelBoton.add(pdf);
	
	    tarjeta.add(Box.createVerticalStrut(20));
	    tarjeta.add(panelBoton);
	
	    centroWrapper.add(tarjeta, BorderLayout.NORTH);
	
	    contenedor.add(panelSuperior, BorderLayout.NORTH);
	    contenedor.add(centroWrapper, BorderLayout.CENTER);
	
	    app.cambiarVista(contenedor,
	        "Grupos",
	        "Detalle de los grupos seleccionada");
     }
    
    public void editarGrupo(int fila) {

        JPanel detalle = new JPanel(new BorderLayout());
        detalle.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("EDITAR GRUPO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel info = new JLabel("Editando grupo en fila: " + fila);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        info.setHorizontalAlignment(SwingConstants.CENTER);

        JButton volver = new JButton("VOLVER");
        volver.setPreferredSize(new Dimension(250, 45));
        volver.setBorder(new LineBorder(Color.WHITE, 1, true));
        volver.setFocusable(false);
        volver.setForeground(Color.WHITE);
        volver.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        volver.setBackground(new Color(14, 48, 170));

        volver.addActionListener(e -> app.cambiarVista(new GruposView(app), "Grupos", "Gestion integral de grupos en el sistema"));

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(volver);

        detalle.add(titulo, BorderLayout.NORTH);
        detalle.add(info, BorderLayout.CENTER);
        detalle.add(panelBtn, BorderLayout.SOUTH);

        app.cambiarVista(detalle, "Grupo", "Editar grupo seleccionado");
    }
    
    public void eliminarGrupo(int fila) {

        String nombreGrupo = tabla.getValueAt(fila, 0).toString();
        
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar el grupo " + nombreGrupo + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
        	JOptionPane.showMessageDialog(
        			null,
        			"Se eliminó el grupo: " + nombreGrupo,
        			"Grupo eliminado",
        			JOptionPane.INFORMATION_MESSAGE
        			
        			);
        	
        } else if (confirm == JOptionPane.NO_OPTION) {
        	System.out.println("Accion cancelada");
        	
     }
        	


    }
    
    public void agregarGrupo() {

        JPanel detalle = new JPanel(new BorderLayout());
        detalle.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("CREAR GRUPO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel info = new JLabel("Formulario para crear un nuevo grupo");
        info.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        info.setHorizontalAlignment(SwingConstants.CENTER);

        JButton volver = new JButton("VOLVER");
        volver.setPreferredSize(new Dimension(250, 45));
        volver.setBorder(new LineBorder(Color.WHITE, 1, true));
        volver.setFocusable(false);
        volver.setForeground(Color.WHITE);
        volver.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        volver.setBackground(new Color(14, 48, 170));

        volver.addActionListener(e -> app.cambiarVista(new GruposView(app), "Grupos", "Gestion integral de grupos en el sistema"));

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.add(volver);

        detalle.add(titulo, BorderLayout.NORTH);
        detalle.add(info, BorderLayout.CENTER);
        detalle.add(panelBtn, BorderLayout.SOUTH);

        app.cambiarVista(detalle, "Grupo", "Crear grupo");
    }
}
