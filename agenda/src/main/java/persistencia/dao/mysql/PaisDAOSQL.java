package persistencia.dao.mysql;

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

    public List<PaisDTO> readAll() {
	PreparedStatement statement;
	ResultSet resultSet;
	ArrayList<PaisDTO> paises = new ArrayList<PaisDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readAll);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		paises.add(getPaisDTO(resultSet));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return paises;
    }

    private PaisDTO getPaisDTO(ResultSet resultSet) throws SQLException {
	int id = resultSet.getInt("idPais");
	String nombre = resultSet.getString("Nombre");
	return new PaisDTO(id, nombre);
    }

}
