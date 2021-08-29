package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.PaisDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PaisDAO;

public class PaisDAOSQL implements PaisDAO {

	private static final String readAll = "SELECT * FROM pais;";
	private static final String insert = "INSERT INTO pais(idPais, nombre) VALUES(default, ?);";
    private static final String update = "UPDATE pais SET nombre = ? WHERE idPais = ?;";
    private static final String delete = "DELETE FROM pais WHERE idPais = ?; DELETE FROM provincia WHERE Pais = ?";
	private static final String idPaisByNombre = "SELECT idPais FROM pais WHERE nombre = ?;";
    
    
	public List<PaisDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PaisDTO> paises = new ArrayList<PaisDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				paises.add(getPaisDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return paises;
	}
	
	private PaisDTO getPaisDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPais");
		String nombre = resultSet.getString("Nombre");
		return new PaisDTO(id, nombre);
	}

	public boolean insert(String nombrePais) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
		    statement = conexion.prepareStatement(insert);
		    statement.setString(1, nombrePais);	
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

	public boolean update(PaisDTO editPais) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
		    statement = conexion.prepareStatement(update);
		    statement.setString(1, editPais.getNombre());
		    statement.setInt(2, 4);
		    
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
	
	public boolean delete(int idPais) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
		    statement = conexion.prepareStatement(delete);
		    statement.setInt(1, idPais);	
		    statement.setInt(2, idPais);	
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

	public int getIdPaisByNombre(String nombrePais) {
		PreparedStatement statement;
		ResultSet resultSet;
		int idPais = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(idPaisByNombre);
			statement.setString(1, nombrePais);	
			resultSet = statement.executeQuery();
			if(resultSet.next())
				idPais = resultSet.getInt("idPais");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return idPais;
	}
	

}
