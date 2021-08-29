package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MedioTransporteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MedioTransporteDAO;

public class MedioTransporteDAOSQL implements MedioTransporteDAO {

    private static final String insert = "INSERT INTO medio_transporte(idMedio_transporte, nombre) VALUES(default, ? )";
	private static final String update = "UPDATE medio_transporte SET nombre = ? WHERE idMedio_transporte = ? ;";
	private static final String deleteByNombre = "DELETE FROM medio_transporte WHERE nombre = ?";
	private static final String deleteById = "DELETE FROM medio_transporte WHERE idMedio_transporte = ?";
	
	private static final String readAll = "SELECT * FROM medio_transporte;";
	private static final String readIdByNombre = "SELECT idMedio_transporte FROM medio_transporte WHERE nombre = ?;";
	private static final String readNombreById = "SELECT nombre FROM medio_transporte WHERE idMedio_transporte = ?;";
	
	
	public boolean insert(MedioTransporteDTO medioTransporte)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, medioTransporte.getNombre());
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
	
	public boolean update(MedioTransporteDTO medioTransporte) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, medioTransporte.getNombre());
			statement.setInt(2, medioTransporte.getIdMedioTransporte());
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
	
	public boolean delete(String medioTransporte)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deleteByNombre);
			statement.setString(1, medioTransporte);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public boolean delete(MedioTransporteDTO medioTransporte) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deleteById);
			statement.setInt(1, medioTransporte.getIdMedioTransporte());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	
	
	public List<MedioTransporteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<MedioTransporteDTO> mediosTransporte = new ArrayList<MedioTransporteDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) 
			{
			    mediosTransporte.add(getMedioTransporteDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return mediosTransporte;
	}
	
	private MedioTransporteDTO getMedioTransporteDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idMedio_transporte");
		String nombre = resultSet.getString("Nombre");
		
		return new MedioTransporteDTO(id, nombre);
	}
	public int readIdByNombre(String nombre) {
		PreparedStatement statement;
		ResultSet resultSet;
		int idMedioTransporte = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readIdByNombre);
			statement.setString(1, nombre);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				idMedioTransporte = resultSet.getInt("idMedio_transporte");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idMedioTransporte;
	}

	@Override
	public String readNombreById(int id) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		String ret = "";
		try {
			statement = conexion.prepareStatement(readNombreById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				ret = resultSet.getString("Nombre");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
