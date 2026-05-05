package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import application.EducadexMain;
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
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        panel_1.add(lblNewLabel, BorderLayout.CENTER);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setPreferredSize(new Dimension(0, 50));
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/logo_virrete-32x32.png")));
        panel_1.add(lblNewLabel_1, BorderLayout.NORTH);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setPreferredSize(new Dimension(10, 120));
        panel_1_1.setBackground(azul_principal);
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
        btnNewButton.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_cerrar.png")));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);

        btnNewButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setBorderPainted(true);
                btnNewButton.setOpaque(true);
                btnNewButton.setBackground(azul_hover);
            }
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
            public void mouseEntered(MouseEvent e) {
                b = (JButton) e.getSource();
                if (b != botonActivo) {
                    b.setBorderPainted(true);
                    b.setOpaque(true);
                    b.setBackground(azul_hover);
                }
            }
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

        botonInicio.addActionListener(e -> cambiarVista(new JPanel(), "Inicio", "Bienvenido al sistema de control escolar"));
        botonDocentes.addActionListener(e -> cambiarVista(new DocentesView(this), "Docentes", "Gestion integral de docentes en el sistema"));
        botonAlumnos.addActionListener(e -> cambiarVista(new AlumnosView(this), "Alumnos", "Gestion integral de alumnos en el sistema"));
        botonGrupos.addActionListener(e -> cambiarVista(new GruposView(this), "Grupos", "Gestion integral de grupos en el sistema"));
        botonAsignaturas.addActionListener(e -> cambiarVista(new AsignaturasView(this), "Asignaturas", "Gestion integral de asignaturas en el sistema"));

        botonInicio.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_inicio_dorado.png")));
        botonInicio.setBackground(azul_hover);
        botonInicio.setFont(new Font("Segoe UI", Font.BOLD, 24));
        botonInicio.setForeground(dorado);
        botonInicio.setBorderPainted(true);
        botonActivo = botonInicio;

        panel_8.add(botonInicio);
        panel_8.add(botonDocentes);
        panel_8.add(botonAlumnos);
        panel_8.add(botonGrupos);
        panel_8.add(botonAsignaturas);

        panel_9 = new JPanel();
        this.getContentPane().add(panel_9, BorderLayout.CENTER);
        panel_9.setLayout(new BorderLayout(0, 0));

        JPanel panel_10 = new JPanel();
        panel_10.setBackground(Color.WHITE);
        panel_10.setPreferredSize(new Dimension(10, 120));
        panel_9.add(panel_10, BorderLayout.NORTH);
        panel_10.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_13 = new JPanel();
        panel_13.setBackground(Color.WHITE);
        panel_10.add(panel_13);
        panel_13.setLayout(new GridLayout(0, 1, 0, 0));

        titulo_panel = new JLabel("    Inicio");
        titulo_panel.setVerticalAlignment(SwingConstants.BOTTOM);
        titulo_panel.setHorizontalAlignment(SwingConstants.LEFT);
        titulo_panel.setForeground(azul_principal);
        titulo_panel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        panel_13.add(titulo_panel);

        descripcion_titulo = new JLabel("       Bienvenido al sistema de control escolar");
        descripcion_titulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        panel_13.add(descripcion_titulo);

        JPanel panel_12 = new JPanel(new BorderLayout());
        panel_12.setBackground(Color.WHITE);
        panel_10.add(panel_12);

        JPanel panel_15 = new JPanel(new GridLayout(2,1));
        panel_15.setBackground(Color.WHITE);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(Color.WHITE);

        contenedor.add(panel_15, BorderLayout.WEST);

        JPanel separadorDerecha = new JPanel();
        separadorDerecha.setPreferredSize(new Dimension(20, 0));
        separadorDerecha.setBackground(Color.WHITE);
        contenedor.add(separadorDerecha, BorderLayout.EAST);

        panel_12.add(contenedor, BorderLayout.EAST);

        JLabel iconUser = new JLabel();
        iconUser.setHorizontalAlignment(SwingConstants.CENTER);
        Image img = new ImageIcon(AppView.class.getResource("/resources/user_online.png"))
                .getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        iconUser.setIcon(new ImageIcon(img));
        panel_15.add(iconUser);

        lblNewLabel_4 = new JLabel("nombre usuario");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        panel_15.add(lblNewLabel_4);

        panel_centro = new JPanel();
        panel_9.add(panel_centro, BorderLayout.CENTER);

        cambiarVista(new JPanel(), "Inicio", "Bienvenido al sistema de control escolar");

        this.setVisible(true);
    }

    public void cambiarVista(JPanel vista, String titulo, String descripcion){
        panel_9.remove(panel_centro);
        panel_centro = vista;
        panel_9.add(panel_centro, BorderLayout.CENTER);
        titulo_panel.setText("    " + titulo);
        descripcion_titulo.setText("       " + descripcion);
        panel_9.revalidate();
        panel_9.repaint();
    }

    private JButton crearBoton(String texto, MouseAdapter hover, ActionListener action) {
        JButton b = new JButton(texto);
        b.setIconTextGap(20);
        if(texto.equals("Inicio")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_inicio.png")));
        } else if(texto.equals("Docentes")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_docentes.png")));
        } else if(texto.equals("Alumnos")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_alumnos.png")));
        } else if(texto.equals("Grupos")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_grupos.png")));
        } else if(texto.equals("Asignaturas")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_asignaturas.png")));
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
                botonActivo.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_inicio.png")));
            } else if (botonActivo.getText().equals("Docentes")) {
                botonActivo.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_docentes.png")));
            } else if (botonActivo.getText().equals("Alumnos")) {
                botonActivo.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_alumnos.png")));
            } else if (botonActivo.getText().equals("Grupos")) {
                botonActivo.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_grupos.png")));
            } else if (botonActivo.getText().equals("Asignaturas")) {
                botonActivo.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_asignaturas.png")));
            }

            botonActivo.setBackground(azul_principal);
            botonActivo.setForeground(Color.WHITE);
            botonActivo.setFont(new Font("Segoe UI", Font.PLAIN, 24));
            botonActivo.setBorderPainted(false);
        }

        botonActivo = b;

        if (b.getText().equals("Inicio")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_inicio_dorado.png")));
        } else if (b.getText().equals("Docentes")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_docentes_dorado.png")));
        } else if (b.getText().equals("Alumnos")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_alumnos_dorado.png")));
        } else if (b.getText().equals("Grupos")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_grupos_dorado.png")));
        } else if (b.getText().equals("Asignaturas")) {
            b.setIcon(new ImageIcon(EducadexMain.class.getResource("/resources/icono_asignaturas_dorado.png")));
        }

        b.setBackground(azul_hover);
        b.setFont(new Font("Segoe UI", Font.BOLD, 24));
        b.setForeground(dorado);
        b.setBorderPainted(true);
    }

    public void setController(AppController controller) {
        this.controller = controller;
    }
}