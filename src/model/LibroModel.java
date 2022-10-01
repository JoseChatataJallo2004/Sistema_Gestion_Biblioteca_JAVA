package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import entidad.Libro;
import util.MySqlDBConexion;

public class LibroModel {
	
	
	private static Logger log = Logger.getLogger(LibroModel.class.getName());
	
	
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
	}
