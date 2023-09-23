package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Categoria;
import entidad.Libro;
import entidad.Pais;
import entidad.Pais;
import entidad.Proveedor;
import entidad.Proveedor;
import util.MySqlDBConexion;

public class ProveedorModel {
		
private static Logger log = Logger.getLogger(ProveedorModel.class.getName());
	
	
	public int registroProveedor(Proveedor obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexi�n
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "insert into proveedor value(null,?,?,?,?,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getDni());
			pstm.setString(4, obj.getDireccion());
			pstm.setString(5,obj.getTelefono());
			pstm.setString(6,obj.getCorreo());
			pstm.setInt(7,obj.getEstado());
			pstm.setInt(8,obj.getPais().getIdPais());
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
	//============================================================================================================
	//========================Listar===================================
		public List<Proveedor> listaTodos(){
			ArrayList<Proveedor> salida = new ArrayList<Proveedor>();
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				//1 Se crea la conexion
				conn = MySqlDBConexion.getConexion();
				//2 Se prepara el SQL
				String sql = "SELECT l.*, c.nombre FROM Proveedor l inner join pais c on l.idPais = c.idPais";
				psmt = conn.prepareStatement(sql);
				
				log.info(">>> " + psmt);
				//3 Se ejecuta el SQL en la base de datos
				rs = psmt.executeQuery();
				Proveedor objProveedor = null;
				Pais objPais = null;
				while(rs.next()) {
					objProveedor = new Proveedor();
					objProveedor.setIdProveedor(rs.getInt(1));
					objProveedor.setNombre(rs.getString(2));
					objProveedor.setApellido(rs.getString(3));
					objProveedor.setDni(rs.getString(4));
					objProveedor.setDireccion(rs.getString(5));
					objProveedor.setTelefono(rs.getString(6));
					objProveedor.setCorreo(rs.getString(7));
					objProveedor.setFechaRegistro(rs.getTimestamp(8));
					objProveedor.setEstado(rs.getInt(9));
					objPais = new Pais();
					objPais.setIdPais(rs.getInt(10));
					objPais.setNombre(rs.getString(11));
					objProveedor.setPais(objPais);
					salida.add(objProveedor);
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
			public int eliminarProveedor(int idProveedor) {
				int salida = -1;
				Connection conn = null;
				PreparedStatement pstm = null;
				try {
					//1 Se crea la conexión
					conn = MySqlDBConexion.getConexion();
					//2 Se prepara el SQL
					String sql = "delete from Proveedor where idProveedor =?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, idProveedor);
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
		public int actualizaProveedor(Proveedor obj) {
			int salida = -1;
			Connection conn = null;
			PreparedStatement pstm = null;
			try {
				//1 Se crea la conexión
				conn = MySqlDBConexion.getConexion();
				
				//2 Se prepara el SQL
				String sql = "update Proveedor set nombres=?, apellidos=?, dni=?, direccion=? ,telefono=?, correo=?,estado=?, idPais=? where idProveedor=?";
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, obj.getNombre());
				pstm.setString(2, obj.getApellido());
				pstm.setString(3, obj.getDni());
				pstm.setString(4, obj.getDireccion());
				pstm.setString(5, obj.getTelefono());
				pstm.setString(6, obj.getCorreo());
				pstm.setInt(7, obj.getEstado());
				pstm.setInt(8, obj.getPais().getIdPais());
				pstm.setInt(9, obj.getIdProveedor());
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
		
		public List<Proveedor> listapais(int idpais){
			ArrayList<Proveedor> salida = new ArrayList<Proveedor>();
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				//1 Se crea la conexion
				conn = MySqlDBConexion.getConexion();
			
				String sql = "SELECT l.*, c.nombre FROM Proveedor l inner join pais c on l.idPais = c.idPais where l.idPais=?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1,idpais );
				
				log.info(">>> " + psmt);
				//3 Se ejecuta el SQL en la base de datos
				rs = psmt.executeQuery();
				Proveedor objProveedor = null;
				Pais objPais = null;
				while(rs.next()) {
					objProveedor = new Proveedor();
					objProveedor.setIdProveedor(rs.getInt(1));
					objProveedor.setNombre(rs.getString(2));
					objProveedor.setApellido(rs.getString(3));
					objProveedor.setDni(rs.getString(4));
					objProveedor.setDireccion(rs.getString(5));
					objProveedor.setTelefono(rs.getString(6));
					objProveedor.setCorreo(rs.getString(7));
					objProveedor.setFechaRegistro(rs.getTimestamp(8));
					objProveedor.setEstado(rs.getInt(9));
					objPais = new Pais();
					objPais.setIdPais(rs.getInt(10));
					objPais.setNombre(rs.getString(11));
					objProveedor.setPais(objPais);
					salida.add(objProveedor);
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
		
		public List<Proveedor> listaNombre(String filtro) {
			ArrayList<Proveedor> salida = new ArrayList<Proveedor>();

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				// 1 Se crea la conexion
				conn = MySqlDBConexion.getConexion();

				String sql = "SELECT l.*, c.nombre FROM Proveedor l inner join pais c on l.idPais = c.idPais where l.nombres like ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, filtro + "%");

				log.info(">>> " + psmt);
				// 3 Se ejecuta el SQL en la base de datos
				rs = psmt.executeQuery();
				Proveedor objProveedor = null;
				Pais objPais = null;
				while (rs.next()) {
					objProveedor = new Proveedor();
					objProveedor.setIdProveedor(rs.getInt(1));
					objProveedor.setNombre(rs.getString(2));
					objProveedor.setApellido(rs.getString(3));
					objProveedor.setDni(rs.getString(4));
					objProveedor.setDireccion(rs.getString(5));
					objProveedor.setTelefono(rs.getString(6));
					objProveedor.setCorreo(rs.getString(7));
					objProveedor.setFechaRegistro(rs.getTimestamp(8));
					objProveedor.setEstado(rs.getInt(9));
					objPais = new Pais();
					objPais.setIdPais(rs.getInt(10));
					objPais.setNombre(rs.getString(11));
					objProveedor.setPais(objPais);
					salida.add(objProveedor);
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
