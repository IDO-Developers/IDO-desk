package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import consultas.consultas_usuario;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class login extends JFrame {

	public JPanel contentPane;
	public static JTextField txtUsuario;
	public static JPasswordField txtContraseña;
	public JLabel lblAlerta;
	public JButton btnIngresar;
	public static JLabel lblNombreEmpresa;
	public static JLabel lblFotoEmpresa;
	public static String nombre = null;
	public static String frase = null;
	public static String ipServidor = null;
	public static String ruta_logo = null;
	public static JRadioButton rdbtnPass;
	public static JLabel lblestadocontraseña;
	public JButton btnActualizarBase;

	public static String todo;
	public static String empleado;
	public static String cargoe;
	public static String horario;
	public static String contrato_e;
	public static String cliente;
	public static String contrato_c;
	public static String compra;
	public static String proveedor;
	public static String inventario;
	public static String factura_c;
	public static String factura_e;
	public static String sar;
	public static String ingreso;
	public static String producto;
	public static String servicio;
	public static String venta;
	public static String egreso;
	public static String bonificacion;
	public static String deduccion;
	public static String planilla;
	public static String empresa;
	public static String opciones;
	public static String usuarios;
	public static String acercade;

	public static String nombreCompletoUsuario;
	public static String cargoUsuario;
	public static String tipoUsuario;
	public static String direccionFotoUsuario;
	public static String nombreUsuario;

	public login() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 61, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon ver = new ImageIcon(getClass().getResource("/iconos/ver.png"));
		final ImageIcon ocultar = new ImageIcon(getClass().getResource("/iconos/ocultar.png"));

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(586, 426, 98, 34);
		contentPane.add(btnSalir);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(Color.WHITE);
		panel.setBounds(31, 27, 327, 307);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setForeground(new Color(0, 0, 0));
		lblUsuario.setFont(new Font("Cambria", Font.BOLD, 15));
		lblUsuario.setBounds(136, 165, 108, 20);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setForeground(new Color(0, 0, 0));
		lblContrasea.setFont(new Font("Cambria", Font.BOLD, 15));
		lblContrasea.setBounds(125, 204, 98, 20);
		panel.add(lblContrasea);

		lblestadocontraseña = new JLabel("");
		lblestadocontraseña.setBackground(Color.WHITE);
		lblestadocontraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblestadocontraseña.setBounds(277, 225, 21, 20);
		panel.add(lblestadocontraseña);
		final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage().getScaledInstance(
				lblestadocontraseña.getWidth(), lblestadocontraseña.getHeight(), Image.SCALE_DEFAULT));
		lblestadocontraseña.setIcon(iconoocultar);

		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Cambria", Font.BOLD, 14));
		txtUsuario.setBounds(74, 185, 181, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		InputMap map4 = txtUsuario.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtUsuario.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtUsuario.getText().length() == 15)
					ke.consume();

				if (txtUsuario.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtUsuario.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setFont(new Font("Cambria", Font.BOLD, 15));
		btnIngresar.setBackground(new Color(60, 179, 113));
		btnIngresar.setBounds(110, 257, 113, 25);
		panel.add(btnIngresar);

		txtContraseña = new JPasswordField();
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setFont(new Font("Cambria", Font.BOLD, 14));
		txtContraseña.setBounds(74, 225, 181, 20);
		panel.add(txtContraseña);
		InputMap map5 = txtContraseña.getInputMap(JComponent.WHEN_FOCUSED);
		map5.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContraseña.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContraseña.getText().length() == 15)
					e.consume();

				if (txtContraseña.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContraseña.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		lblAlerta = new JLabel("");
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblAlerta.setBounds(10, 281, 307, 20);
		panel.add(lblAlerta);

		lblNombreEmpresa = new JLabel("Instituto Departamental de Oriente.");
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEmpresa.setFont(new Font("Cambria", Font.BOLD, 15));
		lblNombreEmpresa.setBounds(10, 0, 303, 33);
		panel.add(lblNombreEmpresa);

		rdbtnPass = new JRadioButton("");
		rdbtnPass.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPass.setBackground(Color.WHITE);
		rdbtnPass.setBounds(256, 225, 21, 20);
		panel.add(rdbtnPass);
		rdbtnPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnPass.isSelected()) {
					txtContraseña.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(
							lblestadocontraseña.getWidth(), lblestadocontraseña.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontraseña.setIcon(iconover);
				} else {
					txtContraseña.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage().getScaledInstance(
							lblestadocontraseña.getWidth(), lblestadocontraseña.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontraseña.setIcon(iconoocultar);
					setBackground(Color.BLACK);
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(88, 27, 152, 135);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblFotoEmpresa = new JLabel("");
		lblFotoEmpresa.setBounds(-11, -13, 176, 160);
		panel_1.add(lblFotoEmpresa);
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblFotoEmpresa.getWidth(),
				lblFotoEmpresa.getHeight(), Image.SCALE_DEFAULT));
		lblFotoEmpresa.setIcon(icono);

		JLabel lblLoginSistemaAdministrativo = new JLabel("Sistema de pago y c\u00F3digo de matr\u00EDcula IDO 2020.");
		lblLoginSistemaAdministrativo.setForeground(Color.WHITE);
		lblLoginSistemaAdministrativo.setBackground(Color.WHITE);
		lblLoginSistemaAdministrativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSistemaAdministrativo.setFont(new Font("Cambria", Font.BOLD, 15));
		lblLoginSistemaAdministrativo.setBounds(10, 0, 370, 30);
		contentPane.add(lblLoginSistemaAdministrativo);

	}

	@SuppressWarnings("unlikely-arg-type")
	public void iniciarSesion() {
		principal principal = new principal();
		String user = String.valueOf(txtUsuario.getText().toString());
		String pass = String.valueOf(txtContraseña.getText().toString());
		if (user.equals("") && pass.equals("")) {
			lblAlerta.setText("Los campos (Usuario) y (Contraseña) estan vacios.");
			lblAlerta.setForeground(Color.RED);
		} else {
			if (user.equals("")) {
				lblAlerta.setText("El campo de (Usuario) esta vacio.");
				lblAlerta.setForeground(Color.RED);
			} else {
				if (pass.equals("")) {
					lblAlerta.setText("El campo de (Contraseña) esta vacio.");
					lblAlerta.setForeground(Color.RED);
				} else {
					consultas_usuario consulta = new consultas_usuario();
					clases.usuarios clase = new clases.usuarios();
					clase.setNombre_Usuario(txtUsuario.getText().toString());
					clase.setContraseña_Usuario(txtContraseña.getText().toString());
					if (consulta.buscarUsuario(clase)) {
						principal.setLocationRelativeTo(null);
						principal.setVisible(true);
						principal.construirTabla();
						Timer time = new Timer();
						time.schedule(principal.tarea, 0, 1000);
						if (consultas_usuario.rol.equals("1")) {
							principal.btnAlumnos.setEnabled(true);
							principal.btnUsuarios.setEnabled(true);
							principal.btnMatricula.setEnabled(true);
							principal.btnPrematricula.setEnabled(true);
						} else {
							if (consultas_usuario.rol.equals("2")) {
								principal.btnAlumnos.setEnabled(true);
								principal.btnUsuarios.setEnabled(false);
								principal.btnMatricula.setEnabled(false);
								principal.btnPrematricula.setEnabled(false);

							} else {
								if (consultas_usuario.rol.equals("3")) {
									principal.btnAlumnos.setEnabled(true);
									principal.btnUsuarios.setEnabled(true);
									principal.btnMatricula.setEnabled(true);
									principal.btnPrematricula.setEnabled(true);

								} else {
									principal.btnAlumnos.setEnabled(false);
									principal.btnUsuarios.setEnabled(false);
									principal.btnMatricula.setEnabled(false);
									principal.btnPrematricula.setEnabled(false);
									principal.btnComprobar.setEnabled(false);
									principal.btnImprimir.setEnabled(false);

								}

							}

						}
						dispose();
					} else {
						lblAlerta.setText("El usuario y contraseña son incorrectas");
						lblAlerta.setForeground(Color.RED);
					}
				}
			}
		}

	}
}
