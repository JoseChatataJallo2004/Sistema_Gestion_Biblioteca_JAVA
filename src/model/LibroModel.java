package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import entidad.Libro;
import util.MySqlDBConexion;
import entidad.Categoria;
import java.util.List;
public class LibroModel {
	
	
	private static Logger log = Logger.getLogger(LibroModel.class.getName());
	
	//===========================INSERTAR===============================
	public int registroLibro(Libro obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexi�n
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "insert into libro value(null,?,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnnio());
			pstm.setString(3, obj.getSerie());
			pstm.setInt(4,obj.getEstado());
			pstm.setInt(5,obj.getCategoria().getIdCategoria());
			log.info("SQL >>" + pstm);

		
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
	//========================Listar===================================
	public List<Libro> listaTodos(){
		ArrayList<Libro> salida = new ArrayList<Libro>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			//2 Se prepara el SQL
			String sql = "SELECT l.*, c.descripcion FROM libro l inner join categoria_libro c on l.idCategoria = c.idCategoria";
			psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Libro objLibro = null;
			Categoria objCate = null;
			while(rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setTitulo(rs.getString(2));
				objLibro.setAnnio(rs.getInt(3));
				objLibro.setSerie(rs.getString(4));
				objLibro.setFechaRegistro(rs.getTimestamp(5));
				objLibro.setEstado(rs.getInt(6));
				objCate = new Categoria();
				objCate.setIdCategoria(rs.getInt(7));
				objCate.setDescripcion(rs.getString(8));
				objLibro.setCategoria(objCate);
				salida.add(objLibro);
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
	//===========================ELIMINAR===============================
		public int eliminarLibro(int idLibro) {
			int salida = -1;
			Connection conn = null;
			PreparedStatement pstm = null;
			try {
				//1 Se crea la conexión
				conn = MySqlDBConexion.getConexion();
				//2 Se prepara el SQL
				String sql = "delete from libro where idLibro =?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, idLibro);
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
	
//===========================ACTULIZA===============================
	public int actualizaLibro(Libro obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "update libro set titulo=?, anio=?, serie=?, estado=?, idCategoria=? where idLibro=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnnio());
			pstm.setString(3, obj.getSerie());
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getCategoria().getIdCategoria());
			pstm.setInt(6, obj.getIdLibro());
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

	//====================CONSULTAR LIBRO====================================
	public List<Libro> listaCategoria(int idCategoria){
		ArrayList<Libro> salida = new ArrayList<Libro>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
		
			String sql = "SELECT l.*, c.descripcion FROM libro l inner join categoria_libro c on l.idCategoria = c.idCategoria where l.idCategoria=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,idCategoria );
			
			log.info(">>> " + psmt);
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Libro objLibro = null;
			Categoria objCategoria = null;
			while(rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setTitulo(rs.getString(2));
				objLibro.setAnnio(rs.getInt(3));
				objLibro.setSerie(rs.getString(4));
				objLibro.setFechaRegistro(rs.getTimestamp(5));
				objLibro.setEstado(rs.getInt(6));
				
				objCategoria = new Categoria();
				objCategoria.setIdCategoria(rs.getInt(7));
				objCategoria.setDescripcion(rs.getString(8));
				objLibro.setCategoria(objCategoria);
				salida.add(objLibro);
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
	//=======================================================================================================
	public List<Libro> listaporNombre(String titulo){
		ArrayList<Libro>salida = new ArrayList<Libro>();
		
		Connection conn=null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			//2 Se prepara el SQL
			String sql = "SELECT l.*, c.descripcion FROM libro l inner join categoria_libro c on l.idCategoria = c.idCategoria where l.titulo like ?;"; 
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, titulo + "%");
			
			log.info(">>> " + psmt);
			
			rs = psmt.executeQuery();
			
			Libro objLibro = null;
			Categoria objCategoria=null;
			while(rs.next()) {
				
			
			objLibro = new Libro();
			objLibro.setIdLibro(rs.getInt(1));
			objLibro.setTitulo(rs.getString(2));
			objLibro.setAnnio(rs.getInt(3));
			objLibro.setSerie(rs.getString(4));
			objLibro.setFechaRegistro(rs.getTimestamp(5));
			objLibro.setEstado(rs.getInt(6));
			
			objCategoria = new Categoria();
			objCategoria.setIdCategoria(rs.getInt(7));
			objCategoria.setDescripcion(rs.getString(8));
			objLibro.setCategoria(objCategoria);
			salida.add(objLibro);
			}
		} catch (Exception e) {
			// TODO: handle exception
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


