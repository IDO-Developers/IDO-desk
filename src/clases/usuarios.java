package clases;

public class usuarios {

	int id;
	String Nombre_Usuario;
	String Contraseña_Usuario;
	String Rol;
	String RNE_Administradores;

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

	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		Rol = rol;
	}

	public String getRNE_Administradores() {
		return RNE_Administradores;
	}

	public void setRNE_Administradores(String rNE_Administradores) {
		RNE_Administradores = rNE_Administradores;
	}

}
