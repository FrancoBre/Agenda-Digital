package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO {

	private static final String readByProv = "SELECT * FROM localidad WHERE Provincia = ?;";
	private static final String readNombreProvinciaById = "select b.nombre from localidad a inner " +
	"join provincia b on (a.provincia = b.idProvincia) where a.idLocalidad = ? ;";
	
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
				provincias.add(getProvinciaDTO(resultSet));
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

	private LocalidadDTO getProvinciaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idProvincia");
		String nombre = resultSet.getString("Nombre");
		int provincia = resultSet.getInt("Provincia");
		int pais = resultSet.getInt("Pais");
		return new LocalidadDTO(id, nombre, provincia, pais);
	}

	@Override
	public String getNombreProvincia(int idLocalidad) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
