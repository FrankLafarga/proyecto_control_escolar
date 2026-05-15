package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import constructor_ventanas.App;
import controllers.AsignaturasController;

public class AsignaturasView extends JPanel {
	private AppView app;
	private JTable tabla;
	 Color azul_principal = new Color(14, 48, 170);

	 
	 private String nombre;
	 private String clave;
	 private int semestre;
	 private int creditos;
	 private String grupo;
	 private String docente;
	 
	 private AsignaturasController controller=new AsignaturasController();
	 
    public AsignaturasView(AppView app) {
		this.app = app;
	    setLayout(new BorderLayout());
	    setBackground(Color.WHITE);
	
	    JTextField txtBusqueda = new JTextField("Buscar asignatura...");
	    txtBusqueda.setForeground(Color.GRAY);
	    txtBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	
	    JPanel panel_16 = new JPanel(new BorderLayout());
	    panel_16.setOpaque(false);
	    panel_16.setPreferredSize(new Dimension(10, 50));
	    add(panel_16, BorderLayout.NORTH);
	
	    JPanel panel_17 = new JPanel(new BorderLayout());
	    panel_17.setOpaque(false);
	    panel_16.add(panel_17, BorderLayout.CENTER);
	
	    JButton btnAgregar = new JButton("+ Agregar asignatura");
	    btnAgregar.setPreferredSize(new Dimension(250, 45));
	    btnAgregar.setBorder(new LineBorder(Color.WHITE, 1, true));
	    btnAgregar.setFocusable(false);
	    btnAgregar.setForeground(Color.WHITE);
	    btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	    btnAgregar.setBackground(new Color(14, 48, 170));
	    btnAgregar.addActionListener(e ->{
	    	agregarAsignatura();
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
	
	    modelo.addColumn("Clave");
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Semestre");
	    modelo.addColumn("Créditos");
	    modelo.addColumn("Grupo");
	    modelo.addColumn("Docente");
	    modelo.addColumn("Acciones");
	
	    controller.cargarTabla(modelo);
	
	    for(int i=0;i<modelo.getRowCount();i++){
	        modelo.setValueAt("", i, 6);
	    }
	
	    tabla = new JTable(modelo);
	    tabla.getColumn("Acciones").setCellRenderer(new PanelBotones());
	    configurarTabla(tabla);
	
	    javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
	            new javax.swing.table.TableRowSorter<>(modelo);
	
	    tabla.setRowSorter(sorter);
	
	    txtBusqueda.addMouseListener(new MouseAdapter() {
	
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	
	    		if(txtBusqueda.getText().equals("Buscar asignatura...")) {
	    			txtBusqueda.setText("");
	    			txtBusqueda.setForeground(Color.BLACK);
	    		}
	    	}
	    });
	
	    txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
	
	    	@Override
	    	public void focusLost(java.awt.event.FocusEvent e) {
	
	    		if(txtBusqueda.getText().trim().isEmpty()) {
	    			txtBusqueda.setText("Buscar asignatura...");
	    			txtBusqueda.setForeground(Color.GRAY);
	    		}
	    	}
	    });
	
