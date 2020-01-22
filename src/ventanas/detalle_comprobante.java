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
import javax.swing.border.LineBorder;

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
		setBounds(100, 100, 491, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInstitutoDepartamentalDe = new JLabel("Instituto Departamental de Oriente\r\n");
		lblInstitutoDepartamentalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitutoDepartamentalDe.setForeground(new Color(0, 0, 0));
		lblInstitutoDepartamentalDe.setFont(new Font("Cambria", Font.BOLD, 18));
		lblInstitutoDepartamentalDe.setBounds(0, 11, 474, 49);
		contentPane.add(lblInstitutoDepartamentalDe);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(150, 27, 160, 147);
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

		JLabel lblInformacinDelAlumno = new JLabel("Informaci\u00F3n del alumno:");
		lblInformacinDelAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacinDelAlumno.setForeground(new Color(0, 0, 128));
		lblInformacinDelAlumno.setFont(new Font("Cambria", Font.BOLD, 17));
		lblInformacinDelAlumno.setBounds(0, 172, 474, 34);
		contentPane.add(lblInformacinDelAlumno);

		JLabel lblNombreCompletoDel = new JLabel("Nombre completo del alumno:");
		lblNombreCompletoDel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCompletoDel.setFont(new Font("Cambria", Font.BOLD, 15));
		lblNombreCompletoDel.setBounds(0, 199, 474, 34);
		contentPane.add(lblNombreCompletoDel);

		JLabel lblIdentidadDelAlumno = new JLabel("Identidad del alumno:");
		lblIdentidadDelAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidadDelAlumno.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblIdentidadDelAlumno.setBounds(0, 317, 474, 21);
		contentPane.add(lblIdentidadDelAlumno);

		JLabel lblModalidad_1 = new JLabel("Modalidad:");
		lblModalidad_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblModalidad_1.setFont(new Font("Cambria", Font.BOLD, 15));
		lblModalidad_1.setBounds(0, 253, 474, 21);
		contentPane.add(lblModalidad_1);

		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCdigo.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 16));
		lblCdigo.setBounds(0, 362, 474, 21);
		contentPane.add(lblCdigo);

		JLabel label_8 = new JLabel("Hora :");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Cambria", Font.BOLD, 15));
		label_8.setBounds(275, 421, 82, 21);
		contentPane.add(label_8);

		lblHora = new JLabel("Dato");
		lblHora.setForeground(new Color(0, 0, 128));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setFont(new Font("Cambria", Font.BOLD, 15));
		lblHora.setBounds(330, 421, 135, 21);
		contentPane.add(lblHora);

		JLabel label_10 = new JLabel("Fecha :");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Cambria", Font.BOLD, 15));
		label_10.setBounds(10, 421, 71, 21);
		contentPane.add(label_10);

		lblFecha = new JLabel();
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setText("Dato");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Cambria", Font.BOLD, 15));
		lblFecha.setBounds(44, 421, 271, 21);
		contentPane.add(lblFecha);

		JLabel lblComprobanteDePrematicula = new JLabel("Comprobante para Matr\u00EDcula IDO 2020.");
		lblComprobanteDePrematicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprobanteDePrematicula.setForeground(new Color(0, 0, 0));
		lblComprobanteDePrematicula.setFont(new Font("Cambria", Font.BOLD, 16));
		lblComprobanteDePrematicula.setBounds(0, 149, 474, 34);
		contentPane.add(lblComprobanteDePrematicula);

		lblNombre = new JLabel("Dato");
		lblNombre.setForeground(new Color(0, 0, 128));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Cambria", Font.BOLD, 15));
		lblNombre.setBounds(0, 227, 474, 21);
		contentPane.add(lblNombre);

		lblModalidad = new JLabel("Dato");
		lblModalidad.setForeground(new Color(0, 0, 128));
		lblModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblModalidad.setFont(new Font("Cambria", Font.BOLD, 15));
		lblModalidad.setBounds(0, 274, 474, 21);
		contentPane.add(lblModalidad);
		
		JLabel lblDireccin = new JLabel("Lic. Suyapa Cerna");
		lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin.setForeground(Color.BLACK);
		lblDireccin.setFont(new Font("Cambria", Font.BOLD, 11));
		lblDireccin.setBounds(88, 605, 135, 20);
		contentPane.add(lblDireccin);
		
		JLabel lblMatriculaIdo = new JLabel("\r\nMatr\u00EDcula IDO 2020.\r\n");
		lblMatriculaIdo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatriculaIdo.setForeground(Color.BLACK);
		lblMatriculaIdo.setFont(new Font("PMingLiU-ExtB", Font.BOLD, 18));
		lblMatriculaIdo.setBounds(244, 453, 186, 28);
		contentPane.add(lblMatriculaIdo);
		final ImageIcon logoF = new ImageIcon(getClass().getResource("/iconos/firma_1.png"));
		
		JTextArea txtrlaEnseanzaQue = new JTextArea();
		txtrlaEnseanzaQue.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 11));
		txtrlaEnseanzaQue.setText("         \"La ense\u00F1anza que deja huella,\r\n no es la que se hace de cabeza a cabeza,\r\n            sino, de coraz\u00F3n a coraz\u00F3n.\"");
		txtrlaEnseanzaQue.setBounds(54, 445, 189, 58);
		contentPane.add(txtrlaEnseanzaQue);
		
		JLabel lblDirector = new JLabel();
		lblDirector.setBackground(Color.WHITE);
		lblDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirector.setForeground(new Color(0, 0, 128));
		lblDirector.setFont(new Font("Cambria", Font.BOLD, 15));
		lblDirector.setBounds(235, 495, 197, 99);
		contentPane.add(lblDirector);
		final ImageIcon logod = new ImageIcon(getClass().getResource("/iconos/director.jpg"));
		final ImageIcon iconod = new ImageIcon(
				logod.getImage().getScaledInstance(lblDirector.getWidth(), lblDirector.getHeight(), Image.SCALE_DEFAULT));
		lblDirector.setIcon(iconod);
		
		JLabel lblSecre = new JLabel();
		lblSecre.setBackground(Color.WHITE);
		lblSecre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecre.setForeground(new Color(0, 0, 128));
		lblSecre.setFont(new Font("Cambria", Font.BOLD, 15));
		lblSecre.setBounds(44, 514, 211, 80);
		contentPane.add(lblSecre);
		final ImageIcon logos = new ImageIcon(getClass().getResource("/iconos/secretaria.png"));
		final ImageIcon iconos = new ImageIcon(
				logos.getImage().getScaledInstance(lblSecre.getWidth(), lblSecre.getHeight(), Image.SCALE_DEFAULT));
		lblSecre.setIcon(iconos);
		
		JLabel lblMscHenriRenan = new JLabel("V\u00B0B\u00B0 MSc. Henri \u00C1lvarez ");
		lblMscHenriRenan.setHorizontalAlignment(SwingConstants.CENTER);
		lblMscHenriRenan.setForeground(Color.BLACK);
		lblMscHenriRenan.setFont(new Font("Cambria", Font.BOLD, 11));
		lblMscHenriRenan.setBounds(275, 605, 122, 20);
		contentPane.add(lblMscHenriRenan);
		
		JLabel lblSecretaria = new JLabel("Secretar\u00EDa");
		lblSecretaria.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecretaria.setForeground(Color.BLACK);
		lblSecretaria.setFont(new Font("Cambria", Font.BOLD, 11));
		lblSecretaria.setBounds(88, 618, 135, 21);
		contentPane.add(lblSecretaria);
		
		JLabel lblDireccin_1 = new JLabel("Direcci\u00F3n. ");
		lblDireccin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccin_1.setForeground(Color.BLACK);
		lblDireccin_1.setFont(new Font("Cambria", Font.BOLD, 11));
		lblDireccin_1.setBounds(275, 618, 122, 21);
		contentPane.add(lblDireccin_1);
		
		JLabel label = new JLabel("__________________");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Cambria", Font.BOLD, 15));
		label.setBounds(98, 586, 122, 21);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("__________________");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Cambria", Font.BOLD, 15));
		label_2.setBounds(233, 586, 199, 21);
		contentPane.add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(98, 335, 282, 28);
		contentPane.add(panel);
		panel.setLayout(null);
		
				lblIdentidad = new JLabel("Dato");
				lblIdentidad.setBounds(97, 0, 185, 28);
				panel.add(lblIdentidad);
				lblIdentidad.setForeground(new Color(0, 100, 0));
				lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
				lblIdentidad.setFont(new Font("Cambria", Font.BOLD, 15));
				
				JLabel lblUsuario = new JLabel("Usuario:");
				lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
				lblUsuario.setForeground(new Color(0, 0, 128));
				lblUsuario.setFont(new Font("Cambria", Font.BOLD, 15));
				lblUsuario.setBounds(10, 0, 91, 28);
				panel.add(lblUsuario);
				
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(98, 380, 282, 28);
				contentPane.add(panel_1);
				
						lblCodigo = new JLabel("Dato");
						lblCodigo.setBounds(98, 0, 184, 28);
						panel_1.add(lblCodigo);
						lblCodigo.setForeground(new Color(0, 100, 0));
						lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
						lblCodigo.setFont(new Font("Cambria", Font.BOLD, 15));
						
						JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
						lblContrasea.setHorizontalAlignment(SwingConstants.LEFT);
						lblContrasea.setForeground(new Color(0, 0, 128));
						lblContrasea.setFont(new Font("Cambria", Font.BOLD, 15));
						lblContrasea.setBounds(10, 0, 91, 28);
						panel_1.add(lblContrasea);
						
						JLabel lblNotaParaMatricular = new JLabel("Nota: para matricular ingrese a www.ido.edu.hn");
						lblNotaParaMatricular.setForeground(new Color(0, 0, 0));
						lblNotaParaMatricular.setHorizontalAlignment(SwingConstants.CENTER);
						lblNotaParaMatricular.setFont(new Font("Cambria", Font.BOLD, 13));
						lblNotaParaMatricular.setBounds(0, 636, 474, 34);
						contentPane.add(lblNotaParaMatricular);
						
						JLabel lblCredencialesParaMatricular = new JLabel("Credenciales para matricular: ");
						lblCredencialesParaMatricular.setHorizontalAlignment(SwingConstants.CENTER);
						lblCredencialesParaMatricular.setForeground(new Color(0, 0, 0));
						lblCredencialesParaMatricular.setFont(new Font("Cambria", Font.BOLD, 16));
						lblCredencialesParaMatricular.setBounds(0, 285, 474, 34);
						contentPane.add(lblCredencialesParaMatricular);
		final ImageIcon logose = new ImageIcon(getClass().getResource("/iconos/logose.png"));
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
