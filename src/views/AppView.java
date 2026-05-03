package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import constructor_ventanas.App;
import controllers.AppController;
import models.UserModel;

public class AppView extends JFrame{
	
	private AppController controller;
	
	Color azul_principal = new Color(14, 48, 170);
	Color azul_hover = new Color(53,82,189);
	Color dorado = new Color(208,135,0);
	private JButton botonActivo;
	private JPanel panel_centro,panel_9;
	private JLabel descripcion_titulo,titulo_panel,lblNewLabel_4;
    JButton b = new JButton();


	public void ventana() {
	this.setMinimumSize(new Dimension(1200, 800));
	this.setMaximumSize(new Dimension(1920, 1080));
	this.setExtendedState(Frame.MAXIMIZED_BOTH);
	this.setTitle("Educadex");
	this.setIconImage(Toolkit.getDefaultToolkit().getImage(AuthView.class.getResource("/resources/logo_virrete-32x32.png")));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setLayout(new BorderLayout(0, 0));
	
	JPanel panel = new JPanel();
	panel.setPreferredSize(new Dimension(285, 10));
	panel.setBackground(azul_principal);
	this.getContentPane().add(panel, BorderLayout.WEST);
	panel.setLayout(new BorderLayout(0, 0));
	
	JPanel panel_1 = new JPanel();
	panel_1.setPreferredSize(new Dimension(10, 120));
	panel_1.setBackground(azul_principal);
	panel.add(panel_1, BorderLayout.NORTH);
	panel_1.setLayout(new BorderLayout(0, 0));
	
	JPanel panel_2 = new JPanel();
	panel_2.setBackground(azul_hover);
	panel_2.setPreferredSize(new Dimension(10, 1));
	panel_1.add(panel_2, BorderLayout.SOUTH);
	
	JLabel lblNewLabel = new JLabel("EDUCADEX");
	lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
	lblNewLabel.setIconTextGap(20);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setForeground(new Color(255, 255, 255));
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
	panel_1.add(lblNewLabel, BorderLayout.CENTER);
	
	JLabel lblNewLabel_1 = new JLabel("");
	lblNewLabel_1.setPreferredSize(new Dimension(0, 50));
	lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setIcon(new ImageIcon(App.class.getResource("/resources/logo_virrete-32x32.png")));
	panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
	
	JPanel panel_1_1 = new JPanel();
	panel_1_1.setPreferredSize(new Dimension(10, 120));
	panel_1_1.setBackground(new Color(14, 48, 170));
	panel.add(panel_1_1, BorderLayout.SOUTH);
	panel_1_1.setLayout(new GridLayout(0, 1, 0, 0));
	
	JPanel panel_3 = new JPanel();
	panel_3.setOpaque(false);
	panel_1_1.add(panel_3);
	panel_3.setLayout(new BorderLayout(0, 0));
	
	JPanel panel_5 = new JPanel();
	panel_5.setBackground(azul_hover);
	panel_5.setPreferredSize(new Dimension(10, 1));
	panel_3.add(panel_5, BorderLayout.NORTH);
	
	JButton btnNewButton = new JButton("Cerrar sesión");
    btnNewButton.setIconTextGap(20);
    btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
    btnNewButton.setFocusable(false);
    btnNewButton.setIcon(new ImageIcon(App.class.getResource("/resources/icono_cerrar.png")));
    btnNewButton.setForeground(new Color(255, 255, 255));
    btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 24));
    btnNewButton.setContentAreaFilled(false);
    btnNewButton.setBorderPainted(false);
    btnNewButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        	btnNewButton.setBorderPainted(true);
        	btnNewButton.setOpaque(true);
        	btnNewButton.setBackground(azul_hover);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        	btnNewButton.setBorderPainted(false);
        	btnNewButton.setBackground(azul_principal);
        }
    });
    
    btnNewButton.addActionListener(e -> {
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro que desea salir del sistema de control escolar?\nTendrá que volver a ingresar sus credenciales.",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            controller.cerrarSesion();
        }
    });
	panel_1_1.add(btnNewButton);
	
	JPanel panel_4 = new JPanel();
    panel_4.setOpaque(false);
    panel_1_1.add(panel_4);

    JPanel panel_6 = new JPanel();
    panel_6.setOpaque(false);
    panel.add(panel_6, BorderLayout.WEST);

    JPanel panel_7 = new JPanel();
    panel_7.setOpaque(false);
    panel.add(panel_7, BorderLayout.EAST);

    JPanel panel_8 = new JPanel();
    panel_8.setOpaque(false);
    panel.add(panel_8, BorderLayout.CENTER);
    panel_8.setLayout(new GridLayout(9, 1, 5, 15));

    MouseAdapter hoverListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            b = (JButton) e.getSource();
            if (b != botonActivo) {
            		b.setBorderPainted(true);
            		b.setOpaque(true);
            		b.setBackground(azul_hover);          		
            	
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton b = (JButton) e.getSource();
            if (b != botonActivo) {
                b.setBorderPainted(false);
                b.setBackground(azul_principal);
            	
            }
        }
    };

    ActionListener action = e -> seleccionar((JButton) e.getSource());

    JButton botonInicio = crearBoton("Inicio", hoverListener, action);
    JButton botonDocentes = crearBoton("Docentes", hoverListener, action);
    JButton botonAlumnos = crearBoton("Alumnos", hoverListener, action);
    JButton botonGrupos = crearBoton("Grupos", hoverListener, action);
    JButton botonAsignaturas = crearBoton("Asignaturas", hoverListener, action);
    
    botonInicio.addActionListener(e -> {
    	inicio();
    });

    botonDocentes.addActionListener(e -> {
    	docentes();
    });

    botonAlumnos.addActionListener(e -> {
    	alumnos();
    });

    botonGrupos.addActionListener(e -> {
    	grupos();
    
    });

    botonAsignaturas.addActionListener(e -> {
    	asignaturas();
    });

    botonInicio.setIcon(new ImageIcon(App.class.getResource("/resources/icono_inicio_dorado.png")));
    botonInicio.setBackground(azul_hover);
    botonInicio.setFont(new Font("Segoe UI", Font.BOLD, 24));
    botonInicio.setForeground(dorado);
    botonInicio.setBorderPainted(true);
    b=botonInicio;
    botonActivo=b;
    
    
    panel_8.add(botonInicio);
    panel_8.add(botonDocentes);
    panel_8.add(botonAlumnos);
    panel_8.add(botonGrupos);
    panel_8.add(botonAsignaturas);
    
    panel_9 = new JPanel();
    this.getContentPane().add(panel_9, BorderLayout.CENTER);
    panel_9.setLayout(new BorderLayout(0, 0));
    
    JPanel panel_10 = new JPanel();
    panel_10.setBackground(new Color(255, 255, 255));
    panel_10.setPreferredSize(new Dimension(10, 120));
    panel_9.add(panel_10, BorderLayout.NORTH);
    panel_10.setLayout(new GridLayout(0, 2, 0, 0));
    
    JPanel panel_13 = new JPanel();
    panel_13.setBackground(new Color(255, 255, 255));
    panel_10.add(panel_13);
    panel_13.setLayout(new GridLayout(0, 1, 0, 0));
    
    titulo_panel = new JLabel("    Inicio");
    titulo_panel.setVerticalAlignment(SwingConstants.BOTTOM);
    titulo_panel.setIconTextGap(20);
    titulo_panel.setHorizontalTextPosition(SwingConstants.LEFT);
    titulo_panel.setHorizontalAlignment(SwingConstants.LEFT);
    titulo_panel.setForeground(azul_principal);
    titulo_panel.setFont(new Font("Times New Roman", Font.BOLD, 40));
    panel_13.add(titulo_panel);
    
    descripcion_titulo = new JLabel("       Bienvenido al sistema de control escolar");
    descripcion_titulo.setVerticalAlignment(SwingConstants.TOP);
    descripcion_titulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
    panel_13.add(descripcion_titulo);
    
    JPanel panel_12 = new JPanel();
    panel_12.setBackground(new Color(255, 255, 255));
    panel_10.add(panel_12);
    panel_12.setLayout(new GridLayout(0, 2, 0, 0));
    
    JPanel panel_14 = new JPanel();
    panel_14.setOpaque(false);
    panel_12.add(panel_14);
    
    JPanel panel_15 = new JPanel();
    panel_15.setBackground(new Color(255, 255, 255));
    panel_12.add(panel_15);
    panel_15.setLayout(new GridLayout(2, 1, 0, 0));
    
    JLabel lblNewLabel_5 = new JLabel("");
    lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    ImageIcon iconoUser = new ImageIcon(AppView.class.getResource("/resources/user_online.png"));
    Image imgUser = iconoUser.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    lblNewLabel_5.setIcon(new ImageIcon(imgUser));
    panel_15.add(lblNewLabel_5);
    
    lblNewLabel_4 = new JLabel("nombre usuario");
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
    lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
    panel_15.add(lblNewLabel_4);
    
    panel_centro = new JPanel();
    panel_centro.setBackground(new Color(2,240,240));
    panel_9.add(panel_centro, BorderLayout.CENTER);
    inicio();
    this.setVisible(true);
}

	public void inicio() {
	    panel_9.remove(panel_centro);

	    panel_centro = new JPanel();
	    panel_centro.setBackground(azul_hover);
	    panel_9.add(panel_centro, BorderLayout.CENTER);

	    descripcion_titulo.setText("       Bienvenido al sistema de control escolar");
	    titulo_panel.setText("    Inicio");

	    panel_9.revalidate();
	    panel_9.repaint();
	}

	public void docentes() {
	    panel_9.remove(panel_centro);

	    panel_centro = new JPanel();
	    panel_centro.setBackground(new Color(240, 240, 1));
	    panel_9.add(panel_centro, BorderLayout.CENTER);

	    descripcion_titulo.setText("       Gestion integral de docentes en el sistema");
	    titulo_panel.setText("    Docentes");

	    panel_9.revalidate();
	    panel_9.repaint();
	}

	public void alumnos() {
	    panel_9.remove(panel_centro);
	
	    panel_centro = new JPanel();
	    panel_centro.setLayout(new BorderLayout());
	    panel_centro.setBackground(Color.WHITE);
	    panel_9.add(panel_centro, BorderLayout.CENTER);
	
	    JPanel panel_norte = new JPanel();
	    panel_centro.add(panel_norte, BorderLayout.NORTH);
	
	    JTextField txtBusqueda = new JTextField();
	
	    JButton btnAgregar = new JButton("Agregar alumno");
	    btnAgregar.setIcon(new ImageIcon(AppView.class.getResource("/resources/agregar.png")));
	    btnAgregar.setPreferredSize(new Dimension(250, 45));
	    btnAgregar.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
	    btnAgregar.setFocusable(false);
	    btnAgregar.setForeground(Color.WHITE);
	    btnAgregar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	    btnAgregar.setBackground(new Color(14, 48, 170));
	
	    GroupLayout gl = new GroupLayout(panel_norte);
	    panel_norte.setLayout(gl);
	
	    gl.setHorizontalGroup(
	        gl.createSequentialGroup()
	            .addGap(23)
	            .addComponent(txtBusqueda, 900, 900, Short.MAX_VALUE)
	            .addGap(10)
	            .addComponent(btnAgregar, 250, 300, 300)
	            .addGap(23)
	    );
	
	    gl.setVerticalGroup(
	        gl.createSequentialGroup()
	            .addContainerGap()
	            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
	                .addComponent(txtBusqueda, 40, 50, 50)
	                .addComponent(btnAgregar, 40, 50, 50))
	            .addContainerGap()
	    );
	
	    JPanel panel_tabla = new JPanel(new BorderLayout());
	    panel_centro.add(panel_tabla, BorderLayout.CENTER);
	
	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("Matrícula");
	    modelo.addColumn("Nombre Completo");
	    modelo.addColumn("Grupo");
	    modelo.addColumn("Semestre");
	    modelo.addColumn("Promedio");
	    modelo.addColumn("Estatus");
	    modelo.addColumn("Acciones");
	
	    JTable tabla = new JTable(modelo);
	
	    tabla.getColumn("Acciones").setCellRenderer(new PanelBotones());

	    tabla.setRowHeight(30);
	    tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	    tabla.setBackground(Color.WHITE);
	    tabla.setForeground(new Color(60, 60, 60));
	    tabla.setGridColor(new Color(235, 235, 235));
	
	    tabla.getTableHeader().setBackground(Color.WHITE);
	    tabla.getTableHeader().setForeground(new Color(14, 48, 170));
	    tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
	    tabla.getTableHeader().setBorder(javax.swing.BorderFactory.createEmptyBorder());
	    tabla.getTableHeader().setReorderingAllowed(false);
	
	    tabla.setShowVerticalLines(false);
	    tabla.setShowHorizontalLines(true);

	    javax.swing.table.DefaultTableCellRenderer centrado = new javax.swing.table.DefaultTableCellRenderer();
	    centrado.setHorizontalAlignment(SwingConstants.CENTER);
	
	    for (int i = 0; i < tabla.getColumnCount(); i++) {
	        if (!tabla.getColumnName(i).equals("Acciones")) {
	            tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
	        }
	    }

	    javax.swing.table.DefaultTableCellRenderer renderer = new javax.swing.table.DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(
	                JTable table, Object value, boolean isSelected,
	                boolean hasFocus, int row, int column) {
	
	            Component c = super.getTableCellRendererComponent(
	                    table, value, isSelected, hasFocus, row, column);
	
	            if (isSelected) {
	                c.setBackground(new Color(53, 82, 189));
	                c.setForeground(Color.WHITE);
	            } else {
	                c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 247, 250));
	                c.setForeground(new Color(60, 60, 60));
	            }
	
	            return c;
	        }
	    };
	
	    tabla.setDefaultRenderer(Object.class, renderer);
	
	    JScrollPane scroll = new JScrollPane(tabla);
	    panel_tabla.add(scroll, BorderLayout.CENTER);
	    
	    try {
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/educadex",
	            "root",
	            "educadex2026"
	        );
	
	        String sql = """
	            SELECT 
	                a.matricula,
	                CONCAT(a.nombre, ' ', a.apellido_paterno, ' ', a.apellido_materno) AS nombre_completo,
	                g.grupo AS grupo,
	                a.semestre,
	                a.promedio,
	                a.estatus
	            FROM alumnos a
	            LEFT JOIN grupos g ON a.id_grupo = g.id_grupo
	        """;
	
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	
	        while (rs.next()) {
	            modelo.addRow(new Object[]{
	                rs.getInt("matricula"),
	                rs.getString("nombre_completo"),
	                rs.getString("grupo"),
	                rs.getInt("semestre"),
	                rs.getDouble("promedio"),
	                rs.getString("estatus"),
	                ""
	            });
	        }
	
	        rs.close();
	        ps.close();
	        con.close();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    descripcion_titulo.setText("       Gestion integral de alumnos en el sistema");
	    titulo_panel.setText("    Alumnos");
	
	    panel_9.revalidate();
	    panel_9.repaint();
	}

	public void grupos() {
	    panel_9.remove(panel_centro);

	    panel_centro = new JPanel();
	    panel_centro.setBackground(new Color(200, 255, 200));
	    panel_9.add(panel_centro, BorderLayout.CENTER);

	    descripcion_titulo.setText("       Gestion integral de grupos en el sistema");
	    titulo_panel.setText("    Grupos");

	    panel_9.revalidate();
	    panel_9.repaint();
	}

	public void asignaturas() {
	    panel_9.remove(panel_centro);

	    panel_centro = new JPanel();
	    panel_centro.setBackground(new Color(255, 220, 180));
	    panel_9.add(panel_centro, BorderLayout.CENTER);

	    descripcion_titulo.setText("       Gestion integral de asignaturas en el sistema");
	    titulo_panel.setText("    Asignaturas");

	    panel_9.revalidate();
	    panel_9.repaint();
	}
	
	
	
	
	
	
	private JButton crearBoton(String texto, MouseAdapter hover, ActionListener action) {
	    JButton b = new JButton(texto);
	    b.setIconTextGap(20);
	    if(texto.equals("Inicio")) {
	    	 b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_inicio.png")));
	    }else if(texto.equals("Docentes")) {
	    	 b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_docentes.png")));
	    }else if(texto.equals("Alumnos")) {
	    	 b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_alumnos.png")));

	    }else if(texto.equals("Grupos")) {
	    	 b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_grupos.png")));

	    }else if(texto.equals("Asignaturas")) {
	    	 b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_asignaturas.png")));

	    }
	    
	    b.setHorizontalAlignment(SwingConstants.LEFT);
	    b.setForeground(Color.WHITE);
	    b.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	    b.setFocusable(false);
	    b.setBorderPainted(false);
	    b.setBorder(new LineBorder(azul_hover, 4, true));
	    b.setBackground(azul_principal);
	    b.addMouseListener(hover);
	    b.addActionListener(action);
	    return b;
	}

	public void mostrarUsuario(UserModel usuario) {
		lblNewLabel_4.setText(usuario.getUsername());
	}
	private void seleccionar(JButton b) {
	    if (botonActivo != null) {

	        if (botonActivo.getText().equals("Inicio")) {
	            botonActivo.setIcon(new ImageIcon(App.class.getResource("/resources/icono_inicio.png")));
	        } else if (botonActivo.getText().equals("Docentes")) {
	            botonActivo.setIcon(new ImageIcon(App.class.getResource("/resources/icono_docentes.png")));
	        } else if (botonActivo.getText().equals("Alumnos")) {
	            botonActivo.setIcon(new ImageIcon(App.class.getResource("/resources/icono_alumnos.png")));
	        } else if (botonActivo.getText().equals("Grupos")) {
	            botonActivo.setIcon(new ImageIcon(App.class.getResource("/resources/icono_grupos.png")));
	        } else if (botonActivo.getText().equals("Asignaturas")) {
	            botonActivo.setIcon(new ImageIcon(App.class.getResource("/resources/icono_asignaturas.png")));
	        }

	        botonActivo.setBackground(azul_principal);
	        botonActivo.setForeground(Color.WHITE);
	        botonActivo.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	        botonActivo.setBorderPainted(false);
	    }

	    botonActivo = b;

	    if (b.getText().equals("Inicio")) {
	        b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_inicio_dorado.png")));
	    } else if (b.getText().equals("Docentes")) {
	        b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_docentes_dorado.png")));
	    } else if (b.getText().equals("Alumnos")) {
	        b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_alumnos_dorado.png")));
	    } else if (b.getText().equals("Grupos")) {
	        b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_grupos_dorado.png")));
	    } else if (b.getText().equals("Asignaturas")) {
	        b.setIcon(new ImageIcon(App.class.getResource("/resources/icono_asignaturas_dorado.png")));
	    }

	    b.setBackground(azul_hover);
	    b.setFont(new Font("Segoe UI", Font.BOLD, 24));
	    b.setForeground(dorado);
	    b.setBorderPainted(true);
	    	
	    
	}
	public void setController(AppController controller) {
	    this.controller = controller;
	}
	
	class PanelBotones extends JPanel implements TableCellRenderer {

	    public PanelBotones() {
	        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));

	        JButton btnVer = new JButton("");
	        ImageIcon iconoVer = new ImageIcon(AppView.class.getResource("/resources/eye.png"));
	        Image imgVer = iconoVer.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	        btnVer.setIcon(new ImageIcon(imgVer));

	        btnVer.setPreferredSize(new Dimension(25, 25));
	        btnVer.setBorderPainted(false);
	        btnVer.setContentAreaFilled(false);
	        btnVer.setFocusPainted(false);
	        btnVer.setOpaque(false);

	        JButton btnEditar = new JButton("");
	        ImageIcon iconoEditar = new ImageIcon(AppView.class.getResource("/resources/square-pen.png"));
	        Image imgEditar = iconoEditar.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	        btnEditar.setIcon(new ImageIcon(imgEditar));

	        btnEditar.setPreferredSize(new Dimension(25, 25));
	        btnEditar.setBorderPainted(false);
	        btnEditar.setContentAreaFilled(false);
	        btnEditar.setFocusPainted(false);
	        btnEditar.setOpaque(false);

	        JButton btnEliminar = new JButton("");
	        ImageIcon iconoEliminar = new ImageIcon(AppView.class.getResource("/resources/trash.png"));
	        Image imgEliminar = iconoEliminar.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	        btnEliminar.setIcon(new ImageIcon(imgEliminar));

	        btnEliminar.setPreferredSize(new Dimension(25, 25));
	        btnEliminar.setBorderPainted(false);
	        btnEliminar.setContentAreaFilled(false);
	        btnEliminar.setFocusPainted(false);
	        btnEliminar.setOpaque(false);

	        add(btnVer);
	        add(btnEditar);
	        add(btnEliminar);
	    }

	    @Override
	    public Component getTableCellRendererComponent(
	            JTable table, Object value, boolean isSelected,
	            boolean hasFocus, int row, int column) {

	        setBackground(Color.WHITE);
	        return this;
	    }
	}
	
}
