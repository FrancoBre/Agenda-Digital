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

//    private static final String getById = "SELECT nombre FROM domicilio WHERE idDomicilio = ?;";
//    private static final String getCalleAlturaById = "SELECT calle, altura FROM domicilio WHERE idDomicilio = ?;";
    private static final String readAll = "SELECT * FROM domicilio";
    private static final String insert = "INSERT INTO domicilio(idDomicilio, calle, altura, piso, depto, localidad) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String update = "UPDATE domicilio SET calle = ? , altura = ? , piso = ?, depto = ? , localidad = ? WHERE idDomicilio = ?;";
    private static final String readMaxId = "SELECT idDomicilio FROM domicilio ORDER BY idDomicilio DESC LIMIT 0, 1;";

    public List<DomicilioDTO> readAll() {
	PreparedStatement statement;
	ResultSet resultSet; // Guarda el resultado de la query
	List<DomicilioDTO> ret = new ArrayList<DomicilioDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readAll);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		try {
		    ret.add(getDomicilioDTO(resultSet));
		} catch (ParseException e) {
		    e.printStackTrace();
		}

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ret;
    }

    private DomicilioDTO getDomicilioDTO(ResultSet resultSet) throws SQLException, ParseException {
	int id = resultSet.getInt("idDomicilio");
	String calle = resultSet.getString("Calle");
	String altura = resultSet.getString("Altura");
	String piso = resultSet.getString("Piso");
	String depto = resultSet.getString("Depto");
	int idLocalidad = resultSet.getInt("Localidad");

	return new DomicilioDTO(id, calle, altura, piso, depto, idLocalidad);
    }

    public boolean insert(DomicilioDTO domicilio) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isInsertExitoso = false;
	try {
	    statement = conexion.prepareStatement(insert);
	    statement.setInt(1, domicilio.getIdDomicilio());
	    statement.setString(2, domicilio.getCalle());
	    statement.setString(3, domicilio.getAltura());
	    statement.setString(4, domicilio.getPiso());
	    statement.setString(5, domicilio.getDepto());
	    statement.setInt(6, domicilio.getLocalidad().getIdLocalidad());
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
	    statement.setInt(5, domicilio.getLocalidad().getIdLocalidad());

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

    /*
     * public String getCalleAlturaById(int id) { PreparedStatement statement;
     * Connection conexion = Conexion.getConexion().getSQLConexion(); ResultSet
     * resultSet; StringBuilder ret = new StringBuilder(); try { statement =
     * conexion.prepareStatement(getCalleAlturaById); statement.setInt(1, id);
     * resultSet = statement.executeQuery(); if (resultSet.next())
     * ret.append(resultSet.getString("Calle")); ret.append(" ");
     * ret.append(resultSet.getString("Altura")); } catch (SQLException e) {
     * e.printStackTrace(); } return ret.toString(); }
     * 
     * public String getNombreDomicilioById(int id) { PreparedStatement statement;
     * Connection conexion = Conexion.getConexion().getSQLConexion(); ResultSet
     * resultSet; String ret = null; try { statement =
     * conexion.prepareStatement(getById); statement.setInt(1, id); resultSet =
     * statement.executeQuery(); if (resultSet.next()) ret =
     * resultSet.getString("Nombre"); } catch (SQLException e) {
     * 
     * } return ret; }
     */

}
