package views;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

import controllers.AuthController;

import javax.swing.JButton;

public class AuthView extends JFrame {

	Color azul_hover = new Color(53,82,189);
	Color azul_principal = new Color(14, 48, 170);
	private JTextField textField;
	private JPasswordField textField_3;
	
	private AuthController controller;
	
	public AuthView() {
	}

	public void login() {
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.messageForeground", new Color(14, 48, 170));
		UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
		UIManager.put("Button.background", new Color(14, 48, 170));
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.setTitle("Educadex");
		this.setBackground(new Color(255, 255, 255));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(AuthView.class.getResource("/resources/logo_virrete-32x32.png")));
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(1200, 800));
		this.setMaximumSize(new Dimension(1920, 1080));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel izquierdo = new JPanel();
		izquierdo.setBackground(new Color(14, 48, 170));
		this.getContentPane().add(izquierdo);
		izquierdo.setLayout(new BorderLayout(0, 0));

		JLabel icono = new JLabel() {
			Image img = new ImageIcon(AuthView.class.getResource("/resources/logo_educadex.png")).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				int width = getWidth();
				int height = getHeight();
				int imgW = img.getWidth(this);
				int imgH = img.getHeight(this);
				double scale = Math.max((double) width / imgW, (double) height / imgH);
				int newW = (int) (imgW * scale);
				int newH = (int) (imgH * scale);
				int x = (width - newW) / 2;
				int y = (height - newH) / 2;
				g2.drawImage(img, x, y, newW, newH, this);
			}
		};
		izquierdo.add(icono, BorderLayout.CENTER);

		JPanel derecho = new JPanel();
		derecho.setOpaque(false);
		derecho.setRequestFocusEnabled(false);
		derecho.setFocusable(false);
		derecho.setBackground(new Color(255, 255, 255));
		this.getContentPane().add(derecho);
		
		GridBagLayout gbl_derecho = new GridBagLayout();
		gbl_derecho.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_derecho.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_derecho.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_derecho.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		derecho.setLayout(gbl_derecho);

		JLabel lblNewLabel = new JLabel("Iniciar sesión");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 7;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 5;
		derecho.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(14, 38, 145));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 48));

		JLabel lblNewLabel_1 = new JLabel("Ingresa tus credenciales para acceder");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 7;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 8;
		derecho.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 12;
		derecho.add(lblNewLabel_2, gbc_lblNewLabel_2);

		ImageIcon iconoOriginal = new ImageIcon(AuthView.class.getResource("/resources/usuario_logo.png"));
		Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon iconoFinal = new ImageIcon(imagenEscalada);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(iconoFinal);

		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 13;
		derecho.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(7, 40));
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(203, 213, 225), 2, true));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 13;
		derecho.add(textField, gbc_textField);

		JLabel lblNewLabel_2_1 = new JLabel("Contraseña");
		lblNewLabel_2_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1.gridwidth = 3;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 4;
		gbc_lblNewLabel_2_1.gridy = 15;
		derecho.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

		ImageIcon iconoOriginal4 = new ImageIcon(AuthView.class.getResource("/resources/contraseña_logo.png"));
		Image imagenEscalada4 = iconoOriginal4.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon iconoFinal4 = new ImageIcon(imagenEscalada4);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(iconoFinal4);

		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 16;
		derecho.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_3 = new JPasswordField();
		textField_3.setPreferredSize(new Dimension(7, 40));
		textField_3.setColumns(10);
		textField_3.setBorder(new LineBorder(new Color(203, 213, 225), 2, true));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 7;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 16;
		derecho.add(textField_3, gbc_textField_3);

		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 7;
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 4;
		gbc_panel_9.gridy = 18;

		JButton btnNewButton = new JButton("Iniciar sesión");
		btnNewButton.setPreferredSize(new Dimension(95, 15));
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnNewButton.setBackground(new Color(14, 48, 170));
		btnNewButton.addActionListener(e->{
			String usuario = textField.getText();
		    String password = new String(textField_3.getPassword());
		    controller=new AuthController();
		    if(!controller.autenticar(usuario, password)) {
		    	
		    	textField_3.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));
		    	
		    	textField.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));
		    	
		    	mostrarMensaje("Usuario o contraseña incorrectos");
		    }else {
				textField_3.setBorder(new LineBorder(new Color(203, 213, 225), 2, true));
				textField.setBorder(new LineBorder(new Color(203, 213, 225), 2, true));
				textField.setForeground(new Color(0, 0, 0));
				textField_3.setForeground(new Color(0, 0, 0));
				
				this.dispose();
		    }
		});
		
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
	    
		derecho.add(btnNewButton, gbc_panel_9);
		
		this.setVisible(true);
		
		
	}
	
	public void mostrarMensaje(String mensaje) {
	    JOptionPane.showMessageDialog(this, mensaje);
	}
	
	
	
	
}