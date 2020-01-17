package ventanas;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import clases.alumnos;
import conexion.conexion;
import consultas.consultas_usuario;

import javax.swing.SwingConstants;

public class tabla_alumnos extends JFrame {
	public JScrollPane scrollFunciones;
	public JButton btnLlevarDatos;
	public static String hora_fecha_reporte;

	public static String nombreEmpresa = null;
	public static String totalDatos = null;

	public static String ruta;
	public static ImageIcon imagen;

	public JPanel contentPane;
	public JTextField txtBusquedaContratosEmpleados;
	public JScrollPane barraProductos;
	public JTable tablaProductos;

	public static String ruta_logo;
	public static JLabel label_2;

	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JButton btnAtras;
	public JButton button;
	private JLabel lblAlumnosRegistradosEn;

	public tabla_alumnos() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(0);
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAtras = new JButton("Regresar");
		btnAtras.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnAtras.setBackground(new Color(255, 127, 80));
		btnAtras.setBounds(582, 9, 102, 23);
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

		JPanel panelTablaCargos = new JPanel();
		panelTablaCargos.setLayout(null);
		panelTablaCargos.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panelTablaCargos.setBackground(Color.WHITE);
		panelTablaCargos.setBounds(10, 43, 674, 467);
		contentPane.add(panelTablaCargos);

		JLabel lblCargosRegistrados = new JLabel("Alumnos");
		lblCargosRegistrados.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		lblCargosRegistrados.setBounds(30, 11, 166, 19);
		panelTablaCargos.add(lblCargosRegistrados);

		JLabel lblBuscarContrato = new JLabel("Buscar Alumno :");
		lblBuscarContrato.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblBuscarContrato.setBounds(30, 33, 119, 22);
		panelTablaCargos.add(lblBuscarContrato);

