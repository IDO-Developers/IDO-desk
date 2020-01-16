package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.alumnos;
import clases.alumnos2;
import conexion.conexion;
import consultas.consultas_pago_alumnos;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;

public class verificacion_recibo extends JFrame {

	private JPanel contentPane;
	public JTextField txtRecibo;
	public static String cadena = null;
	public static JLabel lblIdentidad;
	public static JLabel lblNombre;
	public static JLabel lblGrado;

	public static String nombres = null;
	public static String apellidos = null;
	public static String identidad = null;
	public static String codigo = null;
	public static String modalidad = null;
	public static String pago = null;
	public static String recibo = null;
	public static String grado = null;

	public static JLabel lbltabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					verificacion_recibo frame = new verificacion_recibo();
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
	public verificacion_recibo() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Verificacion del Pago de matricula IDO 2020.");

		JLabel label = new JLabel("Nombre completo del alumno :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(102, 43, 225, 21);
		contentPane.add(label);

		lblNombre = new JLabel("Dato");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(72, 61, 139));
		lblNombre.setFont(new Font("Cambria", Font.BOLD, 17));
		lblNombre.setBounds(0, 67, 433, 21);
		contentPane.add(lblNombre);

		JLabel label_2 = new JLabel("Identidad del alumno :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		label_2.setBounds(102, 97, 225, 21);
		contentPane.add(label_2);

		lblIdentidad = new JLabel("Dato");
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setForeground(new Color(72, 61, 139));
		lblIdentidad.setFont(new Font("Cambria", Font.BOLD, 17));
		lblIdentidad.setBounds(0, 121, 433, 21);
		contentPane.add(lblIdentidad);

		JLabel lblVerificarNumeroDe = new JLabel("Verificaci\u00F3n del n\u00FAmero de recibo de pago.");
		lblVerificarNumeroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerificarNumeroDe.setForeground(new Color(72, 61, 139));
		lblVerificarNumeroDe.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblVerificarNumeroDe.setBounds(0, 0, 433, 42);
		contentPane.add(lblVerificarNumeroDe);

		JLabel lblNotaEscriba = new JLabel("Nota : Escriba el n\u00FAmero de recibo");
		lblNotaEscriba.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotaEscriba.setForeground(new Color(72, 61, 139));
		lblNotaEscriba.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblNotaEscriba.setBounds(0, 140, 433, 26);
		contentPane.add(lblNotaEscriba);

		JLabel lblParaComprobarEl = new JLabel("para comprobar el pago y generar el C\u00F3digo de Matricula.");
		lblParaComprobarEl.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaComprobarEl.setForeground(new Color(72, 61, 139));
		lblParaComprobarEl.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblParaComprobarEl.setBounds(0, 166, 433, 26);
		contentPane.add(lblParaComprobarEl);

		JLabel lblNRecibo = new JLabel("N\u00B0 Recibo");
		lblNRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNRecibo.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblNRecibo.setBounds(102, 223, 225, 26);
		contentPane.add(lblNRecibo);

		txtRecibo = new JTextField();
		txtRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecibo.setBounds(133, 245, 166, 20);
		contentPane.add(txtRecibo);
		txtRecibo.setColumns(10);
		InputMap map4 = txtRecibo.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtRecibo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtRecibo.getText().length() == 15)
					ke.consume();

				if (txtRecibo.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtRecibo.setText("");
				}

