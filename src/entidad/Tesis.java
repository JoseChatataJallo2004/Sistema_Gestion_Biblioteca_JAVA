package entidad;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Tesis {

	
	private int idtesis;
	private String titulo;
	private String tema ;
	private Date fechaCreacion;
	private Timestamp fechaRegistro;
	private int estado;
	private  Alumno alumno;
	
	//Inicio get para el reporte
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		public String getFormatoEstado() {
			return estado == 1 ? "Activo" : "Inactivo";
		}
		public String getFormatoAlumno() {
			return alumno.getNombres();
		}
		public String getFormatoFecCreacion() {
			return sdf1.format(fechaCreacion);
		}
		public String getFormatoFecRegistro() {
			return sdf2.format(fechaRegistro);
		}
		//Fin
	
	//=================================================================000
	public int getIdtesis() {
		return idtesis;
	}
	public void setIdtesis(int idtesis) {
		this.idtesis = idtesis;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	
	
	
}
