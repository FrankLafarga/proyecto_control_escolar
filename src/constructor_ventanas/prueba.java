package constructor_ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import views.AuthView;

public class prueba {
	private JTextField textField;
	private JPasswordField textField_3;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba window = new prueba();
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
	public prueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		UIManager.put("OptionPane.messageForeground", new Color(14, 48, 170));
		UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
		UIManager.put("Button.background", new Color(14, 48, 170));
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 14));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setTitle("Educadex");
		frame.setBackground(new Color(255, 255, 255));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AuthView.class.getResource("/resources/logo_virrete-32x32.png")));
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.setMaximumSize(new Dimension(1920, 1080));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel izquierdo = new JPanel();
		izquierdo.setBackground(new Color(14, 48, 170));
		izquierdo.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(izquierdo);

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
		frame.getContentPane().add(derecho);
		
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
		textField_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBorder(new LineBorder(new Color(203, 213, 225), 2, true));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 7;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 16;
		derecho.add(textField_3, gbc_textField_3);

		JPanel panel_9 = new JPanel();
		panel_9.setFocusable(false);
		panel_9.setBorder(null);
		panel_9.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 7;
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 4;
		gbc_panel_9.gridy = 18;
		derecho.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Iniciar sesión");
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		btnNewButton.setBackground(new Color(14, 48, 170));
		
		
		panel_9.add(btnNewButton);
				
		
	}

}
