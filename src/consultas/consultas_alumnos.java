package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.alumno;
import conexion.conexion;

public class consultas_alumnos extends conexion {

	public boolean insertar(alumno alumno) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "INSERT INTO dbo.Reg_Alumno (RNE_Alumno, Nombres, Apellidos, Fcha_Nac, Edad, Direccion, Distancia, Sexo, Codigo, Modalidad_Alumno, Grado_Alumno, id_Grupo ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, alumno.getRNE_Alumno());
			ps.setString(2, alumno.getNombres());
			ps.setString(3, alumno.getApellidos());
			ps.setString(4, alumno.getFcha_Nac());
			ps.setString(5, alumno.getEdad());
			ps.setString(6, alumno.getDireccion());
			ps.setString(7, alumno.getDistancia());
			ps.setString(8, alumno.getSexo());
			ps.setString(9, alumno.getCodigo());
			ps.setString(10, alumno.getModalidad_Alumno());
			ps.setString(11, alumno.getGrado_Alumno());
			ps.setString(12, alumno.getId_Grupo());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public boolean actualizar(alumno alumno) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.Reg_Alumno SET RNE_Alumno=?, Nombres=?, Apellidos=?, Fcha_Nac=?, Edad=?, Direccion=?, Distancia=?, Sexo=?, Codigo=?, Modalidad_Alumno=?, Grado_Alumno=?, id_Grupo=? WHERE RNE_Alumno=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, alumno.getRNE_Alumno());
			ps.setString(2, alumno.getNombres());
			ps.setString(3, alumno.getApellidos());
			ps.setString(4, alumno.getFcha_Nac());
			ps.setString(5, alumno.getEdad());
			ps.setString(6, alumno.getDireccion());
			ps.setString(7, alumno.getDistancia());
			ps.setString(8, alumno.getSexo());
			ps.setString(9, alumno.getCodigo());
			ps.setString(10, alumno.getModalidad_Alumno());
			ps.setString(11, alumno.getGrado_Alumno());
			ps.setString(12, alumno.getId_Grupo());
			ps.setString(13, alumno.getRNE_Alumno());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}

}
