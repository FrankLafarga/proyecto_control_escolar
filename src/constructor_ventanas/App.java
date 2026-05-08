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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class App {

    Color verde_lima = new Color(3, 233, 38);
    Color rojo_claro = new Color(255, 77, 77);
    Color azul_celeste = new Color(61, 161, 255);
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
    
    JPanel panel_14 = new JPanel();
    panel_14.setOpaque(false);
    panel_12.add(panel_14);
    
    JPanel panel_15 = new JPanel();
    panel_15.setOpaque(false);
    panel_15.setBackground(new Color(255, 255, 255));
    panel_12.add(panel_15);
    panel_15.setLayout(new GridLayout(2, 1, 0, 0));
    
    JLabel lblNewLabel_5 = new JLabel("");
    lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_5.setIcon(new ImageIcon(App.class.getResource("/resources/logo_virrete-32x32.png")));
    panel_15.add(lblNewLabel_5);
    
    JLabel lblNewLabel_4 = new JLabel("nombre usuario");
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
    panel_15.add(lblNewLabel_4);
    
    JPanel panel_centro = new JPanel();
    panel_9.add(panel_centro, BorderLayout.CENTER);
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
    
    JButton btnNewButton_1 = new JButton("Volver    ");
    btnNewButton_1.setIcon(new ImageIcon(App.class.getResource("/resources/flecha16}.png")));
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
    btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 26));
    btnNewButton_1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        }
    });
    panel_19.setLayout(new BorderLayout(0, 0));
    
    panel_19.add(btnNewButton_1, BorderLayout.EAST);
    
    JPanel panel_20 = new JPanel();
    panel_20.setPreferredSize(new Dimension(50, 10));
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
    panel_22.setPreferredSize(new Dimension(50, 10));
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
    lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    
    JLabel lblNewLabel_9 = new JLabel("");
    lblNewLabel_9.setIcon(new ImageIcon(App.class.getResource("/resources/medalla.png")));
    
    JLabel lblNewLabel_10 = new JLabel("9.82");
    lblNewLabel_10.setForeground(new Color(0, 255, 64));
    lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 40));
    GroupLayout gl_panel_24 = new GroupLayout(panel_24);
    gl_panel_24.setHorizontalGroup(
    	gl_panel_24.createParallelGroup(Alignment.TRAILING)
    		.addGroup(Alignment.LEADING, gl_panel_24.createSequentialGroup()
    			.addContainerGap()
    			.addComponent(lblNewLabel_9)
    			.addPreferredGap(ComponentPlacement.UNRELATED)
    			.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
    			.addGap(25))
    		.addGroup(Alignment.LEADING, gl_panel_24.createSequentialGroup()
    			.addGap(26)
    			.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
    			.addContainerGap(140, Short.MAX_VALUE))
    );
    gl_panel_24.setVerticalGroup(
    	gl_panel_24.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_panel_24.createSequentialGroup()
    			.addGroup(gl_panel_24.createParallelGroup(Alignment.LEADING)
    				.addGroup(gl_panel_24.createSequentialGroup()
    					.addContainerGap()
    					.addComponent(lblNewLabel_9))
    				.addGroup(gl_panel_24.createSequentialGroup()
    					.addGap(24)
    					.addComponent(lblNewLabel_8)))
    			.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
    			.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
    			.addGap(34))
    );
    panel_24.setLayout(gl_panel_24);
    
    JPanel panel_25 = new JPanel();
    panel_25.setBorder(new LineBorder(new Color(240, 240, 240), 2, true));
    panel_25.setBackground(Color.LIGHT_GRAY);
    panel_23.add(panel_25);
    
    JPanel panel_26 = new JPanel();
    panel_26.setBorder(new LineBorder(new Color(240, 240, 240), 2, true));
    panel_26.setBackground(Color.LIGHT_GRAY);
    panel_23.add(panel_26);
    
    JPanel panel_18 = new JPanel();
    panel_16.add(panel_18);
    
    
    ImageIcon iconoOriginalDocentes = new ImageIcon(App.class.getResource("/resources/Logo Docentes.png"));
    Image imagenEscaladaDocentes = iconoOriginalDocentes.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon iconoOriginalAlumnos = new ImageIcon(App.class.getResource("/resources/Logo Verde.png"));
    Image imagenEscaladaAlumnos = iconoOriginalAlumnos.getImage().getScaledInstance(60,55, Image.SCALE_SMOOTH);
    ImageIcon iconoOriginal = new ImageIcon(App.class.getResource("/resources/Logo Rojo.png"));
    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon iconoOriginalAsignaturas = new ImageIcon(App.class.getResource("/resources/Logo Azul.png"));
    Image imagenEscaladaAsignaturas = iconoOriginalAsignaturas.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    Color azul_principal = new Color(14, 48, 170);
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
