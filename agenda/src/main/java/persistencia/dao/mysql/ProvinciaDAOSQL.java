package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOSQL implements ProvinciaDAO {

    private static final String readAll = "SELECT * FROM personas";
	private static final String readByPais = "SELECT * FROM provincia WHERE pais = ? ;";
	private static final String getNombreById = "SELECT nombre FROM provincia WHERE idProvincia = ? ;";

    public List<ProvinciaDTO> readAll() {
	PreparedStatement statement;
	ResultSet resultSet; // Guarda el resultado de la query
	List<ProvinciaDTO> personas = new ArrayList<ProvinciaDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readAll);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		personas.add(getProvinciaDTO(resultSet));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return personas;
    }

    private ProvinciaDTO getProvinciaDTO(ResultSet resultSet) throws SQLException {
	int id = resultSet.getInt("idProvincia");
	String nombre = resultSet.getString("Nombre");
	int idPais = resultSet.getInt("Pais");
	return new ProvinciaDTO(id, nombre, idPais);
    }


    public List<ProvinciaDTO> readByPais(int idPais) { 
    	PreparedStatement statement;
    	ResultSet resultSet;
    	ArrayList<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
    	Conexion conexion = Conexion.getConexion();
    	try {
    		statement = conexion.getSQLConexion().prepareStatement(readByPais);
    		statement.setInt(1, idPais);
    		resultSet = statement.executeQuery();
    		while (resultSet.next()) { 
    			provincias.add(getProvinciaDTO(resultSet)); 
    			} 
    		} 
    	catch (SQLException e) 
    	{ 
    		e.printStackTrace(); 
    	} 
    	return provincias; 
    }
     
    public String getNombreById(int idProvincia) { 
    	PreparedStatement statement;
    	ResultSet resultSet;
    	Conexion conexion = Conexion.getConexion();
     String nombre = null; try { statement =
    conexion.getSQLConexion().prepareStatement(getNombreById);
    statement.setInt(1, idProvincia); resultSet = statement.executeQuery();
    nombre = resultSet.getString("Nombre"); return nombre; } 
     catch (SQLException e) 
     { 
    	 e.printStackTrace(); 
     } 
     	return nombre; 
    }
   

}