				char c = ke.getKeyChar();
				if ((c < '0' || c > '9'))
					ke.consume();
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {

			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtRecibo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor escriba el N° de recibo para continuar!");
				} else {
					alumnos clase = new alumnos();
					consultas_pago_alumnos consulta = new consultas_pago_alumnos();

					alumnos2 clase2_1 = new alumnos2();
					consultas_pago_alumnos consulta2_1 = new consultas_pago_alumnos();

					alumnos clase2 = new alumnos();
					consultas_pago_alumnos consulta2 = new consultas_pago_alumnos();

					if (lbltabla.getText().toString().equals("Matricula")) {
						obtenerDatosAlumno();
						generarCodigo();
						clase.setCodigo(cadena);

						clase.setRNE_Alumno(lblIdentidad.getText().toString());

						if (txtRecibo.getText().toString().equals("Sin Recibo")) {
							clase2.setEstado_Pago("Pago Pendiente");
						} else {
							clase2.setEstado_Pago("Pago Realizado");
						}

						clase2.setNumero_Recibo(txtRecibo.getText().toString());
						clase2.setRNE_Alumno(lblIdentidad.getText().toString());

						principal principal = new principal();
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						Timer time = new Timer();
						time.schedule(principal.tarea, 0, 1000);

						if (consulta.actualizarCodigo(clase) && consulta2.actualizarPago_y_Recibo(clase2)) {
							JOptionPane.showMessageDialog(null, "Datos comprobados!");
							dispose();
							principal.construirTabla();
							establecerDatosAlumno();
							principal.comboBox.setSelectedIndex(0);
						} else {
							JOptionPane.showMessageDialog(null, "Error! No Comprobado");
						}

					} else {
						obtenerDatosAlumno2();
						generarCodigo2();
						clase2_1.setCodigo(cadena);
						clase2_1.setIdentidad_alumnos(lblIdentidad.getText().toString());

						if (txtRecibo.getText().toString().equals("Sin Recibo")) {
							clase2_1.setEstado_Pago("Pago Pendiente");
						} else {
							clase2_1.setEstado_Pago("Pago Realizado");
						}

						clase2_1.setNumero_Recibo(txtRecibo.getText().toString());
						clase2_1.setIdentidad_alumnos(lblIdentidad.getText().toString());

						principal principal = new principal();
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						Timer time = new Timer();
						time.schedule(principal.tarea, 0, 1000);

						if (consulta.actualizarCodigo2(clase2_1)) {
							JOptionPane.showMessageDialog(null, "Datos comprobados!");
							dispose();
							principal.construirTabla2();
							establecerDatosAlumnos2();
							principal.comboBox.setSelectedIndex(1);
						} else {
							JOptionPane.showMessageDialog(null, "Error! No Comprobado");
						}

					}

				}
			}

		});
		btnAceptar.setFont(new Font("Cambria", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(60, 179, 113));
		btnAceptar.setBounds(167, 269, 99, 20);
		contentPane.add(btnAceptar);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("   Con Recibo")) {
					txtRecibo.setText("");
					txtRecibo.setEditable(true);
				} else {
					txtRecibo.setText("Sin Recibo");
					txtRecibo.setEditable(false);
				}
			}
		});
		comboBox.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "   Con Recibo", "   Sin Recibo" }));
		comboBox.setBounds(167, 195, 99, 20);
		contentPane.add(comboBox);

		lblGrado = new JLabel("Grado");
		lblGrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrado.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblGrado.setBounds(276, 273, 157, 21);
		contentPane.add(lblGrado);

		lbltabla = new JLabel("");
		lbltabla.setBounds(10, 277, 99, 14);
		contentPane.add(lbltabla);
		lblGrado.setVisible(false);

	}

	@SuppressWarnings("unlikely-arg-type")
	public void generarCodigo() {

		String valorGrado = null;
		if (lblGrado.getText().toString().equals("Septimo Grado")) {
			valorGrado = "SEP20-";
		} else {
			if (lblGrado.getText().toString().equals("Octavo Grado")) {
				valorGrado = "OCT20-";
			} else {
				if (lblGrado.getText().toString().equals("Noveno Grado")) {
					valorGrado = "NOV20-";
				} else {
					if (lblGrado.getText().toString().equals("Decimo Grado")) {
						valorGrado = "DEC20-";
					} else {
						if (lblGrado.getText().toString().equals("Undecimo Grado")) {
							valorGrado = "UND20-";
						} else {
							if (lblGrado.getText().toString().equals("Duodecimo Grado")) {
								valorGrado = "DUO20-";
							} else {

							}

						}

					}

				}

			}

		}

		String NumGenerado = String.valueOf(genererNumero());
		cadena = valorGrado + NumGenerado;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void generarCodigo2() {

		String valorGrado = null;
		if (lblGrado.getText().toString().equals("Septimo")) {
			valorGrado = "SEP20-";
		} else {
			valorGrado = "DEC20-";

		}

		String NumGenerado = String.valueOf(genererNumero());
		cadena = valorGrado + NumGenerado;
	}

	public static int genererNumero() {
		return (int) (1000 * Math.random());
	}

	public void establecerDatosAlumno() {

		String querty = "select * from dbo.Matricula inner join dbo.Reg_Alumno on dbo.Matricula.RNE_Alumno = dbo.Reg_Alumno.RNE_Alumno where dbo.Reg_Alumno.RNE_Alumno = '"
				+ lblIdentidad.getText().toString() + "'";
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
				grado = rsr.getString("Grado");

				principal.txtNombre_Alumno.setText(nombres + " " + apellidos);
				principal.txtIdentidad_Alumno.setText(identidad);
				principal.txtCodigo_Matricula.setText(codigo);
				principal.txtModalidad.setText(modalidad);
				principal.txtVerificacion.setText(pago);
				principal.txtRecibo.setText(recibo);
				principal.txtGrado.setText(grado);

			}

			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void obtenerDatosAlumno() {

		String querty = "select * from dbo.Matricula inner join dbo.Reg_Alumno on dbo.Matricula.RNE_Alumno = dbo.Reg_Alumno.RNE_Alumno where dbo.Reg_Alumno.RNE_Alumno = '"
				+ lblIdentidad.getText().toString() + "'";
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
				grado = rsr.getString("Grado");

			}

			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void establecerDatosAlumnos2() {

		String querty = "select * from dbo.prematricula2019_2020 where dbo.prematricula2019_2020.Identidad_alumnos = '"
				+ lblIdentidad.getText().toString() + "'";
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();

		try {
			PreparedStatement stmtr = conn.prepareStatement(querty);

			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				nombres = rsr.getString("Nombres_alumnos");
				apellidos = rsr.getString("Apellidos_alumnos");
				identidad = rsr.getString("Identidad_alumnos");
				codigo = rsr.getString("codigo");
				modalidad = rsr.getString("Modalidad");
				pago = rsr.getString("Estado_Pago");
				recibo = rsr.getString("Numero_Recibo");
				grado = rsr.getString("Grado");

				principal.txtNombre_Alumno.setText(nombres + " " + apellidos);
				principal.txtIdentidad_Alumno.setText(identidad);
				principal.txtCodigo_Matricula.setText(codigo);
				principal.txtModalidad.setText(modalidad);
				principal.txtVerificacion.setText(pago);
				principal.txtRecibo.setText(recibo);
				principal.txtGrado.setText(grado);

			}

			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void obtenerDatosAlumno2() {

		String querty = "select * from dbo.prematricula2019_2020 where dbo.prematricula2019_2020.Identidad_alumnos = '"
				+ lblIdentidad.getText().toString() + "'";
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();

		try {
			PreparedStatement stmtr = conn.prepareStatement(querty);

			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				nombres = rsr.getString("Nombres_alumnos");
				apellidos = rsr.getString("Apellidos_alumnos");
				identidad = rsr.getString("Identidad_alumnos");
				codigo = rsr.getString("codigo");
				modalidad = rsr.getString("Modalidad");
				pago = rsr.getString("Estado_Pago");
				recibo = rsr.getString("Numero_Recibo");
				grado = rsr.getString("Grado");

			}

			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
