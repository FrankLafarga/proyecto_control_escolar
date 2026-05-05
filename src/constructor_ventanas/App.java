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
    
    Color dorado = new Color(208, 135, 0);
    Color verde_lima = new Color(3, 233, 38);
    Color rojo_claro = new Color(255, 77, 77);
    Color azul_celeste = new Color(61, 161, 255);
    
    JPanel panel_centro = new JPanel();
    panel_centro.setBackground(new Color(255, 255, 255));
    panel_centro.setOpaque(false);
    panel_9.add(panel_centro, BorderLayout.CENTER);
    panel_centro.setLayout(new BorderLayout(0, 0));
    
    JPanel panel_11 = new JPanel();
    panel_11.setOpaque(false);
    panel_centro.add(panel_11, BorderLayout.WEST);
    
    JPanel panel_16 = new JPanel();
    panel_16.setOpaque(false);
    panel_centro.add(panel_16, BorderLayout.NORTH);
    
    JPanel panel_17 = new JPanel();
    panel_17.setOpaque(false);
    panel_centro.add(panel_17, BorderLayout.EAST);
    
    JPanel panel_18 = new JPanel();
    panel_18.setOpaque(false);
    panel_centro.add(panel_18, BorderLayout.SOUTH);
    
    JPanel panel_19 = new JPanel();
    panel_19.setOpaque(false);
    panel_centro.add(panel_19, BorderLayout.CENTER);
    panel_19.setLayout(new GridLayout(0, 1, 0, 0));
    
    JPanel panel_norte = new JPanel();
    panel_norte.setOpaque(false);
    panel_norte.setBackground(new Color(240, 240, 240));
    panel_19.add(panel_norte);
    panel_norte.setLayout(new GridLayout(0, 4, 20, 20));
    
    JPanel recuadro_docentes = new JPanel();
    recuadro_docentes.setOpaque(false);
    panel_norte.add(recuadro_docentes);
    recuadro_docentes.setLayout(new BorderLayout(0, 0));

    JPanel espaciador_norte_docentes = new JPanel();
    espaciador_norte_docentes.setOpaque(false);
    espaciador_norte_docentes.setPreferredSize(new Dimension(10, 50));
    recuadro_docentes.add(espaciador_norte_docentes, BorderLayout.NORTH);

    JPanel espaciador_oeste_docentes = new JPanel();
    espaciador_oeste_docentes.setOpaque(false);
    recuadro_docentes.add(espaciador_oeste_docentes, BorderLayout.WEST);

    JPanel espaciador_este_docentes = new JPanel();
    espaciador_este_docentes.setOpaque(false);
    recuadro_docentes.add(espaciador_este_docentes, BorderLayout.EAST);

    JPanel espaciador_sur_docentes = new JPanel();
    espaciador_sur_docentes.setPreferredSize(new Dimension(10, 50));
    espaciador_sur_docentes.setOpaque(false);
    recuadro_docentes.add(espaciador_sur_docentes, BorderLayout.SOUTH);

    JPanel panel_centro_docentes = new JPanel();
    panel_centro_docentes.setBorder(new LineBorder(dorado, 3, true));
    recuadro_docentes.add(panel_centro_docentes, BorderLayout.CENTER);
    panel_centro_docentes.setLayout(new GridLayout(4, 0, 0, 0));
    
    JLabel lblNewLabel_10 = new JLabel("  ");
    lblNewLabel_10.setVerticalAlignment(SwingConstants.BOTTOM);
    lblNewLabel_10.setHorizontalTextPosition(SwingConstants.LEFT);
    lblNewLabel_10.setIconTextGap(5);
    ImageIcon iconoOriginalDocentes = new ImageIcon(App.class.getResource("/resources/Logo Docentes.png"));
    Image imagenEscaladaDocentes = iconoOriginalDocentes.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    lblNewLabel_10.setIcon(new ImageIcon(imagenEscaladaDocentes));
    panel_centro_docentes.add(lblNewLabel_10);
    
    JPanel panel_25 = new JPanel();
    panel_centro_docentes.add(panel_25);
    
    JLabel lblNewLabel_11 = new JLabel("  11");
    lblNewLabel_11.setForeground(azul_principal);
    lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 40));
    lblNewLabel_11.setFocusable(false);
    panel_centro_docentes.add(lblNewLabel_11);
    
    JLabel lblNewLabel_12 = new JLabel("    Docentes");
    lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    panel_centro_docentes.add(lblNewLabel_12);

    JPanel recuadro_alumnos = new JPanel();
    recuadro_alumnos.setOpaque(false);
    panel_norte.add(recuadro_alumnos);
    recuadro_alumnos.setLayout(new BorderLayout(0, 0));

    JPanel espaciador_norte_alumnos = new JPanel();
    espaciador_norte_alumnos.setOpaque(false);
    espaciador_norte_alumnos.setPreferredSize(new Dimension(10, 50));
    recuadro_alumnos.add(espaciador_norte_alumnos, BorderLayout.NORTH);

    JPanel espaciador_oeste_alumnos = new JPanel();
    espaciador_oeste_alumnos.setOpaque(false);
    recuadro_alumnos.add(espaciador_oeste_alumnos, BorderLayout.WEST);

    JPanel espaciador_este_alumnos = new JPanel();
    espaciador_este_alumnos.setOpaque(false);
    recuadro_alumnos.add(espaciador_este_alumnos, BorderLayout.EAST);

    JPanel espaciador_sur_alumnos = new JPanel();
    espaciador_sur_alumnos.setPreferredSize(new Dimension(10, 50));
    espaciador_sur_alumnos.setOpaque(false);
    recuadro_alumnos.add(espaciador_sur_alumnos, BorderLayout.SOUTH);

    JPanel panel_centro_alumnos = new JPanel();
    panel_centro_alumnos.setBorder(new LineBorder(verde_lima, 3, true));
    recuadro_alumnos.add(panel_centro_alumnos, BorderLayout.CENTER);
    panel_centro_alumnos.setLayout(new GridLayout(4, 0, 0, 0));
    
    JLabel lblNewLabel_10_1 = new JLabel("  ");
    lblNewLabel_10_1.setVerticalTextPosition(SwingConstants.BOTTOM);
    lblNewLabel_10_1.setVerticalAlignment(SwingConstants.BOTTOM);
    lblNewLabel_10_1.setIconTextGap(5);
    lblNewLabel_10_1.setHorizontalTextPosition(SwingConstants.LEFT);
    ImageIcon iconoOriginalAlumnos = new ImageIcon(App.class.getResource("/resources/Logo Verde.png"));
    Image imagenEscaladaAlumnos = iconoOriginalAlumnos.getImage().getScaledInstance(60,55, Image.SCALE_SMOOTH);
    lblNewLabel_10_1.setIcon(new ImageIcon(imagenEscaladaAlumnos));
    panel_centro_alumnos.add(lblNewLabel_10_1);
    
    JPanel panel_25_1 = new JPanel();
    panel_centro_alumnos.add(panel_25_1);
    
    JLabel lblNewLabel_11_1 = new JLabel("  200");
    lblNewLabel_11_1.setForeground(azul_principal);
    lblNewLabel_11_1.setFont(new Font("Times New Roman", Font.BOLD, 40));
    lblNewLabel_11_1.setFocusable(false);
    panel_centro_alumnos.add(lblNewLabel_11_1);
    
    JLabel lblNewLabel_12_1 = new JLabel("    Alumnos");
    lblNewLabel_12_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    panel_centro_alumnos.add(lblNewLabel_12_1);

    JPanel recuadro_grupos = new JPanel();
    recuadro_grupos.setOpaque(false);
    panel_norte.add(recuadro_grupos);
    recuadro_grupos.setLayout(new BorderLayout(0, 0));

    JPanel espaciador_norte_grupos = new JPanel();
    espaciador_norte_grupos.setOpaque(false);
    espaciador_norte_grupos.setPreferredSize(new Dimension(10, 50));
    recuadro_grupos.add(espaciador_norte_grupos, BorderLayout.NORTH);

    JPanel espaciador_oeste_grupos = new JPanel();
    espaciador_oeste_grupos.setOpaque(false);
    recuadro_grupos.add(espaciador_oeste_grupos, BorderLayout.WEST);

    JPanel espaciador_este_grupos = new JPanel();
    espaciador_este_grupos.setOpaque(false);
    recuadro_grupos.add(espaciador_este_grupos, BorderLayout.EAST);

    JPanel espaciador_sur_grupos = new JPanel();
    espaciador_sur_grupos.setPreferredSize(new Dimension(10, 50));
    espaciador_sur_grupos.setOpaque(false);
    recuadro_grupos.add(espaciador_sur_grupos, BorderLayout.SOUTH);

    JPanel panel_centro_grupos = new JPanel();
    panel_centro_grupos.setBorder(new LineBorder(rojo_claro, 3, true));
    recuadro_grupos.add(panel_centro_grupos, BorderLayout.CENTER);
    panel_centro_grupos.setLayout(new GridLayout(4, 0, 0, 0));
    
    JLabel lblNewLabel_10_3 = new JLabel("  ");
    ImageIcon iconoOriginal = new ImageIcon(App.class.getResource("/resources/Logo Rojo.png"));
    Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    lblNewLabel_10_3.setIcon(new ImageIcon(imagenEscalada));
    lblNewLabel_10_3.setVerticalAlignment(SwingConstants.BOTTOM);
    lblNewLabel_10_3.setIconTextGap(5);
    
    lblNewLabel_10_3.setHorizontalTextPosition(SwingConstants.LEFT);
    panel_centro_grupos.add(lblNewLabel_10_3);
    
    JPanel panel_25_3 = new JPanel();
    panel_centro_grupos.add(panel_25_3);
    
    JLabel lblNewLabel_11_3 = new JLabel("  10");
    lblNewLabel_11_3.setForeground(azul_principal);
    lblNewLabel_11_3.setFont(new Font("Times New Roman", Font.BOLD, 40));
    lblNewLabel_11_3.setFocusable(false);
    panel_centro_grupos.add(lblNewLabel_11_3);
    
    JLabel lblNewLabel_12_3 = new JLabel("    Grupos");
    lblNewLabel_12_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    panel_centro_grupos.add(lblNewLabel_12_3);

    JPanel recuadro_asignaturas = new JPanel();
    recuadro_asignaturas.setOpaque(false);
    panel_norte.add(recuadro_asignaturas);
    recuadro_asignaturas.setLayout(new BorderLayout(0, 0));

    JPanel espaciador_norte_asignaturas = new JPanel();
    espaciador_norte_asignaturas.setOpaque(false);
    espaciador_norte_asignaturas.setPreferredSize(new Dimension(10, 50));
    recuadro_asignaturas.add(espaciador_norte_asignaturas, BorderLayout.NORTH);

    JPanel espaciador_oeste_asignaturas = new JPanel();
    espaciador_oeste_asignaturas.setOpaque(false);
    recuadro_asignaturas.add(espaciador_oeste_asignaturas, BorderLayout.WEST);

    JPanel espaciador_este_asignaturas = new JPanel();
    espaciador_este_asignaturas.setOpaque(false);
    recuadro_asignaturas.add(espaciador_este_asignaturas, BorderLayout.EAST);

    JPanel espaciador_sur_asignaturas = new JPanel();
    espaciador_sur_asignaturas.setPreferredSize(new Dimension(10, 50));
    espaciador_sur_asignaturas.setOpaque(false);
    recuadro_asignaturas.add(espaciador_sur_asignaturas, BorderLayout.SOUTH);

    JPanel panel_centro_asignaturas = new JPanel();
    panel_centro_asignaturas.setBorder(new LineBorder(azul_celeste, 3, true));
    recuadro_asignaturas.add(panel_centro_asignaturas, BorderLayout.CENTER);
    panel_centro_asignaturas.setLayout(new GridLayout(4, 0, 0, 0));
    
    JLabel lblNewLabel_10_2 = new JLabel("  ");
    lblNewLabel_10_2.setVerticalAlignment(SwingConstants.BOTTOM);
    lblNewLabel_10_2.setIconTextGap(5);
    lblNewLabel_10_2.setHorizontalTextPosition(SwingConstants.LEFT);
    ImageIcon iconoOriginalAsignaturas = new ImageIcon(App.class.getResource("/resources/Logo Azul.png"));
    Image imagenEscaladaAsignaturas = iconoOriginalAsignaturas.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    lblNewLabel_10_2.setIcon(new ImageIcon(imagenEscaladaAsignaturas));
    panel_centro_asignaturas.add(lblNewLabel_10_2);
    
    JPanel panel_25_2 = new JPanel();
    panel_centro_asignaturas.add(panel_25_2);
    
    JLabel lblNewLabel_11_2 = new JLabel("  15");
    lblNewLabel_11_2.setForeground(azul_principal);
    lblNewLabel_11_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
    lblNewLabel_11_2.setFocusable(false);
    panel_centro_asignaturas.add(lblNewLabel_11_2);
    
    JLabel lblNewLabel_12_2 = new JLabel("    Asignaturas");
    lblNewLabel_12_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    panel_centro_asignaturas.add(lblNewLabel_12_2);
    
    JPanel panel_sur = new JPanel();
    panel_sur.setOpaque(false);
    panel_sur.setBackground(new Color(255, 255, 255));
    panel_19.add(panel_sur);
    panel_sur.setLayout(new BorderLayout(0, 0));
    
    JPanel panel_20 = new JPanel();
    panel_20.setOpaque(false);
    panel_sur.add(panel_20, BorderLayout.NORTH);
    panel_20.setLayout(new BorderLayout(0, 0));
    Color azul_principal = new Color(14, 48, 170);
    JLabel lblNewLabel_6 = new JLabel("    Eventos recientes");
    lblNewLabel_6.setForeground(azul_principal);
    lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 40));
    panel_20.add(lblNewLabel_6, BorderLayout.WEST);
    
    JPanel panel_21 = new JPanel();
    panel_21.setPreferredSize(new Dimension(50, 10));
    panel_21.setOpaque(false);
    panel_sur.add(panel_21, BorderLayout.WEST);
    
    JPanel panel_22 = new JPanel();
    panel_22.setOpaque(false);
    panel_sur.add(panel_22, BorderLayout.SOUTH);
    
    JPanel panel_23 = new JPanel();
    panel_23.setPreferredSize(new Dimension(50, 10));
    panel_23.setOpaque(false);
    panel_sur.add(panel_23, BorderLayout.EAST);
    
    JPanel panel_24 = new JPanel();
    panel_24.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    panel_24.setOpaque(false);
    panel_sur.add(panel_24, BorderLayout.CENTER);
    panel_24.setLayout(new GridLayout(4, 1, 0, 5));
    
    JLabel lblNewLabel_7 = new JLabel("Nuevo registro de alumno:");
    lblNewLabel_7.setBackground(new Color(240, 240, 240));
    lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    panel_24.add(lblNewLabel_7);
    
    JLabel lblNewLabel_8 = new JLabel("Asignación reciente:");
    lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblNewLabel_8.setBackground(new Color(240, 240, 240));
    panel_24.add(lblNewLabel_8);
    
    JLabel lblNewLabel_9 = new JLabel("Actualización reciente:");
    lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblNewLabel_9.setBackground(new Color(240, 240, 240));
    panel_24.add(lblNewLabel_9);
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
