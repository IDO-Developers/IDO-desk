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
	public static String nombre = null;
	public static String frase = null;
	public static String ipServidor = null;
	public static String ruta_logo = null;
	public static JRadioButton rdbtnPass;
	public static JLabel lblestadocontraseña;
	public JButton btnActualizarBase;
	private JLabel label;

	public login() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 388);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/ido.png"));
		final ImageIcon fondo = new ImageIcon(getClass().getResource("/iconos/ido_foto.jpg"));
		final ImageIcon ver = new ImageIcon(getClass().getResource("/iconos/ver.png"));
		final ImageIcon ocultar = new ImageIcon(getClass().getResource("/iconos/ocultar.png"));

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(586, 426, 98, 34);
		contentPane.add(btnSalir);

		JLabel lblLoginSistemaAdministrativo = new JLabel("Sistema de  administraci\u00F3n IDO");
		lblLoginSistemaAdministrativo.setBounds(79, 0, 333, 34);
		contentPane.add(lblLoginSistemaAdministrativo);
		lblLoginSistemaAdministrativo.setForeground(Color.BLACK);
		lblLoginSistemaAdministrativo.setBackground(Color.WHITE);
		lblLoginSistemaAdministrativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSistemaAdministrativo.setFont(new Font("Cambria", Font.BOLD, 18));

		JPanel panel = new JPanel();
		panel.setBounds(79, 35, 333, 286);
		contentPane.add(panel);
		panel.setBackground(new Color(0, 0, 0, 100));
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Cambria", Font.BOLD, 15));
		lblUsuario.setBounds(136, 165, 108, 20);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setForeground(Color.WHITE);
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
		btnIngresar.setBounds(110, 249, 113, 27);
		panel.add(btnIngresar);

		txtContraseña = new JPasswordField();
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setFont(new Font("Cambria", Font.BOLD, 14));
		txtContraseña.setBounds(74, 225, 181, 20);
		panel.add(txtContraseña);
		InputMap map5 = txtContraseña.getInputMap(JComponent.WHEN_FOCUSED);
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

		lblNombreEmpresa = new JLabel("Instituto Departamental de Oriente.");
		lblNombreEmpresa.setForeground(Color.WHITE);
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEmpresa.setFont(new Font("Cambria", Font.BOLD, 15));
		lblNombreEmpresa.setBounds(10, 0, 303, 33);
		panel.add(lblNombreEmpresa);

		rdbtnPass = new JRadioButton("");
		rdbtnPass.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPass.setBackground(Color.WHITE);
		rdbtnPass.setBounds(256, 225, 21, 20);
		panel.add(rdbtnPass);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(83, 29, 161, 137);
		panel.add(lblLogo);
		final ImageIcon iconouser = new ImageIcon(
				logo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(iconouser);
		
		lblAlerta = new JLabel("");
		lblAlerta.setForeground(Color.BLACK);
		lblAlerta.setBounds(79, 321, 333, 27);
		contentPane.add(lblAlerta);
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setFont(new Font("Cambria", Font.BOLD, 14));
		

		label = new JLabel("");
		label.setBounds(-15, -12, 528, 388);
		contentPane.add(label);
		final ImageIcon icon = new ImageIcon(
				fondo.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icon);

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

	}

	@SuppressWarnings("unlikely-arg-type")
	public void iniciarSesion() {
		principal principal = new principal();
		String user = String.valueOf(txtUsuario.getText().toString());
		String pass = String.valueOf(txtContraseña.getText().toString());
		if (user.equals("") && pass.equals("")) {
			lblAlerta.setText("Los campos (Usuario) y (Contraseña) estan vacios.");
			lblAlerta.setForeground(Color.WHITE);
		} else {
			if (user.equals("")) {
				lblAlerta.setText("El campo de (Usuario) esta vacio.");
				lblAlerta.setForeground(Color.WHITE);
			} else {
				if (pass.equals("")) {
					lblAlerta.setText("El campo de (Contraseña) esta vacio.");
					lblAlerta.setForeground(Color.WHITE);
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
						principal.contarDatos();
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
						lblAlerta.setForeground(Color.WHITE);
					}
				}
			}
		}

	}
}
