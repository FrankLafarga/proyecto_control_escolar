package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import controllers.DocentesController;
							
public class DocentesView extends JPanel {
	private AppView app;
	private JTable tabla;
	private Color azul_principal = new Color(14, 48, 170);
	
	private DocentesController controller=new DocentesController();
	
	private String nombre_completo;
	private String clave;
	private String correo;
	private String telefono;
	private String fecha;
	private String grado;
	private String area;
	private String estatus;
	private String avatar;

	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	
    public DocentesView(AppView app) {
		this.app = app;
	    setLayout(new BorderLayout());
	    setBackground(Color.WHITE);
	
	    JTextField txtBusqueda = new JTextField("Buscar docente...");
	    txtBusqueda.setForeground(Color.GRAY);
	    txtBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	
	    JPanel panel_16 = new JPanel(new BorderLayout());
	    panel_16.setOpaque(false);
	    panel_16.setPreferredSize(new Dimension(10, 50));
	    add(panel_16, BorderLayout.NORTH);
	
	    JPanel panel_17 = new JPanel(new BorderLayout());
	    panel_17.setOpaque(false);
	    panel_16.add(panel_17, BorderLayout.CENTER);
	                   
	    JButton btnAgregar = new JButton("+ Agregar docente");
	    btnAgregar.setPreferredSize(new Dimension(250, 45));
	    btnAgregar.setBorder(new LineBorder(Color.WHITE, 1, true));
	    btnAgregar.setFocusable(false);
	    btnAgregar.setForeground(Color.WHITE);
	    btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	    btnAgregar.setBackground(new Color(14, 48, 170));
	    btnAgregar.addActionListener(e ->{
	    	agregarDocente();
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
	    panel_tabla.setOpaque(false);
	    add(panel_tabla, BorderLayout.CENTER);
	
	    DefaultTableModel modelo = new DefaultTableModel() {
	        public boolean isCellEditable(int r, int c) { 
	        	return c == 4; 
	        
	        }
	    };
	
	    modelo.addColumn("Clave");
	    modelo.addColumn("Nombre Completo");
	    modelo.addColumn("Especialidad");
	    modelo.addColumn("Estatus");
	    modelo.addColumn("Acciones");
	
	    controller.cargarTabla(modelo);
	
	    tabla = new JTable(modelo);
	    tabla.setFillsViewportHeight(true);
	    tabla.getColumn("Acciones").setCellRenderer(new PanelBotones());
	    configurarTabla(tabla);
	
	    javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
	            new javax.swing.table.TableRowSorter<>(modelo);
	
	    tabla.setRowSorter(sorter);
	
	    txtBusqueda.addMouseListener(new MouseAdapter() {
	
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	
	    		if(txtBusqueda.getText().equals("Buscar docente...")) {
	    			txtBusqueda.setText("");
	    			txtBusqueda.setForeground(Color.BLACK);
	    		}
	    	}
	    });
	
	    txtBusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
	
	    	@Override
	    	public void focusLost(java.awt.event.FocusEvent e) {
	
	    		if(txtBusqueda.getText().trim().isEmpty()) {
	    			txtBusqueda.setText("Buscar docente...");
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
	
				if(texto.equals("Buscar docente...")) {
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

    SwingUtilities.invokeLater(() -> ajustarColumnas(tabla, scroll, new int[]{15,30,25,15,15}));
    
    tabla.getColumn("Acciones").setCellEditor(
    	    new PanelBotonesEditor(tabla, new AccionesTabla() {

    	        @Override
    	        public void ver(int fila) {        	       
    	        	setTodo(fila);
    	        	verDocente(fila);
    	        }
    	        @Override
    	        public void editar(int fila) {
    	        	setTodo(fila);
    	            editarDocente(fila);
    	        }
    	        @Override
    	        public void eliminar(int fila) {
    	        	eliminarDocente(fila);
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
    
    public void verDocente(int fila) {
    	
    	JPanel contenedor = new JPanel(new BorderLayout());
    	contenedor.setBackground(Color.WHITE);

    	JPanel panelSuperior = new JPanel(new BorderLayout());
    	panelSuperior.setBackground(Color.WHITE);
    	panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

    	JLabel titulo = new JLabel("Detalle del docente");
    	titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));

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
            app.cambiarVista(new DocentesView(app),"Docentes","Gestion integral de docentes en el sistema"));

    	panelSuperior.add(titulo, BorderLayout.WEST);
    	panelSuperior.add(volver, BorderLayout.EAST);

    	JPanel panelCentro = new JPanel(new BorderLayout());
    	panelCentro.setBackground(Color.WHITE);
    	panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));

    	JPanel centroWrapper = new JPanel(new BorderLayout());
    	centroWrapper.setBackground(Color.WHITE);
    	centroWrapper.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

    	JPanel tarjeta = new JPanel(new BorderLayout());
    	tarjeta.setBackground(Color.WHITE);

    	tarjeta.setBorder(BorderFactory.createCompoundBorder(
    	    new LineBorder(new Color(200, 200, 200), 2, true),
    	    BorderFactory.createEmptyBorder(20, 30, 20, 30)
    	));

    	JPanel contenido = new JPanel();
    	contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
    	contenido.setOpaque(false);

    	JLabel nombreDocente = new JLabel(nombre_completo);
    	nombreDocente.setFont(new Font("Segoe UI", Font.BOLD, 26));
    	nombreDocente.setAlignmentX(Component.LEFT_ALIGNMENT);

    	JLabel lblClave = new JLabel("Clave: " + clave);
    	JLabel especialidad = new JLabel("Especialidad: " + area);
    	JLabel lblEstatus = new JLabel("Estatus: " + estatus);
    	JLabel grupo = new JLabel("Grado: " + grado);

    	JLabel[] datos = {lblClave, especialidad, lblEstatus, grupo};

    	for (JLabel l : datos) {
    	    l.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    	    l.setAlignmentX(Component.LEFT_ALIGNMENT);
    	}

    	JPanel filaNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	filaNombre.setBackground(Color.WHITE);

    	filaNombre.add(nombreDocente);

    	contenido.add(filaNombre);

    	contenido.add(Box.createVerticalStrut(10));

    	for (JLabel l : datos) {

    	    JPanel filaDato = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	    filaDato.setBackground(Color.WHITE);

    	    filaDato.add(l);

    	    contenido.add(filaDato);
    	}

    	tarjeta.add(contenido, BorderLayout.CENTER);

    	JButton pdf = new JButton(
        		"<html>" +
        	    "<span style='color:black; font-family:Segoe UI; font-size:16px;'>Imprimir </span>" +
        	    "<span style='color:red; font-family:Segoe UI; font-size:16px;'><b>PDF</b></span>" +
        	    "</html>");
    	pdf.setPreferredSize(new Dimension(180, 40));
    	pdf.setFocusable(false);
    	pdf.setContentAreaFilled(false);

    	JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	panelBoton.setBackground(Color.WHITE);

    	panelBoton.add(pdf);

    	tarjeta.add(panelBoton, BorderLayout.SOUTH);

    	centroWrapper.add(tarjeta, BorderLayout.NORTH);

    	panelCentro.add(centroWrapper, BorderLayout.CENTER);

    	contenedor.add(panelSuperior, BorderLayout.NORTH);
    	contenedor.add(panelCentro, BorderLayout.CENTER);

    	app.cambiarVista(contenedor,"Docente","Detalle del docente seleccionado");
    }

	public void editarDocente(int fila) {
		Color azul_principal = new Color(14, 48, 170);
	
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
            app.cambiarVista(new DocentesView(app),
            "Docentes",
            "Gestion integral de docentes en el sistema")
        );
	
	    panelSuperior.add(volver, BorderLayout.EAST);
	
	    JPanel panelCentro = new JPanel();
	    panelCentro.setBackground(Color.WHITE);
	    panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	    panelCentro.setLayout(new GridLayout(10, 3, 20, 15));
	
	    JLabel subtitulo1 = new JLabel("Información académica");
	    subtitulo1.setFont(new Font("Segoe UI", Font.BOLD, 20));
	
	    panelCentro.add(subtitulo1);
	    panelCentro.add(new JLabel(""));
	    panelCentro.add(new JLabel(""));
	
	    JLabel lblClave = new JLabel("Clave de identificación");
	    lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblGrado = new JLabel("Grado de estudios");
	    lblGrado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblAvatar = new JLabel("Avatar");
	    lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAvatar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblClave);
	    panelCentro.add(lblGrado);
	    panelCentro.add(lblAvatar);
	
	    JTextField txtClave = new JTextField();
	    txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtClave.setText(clave);
	
	    JTextField txtGrado = new JTextField();
	    txtGrado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtGrado.setText(grado);
	
	    JLabel imagen = new JLabel("");
	    imagen.setHorizontalAlignment(SwingConstants.CENTER);
	    imagen.setIcon(new ImageIcon(App.class.getResource("/resources/logo_virrete-32x32.png")));
	
	    panelCentro.add(txtClave);
	    panelCentro.add(txtGrado);
	    panelCentro.add(imagen);
	
	    JLabel lblEstatus = new JLabel("Estatus");
	    lblEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblArea = new JLabel("Área de estudios");
	    lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblEstatus);
	    panelCentro.add(lblArea);
	    panelCentro.add(new JLabel(""));
	
	    JTextField txtEstatus = new JTextField();
	    txtEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtEstatus.setText(estatus);
	
	    JTextField txtArea = new JTextField();
	    txtArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtArea.setText(area);
	
	    panelCentro.add(txtEstatus);
	    panelCentro.add(txtArea);
	    panelCentro.add(new JLabel(""));
	
	    JLabel subtitulo2 = new JLabel("Información personal");
	    subtitulo2.setFont(new Font("Segoe UI", Font.BOLD, 20));
	
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
	    txtNombre.setText(nombre);
	
	    JTextField txtPaterno = new JTextField();
	    txtPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtPaterno.setText(apellidoPat);
	
	    JTextField txtMaterno = new JTextField();
	    txtMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtMaterno.setText(apellidoMat);
	
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
	    txtEmail.setText(correo);
	
	    JTextField txtTelefono = new JTextField();
	    txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtTelefono.setText(telefono);
	
	    JTextField txtFecha = new JTextField();
	    txtFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	    txtFecha.setText(fecha);
	
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
	        app.cambiarVista(
	            new DocentesView(app),
	            "Docentes",
	            "Gestion integral de docentes en el sistema"
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
	
	    app.cambiarVista(
	        contenedor,
	        "Docente",
	        "Editar docente seleccionado"
	    );
	}

    public void eliminarDocente(int fila) {

    	String nombreDocente = tabla.getValueAt(fila, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Estás seguro de eliminar el docente " + nombreDocente + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
        	JOptionPane.showMessageDialog(
        			null,
        			"Se eliminó el docente: " + nombreDocente,
        			"Docente eliminado",
        			JOptionPane.INFORMATION_MESSAGE
        			
        			);
        	
        } else if (confirm == JOptionPane.NO_OPTION) {
        	System.out.println("Accion cancelada");
        	
        }
    }
    
	public void agregarDocente() {
	
	    JPanel contenedor = new JPanel(new BorderLayout());
	    contenedor.setBackground(Color.WHITE);
	
	    JPanel panelSuperior = new JPanel(new BorderLayout());
	    panelSuperior.setBackground(Color.WHITE);
	    panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	
	    JLabel titulo = new JLabel("Crear docente");
	    titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
	
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
            app.cambiarVista(new DocentesView(app),
            "Docentes",
            "Gestion integral de docentes en el sistema")
        );
	
	    panelSuperior.add(titulo, BorderLayout.WEST);
	    panelSuperior.add(volver, BorderLayout.EAST);
	
	    JPanel panelCentro = new JPanel();
	    panelCentro.setBackground(Color.WHITE);
	    panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
	    panelCentro.setLayout(new GridLayout(10, 3, 20, 15));
	
	    JLabel subtitulo1 = new JLabel("Información académica");
	    subtitulo1.setFont(new Font("Segoe UI", Font.BOLD, 20));
	
	    panelCentro.add(subtitulo1);
	    panelCentro.add(new JLabel(""));
	    panelCentro.add(new JLabel(""));
	
	    JLabel lblClave = new JLabel("Clave de identificación");
	    lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblGrado = new JLabel("Grado de estudios");
	    lblGrado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblAvatar = new JLabel("Avatar");
	    lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAvatar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblClave);
	    panelCentro.add(lblGrado);
	    panelCentro.add(lblAvatar);
	
	    JTextField txtClave = new JTextField();
	    txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JTextField txtGrado = new JTextField();
	    txtGrado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel imagen = new JLabel("");
	    imagen.setHorizontalAlignment(SwingConstants.CENTER);
	    imagen.setIcon(new ImageIcon(App.class.getResource("/resources/logo_virrete-32x32.png")));
	
	    panelCentro.add(txtClave);
	    panelCentro.add(txtGrado);
	    panelCentro.add(imagen);
	
	    JLabel lblEstatus = new JLabel("Estatus");
	    lblEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    JLabel lblArea = new JLabel("Área de estudios");
	    lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(lblEstatus);
	    panelCentro.add(lblArea);
	    panelCentro.add(new JLabel(""));
	
	    String[] estatuses = {
	    		"ACTIVO",
	    		"INACTIVO"
	    };

	    JComboBox<String> cbEstatus = new JComboBox<>(estatuses);
	    cbEstatus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    String[] areas = {
	    	    "Matematicas",
	    	    "Fisica",
	    	    "Quimica",
	    	    "Historia",
	    	    "Programacion",
	    	    "Biologia",
	    	    "Ingles"
	    	};

	    	JComboBox<String> cbArea = new JComboBox<>(areas);
	    	cbArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	
	    panelCentro.add(cbEstatus);
	    panelCentro.add(cbArea);
	    panelCentro.add(new JLabel(""));
	
	    JLabel subtitulo2 = new JLabel("Información personal");
	    subtitulo2.setFont(new Font("Segoe UI", Font.BOLD, 20));
	
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
	        app.cambiarVista(
	            new DocentesView(app),
	            "Docentes",
	            "Gestion integral de docentes en el sistema"
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
	
	            txtClave.setBorder(bordeNormal);
	            txtGrado.setBorder(bordeNormal);
	            cbEstatus.setBorder(bordeNormal);
	            cbArea.setBorder(bordeNormal);
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
	            	
	            	boolean agregado = controller.addDocente(
	            			
	            			txtClave.getText().trim(),
	            			txtNombre.getText().trim(),
	            			txtPaterno.getText().trim(),
	            			txtMaterno.getText().trim(),
	            			txtEmail.getText().trim(),
	            			txtTelefono.getText().trim(),
	            			txtFecha.getText().trim(),
	            			txtGrado.getText().trim(),
	            			cbArea.getSelectedItem().toString(),
	            			cbEstatus.getSelectedItem().toString()
	            	);
	            	
	            	if(agregado) {
	            		
	            		JOptionPane.showMessageDialog(
	            				null,
	            				"Docente agregado correctamente",
	            				"Exito",
	            				JOptionPane.INFORMATION_MESSAGE
	            		);
	            		
	            		app.cambiarVista(
	            				new DocentesView(app),
	            				"Docentes",
	            				"Gestion integral de docentes en el sistema"
	            		);
	            		
	            	}
	            	else {
	            		
	            		JOptionPane.showMessageDialog(
	            				null,
	            				"No se pudo agregar el docente",
	            				"Error",
	            				JOptionPane.ERROR_MESSAGE
	            		);
	            	}
	            }
	        }
	    });
	
	    panelBotones.add(btnCancelar);
	    panelBotones.add(btnGuardar);
	
	    panelSur.add(panelBotones);
	
	    contenedor.add(panelSuperior, BorderLayout.NORTH);
	    contenedor.add(panelCentro, BorderLayout.CENTER);
	    contenedor.add(panelSur, BorderLayout.SOUTH);
	
	    app.cambiarVista(
	        contenedor,
	        "Docente",
	        "Crear docente"
	    );
	}
	
	private void setTodo(int fila) {
		clave = tabla.getValueAt(fila,0).toString();
		controller.verDocente(clave);
		nombre_completo = controller.getNombre_completo();
    	correo = controller.getCorreo();
    	telefono = controller.getTelefono();
    	fecha = controller.getFecha();
    	grado = controller.getGrado();
    	area = controller.getArea();
    	estatus = controller.getEstatus();
    	avatar = controller.getAvatar();
    	nombre=controller.getNombre();
    	apellidoPat=controller.getApellidoPat();
    	apellidoMat=controller.getApellidoMat();

    	    	
	}
}