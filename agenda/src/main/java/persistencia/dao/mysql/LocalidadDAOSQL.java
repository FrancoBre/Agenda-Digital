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

	private static final String readByProv = "SELECT nombre FROM localidad WHERE idPais = ?;";
	
	public List<LocalidadDTO> readByProvincia(int idProvincia) {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<LocalidadDTO> provincias = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByProv);
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

	private LocalidadDTO getProvinciaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idProvincia");
		String nombre = resultSet.getString("Nombre");
		int provincia = resultSet.getInt("Provincia");
		int pais = resultSet.getInt("Pais");
		return new LocalidadDTO(id, nombre, provincia, pais);
	}
	
}
