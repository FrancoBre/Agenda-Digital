package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOSQL implements ProvinciaDAO {
	private static final String readByPais = "SELECT * FROM provincia WHERE pais = ? ;";
	private static final String getNombreById = "SELECT Nombre FROM provincia WHERE idProvincia = ? ;";
	
	private static final String insert = "INSERT INTO provincia(idProvincia, Nombre, Pais) VALUES(default, ?, ?):";
	private static final String update = "UPDATE provincia SET Nombre = ? WHERE idProvincia = ?;";
	private static final String delete = "DELETE * FROM provincia WHERE idProvincia = ?;";
	
	private static final String idProvinciaByNombre = "SELECT idPais FROM pais WHERE nombre = ?;";

	public List<ProvinciaDTO> readByPais(int idPais) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByPais);
			statement.setInt(1, idPais);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				provincias.add(getProvinciaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provincias;
	}
	
	//Unused
	public String getNombreById(int idProvincia) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		String nombre = null;
		try {
			statement = conexion.getSQLConexion().prepareStatement(getNombreById);
			statement.setInt(1, idProvincia);
			resultSet = statement.executeQuery();
			nombre = resultSet.getString("Nombre");
			return nombre;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return nombre;
	}

	private ProvinciaDTO getProvinciaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idProvincia");
		String nombre = resultSet.getString("Nombre");
		int pais = resultSet.getInt("Pais");
		return new ProvinciaDTO(id, nombre, pais);
	}

	public boolean insert(ProvinciaDTO nuevaProvincia)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, nuevaProvincia.getNombre());
			statement.setInt(2, nuevaProvincia.getPais());
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
	
	@Override
	public boolean update(ProvinciaDTO editProvincia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
		    statement = conexion.prepareStatement(update);
		    statement.setString(1, editProvincia.getNombre());
		    statement.setInt(2, editProvincia.getIdProvincia());
		    
		    if (statement.executeUpdate() > 0) {
		    	conexion.commit();
		    	isUpdateExitoso = true;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
		    	conexion.rollback();
		    } catch (SQLException e1) {
		    	e1.printStackTrace();
		    }
		}

		return isUpdateExitoso;
	   }
	
	public boolean delete(int idProvincia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
		    statement = conexion.prepareStatement(delete);
		    statement.setInt(1, idProvincia);	
		    if (statement.executeUpdate() > 0) {
		    	conexion.commit();
		    	isInsertExitoso = true;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
		    	conexion.rollback();
		    } catch (SQLException e1) {
		    	e1.printStackTrace();
		    }
		}
		return isInsertExitoso;
	}

	@Override
	public int getIdProvinciaByNombre(String nombreProvincia) {
		PreparedStatement statement;
		ResultSet resultSet;
		int idProvincia = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(idProvinciaByNombre);
			statement.setString(1, nombreProvincia);	
			resultSet = statement.executeQuery();
			if(resultSet.next())
				idProvincia = resultSet.getInt("idProvincia");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return idProvincia;
	}
}