		txtBusquedaContratosEmpleados = new JTextField();
		txtBusquedaContratosEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		txtBusquedaContratosEmpleados.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtBusquedaContratosEmpleados.setColumns(10);
		txtBusquedaContratosEmpleados.setBounds(138, 34, 330, 21);
		panelTablaCargos.add(txtBusquedaContratosEmpleados);
		InputMap map6 = txtBusquedaContratosEmpleados.getInputMap(JComponent.WHEN_FOCUSED);
		map6.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtBusquedaContratosEmpleados.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo = new TableRowSorter(tablaProductos.getModel());
				tablaProductos.setRowSorter(trsfiltroCodigo);
			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtBusquedaContratosEmpleados.getText().toString());
				txtBusquedaContratosEmpleados.setText(cadena);
				repaint();
				filtro();
			}
		});

		barraProductos = new JScrollPane(tablaProductos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelTablaCargos.add(barraProductos);
		barraProductos.setBounds(28, 66, 616, 379);

		tablaProductos = new JTable();
		barraProductos.setViewportView(tablaProductos);

		label_2 = new JLabel();
		label_2.setBounds(355, 41, 49, 44);
		panelTablaCargos.add(label_2);

		btnLlevarDatos = new JButton("Seleccionar Alumno");
		btnLlevarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int filaseleccionada;
				try {
					filaseleccionada = tablaProductos.getSelectedRow();
					if (filaseleccionada == -1) {
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
					} else {
						String nombres = tablaProductos.getValueAt(filaseleccionada, 0).toString();
						String apellidos = tablaProductos.getValueAt(filaseleccionada, 1).toString();
						String identidad = tablaProductos.getValueAt(filaseleccionada, 2).toString();
						String codigo = tablaProductos.getValueAt(filaseleccionada, 3).toString();
						String modalidad = tablaProductos.getValueAt(filaseleccionada, 4).toString();
						String pago = tablaProductos.getValueAt(filaseleccionada, 5).toString();
						String recibo = tablaProductos.getValueAt(filaseleccionada, 6).toString();

						principal formulario = new principal();
						formulario.setVisible(true);
						formulario.setLocationRelativeTo(null);
						formulario.txtNombre_Alumno.setText(nombres + " " + apellidos);
						formulario.txtIdentidad_Alumno.setText(identidad);
						formulario.txtModalidad.setText(modalidad);

						if (codigo.equals("null")) {
							formulario.txtCodigo_Matricula.setText("Comprobar");
						} else {
							formulario.txtCodigo_Matricula.setText(codigo);
						}

						if (pago.equals("0")) {
							formulario.txtVerificacion.setText("Comprobar");

						} else {
							formulario.txtVerificacion.setText(pago);
						}

						if (recibo.equals("0000")) {
							formulario.txtRecibo.setText("Comprobar");
						} else {
							formulario.txtRecibo.setText(recibo);
						}

						dispose();

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLlevarDatos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnLlevarDatos.setBackground(new Color(60, 179, 113));
		btnLlevarDatos.setBounds(478, 33, 166, 23);
		panelTablaCargos.add(btnLlevarDatos);

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

					String encabezado = "Reporte de alumnos registrados en la prematricula ";

					utilJTablePrint(tablaProductos, encabezado,
							"Pagina {0} de " + i + "          Matricula IDO 2020          " + fecha, true);

				}
			}
		});
		button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		button.setBackground(new Color(60, 179, 113));
		button.setBounds(310, 11, 158, 19);
		panelTablaCargos.add(button);

		lblAlumnosRegistradosEn = new JLabel("Alumnos registrados en la prematricula.");
		lblAlumnosRegistradosEn.setForeground(new Color(255, 255, 255));
		lblAlumnosRegistradosEn.setBackground(new Color(255, 255, 255));
		lblAlumnosRegistradosEn.setFont(new Font("Book Antiqua", Font.BOLD | Font.ITALIC, 15));
		lblAlumnosRegistradosEn.setBounds(45, 11, 284, 19);
		contentPane.add(lblAlumnosRegistradosEn);

	}

	public void construirTabla() {
		String titulos[] = { "Nombres", "Apellidos", "Identidad", "Codigo", "Modalidad", "Estado del pago",
				"Numero de recibo" };
		String informacion[][] = obtenerMatriz();
		tablaProductos = new JTable(informacion, titulos);
		barraProductos.setViewportView(tablaProductos);
		for (int c = 0; c < tablaProductos.getColumnCount(); c++) {
			Class<?> col_class = tablaProductos.getColumnClass(c);
			tablaProductos.setDefaultEditor(col_class, null);
			tablaProductos.getTableHeader().setReorderingAllowed(false);

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
		String matrizInfo[][] = new String[miLista.size()][7];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getNombres() + "";
			matrizInfo[i][1] = miLista.get(i).getApellidos() + "";
			matrizInfo[i][2] = miLista.get(i).getRNE_Alumno() + "";
			matrizInfo[i][3] = miLista.get(i).getCodigo() + "";
			matrizInfo[i][4] = miLista.get(i).getModalidad() + "";
			matrizInfo[i][5] = miLista.get(i).getEstado_Pago() + "";
			matrizInfo[i][6] = miLista.get(i).getNumero_Recibo() + "";
		}
		return matrizInfo;
	}

	public void filtro() {
		filtroCodigo = txtBusquedaContratosEmpleados.getText();
		trsfiltroCodigo
				.setRowFilter(RowFilter.regexFilter(txtBusquedaContratosEmpleados.getText(), 0, 1, 2, 3, 4, 5, 6));
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

	public void consultarEmpresa() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT direccion_logo_empresa FROM empresa where id_empresa = 1");

			if (rs.next()) {
				ruta_logo = (rs.getString("direccion_logo_empresa"));
				final ImageIcon logo = new ImageIcon(ruta_logo);

				final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(
						lblAlumnosRegistradosEn.getWidth(), lblAlumnosRegistradosEn.getHeight(), Image.SCALE_DEFAULT));
				lblAlumnosRegistradosEn.setIcon(icono);

				final ImageIcon icono2 = new ImageIcon(logo.getImage().getScaledInstance(label_2.getWidth(),
						label_2.getHeight(), Image.SCALE_DEFAULT));
				label_2.setIcon(icono2);
			} else {
				JOptionPane.showMessageDialog(null,
						"Para una mejor experiencia Personalice su empresa en :" + " MAS INFORMACIONS DE LA EMPRESA.");
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void establecerFechaRegistro() {
		try {
			LocalDate fechaActual = LocalDate.now();
			Date date = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
		} catch (Exception e) {

		}

	}

	public void obtenerTotalDatosReporte() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM Reg_Alumno ORDER BY id DESC");
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

}
