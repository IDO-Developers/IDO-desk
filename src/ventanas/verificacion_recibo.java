package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.alumnos;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_pago_alumnos;
import consultas.consultas_usuario;

import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Timer;
import java.awt.event.ActionEvent;

public class verificacion_recibo extends JFrame {

	private JPanel contentPane;
	public JTextField txtRecibo;
	public static String cadena;
	public static JLabel lblIdentidad;
	public static JLabel lblNombre;

	public static String nombres = null;
	public static String apellidos = null;
	public static String identidad = null;
	public static String codigo = null;
	public static String modalidad = null;
	public static String pago = null;
	public static String recibo = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Nombre completo del alumno :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label.setBounds(102, 43, 225, 21);
		contentPane.add(label);

		lblNombre = new JLabel("Dato");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(72, 61, 139));
		lblNombre.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblNombre.setBounds(0, 67, 433, 21);
		contentPane.add(lblNombre);

		JLabel label_2 = new JLabel("Identidad del alumno :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		label_2.setBounds(102, 97, 225, 21);
		contentPane.add(label_2);

		lblIdentidad = new JLabel("Dato");
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setForeground(new Color(72, 61, 139));
		lblIdentidad.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblIdentidad.setBounds(0, 121, 433, 21);
		contentPane.add(lblIdentidad);

		JLabel lblVerificarNumeroDe = new JLabel("Verificaci\u00F3n del n\u00FAmero de recibo de pago.");
		lblVerificarNumeroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerificarNumeroDe.setForeground(new Color(72, 61, 139));
		lblVerificarNumeroDe.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblVerificarNumeroDe.setBounds(0, 0, 433, 42);
		contentPane.add(lblVerificarNumeroDe);

		JLabel lblNotaEscriba = new JLabel("Nota : Escriba el n\u00FAmero de recibo");
		lblNotaEscriba.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotaEscriba.setForeground(new Color(72, 61, 139));
		lblNotaEscriba.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 15));
		lblNotaEscriba.setBounds(0, 140, 433, 26);
		contentPane.add(lblNotaEscriba);

		JLabel lblParaComprobarEl = new JLabel("para comprobar el pago y generar el C\u00F3digo de Matricula.");
		lblParaComprobarEl.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaComprobarEl.setForeground(new Color(72, 61, 139));
		lblParaComprobarEl.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 15));
		lblParaComprobarEl.setBounds(0, 166, 433, 26);
		contentPane.add(lblParaComprobarEl);

		JLabel lblNRecibo = new JLabel("N\u00B0 Recibo");
		lblNRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNRecibo.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 18));
		lblNRecibo.setBounds(102, 203, 225, 21);
		contentPane.add(lblNRecibo);

		txtRecibo = new JTextField();
		txtRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecibo.setBounds(133, 235, 166, 20);
		contentPane.add(txtRecibo);
		txtRecibo.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtRecibo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor escriba el N° de recibo para continuar!");
				} else {
					generarCodigo();
					alumnos clase = new alumnos();
					consultas_pago_alumnos consulta = new consultas_pago_alumnos();

					alumnos clase2 = new alumnos();
					consultas_pago_alumnos consulta2 = new consultas_pago_alumnos();

					clase.setCodigo(cadena);
					clase.setRNE_Alumno(lblIdentidad.getText().toString());

					clase2.setEstado_Pago("Pago Realizado");
					clase2.setNumero_Recibo(txtRecibo.getText().toString());
					clase2.setRNE_Alumno(lblIdentidad.getText().toString());
					
					principal principal = new principal();
					principal.setLocationRelativeTo(null);
					principal.setVisible(true);
					Timer time = new Timer();
					time.schedule(principal.tarea, 0, 1000);

					if (consulta.actualizarCodigo(clase) && consulta2.actualizarPago_y_Recibo(clase2)) {
						JOptionPane.showMessageDialog(null, "            Comprobado!");
						obtenerDatosAlumno();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Error! No Comprobado");
					}
				}
			}

		});
		btnAceptar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAceptar.setBackground(new Color(60, 179, 113));
		btnAceptar.setBounds(167, 266, 99, 23);
		contentPane.add(btnAceptar);
		setTitle("Verificacion del Pago de matricula IDO 2020.");
	}

	public void generarCodigo() {
		Random aleatorio = new Random();
		String alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
		cadena = "";
		int numero;
		int forma;
		forma = (int) (aleatorio.nextDouble() * alfa.length() - 1 + 0);
		numero = (int) (aleatorio.nextDouble() * 99 + 100);
		cadena = cadena + alfa.charAt(forma) + numero;
	}

	public void obtenerDatosAlumno() {
		principal principal = new principal();
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

				principal.txtNombre_Alumno.setText(nombres + " " + apellidos);
				principal.txtIdentidad_Alumno.setText(identidad);
				principal.txtCodigo_Matricula.setText(codigo);
				principal.txtModalidad.setText(modalidad);
				principal.txtVerificacion.setText(pago);
				principal.txtRecibo.setText(recibo);

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
