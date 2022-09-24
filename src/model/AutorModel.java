package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import entidad.Autor;
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
}
