package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String insert = "INSERT INTO tipo_contacto(idTipo_contacto, Tipo) VALUES(default, ? )";
	private static final String update = "UPDATE tipo_contacto SET Tipo = ? WHERE idTipo_contacto = ? ;";
	private static final String deleteTipo = "DELETE FROM tipo_contacto WHERE Tipo = ?";
	private static final String deleteId = "DELETE FROM tipo_contacto WHERE idTipo_contacto = ?";
	
	private static final String readAll = "SELECT * FROM tipo_contacto;";
	private static final String readIdByNombre = "SELECT idTipo_contacto FROM tipo_contacto WHERE Tipo = ?;";
	private static final String readNombreById = "SELECT nombre FROM tipo_contacto WHERE idTipo_contacto = ?;";
	
	
	public boolean insert(TipoContactoDTO tipoContacto)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, tipoContacto.getDescripcion());
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
	
	public boolean update(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, tipoContacto.getDescripcion());
			statement.setInt(2, tipoContacto.getIdTipo());
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
	
	public boolean delete(String tipoContacto)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deleteTipo);
			statement.setString(1, tipoContacto);
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
	
	public boolean delete(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deleteId);
			statement.setInt(1, tipoContacto.getIdTipo());
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
	
	
	
	public List<TipoContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tiposContacto = new ArrayList<TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) 
			{
				tiposContacto.add(getTipoDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tiposContacto;
	}
	
	private TipoContactoDTO getTipoDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idTipo_contacto");
		String descrip = resultSet.getString("Tipo");
		
		return new TipoContactoDTO(id, descrip);
	}
	public int readIdByNombre(String nombre) {
		PreparedStatement statement;
		ResultSet resultSet;
		int idTipoContacto = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readIdByNombre);
			statement.setString(1, nombre);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				idTipoContacto = resultSet.getInt("idTipo_contacto");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idTipoContacto;
	}

	@Override
	public String readNombreById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
