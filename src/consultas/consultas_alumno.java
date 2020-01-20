package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.alumnos;
import clases.alumnos2;
import conexion.conexion;

public class consultas_alumno extends conexion {
	public boolean actualizarCodigo(alumnos alumnos) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.Reg_Alumno SET Codigo=? WHERE RNE_Alumno=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, alumnos.getCodigo());
			ps.setString(2, alumnos.getRNE_Alumno());
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

	public boolean actualizarCodigo2(alumnos2 alumnos2) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.prematricula2019_2020 SET Nombres_alumnos=?, Apellidos_alumnos=? WHERE Identidad_alumnos=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, alumnos2.getEstado_Pago());
			ps.setString(2, alumnos2.getNumero_Recibo());
			ps.setString(3, alumnos2.getIdentidad_alumnos());
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
