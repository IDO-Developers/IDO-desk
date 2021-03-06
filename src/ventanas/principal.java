package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import clases.alumnos;
import clases.alumnos2;

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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.conexion;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class principal extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNombre_Alumno;
	public static JTextField txtIdentidad_Alumno;
	public static JTextField txtCodigo_Matricula;
	public JButton btnBuscar;
	public JPanel panelInformacion;
	public static JComboBox comboBox;
	public static JComboBox comboBox_1;
	public JButton btnUsuarios;
	public JButton btnAlumnos;
	public JButton btnMatricula;
	public JButton btnPrematricula;
	public JLabel lblRegistros;
	public JLabel lblTotalAlumnosDe;
	public JLabel lblContGrados;

	public static String nombres = null;
	public static String apellidos = null;
	public static String identidad = null;
	public static String codigo = null;
	public static String modalidad = null;
	public static String pago = null;
	public static String recibo = null;

	public static JTextField txtBuscar;
	public static JTextField txtGrado;
	public static JTextField txtModalidad;
	public static JTextField txtVerificacion;
	public static JTextField txtRecibo;
	public static JLabel lblHoraSistema;
	public static JLabel lblFecha;
	public JButton btnComprobar;
	public JButton btnImprimir;

	public JScrollPane barraAlumno;
	public JTable tablaAlumno;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					principal principal = new principal();
					principal.setVisible(true);
					principal.setLocationRelativeTo(null);
					principal.setVisible(true);
					principal.construirTabla();
					Timer time = new Timer();
					time.schedule(principal.tarea, 0, 1000);
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
	public principal() {
		setType(Type.UTILITY);
		setResizable(false);
		setBounds(100, 100, 814, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#212121"));
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
		panel.setBackground(Color.decode("#BDBDBD"));
		panel.setBounds(10, 11, 674, 309);
		contentPane.add(panel);
		panel.setLayout(null);

		MaskFormatter formato1 = null;
		try {
			formato1 = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		final ImageIcon logo2 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));

		JLabel label_1 = new JLabel(
				"Bienvenido al sistema de b\u00FAsqueda de c\u00F3digos de matr\u00EDcula IDO 2020.");
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		label_1.setBounds(10, 0, 654, 44);
		panel.add(label_1);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 36, 654, 262);
		panel.add(panel_3);

		JLabel label_3 = new JLabel("Buscar Alumno :");
		label_3.setFont(new Font("Cambria", Font.BOLD, 14));
		label_3.setBounds(245, 0, 119, 22);
		panel_3.add(label_3);

		txtBuscar = new JTextField(){
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
		barraAlumno.setBounds(10, 50, 634, 185);

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
		comboBox.setBounds(10, 21, 212, 19);
		panel_3.add(comboBox);

		lblRegistros = new JLabel("");
		lblRegistros.setForeground(new Color(0, 128, 0));
		lblRegistros.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistros.setFont(new Font("Cambria", Font.BOLD, 14));
		lblRegistros.setBounds(184, 236, 102, 26);
		panel_3.add(lblRegistros);

		JLabel label_5 = new JLabel("Total alumnos registrados :");
		label_5.setFont(new Font("Cambria", Font.BOLD, 14));
		label_5.setBounds(10, 236, 188, 26);
		panel_3.add(label_5);

		lblTotalAlumnosDe = new JLabel("Total alumnos de :");
		lblTotalAlumnosDe.setFont(new Font("Cambria", Font.BOLD, 14));
		lblTotalAlumnosDe.setBounds(307, 236, 127, 26);
		panel_3.add(lblTotalAlumnosDe);

		lblContGrados = new JLabel("");
		lblContGrados.setHorizontalAlignment(SwingConstants.CENTER);
		lblContGrados.setForeground(new Color(0, 128, 0));
		lblContGrados.setFont(new Font("Cambria", Font.BOLD, 14));
		lblContGrados.setBounds(542, 236, 102, 26);
		panel_3.add(lblContGrados);

		comboBox_1 = new JComboBox<Object>();
		comboBox_1.setFont(new Font("Cambria", Font.BOLD, 14));
		comboBox_1.setBounds(430, 241, 102, 18);
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "8\u00B0 Grado", "9\u00B0 Grado", "11\u00B0 Grado", "12\u00B0 Grado" }));
		panel_3.add(comboBox_1);
		
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_1.getSelectedItem().toString().equals("7� Grado")) {
					txtBuscar.setText("Septimo");
					contarDatosPorGrado();
				} else {
					if (comboBox_1.getSelectedItem().toString().equals("8� Grado")) {
						txtBuscar.setText("Octavo Grado");
						contarDatosPorGrado();
					} else {
						if (comboBox_1.getSelectedItem().toString().equals("9� Grado")) {
							txtBuscar.setText("Noveno Grado");
							contarDatosPorGrado();
						} else {
							if (comboBox_1.getSelectedItem().toString().equals("10� Grado")) {
								txtBuscar.setText("D�cimo");
								contarDatosPorGrado();
							} else {
								if (comboBox_1.getSelectedItem().toString().equals("11� Grado")) {
									txtBuscar.setText("Undecimo Grado");
									contarDatosPorGrado();
								} else {
									if (comboBox_1.getSelectedItem().toString().equals("12� Grado")) {
										txtBuscar.setText("Duodecimo Grado");
										contarDatosPorGrado();
									} else {
										
									}
									
								}
							}
							
						}
						
					}
					
				}

			}
		});

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().equals("Matricula")) {
					construirTabla();
					contarDatos();
					comboBox_1.setModel(new DefaultComboBoxModel(
							new String[] { "8\u00B0 Grado", "9\u00B0 Grado", "11\u00B0 Grado", "12\u00B0 Grado" }));

				} else {
					construirTabla2();
					contarDatos();
					comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "7\u00B0 Grado", "10\u00B0 Grado" }));
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
						String codigo = tablaAlumno.getValueAt(filaseleccionada, 3).toString();
						String modalidad = tablaAlumno.getValueAt(filaseleccionada, 4).toString();
						String grado = tablaAlumno.getValueAt(filaseleccionada, 5).toString();
						String pago = tablaAlumno.getValueAt(filaseleccionada, 6).toString();
						String recibo = tablaAlumno.getValueAt(filaseleccionada, 7).toString();

						txtNombre_Alumno.setText(nombres + " " + apellidos);
						txtIdentidad_Alumno.setText(identidad);
						txtModalidad.setText(modalidad);
						txtGrado.setText(grado);

						if (codigo.equals("null") || codigo.toString().equals("null")) {
							txtCodigo_Matricula.setText("Comprobar");
						} else {
							txtCodigo_Matricula.setText(codigo);
						}

						if (pago.equals("0") || pago.toString().equals("null")) {
							txtVerificacion.setText("Comprobar");

						} else {
							txtVerificacion.setText(pago);
						}

						if (recibo.equals("0000") || recibo.toString().equals("null")) {
							txtRecibo.setText("Comprobar");
						} else {
							txtRecibo.setText(recibo);
						}

					}

				} catch (HeadlessException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInt�ntelo nuevamente",
							" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.decode("#BDBDBD"));
		panelInformacion.setBounds(10, 331, 674, 329);
		contentPane.add(panelInformacion);
		panelInformacion.setLayout(null);

		JLabel lblNombreCompletoDel = new JLabel("Nombre completo del alumno :");
		lblNombreCompletoDel.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNombreCompletoDel.setBounds(44, 103, 225, 21);
		panelInformacion.add(lblNombreCompletoDel);

		JLabel lblIdentidadDelAlumno = new JLabel("Identidad del alumno :");
		lblIdentidadDelAlumno.setFont(new Font("Cambria", Font.BOLD, 14));
		lblIdentidadDelAlumno.setBounds(44, 167, 225, 21);
		panelInformacion.add(lblIdentidadDelAlumno);

		JLabel lblCodigoDelAlumno = new JLabel("Codigo :");
		lblCodigoDelAlumno.setFont(new Font("Cambria", Font.BOLD, 14));
		lblCodigoDelAlumno.setBounds(44, 199, 225, 21);
		panelInformacion.add(lblCodigoDelAlumno);

		txtNombre_Alumno = new JTextField(){
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
		txtNombre_Alumno.setFont(new Font("Cambria", Font.BOLD, 14));
		txtNombre_Alumno.setEditable(false);
		txtNombre_Alumno.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre_Alumno.setBounds(252, 104, 369, 20);
		panelInformacion.add(txtNombre_Alumno);
		txtNombre_Alumno.setColumns(10);

		txtIdentidad_Alumno = new JTextField(){
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
		txtIdentidad_Alumno.setFont(new Font("Cambria", Font.BOLD, 14));
		txtIdentidad_Alumno.setEditable(false);
		txtIdentidad_Alumno.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad_Alumno.setColumns(10);
		txtIdentidad_Alumno.setBounds(252, 167, 172, 20);
		panelInformacion.add(txtIdentidad_Alumno);

		txtCodigo_Matricula = new JTextField(){
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
		txtCodigo_Matricula.setFont(new Font("Cambria", Font.BOLD, 14));
		txtCodigo_Matricula.setEditable(false);
		txtCodigo_Matricula.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo_Matricula.setColumns(10);
		txtCodigo_Matricula.setBounds(252, 199, 172, 20);
		panelInformacion.add(txtCodigo_Matricula);

		JLabel lblCompro = new JLabel("Modalidad :");
		lblCompro.setFont(new Font("Cambria", Font.BOLD, 14));
		lblCompro.setBounds(44, 135, 225, 21);
		panelInformacion.add(lblCompro);

		txtModalidad = new JTextField(){
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
		txtModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtModalidad.setFont(new Font("Cambria", Font.BOLD, 14));
		txtModalidad.setEditable(false);
		txtModalidad.setColumns(10);
		txtModalidad.setBounds(252, 135, 369, 20);
		panelInformacion.add(txtModalidad);

		JLabel lblInstitutoDepartamentalDe = new JLabel("Instituto Departamental de Oriente\r\n");
		lblInstitutoDepartamentalDe.setForeground(Color.BLACK);
		lblInstitutoDepartamentalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstitutoDepartamentalDe.setFont(new Font("Serif", Font.BOLD, 18));
		lblInstitutoDepartamentalDe.setBounds(10, 7, 664, 32);
		panelInformacion.add(lblInstitutoDepartamentalDe);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(78, 0, 116, 110);
		panelInformacion.add(lblNewLabel);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);

		btnImprimir = new JButton("Imprimir informaci\u00F3n");
		btnImprimir.setBounds(450, 274, 171, 21);
		panelInformacion.add(btnImprimir);
		btnImprimir.setFont(new Font("Cambria", Font.BOLD, 13));
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNombre_Alumno.getText().toString().equals("")
						&& txtIdentidad_Alumno.getText().toString().equals("")
						&& txtCodigo_Matricula.getText().toString().equals("")
						&& txtModalidad.getText().toString().equals("") && txtRecibo.getText().toString().equals("")
						&& txtVerificacion.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "No hay datos que imprimir, haga la busqueda antes.");

				} else {
					if (txtCodigo_Matricula.getText().toString().equals("Comprobar")
							&& txtRecibo.getText().toString().equals("Comprobar")
							&& txtVerificacion.getText().toString().equals("Comprobar")) {
						JOptionPane.showMessageDialog(null,
								"Antes imprimir, Compruebe el Pago de Matricula.\nPresione : Comprobar informaci�n de pago del estudiante");

					} else {

						detalle_comprobante detalle = new detalle_comprobante();
						detalle.setVisible(true);
						detalle.setLocationRelativeTo(null);
						detalle_comprobante.lblNombre.setText(txtNombre_Alumno.getText().toString());
						detalle_comprobante.lblIdentidad.setText(txtIdentidad_Alumno.getText().toString());
						detalle_comprobante.lblModalidad.setText(txtModalidad.getText().toString());
						detalle_comprobante.lblCodigo.setText(txtCodigo_Matricula.getText().toString());
						detalle_comprobante.lblFecha.setText(lblFecha.getText().toString());
						detalle_comprobante.lblHora.setText(lblHoraSistema.getText().toString());

					}
				}

			}
		});
		btnImprimir.setBackground(new Color(255, 215, 0));

		lblFecha = new JLabel("");
		lblFecha.setForeground(new Color(0, 0, 128));
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Serif", Font.BOLD, 14));
		lblFecha.setBounds(239, 36, 276, 21);
		panelInformacion.add(lblFecha);
		lblFecha.setText(getFecha());

		JLabel lblFecha_1 = new JLabel("Fecha :");
		lblFecha_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha_1.setForeground(Color.BLACK);
		lblFecha_1.setFont(new Font("Serif", Font.BOLD, 14));
		lblFecha_1.setBounds(217, 36, 143, 21);
		panelInformacion.add(lblFecha_1);

		JLabel lblVerificacionDePago = new JLabel("Verificacion de pago :");
		lblVerificacionDePago.setFont(new Font("Cambria", Font.BOLD, 14));
		lblVerificacionDePago.setBounds(44, 231, 225, 21);
		panelInformacion.add(lblVerificacionDePago);

		txtVerificacion = new JTextField(){
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
		txtVerificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txtVerificacion.setFont(new Font("Cambria", Font.BOLD, 14));
		txtVerificacion.setEditable(false);
		txtVerificacion.setColumns(10);
		txtVerificacion.setBounds(252, 231, 172, 20);
		panelInformacion.add(txtVerificacion);

		JLabel lblNDeRecibo = new JLabel("N\u00B0 de recibo.");
		lblNDeRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeRecibo.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNDeRecibo.setBounds(449, 211, 172, 21);
		panelInformacion.add(lblNDeRecibo);

		txtRecibo = new JTextField(){
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
		txtRecibo.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecibo.setFont(new Font("Cambria", Font.BOLD, 14));
		txtRecibo.setEditable(false);
		txtRecibo.setColumns(10);
		txtRecibo.setBounds(449, 231, 172, 20);
		panelInformacion.add(txtRecibo);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(494, 0, 116, 110);
		panelInformacion.add(label_2);
		final ImageIcon logo22 = new ImageIcon(getClass().getResource("/iconos/logo_ido.png"));
		final ImageIcon icono22 = new ImageIcon(
				logo22.getImage().getScaledInstance(label_2.getWidth(), label_2.getHeight(), Image.SCALE_DEFAULT));
		label_2.setIcon(icono22);

		btnComprobar = new JButton("Comprobar informaci\u00F3n");
		btnComprobar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNombre_Alumno.getText().toString().equals("")
						&& txtIdentidad_Alumno.getText().toString().equals("")
						&& txtCodigo_Matricula.getText().toString().equals("")
						&& txtModalidad.getText().toString().equals("") && txtRecibo.getText().toString().equals("")
						&& txtVerificacion.getText().toString().equals("")
						&& txtGrado.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "No hay datos que comprobar, haga la busqueda antes.");

				} else {
					verificacion_recibo recibo = new verificacion_recibo();
					recibo.setVisible(true);
					recibo.setLocationRelativeTo(null);
					verificacion_recibo.lblIdentidad.setText(txtIdentidad_Alumno.getText().toString());
					verificacion_recibo.lblNombre.setText(txtNombre_Alumno.getText().toString());
					verificacion_recibo.lblGrado.setText(txtGrado.getText().toString());
					verificacion_recibo.lbltabla.setText(comboBox.getSelectedItem().toString());
					dispose();
				}

			}
		});
		btnComprobar.setFont(new Font("Cambria", Font.BOLD, 12));
		btnComprobar.setBackground(new Color(60, 179, 113));
		btnComprobar.setBounds(253, 274, 172, 21);
		panelInformacion.add(btnComprobar);

		JLabel lblComprobanteDeMatrcula = new JLabel("Comprobante de matr\u00EDcula IDO 2020. ");
		lblComprobanteDeMatrcula.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprobanteDeMatrcula.setForeground(Color.BLACK);
		lblComprobanteDeMatrcula.setFont(new Font("Serif", Font.BOLD, 18));
		lblComprobanteDeMatrcula.setBounds(20, 50, 654, 42);
		panelInformacion.add(lblComprobanteDeMatrcula);

		JLabel lblGrado = new JLabel("Grado.");
		lblGrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrado.setFont(new Font("Cambria", Font.BOLD, 14));
		lblGrado.setBounds(449, 167, 172, 18);
		panelInformacion.add(lblGrado);

		txtGrado = new JTextField(){
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
		txtGrado.setHorizontalAlignment(SwingConstants.CENTER);
		txtGrado.setFont(new Font("Cambria", Font.BOLD, 14));
		txtGrado.setEditable(false);
		txtGrado.setColumns(10);
		txtGrado.setBounds(449, 186, 172, 20);
		panelInformacion.add(txtGrado);
		final ImageIcon logo1 = new ImageIcon(getClass().getResource("/iconos/prueba.png"));
		final ImageIcon logo221 = new ImageIcon(getClass().getResource("/iconos/estudiante.png"));
		final ImageIcon logo222 = new ImageIcon(getClass().getResource("/iconos/escribir.png"));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.decode("#BDBDBD"));
		panel_2.setBounds(694, 11, 105, 649);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblRegistroDeUsuarios = new JLabel("Usuarios");
		lblRegistroDeUsuarios.setBounds(10, 80, 85, 21);
		panel_2.add(lblRegistroDeUsuarios);
		lblRegistroDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeUsuarios.setFont(new Font("Cambria", Font.BOLD, 14));

		btnUsuarios = new JButton("");
		btnUsuarios.setBounds(10, 102, 85, 67);
		panel_2.add(btnUsuarios);
		btnUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registro_usuarios usuarios = new registro_usuarios();
				usuarios.setVisible(true);
				usuarios.setLocationRelativeTo(null);
				usuarios.construirTabla();
				usuarios.obtenerUltimoId();
				usuarios.btnBorrar.setVisible(false);
				usuarios.btnActualizar.setVisible(false);
				registro_usuarios.llena_combo();
				usuarios.cargarIdRol();
				dispose();
			}
		});
		btnUsuarios.setFont(new Font("Calibri", Font.BOLD, 14));
		btnUsuarios.setBackground(Color.WHITE);
		final ImageIcon icono1 = new ImageIcon(logo1.getImage().getScaledInstance(btnUsuarios.getWidth(),
				btnUsuarios.getHeight(), Image.SCALE_DEFAULT));
		btnUsuarios.setIcon(icono1);

		JLabel lblAlumnos = new JLabel("Alumnos");
		lblAlumnos.setBounds(10, 180, 85, 21);
		panel_2.add(lblAlumnos);
		lblAlumnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumnos.setFont(new Font("Cambria", Font.BOLD, 14));

		btnAlumnos = new JButton("");
		btnAlumnos.setBounds(10, 204, 85, 67);
		panel_2.add(btnAlumnos);
		btnAlumnos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registro_alumnos principal = new registro_alumnos();
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				principal.construirTabla();
				principal.obtenerUltimoId();
				principal.btnActualizar.setVisible(false);
				principal.btnNuevo.setVisible(true);
				principal.btnGuardar.setVisible(true);
				dispose();
			}
		});
		btnAlumnos.setFont(new Font("Calibri", Font.BOLD, 14));
		btnAlumnos.setBackground(Color.WHITE);
		final ImageIcon icono221 = new ImageIcon(logo221.getImage().getScaledInstance(btnAlumnos.getWidth(),
				btnAlumnos.getHeight(), Image.SCALE_DEFAULT));
		btnAlumnos.setIcon(icono221);

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.setBounds(10, 499, 85, 21);
		panel_2.add(lblAcercaDe);
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setFont(new Font("Cambria", Font.BOLD, 14));

		JButton button_2 = new JButton("");
		button_2.setBounds(10, 518, 82, 67);
		panel_2.add(button_2);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acerca_de tabla = new acerca_de();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
			}
		});
		button_2.setFont(new Font("Calibri", Font.BOLD, 14));
		button_2.setBackground(Color.WHITE);
		final ImageIcon icono222 = new ImageIcon(
				logo222.getImage().getScaledInstance(button_2.getWidth(), button_2.getHeight(), Image.SCALE_DEFAULT));
		button_2.setIcon(icono222);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(10, 0, 85, 28);
		panel_2.add(lblHora);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Serif", Font.BOLD, 18));

		JLabel lblMen = new JLabel("Men\u00FA ");
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setForeground(Color.BLACK);
		lblMen.setFont(new Font("Serif", Font.BOLD, 18));
		lblMen.setBounds(10, 57, 85, 28);
		panel_2.add(lblMen);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 26, 85, 28);
		panel_2.add(panel_1);
		panel_1.setLayout(null);

		lblHoraSistema = new JLabel("");
		lblHoraSistema.setBounds(0, 0, 85, 28);
		panel_1.add(lblHoraSistema);
		lblHoraSistema.setForeground(new Color(0, 0, 128));
		lblHoraSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraSistema.setFont(new Font("Dialog", Font.BOLD, 15));

		JLabel lblPre = new JLabel("Verificar ");
		lblPre.setHorizontalAlignment(SwingConstants.CENTER);
		lblPre.setFont(new Font("Cambria", Font.BOLD, 14));
		lblPre.setBounds(10, 282, 85, 21);
		panel_2.add(lblPre);

		btnPrematricula = new JButton("");
		btnPrematricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificar_matricula frame = new verificar_matricula();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnPrematricula.setFont(new Font("Calibri", Font.BOLD, 14));
		btnPrematricula.setBackground(Color.WHITE);
		btnPrematricula.setBounds(10, 319, 85, 67);
		panel_2.add(btnPrematricula);
		final ImageIcon logo2211 = new ImageIcon(getClass().getResource("/iconos/pre_matricula.png"));
		final ImageIcon icono2211 = new ImageIcon(logo2211.getImage().getScaledInstance(btnPrematricula.getWidth(),
				btnPrematricula.getHeight(), Image.SCALE_DEFAULT));
		btnPrematricula.setIcon(icono2211);

		JLabel lblMatricula_1 = new JLabel("Matricula");
		lblMatricula_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricula_1.setFont(new Font("Cambria", Font.BOLD, 14));
		lblMatricula_1.setBounds(10, 397, 85, 21);
		panel_2.add(lblMatricula_1);

		btnMatricula = new JButton("");
		btnMatricula.setFont(new Font("Calibri", Font.BOLD, 14));
		btnMatricula.setBackground(Color.WHITE);
		btnMatricula.setBounds(10, 421, 85, 67);
		panel_2.add(btnMatricula);
		final ImageIcon logo22112 = new ImageIcon(getClass().getResource("/iconos/matricula.png"));
		final ImageIcon icono22112 = new ImageIcon(logo22112.getImage().getScaledInstance(btnMatricula.getWidth(),
				btnMatricula.getHeight(), Image.SCALE_DEFAULT));
		btnMatricula.setIcon(icono22112);

		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				login login = new login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSalir.setFont(new Font("Calibri", Font.BOLD, 14));
		btnSalir.setBackground(new Color(255, 69, 0));
		btnSalir.setBounds(10, 594, 85, 44);
		panel_2.add(btnSalir);
		final ImageIcon logo01 = new ImageIcon(getClass().getResource("/iconos/salida.png"));
		final ImageIcon icono01 = new ImageIcon(
				logo01.getImage().getScaledInstance(btnSalir.getWidth(), btnSalir.getHeight(), Image.SCALE_DEFAULT));
		btnSalir.setIcon(icono01);
		
		JLabel lblMatricula_2 = new JLabel("Matricula");
		lblMatricula_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricula_2.setFont(new Font("Cambria", Font.BOLD, 14));
		lblMatricula_2.setBounds(10, 300, 85, 21);
		panel_2.add(lblMatricula_2);

	}

	Timer time = new Timer();
	public TimerTask tarea = new TimerTask() {
		@Override
		public void run() {
			Calendar calendario = new GregorianCalendar();
			Date fechaHoraActual = new Date();
			calendario.setTime(fechaHoraActual);
			String horas;
			String minutos;
			String segundos;
			String ampm;
			Thread hilo = null;
			Thread hilo2;
			hilo2 = Thread.currentThread();
			hilo = new Thread();
			hilo.start();
			ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			if (ampm.equals("PM")) {
				int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
				horas = h > 9 ? "" + h : "0" + h;
			} else {
				horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
						: "0" + calendario.get(Calendar.HOUR_OF_DAY);
			}
			minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
					: "0" + calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
					: "0" + calendario.get(Calendar.SECOND);

			lblHoraSistema.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};

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
		if (JOptionPane.showConfirmDialog(rootPane, "�Desea realmente salir del sistema?", "Salir del sistema",
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

	public void contarDatosPorGrado() {
		int registros = 0;
		registros = tablaAlumno.getRowCount();
		lblContGrados.setText(String.valueOf(registros+" "));

	}
}
