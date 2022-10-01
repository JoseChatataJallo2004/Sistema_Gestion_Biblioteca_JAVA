package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import entidad.Sala;
import util.MySqlDBConexion;

public class SalaModel {
	
	private static Logger log = Logger.getLogger(SalaModel.class.getName());
	
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

}
