package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.DomicilioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO {
	
	private static final String getById = "SELECT nombre FROM domicilio WHERE idDomicilio = ?;";
	private static final String getCalleAlturaById= "SELECT calle, altura FROM domicilio WHERE idDomicilio = ?;";
	private static final String insert = "INSERT INTO domicilio(idDomicilio, calle, altura, piso, depto, localidad) VALUES (default, ?, ?, ?, ?, ?);";
	private static final String delete = "DELETE FROM domicilio WHERE idDomicilio = ?;";
	private static final String update = "UPDATE domicilio SET calle = ? , altura = ? , piso = ?, depto = ? , localidad = ? WHERE idDomicilio = ?;";
	private static final String readMaxId = "SELECT idDomicilio FROM domicilio ORDER BY idDomicilio DESC LIMIT 0, 1;";

	public String getNombreDomicilioById(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		String ret = null;
		try {
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				ret = resultSet.getString("Nombre");
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
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setInt(3, domicilio.getPiso());
			statement.setInt(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getLocalidad());
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
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setInt(3, domicilio.getPiso());
			statement.setInt(4, domicilio.getDepto());
			statement.setInt(5, domicilio.getLocalidad());
			
			statement.setInt(6, domicilio.getIdDomicilio());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
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
		
		return isUpdateExitoso;
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
			if(resultSet.next())
				maxId = resultSet.getInt("idDomicilio");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		return maxId;
	}

	public String getCalleAlturaById(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		StringBuilder ret = new StringBuilder();
		try {
			statement = conexion.prepareStatement(getCalleAlturaById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				ret.append(resultSet.getString("Calle"));
				ret.append(" ");
				ret.append(resultSet.getString("Altura"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ret.toString();
	}

}
