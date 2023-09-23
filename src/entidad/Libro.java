package entidad;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
public class Libro {
	
	private int idLibro;
	private String titulo;
	private int annio;
	private String serie;
	private Timestamp fechaRegistro;
	private int estado;
	private  Categoria categoria;
	
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	
	public String getFormatoRegistro() {
		return sdf.format(fechaRegistro);
	}
	
	public String getFormatoEstado() {
		return estado==1 ? "Activo" : "Inactivo";
	}
	
	public String getFormatoCategoria() {
		return categoria.getDescripcion();
	}
	
	
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAnnio() {
		return annio;
	}
	public void setAnnio(int annio) {
		this.annio = annio;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	 
}
	