package clases;

public class usuarios {

	int id;
	String Nombre_Usuario;
	String Contraseña_Usuario;
	String id_Rol;
	String RNE_Empleado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_Usuario() {
		return Nombre_Usuario;
	}

	public void setNombre_Usuario(String nombre_Usuario) {
		Nombre_Usuario = nombre_Usuario;
	}

	public String getContraseña_Usuario() {
		return Contraseña_Usuario;
	}

	public void setContraseña_Usuario(String contraseña_Usuario) {
		Contraseña_Usuario = contraseña_Usuario;
	}

	public String getId_Rol() {
		return id_Rol;
	}

	public void setId_Rol(String id_Rol) {
		this.id_Rol = id_Rol;
	}

	public String getRNE_Empleado() {
		return RNE_Empleado;
	}

	public void setRNE_Empleado(String rNE_Empleado) {
		RNE_Empleado = rNE_Empleado;
	}

}
