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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class login extends JFrame {

	public JPanel contentPane;
	public static JTextField txtUsuario;
	public static JPasswordField txtContrase�a;
	public JLabel lblAlerta;
	public JButton btnIngresar;
	public static JLabel lblNombreEmpresa;
	public static String nombre = null;
	public static String frase = null;
	public static String ipServidor = null;
	public static String ruta_logo = null;
	public static JRadioButton rdbtnPass;
	public static JLabel lblestadocontrase�a;
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
		lblLoginSistemaAdministrativo.setBounds(79, 0, 333, 27);
		contentPane.add(lblLoginSistemaAdministrativo);
		lblLoginSistemaAdministrativo.setForeground(Color.BLACK);
		lblLoginSistemaAdministrativo.setBackground(Color.WHITE);
		lblLoginSistemaAdministrativo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSistemaAdministrativo.setFont(new Font("Cambria", Font.BOLD, 18));

		JPanel panel = new JPanel();
		panel.setBounds(79, 24, 333, 298);
		contentPane.add(panel);
		panel.setBackground(new Color(0, 0, 0, 100));
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setForeground(SystemColor.window);
		lblUsuario.setFont(new Font("Cambria", Font.BOLD, 15));
		lblUsuario.setBounds(136, 165, 108, 20);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a :");
		lblContrasea.setForeground(SystemColor.window);
		lblContrasea.setFont(new Font("Cambria", Font.BOLD, 15));
		lblContrasea.setBounds(125, 204, 98, 20);
		panel.add(lblContrasea);

		lblestadocontrase�a = new JLabel("");
		lblestadocontrase�a.setBackground(Color.WHITE);
		lblestadocontrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		lblestadocontrase�a.setBounds(278, 225, 21, 20);
		panel.add(lblestadocontrase�a);
		final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage().getScaledInstance(
				lblestadocontrase�a.getWidth(), lblestadocontrase�a.getHeight(), Image.SCALE_DEFAULT));
		lblestadocontrase�a.setIcon(iconoocultar);

		txtUsuario = new JTextField(){
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth() - 1;
					int h = getHeight() - 1;
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w, h, h, h);
					g2.setPaint(Color.GRAY);
					g2.drawRoundRect(0, 0, w, h, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}

			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			}
		};
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

		btnIngresar = new JButton("Ingresar"){
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth() - 1;
					int h = getHeight() - 1;
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("Button.background"));
					g2.fillRoundRect(0, 0, w, h, h, h);
					g2.setPaint(Color.GRAY);
					g2.drawRoundRect(0, 0, w, h, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}

			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			}
		};
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setFont(new Font("Cambria", Font.BOLD, 15));
		btnIngresar.setBackground(new Color(0, 100, 0));
		btnIngresar.setBounds(74, 265, 181, 22);
		panel.add(btnIngresar);

		txtContrase�a = new JPasswordField(){
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth() - 1;
					int h = getHeight() - 1;
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w, h, h, h);
					g2.setPaint(Color.GRAY);
					g2.drawRoundRect(0, 0, w, h, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}

			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			}
		};
		txtContrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrase�a.setFont(new Font("Cambria", Font.BOLD, 14));
		txtContrase�a.setBounds(74, 225, 181, 20);
		panel.add(txtContrase�a);
		InputMap map5 = txtContrase�a.getInputMap(JComponent.WHEN_FOCUSED);
		txtContrase�a.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContrase�a.getText().length() == 15)
					e.consume();

				if (txtContrase�a.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContrase�a.setText("");
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
		lblNombreEmpresa.setForeground(SystemColor.window);
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreEmpresa.setFont(new Font("Cambria", Font.BOLD, 15));
		lblNombreEmpresa.setBounds(10, 0, 303, 33);
		panel.add(lblNombreEmpresa);

		rdbtnPass = new JRadioButton("");
		rdbtnPass.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPass.setBackground(Color.WHITE);
		rdbtnPass.setBounds(257, 225, 21, 20);
		panel.add(rdbtnPass);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(83, 29, 161, 137);
		panel.add(lblLogo);
		final ImageIcon iconouser = new ImageIcon(
				logo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(iconouser);
		
		lblAlerta = new JLabel("");
		lblAlerta.setForeground(SystemColor.window);
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
					txtContrase�a.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(
							lblestadocontrase�a.getWidth(), lblestadocontrase�a.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontrase�a.setIcon(iconover);
				} else {
					txtContrase�a.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage().getScaledInstance(
							lblestadocontrase�a.getWidth(), lblestadocontrase�a.getHeight(), Image.SCALE_DEFAULT));
					lblestadocontrase�a.setIcon(iconoocultar);
					setBackground(Color.BLACK);
				}
			}
		});

	}

	@SuppressWarnings("unlikely-arg-type")
	public void iniciarSesion() {
		principal principal = new principal();
		String user = String.valueOf(txtUsuario.getText().toString());
		String pass = String.valueOf(txtContrase�a.getText().toString());
		if (user.equals("") && pass.equals("")) {
			lblAlerta.setText("Los campos (Usuario) y (Contrase�a) estan vacios.");
			lblAlerta.setForeground(Color.WHITE);
		} else {
			if (user.equals("")) {
				lblAlerta.setText("El campo de (Usuario) esta vacio.");
				lblAlerta.setForeground(Color.WHITE);
			} else {
				if (pass.equals("")) {
					lblAlerta.setText("El campo de (Contrase�a) esta vacio.");
					lblAlerta.setForeground(Color.WHITE);
				} else {
					consultas_usuario consulta = new consultas_usuario();
					clases.usuarios clase = new clases.usuarios();
					clase.setNombre_Usuario(txtUsuario.getText().toString());
					clase.setContrase�a_Usuario(txtContrase�a.getText().toString());
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
						lblAlerta.setText("El usuario y contrase�a son incorrectas");
						lblAlerta.setForeground(Color.WHITE);
					}
				}
			}
		}

	}
}
