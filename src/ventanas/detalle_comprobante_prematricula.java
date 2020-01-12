package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class detalle_comprobante_prematricula extends JFrame implements Printable {

	private JPanel contentPane;
	public JButton btnImprimir;

	public static JLabel lblNombre = null;
	public static JLabel lblIdentidad = null;
	public static JLabel lblModalidad = null;
	public static JLabel lblHora = null;
	public static JLabel lblFecha = null;
	public static JLabel lblCodigo = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detalle_comprobante_prematricula frame = new detalle_comprobante_prematricula();
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
	public detalle_comprobante_prematricula() {
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Instituto Departamental de Oriente, \r\nPre-Matricula 2020.\r\n");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label.setBounds(10, 25, 464, 28);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(136, 21, 216, 212);
		contentPane.add(label_1);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(
				logo.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
		label_1.setIcon(icono);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImprimir.setVisible(false);
				imprimirInformacionDelAlumno();
			}
		});
		btnImprimir.setFont(new Font("Calibri", Font.BOLD, 14));
		btnImprimir.setBackground(new Color(102, 205, 170));
		btnImprimir.setBounds(197, 0, 99, 21);
		contentPane.add(btnImprimir);

		JLabel label_2 = new JLabel("Informaci\u00F3n del alumno :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(72, 61, 139));
		label_2.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_2.setBounds(25, 231, 428, 28);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Nombre completo del alumno :");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_3.setBounds(127, 274, 225, 21);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("Identidad del alumno :");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_4.setBounds(127, 328, 225, 21);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("Modalidad :");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_5.setBounds(127, 379, 225, 21);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("Codigo :");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_6.setBounds(127, 431, 225, 21);
		contentPane.add(label_6);

		JLabel label_8 = new JLabel("Hora :");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_8.setBounds(21, 501, 60, 28);
		contentPane.add(label_8);

		lblHora = new JLabel("Dato");
		lblHora.setForeground(new Color(72, 61, 139));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 15));
		lblHora.setBounds(53, 501, 137, 31);
		contentPane.add(lblHora);

		JLabel label_10 = new JLabel("Fecha :");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_10.setBounds(188, 501, 71, 28);
		contentPane.add(label_10);

		lblFecha = new JLabel();
		lblFecha.setForeground(new Color(72, 61, 139));
		lblFecha.setText("Dato");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 15));
		lblFecha.setBounds(214, 500, 278, 32);
		contentPane.add(lblFecha);

		JLabel lblComprobanteDePrematicula = new JLabel("Comprobante de Pre-Matricula IDO 2020.");
		lblComprobanteDePrematicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprobanteDePrematicula.setForeground(Color.BLACK);
		lblComprobanteDePrematicula.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblComprobanteDePrematicula.setBounds(10, 191, 464, 42);
		contentPane.add(lblComprobanteDePrematicula);

		lblNombre = new JLabel("Dato");
		lblNombre.setForeground(new Color(72, 61, 139));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblNombre.setBounds(25, 298, 433, 21);
		contentPane.add(lblNombre);

		lblIdentidad = new JLabel("Dato");
		lblIdentidad.setForeground(new Color(72, 61, 139));
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblIdentidad.setBounds(25, 352, 433, 21);
		contentPane.add(lblIdentidad);

		lblModalidad = new JLabel("Dato");
		lblModalidad.setForeground(new Color(72, 61, 139));
		lblModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblModalidad.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblModalidad.setBounds(25, 403, 433, 21);
		contentPane.add(lblModalidad);

		lblCodigo = new JLabel("Dato");
		lblCodigo.setForeground(new Color(72, 61, 139));
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblCodigo.setBounds(25, 455, 433, 21);
		contentPane.add(lblCodigo);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex == 0) {
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			contentPane.printAll(graphics);
			contentPane.printAll(graphics);
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}

	public void imprimirInformacionDelAlumno() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setPrintable(this);
		try {
			printerJob.print();
		} catch (PrinterException ex) {
			System.out.println("Error:" + ex);
		}
	}

}
