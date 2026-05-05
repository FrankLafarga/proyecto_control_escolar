package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PanelBotones extends JPanel implements TableCellRenderer {

    public PanelBotones() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JButton btnVer = new JButton("");
        ImageIcon iconoVer = new ImageIcon(getClass().getResource("/resources/eye.png"));
        btnVer.setIcon(new ImageIcon(iconoVer.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        btnVer.setPreferredSize(new Dimension(25, 25));
        btnVer.setBorderPainted(false);
        btnVer.setContentAreaFilled(false);
        btnVer.setFocusPainted(false);
        btnVer.setOpaque(false);

        JButton btnEditar = new JButton("");
        ImageIcon iconoEditar = new ImageIcon(getClass().getResource("/resources/square-pen.png"));
        btnEditar.setIcon(new ImageIcon(iconoEditar.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        btnEditar.setPreferredSize(new Dimension(25, 25));
        btnEditar.setBorderPainted(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setFocusPainted(false);
        btnEditar.setOpaque(false);

        JButton btnEliminar = new JButton("");
        ImageIcon iconoEliminar = new ImageIcon(getClass().getResource("/resources/trash.png"));
        btnEliminar.setIcon(new ImageIcon(iconoEliminar.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        btnEliminar.setPreferredSize(new Dimension(25, 25));
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setOpaque(false);

        add(btnVer);
        add(btnEditar);
        add(btnEliminar);
    }

    public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
        setBackground(Color.WHITE);
        return this;
    }
}