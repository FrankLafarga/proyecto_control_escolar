package views;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controllers.InicioController;

import java.awt.*;

public class InicioView extends JPanel {
    
	private InicioController controller;
    private AppView app;
    private int lblDocentes;
    private int lblAlumnos;
    private int lblGrupos;
    private int lblAsignaturas;

    public InicioView(AppView app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        controller=new InicioController(this);
        Color dorado = new Color(208, 135, 0);
        Color verde_lima = new Color(3, 233, 38);
        Color rojo_claro = new Color(255, 77, 77);
        Color azul_celeste = new Color(61, 161, 255);
        Color azul_principal = new Color(14, 48, 170);

        JPanel panel_centro = new JPanel();
        panel_centro.setOpaque(false);
        add(panel_centro, BorderLayout.CENTER);
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
        panel_centro_docentes.setOpaque(false);
        panel_centro_docentes.setBorder(new LineBorder(dorado, 3, true));
        recuadro_docentes.add(panel_centro_docentes, BorderLayout.CENTER);
        panel_centro_docentes.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel lblNewLabel_10 = new JLabel("  ");
        lblNewLabel_10.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_10.setHorizontalTextPosition(SwingConstants.LEFT);
        lblNewLabel_10.setIconTextGap(5);
        ImageIcon iconoOriginalDocentes = new ImageIcon(InicioView.class.getResource("/resources/Logo Docentes.png"));
        Image imagenEscaladaDocentes = iconoOriginalDocentes.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblNewLabel_10.setIcon(new ImageIcon(imagenEscaladaDocentes));
        panel_centro_docentes.add(lblNewLabel_10);

        JPanel panel_25 = new JPanel();
        panel_25.setOpaque(false);
        panel_centro_docentes.add(panel_25);

        JLabel lblNewLabel_11 = new JLabel("  "+lblDocentes);
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
        panel_centro_alumnos.setOpaque(false);
        panel_centro_alumnos.setBorder(new LineBorder(verde_lima, 3, true));
        recuadro_alumnos.add(panel_centro_alumnos, BorderLayout.CENTER);
        panel_centro_alumnos.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel lblNewLabel_10_1 = new JLabel("  ");
        lblNewLabel_10_1.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblNewLabel_10_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_10_1.setIconTextGap(5);
        lblNewLabel_10_1.setHorizontalTextPosition(SwingConstants.LEFT);
        ImageIcon iconoOriginalAlumnos = new ImageIcon(InicioView.class.getResource("/resources/Logo Verde.png"));
        Image imagenEscaladaAlumnos = iconoOriginalAlumnos.getImage().getScaledInstance(60, 55, Image.SCALE_SMOOTH);
        lblNewLabel_10_1.setIcon(new ImageIcon(imagenEscaladaAlumnos));
        panel_centro_alumnos.add(lblNewLabel_10_1);

        JPanel panel_25_1 = new JPanel();
        panel_25_1.setOpaque(false);
        panel_centro_alumnos.add(panel_25_1);

        JLabel lblNewLabel_11_1 = new JLabel("  "+lblAlumnos);
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
        panel_centro_grupos.setOpaque(false);
        panel_centro_grupos.setBorder(new LineBorder(rojo_claro, 3, true));
        recuadro_grupos.add(panel_centro_grupos, BorderLayout.CENTER);
        panel_centro_grupos.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel lblNewLabel_10_3 = new JLabel("  ");
        ImageIcon iconoOriginal = new ImageIcon(InicioView.class.getResource("/resources/Logo Rojo.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblNewLabel_10_3.setIcon(new ImageIcon(imagenEscalada));
        lblNewLabel_10_3.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_10_3.setIconTextGap(5);
        lblNewLabel_10_3.setHorizontalTextPosition(SwingConstants.LEFT);
        panel_centro_grupos.add(lblNewLabel_10_3);

        JPanel panel_25_3 = new JPanel();
        panel_25_3.setOpaque(false);
        panel_centro_grupos.add(panel_25_3);

        JLabel lblNewLabel_11_3 = new JLabel("  "+lblGrupos);
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
        panel_centro_asignaturas.setOpaque(false);
        panel_centro_asignaturas.setBorder(new LineBorder(azul_celeste, 3, true));
        recuadro_asignaturas.add(panel_centro_asignaturas, BorderLayout.CENTER);
        panel_centro_asignaturas.setLayout(new GridLayout(4, 0, 0, 0));

        JLabel lblNewLabel_10_2 = new JLabel("  ");
        lblNewLabel_10_2.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_10_2.setIconTextGap(5);
        lblNewLabel_10_2.setHorizontalTextPosition(SwingConstants.LEFT);
        ImageIcon iconoOriginalAsignaturas = new ImageIcon(InicioView.class.getResource("/resources/Logo Azul.png"));
        Image imagenEscaladaAsignaturas = iconoOriginalAsignaturas.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        lblNewLabel_10_2.setIcon(new ImageIcon(imagenEscaladaAsignaturas));
        panel_centro_asignaturas.add(lblNewLabel_10_2);

        JPanel panel_25_2 = new JPanel();
        panel_25_2.setOpaque(false);
        panel_centro_asignaturas.add(panel_25_2);

        JLabel lblNewLabel_11_2 = new JLabel("  "+lblAsignaturas);
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
    
    public int getLblDocentes() { return lblDocentes; }
    public int getLblAlumnos() { return lblAlumnos; }
    public int getLblGrupos() { return lblGrupos; }
    public int getLblAsignaturas() { return lblAsignaturas; }

	public void setLblDocentes(int lblDocentes) {
		this.lblDocentes = lblDocentes;
	}

	public void setLblAlumnos(int lblAlumnos) {
		this.lblAlumnos = lblAlumnos;
	}

	public void setLblGrupos(int lblGrupos) {
		this.lblGrupos = lblGrupos;
	}

	public void setLblAsignaturas(int lblAsignaturas) {
		this.lblAsignaturas = lblAsignaturas;
	}
}