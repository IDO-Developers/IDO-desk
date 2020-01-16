package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import clases.usuarios;
import conexion.conexion;

public class consultas_usuario extends conexion {
	public static String rol = null;
	
	public boolean insertar(usuarios usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO dbo.Usuario (Nombre_Usuario, id_Rol, Contraseña_Usuario, RNE_Empleado) VALUES(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNombre_Usuario());
			ps.setString(2, usuario.getId_Rol());
			ps.setString(3, usuario.getContraseña_Usuario());
			ps.setString(4, usuario.getRNE_Empleado());

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

	public boolean actualizar(usuarios usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.Usuario SET Nombre_Usuario=?, id_Rol=?, Contraseña_Usuario=?, RNE_Empleado=? WHERE id=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNombre_Usuario());
			ps.setString(2, usuario.getId_Rol());
			ps.setString(3, usuario.getContraseña_Usuario());
			ps.setString(4, usuario.getRNE_Empleado());
			ps.setInt(5, usuario.getId());
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

	public boolean buscarUsuario(usuarios usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT * FROM dbo.Usuario WHERE Nombre_Usuario=? and Contraseña_Usuario=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNombre_Usuario());
			ps.setString(2, usuario.getContraseña_Usuario());
			rs = ps.executeQuery();

			if (rs.next()) {
				usuario.setNombre_Usuario(rs.getString("Nombre_Usuario"));
				usuario.setContraseña_Usuario(rs.getString("Contraseña_Usuario"));
				usuario.setRNE_Empleado(rs.getString("RNE_Empleado"));
				usuario.setId_Rol(rs.getString("id_Rol"));
				rol = rs.getString("id_Rol");
				return true;
			}
			return false;
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
