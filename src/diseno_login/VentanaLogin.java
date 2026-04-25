package diseno_login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

public class VentanaLogin {

	private JFrame frmEducadex;
	Color azul_titulos= new Color(14,48,170);
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frmEducadex.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEducadex = new JFrame();
		frmEducadex.setTitle("Educadex");
		frmEducadex.setBackground(new Color(255, 255, 255));
		frmEducadex.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/resources/logo_virrete-32x32.png")));
		frmEducadex.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmEducadex.setMinimumSize(new Dimension(800, 600));
		frmEducadex.setMaximumSize(new Dimension(1920, 1080));
		frmEducadex.setBounds(100, 100, 1920,1080);
		frmEducadex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEducadex.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel izquierdo = new JPanel();
		izquierdo.setBackground(new Color(14, 48, 170));
		frmEducadex.getContentPane().add(izquierdo);
		izquierdo.setLayout(new BorderLayout(0, 0));
		
		JLabel icono = new JLabel() {
		    Image img = new ImageIcon(
		        VentanaLogin.class.getResource("/resources/logo_educadex.png")
		    ).getImage();

		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);

		        Graphics2D g2 = (Graphics2D) g;
		        g2.setRenderingHint(
		            RenderingHints.KEY_INTERPOLATION,
		            RenderingHints.VALUE_INTERPOLATION_BILINEAR
		        );

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
		frmEducadex.getContentPane().add(derecho);
		derecho.setLayout(new GridLayout(12, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setFocusable(false);
		panel.setVisible(false);
		derecho.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFocusable(false);
		panel_1.setOpaque(false);
		panel_1.setVisible(false);
		derecho.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Iniciar sesión");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(14, 38, 145));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 48));
		derecho.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingresa tus credenciales para acceder");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		derecho.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		derecho.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		JPanel panel_3 = new JPanel();
		derecho.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_3.add(panel_5, gbc_panel_5);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(7, 40));
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(221, 221, 221), 1, true));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 14;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 9;
		gbc_textField.gridy = 0;
		panel_3.add(textField, gbc_textField);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 31;
		gbc_panel_4.gridy = 0;
		panel_3.add(panel_4, gbc_panel_4);
	
		frmEducadex.repaint();
		frmEducadex.setVisible(true);
	}
}