	    txtBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
	
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				buscar();
			}
	
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				buscar();
			}
	
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				buscar();
			}
	
			private void buscar() {
	
				String texto = txtBusqueda.getText();
	
				if(texto.equals("Buscar asignatura...")) {
					sorter.setRowFilter(null);
				}
				else if(texto.trim().isEmpty()) {
					sorter.setRowFilter(null);
				}
				else {
					sorter.setRowFilter(
						javax.swing.RowFilter.regexFilter("(?i)" + texto)
					);
				}
			}
		});
	
	    JScrollPane scroll = new JScrollPane(tabla);
	    panel_tabla.add(scroll, BorderLayout.CENTER);
	    tabla.setFillsViewportHeight(true);
	    scroll.getViewport().setBackground(Color.WHITE);
	
	    SwingUtilities.invokeLater(() -> ajustarColumnas(tabla, scroll, new int[]{10,20,10,10,15,20,15}));
	    
	    tabla.getColumn("Acciones").setCellEditor(
	    	    new PanelBotonesEditor(tabla, new AccionesTabla() {
	
	    	        @Override
	    	        public void ver(int fila) {
	    	        	setTodo(fila);       	        	
	    	        	verAsignatura(fila);
	    	            
	    	        }
	    	        @Override
	    	        public void editar(int fila) {
	    	        	setTodo(fila);       	        	
	    	            editarAsignatura(fila);
	    	        }
	    	        @Override
	    	        public void eliminar(int fila) {
	    	        	eliminarAsignatura(fila);
	    	        }
	    	    })
	    	);
    }
    
    public void verAsignatura(int fila) {

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
            app.cambiarVista(new AsignaturasView(app),
            "Asignaturas",
            "Gestion integral de asignaturas en el sistema")
        );

        panelSuperior.add(volver, BorderLayout.EAST);

        JPanel centroWrapper = new JPanel(new BorderLayout());
        centroWrapper.setBackground(Color.WHITE);
        centroWrapper.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200,200,200), 2, true),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        JLabel nombreLabel = new JLabel(nombre);
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        nombreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nombreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel semestreLabel = new JLabel("Semestre: " + semestre);
        JLabel claveLabel = new JLabel("Clave: " + clave);
        JLabel creditosLabel = new JLabel("Créditos: " + creditos);
        JLabel grupoLabel = new JLabel("Grupo: " + grupo);
        JLabel docenteLabel = new JLabel("Docente: " + docente);

        JLabel[] datos = {
            semestreLabel,
            claveLabel,
            creditosLabel,
            grupoLabel,
            docenteLabel
        };

        for(JLabel l : datos){
            l.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        
        JPanel filaNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaNombre.setBackground(Color.WHITE);

        filaNombre.add(nombreLabel);

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

        app.cambiarVista(contenedor,"Asignatura","Detalle de la asignatura seleccionada");
    }

    public void editarAsignatura(int fila) {

        JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setBackground(new Color(245, 247, 250));

    JPanel panelSuperior = new JPanel(new BorderLayout());
    panelSuperior.setBackground(new Color(245, 247, 250));
    panelSuperior.setBorder(BorderFactory.createEmptyBorder(25, 40, 10, 40));

    JLabel subtitulo1 = new JLabel("Editar Asignatura seleccionada");
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
        app.cambiarVista(new AsignaturasView(app),
        "Asignaturas",
        "Gestion integral de asignaturas en el sistema")
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

    JLabel lblNombreGrupo = new JLabel("Clave");
    lblNombreGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JLabel lblAsignatura = new JLabel("Nombre de la asignatura");
    lblAsignatura.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JTextField txtGrupo = new JTextField(clave);
    txtGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtGrupo.setPreferredSize(new Dimension(200, 45));
    
    JTextField txtAsignatura = new JTextField(nombre);
    txtAsignatura.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtAsignatura.setPreferredSize(new Dimension(200, 45));

   

    JLabel lblSemestre = new JLabel("Semestre");
    lblSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JLabel lblCapacidad = new JLabel("Créditos");
    lblCapacidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));

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

    JTextField txtCreditos = new JTextField(creditos+"");
    txtCreditos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtCreditos.setPreferredSize(new Dimension(200, 45));

    gbc.gridx = 0;
    gbc.gridy = 0;
    tarjeta.add(lblNombreGrupo, gbc);

    gbc.gridx = 1;
    tarjeta.add(lblAsignatura, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    tarjeta.add(txtGrupo, gbc);

    gbc.gridx = 1;
    tarjeta.add(txtAsignatura, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    tarjeta.add(lblSemestre, gbc);

    gbc.gridx = 1;
    tarjeta.add(lblCapacidad, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    tarjeta.add(comboSemestre, gbc);

    gbc.gridx = 1;
    tarjeta.add(txtCreditos, gbc);

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
            new AsignaturasView(app),
            "Asignaturas",
            "Gestion integral de asignaturas en el sistema"
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
        "Asignaturas",
        "Gestion integral de asignaturas en el sistema"
    );
    }

    public void eliminarAsignatura(int fila) {

    	String nombreAsignatura = tabla.getValueAt(fila, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar la asignatura " + nombreAsignatura + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
        	JOptionPane.showMessageDialog(
        			null,
        			"Se eliminó la asignatura: " + nombreAsignatura,
        			"Asignatura eliminado",
        			JOptionPane.INFORMATION_MESSAGE
        			
        			);
        	
        } else if (confirm == JOptionPane.NO_OPTION) {
        	System.out.println("Accion cancelada");
        	
        }
    }
    
    public void agregarAsignatura() {

        
        JPanel contenedor = new JPanel(new BorderLayout());
    contenedor.setBackground(new Color(245, 247, 250));

    JPanel panelSuperior = new JPanel(new BorderLayout());
    panelSuperior.setBackground(new Color(245, 247, 250));
    panelSuperior.setBorder(BorderFactory.createEmptyBorder(25, 40, 10, 40));

    JLabel subtitulo1 = new JLabel("Editar Asignatura seleccionada");
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
        app.cambiarVista(new AsignaturasView(app),
        "Asignaturas",
        "Gestion integral de asignaturas en el sistema")
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

    JLabel lblClave = new JLabel("Nombre de la asignatura");
    lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JLabel lblAsignatura = new JLabel("Clave");
    lblAsignatura.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JTextField txtGrupo = new JTextField("");
    txtGrupo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtGrupo.setPreferredSize(new Dimension(200, 45));

    JTextField txtClave = new JTextField();
    txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtClave.setPreferredSize(new Dimension(200, 45));

    JLabel lblSemestre = new JLabel("Semestre");
    lblSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 20));

    JLabel lblCapacidad = new JLabel("Créditos");
    lblCapacidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));

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

    JTextField txtCreditos = new JTextField("6");
    txtCreditos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    txtCreditos.setPreferredSize(new Dimension(200, 45));

    gbc.gridx = 0;
    gbc.gridy = 0;
    tarjeta.add(lblClave, gbc);

    gbc.gridx = 1;
    tarjeta.add(lblAsignatura, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    tarjeta.add(txtGrupo, gbc);

    gbc.gridx = 1;
    tarjeta.add(txtClave, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    tarjeta.add(lblSemestre, gbc);

    gbc.gridx = 1;
    tarjeta.add(lblCapacidad, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    tarjeta.add(comboSemestre, gbc);

    gbc.gridx = 1;
    tarjeta.add(txtCreditos, gbc);

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
            new AsignaturasView(app),
            "Asignaturas",
            "Gestion integral de asignaturas en el sistema"
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
        "Asignaturas",
        "Gestion integral de asignaturas en el sistema"
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
        tabla.getTableHeader().setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tabla.getTableHeader().setReorderingAllowed(false);

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
    
    private void setTodo(int fila) {
    	clave = tabla.getValueAt(fila,0).toString();
    	controller.verAsignatura(clave);
    	
    	nombre = controller.getNombre();      	        	
    	semestre = controller.getSemestre();
    	creditos = controller.getCreditos();
    	grupo = controller.getGrupo();
    	docente = controller.getDocente();
    }
   
}