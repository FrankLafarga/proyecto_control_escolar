package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import constructor_ventanas.App;
import controllers.AlumnosController;

public class AlumnosView extends JPanel {
	private AppView app;
	private JTable tabla;
	Color azul_principal = new Color(14, 48, 170);

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

    	JPanel panel_centro = new JPanel();
        panel_centro.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_11 = new JPanel();
        panel_11.setBackground(new Color(28,57,152));
        panel_centro.add(panel_11, BorderLayout.NORTH);
        
        JLabel lblNewLabel_6 = new JLabel("María  Ines Gonzalez Sanchez");
        lblNewLabel_6.setForeground(new Color(255, 255, 255));
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        
        JLabel lblNewLabel_7 = new JLabel("Matrícula: A2024001");
        lblNewLabel_7.setForeground(new Color(255, 255, 255));
        lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        
        JLabel lblNewLabel_7_1 = new JLabel("3°Semestre");
        lblNewLabel_7_1.setForeground(Color.WHITE);
        lblNewLabel_7_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        
        JLabel lblNewLabel_7_1_1 = new JLabel("Grupo 3°A");
        lblNewLabel_7_1_1.setForeground(Color.WHITE);
        lblNewLabel_7_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        GroupLayout gl_panel_11 = new GroupLayout(panel_11);
        gl_panel_11.setHorizontalGroup(
        	gl_panel_11.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_11.createSequentialGroup()
        			.addGap(25)
        			.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 649, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel_11.createSequentialGroup()
        					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblNewLabel_7_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblNewLabel_7_1_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(225, Short.MAX_VALUE))
        );
        gl_panel_11.setVerticalGroup(
        	gl_panel_11.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_11.createSequentialGroup()
        			.addGap(20)
        			.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(gl_panel_11.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_7)
        				.addComponent(lblNewLabel_7_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_7_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(29, Short.MAX_VALUE))
        );
        panel_11.setLayout(gl_panel_11);
        
        JPanel panel_16 = new JPanel();
        panel_centro.add(panel_16, BorderLayout.CENTER);
        panel_16.setLayout(new GridLayout(2, 0, 0, 0));
        
        JPanel panel_17 = new JPanel();
        panel_16.add(panel_17);
        panel_17.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_19 = new JPanel();
        panel_19.setPreferredSize(new Dimension(10, 50));
        panel_19.setOpaque(false);
        panel_17.add(panel_19, BorderLayout.NORTH);
        
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
            app.cambiarVista(new AlumnosView(app),
            "Alumnos",
            "Gestion integral de alumnos en el sistema")
        );	
        
        panel_19.setLayout(new BorderLayout(0, 0));
        
        panel_19.add(volver, BorderLayout.EAST);
        
        JPanel panel_20 = new JPanel();
        panel_20.setPreferredSize(new Dimension(70, 10));
        panel_20.setOpaque(false);
        panel_17.add(panel_20, BorderLayout.WEST);
        
        JPanel panel_21 = new JPanel();
        panel_21.setOpaque(false);
        panel_17.add(panel_21, BorderLayout.SOUTH);
        panel_21.setLayout(new BorderLayout(0, 0));   
        
        JPanel panel_27 = new JPanel();
        panel_21.add(panel_27, BorderLayout.CENTER);
        panel_27.setLayout(new BorderLayout(0, 0));
        JButton btnPDF = new JButton(
        		"<html>" +
        				"<span style='color:black; font-family:Segoe UI; font-size:16px;'> Generar Credencial </span>" +
        				"</html>"
        		);
        btnPDF.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnPDF.setText("<html>" +
        				"<span style='color:blue; font-family:Segoe UI; font-size:16px;'> Generar Credencial </span>" +
        				"</html>");
        	    btnPDF.setBorder(new LineBorder(azul_principal, 2));

        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnPDF.setText("<html>" +
        				"<span style='color:black; font-family:Segoe UI; font-size:16px;'>Generar Credencial</span>" +
        				"</html>");
        	    btnPDF.setBorder(new LineBorder(new Color(0, 0, 0), 2));

        	}
        });
        btnPDF.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnPDF.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        btnPDF.setFocusable(false);
        btnPDF.setContentAreaFilled(false);
        panel_27.add(btnPDF, BorderLayout.EAST);
        
        JPanel panel_28 = new JPanel();
        panel_21.add(panel_28, BorderLayout.EAST);
        
        JPanel panel_29 = new JPanel();
        panel_21.add(panel_29, BorderLayout.NORTH);
        
        JPanel panel_22 = new JPanel();
        panel_22.setPreferredSize(new Dimension(70, 10));
        panel_22.setOpaque(false);
        panel_17.add(panel_22, BorderLayout.EAST);
        
        JPanel panel_23 = new JPanel();
        panel_17.add(panel_23, BorderLayout.CENTER);
        panel_23.setLayout(new GridLayout(0, 3, 50, 20));
        
        JPanel panel_24 = new JPanel();
        panel_24.setBorder(new LineBorder(new Color(240, 240, 240), 2, true));
        panel_24.setBackground(new Color(192, 192, 192));
        panel_23.add(panel_24);
        
        JLabel lblNewLabel_8 = new JLabel("Promedio general");
        lblNewLabel_8.setPreferredSize(new Dimension(83, 30));
        lblNewLabel_8.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblNewLabel_8.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_8.setForeground(new Color(0, 0, 160));
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        JLabel lblNewLabel_9 = new JLabel("");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_9.setIcon(new ImageIcon(App.class.getResource("/resources/medalla.png")));
        
        JLabel lblNewLabel_10 = new JLabel("9.82");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_10.setForeground(new Color(0, 255, 64));
        lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 40));
        panel_24.setLayout(new BorderLayout(0, 0));
        panel_24.add(lblNewLabel_9, BorderLayout.CENTER);
        panel_24.add(lblNewLabel_8, BorderLayout.NORTH);
        panel_24.add(lblNewLabel_10, BorderLayout.SOUTH);
        
        JPanel panel_25 = new JPanel();
        panel_25.setBorder(new LineBorder(new Color(240, 240, 240), 2, true));
        panel_25.setBackground(Color.LIGHT_GRAY);
        panel_23.add(panel_25);
        panel_25.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_8_1 = new JLabel("Carrera actual");
        lblNewLabel_8_1.setPreferredSize(new Dimension(69, 30));
        lblNewLabel_8_1.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblNewLabel_8_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8_1.setForeground(new Color(0, 0, 160));
        lblNewLabel_8_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel_25.add(lblNewLabel_8_1, BorderLayout.NORTH);
        
        JLabel lblNewLabel_9_1 = new JLabel("");
        lblNewLabel_9_1.setIcon(new ImageIcon(App.class.getResource("/resources/mochila.png")));
        lblNewLabel_9_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_25.add(lblNewLabel_9_1, BorderLayout.CENTER);
        
        JTextArea txtrIngenieriaEnDesarrollo = new JTextArea();
        txtrIngenieriaEnDesarrollo.setMargin(new Insets(4, 10, 4, 10));
        txtrIngenieriaEnDesarrollo.setForeground(new Color(0, 0, 160));
        txtrIngenieriaEnDesarrollo.setText("ingenieria en desarrollo de software");
        txtrIngenieriaEnDesarrollo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtrIngenieriaEnDesarrollo.setLineWrap(true);
        txtrIngenieriaEnDesarrollo.setWrapStyleWord(true);
        txtrIngenieriaEnDesarrollo.setEditable(false);
        txtrIngenieriaEnDesarrollo.setOpaque(false);
        panel_25.add(txtrIngenieriaEnDesarrollo, BorderLayout.SOUTH);
        
        JPanel panel_26 = new JPanel();
        panel_26.setBorder(new LineBorder(new Color(240, 240, 240), 2, true));
        panel_26.setBackground(Color.LIGHT_GRAY);
        panel_23.add(panel_26);
        panel_26.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_11 = new JLabel("");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setIcon(new ImageIcon(App.class.getResource("/resources/usuario_logo.png")));
        panel_26.add(lblNewLabel_11);
        
        JPanel panel_sur = new JPanel();
        panel_16.add(panel_sur);
        panel_sur.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_18 = new JPanel();
        panel_sur.add(panel_18, BorderLayout.NORTH);
        panel_18.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_12 = new JLabel("    Información personal");
        lblNewLabel_12.setForeground(azul_principal);
        lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panel_18.add(lblNewLabel_12, BorderLayout.WEST);
        
        JPanel panel_30 = new JPanel();
        panel_30.setBackground(new Color(192, 192, 192));
        panel_30.setPreferredSize(new Dimension(10, 1));
        panel_18.add(panel_30, BorderLayout.SOUTH);
        
        JPanel panel_31 = new JPanel();
        panel_sur.add(panel_31, BorderLayout.CENTER);
        panel_31.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_32 = new JPanel();
        panel_32.setOpaque(false);
        panel_31.add(panel_32, BorderLayout.WEST);
        
        JPanel panel_33 = new JPanel();
        panel_33.setOpaque(false);
        panel_31.add(panel_33, BorderLayout.EAST);
        
        JPanel panel_34 = new JPanel();
        panel_34.setOpaque(false);
        panel_31.add(panel_34, BorderLayout.SOUTH);
        
        JPanel panel_35 = new JPanel();
        panel_35.setOpaque(false);
        panel_31.add(panel_35, BorderLayout.CENTER);
        panel_35.setLayout(new GridLayout(1, 3, 0, 0));
        
        JPanel panel_36 = new JPanel();
        panel_36.setOpaque(false);
        panel_35.add(panel_36);
        panel_36.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_39 = new JPanel();
        panel_39.setOpaque(false);
        panel_36.add(panel_39);
        
        JLabel lblNewLabel_13 = new JLabel("Fecha de nacimiento:");
        lblNewLabel_13.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_13.setIconTextGap(10);
        lblNewLabel_13.setIcon(new ImageIcon(App.class.getResource("/resources/calendario.png")));
        lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblNewLabel_13.setForeground(new Color(128, 128, 128));
        panel_36.add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("    02 de Febrero de 2006");
        lblNewLabel_14.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_14.setVerticalTextPosition(SwingConstants.TOP);
        lblNewLabel_14.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panel_36.add(lblNewLabel_14);
        
        JLabel lblNewLabel_16 = new JLabel("Correo electrónico");
        lblNewLabel_16.setForeground(new Color(128, 128, 128));
        lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblNewLabel_16.setIcon(new ImageIcon(App.class.getResource("/resources/correo.png")));
        panel_36.add(lblNewLabel_16);
        
        JLabel lblNewLabel_15 = new JLabel("    mariag_24@alu.educadex.mx");
        lblNewLabel_15.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblNewLabel_15.setForeground(new Color(0, 0, 160));
        panel_36.add(lblNewLabel_15);
        
        JPanel panel_37 = new JPanel();
        panel_37.setOpaque(false);
        panel_35.add(panel_37);
        panel_37.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_39_1 = new JPanel();
        panel_39_1.setOpaque(false);
        panel_37.add(panel_39_1);
        
        JLabel lblNewLabel_13_1 = new JLabel("Género:");
        lblNewLabel_13_1.setIconTextGap(10);
        lblNewLabel_13_1.setIcon(new ImageIcon(App.class.getResource("/resources/usuario16.png")));
        lblNewLabel_13_1.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblNewLabel_13_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_13_1.setForeground(Color.GRAY);
        lblNewLabel_13_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panel_37.add(lblNewLabel_13_1);
        
        JLabel lblNewLabel_14_1 = new JLabel("    Femenino");
        lblNewLabel_14_1.setVerticalTextPosition(SwingConstants.TOP);
        lblNewLabel_14_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_14_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panel_37.add(lblNewLabel_14_1);
        
        JLabel lblNewLabel_16_1 = new JLabel("Teléfono");
        lblNewLabel_16_1.setIcon(new ImageIcon(App.class.getResource("/resources/telefono.png")));
        lblNewLabel_16_1.setForeground(Color.GRAY);
        lblNewLabel_16_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panel_37.add(lblNewLabel_16_1);
        
        JLabel lblNewLabel_15_1 = new JLabel("    6122182756");
        lblNewLabel_15_1.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_15_1.setVerticalTextPosition(SwingConstants.TOP);
        lblNewLabel_15_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_15_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel_37.add(lblNewLabel_15_1);
        
        JPanel panel_38 = new JPanel();
        panel_35.add(panel_38);
        JButton pdf = new JButton(
        		"<html>" +
        	    "<span style='color:black; font-family:Segoe UI; font-size:16px;'>Imprimir </span>" +
        	    "<span style='color:red; font-family:Segoe UI; font-size:16px;'><b>PDF</b></span>" +
        	    "</html>");
        pdf.setPreferredSize(new Dimension(180, 40));
        pdf.setFocusable(false);
        pdf.setContentAreaFilled(false);
        GroupLayout gl_panel_38 = new GroupLayout(panel_38);
        gl_panel_38.setHorizontalGroup(
        	gl_panel_38.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panel_38.createSequentialGroup()
        			.addContainerGap(58, Short.MAX_VALUE)
        			.addComponent(pdf, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
        );
        gl_panel_38.setVerticalGroup(
        	gl_panel_38.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_38.createSequentialGroup()
        			.addGap(80)
        			.addComponent(pdf, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(70, Short.MAX_VALUE))
        );
        panel_38.setLayout(gl_panel_38);       
      

        app.cambiarVista(panel_centro, "Alumno", "Detalle del alumno seleccionado");
    }

    public void editarAlumno(int fila) {

    	JPanel contenedor = new JPanel(new BorderLayout());
	    contenedor.setBackground(Color.WHITE);
	
	    JPanel panelSuperior = new JPanel(new BorderLayout());
	    panelSuperior.setBackground(Color.WHITE);
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
            app.cambiarVista(new AlumnosView(app),
            "Alumnos",
            "Gestion integral de alumnos en el sistema")
        );
	
	    panelSuperior.add(volver, BorderLayout.EAST);
	
	    JPanel panelCentro = new JPanel();
	    panelCentro.setBackground(Color.WHITE);
	    panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	    panelCentro.setLayout(new GridLayout(10, 3, 20, 15));
	
	    JLabel subtitulo1 = new JLabel("Información académica");
	    subtitulo1.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    subtitulo1.setForeground(azul_principal);
	    panelCentro.add(subtitulo1);
	    panelCentro.add(new JLabel(""));
	    panelCentro.add(new JLabel(""));
	
	    JLabel lblMatrícula = new JLabel("Matrícula");
	    lblMatrícula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblSemestre = new JLabel("Semestre");
	    lblSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblGrupo = new JLabel("Grupo");
  	    lblGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblMatrícula);
	    panelCentro.add(lblSemestre);
	    panelCentro.add(lblGrupo);
	
	    JTextField txtClave = new JTextField();
	    txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtClave.setText("D11");
	
	    JTextField txtGrado = new JTextField();
	    txtGrado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtGrado.setText("Licenciatura");
	
	    JTextField txtGrupo = new JTextField();
	    txtGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtGrupo.setText("1A");
	
	    panelCentro.add(txtClave);
	    panelCentro.add(txtGrado);
	    panelCentro.add(txtGrupo);
	
	    JLabel lblArea = new JLabel("Promedio general");
	    lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    
	    JLabel lblEstatus = new JLabel("Estatus");
	    lblEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	
	    panelCentro.add(lblArea);
	    panelCentro.add(lblEstatus);
	    panelCentro.add(new JLabel(""));
	
	    JTextField txtEstatus = new JTextField();
	    txtEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtEstatus.setText("Activo");
	
	    JTextField txtArea = new JTextField();
	    txtArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtArea.setText("Matematicas");
	
	    panelCentro.add(txtEstatus);
	    panelCentro.add(txtArea);
	    panelCentro.add(new JLabel(""));
	
	    JLabel subtitulo2 = new JLabel("Información personal");
	    subtitulo2.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    subtitulo2.setForeground(azul_principal);
	
	    panelCentro.add(subtitulo2);
	    panelCentro.add(new JLabel(""));
	    panelCentro.add(new JLabel(""));
	
	    JLabel lblNombre = new JLabel("Nombre(s)");
	    lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblPaterno = new JLabel("Apellido paterno");
	    lblPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblMaterno = new JLabel("Apellido materno");
	    lblMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblNombre);
	    panelCentro.add(lblPaterno);
	    panelCentro.add(lblMaterno);
	
	    JTextField txtNombre = new JTextField();
	    txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtNombre.setText("Juan");
	
	    JTextField txtPaterno = new JTextField();
	    txtPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtPaterno.setText("Perez");
	
	    JTextField txtMaterno = new JTextField();
	    txtMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtMaterno.setText("Silva");
	
	    panelCentro.add(txtNombre);
	    panelCentro.add(txtPaterno);
	    panelCentro.add(txtMaterno);
	
	    JLabel lblEmail = new JLabel("Email");
	    lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblTelefono = new JLabel("Teléfono");
	    lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblFecha = new JLabel("Fecha de nacimiento");
	    lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblEmail);
	    panelCentro.add(lblTelefono);
	    panelCentro.add(lblFecha);
	
	    JTextField txtEmail = new JTextField();
	    txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtEmail.setText("juanp_24@educadex.com");
	
	    JTextField txtTelefono = new JTextField();
	    txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtTelefono.setText("6124568412");
	
	    JTextField txtFecha = new JTextField();
	    txtFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtFecha.setText("10/02/1998");
	
	    panelCentro.add(txtEmail);
	    panelCentro.add(txtTelefono);
	    panelCentro.add(txtFecha);
	
	    JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panelSur.setBackground(Color.WHITE);
	
	    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
	    panelBotones.setBackground(Color.WHITE);
	
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setFocusable(false);
	    btnCancelar.setForeground(new Color(180, 0, 0));
	    btnCancelar.setBackground(Color.WHITE);
	    btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    btnCancelar.setBorder(new LineBorder(new Color(180, 0, 0), 2, true));
	    btnCancelar.setPreferredSize(new Dimension(180, 45));
	
	    btnCancelar.addActionListener(e ->
	    app.cambiarVista(new AlumnosView(app),
	            "Alumnos",
	            "Gestion integral de alumnos en el sistema")
	    );
	
	    JButton btnGuardar = new JButton("Confirmar");
	    btnGuardar.setFocusable(false);
	    btnGuardar.setForeground(Color.WHITE);
	    btnGuardar.setBackground(new Color(14, 48, 170));
	    btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    btnGuardar.setPreferredSize(new Dimension(180, 45));
	
	    btnGuardar.addActionListener(new ActionListener() {
	
	        public void actionPerformed(ActionEvent e) {
	
	            LineBorder bordeRojo = new LineBorder(Color.RED, 2, true);
	            LineBorder bordeNormal = new LineBorder(new Color(180,180,180), 1, true);
	
	            txtClave.setBorder(bordeNormal);
	            txtGrupo.setBorder(bordeNormal);
	            txtGrado.setBorder(bordeNormal);
	            txtEstatus.setBorder(bordeNormal);
	            txtArea.setBorder(bordeNormal);
	            txtNombre.setBorder(bordeNormal);
	            txtPaterno.setBorder(bordeNormal);
	            txtMaterno.setBorder(bordeNormal);
	            txtEmail.setBorder(bordeNormal);
	            txtTelefono.setBorder(bordeNormal);
	            txtFecha.setBorder(bordeNormal);
	
	            boolean valido = true;
	
	            if(txtClave.getText().trim().isEmpty()) {
	                txtClave.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtGrado.getText().trim().isEmpty() || txtGrado.getText().matches(".*\\d.*")) {
	                txtGrado.setBorder(bordeRojo);
	                valido = false;
	            }
	            
	            if(txtGrupo.getText().trim().isEmpty()) {
	            	txtGrupo.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtEstatus.getText().trim().isEmpty() || txtEstatus.getText().matches(".*\\d.*")) {
	                txtEstatus.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtArea.getText().trim().isEmpty() || txtArea.getText().matches(".*\\d.*")) {
	                txtArea.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtNombre.getText().trim().isEmpty() || txtNombre.getText().matches(".*\\d.*")) {
	                txtNombre.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtPaterno.getText().trim().isEmpty() || txtPaterno.getText().matches(".*\\d.*")) {
	                txtPaterno.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtMaterno.getText().trim().isEmpty() || txtMaterno.getText().matches(".*\\d.*")) {
	                txtMaterno.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtEmail.getText().trim().isEmpty() || !txtEmail.getText().contains("@")) {
	                txtEmail.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtTelefono.getText().trim().isEmpty() || !txtTelefono.getText().matches("\\d+")) {
	                txtTelefono.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtFecha.getText().trim().isEmpty()) {
	                txtFecha.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(valido) {
	                System.out.println("Formulario válido");
	            }
	        }
	    });
	
	    panelBotones.add(btnCancelar);
	    panelBotones.add(btnGuardar);
	
	    panelSur.add(panelBotones);
	
	    contenedor.add(panelSuperior, BorderLayout.NORTH);
	    contenedor.add(panelCentro, BorderLayout.CENTER);
	    contenedor.add(panelSur, BorderLayout.SOUTH);
	
	    
        app.cambiarVista(contenedor, "Alumno", "Editar alumno seleccionado");
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

    	JPanel contenedor = new JPanel(new BorderLayout());
	    contenedor.setBackground(Color.WHITE);
	
	    JPanel panelSuperior = new JPanel(new BorderLayout());
	    panelSuperior.setBackground(Color.WHITE);
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
            app.cambiarVista(new AlumnosView(app),
            "Alumnos",
            "Gestion integral de alumnos en el sistema")
        );
	
	    panelSuperior.add(volver, BorderLayout.EAST);
	
	    JPanel panelCentro = new JPanel();
	    panelCentro.setBackground(Color.WHITE);
	    panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	    panelCentro.setLayout(new GridLayout(10, 3, 20, 15));
	
	    JLabel subtitulo1 = new JLabel("Información académica");
	    subtitulo1.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    subtitulo1.setForeground(azul_principal);
	    panelCentro.add(subtitulo1);
	    panelCentro.add(new JLabel(""));
	    panelCentro.add(new JLabel(""));
	
	    JLabel lblMatrícula = new JLabel("Matrícula");
	    lblMatrícula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblSemestre = new JLabel("Semestre");
	    lblSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblGrupo = new JLabel("Grupo");
  	    lblGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblMatrícula);
	    panelCentro.add(lblSemestre);
	    panelCentro.add(lblGrupo);
	
	    JTextField txtClave = new JTextField();
	    txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtGrado = new JTextField();
	    txtGrado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtGrupo = new JTextField();
	    txtGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(txtClave);
	    panelCentro.add(txtGrado);
	    panelCentro.add(txtGrupo);
	
	    JLabel lblArea = new JLabel("Promedio general");
	    lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    
	    JLabel lblEstatus = new JLabel("Estatus");
	    lblEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	
	    panelCentro.add(lblArea);
	    panelCentro.add(lblEstatus);
	    panelCentro.add(new JLabel(""));
	
	    JTextField txtEstatus = new JTextField();
	    txtEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtArea = new JTextField();
	    txtArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(txtEstatus);
	    panelCentro.add(txtArea);
	    panelCentro.add(new JLabel(""));
	
	    JLabel subtitulo2 = new JLabel("Información personal");
	    subtitulo2.setFont(new Font("Segoe UI", Font.BOLD, 20));
	    subtitulo2.setForeground(azul_principal);
	
	    panelCentro.add(subtitulo2);
	    panelCentro.add(new JLabel(""));
	    panelCentro.add(new JLabel(""));
	
	    JLabel lblNombre = new JLabel("Nombre(s)");
	    lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblPaterno = new JLabel("Apellido paterno");
	    lblPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblMaterno = new JLabel("Apellido materno");
	    lblMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblNombre);
	    panelCentro.add(lblPaterno);
	    panelCentro.add(lblMaterno);
	
	    JTextField txtNombre = new JTextField();
	    txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtPaterno = new JTextField();
	    txtPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtMaterno = new JTextField();
	    txtMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(txtNombre);
	    panelCentro.add(txtPaterno);
	    panelCentro.add(txtMaterno);
	
	    JLabel lblEmail = new JLabel("Email");
	    lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblTelefono = new JLabel("Teléfono");
	    lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblFecha = new JLabel("Fecha de nacimiento");
	    lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblEmail);
	    panelCentro.add(lblTelefono);
	    panelCentro.add(lblFecha);
	
	    JTextField txtEmail = new JTextField();
	    txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtTelefono = new JTextField();
	    txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtFecha = new JTextField();
	    txtFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(txtEmail);
	    panelCentro.add(txtTelefono);
	    panelCentro.add(txtFecha);
	
	    JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panelSur.setBackground(Color.WHITE);
	
	    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
	    panelBotones.setBackground(Color.WHITE);
	
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setFocusable(false);
	    btnCancelar.setForeground(new Color(180, 0, 0));
	    btnCancelar.setBackground(Color.WHITE);
	    btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    btnCancelar.setBorder(new LineBorder(new Color(180, 0, 0), 2, true));
	    btnCancelar.setPreferredSize(new Dimension(180, 45));
	
	    btnCancelar.addActionListener(e ->
	    app.cambiarVista(new AlumnosView(app),
	            "Alumnos",
	            "Gestion integral de alumnos en el sistema")
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
	
	            txtClave.setBorder(bordeNormal);
	            txtGrado.setBorder(bordeNormal);
	            txtEstatus.setBorder(bordeNormal);
	            txtArea.setBorder(bordeNormal);
	            txtNombre.setBorder(bordeNormal);
	            txtPaterno.setBorder(bordeNormal);
	            txtMaterno.setBorder(bordeNormal);
	            txtEmail.setBorder(bordeNormal);
	            txtTelefono.setBorder(bordeNormal);
	            txtFecha.setBorder(bordeNormal);
	
	            boolean valido = true;
	
	            if(txtClave.getText().trim().isEmpty()) {
	                txtClave.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtGrado.getText().trim().isEmpty() || txtGrado.getText().matches(".*\\d.*")) {
	                txtGrado.setBorder(bordeRojo);
	                valido = false;
	            }
	            
	            if(txtGrupo.getText().trim().isEmpty() || txtGrupo.getText().matches(".*\\d.*")) {
	            	txtGrupo.setBorder(bordeRojo);
	                valido = false;
	            }
	            
	            if(txtEstatus.getText().trim().isEmpty() || txtEstatus.getText().matches(".*\\d.*")) {
	                txtEstatus.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtArea.getText().trim().isEmpty() || txtArea.getText().matches(".*\\d.*")) {
	                txtArea.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtNombre.getText().trim().isEmpty() || txtNombre.getText().matches(".*\\d.*")) {
	                txtNombre.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtPaterno.getText().trim().isEmpty() || txtPaterno.getText().matches(".*\\d.*")) {
	                txtPaterno.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtMaterno.getText().trim().isEmpty() || txtMaterno.getText().matches(".*\\d.*")) {
	                txtMaterno.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtEmail.getText().trim().isEmpty() || !txtEmail.getText().contains("@")) {
	                txtEmail.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtTelefono.getText().trim().isEmpty() || !txtTelefono.getText().matches("\\d+")) {
	                txtTelefono.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(txtFecha.getText().trim().isEmpty()) {
	                txtFecha.setBorder(bordeRojo);
	                valido = false;
	            }
	
	            if(valido) {
	                System.out.println("Formulario válido");
	            }
	        }
	    });
	
	    panelBotones.add(btnCancelar);
	    panelBotones.add(btnGuardar);
	
	    panelSur.add(panelBotones);
	
	    contenedor.add(panelSuperior, BorderLayout.NORTH);
	    contenedor.add(panelCentro, BorderLayout.CENTER);
	    contenedor.add(panelSur, BorderLayout.SOUTH);
	
	    
        app.cambiarVista(contenedor, "Alumno", "Editar alumno seleccionado");
    }
}