package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Sede;
import entidad.Sala;
import entidad.Sala;
import entidad.Sede;
import util.MySqlDBConexion;

public class SalaModel {
	
	private static Logger log = Logger.getLogger(SalaModel.class.getName());
	//===========================INSERTAR===============================
	public int insertarSala(Sala obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try { 
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el SQL
			String sql = "insert into sala values(null,?,?,?,?, curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setInt(5,obj.getEstado());
			pstm.setInt(6, obj.getSede().getIdSede());
			
			log.info("SQL >>" + pstm);
			
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
	
	
	//========================Listar===================================
		public List<Sala> listaTodos(){
			ArrayList<Sala> salida = new ArrayList<Sala>();
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				//1 Se crea la conexion
				conn = MySqlDBConexion.getConexion();
				//2 Se prepara el SQL
				String sql = "SELECT A.*, s.nombre FROM sala A inner join sede s on A.idSede = s.idSede";
				psmt = conn.prepareStatement(sql);
				
				log.info(">>> " + psmt);
				//3 Se ejecuta el SQL en la base de datos
				rs = psmt.executeQuery();
				Sala objSala = null;
				Sede objSede = null;
				while(rs.next()) {
					objSala = new Sala();
					objSala.setIdSala(rs.getInt(1));
					objSala.setNumero(rs.getString(2));
					objSala.setPiso(rs.getInt(3));
					objSala.setNumAlumnos(rs.getInt(4));
					objSala.setRecursos(rs.getString(5));
					objSala.setFechaRegistro(rs.getTimestamp(6));
					objSala.setEstado(rs.getInt(7));
					objSede = new Sede();
					objSede.setIdSede(rs.getInt(8));
					objSede.setNombre(rs.getString(9));
					objSala.setSede(objSede);
					salida.add(objSala);
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
				public int eliminarSala(int idSala) {
					int salida = -1;
					Connection conn = null;
					PreparedStatement pstm = null;
					try {
						//1 Se crea la conexión
						conn = MySqlDBConexion.getConexion();
						//2 Se prepara el SQL
						String sql = "delete from sala where idSala =?";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1, idSala);
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
				public int actualizaSala(Sala obj) {
					int salida = -1;
					Connection conn = null;
					PreparedStatement pstm = null;
					try {
						//1 Se crea la conexión
						conn = MySqlDBConexion.getConexion();
						
						//2 Se prepara el SQL
						String sql = "update sala set numero=?, piso=?, numAlumnos=?, recursos=?, estado=?,idSede=? where idSala=?";
						pstm = conn.prepareStatement(sql);
						pstm.setString(1, obj.getNumero());
						pstm.setInt(2, obj.getPiso());
						pstm.setInt(3, obj.getNumAlumnos());
						pstm.setString(4, obj.getRecursos());
						pstm.setInt(5, obj.getEstado());
						pstm.setInt(6, obj.getSede().getIdSede());
						pstm.setInt(7, obj.getIdSala());
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
				//=================================================================
				
				public List<Sala> listaSede(int idSede){
					ArrayList<Sala> salida = new ArrayList<Sala>();
					
					Connection conn = null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					try {
						//1 Se crea la conexion
						conn = MySqlDBConexion.getConexion();
						
						//2 Se prepara el SQL
						String sql = "SELECT A.*, s.nombre FROM sala A inner join sede s on A.idSede = s.idSede where A.idSede=?";
						psmt = conn.prepareStatement(sql);
						psmt.setInt(1, idSede);
						
						//String sql = "call sp_Sala_list()";
						//psmt = conn.prepareStatement(sql);
						
						log.info(">>> " + psmt);
						
						//3 Se ejecuta el SQL en la base de datos
						rs = psmt.executeQuery();
						Sala objSala = null;
						Sede objSede = null;
						while(rs.next()) {
							objSala=new Sala();
							objSala.setIdSala(rs.getInt(1));
							objSala.setNumero(rs.getString(2));
							objSala.setPiso(rs.getInt(3));
							objSala.setNumAlumnos(rs.getInt(4));
							objSala.setRecursos(rs.getString(5));
							objSala.setFechaRegistro(rs.getTimestamp(6));
							objSala.setEstado(rs.getInt(7));
							objSede = new Sede();
							objSede.setIdSede(rs.getInt(8));
							objSede.setNombre(rs.getString(9));
							objSala.setSede(objSede);
							salida.add(objSala);
							
							
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
				
			//===========================================================================
				public List<Sala> listaporNombre(String titulo){
					ArrayList<Sala>salida = new ArrayList<Sala>();
					
					Connection conn=null;
					PreparedStatement psmt = null;
					ResultSet rs = null;
					
					try {
						//1 Se crea la conexion
						conn = MySqlDBConexion.getConexion();
						//2 Se prepara el SQL
						String sql = "SELECT A.*, s.nombre FROM sala A inner join sede s on A.idSede = s.idSede where A.piso like ?;"; 
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, titulo + "%");
						
						log.info(">>> " + psmt);
						
						rs = psmt.executeQuery();
						
						Sala objSala = null;
						Sede objSede=null;
						while(rs.next()) {
							
						
						objSala = new Sala();
						objSala.setIdSala(rs.getInt(1));
						objSala.setNumero(rs.getString(2));
						objSala.setPiso(rs.getInt(3));
						objSala.setNumAlumnos(rs.getInt(4));
						objSala.setRecursos(rs.getString(5));
						objSala.setFechaRegistro(rs.getTimestamp(6));
						objSala.setEstado(rs.getInt(7));
						
						objSede = new Sede();
						objSede.setIdSede(rs.getInt(8));
						objSede.setNombre(rs.getString(9));
						objSala.setSede(objSede);
						salida.add(objSala);
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
