package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import clases.alumno;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.InputMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.conexion;
import consultas.consultas_alumnos;
import consultas.consultas_usuario;

import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class registro_alumnos extends JFrame {

	private JPanel contentPane;
	public JButton btnBuscar;
	public JPanel panelInformacion;
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
	public JButton btnImprimir;

	public JScrollPane barraAlumno;
	public JTable tablaAlumno;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JTextField txtid;
	public JTextField txtidentidad;
	public JTextField txtnombres;
	public JTextField txtapellidos;
	public JTextField txtnacimiento;
	public JTextField txtedad;
	public JTextField txtdireccion;
	public JTextField txtdistancia;
	public JTextField txtsexo;
	public JTextField txtcodigo;
	public JTextField txtmodalidad;
	public JTextField txtgrado;
	public JTextField txtgrupo;

	public JButton btnGuardar;
	public JButton btnActualizar;
	public JButton btnNuevo;

	public JButton btnCancelar;

	public registro_alumnos() {
		setType(Type.POPUP);
		setResizable(false);
		setBounds(100, 100, 850, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("ALUMNOS IDO 2020");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/iconos/estudiante.png")));
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 822, 309);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAdministracinYCorreccin = new JLabel("ALUMNOS");
		lblAdministracinYCorreccin.setForeground(new Color(0, 0, 128));
		lblAdministracinYCorreccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdministracinYCorreccin.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		lblAdministracinYCorreccin.setBounds(10, 0, 549, 44);
		panel.add(lblAdministracinYCorreccin);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 46, 800, 251);
		panel.add(panel_3);

		JLabel label_3 = new JLabel("Buscar Alumno :");
		label_3.setFont(new Font("Cambria", Font.BOLD, 14));
		label_3.setBounds(10, 0, 119, 22);
		panel_3.add(label_3);

		txtBuscar = new JTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setFont(new Font("Cambria", Font.BOLD, 14));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(10, 20, 600, 19);
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
		barraAlumno.setBounds(10, 41, 778, 198);
		barraAlumno.setBackground(Color.WHITE);

		tablaAlumno = new JTable();
		barraAlumno.setViewportView(tablaAlumno);

		JLabel label_4 = new JLabel();
		label_4.setBounds(355, 41, 49, 44);
		panel_3.add(label_4);

		JButton btnActualizarDatos = new JButton("Actualizar Datos");
		btnActualizarDatos.setFont(new Font("Cambria", Font.BOLD, 13));
		btnActualizarDatos.setBackground(new Color(60, 179, 113));
		btnActualizarDatos.setBounds(622, 21, 166, 19);
		panel_3.add(btnActualizarDatos);

		JButton btnMatricula = new JButton("Regresar");
		btnMatricula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
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

			}
		});
		btnMatricula.setFont(new Font("Cambria", Font.BOLD, 12));
		btnMatricula.setBackground(new Color(255, 127, 80));
		btnMatricula.setBounds(695, 9, 115, 23);
		panel.add(btnMatricula);

		btnActualizarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int filaseleccionada;
				try {
					filaseleccionada = tablaAlumno.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String id = tablaAlumno.getValueAt(filaseleccionada, 0).toString();
						String identidad = tablaAlumno.getValueAt(filaseleccionada, 1).toString();
						String nombres = tablaAlumno.getValueAt(filaseleccionada, 2).toString();
						String apellidos = tablaAlumno.getValueAt(filaseleccionada, 3).toString();
						String fecha = tablaAlumno.getValueAt(filaseleccionada, 4).toString();
						String edad = tablaAlumno.getValueAt(filaseleccionada, 5).toString();
						String direccion = tablaAlumno.getValueAt(filaseleccionada, 6).toString();
						String distancia = tablaAlumno.getValueAt(filaseleccionada, 7).toString();
						String sexo = tablaAlumno.getValueAt(filaseleccionada, 8).toString();
						String codigo = tablaAlumno.getValueAt(filaseleccionada, 9).toString();
						String modalidad = tablaAlumno.getValueAt(filaseleccionada, 10).toString();
						String grado = tablaAlumno.getValueAt(filaseleccionada, 11).toString();
						String grupo = tablaAlumno.getValueAt(filaseleccionada, 12).toString();

						txtid.setText(id);
						txtidentidad.setText(identidad);
						txtnombres.setText(nombres);
						txtapellidos.setText(apellidos);
						txtnacimiento.setText(fecha);
						txtedad.setText(edad);
						txtdireccion.setText(direccion);
						txtdistancia.setText(distancia);
						txtsexo.setText(sexo);
						txtcodigo.setText(codigo);
						txtmodalidad.setText(modalidad);
						txtgrado.setText(grado);
						txtgrupo.setText(grupo);

						btnActualizar.setVisible(true);
						btnGuardar.setVisible(false);
						btnNuevo.setVisible(false);

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panelInformacion = new JPanel();
		panelInformacion.setBackground(new Color(255, 255, 255));
		panelInformacion.setBounds(10, 325, 822, 292);
		contentPane.add(panelInformacion);
		panelInformacion.setLayout(null);

		JLabel lblDatosDelAlumno = new JLabel("DATOS DEL ALUMNO:");
		lblDatosDelAlumno.setForeground(new Color(0, 0, 128));
		lblDatosDelAlumno.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDelAlumno.setFont(new Font("Serif", Font.BOLD, 18));
		lblDatosDelAlumno.setBounds(63, 0, 263, 47);
		panelInformacion.add(lblDatosDelAlumno);

		JLabel lblNewLabel = new JLabel("Id:");
		lblNewLabel.setBounds(63, 47, 55, 26);
		panelInformacion.add(lblNewLabel);

		txtid = new JTextField();
		txtid.setFont(new Font("Cambria", Font.BOLD, 14));
		txtid.setEditable(false);
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setBounds(157, 47, 376, 26);
		panelInformacion.add(txtid);
		txtid.setColumns(10);

		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setBounds(63, 75, 55, 26);
		panelInformacion.add(lblIdentidad);

		txtidentidad = new JTextField();
		txtidentidad.setFont(new Font("Cambria", Font.BOLD, 14));
		txtidentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtidentidad.setColumns(10);
		txtidentidad.setBounds(157, 75, 376, 26);
		panelInformacion.add(txtidentidad);

		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(63, 103, 55, 26);
		panelInformacion.add(lblNombres);

		txtnombres = new JTextField();
		txtnombres.setFont(new Font("Cambria", Font.BOLD, 14));
		txtnombres.setHorizontalAlignment(SwingConstants.CENTER);
		txtnombres.setColumns(10);
		txtnombres.setBounds(157, 103, 376, 26);
		panelInformacion.add(txtnombres);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(63, 131, 55, 26);
		panelInformacion.add(lblApellidos);

		txtapellidos = new JTextField();
		txtapellidos.setFont(new Font("Cambria", Font.BOLD, 14));
		txtapellidos.setHorizontalAlignment(SwingConstants.CENTER);
		txtapellidos.setColumns(10);
		txtapellidos.setBounds(157, 131, 376, 26);
		panelInformacion.add(txtapellidos);

		JLabel lblFecha = new JLabel("Fecha Nac.");
		lblFecha.setBounds(63, 159, 93, 26);
		panelInformacion.add(lblFecha);

		txtnacimiento = new JTextField();
		txtnacimiento.setFont(new Font("Cambria", Font.BOLD, 14));
		txtnacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		txtnacimiento.setColumns(10);
		txtnacimiento.setBounds(157, 159, 376, 26);
		panelInformacion.add(txtnacimiento);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(551, 75, 55, 26);
		panelInformacion.add(lblEdad);

		txtedad = new JTextField();
		txtedad.setFont(new Font("Cambria", Font.BOLD, 14));
		txtedad.setHorizontalAlignment(SwingConstants.CENTER);
		txtedad.setColumns(10);
		txtedad.setBounds(644, 75, 114, 26);
		panelInformacion.add(txtedad);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(63, 187, 93, 26);
		panelInformacion.add(lblDireccion);

		txtdireccion = new JTextField();
		txtdireccion.setFont(new Font("Cambria", Font.BOLD, 14));
		txtdireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtdireccion.setColumns(10);
		txtdireccion.setBounds(157, 187, 376, 26);
		panelInformacion.add(txtdireccion);

		JLabel lblDistancia = new JLabel("Distancia:");
		lblDistancia.setBounds(63, 215, 93, 26);
		panelInformacion.add(lblDistancia);

		txtdistancia = new JTextField();
		txtdistancia.setFont(new Font("Cambria", Font.BOLD, 14));
		txtdistancia.setHorizontalAlignment(SwingConstants.CENTER);
		txtdistancia.setColumns(10);
		txtdistancia.setBounds(157, 215, 376, 26);
		panelInformacion.add(txtdistancia);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(551, 159, 55, 26);
		panelInformacion.add(lblSexo);

		txtsexo = new JTextField();
		txtsexo.setFont(new Font("Cambria", Font.BOLD, 14));
		txtsexo.setHorizontalAlignment(SwingConstants.CENTER);
		txtsexo.setColumns(10);
		txtsexo.setBounds(644, 159, 114, 26);
		panelInformacion.add(txtsexo);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(551, 47, 55, 26);
		panelInformacion.add(lblCodigo);

		txtcodigo = new JTextField();
		txtcodigo.setFont(new Font("Cambria", Font.BOLD, 14));
		txtcodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtcodigo.setColumns(10);
		txtcodigo.setBounds(644, 47, 114, 26);
		panelInformacion.add(txtcodigo);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setBounds(63, 243, 93, 26);
		panelInformacion.add(lblModalidad);

		txtmodalidad = new JTextField();
		txtmodalidad.setFont(new Font("Cambria", Font.BOLD, 14));
		txtmodalidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtmodalidad.setColumns(10);
		txtmodalidad.setBounds(157, 243, 376, 26);
		panelInformacion.add(txtmodalidad);

		JLabel lblGrado = new JLabel("Grado:");
		lblGrado.setBounds(551, 103, 55, 26);
		panelInformacion.add(lblGrado);

		txtgrado = new JTextField();
		txtgrado.setFont(new Font("Cambria", Font.BOLD, 14));
		txtgrado.setHorizontalAlignment(SwingConstants.CENTER);
		txtgrado.setColumns(10);
		txtgrado.setBounds(644, 103, 114, 26);
		panelInformacion.add(txtgrado);

		JLabel lblGrupo = new JLabel("Grupo:");
		lblGrupo.setBounds(551, 131, 55, 26);
		panelInformacion.add(lblGrupo);

		txtgrupo = new JTextField();
		txtgrupo.setFont(new Font("Cambria", Font.BOLD, 14));
		txtgrupo.setHorizontalAlignment(SwingConstants.CENTER);
		txtgrupo.setColumns(10);
		txtgrupo.setBounds(644, 131, 114, 26);
		panelInformacion.add(txtgrupo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(44, 12, 729, 268);
		panelInformacion.add(panel_1);
		panel_1.setLayout(null);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnActualizar.setBackground(new Color(60, 179, 113));
		btnActualizar.setBounds(510, 225, 207, 31);
		panel_1.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtid.getText().isEmpty() || txtidentidad.getText().isEmpty() || txtnombres.getText().isEmpty()
						|| txtapellidos.getText().isEmpty() || txtnacimiento.getText().isEmpty()
						|| txtedad.getText().isEmpty() || txtdireccion.getText().isEmpty()
						|| txtdistancia.getText().isEmpty() || txtsexo.getText().isEmpty()
						|| txtcodigo.getText().isEmpty() || txtmodalidad.getText().isEmpty()
						|| txtgrado.getText().isEmpty() || txtgrupo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el alumno!");
				} else {
					alumno clase = new alumno();
					consultas_alumnos consulta = new consultas_alumnos();
					clase.setRNE_Alumno(txtidentidad.getText().toString());
					clase.setNombres(txtnombres.getText().toString());
					clase.setApellidos(txtapellidos.getText().toString());
					clase.setFcha_Nac(txtnacimiento.getText().toString());
					clase.setEdad(txtedad.getText().toString());
					clase.setDireccion(txtdireccion.getText().toString());
					clase.setDistancia(txtdistancia.getText().toString());
					clase.setSexo(txtsexo.getText().toString());
					clase.setCodigo(txtcodigo.getText().toString());
					clase.setModalidad_Alumno(txtmodalidad.getText().toString());
					clase.setGrado_Alumno(txtgrado.getText().toString());
					clase.setId_Grupo(txtgrupo.getText().toString());
					clase.setRNE_Alumno(txtidentidad.getText().toString());

					if (consulta.actualizar(clase)) {
						JOptionPane.showMessageDialog(null, "Alumno actualizado!");
						construirTabla();
						limpiar();
						obtenerUltimoId();
					} else {
						JOptionPane.showMessageDialog(null, "Error! Alumno no actualizado");
						construirTabla();
						limpiar();
						obtenerUltimoId();
					}

				}

			}
		});

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(510, 225, 207, 31);
		panel_1.add(btnGuardar);
		btnGuardar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnGuardar.setBackground(new Color(60, 179, 113));
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtidentidad.getText().isEmpty() || txtnombres.getText().isEmpty()
						|| txtapellidos.getText().isEmpty() || txtnacimiento.getText().isEmpty()
						|| txtedad.getText().isEmpty() || txtdireccion.getText().isEmpty()
						|| txtdistancia.getText().isEmpty() || txtsexo.getText().isEmpty()
						|| txtcodigo.getText().isEmpty() || txtmodalidad.getText().isEmpty()
						|| txtgrado.getText().isEmpty() || txtgrupo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Porfavor llene los campos para guardar el alumno!");
				} else {
					alumno clase = new alumno();
					consultas_alumnos consulta = new consultas_alumnos();
					clase.setRNE_Alumno(txtidentidad.getText().toString());
					clase.setNombres(txtnombres.getText().toString());
					clase.setApellidos(txtapellidos.getText().toString());
					clase.setFcha_Nac(txtnacimiento.getText().toString());
					clase.setEdad(txtedad.getText().toString());
					clase.setDireccion(txtdireccion.getText().toString());
					clase.setDistancia(txtdistancia.getText().toString());
					clase.setSexo(txtsexo.getText().toString());
					clase.setCodigo(txtcodigo.getText().toString());
					clase.setModalidad_Alumno(txtmodalidad.getText().toString());
					clase.setGrado_Alumno(txtgrado.getText().toString());
					clase.setId_Grupo(txtgrupo.getText().toString());

					if (consulta.insertar(clase)) {
						JOptionPane.showMessageDialog(null, "Alumno registrado!");
						construirTabla();
						limpiar();
					} else {
						JOptionPane.showMessageDialog(null, "Error! Alumno no registrado");
						construirTabla();
						limpiar();
					}

				}

			}
		});

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setBounds(510, 188, 207, 31);
		panel_1.add(btnNuevo);
		btnNuevo.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNuevo.setBackground(new Color(255, 255, 255));
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiar();
				obtenerUltimoId();
			}
		});

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(510, 188, 207, 31);
		panel_1.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiar();
				obtenerUltimoId();
				construirTabla();
				btnActualizar.setVisible(false);
				btnNuevo.setVisible(true);
				btnGuardar.setVisible(true);
				btnCancelar.setVisible(false);

			}
		});

	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void construirTabla() {
		String titulos[] = { "Id", "Identidad", "Nombres", "Apellidos", "Fecha Nac.", "Edad", "Direccion", "Distancia",
				"Sexo", "Codigo", "Modalidad", "Grado", "Grupo" };
		String informacion[][] = obtenerMatriz();
		tablaAlumno = new JTable(informacion, titulos);
		barraAlumno.setViewportView(tablaAlumno);
		for (int c = 0; c < tablaAlumno.getColumnCount(); c++) {
			Class<?> col_class = tablaAlumno.getColumnClass(c);
			tablaAlumno.setDefaultEditor(col_class, null);
			tablaAlumno.getTableHeader().setReorderingAllowed(false);

		}
	}

	public static ArrayList<alumno> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<alumno> miLista = new ArrayList<alumno>();
		alumno alumno;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("select * from dbo.Reg_Alumno");

			while (rs.next()) {
				alumno = new alumno();
				alumno.setID_reg(Integer.parseInt(rs.getString("ID_reg")));
				alumno.setRNE_Alumno(rs.getString("RNE_Alumno"));
				alumno.setNombres(rs.getString("Nombres"));
				alumno.setApellidos(rs.getString("Apellidos"));
				alumno.setFcha_Nac(rs.getString("Fcha_Nac"));
				alumno.setEdad(rs.getString("Edad"));
				alumno.setDireccion(rs.getString("Direccion"));
				alumno.setDistancia(rs.getString("Distancia"));
				alumno.setSexo(rs.getString("Sexo"));
				alumno.setCodigo(rs.getString("Codigo"));
				alumno.setModalidad_Alumno(rs.getString("Modalidad_Alumno"));
				alumno.setGrado_Alumno(rs.getString("Grado_Alumno"));
				alumno.setId_Grupo(rs.getString("id_Grupo"));
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
		ArrayList<alumno> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][13];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getID_reg() + "";
			matrizInfo[i][1] = miLista.get(i).getRNE_Alumno() + "";
			matrizInfo[i][2] = miLista.get(i).getNombres() + "";
			matrizInfo[i][3] = miLista.get(i).getApellidos() + "";
			matrizInfo[i][4] = miLista.get(i).getFcha_Nac() + "";
			matrizInfo[i][5] = miLista.get(i).getEdad() + "";
			matrizInfo[i][6] = miLista.get(i).getDireccion() + "";
			matrizInfo[i][7] = miLista.get(i).getDistancia() + "";
			matrizInfo[i][8] = miLista.get(i).getSexo() + "";
			matrizInfo[i][9] = miLista.get(i).getCodigo() + "";
			matrizInfo[i][10] = miLista.get(i).getModalidad_Alumno() + "";
			matrizInfo[i][11] = miLista.get(i).getGrado_Alumno() + "";
			matrizInfo[i][12] = miLista.get(i).getId_Grupo() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo
				.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 0, 1, 2, 3, 4, 5, 6, 7));
	}

	public void limpiar() {
		txtid.setText("");
		txtidentidad.setText("");
		txtnombres.setText("");
		txtapellidos.setText("");
		txtnacimiento.setText("");
		txtedad.setText("");
		txtdireccion.setText("");
		txtdistancia.setText("");
		txtsexo.setText("");
		txtcodigo.setText("");
		txtmodalidad.setText("");
		txtgrado.setText("");
		txtgrupo.setText("");

	}

	public void obtenerUltimoId() {
		String ultimoValor = null;
		int valor;
		String id = null;
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM dbo.Reg_Alumno ORDER BY ID_Reg DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("ID_Reg");
				valor = Integer.parseInt(ultimoValor);
				valor = valor + 1;
				id = String.valueOf(valor);
			}
			txtid.setText(id);
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
