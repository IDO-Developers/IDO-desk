package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import clases.alumnos;
import clases.alumnos2;
import clases.usuarios;

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
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.conexion;
import consultas.consultas_alumno;
import consultas.consultas_usuario;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class registro_alumnos extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNombres;
	public JButton btnBuscar;
	public JPanel panelInformacion;
	public static JComboBox comboBox;
	public JLabel lblRegistros;
	public JLabel lblIden;

	public static String nombres = null;
	public static String apellidos = null;
	public static String identidad = null;
	public static String codigo = null;
	public static String modalidad = null;
	public static String pago = null;
	public static String recibo = null;
	public static String IDENTIDADALUMNO = null;

	public static JTextField txtBuscar;
	public static JFormattedTextField txtIdentidad;
	public JButton btnActualizar;
	public JButton btnImprimir;

	public JScrollPane barraAlumno;
	public JTable tablaAlumno;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	private JTextField txtApellidos;

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
					principal.contarDatos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public registro_alumnos() {
		setType(Type.UTILITY);
		setResizable(false);
		setBounds(100, 100, 702, 516);
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
		panel.setBounds(10, 11, 674, 309);
		contentPane.add(panel);
		panel.setLayout(null);
		final ImageIcon logo1 = new ImageIcon(getClass().getResource("/iconos/prueba.png"));
		final ImageIcon logo_estudiante = new ImageIcon(getClass().getResource("/iconos/estudiante.png"));
		final ImageIcon logo222 = new ImageIcon(getClass().getResource("/iconos/escribir.png"));
		final ImageIcon logo2211 = new ImageIcon(getClass().getResource("/iconos/pre_matricula.png"));
		final ImageIcon logo22112 = new ImageIcon(getClass().getResource("/iconos/matricula.png"));
		final ImageIcon logo01 = new ImageIcon(getClass().getResource("/iconos/salida.png"));

		JLabel lblBuscarInformacionDe = new JLabel("Buscar informaci\u00F3n del alumno :");
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

		JLabel lblAdministracinYCorreccin = new JLabel(
				"Administraci\u00F3n y correcci\u00F3n de datos de los alumnos. ");
		lblAdministracinYCorreccin.setForeground(new Color(0, 0, 128));
		lblAdministracinYCorreccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdministracinYCorreccin.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblAdministracinYCorreccin.setBounds(10, 0, 549, 44);
		panel.add(lblAdministracinYCorreccin);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 55, 654, 243);
		panel.add(panel_3);

		JLabel label_3 = new JLabel("Buscar Alumno :");
		label_3.setFont(new Font("Cambria", Font.BOLD, 14));
		label_3.setBounds(245, 0, 119, 22);
		panel_3.add(label_3);

		txtBuscar = new JTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setFont(new Font("Cambria", Font.BOLD, 14));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(245, 20, 212, 19);
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
		barraAlumno.setBounds(10, 41, 634, 180);

		tablaAlumno = new JTable();
		barraAlumno.setViewportView(tablaAlumno);

		JLabel label_4 = new JLabel();
		label_4.setBounds(355, 41, 49, 44);
		panel_3.add(label_4);

		JButton button_3 = new JButton("Seleccionar Alumno");
		button_3.setFont(new Font("Cambria", Font.BOLD, 13));
		button_3.setBackground(new Color(60, 179, 113));
		button_3.setBounds(478, 21, 166, 19);
		panel_3.add(button_3);

		JLabel lblElegirTabla = new JLabel("Elegir tabla:");
		lblElegirTabla.setFont(new Font("Cambria", Font.BOLD, 14));
		lblElegirTabla.setBounds(10, 0, 119, 22);
		panel_3.add(lblElegirTabla);

		comboBox = new JComboBox<Object>();
		comboBox.setFont(new Font("Cambria", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "Matricula", "Prematricula" }));
		comboBox.setBounds(10, 21, 212, 18);
		panel_3.add(comboBox);

		lblRegistros = new JLabel("");
		lblRegistros.setForeground(new Color(0, 0, 128));
		lblRegistros.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistros.setFont(new Font("Cambria", Font.BOLD, 14));
		lblRegistros.setBounds(184, 217, 119, 26);
		panel_3.add(lblRegistros);

		JLabel label_5 = new JLabel("Total alumnos registrados :");
		label_5.setFont(new Font("Cambria", Font.BOLD, 14));
		label_5.setBounds(10, 217, 188, 26);
		panel_3.add(label_5);

		JButton button = new JButton("Regresar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				principal principal = new principal();
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				principal.setTitle("Sistema de busqueda de codigos de matricula. IDO 2020");
				Timer time = new Timer();
				time.schedule(principal.tarea, 0, 1000);
				principal.construirTabla();
				principal.contarDatos();
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
		button.setFont(new Font("Cambria", Font.BOLD, 12));
		button.setBackground(new Color(255, 127, 80));
		button.setBounds(562, 14, 102, 23);
		panel.add(button);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().equals("Matricula")) {
					construirTabla();
					contarDatos();
				} else {
					construirTabla2();
					contarDatos();
				}

			}
		});

		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int filaseleccionada;
				try {
					filaseleccionada = tablaAlumno.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String nombres = tablaAlumno.getValueAt(filaseleccionada, 0).toString();
						String apellidos = tablaAlumno.getValueAt(filaseleccionada, 1).toString();
						String identidad = tablaAlumno.getValueAt(filaseleccionada, 2).toString();
						lblIden.setText(identidad);

						txtNombres.setText(nombres);
						txtApellidos.setText(apellidos);
						txtIdentidad.setText(identidad);
						

						txtNombres.setEditable(true);
						txtApellidos.setEditable(true);
						txtIdentidad.setEditable(true);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panelInformacion = new JPanel();
		panelInformacion.setBackground(new Color(255, 255, 255));
		panelInformacion.setBounds(10, 332, 674, 141);
		contentPane.add(panelInformacion);
		panelInformacion.setLayout(null);

		JLabel lblNombreCompletoDel = new JLabel("Nombres :");
		lblNombreCompletoDel.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNombreCompletoDel.setBounds(10, 41, 106, 21);
		panelInformacion.add(lblNombreCompletoDel);

		JLabel lblIdentidadDelAlumno = new JLabel("Identidad :");
		lblIdentidadDelAlumno.setFont(new Font("Cambria", Font.BOLD, 14));
		lblIdentidadDelAlumno.setBounds(10, 105, 225, 21);
		panelInformacion.add(lblIdentidadDelAlumno);

		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Cambria", Font.BOLD, 14));
		txtNombres.setEditable(false);
		txtNombres.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombres.setBounds(115, 41, 372, 20);
		panelInformacion.add(txtNombres);
		txtNombres.setColumns(10);
		InputMap map5 = txtNombres.getInputMap(JComponent.WHEN_FOCUSED);
		map5.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtNombres.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombres.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (Character.isDigit(c)) {
					Toolkit.getDefaultToolkit().beep();
					ke.consume();
				}
				
				if (txtNombres.getText().length() == 30)
					ke.consume();

				if (txtNombres.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtNombres.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		MaskFormatter formato = null;
		try {
			formato = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtIdentidad = new JFormattedTextField(formato);
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setFont(new Font("Cambria", Font.BOLD, 14));
		txtIdentidad.setEditable(false);
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(115, 105, 372, 20);
		panelInformacion.add(txtIdentidad);
		InputMap map4 = txtIdentidad.getInputMap(JComponent.WHEN_FOCUSED);
		map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (txtIdentidad.getText().length() == 13)
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
		
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon logo22 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtIdentidad.getText().isEmpty() || txtNombres.getText().isEmpty()
						|| txtApellidos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el alumno!");
				} else {
					alumnos clase = new alumnos();
					alumnos2 clase2 = new alumnos2();
					consultas_alumno consulta = new consultas_alumno();

					if (comboBox.getSelectedItem().toString().equals("Matricula")) {
						
						clase.setNombres(txtNombres.getText().toString());
						clase.setApellidos(txtApellidos.getText().toString());
						clase.setRNE_Alumno(txtIdentidad.getText().toString());
						clase.setIdentidad(lblIden.getText().toString());

						if (consulta.actualizarAlumnmo(clase)) {
							JOptionPane.showMessageDialog(null, "Datos del Alumno actualizados!");
							txtIdentidad.setText("");
							txtNombres.setText("");
							txtApellidos.setText("");
							txtIdentidad.setEditable(false);
							txtNombres.setEditable(false);
							txtApellidos.setEditable(false);
							construirTabla();
							comboBox.setSelectedIndex(0);
							lblIden.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Error! Alumno no actualizado");
							txtIdentidad.setText("");
							txtNombres.setText("");
							txtApellidos.setText("");
							txtIdentidad.setEditable(false);
							txtNombres.setEditable(false);
							txtApellidos.setEditable(false);
							construirTabla();
							comboBox.setSelectedIndex(0);
							lblIden.setText("");
						}

					} else {
						
						clase2.setNombres_alumnos(txtNombres.getText().toString());
						clase2.setApellidos_alumnos(txtApellidos.getText().toString());
						clase2.setIdentidad_alumnos(txtIdentidad.getText().toString());
						clase2.setIdentidad(lblIden.getText().toString());

						if (consulta.actualizarAlumno2(clase2)) {
							JOptionPane.showMessageDialog(null, "Datos del Alumno actualizados!");
							txtIdentidad.setText("");
							txtNombres.setText("");
							txtApellidos.setText("");
							txtIdentidad.setEditable(false);
							txtNombres.setEditable(false);
							txtApellidos.setEditable(false);
							construirTabla2();
							comboBox.setSelectedIndex(1);
							lblIden.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Error! Alumno no actualizado");
							txtIdentidad.setText("");
							txtNombres.setText("");
							txtApellidos.setText("");
							txtIdentidad.setEditable(false);
							txtNombres.setEditable(false);
							txtApellidos.setEditable(false);
							construirTabla2();
							comboBox.setSelectedIndex(1);
							lblIden.setText("");
						}
					}

				}

			}
		});
		btnActualizar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnActualizar.setBackground(new Color(60, 179, 113));
		btnActualizar.setBounds(499, 105, 167, 21);
		panelInformacion.add(btnActualizar);

		JLabel lblDatosDelAlumno = new JLabel("Datos del alumno :");
		lblDatosDelAlumno.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDelAlumno.setFont(new Font("Serif", Font.BOLD, 18));
		lblDatosDelAlumno.setBounds(10, 0, 263, 41);
		panelInformacion.add(lblDatosDelAlumno);

		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setFont(new Font("Cambria", Font.BOLD, 14));
		lblApellidos.setBounds(10, 73, 106, 21);
		panelInformacion.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellidos.setFont(new Font("Cambria", Font.BOLD, 14));
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(115, 73, 372, 20);
		panelInformacion.add(txtApellidos);
		InputMap map11 = txtApellidos.getInputMap(JComponent.WHEN_FOCUSED);
		map11.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellidos.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				char c = ke.getKeyChar();
				if (Character.isDigit(c)) {
					Toolkit.getDefaultToolkit().beep();
					ke.consume();
				}
				
				if (txtApellidos.getText().length() == 40)
					ke.consume();

				if (txtApellidos.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtApellidos.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		JLabel label = new JLabel("");
		label.setFont(new Font("Cambria", Font.BOLD, 14));
		label.setBounds(538, 12, 89, 84);
		panelInformacion.add(label);
		final ImageIcon iconouser = new ImageIcon(
				logo_estudiante.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(iconouser);
		
		lblIden = new JLabel("");
		lblIden.setForeground(new Color(0, 0, 128));
		lblIden.setHorizontalAlignment(SwingConstants.CENTER);
		lblIden.setFont(new Font("Serif", Font.BOLD, 18));
		lblIden.setBounds(173, 0, 263, 41);
		panelInformacion.add(lblIden);

	}

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
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
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

	public void construirTabla2() {
		String titulos[] = { "Nombres", "Apellidos", "Identidad", "Codigo", "Modalidad", "Grado", "Estado del pago",
				"Numero de recibo" };
		String informacion[][] = obtenerMatriz2();
		tablaAlumno = new JTable(informacion, titulos);
		barraAlumno.setViewportView(tablaAlumno);
		for (int c = 0; c < tablaAlumno.getColumnCount(); c++) {
			Class<?> col_class = tablaAlumno.getColumnClass(c);
			tablaAlumno.setDefaultEditor(col_class, null);
			tablaAlumno.getTableHeader().setReorderingAllowed(false);

		}
	}

	public static ArrayList<alumnos2> buscarUsuariosConMatriz2() {
		conexion conex = new conexion();
		ArrayList<alumnos2> miLista = new ArrayList<alumnos2>();
		alumnos2 alumnos2;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from dbo.prematricula2019_2020");

			while (rs.next()) {
				alumnos2 = new alumnos2();
				alumnos2.setNombres_alumnos(rs.getString("Nombres_alumnos"));
				alumnos2.setApellidos_alumnos(rs.getString("Apellidos_alumnos"));
				alumnos2.setIdentidad_alumnos(rs.getString("Identidad_alumnos"));
				alumnos2.setCodigo(rs.getString("codigo"));
				alumnos2.setModalidad(rs.getString("Modalidad"));
				alumnos2.setGrado(rs.getString("Grado"));
				alumnos2.setEstado_Pago(rs.getString("Estado_Pago"));
				alumnos2.setNumero_Recibo(rs.getString("Numero_Recibo"));
				miLista.add(alumnos2);
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

	public static String[][] obtenerMatriz2() {
		ArrayList<alumnos2> miLista = buscarUsuariosConMatriz2();
		String matrizInfo[][] = new String[miLista.size()][8];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getNombres_alumnos() + "";
			matrizInfo[i][1] = miLista.get(i).getApellidos_alumnos() + "";
			matrizInfo[i][2] = miLista.get(i).getIdentidad_alumnos() + "";
			matrizInfo[i][3] = miLista.get(i).getCodigo() + "";
			matrizInfo[i][4] = miLista.get(i).getModalidad() + "";
			matrizInfo[i][5] = miLista.get(i).getGrado() + "";
			matrizInfo[i][6] = miLista.get(i).getEstado_Pago() + "";
			matrizInfo[i][7] = miLista.get(i).getNumero_Recibo() + "";
		}
		return matrizInfo;
	}

	public void contarDatos() {
		int registros = 0;
		registros = tablaAlumno.getRowCount();
		lblRegistros.setText(String.valueOf(registros));

	}
}
