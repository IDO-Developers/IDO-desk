package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class detalle_comprobante extends JFrame implements Printable {

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
			@Override
			public void run() {
				try {
					detalle_comprobante frame = new detalle_comprobante();
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
	public detalle_comprobante() {
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 675);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInstitutoDepartamentalDe = new JLabel("Instituto Departamental de Oriente, \r\nMatricula 2020.\r\n");
		lblInstitutoDepartamentalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitutoDepartamentalDe.setForeground(Color.BLACK);
		lblInstitutoDepartamentalDe.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));
		lblInstitutoDepartamentalDe.setBounds(0, 25, 474, 28);
		contentPane.add(lblInstitutoDepartamentalDe);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(148, 38, 184, 175);
		contentPane.add(label_1);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(
				logo.getImage().getScaledInstance(label_1.getWidth(), label_1.getHeight(), Image.SCALE_DEFAULT));
		label_1.setIcon(icono);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnImprimir.setVisible(false);
				imprimirInformacionDelAlumno();
				
			}
		});
		btnImprimir.setFont(new Font("Calibri", Font.BOLD, 14));
		btnImprimir.setBackground(new Color(102, 205, 170));
		btnImprimir.setBounds(196, 6, 99, 21);
		contentPane.add(btnImprimir);

		JLabel label_2 = new JLabel("Informaci\u00F3n del alumno :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Cambria", Font.BOLD, 17));
		label_2.setBounds(0, 231, 474, 28);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Nombre completo del alumno :");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		label_3.setBounds(0, 274, 474, 21);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("Identidad del alumno :");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		label_4.setBounds(0, 328, 474, 21);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("Modalidad :");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		label_5.setBounds(0, 379, 474, 21);
		contentPane.add(label_5);

		JLabel lblCdigo = new JLabel("C\u00F3digo : ");
		lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdigo.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblCdigo.setBounds(0, 431, 474, 21);
		contentPane.add(lblCdigo);

		JLabel label_8 = new JLabel("Hora :");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		label_8.setBounds(0, 488, 82, 21);
		contentPane.add(label_8);

		lblHora = new JLabel("Dato");
		lblHora.setForeground(new Color(0, 0, 128));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Cambria", Font.BOLD, 15));
		lblHora.setBounds(55, 488, 122, 21);
		contentPane.add(lblHora);

		JLabel label_10 = new JLabel("Fecha :");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		label_10.setBounds(169, 488, 71, 21);
		contentPane.add(label_10);

		lblFecha = new JLabel();
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setText("Dato");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Cambria", Font.BOLD, 15));
		lblFecha.setBounds(203, 488, 271, 21);
		contentPane.add(lblFecha);

		JLabel lblComprobanteDePrematicula = new JLabel("Comprobante de Matricula IDO 2020.");
		lblComprobanteDePrematicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprobanteDePrematicula.setForeground(Color.BLACK);
		lblComprobanteDePrematicula.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblComprobanteDePrematicula.setBounds(0, 191, 474, 42);
		contentPane.add(lblComprobanteDePrematicula);

		lblNombre = new JLabel("Dato");
		lblNombre.setForeground(new Color(0, 0, 128));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Cambria", Font.BOLD, 15));
		lblNombre.setBounds(0, 298, 474, 21);
		contentPane.add(lblNombre);

		lblIdentidad = new JLabel("Dato");
		lblIdentidad.setForeground(new Color(0, 0, 128));
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setFont(new Font("Cambria", Font.BOLD, 15));
		lblIdentidad.setBounds(0, 352, 474, 21);
		contentPane.add(lblIdentidad);

		lblModalidad = new JLabel("Dato");
		lblModalidad.setForeground(new Color(0, 0, 128));
		lblModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblModalidad.setFont(new Font("Cambria", Font.BOLD, 15));
		lblModalidad.setBounds(0, 403, 474, 21);
		contentPane.add(lblModalidad);

		lblCodigo = new JLabel("Dato");
		lblCodigo.setForeground(new Color(0, 0, 128));
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Cambria", Font.BOLD, 15));
		lblCodigo.setBounds(0, 455, 474, 21);
		contentPane.add(lblCodigo);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n. \r\n");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setForeground(Color.BLACK);
		lblDireccin.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblDireccin.setBounds(236, 588, 92, 32);
		contentPane.add(lblDireccin);
		
		JLabel lblMatriculaIdo = new JLabel("\r\nMatricula IDO 2020.\r\n");
		lblMatriculaIdo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatriculaIdo.setForeground(Color.BLACK);
		lblMatriculaIdo.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));
		lblMatriculaIdo.setBounds(10, 589, 216, 28);
		contentPane.add(lblMatriculaIdo);
		final ImageIcon logoF = new ImageIcon(getClass().getResource("/iconos/firma_1.png"));
		
		JTextArea txtrlaEnseanzaQue = new JTextArea();
		txtrlaEnseanzaQue.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 11));
		txtrlaEnseanzaQue.setText("         \"La ense\u00F1anza que deja huella\r\n no es la que se hace de cabeza a cabeza\r\n            sino de coraz\u00F3n a coraz\u00F3n.\"");
		txtrlaEnseanzaQue.setBounds(22, 533, 203, 58);
		contentPane.add(txtrlaEnseanzaQue);
		
		JLabel lblSecretaria = new JLabel("Secretaria. ");
		lblSecretaria.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecretaria.setForeground(Color.BLACK);
		lblSecretaria.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblSecretaria.setBounds(356, 588, 92, 32);
		contentPane.add(lblSecretaria);
		
		JLabel lblDirector = new JLabel();
		lblDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirector.setForeground(new Color(0, 0, 128));
		lblDirector.setFont(new Font("Cambria", Font.BOLD, 15));
		lblDirector.setBounds(213, 520, 122, 71);
		contentPane.add(lblDirector);
		final ImageIcon logod = new ImageIcon(getClass().getResource("/iconos/director.png"));
		final ImageIcon iconod = new ImageIcon(
				logod.getImage().getScaledInstance(lblDirector.getWidth(), lblDirector.getHeight(), Image.SCALE_DEFAULT));
		lblDirector.setIcon(iconod);
		
		JLabel lblSecre = new JLabel();
		lblSecre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecre.setForeground(new Color(0, 0, 128));
		lblSecre.setFont(new Font("Cambria", Font.BOLD, 15));
		lblSecre.setBounds(338, 520, 122, 71);
		contentPane.add(lblSecre);
		final ImageIcon logos = new ImageIcon(getClass().getResource("/iconos/secretaria.png"));
		final ImageIcon iconos = new ImageIcon(
				logos.getImage().getScaledInstance(lblSecre.getWidth(), lblSecre.getHeight(), Image.SCALE_DEFAULT));
		lblSecre.setIcon(iconos);
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
