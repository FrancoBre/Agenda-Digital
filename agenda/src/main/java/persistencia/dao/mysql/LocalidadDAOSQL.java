package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO {

	private static final String readByNombreProv ="SELECT * FROM localidad l, provincia p WHERE p.idProvincia "+
	" = l.provincia AND p.pais=l.pais AND p.nombre = ?;";
	private static final String readByProv = "SELECT * FROM localidad WHERE Provincia = ?;";
	private static final String readNombreProvinciaById = "select b.nombre from localidad a inner " +
	"join provincia b on (a.provincia = b.idProvincia) where a.idLocalidad = ? ;";
	private static final String readIdByNombre = "SELECT idLocalidad FROM localidad WHERE nombre = ?;";
	
	private static final String insert = "INSERT INTO localidad(idLocalidad, Nombre, Provincia, Pais) VALUES(default, ?, ?, ?);";
	private static final String update = "UPDATE localidad SET Nombre = ? WHERE idLocalidad = ?;";
	private static final String delete = "DELETE FROM localidad WHERE idLocalidad = ?;";
	
	private static final String idLocalidadByNombre = "SELECT idLocalidad FROM localidad WHERE Nombre = ?;";
	
	public List<LocalidadDTO> readByProvincia(int idProvincia) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<LocalidadDTO> provincias = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByProv);
			statement.setInt(1, idProvincia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				provincias.add(getLocalidadDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provincias;
	}
	
	public List<LocalidadDTO> readByNombreProvincia(String Provincia) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<LocalidadDTO> provincias = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByNombreProv);
			statement.setString(1, Provincia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				provincias.add(getLocalidadDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provincias;
	}
	
	public String readNombreProvinciaById(int idLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet;
		String nombreProvincia = null;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readNombreProvinciaById);
			statement.setInt(1, idLocalidad);
			resultSet = statement.executeQuery();
			nombreProvincia = resultSet.getString("Nombre");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return nombreProvincia;
		
	}

	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idLocalidad");
		String nombre = resultSet.getString("Nombre");
		int provincia = resultSet.getInt("Provincia");
		int pais = resultSet.getInt("Pais");
		return new LocalidadDTO(id, nombre, provincia, pais);
	}


	public int readIdByNombre(String nombre) {
		PreparedStatement statement;
		ResultSet resultSet;
		int idLocalidad = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readIdByNombre);
			statement.setString(1, nombre);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				idLocalidad = resultSet.getInt("idLocalidad");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idLocalidad;
	}

	@Override
	public boolean insert(LocalidadDTO nuevaLocalidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, nuevaLocalidad.getNombre());
			statement.setInt(2, nuevaLocalidad.getProvincia());
			statement.setInt(3, nuevaLocalidad.getPais());
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
	public boolean update(LocalidadDTO editLocalidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
		    statement = conexion.prepareStatement(update);
		    statement.setString(1, editLocalidad.getNombre());
		    statement.setInt(2, editLocalidad.getIdLocalidad());
		    
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

	@Override
	public boolean delete(int idLocalidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
		    statement = conexion.prepareStatement(delete);
		    statement.setInt(1, idLocalidad);	
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
	public int getIdProvinciaByNombre(String nombreLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet;
		int idProvincia = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(idLocalidadByNombre);
			statement.setString(1, nombreLocalidad);	
			resultSet = statement.executeQuery();
			if(resultSet.next())
				idProvincia = resultSet.getInt("idLocalidad");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return idProvincia;
	}
	
}
