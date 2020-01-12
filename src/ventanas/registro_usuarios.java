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
import javax.swing.JToggleButton;
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
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import clases.alumnos;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_usuario;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.Window.Type;

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

	public static String ruta;
	public static String usuario;
	public static ImageIcon imagen;

	public JPanel contentPane;
	public JTextField txtBusquedaDato;
	public JScrollPane barra;
	public JTable tabla;

	public static String ruta_logo;
	public static JLabel label_2;

	public JComboBox<?> cbxTipoUsuario;

	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
	final ImageIcon ver = new ImageIcon(getClass().getResource("/iconos/ver.png"));
	final ImageIcon ocultar = new ImageIcon(getClass().getResource("/iconos/ocultar.png"));

	public JButton btnAtras;
	public JButton button;
	public static JFormattedTextField txtIdentidad;
	public static int contador;
	private JLabel lblRegistroDeUsuarios;
	private JLabel lblUsuario;
	public JTextField txtUsuario;
	private JLabel lblContrasea;
	public JPasswordField txtContrase�a;
	private JRadioButton radioButton;
	private JLabel label_3;
	private JTextField txtCodigo;;

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

		btnAtras = new JButton("Regresar");
		btnAtras.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
			public void actionPerformed(ActionEvent e) {
				btnNuevo.setVisible(true);
				btnGuardar.setVisible(true);
				btnActualizar.setVisible(false);
				btnActualizarDatos.setVisible(true);
				btnVer.setVisible(true);
				btnBorrar.setVisible(false);
				txtIdentidad.setText("");
				txtUsuario.setText("");
				txtContrase�a.setText("");
				txtIdentidad.setEditable(true);
				txtUsuario.setEditable(true);
				txtContrase�a.setEditable(true);
				obtenerUltimoId();
			}
		});
		btnNuevo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnNuevo.setBounds(28, 197, 91, 23);
		panelRegistro.add(btnNuevo);
		btnNuevo.setBackground(new Color(255, 255, 255));

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validarUsuarioPorIdentidad();
				if (txtIdentidad.getText().isEmpty() || txtUsuario.getText().isEmpty()
						|| txtContrase�a.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para guardar el usuario!");
				} else {
					if (registro_usuarios.txtIdentidad.getText().toString().equals(identidad)) {
						JOptionPane.showMessageDialog(null,
								"Se encontrado un registro con esta identidad : " + identidad,
								"Alerta!\n" + "Nota: Solo debe de haber 1 usuario por identidad",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						usuarios clase = new usuarios();
						consultas_usuario consulta = new consultas_usuario();
						clase.setNombre_Usuario(txtUsuario.getText().toString());
						clase.setRol(cbxTipoUsuario.getSelectedItem().toString());
						clase.setContrase�a_Usuario(txtContrase�a.getText().toString());
						clase.setRNE_Administradores(txtIdentidad.getText().toString());
						if (consulta.insertar(clase)) {
							JOptionPane.showMessageDialog(null, "Usuario registrado!");
							txtIdentidad.setText("");
							txtUsuario.setText("");
							txtContrase�a.setText("");
							construirTabla();
							obtenerUltimoId();
						} else {
							JOptionPane.showMessageDialog(null, "Error! Usuario no registrado");
							txtIdentidad.setText("");
							txtUsuario.setText("");
							txtContrase�a.setText("");
							construirTabla();
							obtenerUltimoId();
						}

					}
				}
			}

		});
		btnGuardar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnGuardar.setBounds(312, 197, 99, 23);
		panelRegistro.add(btnGuardar);
		btnGuardar.setBackground(new Color(60, 179, 113));

		JLabel lblTipo = new JLabel("1. N\u00B0 de identidad :");
		lblTipo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblTipo.setBounds(28, 48, 120, 23);
		panelRegistro.add(lblTipo);

		JLabel lblRegistroCargos = new JLabel("Datos del registro:");
		lblRegistroCargos.setBounds(28, 11, 174, 32);
		panelRegistro.add(lblRegistroCargos);
		lblRegistroCargos.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdentidad.getText().isEmpty() || txtUsuario.getText().isEmpty()
						|| txtContrase�a.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el usuario!");
				} else {
					usuarios clase = new usuarios();
					consultas_usuario consulta = new consultas_usuario();

					clase.setNombre_Usuario(txtUsuario.getText().toString());
					clase.setRol(cbxTipoUsuario.getSelectedItem().toString());
					clase.setContrase�a_Usuario(txtContrase�a.getText().toString());
					clase.setRNE_Administradores(txtIdentidad.getText().toString());
					clase.setId(Integer.parseInt(txtCodigo.getText().toString()));

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "Usuario actualizado!");
						txtIdentidad.setText("");
						txtUsuario.setText("");
						txtContrase�a.setText("");
						construirTabla();
						obtenerUltimoId();
					} else {
						JOptionPane.showMessageDialog(null, "Error! Usuario no actualizado");
						txtIdentidad.setText("");
						txtUsuario.setText("");
						txtContrase�a.setText("");
						construirTabla();
						obtenerUltimoId();
					}

				}

			}
		});
		btnActualizar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
		lblCantidad.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblCantidad.setBounds(28, 152, 136, 22);
		panelRegistro.add(lblCantidad);
		final ImageIcon iconoFoto = new ImageIcon(getClass().getResource("/iconos/usuario.png"));

		cbxTipoUsuario = new JComboBox();
		cbxTipoUsuario.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		cbxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] { "Administrador", "Docente" }));
		cbxTipoUsuario.setBounds(199, 150, 212, 26);
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
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(199, 48, 212, 23);
		panelRegistro.add(txtIdentidad);

		lblUsuario = new JLabel("2. Usuario :");
		lblUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblUsuario.setBounds(28, 83, 99, 22);
		panelRegistro.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(199, 82, 160, 23);
		panelRegistro.add(txtUsuario);

		lblContrasea = new JLabel("3. Contrase\u00F1a :");
		lblContrasea.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblContrasea.setBounds(28, 119, 99, 22);
		panelRegistro.add(lblContrasea);

		txtContrase�a = new JPasswordField();
		txtContrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrase�a.setColumns(10);
		txtContrase�a.setBounds(199, 116, 160, 23);
		panelRegistro.add(txtContrase�a);

		radioButton = new JRadioButton("");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(365, 119, 21, 20);
		panelRegistro.add(radioButton);
		radioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (radioButton.isSelected()) {
					txtContrase�a.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(label_3.getWidth(),
							label_3.getHeight(), Image.SCALE_DEFAULT));
					label_3.setIcon(iconover);
				} else {
					txtContrase�a.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage()
							.getScaledInstance(label_3.getWidth(), label_3.getHeight(), Image.SCALE_DEFAULT));
					label_3.setIcon(iconoocultar);
					setBackground(Color.BLACK);
				}
			}
		});

		label_3 = new JLabel("");
		label_3.setBounds(390, 119, 21, 20);
		panelRegistro.add(label_3);
		final ImageIcon iconoocultar = new ImageIcon(
				ocultar.getImage().getScaledInstance(label_3.getWidth(), label_3.getHeight(), Image.SCALE_DEFAULT));
		label_3.setIcon(iconoocultar);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(199, 18, 26, 23);
		panelRegistro.add(txtCodigo);

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
		lblBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblBuscar.setBounds(10, 33, 119, 22);
		panelTablaCargos.add(lblBuscar);

		txtBusquedaDato = new JTextField();
		txtBusquedaDato.setHorizontalAlignment(SwingConstants.CENTER);
		txtBusquedaDato.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtBusquedaDato.getText());
				txtBusquedaDato.setText(cadena);
				repaint();
				filtro();
			}
		});

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
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
						txtContrase�a.setText("");
						construirTabla();

					}

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al Eliminar");
					System.out.println(ex.toString());
				}
			}
		});
		btnBorrar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String id = tabla.getValueAt(filaseleccionada, 0).toString();
						String usuario = tabla.getValueAt(filaseleccionada, 1).toString();
						String contrase�a = tabla.getValueAt(filaseleccionada, 2).toString();
						String identidad = tabla.getValueAt(filaseleccionada, 3).toString();
						String rol = tabla.getValueAt(filaseleccionada, 4).toString();

						txtCodigo.setText(id);
						txtUsuario.setText(usuario);
						txtContrase�a.setText(contrase�a);
						txtIdentidad.setText(identidad);
						cbxTipoUsuario.setSelectedItem(rol);
						
						txtUsuario.setEditable(true);
						txtContrase�a.setEditable(true);
						txtIdentidad.setEditable(true);
						cbxTipoUsuario.setEditable(true);
						
						btnNuevo.setVisible(true);
						btnGuardar.setVisible(false);
						btnActualizar.setVisible(true);
						btnActualizarDatos.setVisible(true);
						btnVer.setVisible(false);
						btnBorrar.setVisible(true);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInt�ntelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnActualizarDatos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnActualizarDatos.setBackground(new Color(60, 179, 113));
		btnActualizarDatos.setBounds(299, 270, 137, 23);
		panelTablaCargos.add(btnActualizarDatos);

		btnVer = new JButton("Ver detalles");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaseleccionada;
				try {
					filaseleccionada = tabla.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String id = tabla.getValueAt(filaseleccionada, 0).toString();
						String usuario = tabla.getValueAt(filaseleccionada, 1).toString();
						String contrase�a = tabla.getValueAt(filaseleccionada, 2).toString();
						String identidad = tabla.getValueAt(filaseleccionada, 3).toString();
						String rol = tabla.getValueAt(filaseleccionada, 4).toString();

						txtCodigo.setText(id);
						txtUsuario.setText(usuario);
						txtContrase�a.setText(contrase�a);
						txtIdentidad.setText(identidad);
						cbxTipoUsuario.setSelectedItem(rol);

						txtUsuario.setEditable(false);
						txtContrase�a.setEditable(false);
						txtIdentidad.setEditable(false);
						cbxTipoUsuario.setEditable(false);
						
						btnActualizar.setVisible(false);
						btnGuardar.setVisible(false);
						btnBorrar.setVisible(false);
						btnActualizarDatos.setVisible(false);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInt�ntelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnVer.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
					nombreEmpresa = login.nombre.toString();
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

					String encabezado = "Reporte de usuarios de " + login.nombre.toString();

					utilJTablePrint(tabla, encabezado, "Pagina {0} de " + i + "          Impreso por: "
							+ login.nombreCompletoUsuario.toString() + "          " + fecha, true);

				}
			}
		});
		button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
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
		lblRegistroDeUsuarios.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		lblRegistroDeUsuarios.setBounds(10, 6, 249, 32);
		contentPane.add(lblRegistroDeUsuarios);

	}

	public void construirTabla() {
		String titulos[] = { "N�", "Usuario", "Contrase�a", "Identidad", "Rol" };
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
				usuarios.setContrase�a_Usuario(rs.getString("Contrase�a_Usuario"));
				usuarios.setRNE_Administradores(rs.getString("RNE_Administradores"));
				usuarios.setRol(rs.getString("Rol"));
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
			matrizInfo[i][2] = miLista.get(i).getContrase�a_Usuario() + "";
			matrizInfo[i][3] = miLista.get(i).getRNE_Administradores() + "";
			matrizInfo[i][4] = miLista.get(i).getRol() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBusquedaDato.getText();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter(txtBusquedaDato.getText(), 0, 1, 2, 3, 4, 5, 6));
	}

	public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
		boolean fitWidth = true;
		boolean interactive = true;
		JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
		try {
			boolean complete = jTable.print(mode, new MessageFormat(header), new MessageFormat(footer), showPrintDialog,
					null, interactive);
			if (complete) {
				JOptionPane.showMessageDialog(jTable, "Print complete (Impresi�n completa)",
						"Print result (Resultado de la impresi�n)", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jTable, "Print canceled (Impresi�n cancelada)",
						"Print result (Resultado de la impresi�n)", JOptionPane.WARNING_MESSAGE);
			}
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(jTable, "Print fail (Fallo de impresi�n): " + pe.getMessage(),
					"Print result (Resultado de la impresi�n)", JOptionPane.ERROR_MESSAGE);
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
			PreparedStatement stmtr = conn
					.prepareStatement("SELECT * FROM dbo.Usuario ORDER BY id DESC");
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
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM usuario ORDER BY id_usuario DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				totalDatos = rsr.getString("id_usuario");
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
					.executeQuery("SELECT RNE_Administradores FROM dbo.Usuario where RNE_Administradores = '"
							+ registro_usuarios.txtIdentidad.getText().toString() + "'");

			if (rs.next()) {
				identidad = (rs.getString("RNE_Administradores"));
			}

			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException exx) {
			System.out.println(exx.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}
}
