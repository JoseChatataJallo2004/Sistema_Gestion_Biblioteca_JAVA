package entidad;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Proveedor {
		
	private int idProveedor;
	private String nombre;
	private String apellido;
	private String dni;
	private String direccion;
	private String telefono;
	private String correo;
	private Timestamp fechaRegistro;
	private int estado;
	private  Pais pais;
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getFormatoFecRegistro() {
		return sdf.format(fechaRegistro);
	}
	
	public String getFormatoEstado() {
		return estado == 1 ? "Activo" : "Inactivo";
	}
	public String getFormatopais() {
		return pais.getNombre();
	}
	
	
	
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	
	
	
	
	
}
