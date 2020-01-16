package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import clases.alumnos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.conexion;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ScrollPaneConstants;

public class registro_alumnos extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNombre_Alumno;
	public static JTextField txtIdentidad_Alumno;
	public static JTextField txtCodigo_Matricula;
	public JButton btnBuscar;
	public JPanel panelInformacion;

	public static String nombres = null;
	public static String apellidos = null;
	public static String identidad = null;
	public static String codigo = null;
	public static String modalidad = null;
	public static String pago = null;
	public static String recibo = null;

	public static JTextField txtBuscar;
	public static JTextField txtGrado;
	public static JTextField txtModalidad;
	public static JTextField txtVerificacion;
	public static JTextField txtRecibo;
	public static JLabel lblFecha;

	public JScrollPane barraAlumno;
	public JTable tablaAlumno;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					registro_alumnos principal = new registro_alumnos();
					principal.setVisible(true);
					principal.setLocationRelativeTo(null);
					principal.setVisible(true);
					principal.construirTabla();
					Timer time = new Timer();
					time.schedule(principal.tarea, 0, 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registro_alumnos() {
		setType(Type.UTILITY);
		setResizable(false);
		setBounds(100, 100, 814, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Sistema de busqueda de codigos de matricula. IDO 2020");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/iconos/logo_ido.png")));
		contentPane.setLayout(null);

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 788, 309);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBuscarInformacionDe = new JLabel("Buscar informaci\u00F3n de la matr\u00EDcula del alumno :");
		lblBuscarInformacionDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscarInformacionDe.setFont(new Font("Serif", Font.BOLD, 18));
		lblBuscarInformacionDe.setBounds(10, 31, 654, 30);
		panel.add(lblBuscarInformacionDe);

		MaskFormatter formato1 = null;
		try {
			formato1 = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		final ImageIcon logo2 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));

		JLabel label_1 = new JLabel(
				"Bienvenido al sistema de b\u00FAsqueda de c\u00F3digos de matr\u00EDcula IDO 2020.");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		label_1.setBounds(10, 0, 654, 44);
		panel.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 55, 768, 243);
		panel.add(panel_3);

		JLabel label_3 = new JLabel("Buscar Alumno :");
		label_3.setFont(new Font("Cambria", Font.BOLD, 14));
		label_3.setBounds(10, 11, 119, 22);
		panel_3.add(label_3);

		txtBuscar = new JTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setFont(new Font("Cambria", Font.BOLD, 14));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(121, 14, 347, 19);
		panel_3.add(txtBuscar);
		InputMap map433 = txtBuscar.getInputMap(JComponent.WHEN_FOCUSED);
		map433.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtBuscar.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo = new TableRowSorter<>(tablaAlumno.getModel());
				tablaAlumno.setRowSorter(trsfiltroCodigo);

				if (txtBuscar.getText().length() == 30)
					ke.consume();

				if (txtBuscar.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtBuscar.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtBuscar.getText());
				txtBuscar.setText(cadena);
				repaint();
				filtro();
			}
		});

		barraAlumno = new JScrollPane(tablaAlumno, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_3.add(barraAlumno);
		barraAlumno.setBounds(10, 44, 748, 196);

		tablaAlumno = new JTable();
		barraAlumno.setViewportView(tablaAlumno);

		JLabel label_4 = new JLabel();
		label_4.setBounds(355, 41, 49, 44);
		panel_3.add(label_4);

		panelInformacion = new JPanel();
		panelInformacion.setBackground(new Color(255, 255, 255));
		panelInformacion.setBounds(10, 331, 788, 329);
		contentPane.add(panelInformacion);
		panelInformacion.setLayout(null);

		JLabel lblNombreCompletoDel = new JLabel("Nombre completo del alumno :");
		lblNombreCompletoDel.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNombreCompletoDel.setBounds(46, 132, 225, 21);
		panelInformacion.add(lblNombreCompletoDel);

		JLabel lblIn = new JLabel("Informaci\u00F3n del alumno :");
		lblIn.setForeground(Color.BLACK);
		lblIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIn.setFont(new Font("Serif", Font.BOLD, 14));
		lblIn.setBounds(20, 100, 654, 21);
		panelInformacion.add(lblIn);

		JLabel lblIdentidadDelAlumno = new JLabel("Identidad del alumno :");
		lblIdentidadDelAlumno.setFont(new Font("Cambria", Font.BOLD, 14));
		lblIdentidadDelAlumno.setBounds(46, 196, 225, 21);
		panelInformacion.add(lblIdentidadDelAlumno);

		JLabel lblCodigoDelAlumno = new JLabel("Codigo :");
		lblCodigoDelAlumno.setFont(new Font("Cambria", Font.BOLD, 14));
		lblCodigoDelAlumno.setBounds(46, 228, 225, 21);
		panelInformacion.add(lblCodigoDelAlumno);

		txtNombre_Alumno = new JTextField();
		txtNombre_Alumno.setFont(new Font("Cambria", Font.BOLD, 14));
		txtNombre_Alumno.setEditable(false);
		txtNombre_Alumno.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre_Alumno.setBounds(254, 133, 369, 20);
		panelInformacion.add(txtNombre_Alumno);
		txtNombre_Alumno.setColumns(10);

		txtIdentidad_Alumno = new JTextField();
		txtIdentidad_Alumno.setFont(new Font("Cambria", Font.BOLD, 14));
		txtIdentidad_Alumno.setEditable(false);
		txtIdentidad_Alumno.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad_Alumno.setColumns(10);
		txtIdentidad_Alumno.setBounds(254, 196, 172, 20);
		panelInformacion.add(txtIdentidad_Alumno);

		txtCodigo_Matricula = new JTextField();
		txtCodigo_Matricula.setFont(new Font("Cambria", Font.BOLD, 14));
		txtCodigo_Matricula.setEditable(false);
		txtCodigo_Matricula.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo_Matricula.setColumns(10);
		txtCodigo_Matricula.setBounds(254, 228, 172, 20);
		panelInformacion.add(txtCodigo_Matricula);

		JLabel lblCompro = new JLabel("Modalidad :");
		lblCompro.setFont(new Font("Cambria", Font.BOLD, 14));
		lblCompro.setBounds(46, 164, 225, 21);
		panelInformacion.add(lblCompro);

		txtModalidad = new JTextField();
		txtModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtModalidad.setFont(new Font("Cambria", Font.BOLD, 14));
		txtModalidad.setEditable(false);
		txtModalidad.setColumns(10);
		txtModalidad.setBounds(254, 164, 369, 20);
		panelInformacion.add(txtModalidad);

		JLabel lblInstitutoDepartamentalDe = new JLabel("Instituto Departamental de Oriente\r\n");
		lblInstitutoDepartamentalDe.setForeground(Color.BLACK);
		lblInstitutoDepartamentalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitutoDepartamentalDe.setFont(new Font("Serif", Font.BOLD, 18));
		lblInstitutoDepartamentalDe.setBounds(10, 0, 654, 32);
		panelInformacion.add(lblInstitutoDepartamentalDe);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11, 116, 110);
		panelInformacion.add(lblNewLabel);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);

		JButton btnImprimir = new JButton("Imprimir informaci\u00F3n");
		btnImprimir.setBounds(451, 291, 171, 21);
		panelInformacion.add(btnImprimir);
		btnImprimir.setFont(new Font("Cambria", Font.BOLD, 13));
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNombre_Alumno.getText().toString().equals("")
						&& txtIdentidad_Alumno.getText().toString().equals("")
						&& txtCodigo_Matricula.getText().toString().equals("")
						&& txtModalidad.getText().toString().equals("") && txtRecibo.getText().toString().equals("")
						&& txtVerificacion.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "No hay datos que imprimir, haga la busqueda antes.");

				} else {
					if (txtCodigo_Matricula.getText().toString().equals("Comprobar")
							&& txtRecibo.getText().toString().equals("Comprobar")
							&& txtVerificacion.getText().toString().equals("Comprobar")) {
						JOptionPane.showMessageDialog(null,
								"Antes imprimir, Compruebe el Pago de Matricula.\nPresione : Comprobar informaci�n de pago del estudiante");

					} else {

						detalle_comprobante detalle = new detalle_comprobante();
						detalle.setVisible(true);
						detalle.setLocationRelativeTo(null);
						detalle_comprobante.lblNombre.setText(txtNombre_Alumno.getText().toString());
						detalle_comprobante.lblIdentidad.setText(txtIdentidad_Alumno.getText().toString());
						detalle_comprobante.lblModalidad.setText(txtModalidad.getText().toString());
						detalle_comprobante.lblCodigo.setText(txtCodigo_Matricula.getText().toString());
						detalle_comprobante.lblFecha.setText(lblFecha.getText().toString());
						detalle_comprobante.lblHora.setText(lblHoraSistema.getText().toString());

					}
				}

			}
		});
		btnImprimir.setBackground(new Color(255, 215, 0));

		lblFecha = new JLabel("");
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Serif", Font.BOLD, 14));
		lblFecha.setBounds(217, 52, 298, 21);
		panelInformacion.add(lblFecha);
		lblFecha.setText(getFecha());

		JLabel lblFecha_1 = new JLabel("Fecha :");
		lblFecha_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha_1.setForeground(Color.BLACK);
		lblFecha_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblFecha_1.setBounds(207, 52, 143, 21);
		panelInformacion.add(lblFecha_1);

		JLabel lblVerificacionDePago = new JLabel("Verificacion de pago :");
		lblVerificacionDePago.setFont(new Font("Cambria", Font.BOLD, 14));
		lblVerificacionDePago.setBounds(46, 260, 225, 21);
		panelInformacion.add(lblVerificacionDePago);

		txtVerificacion = new JTextField();
		txtVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerificacion.setFont(new Font("Cambria", Font.BOLD, 14));
		txtVerificacion.setEditable(false);
		txtVerificacion.setColumns(10);
		txtVerificacion.setBounds(254, 260, 172, 20);
		panelInformacion.add(txtVerificacion);

		JLabel lblNDeRecibo = new JLabel("N\u00B0 de recibo.");
		lblNDeRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeRecibo.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNDeRecibo.setBounds(451, 240, 172, 21);
		panelInformacion.add(lblNDeRecibo);

		txtRecibo = new JTextField();
		txtRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecibo.setFont(new Font("Cambria", Font.BOLD, 14));
		txtRecibo.setEditable(false);
		txtRecibo.setColumns(10);
		txtRecibo.setBounds(451, 260, 172, 20);
		panelInformacion.add(txtRecibo);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(548, 11, 116, 110);
		panelInformacion.add(label_2);
		final ImageIcon logo22 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono22 = new ImageIcon(
				logo22.getImage().getScaledInstance(label_2.getWidth(), label_2.getHeight(), Image.SCALE_DEFAULT));
		label_2.setIcon(icono22);

		JButton btnComprobarInformacin = new JButton("Comprobar informaci\u00F3n");
		btnComprobarInformacin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNombre_Alumno.getText().toString().equals("")
						&& txtIdentidad_Alumno.getText().toString().equals("")
						&& txtCodigo_Matricula.getText().toString().equals("")
						&& txtModalidad.getText().toString().equals("") && txtRecibo.getText().toString().equals("")
						&& txtVerificacion.getText().toString().equals("")
						&& txtGrado.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "No hay datos que comprobar, haga la busqueda antes.");

				} else {
					verificacion_recibo recibo = new verificacion_recibo();
					recibo.setVisible(true);
					recibo.setLocationRelativeTo(null);
					verificacion_recibo.lblIdentidad.setText(txtIdentidad_Alumno.getText().toString());
					verificacion_recibo.lblNombre.setText(txtNombre_Alumno.getText().toString());
					verificacion_recibo.lblGrado.setText(txtGrado.getText().toString());
					dispose();
				}

			}
		});
		btnComprobarInformacin.setFont(new Font("Cambria", Font.BOLD, 12));
		btnComprobarInformacin.setBackground(new Color(60, 179, 113));
		btnComprobarInformacin.setBounds(254, 291, 172, 21);
		panelInformacion.add(btnComprobarInformacin);

		JLabel lblMatricula = new JLabel("Matricula 2020.\r\n");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricula.setForeground(Color.BLACK);
		lblMatricula.setFont(new Font("Serif", Font.BOLD, 18));
		lblMatricula.setBounds(20, 28, 628, 21);
		panelInformacion.add(lblMatricula);

		JLabel lblComprobanteDeMatrcula = new JLabel("Comprobante de matr\u00EDcula IDO 2020. ");
		lblComprobanteDeMatrcula.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprobanteDeMatrcula.setForeground(Color.BLACK);
		lblComprobanteDeMatrcula.setFont(new Font("Serif", Font.BOLD, 14));
		lblComprobanteDeMatrcula.setBounds(20, 78, 654, 21);
		panelInformacion.add(lblComprobanteDeMatrcula);

		JLabel lblGrado = new JLabel("Grado.");
		lblGrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrado.setFont(new Font("Cambria", Font.BOLD, 14));
		lblGrado.setBounds(451, 196, 172, 18);
		panelInformacion.add(lblGrado);

		txtGrado = new JTextField();
		txtGrado.setHorizontalAlignment(SwingConstants.CENTER);
		txtGrado.setFont(new Font("Cambria", Font.BOLD, 14));
		txtGrado.setEditable(false);
		txtGrado.setColumns(10);
		txtGrado.setBounds(451, 215, 172, 20);
		panelInformacion.add(txtGrado);
		final ImageIcon logo1 = new ImageIcon(getClass().getResource("/iconos/prueba.png"));
		final ImageIcon logo221 = new ImageIcon(getClass().getResource("/iconos/estudiante.png"));
		final ImageIcon logo222 = new ImageIcon(getClass().getResource("/iconos/escribir.png"));
		final ImageIcon logo2211 = new ImageIcon(getClass().getResource("/iconos/pre_matricula.png"));
		final ImageIcon logo22112 = new ImageIcon(getClass().getResource("/iconos/matricula.png"));

	}

	Timer time = new Timer();
	public TimerTask tarea = new TimerTask() {
		@Override
		public void run() {
			Calendar calendario = new GregorianCalendar();
			Date fechaHoraActual = new Date();
			calendario.setTime(fechaHoraActual);
			String horas;
			String minutos;
			String segundos;
			String ampm;
			Thread hilo = null;
			Thread hilo2;
			hilo2 = Thread.currentThread();
			hilo = new Thread();
			hilo.start();
			ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			if (ampm.equals("PM")) {
				int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
				horas = h > 9 ? "" + h : "0" + h;
			} else {
				horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
						: "0" + calendario.get(Calendar.HOUR_OF_DAY);
			}
			minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
					: "0" + calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
					: "0" + calendario.get(Calendar.SECOND);

			lblHoraSistema.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};

	public static String getFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'del' yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	public static void getHora() {
		Calendar cal = Calendar.getInstance();
		Date fecha = cal.getTime();
		DateFormat formatter = DateFormat.getTimeInstance();
		detalle_comprobante.lblHora.setText(formatter.format(fecha));
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "�Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void construirTabla() {
		String titulos[] = { "Nombres", "Apellidos", "Identidad", "Codigo", "Modalidad", "Grado", "Estado del pago",
				"Numero de recibo" };
		String informacion[][] = obtenerMatriz();
		tablaAlumno = new JTable(informacion, titulos);
		barraAlumno.setViewportView(tablaAlumno);
		for (int c = 0; c < tablaAlumno.getColumnCount(); c++) {
			Class<?> col_class = tablaAlumno.getColumnClass(c);
			tablaAlumno.setDefaultEditor(col_class, null);
			tablaAlumno.getTableHeader().setReorderingAllowed(false);

		}
	}

	public static ArrayList<alumnos> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<alumnos> miLista = new ArrayList<alumnos>();
		alumnos alumno;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"select * from dbo.Matricula inner join dbo.Reg_Alumno on dbo.Matricula.RNE_Alumno = dbo.Reg_Alumno.RNE_Alumno");

			while (rs.next()) {
				alumno = new alumnos();
				alumno.setNombres(rs.getString("Nombres"));
				alumno.setApellidos(rs.getString("Apellidos"));
				alumno.setRNE_Alumno(rs.getString("RNE_Alumno"));
				alumno.setCodigo(rs.getString("Codigo"));
				alumno.setModalidad(rs.getString("Modalidad"));
				alumno.setGrado(rs.getString("Grado"));
				alumno.setEstado_Pago(rs.getString("Estado_Pago"));
				alumno.setNumero_Recibo(rs.getString("Numero_Recibo"));
				miLista.add(alumno);
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}
		return miLista;
	}

	public static String[][] obtenerMatriz() {
		ArrayList<alumnos> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][8];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getNombres() + "";
			matrizInfo[i][1] = miLista.get(i).getApellidos() + "";
			matrizInfo[i][2] = miLista.get(i).getRNE_Alumno() + "";
			matrizInfo[i][3] = miLista.get(i).getCodigo() + "";
			matrizInfo[i][4] = miLista.get(i).getModalidad() + "";
			matrizInfo[i][5] = miLista.get(i).getGrado() + "";
			matrizInfo[i][6] = miLista.get(i).getEstado_Pago() + "";
			matrizInfo[i][7] = miLista.get(i).getNumero_Recibo() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo
				.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 0, 1, 2, 3, 4, 5, 6, 7));
	}
}