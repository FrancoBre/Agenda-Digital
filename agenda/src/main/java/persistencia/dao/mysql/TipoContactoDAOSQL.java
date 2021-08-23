package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String readAll = "SELECT * FROM tipo_contacto;";
	private static final String readIdByNombre = "SELECT idTipo_contacto FROM tipo_contacto WHERE nombre = '?';";
	
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
				tiposContacto.add(getTipoContactoDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tiposContacto;
	}
	
	private TipoContactoDTO getTipoContactoDTO(ResultSet resultSet) throws SQLException
	{
		//int id = resultSet.getInt("idTipo_contacto");
		String nombre = resultSet.getString("Nombre");
		
		return getTipoContactoByName(nombre);

	}
	
	private TipoContactoDTO getTipoContactoByName(String name) {
		ArrayList<TipoContactoDTO> tipos = new ArrayList<TipoContactoDTO>();
		
		TipoContactoDTO uno = TipoContactoDTO.Familia;
		TipoContactoDTO dos = TipoContactoDTO.Amigo;
		TipoContactoDTO tres = TipoContactoDTO.Trabajo;
		TipoContactoDTO cuatro = TipoContactoDTO.Futbol;
		
		 tipos.add(uno);
		 tipos.add(dos);
		 tipos.add(tres);
		 tipos.add(cuatro);
		 
		 for (TipoContactoDTO tipo : tipos) {
			 if(tipo.name().equals(name)) return tipo;
		}
		 
		 return null;
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
				idTipoContacto = resultSet.getInt("idTipoContacto");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return idTipoContacto;
	}

}
