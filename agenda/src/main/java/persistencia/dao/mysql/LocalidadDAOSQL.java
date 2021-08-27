package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO {

    private static final String readAll = "SELECT * FROM localidad";
    
    private static final String readByNombreProv = "SELECT * FROM localidad l, provincia p WHERE p.idProvincia = l.provincia AND p.pais=l.pais AND p.nombre = ?;"; 
    private static final String readByProv = "SELECT * FROM localidad WHERE Provincia = ?;"; 
    private static final String readNombreProvinciaById = "select b.nombre from localidad a inner join provincia b on (a.provincia = b.idProvincia) where a.idLocalidad = ? ;";
    private static final String readIdByNombre = "SELECT idLocalidad FROM localidad WHERE nombre = ?;";
     
    private static final String selectById = "SELECT * FROM localidad WHERE idLocalidad = ? ";

    public List<LocalidadDTO> readAll() {
	PreparedStatement statement;
	ResultSet resultSet; // Guarda el resultado de la query
	List<LocalidadDTO> ret = new ArrayList<LocalidadDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readAll);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		try {
		    ret.add(getLocalidadDTO(resultSet));
		} catch (ParseException e) {
		    e.printStackTrace();
		}

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ret;
    }

    private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException, ParseException {
	int id = resultSet.getInt("idLocalidad");
	String nombre = resultSet.getString("Nombre");
	int idProvincia = resultSet.getInt("Provincia");
	int idPais = resultSet.getInt("Pais");

	return new LocalidadDTO(id, nombre, idProvincia, idPais);
    }

    
      public List<LocalidadDTO> readByProvincia(int idProvincia) {
      PreparedStatement statement; ResultSet resultSet; ArrayList<LocalidadDTO>
      provincias = new ArrayList<LocalidadDTO>(); Conexion conexion =
      Conexion.getConexion(); try { statement =
      conexion.getSQLConexion().prepareStatement(readByProv); statement.setInt(1,
      idProvincia); resultSet = statement.executeQuery(); while (resultSet.next())
      { provincias.add(getProvinciaDTO(resultSet)); } } catch (SQLException e) {
      e.printStackTrace(); } return provincias; }
      
      public List<LocalidadDTO> readByNombreProvincia(String Provincia) {
      PreparedStatement statement; ResultSet resultSet; ArrayList<LocalidadDTO>
      provincias = new ArrayList<LocalidadDTO>(); Conexion conexion =
      Conexion.getConexion(); try { statement =
      conexion.getSQLConexion().prepareStatement(readByNombreProv);
      statement.setString(1, Provincia); resultSet = statement.executeQuery();
      while (resultSet.next()) { provincias.add(getProvinciaDTO(resultSet)); } }
      catch (SQLException e) { e.printStackTrace(); } return provincias; }
      
      public String readNombreProvinciaById(int idLocalidad) { PreparedStatement
      statement; ResultSet resultSet; String nombreProvincia = null; Conexion
      conexion = Conexion.getConexion(); try { statement =
      conexion.getSQLConexion().prepareStatement(readNombreProvinciaById);
      statement.setInt(1, idLocalidad); resultSet = statement.executeQuery();
      nombreProvincia = resultSet.getString("Nombre"); } catch (SQLException e) {
      e.printStackTrace(); } return nombreProvincia;
      
      }
      
      private LocalidadDTO getProvinciaDTO(ResultSet resultSet) throws SQLException
      { int id = resultSet.getInt("idProvincia"); String nombre =
      resultSet.getString("Nombre"); int provincia = resultSet.getInt("Provincia");
      int pais = resultSet.getInt("Pais"); return new LocalidadDTO(id, nombre,
      provincia, pais); }
      
      // unused
      
      public String getNombreProvincia(int idLocalidad) { 
    	  // TODO Auto-generated method stub 
    	  return null; }
      
      public int readIdByNombre(String nombre) { PreparedStatement statement;
      ResultSet resultSet; int idLocalidad = 0; Conexion conexion =
      Conexion.getConexion(); try { statement =
      conexion.getSQLConexion().prepareStatement(readIdByNombre);
      statement.setString(1, nombre); resultSet = statement.executeQuery(); if
      (resultSet.next()) idLocalidad = resultSet.getInt("idLocalidad"); } catch
      (SQLException e) { e.printStackTrace(); } return idLocalidad; }
     
    
}
