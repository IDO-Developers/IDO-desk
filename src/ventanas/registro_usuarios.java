package ventanas;

import java.awt.Color;

import java.awt.Event;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import clases.usuarios;
import conexion.conexion;
import consultas.consultas_usuario;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class registro_usuarios extends JFrame {
	public JScrollPane scrollFunciones;

	public JButton btnGuardar;
	public JButton btnNuevo;
	public JButton btnActualizarDatos;
	public JButton btnBorrar;
	public JButton btnActualizar;
	public JButton btnVer;
	public static String hora_fecha_reporte;
	public static String nombreEmpresa = null;
	public static String totalDatos = null;
	public static String identidad = null;
	public static String userRepetido = null;
	public static String id_rol = null;
	public static String nombre_rol = null;

	public static String ruta;
	public static String usuario;
	public static ImageIcon imagen;
	public static DefaultComboBoxModel modelo;

	public JPanel contentPane;
	public JTextField txtBusquedaDato;
	public JScrollPane barra;
	public JTable tabla;

	public static String ruta_logo;
	public static JLabel label_2;

	public static JComboBox<?> cbxTipoUsuario;

	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
	final ImageIcon ver = new ImageIcon(getClass().getResource("/iconos/ver.png"));
	final ImageIcon ocultar = new ImageIcon(getClass().getResource("/iconos/ocultar.png"));
	final ImageIcon usuarioLogo = new ImageIcon(getClass().getResource("/iconos/usuario.png"));

	public JButton btnAtras;
	public JButton button;
	public static JFormattedTextField txtIdentidad;
	public static int contador;
	private JLabel lblRegistroDeUsuarios;
	private JLabel lblUsuario;
	public JTextField txtUsuario;
	private JLabel lblContrasea;
	public JPasswordField txtContraseña;
	private JRadioButton radioButton;
	private JLabel label_3;
	private JTextField txtCodigo;
	private JLabel label;
	private JLabel lblDatos;
	private JLabel lblUsuario_1;;

	public registro_usuarios() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(0);
		setBounds(100, 100, 472, 635);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 61, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/iconos/logo_ido.png")));
		modelo = new DefaultComboBoxModel();

		btnAtras = new JButton("Regresar");
		btnAtras.setFont(new Font("Cambria", Font.BOLD, 12));
		btnAtras.setBackground(new Color(255, 127, 80));
		btnAtras.setBounds(355, 11, 102, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				principal principal = new principal();
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				principal.setTitle("Sistema de busqueda de codigos de matricula. IDO 2020");
				Timer time = new Timer();
				time.schedule(principal.tarea, 0, 1000);
				principal.construirTabla();
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
			}
		});
		scrollFunciones = new JScrollPane();

		JPanel panelRegistro = new JPanel();
		panelRegistro.setBackground(Color.WHITE);
		panelRegistro.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelRegistro.setBounds(10, 38, 447, 235);
		contentPane.add(panelRegistro);
		panelRegistro.setLayout(null);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				registro_usuarios usuarios = new registro_usuarios();
				usuarios.setVisible(true);
				usuarios.setLocationRelativeTo(null);
				usuarios.construirTabla();
				usuarios.obtenerUltimoId();
				usuarios.btnBorrar.setVisible(false);
				usuarios.btnActualizar.setVisible(false);
				registro_usuarios.llena_combo();
				usuarios.cargarIdRol();
			}
		});
		btnNuevo.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNuevo.setBounds(28, 197, 91, 23);
		panelRegistro.add(btnNuevo);
		btnNuevo.setBackground(new Color(255, 255, 255));

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				validarUsuarioPorIdentidad();
				if (txtIdentidad.getText().isEmpty() || txtUsuario.getText().isEmpty()
						|| txtContraseña.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para guardar el usuario!");
				} else {
					if (registro_usuarios.txtIdentidad.getText().toString().equals(identidad)) {
						JOptionPane.showMessageDialog(null,
								"Se encontrado un registro con esta identidad : " + identidad,
								"Alerta!\n" + "Nota: Solo debe de haber 1 usuario por identidad",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						if (txtUsuario.getText().toString().equals(userRepetido)) {
							JOptionPane.showMessageDialog(null,
									"Se encontrado un registro con esta Usuario : " + userRepetido,
									"Alerta!\n" + "Nota: Solo debe de haber 1 usuario con este nombre",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							usuarios clase = new usuarios();
							consultas_usuario consulta = new consultas_usuario();
							clase.setNombre_Usuario(txtUsuario.getText().toString());
							clase.setId_Rol(id_rol);
							clase.setContraseña_Usuario(txtContraseña.getText().toString());
							clase.setRNE_Empleado(txtIdentidad.getText().toString());
							if (consulta.insertar(clase)) {
								JOptionPane.showMessageDialog(null, "Usuario registrado!");
								txtIdentidad.setText("");
								txtUsuario.setText("");
								txtContraseña.setText("");
								construirTabla();
								obtenerUltimoId();
							} else {
								JOptionPane.showMessageDialog(null, "Error! Usuario no registrado");
								txtIdentidad.setText("");
								txtUsuario.setText("");
								txtContraseña.setText("");
								construirTabla();
								obtenerUltimoId();
							}
						}

					}
				}
			}

		});
		btnGuardar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnGuardar.setBounds(312, 197, 99, 23);
		panelRegistro.add(btnGuardar);
		btnGuardar.setBackground(new Color(60, 179, 113));

		JLabel lblTipo = new JLabel("1. N\u00B0 de identidad :");
		lblTipo.setFont(new Font("Cambria", Font.BOLD, 12));
		lblTipo.setBounds(28, 52, 120, 23);
		panelRegistro.add(lblTipo);

		JLabel lblRegistroCargos = new JLabel("Datos del registro:");
		lblRegistroCargos.setBounds(28, 11, 143, 32);
		panelRegistro.add(lblRegistroCargos);
		lblRegistroCargos.setFont(new Font("Cambria", Font.BOLD, 12));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtIdentidad.getText().isEmpty() || txtUsuario.getText().isEmpty()
						|| txtContraseña.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el usuario!");
				} else {
					usuarios clase = new usuarios();
					consultas_usuario consulta = new consultas_usuario();

					clase.setNombre_Usuario(txtUsuario.getText().toString());
					clase.setId_Rol(id_rol);
					clase.setContraseña_Usuario(txtContraseña.getText().toString());
					clase.setRNE_Empleado(txtIdentidad.getText().toString());
					clase.setId(Integer.parseInt(txtCodigo.getText().toString()));

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "Usuario actualizado!");
						txtIdentidad.setText("");
						txtUsuario.setText("");
						txtContraseña.setText("");
						construirTabla();
						obtenerUltimoId();
					} else {
						JOptionPane.showMessageDialog(null, "Error! Usuario no actualizado");
						txtIdentidad.setText("");
						txtUsuario.setText("");
						txtContraseña.setText("");
						construirTabla();
						obtenerUltimoId();
					}

				}

			}
		});
		btnActualizar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnActualizar.setBackground(new Color(60, 179, 113));
		btnActualizar.setBounds(312, 197, 99, 23);
		panelRegistro.add(btnActualizar);

		MaskFormatter formato = null;
		try {
			formato = new MaskFormatter("####-####-#####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		MaskFormatter formatter1 = null;
		try {
			formatter1 = new MaskFormatter("####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JLabel lblCantidad = new JLabel("4. Rol :");
		lblCantidad.setFont(new Font("Cambria", Font.BOLD, 12));
		lblCantidad.setBounds(28, 154, 136, 20);
		panelRegistro.add(lblCantidad);
		final ImageIcon iconoFoto = new ImageIcon(getClass().getResource("/iconos/usuario.png"));

		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarIdRol();
			}
		});
		cbxTipoUsuario.setFont(new Font("Cambria", Font.BOLD, 12));
		cbxTipoUsuario.setBounds(158, 154, 174, 22);
		panelRegistro.add(cbxTipoUsuario);

		MaskFormatter formatter11 = null;
		try {
			formatter11 = new MaskFormatter("####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		MaskFormatter formato11 = null;
		try {
			formato11 = new MaskFormatter("##############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		MaskFormatter formato1 = null;
		try {
			formato1 = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtIdentidad = new JFormattedTextField(formato1);
		txtIdentidad.setFont(new Font("Cambria", Font.BOLD, 12));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(158, 52, 174, 23);
		panelRegistro.add(txtIdentidad);
		InputMap map4 = txtIdentidad.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtIdentidad.getText().length() == 15)
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

		lblUsuario = new JLabel("2. Usuario :");
		lblUsuario.setFont(new Font("Cambria", Font.BOLD, 12));
		lblUsuario.setBounds(28, 86, 99, 22);
		panelRegistro.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Cambria", Font.BOLD, 12));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(158, 86, 174, 23);
		panelRegistro.add(txtUsuario);
		InputMap map41 = txtUsuario.getInputMap(JComponent.WHEN_FOCUSED);
		map41.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

		lblContrasea = new JLabel("3. Contrase\u00F1a :");
		lblContrasea.setFont(new Font("Cambria", Font.BOLD, 12));
		lblContrasea.setBounds(28, 121, 136, 22);
		panelRegistro.add(lblContrasea);

		txtContraseña = new JPasswordField();
		txtContraseña.setFont(new Font("Cambria", Font.BOLD, 12));
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(158, 120, 174, 23);
		panelRegistro.add(txtContraseña);
		InputMap map411 = txtContraseña.getInputMap(JComponent.WHEN_FOCUSED);
		map411.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContraseña.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtContraseña.getText().length() == 15)
					ke.consume();

				if (txtContraseña.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContraseña.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		radioButton = new JRadioButton("");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(342, 123, 21, 20);
		panelRegistro.add(radioButton);
		radioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (radioButton.isSelected()) {
					txtContraseña.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(label_3.getWidth(),
							label_3.getHeight(), Image.SCALE_DEFAULT));
					label_3.setIcon(iconover);
				} else {
					txtContraseña.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage()
							.getScaledInstance(label_3.getWidth(), label_3.getHeight(), Image.SCALE_DEFAULT));
					label_3.setIcon(iconoocultar);
					setBackground(Color.BLACK);
				}
			}
		});

		label_3 = new JLabel("");
		label_3.setBounds(367, 123, 21, 20);
		panelRegistro.add(label_3);
		final ImageIcon iconoocultar = new ImageIcon(
				ocultar.getImage().getScaledInstance(label_3.getWidth(), label_3.getHeight(), Image.SCALE_DEFAULT));
		label_3.setIcon(iconoocultar);

		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Cambria", Font.BOLD, 12));
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(158, 16, 30, 27);
		panelRegistro.add(txtCodigo);

		label = new JLabel();
		label.setBounds(361, 11, 62, 56);
		panelRegistro.add(label);
		final ImageIcon iconouser = new ImageIcon(
				usuarioLogo.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(iconouser);

		lblDatos = new JLabel("Datos");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setFont(new Font("Cambria", Font.BOLD, 12));
		lblDatos.setBounds(342, 67, 99, 22);
		panelRegistro.add(lblDatos);

		lblUsuario_1 = new JLabel("Usuario");
		lblUsuario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblUsuario_1.setBounds(342, 83, 99, 22);
		panelRegistro.add(lblUsuario_1);

		InputMap map22 = txtIdentidad.getInputMap(JComponent.WHEN_FOCUSED);
		map22.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
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

		MaskFormatter formatooo = null;
		try {
			formatooo = new MaskFormatter("####-####-#####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		final ImageIcon usu = new ImageIcon(getClass().getResource("/iconos/usuario.png"));

		JPanel panelTablaCargos = new JPanel();
		panelTablaCargos.setLayout(null);
		panelTablaCargos.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelTablaCargos.setBackground(Color.WHITE);
		panelTablaCargos.setBounds(10, 284, 447, 306);
		contentPane.add(panelTablaCargos);

		JLabel lblCargosRegistrados = new JLabel("Usuarios registrados :");
		lblCargosRegistrados.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		lblCargosRegistrados.setBounds(10, 11, 166, 19);
		panelTablaCargos.add(lblCargosRegistrados);

		JLabel lblBuscar = new JLabel("Buscar Usuario :");
		lblBuscar.setFont(new Font("Cambria", Font.BOLD, 12));
		lblBuscar.setBounds(10, 33, 119, 22);
		panelTablaCargos.add(lblBuscar);

		txtBusquedaDato = new JTextField();
		txtBusquedaDato.setHorizontalAlignment(SwingConstants.CENTER);
		txtBusquedaDato.setFont(new Font("Cambria", Font.BOLD, 12));
		txtBusquedaDato.setColumns(10);
		txtBusquedaDato.setBounds(118, 34, 257, 21);
		panelTablaCargos.add(txtBusquedaDato);
		InputMap map6 = txtBusquedaDato.getInputMap(JComponent.WHEN_FOCUSED);
		map6.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtBusquedaDato.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo = new TableRowSorter(tabla.getModel());
				tabla.setRowSorter(trsfiltroCodigo);
				
				if (txtUsuario.getText().length() == 25)
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
				String cadena = (txtBusquedaDato.getText().toString());
				txtBusquedaDato.setText(cadena);
				repaint();
				filtro();
			}
		});
		
		

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement ps = null;
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						conexion objCon = new conexion();
						Connection conn = objCon.getConexion();
						int Fila = tabla.getSelectedRow();
						String codigo = tabla.getValueAt(Fila, 0).toString();
						ps = conn.prepareStatement("DELETE FROM dbo.Usuario WHERE id=?");
						ps.setString(1, codigo);
						ps.execute();
						JOptionPane.showMessageDialog(null, "Usuario Eliminado!");
						txtIdentidad.setText("");
						txtUsuario.setText("");
						txtContraseña.setText("");
						construirTabla();

					}

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al Eliminar");
					System.out.println(ex.toString());
				}
			}
		});
		btnBorrar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnBorrar.setBackground(new Color(220, 20, 60));
		btnBorrar.setBounds(10, 270, 99, 23);
		panelTablaCargos.add(btnBorrar);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelTablaCargos.add(barra);
		barra.setBounds(10, 66, 426, 193);

		tabla = new JTable();
		barra.setViewportView(tabla);

		label_2 = new JLabel();
		label_2.setBounds(405, 50, 49, 44);
		panelTablaCargos.add(label_2);

		btnActualizarDatos = new JButton("Actualizar Datos");
		btnActualizarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String id = tabla.getValueAt(filaseleccionada, 0).toString();
						String usuario = tabla.getValueAt(filaseleccionada, 1).toString();
						String contraseña = tabla.getValueAt(filaseleccionada, 2).toString();
						String identidad = tabla.getValueAt(filaseleccionada, 3).toString();
						String rol = tabla.getValueAt(filaseleccionada, 4).toString();

						txtCodigo.setText(id);
						txtUsuario.setText(usuario);
						txtContraseña.setText(contraseña);
						txtIdentidad.setText(identidad);

						conexion conex = new conexion();
						try {

							Statement estatuto = conex.getConexion().createStatement();
							ResultSet rs = estatuto
									.executeQuery("Select Nombre_Rol from Roles where id_Rol='" + rol + "'");
							while (rs.next()) {
								nombre_rol = rs.getString("Nombre_Rol");
							}
						} catch (SQLException ex) {
							Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
							JOptionPane.showMessageDialog(null, ex);
						}

						cbxTipoUsuario.setSelectedItem(nombre_rol);

						txtUsuario.setEditable(true);
						txtContraseña.setEditable(true);
						txtIdentidad.setEditable(true);

						btnNuevo.setVisible(true);
						btnGuardar.setVisible(false);
						btnActualizar.setVisible(true);
						btnActualizarDatos.setVisible(true);
						btnVer.setVisible(false);
						btnBorrar.setVisible(true);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnActualizarDatos.setFont(new Font("Cambria", Font.BOLD, 12));
		btnActualizarDatos.setBackground(new Color(60, 179, 113));
		btnActualizarDatos.setBounds(299, 270, 137, 23);
		panelTablaCargos.add(btnActualizarDatos);

		btnVer = new JButton("Ver detalles");
		btnVer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String id = tabla.getValueAt(filaseleccionada, 0).toString();
						String usuario = tabla.getValueAt(filaseleccionada, 1).toString();
						String contraseña = tabla.getValueAt(filaseleccionada, 2).toString();
						String identidad = tabla.getValueAt(filaseleccionada, 3).toString();
						String rol = tabla.getValueAt(filaseleccionada, 4).toString();

						conexion conex = new conexion();
						try {

							Statement estatuto = conex.getConexion().createStatement();
							ResultSet rs = estatuto
									.executeQuery("Select Nombre_Rol from Roles where id_Rol='" + rol + "'");
							while (rs.next()) {
								nombre_rol = rs.getString("Nombre_Rol");
							}
						} catch (SQLException ex) {
							Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
							JOptionPane.showMessageDialog(null, ex);
						}

						txtCodigo.setText(id);
						txtUsuario.setText(usuario);
						txtContraseña.setText(contraseña);
						txtIdentidad.setText(identidad);
						cbxTipoUsuario.setSelectedItem(nombre_rol);

						txtUsuario.setEditable(false);
						txtContraseña.setEditable(false);
						txtIdentidad.setEditable(false);

						btnActualizar.setVisible(false);
						btnGuardar.setVisible(false);
						btnBorrar.setVisible(false);
						btnActualizarDatos.setVisible(false);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnVer.setFont(new Font("Cambria", Font.BOLD, 12));
		btnVer.setBackground(new Color(0, 206, 209));
		btnVer.setBounds(181, 270, 108, 23);
		panelTablaCargos.add(btnVer);

		button = new JButton("Imprimir Reporte");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerTotalDatosReporte();
				if (totalDatos == null) {
					JOptionPane.showMessageDialog(null, "No hay registros disponibles para imprimir un reporte");
				} else {
					String ampm;
					Calendar cal = new GregorianCalendar();
					ampm = cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
					String fecha = getFechaYHora() + ampm;
					int total = Integer.valueOf(totalDatos);
					String i = null;
					if (total <= 46) {
						i = "1";
					} else {
						if (total > 46 && total <= 92) {
							i = "2";
						} else {
							if (total > 92 && total <= 138) {
								i = "3";
							} else {
								if (total > 138 && total <= 184) {
									i = "4";
								} else {
									if (total > 184 && total <= 230) {
										i = "5";
									} else {
										if (total > 230 && total <= 276) {
											i = "6";
										} else {
											if (total > 276 && total <= 322) {
												i = "7";
											} else {
												if (total > 322 && total <= 368) {
													i = "8";
												} else {
													if (total > 368 && total <= 414) {
														i = "9";
													} else {
														if (total > 414 && total <= 460) {
															i = "10";
														} else {
															i = "Mas de 10 paginas";

														}

													}

												}
											}
										}
									}
								}
							}
						}
					}

					String encabezado = "Reporte de usuarios registrados";

					utilJTablePrint(tabla, encabezado,
							"Pagina {0} de " + i + "          Matricula IDO 2020          " + fecha, true);

				}
			}
		});
		button.setFont(new Font("Cambria", Font.BOLD, 12));
		button.setBackground(new Color(60, 179, 113));
		button.setBounds(238, 11, 137, 19);
		panelTablaCargos.add(button);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(377, 0, 70, 65);
		panelTablaCargos.add(lblNewLabel);
		final ImageIcon i = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(i);

		lblRegistroDeUsuarios = new JLabel("Registro de Usuarios.");
		lblRegistroDeUsuarios.setForeground(Color.WHITE);
		lblRegistroDeUsuarios.setFont(new Font("Cambria", Font.BOLD, 12));
		lblRegistroDeUsuarios.setBounds(10, 6, 249, 32);
		contentPane.add(lblRegistroDeUsuarios);

	}

	public void construirTabla() {
		String titulos[] = { "N°", "Usuario", "Contraseña", "Identidad", "Rol" };
		String informacion[][] = obtenerMatriz();
		tabla = new JTable(informacion, titulos);
		barra.setViewportView(tabla);
		for (int c = 0; c < tabla.getColumnCount(); c++) {
			Class<?> col_class = tabla.getColumnClass(c);
			tabla.setDefaultEditor(col_class, null);
			tabla.getTableHeader().setReorderingAllowed(false);

		}
	}

	public static ArrayList<usuarios> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<usuarios> miLista = new ArrayList<usuarios>();
		usuarios usuarios;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM dbo.Usuario;");

			while (rs.next()) {
				usuarios = new usuarios();
				usuarios.setId(Integer.parseInt(rs.getString("id")));
				usuarios.setNombre_Usuario(rs.getString("Nombre_Usuario"));
				usuarios.setContraseña_Usuario(rs.getString("Contraseña_Usuario"));
				usuarios.setRNE_Empleado(rs.getString("RNE_Empleado"));
				usuarios.setId_Rol(rs.getString("id_Rol"));
				miLista.add(usuarios);
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
		ArrayList<usuarios> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][5];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getNombre_Usuario() + "";
			matrizInfo[i][2] = miLista.get(i).getContraseña_Usuario() + "";
			matrizInfo[i][3] = miLista.get(i).getRNE_Empleado() + "";
			matrizInfo[i][4] = miLista.get(i).getId_Rol() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBusquedaDato.getText().toString();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)"+txtBusquedaDato.getText().toString(), 0, 1, 2, 3, 4));
	}

	public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
		boolean fitWidth = true;
		boolean interactive = true;
		JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
		try {
			boolean complete = jTable.print(mode, new MessageFormat(header), new MessageFormat(footer), showPrintDialog,
					null, interactive);
			if (complete) {
				JOptionPane.showMessageDialog(jTable, "Print complete (Impresión completa)",
						"Print result (Resultado de la impresión)", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jTable, "Print canceled (Impresión cancelada)",
						"Print result (Resultado de la impresión)", JOptionPane.WARNING_MESSAGE);
			}
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(jTable, "Print fail (Fallo de impresión): " + pe.getMessage(),
					"Print result (Resultado de la impresión)", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static String getFechaYHora() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("dd'/'MMMMM'/'yyyy HH:mm:ss ");
		date = cal.getTime();
		return df.format(date);
	}

	public void obtenerUltimoId() {
		String ultimoValor = null;
		int valor;
		String id = null;
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM dbo.Usuario ORDER BY id DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id");
				valor = Integer.parseInt(ultimoValor);
				valor = valor + 1;
				id = String.valueOf(valor);
			}
			txtCodigo.setText(id);
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void obtenerTotalDatosReporte() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM dbo.Usuario ORDER BY id DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validarUsuarioPorIdentidad() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT RNE_Empleado, Nombre_Usuario FROM dbo.Usuario where RNE_Empleado = '"
							+ registro_usuarios.txtIdentidad.getText().toString() + "'");

			if (rs.next()) {
				identidad = (rs.getString("RNE_Empleado"));
				userRepetido = (rs.getString("Nombre_Usuario"));
			}

			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException exx) {
			System.out.println(exx.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	@SuppressWarnings("unchecked")
	public static void llena_combo() {
		conexion conex = new conexion();
		try {
			modelo.removeAllElements();
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("Select * from Roles");

			while (rs.next()) {
				modelo.addElement(rs.getString("Nombre_Rol"));
			}
			cbxTipoUsuario.setModel(modelo);
		} catch (SQLException ex) {
			Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void cargarIdRol() {
		conexion conex = new conexion();
		try {

			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"Select id_Rol from Roles where Nombre_Rol='" + cbxTipoUsuario.getSelectedItem().toString() + "'");
			while (rs.next()) {
				id_rol = rs.getString("id_Rol");
			}
		} catch (SQLException ex) {
			Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

}
