package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constructor_ventanas.App;

public class AppView extends JFrame{
	
	Color azul_principal = new Color(14, 48, 170);
	Color azul_hover = new Color(53,82,189);

	public void inicio() {
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
	btnNewButton.setIcon(new ImageIcon(App.class.getResource("/resources/logo_virrete-32x32.png")));
	btnNewButton.setForeground(new Color(255, 255, 255));
	btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	btnNewButton.setContentAreaFilled(false);
	btnNewButton.setBorderPainted(false);
	btnNewButton.addActionListener(e->{
		int opcion = JOptionPane.showConfirmDialog(this,
			"¿Está seguro que desea salir del sistema de control escolar?\nTendrá que volver a ingresar sus credenciales.",
			"Confirmar salida",JOptionPane.YES_NO_OPTION);

			if (opcion == JOptionPane.YES_OPTION) {
			    System.exit(0);
			}	
	});
	panel_1_1.add(btnNewButton);
	
	JPanel panel_4 = new JPanel();
	panel_4.setOpaque(false);
	panel_1_1.add(panel_4);
	
	this.setVisible(true);
	}
}
