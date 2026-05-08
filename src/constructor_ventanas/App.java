package constructor_ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import controllers.InicioController;

import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;


public class App {

	Color azul_principal = new Color(14, 48, 170);
	Color azul_hover = new Color(53,82,189);
	Color dorado = new Color(208,135,0);
	private JButton botonActivo;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        Color dorado = new Color(208, 135, 0);
        Color verde_lima = new Color(3, 233, 38);
        Color rojo_claro = new Color(255, 77, 77);
        Color azul_celeste = new Color(61, 161, 255);
        Color azul_principal = new Color(14, 48, 170);
		
    frame = new JFrame();
    frame.setMinimumSize(new Dimension(1200, 800));
    frame.setMaximumSize(new Dimension(1920, 1080));
    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout(0, 0));

    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(285, 10));
    panel.setBackground(azul_principal);
    frame.getContentPane().add(panel, BorderLayout.WEST);
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
    panel_1_1.setPreferredSize(new Dimension(10, 100));
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
            JButton b = (JButton) e.getSource();
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

    panel_8.add(botonInicio);
    panel_8.add(botonDocentes);
    panel_8.add(botonAlumnos);
    panel_8.add(botonGrupos);
    panel_8.add(botonAsignaturas);
    
    JPanel panel_9 = new JPanel();
    frame.getContentPane().add(panel_9, BorderLayout.CENTER);
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
    
    JLabel lblNewLabel_3 = new JLabel("    Inicio");
    lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
    lblNewLabel_3.setIconTextGap(20);
    lblNewLabel_3.setHorizontalTextPosition(SwingConstants.LEFT);
    lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
    lblNewLabel_3.setForeground(azul_principal);
    lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 40));
    panel_13.add(lblNewLabel_3);
    
    JLabel lblNewLabel_2 = new JLabel("       Bienvenido al sistema de control escolar");
    lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
    lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
    panel_13.add(lblNewLabel_2);
    
    JPanel panel_12 = new JPanel();
    panel_12.setBackground(new Color(255, 255, 255));
    panel_10.add(panel_12);
    panel_12.setLayout(new GridLayout(0, 2, 0, 0));
    
    JPanel panel_16 = new JPanel();
    panel_9.add(panel_16, BorderLayout.CENTER);
    panel_16.setLayout(new BorderLayout(0, 0));
    
    JPanel panel_18 = new JPanel();
    panel_16.add(panel_18, BorderLayout.NORTH);
    
    JLabel lblNewLabel_4 = new JLabel("Detalles del grupo");
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_4.setForeground(azul_principal);
    lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 40));
    
    JButton btnNewButton_1 = new JButton("<-  Volver");
    btnNewButton_1.setFocusable(false);
    btnNewButton_1.setBorder(null);
    btnNewButton_1.setBorderPainted(false);
    btnNewButton_1.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseEntered(MouseEvent e) {
    		btnNewButton_1.setForeground(azul_principal);
    	}
    	@Override
    	public void mouseExited(MouseEvent e) {
    		btnNewButton_1.setForeground(new Color(64, 64, 64));
    	}
    });
    btnNewButton_1.setForeground(new Color(64, 64, 64));
    btnNewButton_1.setContentAreaFilled(false);
    btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 28));
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	}
    });
    GroupLayout gl_panel_18 = new GroupLayout(panel_18);
    gl_panel_18.setHorizontalGroup(
    	gl_panel_18.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_panel_18.createSequentialGroup()
    			.addGap(57)
    			.addComponent(lblNewLabel_4)
    			.addPreferredGap(ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
    			.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
    			.addGap(52))
    );
    gl_panel_18.setVerticalGroup(
    	gl_panel_18.createParallelGroup(Alignment.TRAILING)
    		.addGroup(gl_panel_18.createSequentialGroup()
    			.addContainerGap(54, Short.MAX_VALUE)
    			.addGroup(gl_panel_18.createParallelGroup(Alignment.BASELINE)
    				.addComponent(lblNewLabel_4)
    				.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
    			.addGap(26))
    );
    panel_18.setLayout(gl_panel_18);
    
    JPanel panel_19 = new JPanel();
    FlowLayout flowLayout_2 = (FlowLayout) panel_19.getLayout();
    flowLayout_2.setVgap(140);
    panel_16.add(panel_19, BorderLayout.SOUTH);
    
    JPanel panel_20 = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panel_20.getLayout();
    flowLayout.setHgap(30);
    panel_16.add(panel_20, BorderLayout.WEST);
    
    JPanel panel_11 = new JPanel();
    FlowLayout flowLayout_3 = (FlowLayout) panel_11.getLayout();
    flowLayout_3.setHgap(30);
    panel_16.add(panel_11, BorderLayout.EAST);
    
    JPanel panel_14 = new JPanel();
    panel_14.setBackground(new Color(255, 255, 255));
    panel_14.setBorder(new LineBorder(new Color(192, 192, 192), 4, true));
    panel_16.add(panel_14, BorderLayout.CENTER);
    
    JLabel lblNewLabel_5 = new JLabel("Grupo 1A");
    lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 30));
    
    JLabel lblNewLabel_6 = new JLabel("Semestre: ");
    lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
    
    JLabel lblNewLabel_6_1 = new JLabel("Turno:");
    lblNewLabel_6_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
    
    JLabel lblNewLabel_6_2 = new JLabel("Capacidad:");
    lblNewLabel_6_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
    
    JLabel lblNewLabel_6_3 = new JLabel("Docente:");
    lblNewLabel_6_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
    
    JButton btnPDF = new JButton(
    	    "<html>" +
    	    "<span style='color:black; font-family:Segoe UI; font-size:16px;'>Imprimir </span>" +
    	    "<span style='color:red; font-family:Segoe UI; font-size:16px;'><b>PDF</b></span>" +
    	    "</html>"
    	);
    btnPDF.setFocusable(false);
    btnPDF.setContentAreaFilled(false);
    GroupLayout gl_panel_14 = new GroupLayout(panel_14);
    gl_panel_14.setHorizontalGroup(
    	gl_panel_14.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_panel_14.createSequentialGroup()
    			.addGap(34)
    			.addGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_14.createSequentialGroup()
    					.addComponent(lblNewLabel_6_3)
    					.addPreferredGap(ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
    					.addComponent(btnPDF)
    					.addGap(125))
    				.addGroup(gl_panel_14.createSequentialGroup()
    					.addGroup(gl_panel_14.createParallelGroup(Alignment.LEADING)
    						.addComponent(lblNewLabel_6_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblNewLabel_6)
    						.addComponent(lblNewLabel_6_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
    						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
    					.addContainerGap(589, Short.MAX_VALUE))))
    );
    gl_panel_14.setVerticalGroup(
    	gl_panel_14.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_panel_14.createSequentialGroup()
    			.addGap(33)
    			.addComponent(lblNewLabel_5)
    			.addGap(26)
    			.addComponent(lblNewLabel_6)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(lblNewLabel_6_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(lblNewLabel_6_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addGroup(gl_panel_14.createParallelGroup(Alignment.BASELINE)
    				.addComponent(lblNewLabel_6_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
    				.addComponent(btnPDF, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
    			.addContainerGap(11, Short.MAX_VALUE))
    );
    panel_14.setLayout(gl_panel_14);
    
    
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
}
