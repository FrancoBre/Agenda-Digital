package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.DomicilioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO {
	
	private static final String getById = "SELECT nombre FROM domicilio WHERE idDomicilio = ?";
	private static final String insert = "INSERT INTO domicilio(idDomicilio, calle, altura, piso, depto, localidad) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM domicilio WHERE idDomicilio = ?";
	private static final String update = "UPDATE domicilio SET calle = ? , altura = ? , piso = ?, depto = ? , localidad = ? WHERE idDomicilio = ?";
	private static final String readMaxId = "SELECT idDomicilio FROM domicilio ORDER BY idDomicilio DESC LIMIT 0, 1;";

	public String getNombreDomicilioById(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		String ret = null;
		try {
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, id);
			statement.getResultSet();
		}
		catch (SQLException e) {
			
		}
		return ret;
	}

	public boolean insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, domicilio.getIdDomicilio());
			statement.setString(2, domicilio.getCalle());
			statement.setString(3, domicilio.getAltura());
			statement.setInt(4, domicilio.getPiso());
			statement.setInt(5, domicilio.getDepto());
			statement.setInt(6, domicilio.getLocalidad());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	public boolean delete(DomicilioDTO domicilio) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(DomicilioDTO domicilio) {
		// TODO Auto-generated method stub
		return false;
	}
 
	public int readMaxId() {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		int maxId = 0;
		try
		{
			statement = conexion.prepareStatement(readMaxId);
			resultSet = statement.executeQuery();
			maxId = resultSet.getInt("idDomicilio");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		return maxId;
	}

}
