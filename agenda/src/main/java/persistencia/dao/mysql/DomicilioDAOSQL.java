package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dto.DomicilioDTO;
import dto.PersonaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO {

    private static final String getById = "SELECT nombre FROM domicilio WHERE idDomicilio = ?;";
    private static final String getCalleAlturaById = "SELECT calle, altura FROM domicilio WHERE idDomicilio = ?;";
    private static final String insert = "INSERT INTO domicilio(idDomicilio, calle, altura, piso, depto, localidad) VALUES (default, ?, ?, ?, ?, ?);";
    private static final String delete = "DELETE FROM domicilio WHERE idDomicilio = ?;";
    private static final String update = "UPDATE domicilio SET calle = ? , altura = ? , piso = ?, depto = ? , localidad = ? WHERE idDomicilio = ?;";
    private static final String readMaxId = "SELECT idDomicilio FROM domicilio ORDER BY idDomicilio DESC LIMIT 0, 1;";
    private static final String readAll = "SELECT * FROM domicilio;";

    public boolean insert(DomicilioDTO domicilio) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isInsertExitoso = false;
	try {
	    statement = conexion.prepareStatement(insert);
	    statement.setString(1, domicilio.getCalle());
	    statement.setString(2, domicilio.getAltura());
	    statement.setString(3, domicilio.getPiso());
	    statement.setString(4, domicilio.getDepto());
	    statement.setInt(5, domicilio.getLocalidad());
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

    public boolean delete(DomicilioDTO domicilio) {
	// TODO Auto-generated method stub
	return false;
    }

    public boolean update(DomicilioDTO domicilio) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isUpdateExitoso = false;
	try {
	    statement = conexion.prepareStatement(update);

	    statement.setString(1, domicilio.getCalle());
	    statement.setString(2, domicilio.getAltura());
	    statement.setString(3, domicilio.getPiso());
	    statement.setString(4, domicilio.getDepto());
	    statement.setInt(5, domicilio.getLocalidad());

	    statement.setInt(6, domicilio.getIdDomicilio());
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

    public int readMaxId() {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	ResultSet resultSet;
	int maxId = 0;
	try {
	    statement = conexion.prepareStatement(readMaxId);
	    resultSet = statement.executeQuery();
	    if (resultSet.next())
		maxId = resultSet.getInt("idDomicilio");

	} catch (SQLException e) {
	    e.printStackTrace();

	}
	return maxId;
    }

    public String getCalleAlturaById(int id) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	ResultSet resultSet;
	StringBuilder ret = new StringBuilder();
	try {
	    statement = conexion.prepareStatement(getCalleAlturaById);
	    statement.setInt(1, id);
	    resultSet = statement.executeQuery();
	    if (resultSet.next())
		ret.append(resultSet.getString("Calle"));
	    ret.append(" ");
	    ret.append(resultSet.getString("Altura"));
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ret.toString();
    }

    public List<DomicilioDTO> readAll() {
	PreparedStatement statement;
	ResultSet resultSet; // Guarda el resultado de la query
	List<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readAll);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		try {
		    domicilios.add(getDomicilioDTO(resultSet));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return domicilios;
    }

    private DomicilioDTO getDomicilioDTO(ResultSet resultSet) throws SQLException, ParseException {
	int id = resultSet.getInt("idDomicilio");
	String calle = resultSet.getString("calle");
	String altura = resultSet.getString("altura");
	String piso = resultSet.getString("piso");
	String depto = resultSet.getString("depto");
	int localidad = resultSet.getInt("localidad");
	
	return new DomicilioDTO(id, calle, altura, piso, depto, localidad);
    }

}
