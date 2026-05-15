package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import constructor_ventanas.App;
import controllers.GruposController;

public class GruposView extends JPanel {
	private AppView app;
	private JTable tabla;
	private Color azul_principal = new Color(14, 48, 170);

	private int idGrupo;
    private String nombre;
    private String turno;
    private int capacidad;
    private int docentes;
    
    GruposController controller = new GruposController();

    public GruposView(AppView app) {
		this.app = app;
	    setLayout(new BorderLayout());
	    setBackground(Color.WHITE);
	
	    JTextField txtBusqueda = new JTextField("Buscar grupo...");
	    txtBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    txtBusqueda.setForeground(Color.GRAY);
	
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
	        	return c == 5;
	        }
	    };
	
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Turno");
	    modelo.addColumn("Capacidad");
	    modelo.addColumn("Alumnos");
	    modelo.addColumn("Docentes");
	    modelo.addColumn("Acciones");
	
	    controller.cargarTabla(modelo);
	
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
	
	    SwingUtilities.invokeLater(() -> 
	    	ajustarColumnas(tabla, scroll, new int[]{20,15,15,15,20,15})
	    );
	
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
	    tabla.setRowSorter(sorter);
	
	    txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
	
	    	@Override
	    	public void focusGained(java.awt.event.FocusEvent e) {
	
	    		if(txtBusqueda.getText().equals("Buscar grupo...")) {
	
	    			txtBusqueda.setText("");
	    			txtBusqueda.setForeground(Color.BLACK);
	    		}
	    	}
	
	    	@Override
	    	public void focusLost(java.awt.event.FocusEvent e) {
	
	    		if(txtBusqueda.getText().trim().isEmpty()) {
	
	    			txtBusqueda.setText("Buscar grupo...");
	    			txtBusqueda.setForeground(Color.GRAY);
	    		}
	    	}
	    });
	
	    txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
	
	    	@Override
	    	public void keyReleased(java.awt.event.KeyEvent e) {
	
	    		String texto = txtBusqueda.getText();
	
	    		if(texto.equals("Buscar grupo...")) {
	
	    			sorter.setRowFilter(null);
	    		}
	    		else {
	
	    			sorter.setRowFilter(
	    				RowFilter.regexFilter("(?i)" + texto)
	    			);
	    		}
	    	}
	    });
	
	    tabla.getColumn("Acciones").setCellEditor(
		    new PanelBotonesEditor(tabla, new AccionesTabla() {
	
		        @Override
		        public void ver(int fila) {
		        	setTodo(fila);
		            verGrupos(fila);
		        }
	
		        @Override
		        public void editar(int fila) {
		        	setTodo(fila);
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
	    	    	    
	    JLabel nombre = new JLabel("Grupo: " + this.nombre);
	    nombre.setFont(new Font("Segoe UI", Font.BOLD, 26));
	    nombre.setAlignmentX(Component.LEFT_ALIGNMENT);
	    nombre.setHorizontalAlignment(SwingConstants.LEFT);

	    JLabel turno = new JLabel(
	        "Turno: " + this.turno
	    );

	    JLabel capacidad = new JLabel(
	        "Capacidad: " + this.capacidad
	    );

	    JLabel docentes = new JLabel(
	        "Docentes: " + this.docentes
	    );
	    
	    JLabel idGrupo = new JLabel(
	    	    "ID Grupo: " + this.idGrupo
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
	        "Grupo",
	        "Detalle de los grupos seleccionada");
     }
    
    public void editarGrupo(int fila) {

    JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setBackground(new Color(245, 247, 250));

    JPanel panelSuperior = new JPanel(new BorderLayout());
    panelSuperior.setBackground(new Color(245, 247, 250));
    panelSuperior.setBorder(BorderFactory.createEmptyBorder(25, 40, 10, 40));

    JLabel subtitulo1 = new JLabel("Editar Grupo seleccionado");
    subtitulo1.setForeground(azul_principal);
    subtitulo1.setFont(new Font("Times New Roman", Font.BOLD, 28));
    
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
    panelSuperior.add(subtitulo1, BorderLayout.WEST);

    JPanel panelCentroWrapper = new JPanel(new BorderLayout());
    panelCentroWrapper.setBackground(new Color(245, 247, 250));
    panelCentroWrapper.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));

    JPanel tarjeta = new JPanel();
    tarjeta.setBackground(Color.WHITE);
    tarjeta.setLayout(new GridBagLayout());
    tarjeta.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220,220,220), 1, true),
        BorderFactory.createEmptyBorder(35, 35, 35, 35)
    ));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;

    JLabel lblNombreGrupo = new JLabel("Nombre del grupo");
    lblNombreGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JLabel lblSemestre = new JLabel("Semestre");
    lblSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JTextField txtGrupo = new JTextField(nombre);
    txtGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtGrupo.setPreferredSize(new Dimension(200, 45));

    JComboBox<String> comboSemestre = new JComboBox<>();
    comboSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    comboSemestre.setPreferredSize(new Dimension(200, 45));

    comboSemestre.addItem("1er semestre");
    comboSemestre.addItem("2do semestre");
    comboSemestre.addItem("3er semestre");
    comboSemestre.addItem("4to semestre");
    comboSemestre.addItem("5to semestre");
    comboSemestre.addItem("6to semestre");
    comboSemestre.addItem("7mo semestre");
    comboSemestre.addItem("8vo semestre");
    comboSemestre.addItem("9no semestre");

    JLabel lblTurno = new JLabel("Turno");
    lblTurno.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JLabel lblCapacidad = new JLabel("Capacidad");
    lblCapacidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JComboBox<String> comboTurno = new JComboBox<>();
    comboTurno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    comboTurno.setPreferredSize(new Dimension(200, 45));

    if(turno.equals("MATUTINO")) {	
    	comboTurno.addItem(turno);
    	comboTurno.addItem("VESPERTINO");
    }
    else{comboTurno.addItem(turno);
    	comboTurno.addItem("MATUTINO");
    }
    
    

    JTextField txtCapacidad = new JTextField();
    txtCapacidad.setText(capacidad+"");
    txtCapacidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtCapacidad.setPreferredSize(new Dimension(200, 45));

    gbc.gridx = 0;
    gbc.gridy = 0;
    tarjeta.add(lblNombreGrupo, gbc);

    gbc.gridx = 1;
    tarjeta.add(lblSemestre, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    tarjeta.add(txtGrupo, gbc);

    gbc.gridx = 1;
    tarjeta.add(comboSemestre, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    tarjeta.add(lblTurno, gbc);

    gbc.gridx = 1;
    tarjeta.add(lblCapacidad, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    tarjeta.add(comboTurno, gbc);

    gbc.gridx = 1;
    tarjeta.add(txtCapacidad, gbc);

    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
    panelBotones.setBackground(Color.WHITE);

    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setFocusable(false);
    btnCancelar.setForeground(new Color(180, 0, 0));
    btnCancelar.setBackground(Color.WHITE);
    btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    btnCancelar.setBorder(new LineBorder(new Color(180, 0, 0), 2, true));
    btnCancelar.setPreferredSize(new Dimension(180, 45));

    btnCancelar.addActionListener(e ->
        app.cambiarVista(
            new GruposView(app),
            "Grupos",
            "Gestion integral de grupos en el sistema"
        )
    );

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setFocusable(false);
    btnGuardar.setForeground(Color.WHITE);
    btnGuardar.setBackground(new Color(14, 48, 170));
    btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    btnGuardar.setPreferredSize(new Dimension(180, 45));

    panelBotones.add(btnCancelar);
    panelBotones.add(btnGuardar);

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    tarjeta.add(panelBotones, gbc);

    panelCentroWrapper.add(tarjeta, BorderLayout.NORTH);

    contenedor.add(panelSuperior, BorderLayout.NORTH);
    contenedor.add(panelCentroWrapper, BorderLayout.CENTER);

    app.cambiarVista(
        contenedor,
        "Grupo",
        "Gestion integral de grupos en el sistema"
    );
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

    	JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(new Color(245, 247, 250));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(245, 247, 250));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(25, 40, 10, 40));

        JLabel subtitulo1 = new JLabel("Crear un grupo nuevo");
        subtitulo1.setForeground(azul_principal);
        subtitulo1.setFont(new Font("Times New Roman", Font.BOLD, 28));
        
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
        panelSuperior.add(subtitulo1, BorderLayout.WEST);

        JPanel panelCentroWrapper = new JPanel(new BorderLayout());
        panelCentroWrapper.setBackground(new Color(245, 247, 250));
        panelCentroWrapper.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));

        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setLayout(new GridBagLayout());
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220,220,220), 1, true),
            BorderFactory.createEmptyBorder(35, 35, 35, 35)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel lblNombreGrupo = new JLabel("Nombre del grupo");
        lblNombreGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel lblSemestre = new JLabel("Semestre");
        lblSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JTextField txtGrupo = new JTextField("1A");
        txtGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtGrupo.setPreferredSize(new Dimension(200, 45));

        JComboBox<String> comboSemestre = new JComboBox<>();
        comboSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        comboSemestre.setPreferredSize(new Dimension(200, 45));

        comboSemestre.addItem("1er semestre");
        comboSemestre.addItem("2do semestre");
        comboSemestre.addItem("3er semestre");
        comboSemestre.addItem("4to semestre");
        comboSemestre.addItem("5to semestre");
        comboSemestre.addItem("6to semestre");
        comboSemestre.addItem("7mo semestre");
        comboSemestre.addItem("8vo semestre");
        comboSemestre.addItem("9no semestre");

        JLabel lblDocente = new JLabel("Docente");
        lblDocente.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel lblCapacidad = new JLabel("Turno");
        lblCapacidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JComboBox<String> comboDocente = new JComboBox<>();
        comboDocente.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        comboDocente.setPreferredSize(new Dimension(200, 45));

        comboDocente.addItem("Juan Daniel Perez Olvera");
        comboDocente.addItem("Jose Luis Torres Pinzon");
        comboDocente.addItem("Ariel Camacho");
        comboDocente.addItem("Luis Fernando Padilla");
        comboDocente.addItem("Michell Alejandra Lopez Cabrera");
        comboDocente.addItem("Karina Meza Zepeda");
        
        JComboBox<String> comboTurno = new JComboBox<>();
        comboTurno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        comboTurno.setPreferredSize(new Dimension(200, 45));
        
        comboTurno.addItem("Matutino");
        comboTurno.addItem("Vespertino");
        
        JTextField txtCapacidad = new JTextField("40");
        txtCapacidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtCapacidad.setPreferredSize(new Dimension(200, 45));

        gbc.gridx = 0;
        gbc.gridy = 0;
        tarjeta.add(lblNombreGrupo, gbc);

        gbc.gridx = 1;
        tarjeta.add(lblSemestre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        tarjeta.add(txtGrupo, gbc);

        gbc.gridx = 1;
        tarjeta.add(comboSemestre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        tarjeta.add(lblDocente, gbc);

        gbc.gridx = 1;
        tarjeta.add(lblCapacidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        tarjeta.add(comboDocente, gbc);

        gbc.gridx = 1;
        tarjeta.add(comboTurno, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotones.setBackground(Color.WHITE);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFocusable(false);
        btnCancelar.setForeground(new Color(180, 0, 0));
        btnCancelar.setBackground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnCancelar.setBorder(new LineBorder(new Color(180, 0, 0), 2, true));
        btnCancelar.setPreferredSize(new Dimension(180, 45));

        btnCancelar.addActionListener(e ->
            app.cambiarVista(
                new GruposView(app),
                "Grupos",
                "Gestion integral de grupos en el sistema"
            )
        );

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(14, 48, 170));
        btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnGuardar.setPreferredSize(new Dimension(180, 45));

        btnGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                LineBorder bordeRojo = new LineBorder(Color.RED, 2, true);
                LineBorder bordeNormal = new LineBorder(new Color(180,180,180), 1, true);

                txtGrupo.setBorder(bordeNormal);
                txtCapacidad.setBorder(bordeNormal);

                boolean valido = true;

                if(txtGrupo.getText().trim().isEmpty()) {
                    txtGrupo.setBorder(bordeRojo);
                    valido = false;
                }

                if(txtCapacidad.getText().trim().isEmpty() || !txtCapacidad.getText().matches("\\d+")) {
                    txtCapacidad.setBorder(bordeRojo);
                    valido = false;
                }

                if(valido) {
                    System.out.println("Formulario válido");
                }
            }
        });

        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tarjeta.add(panelBotones, gbc);

        panelCentroWrapper.add(tarjeta, BorderLayout.NORTH);

        contenedor.add(panelSuperior, BorderLayout.NORTH);
        contenedor.add(panelCentroWrapper, BorderLayout.CENTER);

        app.cambiarVista(
            contenedor,
            "Grupo",
            "Gestion integral de grupos en el sistema"
        );
    }
    
    private void setTodo(int fila) {
    	String nom = tabla.getValueAt(fila, 0).toString();
	    controller.cargarGrupo(nom);
	    idGrupo=controller.getIdGrupo();
	    nombre=controller.getNombre();
	    turno=controller.getTurno();
	    capacidad=controller.getCapacidad();
	    docentes=controller.getDocentes();
    }
}
