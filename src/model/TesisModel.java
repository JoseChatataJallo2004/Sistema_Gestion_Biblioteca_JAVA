package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Alumno;
import entidad.Alumno;
import entidad.Tesis;
import entidad.Tesis;
import util.MySqlDBConexion;

public class TesisModel {
		
private static Logger log = Logger.getLogger(TesisModel.class.getName());
	
	
	public int registroTesis(Tesis obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexi�n
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "insert into tesis value(null,?,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getTema());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setInt(4,obj.getEstado());
			pstm.setInt(5,obj.getAlumno().getIdAlumno());
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
	//////////////////////
	//========================Listar===================================
	public List<Tesis> listaTodos(){
		ArrayList<Tesis> salida = new ArrayList<Tesis>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			//2 Se prepara el SQL
			String sql = "SELECT l.*, c.nombres FROM tesis l inner join alumno c on l.idAlumno = c.idAlumno";
			psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Tesis objTesis = null;
			Alumno objAlum= null;
			while(rs.next()) {
				objTesis = new Tesis();
				objTesis.setIdtesis(rs.getInt(1));
				objTesis.setTitulo(rs.getString(2));
				objTesis.setTema(rs.getString(3));
				objTesis.setFechaCreacion(rs.getDate(4));
				objTesis.setFechaRegistro(rs.getTimestamp(5));
				objTesis.setEstado(rs.getInt(6));
				objAlum = new Alumno();
				objAlum.setIdAlumno(rs.getInt(7));
				objAlum.setNombres(rs.getString(8));
				objTesis.setAlumno(objAlum);
				salida.add(objTesis);
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
		public int eliminarTesis(int idTesis) {
			int salida = -1;
			Connection conn = null;
			PreparedStatement pstm = null;
			try {
				//1 Se crea la conexión
				conn = MySqlDBConexion.getConexion();
				//2 Se prepara el SQL
				String sql = "delete from Tesis where idTesis =?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, idTesis);
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
	public int actualizaTesis(Tesis obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "update Tesis set titulo=?, tema=?, fechaCreacion=?, estado=?, idAlumno=? where idTesis=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getTema());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getAlumno().getIdAlumno());
			pstm.setInt(6, obj.getIdtesis());
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
	
	//=====================================================================
	public List<Tesis> listaAlumno(int idAlumno){
		ArrayList<Tesis> salida = new ArrayList<Tesis>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
		
			String sql = "SELECT l.*, c.nombres FROM tesis l inner join alumno c on l.idAlumno = c.idAlumno where l.idAlumno=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,idAlumno );
			
			log.info(">>> " + psmt);
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Tesis objTesis = null;
			Alumno objAlumno = null;
			while(rs.next()) {
				objTesis = new Tesis();
				objTesis.setIdtesis(rs.getInt(1));
				objTesis.setTitulo(rs.getString(2));
				objTesis.setTema(rs.getString(3));
				objTesis.setFechaCreacion(rs.getDate(4));
				objTesis.setFechaRegistro(rs.getTimestamp(5));
				objTesis.setEstado(rs.getInt(6));
				
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(7));
				objAlumno.setNombres(rs.getString(8));
				objTesis.setAlumno(objAlumno);
				salida.add(objTesis);
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
	
	
	//=====================================================================
	public List<Tesis> listaPorTema(String tema) {
		ArrayList<Tesis> salida = new ArrayList<Tesis>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el SQL
			String sql = "select t.*, a.nombres from tesis t inner join alumno a on t.idAlumno = a.idAlumno where t.tema like ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, tema + "%");

			log.info(">>> " + psmt);

			// 3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Tesis c = null;
			Alumno objAlu = null;
			while (rs.next()) {
				c = new Tesis();

				// Se colocan los campos de la base de datos
				c.setIdtesis(rs.getInt(1));
				c.setTitulo(rs.getString(2));
				c.setTema(rs.getString(3));
				c.setFechaCreacion(rs.getDate(4));
				c.setFechaRegistro(rs.getTimestamp(5));
				c.setEstado(rs.getInt(6));

				objAlu = new Alumno();
				objAlu.setIdAlumno(rs.getInt(7));
				objAlu.setNombres(rs.getString(8));
				c.setAlumno(objAlu);
				salida.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}

		}
		return salida;
	}
	
}
