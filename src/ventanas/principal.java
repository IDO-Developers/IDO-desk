package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import clases.usuarios;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.print.Printable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.conexion;
import consultas.consultas_usuario;

public class principal extends JFrame {

	private JPanel contentPane;
	public JFormattedTextField txtIdentidad;
	public JTextField txtNombre_Alumno;
	public JTextField txtIdentidad_Alumno;
	public JTextField txtCodigo_Matricula;
	public JButton btnTabla;
	public JButton btnBuscar;
	public JPanel panelInformacion;

	public static String nombres = null;
	public static String apellidos = null;
	public static String identidad = null;
	public static String codigo = null;
	public static String modalidad = null;
	public static String pago = null;
	public static String recibo = null;

	public JTextField txtModalidad;
	public JTextField txtVerificacion;
	public JTextField txtRecibo;
	public JLabel mensaje;
	public static JLabel lblHoraSistema;
	public static JLabel lblFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					principal frame = new principal();
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
	public principal() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 61, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Sistema de busqueda de codigos de matricula. IDO 2020");
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 674, 165);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBuscarInformacionDe = new JLabel("Buscar informaci\u00F3n de la matr\u00EDcula del alumno :");
		lblBuscarInformacionDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscarInformacionDe.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblBuscarInformacionDe.setBounds(23, 48, 644, 30);
		panel.add(lblBuscarInformacionDe);

		JLabel lblBuscarPor = new JLabel("Buscar por :");
		lblBuscarPor.setFont(new Font("Calibri", Font.BOLD, 14));
		lblBuscarPor.setBounds(20, 101, 179, 21);
		panel.add(lblBuscarPor);

		MaskFormatter formato1 = null;
		try {
			formato1 = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtIdentidad = new JFormattedTextField(formato1);
		txtIdentidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setBounds(243, 101, 179, 21);
		panel.add(txtIdentidad);
		txtIdentidad.setColumns(10);
		InputMap map22 = txtIdentidad.getInputMap(JComponent.WHEN_FOCUSED);
		map22.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if ((c < '0' || c > '9'))
					ke.consume();

				if (txtIdentidad.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtIdentidad.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Calibri", Font.BOLD, 14));
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtIdentidad.getText().toString() == "             ") {
					JOptionPane.showMessageDialog(null, "Por favor escriba la identidad del alumno, antes de buscar.");
				} else {
					obtenerDatosAlumno();
				}
			}
		});
		btnBuscar.setBackground(new Color(64, 224, 208));
		btnBuscar.setBounds(432, 101, 89, 21);
		panel.add(btnBuscar);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().equals("Identidad")) {
					txtIdentidad.setEditable(true);
					btnBuscar.setVisible(true);
					btnTabla.setVisible(false);
					mensaje.setText("Para hacer una búsqueda por identidad escriba la identidad, y presione Buscar.");
					mensaje.setForeground(Color.blue);

				} else {
					txtIdentidad.setEditable(false);
					btnBuscar.setVisible(false);
					btnTabla.setVisible(true);
					txtIdentidad.setText("");
					mensaje.setText(
							"Atencion! Para hacer una búsqueda especifica por nombre, presione Buscar por nombre.");
					mensaje.setForeground(Color.blue);

				}
			}
		});
		comboBox.setFont(new Font("Calibri", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Identidad", "Nombre" }));
		comboBox.setBounds(123, 101, 110, 20);
		panel.add(comboBox);

		btnTabla = new JButton("Buscar por nombre");
		btnTabla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabla_alumnos tabla = new tabla_alumnos();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		btnTabla.setFont(new Font("Calibri", Font.BOLD, 14));
		btnTabla.setBackground(new Color(64, 224, 208));
		btnTabla.setBounds(243, 79, 179, 21);
		panel.add(btnTabla);

		JLabel label = new JLabel("");
		label.setBounds(525, 22, 139, 132);
		panel.add(label);
		btnTabla.setVisible(false);
		final ImageIcon logo2 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono2 = new ImageIcon(
				logo2.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icono2);

		mensaje = new JLabel("");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Calibri", Font.BOLD, 12));
		mensaje.setBounds(23, 133, 498, 21);
		panel.add(mensaje);

		JLabel label_1 = new JLabel(
				"Bienvenido al sistema de b\u00FAsqueda de c\u00F3digos de matr\u00EDcula IDO 2020.");
		label_1.setForeground(Color.BLACK);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 22));
		label_1.setBounds(10, 0, 654, 44);
		panel.add(label_1);

		panelInformacion = new JPanel();
		panelInformacion.setBackground(new Color(255, 255, 255));
		panelInformacion.setBounds(10, 187, 674, 367);
		contentPane.add(panelInformacion);
		panelInformacion.setLayout(null);

		JLabel lblNombreCompletoDel = new JLabel("Nombre completo del alumno :");
		lblNombreCompletoDel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNombreCompletoDel.setBounds(50, 132, 225, 21);
		panelInformacion.add(lblNombreCompletoDel);

		JLabel lblIn = new JLabel("Informaci\u00F3n del alumno :");
		lblIn.setForeground(Color.BLACK);
		lblIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIn.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblIn.setBounds(50, 76, 577, 57);
		panelInformacion.add(lblIn);

		JLabel lblIdentidadDelAlumno = new JLabel("Identidad del alumno :");
		lblIdentidadDelAlumno.setFont(new Font("Arial", Font.BOLD, 12));
		lblIdentidadDelAlumno.setBounds(50, 164, 225, 21);
		panelInformacion.add(lblIdentidadDelAlumno);

		JLabel lblCodigoDelAlumno = new JLabel("Codigo :");
		lblCodigoDelAlumno.setFont(new Font("Arial", Font.BOLD, 12));
		lblCodigoDelAlumno.setBounds(50, 269, 225, 21);
		panelInformacion.add(lblCodigoDelAlumno);

		txtNombre_Alumno = new JTextField();
		txtNombre_Alumno.setFont(new Font("Arial", Font.BOLD, 12));
		txtNombre_Alumno.setEditable(false);
		txtNombre_Alumno.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre_Alumno.setBounds(258, 133, 369, 20);
		panelInformacion.add(txtNombre_Alumno);
		txtNombre_Alumno.setColumns(10);

		txtIdentidad_Alumno = new JTextField();
		txtIdentidad_Alumno.setFont(new Font("Arial", Font.BOLD, 12));
		txtIdentidad_Alumno.setEditable(false);
		txtIdentidad_Alumno.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad_Alumno.setColumns(10);
		txtIdentidad_Alumno.setBounds(258, 164, 369, 20);
		panelInformacion.add(txtIdentidad_Alumno);

		txtCodigo_Matricula = new JTextField();
		txtCodigo_Matricula.setFont(new Font("Arial", Font.BOLD, 12));
		txtCodigo_Matricula.setEditable(false);
		txtCodigo_Matricula.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo_Matricula.setColumns(10);
		txtCodigo_Matricula.setBounds(258, 269, 172, 20);
		panelInformacion.add(txtCodigo_Matricula);

		JLabel lblCompro = new JLabel("Modalidad :");
		lblCompro.setFont(new Font("Arial", Font.BOLD, 12));
		lblCompro.setBounds(50, 196, 225, 21);
		panelInformacion.add(lblCompro);

		txtModalidad = new JTextField();
		txtModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtModalidad.setFont(new Font("Arial", Font.BOLD, 12));
		txtModalidad.setEditable(false);
		txtModalidad.setColumns(10);
		txtModalidad.setBounds(258, 196, 369, 20);
		panelInformacion.add(txtModalidad);

		JLabel lblInstitutoDepartamentalDe = new JLabel(
				"Instituto Departamental de Oriente, \r\nPre-Matricula 2020.\r\n");
		lblInstitutoDepartamentalDe.setForeground(Color.BLACK);
		lblInstitutoDepartamentalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitutoDepartamentalDe.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblInstitutoDepartamentalDe.setBounds(33, 11, 609, 21);
		panelInformacion.add(lblInstitutoDepartamentalDe);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(43, 0, 116, 110);
		panelInformacion.add(lblNewLabel);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);

		JButton btnImprimir = new JButton("Imprimir informaci\u00F3n");
		btnImprimir.setBounds(455, 333, 171, 21);
		panelInformacion.add(btnImprimir);
		btnImprimir.setFont(new Font("Calibri", Font.BOLD, 14));
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
								"Antes imprimir, Compruebe el Pago de Matricula.\nPresione : Comprobar información de pago del estudiante");

					} else {

						detalle_comprobante_prematricula detalle = new detalle_comprobante_prematricula();
						detalle.setVisible(true);
						detalle.setLocationRelativeTo(null);
						detalle.lblNombre.setText(txtNombre_Alumno.getText().toString());
						detalle.lblIdentidad.setText(txtIdentidad_Alumno.getText().toString());
						detalle.lblModalidad.setText(txtModalidad.getText().toString());
						detalle.lblCodigo.setText(txtCodigo_Matricula.getText().toString());
						detalle.lblFecha.setText(lblFecha.getText().toString());
						getHora();
					}
				}

			}
		});
		btnImprimir.setBackground(new Color(255, 215, 0));

		lblFecha = new JLabel("");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 15));
		lblFecha.setBounds(218, 33, 294, 32);
		panelInformacion.add(lblFecha);
		lblFecha.setText(getFecha());

		JLabel lblHora = new JLabel("Hora :");
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblHora.setBounds(55, 333, 71, 28);
		panelInformacion.add(lblHora);

		JLabel lblFecha_1 = new JLabel("Fecha :");
		lblFecha_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha_1.setForeground(Color.BLACK);
		lblFecha_1.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblFecha_1.setBounds(195, 34, 161, 28);
		panelInformacion.add(lblFecha_1);

		lblHoraSistema = new JLabel("");
		lblHoraSistema.setForeground(new Color(72, 61, 139));
		lblHoraSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraSistema.setFont(new Font("Broadway", Font.BOLD, 15));
		lblHoraSistema.setBounds(106, 333, 117, 31);
		panelInformacion.add(lblHoraSistema);

		JLabel lblVerificacionDePago = new JLabel("Verificacion de pago :");
		lblVerificacionDePago.setFont(new Font("Arial", Font.BOLD, 12));
		lblVerificacionDePago.setBounds(50, 301, 225, 21);
		panelInformacion.add(lblVerificacionDePago);

		txtVerificacion = new JTextField();
		txtVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerificacion.setFont(new Font("Arial", Font.BOLD, 12));
		txtVerificacion.setEditable(false);
		txtVerificacion.setColumns(10);
		txtVerificacion.setBounds(258, 301, 172, 20);
		panelInformacion.add(txtVerificacion);

		JLabel lblNDeRecibo = new JLabel("N\u00B0 de recibo.");
		lblNDeRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeRecibo.setFont(new Font("Arial", Font.BOLD, 12));
		lblNDeRecibo.setBounds(455, 269, 172, 21);
		panelInformacion.add(lblNDeRecibo);

		txtRecibo = new JTextField();
		txtRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecibo.setFont(new Font("Arial", Font.BOLD, 12));
		txtRecibo.setEditable(false);
		txtRecibo.setColumns(10);
		txtRecibo.setBounds(455, 301, 172, 20);
		panelInformacion.add(txtRecibo);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(522, 0, 116, 110);
		panelInformacion.add(label_2);
		final ImageIcon logo22 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono22 = new ImageIcon(
				logo22.getImage().getScaledInstance(label_2.getWidth(), label_2.getHeight(), Image.SCALE_DEFAULT));
		label_2.setIcon(icono22);

		JLabel label_3 = new JLabel("Informaci\u00F3n del alumno :");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_3.setBounds(50, 228, 201, 30);
		panelInformacion.add(label_3);

		JButton btnComprobarInformacin = new JButton("Comprobar informaci\u00F3n de pago del estudiante");
		btnComprobarInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNombre_Alumno.getText().toString().equals("")
						&& txtIdentidad_Alumno.getText().toString().equals("")
						&& txtCodigo_Matricula.getText().toString().equals("")
						&& txtModalidad.getText().toString().equals("") && txtRecibo.getText().toString().equals("")
						&& txtVerificacion.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "No hay datos que comprobar, haga la busqueda antes.");

				} else {
					verificacion_recibo recibo = new verificacion_recibo();
					recibo.setVisible(true);
					recibo.setLocationRelativeTo(null);
					recibo.lblIdentidad.setText(txtIdentidad_Alumno.getText().toString());
					recibo.lblNombre.setText(txtNombre_Alumno.getText().toString());
					dispose();
				}

			}
		});
		btnComprobarInformacin.setFont(new Font("Calibri", Font.BOLD, 14));
		btnComprobarInformacin.setBackground(new Color(60, 179, 113));
		btnComprobarInformacin.setBounds(258, 237, 369, 21);
		panelInformacion.add(btnComprobarInformacin);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 565, 674, 95);
		contentPane.add(panel_1);

		JLabel lblMenDeOpciones = new JLabel("Men\u00FA de opciones :");
		lblMenDeOpciones.setForeground(Color.BLACK);
		lblMenDeOpciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblMenDeOpciones.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblMenDeOpciones.setBounds(47, 2, 169, 23);
		panel_1.add(lblMenDeOpciones);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registro_usuarios usuarios = new registro_usuarios();
				usuarios.setVisible(true);
				usuarios.setLocationRelativeTo(null);
				usuarios.construirTabla();
				usuarios.obtenerUltimoId();
				usuarios.btnBorrar.setVisible(false);
				usuarios.btnActualizar.setVisible(false);
				dispose();
			}
		});
		button.setFont(new Font("Calibri", Font.BOLD, 14));
		button.setBackground(Color.WHITE);
		button.setBounds(237, 25, 72, 59);
		panel_1.add(button);
		final ImageIcon logo1 = new ImageIcon(getClass().getResource("/iconos/prueba.png"));
		final ImageIcon icono1 = new ImageIcon(
				logo1.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
		button.setIcon(icono1);

		JLabel lblRegistroDeUsuarios = new JLabel("Registro de Usuarios");
		lblRegistroDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeUsuarios.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 14));
		lblRegistroDeUsuarios.setBounds(186, 2, 169, 21);
		panel_1.add(lblRegistroDeUsuarios);

		JLabel lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 14));
		lblAlumnos.setBounds(350, 2, 169, 21);
		panel_1.add(lblAlumnos);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla_alumnos tabla = new tabla_alumnos();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		button_1.setFont(new Font("Calibri", Font.BOLD, 14));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(401, 25, 72, 59);
		panel_1.add(button_1);
		final ImageIcon logo221 = new ImageIcon(getClass().getResource("/iconos/estudiante.png"));
		final ImageIcon icono221 = new ImageIcon(
				logo221.getImage().getScaledInstance(button_1.getWidth(), button_1.getHeight(), Image.SCALE_DEFAULT));
		button_1.setIcon(icono221);

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 14));
		lblAcercaDe.setBounds(512, 2, 162, 21);
		panel_1.add(lblAcercaDe);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acerca_de tabla = new acerca_de();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
			}
		});
		button_2.setFont(new Font("Calibri", Font.BOLD, 14));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(556, 25, 72, 59);
		panel_1.add(button_2);
		final ImageIcon logo222 = new ImageIcon(getClass().getResource("/iconos/escribir.png"));
		final ImageIcon icono222 = new ImageIcon(
				logo222.getImage().getScaledInstance(button_2.getWidth(), button_2.getHeight(), Image.SCALE_DEFAULT));
		button_2.setIcon(icono222);
	}

	public void obtenerDatosAlumno() {
		String querty = "select * from dbo.Matricula inner join dbo.Reg_Alumno on dbo.Matricula.RNE_Alumno = dbo.Reg_Alumno.RNE_Alumno where dbo.Reg_Alumno.RNE_Alumno = '"
				+ txtIdentidad.getText().toString() + "'";
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(querty);

			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				nombres = rsr.getString("Nombres");
				apellidos = rsr.getString("Apellidos");
				identidad = rsr.getString("RNE_Alumno");
				codigo = rsr.getString("Codigo");
				modalidad = rsr.getString("Modalidad");
				pago = rsr.getString("Estado_Pago");
				recibo = rsr.getString("Numero_Recibo");

				txtNombre_Alumno.setText(nombres + " " + apellidos);
				txtIdentidad_Alumno.setText(identidad);
				txtModalidad.setText(modalidad);

				if (codigo.equals("null")) {
					txtCodigo_Matricula.setText("Comprobar");
				} else {
					txtCodigo_Matricula.setText(codigo);
				}

				if (pago.equals("0")) {
					txtVerificacion.setText("Comprobar");
				} else {
					txtVerificacion.setText(pago);
				}

				if (recibo.equals("0000")) {
					txtRecibo.setText("Comprobar");
				} else {
					txtRecibo.setText(recibo);
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"No se encontró el alumno con esta identidad\n  Intente buscar por nombres del alumno. \n Asegúrese de no dejar los campos vacíos");
			}

			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		SimpleDateFormat df = new SimpleDateFormat("'Dia' EEEEEEEEE dd 'de' MMMMM 'del' yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	public static void getHora() {
		Calendar cal = Calendar.getInstance();
		Date fecha = cal.getTime();
		DateFormat formatter = DateFormat.getTimeInstance();
		detalle_comprobante_prematricula.lblHora.setText(formatter.format(fecha));
	}
}
