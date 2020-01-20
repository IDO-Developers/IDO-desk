package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.alumnos;
import clases.alumnos2;
import conexion.conexion;

public class consultas_alumno extends conexion {
	public boolean actualizarAlumnmo(alumnos alumnos) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.Reg_Alumno SET Nombres=?, Apellidos=?, RNE_Alumno=? WHERE RNE_Alumno=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, alumnos.getNombres());
			ps.setString(2, alumnos.getApellidos());
			ps.setString(3, alumnos.getRNE_Alumno());
			ps.setString(4, alumnos.getIdentidad());
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

	public boolean actualizarAlumno2(alumnos2 alumnos2) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.prematricula2019_2020 SET Nombres_alumnos=?, Apellidos_alumnos=?, Identidad_alumnos=? WHERE Identidad_alumnos=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, alumnos2.getNombres_alumnos());
			ps.setString(2, alumnos2.getApellidos_alumnos());
			ps.setString(3, alumnos2.getIdentidad_alumnos());
			ps.setString(4, alumnos2.getIdentidad());
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
