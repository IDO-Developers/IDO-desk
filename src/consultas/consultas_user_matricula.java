package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.user_matricula;
import conexion.conexion;

public class consultas_user_matricula extends conexion {

	public boolean insertar(user_matricula usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO dbo.User_Alumno (Id_Alumno, Password, Estado) VALUES(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getId_Alumno());
			ps.setString(2, usuario.getPassword());
			ps.setInt(3, usuario.getEstado());

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

	public boolean actualizar(user_matricula usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.User_Alumno SET Password=?, Estado=? WHERE Id_Alumno=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getPassword());
			ps.setInt(2, usuario.getEstado());
			ps.setString(3, usuario.getId_Alumno());
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
