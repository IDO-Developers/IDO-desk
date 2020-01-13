package ventanas;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class acerca_de extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					acerca_de frame = new acerca_de();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public acerca_de() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 490);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		lblAcercaDe.setBounds(0, 0, 371, 28);
		contentPane.add(lblAcercaDe);

		JLabel lblSistemaAdministrativoBy = new JLabel("Sistema de pago y c\u00F3digo de matr\u00EDcula IDO 2020.");
		lblSistemaAdministrativoBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaAdministrativoBy.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblSistemaAdministrativoBy.setBounds(10, 22, 371, 32);
		contentPane.add(lblSistemaAdministrativoBy);

		JTextArea txtrTodosLosDerechos = new JTextArea();
		txtrTodosLosDerechos.setText(
				"          Todos los derechos reservados a.\r\n            Programadores y Dise\u00F1adores:\r\n   Licenciados en Inform\u00E1tica Administrativa.\r\n        Abd\u00EDas Ismael Vel\u00E1squez Gonzalez\r\n        Justin Mois\u00E9s Chac\u00F3n Ferrari\r\n        Cristian Emmanuel D\u00EDaz Rodr\u00EDguez\r\n      Contacto: krizemandiaz11@gmail.com\r\n UNAH-Tec Danl\u00ED, El Para\u00EDso Honduras 2020\r\n");
		txtrTodosLosDerechos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtrTodosLosDerechos.setBounds(59, 311, 291, 129);
		txtrTodosLosDerechos.setEditable(false);
		contentPane.add(txtrTodosLosDerechos);

		JLabel label = new JLabel("");
		label.setBounds(53, 39, 145, 141);
		contentPane.add(label);
		final ImageIcon logo2 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono2 = new ImageIcon(
				logo2.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono2);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(198, 40, 85, 127);
		contentPane.add(label_1);
		final ImageIcon logo3 = new ImageIcon(getClass().getResource("/iconos/UNAH_logo.jpg"));
		final ImageIcon icono3 = new ImageIcon(
				logo3.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
		label_1.setIcon(icono3);
		final ImageIcon logo0 = new ImageIcon(getClass().getResource("/iconos/abdias.jpeg"));
		final ImageIcon logo00 = new ImageIcon(getClass().getResource("/iconos/justin.jpeg"));
		final ImageIcon logo000 = new ImageIcon(getClass().getResource("/iconos/cris.jpeg"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 178, 351, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setToolTipText("Lic. Abd\u00EDas Ismael Vel\u00E1squez Gonzalez");
		label_2.setBounds(10, 11, 109, 101);
		panel.add(label_2);
		label_2.setBackground(Color.WHITE);
		final ImageIcon icono0 = new ImageIcon(
				logo0.getImage().getScaledInstance(label_2.getWidth(), label_2.getHeight(), Image.SCALE_DEFAULT));
		label_2.setIcon(icono0);
		
		JLabel label_3 = new JLabel("");
		label_3.setToolTipText("Lic. Justin Mois\u00E9s Chac\u00F3n Ferrari");
		label_3.setBounds(112, 11, 103, 101);
		panel.add(label_3);
		final ImageIcon icono00 = new ImageIcon(
				logo00.getImage().getScaledInstance(label_3.getWidth(), label_3.getHeight(), Image.SCALE_DEFAULT));
		label_3.setIcon(icono00);
		
		JLabel label_4 = new JLabel("");
		label_4.setToolTipText("Lic.  Cristian Emmanuel D\u00EDaz Rodr\u00EDguez");
		label_4.setBounds(225, 11, 103, 101);
		panel.add(label_4);
		final ImageIcon icono000 = new ImageIcon(
				logo000.getImage().getScaledInstance(label_4.getWidth(), label_4.getHeight(), Image.SCALE_DEFAULT));
		label_4.setIcon(icono000);
	}
}
