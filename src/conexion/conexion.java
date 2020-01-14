package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexion {

	private final String base = "MatriculaIDONueva";
	private final String user = "Mat";
	private final String password = "Ido2018";
	public static String urlGlobal = "192.168.100.120";
	private final String url = "jdbc:sqlserver://192.168.100.120:1433;databaseName=MatriculaIDONueva;";
	private Connection con = null;

	public Connection getConexion() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(this.url, this.user, this.password);

		} catch (SQLException e) {
			System.err.println(e);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;
	}

	public void desconectar() {
		con = null;
	}

}
