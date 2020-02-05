package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.alumno;
import clases.matricula;
import conexion.conexion;

public class consultas_matricula extends conexion {

	public boolean insertar(matricula matricula) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "INSERT INTO dbo.Reg_Alumno (RNE_Alumno, RNE_Encargado, Año, Fech_Mat, Centro_Procd, Jornada, Grado, Grupo, Semestre, Modalidad, Retrasadas, Sexo, Certificado_Est, Partd_Nac, Foto, Copia_Acta, Otros, Estado, Profe_Español, Profe_Mate, Modulo, Estado_Pago, Numero_Recibo, Condicionado ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, matricula.getRNE_Alumno());
			ps.setString(2, matricula.getRNE_Encargado());
			ps.setString(3, matricula.getAño());
			ps.setString(4, matricula.getFech_Mat());
			ps.setString(5, matricula.getCentro_Procd());
			ps.setString(6, matricula.getJornada());
			ps.setString(7, matricula.getGrado());
			ps.setString(8, matricula.getGrupo());
			ps.setString(9, matricula.getSemestre());
			ps.setString(10, matricula.getModalidad());
			ps.setString(11, matricula.getRetrasadas());
			ps.setString(12, matricula.getSexo());
			ps.setString(13, matricula.getCertificado_Est());
			ps.setString(14, matricula.getPartd_Nac());
			ps.setString(15, matricula.getFoto());
			ps.setString(16, matricula.getCopia_Acta());
			ps.setString(17, matricula.getOtros());
			ps.setString(18, matricula.getEstado());
			ps.setString(19, matricula.getProfe_Español());
			ps.setString(20, matricula.getProfe_Mate());
			ps.setString(21, matricula.getModulo());
			ps.setString(22, matricula.getEstado_Pago());
			ps.setString(23, matricula.getNumero_Recibo());
			ps.setString(24, matricula.getCondicionado());
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

	public boolean actualizar(matricula matricula) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.Reg_Alumno SET RNE_Alumno=?, RNE_Encargado=?, Año=?, Fech_Mat=?, Centro_Procd=?, Jornada=?, Grado=?, Grupo=?, Semestre=?, Modalidad=?, Retrasadas=?, Sexo=?, Certificado_Est=?, Partd_Nac=?, Foto=?, Copia_Acta=?, Otros=?, Estado=?, Profe_Español=?, Profe_Mate=?, Modulo=?, Estado_Pago=?, Numero_Recibo=?, Condicionado=? WHERE RNE_Alumno=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, matricula.getRNE_Alumno());
			ps.setString(2, matricula.getRNE_Encargado());
			ps.setString(3, matricula.getAño());
			ps.setString(4, matricula.getFech_Mat());
			ps.setString(5, matricula.getCentro_Procd());
			ps.setString(6, matricula.getJornada());
			ps.setString(7, matricula.getGrado());
			ps.setString(8, matricula.getGrupo());
			ps.setString(9, matricula.getSemestre());
			ps.setString(10, matricula.getModalidad());
			ps.setString(11, matricula.getRetrasadas());
			ps.setString(12, matricula.getSexo());
			ps.setString(13, matricula.getCertificado_Est());
			ps.setString(14, matricula.getPartd_Nac());
			ps.setString(15, matricula.getFoto());
			ps.setString(16, matricula.getCopia_Acta());
			ps.setString(17, matricula.getOtros());
			ps.setString(18, matricula.getEstado());
			ps.setString(19, matricula.getProfe_Español());
			ps.setString(20, matricula.getProfe_Mate());
			ps.setString(21, matricula.getModulo());
			ps.setString(22, matricula.getEstado_Pago());
			ps.setString(23, matricula.getNumero_Recibo());
			ps.setString(24, matricula.getCondicionado());
			ps.setString(25, matricula.getRNE_Alumno());
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
