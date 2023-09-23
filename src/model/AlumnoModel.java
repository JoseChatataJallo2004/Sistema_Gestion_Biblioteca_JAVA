package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Alumno;
import entidad.Pais;
import util.MySqlDBConexion;

public class AlumnoModel {

private static Logger log = Logger.getLogger(AlumnoModel.class.getName());
	
	public int insertarAlumno(Alumno obj) {
		log.info(">> Inicio >> insertaAlumno()");
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try { 
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "insert into alumno values(null,?,?,?,?,?,?, curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setString(3, obj.getTelefono());
			pstm.setString(4, obj.getDni());
			pstm.setString(5, obj.getCorreo());
			pstm.setDate(6, obj.getFechaNacimiento());
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getPais().getIdPais());
			
			
			log.info(">> SQL >> " + pstm);
			
			//3 se ejecuta en la BD
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
           try {
	       if (pstm != null) pstm.close();
	       if (conn != null) conn.close();
           }catch (Exception e2) {}	
		}
		log.info(">> Fin >> insertaAlumno()");
		return salida;
	}
	
	/*PC2*/
	
	public int eliminaAlumno(int idAlumno) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexi�n
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "delete from alumno where idAlumno = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idAlumno);			
			log.info(">>> " + pstm);
			
			//3 Ejecutamos a la base de datos
			//Retorna la cantidad de registrados en salida
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public int actualizaClub(Alumno obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexi�n
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "update alumno set nombres=?, apellidos=?, telefono=?, dni=?, correo=?, fechaNacimiento=?, fechaRegistro=?, estado=?, idPais=? where idAlumno=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setString(3, obj.getTelefono());
			pstm.setString(4, obj.getDni());
			pstm.setString(5, obj.getCorreo());
			pstm.setDate(6, obj.getFechaNacimiento());
			pstm.setTimestamp(7, obj.getFechaRegistro());
			pstm.setInt(8, obj.getEstado());
			pstm.setInt(9, obj.getPais().getIdPais());
			//insertamos la nueva sentencia
			pstm.setInt(10, obj.getIdAlumno());
			log.info(">>> " + pstm);
			
			//3 Ejecutamos a la base de datos
			//Retorna la cantidad de registrados en salida
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Alumno> listaTodos(){
		ArrayList<Alumno> salida = new ArrayList<Alumno>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select a.*, p.nombre from alumno a inner join pais p on a.idPais = p.idPais;";
			psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Alumno objAlu = null;
			//Definimos Pais
			Pais objPais = null;
			while(rs.next()) {
				objAlu = new Alumno();
				objAlu.setIdAlumno(rs.getInt(1));
				objAlu.setNombres(rs.getString(2));
				objAlu.setApellidos(rs.getString(3));
				objAlu.setTelefono(rs.getString(4));
				objAlu.setDni(rs.getString(5));
				objAlu.setCorreo(rs.getString(6));
				objAlu.setFechaNacimiento(rs.getDate(7));
				objAlu.setFechaRegistro(rs.getTimestamp(8));
				objAlu.setEstado(rs.getInt(9));				
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				
				objAlu.setPais(objPais);
				
				salida.add(objAlu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Alumno> listaPorPais(int idPais){
		ArrayList<Alumno> salida = new ArrayList<Alumno>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "select a.*, p.nombre from alumno a inner join pais p on a.idPais = p.idPais where a.idPais = ?;";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idPais);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Alumno objAlu = null;
			//Definimos Pais
			Pais objPais = null;
			while(rs.next()) {
				objAlu = new Alumno();
				objAlu.setIdAlumno(rs.getInt(1));
				objAlu.setNombres(rs.getString(2));
				objAlu.setApellidos(rs.getString(3));
				objAlu.setTelefono(rs.getString(4));
				objAlu.setDni(rs.getString(5));
				objAlu.setCorreo(rs.getString(6));
				objAlu.setFechaNacimiento(rs.getDate(7));
				objAlu.setFechaRegistro(rs.getTimestamp(8));
				objAlu.setEstado(rs.getInt(9));				
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				
				objAlu.setPais(objPais);
				
				salida.add(objAlu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Alumno> listaPorNombre(String filtro){
		ArrayList<Alumno> salida = new ArrayList<Alumno>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "SELECT a.*, p.nombre FROM alumno a inner join pais p on a.idPais = p.idPais where a.nombres like ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, filtro);
			
			log.info(">>> " + psmt);
			
			//3 Se ejeuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Alumno objAlumno = null;
			Pais objPais = null;
			while(rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt("idAlumno"));
				objAlumno.setNombres(rs.getString(2));
				objAlumno.setApellidos(rs.getString(3));
				objAlumno.setTelefono(rs.getString(4));
				objAlumno.setDni(rs.getString(5));
				objAlumno.setCorreo(rs.getString(6));
				objAlumno.setFechaNacimiento(rs.getDate(7));
				objAlumno.setFechaRegistro(rs.getTimestamp(8));
				objAlumno.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objAlumno.setPais(objPais);
				salida.add(objAlumno);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return salida;
	}
}
