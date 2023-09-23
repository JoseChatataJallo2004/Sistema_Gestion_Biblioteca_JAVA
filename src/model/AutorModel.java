package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Autor;
import entidad.Grado;
import util.MySqlDBConexion;

public class AutorModel {

private static Logger log = Logger.getLogger(AutorModel.class.getName());
	
	public int insertarAutor(Autor obj) {
		log.info(">> Inicio >> insertaAutor()");
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try { 
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "insert into autor values(null,?,?,?,?, curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setDate(3, obj.getFechaNacimiento());
			pstm.setString(4, obj.getTelefono());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getGrado().getIdGrado());
			
			
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
		log.info(">> Fin >> insertaAutor()");
		return salida;
	}
	
	public int eliminaAutor(int idAutor) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try { 
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "delete from autor where idautor = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idAutor);
			
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
		return salida;
	}
	
	public int actualizaAutor(Autor obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try { 
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "update autor set nombres=?, apellidos=?, fechaNacimiento=?, telefono=?, estado=?, idGrado=? where idAutor=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setDate(3, obj.getFechaNacimiento());
			pstm.setString(4, obj.getTelefono());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getGrado().getIdGrado());
			pstm.setInt(7,obj.getIdAutor());
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
		return salida;
	}
	
	public List<Autor> listaTodos(){
		ArrayList<Autor> salida = new ArrayList<Autor>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "SELECT a.*, g.descripcion FROM autor a inner join grado_autor g on a.idGrado = g.idGrado";
			psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			
			//3 Se ejeuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Autor objAutor = null;
			Grado objGrado = null;
			while(rs.next()) {
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt("idAutor"));
				objAutor.setNombres(rs.getString(2));
				objAutor.setApellidos(rs.getString(3));
				objAutor.setFechaNacimiento(rs.getDate(4));
				objAutor.setTelefono(rs.getString(5));
				objAutor.setFechaRegistro(rs.getTimestamp(6));
				objAutor.setEstado(rs.getInt(7));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(8));
				objGrado.setDescripcion(rs.getString(9));
				objAutor.setGrado(objGrado);
				salida.add(objAutor);
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
	
	public List<Autor> listaPorGrado(int idGrado){
		ArrayList<Autor> salida = new ArrayList<Autor>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "SELECT a.*, g.descripcion FROM autor a inner join grado_autor g on a.idGrado = g.idGrado where a.idGrado = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idGrado);
			
			log.info(">>> " + psmt);
			
			//3 Se ejeuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Autor objAutor = null;
			Grado objGrado = null;
			while(rs.next()) {
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt("idAutor"));
				objAutor.setNombres(rs.getString(2));
				objAutor.setApellidos(rs.getString(3));
				objAutor.setFechaNacimiento(rs.getDate(4));
				objAutor.setTelefono(rs.getString(5));
				objAutor.setFechaRegistro(rs.getTimestamp(6));
				objAutor.setEstado(rs.getInt(7));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(8));
				objGrado.setDescripcion(rs.getString(9));
				objAutor.setGrado(objGrado);
				salida.add(objAutor);
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
	
	public List<Autor> listaPorNombre(String filtro){
		ArrayList<Autor> salida = new ArrayList<Autor>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "SELECT a.*, g.descripcion FROM autor a inner join grado_autor g on a.idGrado = g.idGrado where a.nombres like ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, filtro);
			
			log.info(">>> " + psmt);
			
			//3 Se ejeuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Autor objAutor = null;
			Grado objGrado = null;
			while(rs.next()) {
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt("idAutor"));
				objAutor.setNombres(rs.getString(2));
				objAutor.setApellidos(rs.getString(3));
				objAutor.setFechaNacimiento(rs.getDate(4));
				objAutor.setTelefono(rs.getString(5));
				objAutor.setFechaRegistro(rs.getTimestamp(6));
				objAutor.setEstado(rs.getInt(7));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(8));
				objGrado.setDescripcion(rs.getString(9));
				objAutor.setGrado(objGrado);
				salida.add(objAutor);
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
