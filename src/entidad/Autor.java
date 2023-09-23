package entidad;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;



public class Autor {
	
	private int idAutor;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String telefono;	
	private Timestamp fechaRegistro;
	private int estado;
	private Grado grado;
	
	//Inicio get para el reporte
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getFormatoEstado() {
		return estado == 1 ? "Activo": "Inactivo";
	}
	
	public String getDescripcionGrado() {
		return grado.getDescripcion();
	}
	
	public String getFormatoFecNacimiento() {
		return sdf1.format(fechaNacimiento);
	}
	
	public String getFormatoFecRegistro() {
		return sdf2.format(fechaRegistro);
	}
	
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Grado getGrado() {
		return grado;
	}
	public void setGrado(Grado grado) {
		this.grado = grado;
	}
	

}
