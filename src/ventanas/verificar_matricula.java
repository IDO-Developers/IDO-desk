package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import conexion.conexion;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Color;

public class verificar_matricula extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField txtIdentidad;
	
	public String nombres = null;
	public String apellidos = null;
	public String grado = null;
	public String modalidad = null;
	public String Jornada = null;
	public String grupo = null;

	
	public JLabel lblNombre;
	public JLabel lblApellido;
	public JLabel lblGrado;
	public JLabel lblModalidad;
	public JLabel lblGrupo;
	public JLabel lblJornada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					verificar_matricula frame = new verificar_matricula();
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
	public verificar_matricula() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 354, 300);
		setLocationRelativeTo(null);
		setTitle("Comprobar Matricula de 8°, 9°, 11 y 12°");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MaskFormatter formatooo = null;
		try {
			formatooo = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtIdentidad = new JFormattedTextField(formatooo);
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setBounds(28, 35, 176, 20);
		contentPane.add(txtIdentidad);
		txtIdentidad.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese la identidad del alumno:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(28, 10, 234, 14);
		contentPane.add(lblNewLabel);
		
		lblNombre = new JLabel("");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(122, 100, 213, 14);
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setBounds(122, 125, 213, 14);
		contentPane.add(lblApellido);
		
		lblGrado = new JLabel("");
		lblGrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrado.setBounds(122, 150, 213, 14);
		contentPane.add(lblGrado);
		
		lblModalidad = new JLabel("");
		lblModalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblModalidad.setBounds(122, 175, 213, 14);
		contentPane.add(lblModalidad);
		
		lblGrupo = new JLabel("");
		lblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupo.setBounds(122, 200, 213, 14);
		contentPane.add(lblGrupo);
		
		lblJornada = new JLabel("");
		lblJornada.setHorizontalAlignment(SwingConstants.CENTER);
		lblJornada.setBounds(122, 224, 213, 14);
		contentPane.add(lblJornada);
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtIdentidad.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Ingrese la identidad");
				}else {
					buscarInfoAlumno();
					lblNombre.setText(nombres);
					lblApellido.setText(apellidos);
					lblGrado.setText(grado);
					lblModalidad.setText(modalidad);
					lblGrupo.setText(grupo);
					lblJornada.setText(Jornada);
					
					
					
				}
				
			}
		});
		btnNewButton.setBounds(214, 34, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombres.setBounds(28, 100, 213, 14);
		contentPane.add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setBounds(28, 125, 213, 14);
		contentPane.add(lblApellidos);
		
		JLabel lblGrado_1 = new JLabel("Grado");
		lblGrado_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrado_1.setBounds(28, 150, 213, 14);
		contentPane.add(lblGrado_1);
		
		JLabel lblModalidad_1 = new JLabel("Modalidad");
		lblModalidad_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidad_1.setBounds(28, 175, 213, 14);
		contentPane.add(lblModalidad_1);
		
		JLabel lblGrupo_1 = new JLabel("Grupo");
		lblGrupo_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrupo_1.setBounds(28, 200, 213, 14);
		contentPane.add(lblGrupo_1);
		
		JLabel lblJornadaq = new JLabel("Jornada");
		lblJornadaq.setHorizontalAlignment(SwingConstants.LEFT);
		lblJornadaq.setBounds(28, 224, 213, 14);
		contentPane.add(lblJornadaq);
		
		JLabel lblDatosDelAlumno = new JLabel("Datos del alumno:");
		lblDatosDelAlumno.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDelAlumno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDatosDelAlumno.setBounds(28, 66, 234, 14);
		contentPane.add(lblDatosDelAlumno);
		
		
	}
	
	public void buscarInfoAlumno() {
		conexion conex = new conexion();
		try {

			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"select Nombres, Apellidos, Modalidad, Grado, Jornada, grupo  from Reg_Alumno, Matricula where Reg_Alumno.RNE_Alumno='"+ txtIdentidad.getText().toString() +"' and Matricula.RNE_Alumno='"+ txtIdentidad.getText().toString() +"'");
			if (rs.next()) {
				nombres = rs.getString("Nombres");
				apellidos = rs.getString("Apellidos");
				grado = rs.getString("Grado");
				modalidad = rs.getString("Modalidad");
				Jornada = rs.getString("Jornada");
				grupo = rs.getString("grupo");
			}else {
				JOptionPane.showMessageDialog(null, "No se encontro en los registros de matricula");
			}
		} catch (SQLException ex) {
			Logger.getLogger(verificar_matricula.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}
}
