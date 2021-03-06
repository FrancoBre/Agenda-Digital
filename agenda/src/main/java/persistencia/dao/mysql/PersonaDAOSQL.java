package persistencia.dao.mysql;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*import persistencia.conexion.Conexion;*/
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {

    private static final String insert = "INSERT INTO personas(idPersona, nombrePersona, telefono, email, dominioEmail, nacimiento, domicilio, tipo_contacto, medio_transporte) VALUES(default, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String update = "UPDATE personas SET nombrePersona = ? , telefono = ? , email = ? , dominioEmail = ? , nacimiento = ? , domicilio = ?, tipo_contacto = ?, medio_transporte = ? WHERE idPersona = ?;";
    private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
    private static final String readall = "SELECT * FROM personas";
    private static final String readMaxId = "SELECT idPersona FROM personas ORDER BY idPersona DESC LIMIT 0, 1;";
    private static final String READALLDATE = "SELECT p.idPersona, p.nombrePersona, p.telefono, p.email, p.dominioEmail , p.nacimiento, d.calle, d.altura, d.piso, d.depto, "
	    + "l.nombreLocalidad AS localidad, pr.nombreProvincia AS provincia, pa.nombrePais AS pais, c.Tipo AS Etiqueta, p.medio_transporte "
	    + "FROM personas p, domicilio d, localidad l, provincia pr, pais pa, tipo_contacto c "
	    + "WHERE p.Domicilio = d.idDomicilio AND d.Localidad=l.idLocalidad AND l.provincia= idProvincia AND pr.pais=pa.idPais AND p.tipo_contacto = c.idTipo_contacto;";

    public boolean insert(PersonaDTO persona) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isInsertExitoso = false;
	try {
	    statement = conexion.prepareStatement(insert);
	    statement.setString(1, persona.getNombrePersona());
	    statement.setString(2, persona.getTelefono());
	    statement.setString(3, persona.getEmail());
	    statement.setString(4, persona.getDominioEmail());
	    statement.setDate(5, persona.getNacimiento());
	    statement.setInt(6, persona.getDomicilio());
	    statement.setInt(7, persona.getTipo_contacto());
	    statement.setInt(8, persona.getMedio_transporte());

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

    public boolean update(PersonaDTO persona_a_editar) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isUpdateExitoso = false;
	try {
	    statement = conexion.prepareStatement(update);
	    statement.setString(1, persona_a_editar.getNombrePersona());
	    statement.setString(2, persona_a_editar.getTelefono());
	    statement.setString(3, persona_a_editar.getEmail());
	    statement.setString(4, persona_a_editar.getDominioEmail());
	    statement.setDate(5, persona_a_editar.getNacimiento());
	    statement.setInt(6, persona_a_editar.getDomicilio());
	    statement.setInt(7, persona_a_editar.getTipo_contacto());
	    statement.setInt(8, persona_a_editar.getMedio_transporte());
	    statement.setInt(9, persona_a_editar.getIdPersona());
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

    public boolean delete(PersonaDTO persona_a_eliminar) {
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isdeleteExitoso = false;
	try {
	    statement = conexion.prepareStatement(delete);
	    statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
	    if (statement.executeUpdate() > 0) {
		conexion.commit();
		isdeleteExitoso = true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return isdeleteExitoso;
    }

    public List<PersonaDTO> READALLDATE() {
	PreparedStatement statement;
	ResultSet resultSet; // Guarda el resultado de la query
	ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(READALLDATE);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		try {
		    personas.add(getPersonaDTO(resultSet));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return personas;
    }

    public List<PersonaDTO> readAll() {
	PreparedStatement statement;
	ResultSet resultSet; // Guarda el resultado de la query
	ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
	Conexion conexion = Conexion.getConexion();
	try {
	    statement = conexion.getSQLConexion().prepareStatement(readall);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		try {
		    personas.add(getPersonaDTO(resultSet));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return personas;
    }

    private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException, ParseException {
	int id = resultSet.getInt("idPersona");
	String nombre = resultSet.getString("NombrePersona");
	String tel = resultSet.getString("Telefono");
	String email = resultSet.getString("Email");
	String dominioEmail = resultSet.getString("DominioEmail");
	String nac = resultSet.getString("Nacimiento");
	String calle = resultSet.getString("calle");
	String altura = resultSet.getString("altura");
	String piso = resultSet.getString("piso");
	String depto = resultSet.getString("depto");
	String localidad = resultSet.getString("localidad");
	String provincia = resultSet.getString("provincia");
	String pais = resultSet.getString("pais");
	String Etiqueta = resultSet.getString("Etiqueta");
	int idMedioTransporte = resultSet.getInt("Medio_transporte");

	java.sql.Date nacimiento = parseNacimiento(nac);

	return new PersonaDTO(id, nombre, tel, email, dominioEmail, nacimiento, calle, altura, piso, depto, localidad,
		provincia, pais, Etiqueta, idMedioTransporte);
    }

//    private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException, ParseException {
//		int id = resultSet.getInt("idPersona");
//		String nombre = resultSet.getString("Nombre");
//		String tel = resultSet.getString("Telefono");
//		String email = resultSet.getString("Email");
//		String nac = resultSet.getString("Nacimiento");
//		int idDomicilio = resultSet.getInt("Domicilio");
//		int idTipoContacto = resultSet.getInt("Tipo_contacto");
//
//		java.sql.Date nacimiento = parseNacimiento(nac);
//	
//		return new PersonaDTO(id, nombre, tel, email, nacimiento, idDomicilio, idTipoContacto);
//    }

    /*
     * Esto no deberia estar aca otra vez pero bueno Arreglar porque no anda
     */
    private java.sql.Date parseNacimiento(String nacimiento) throws ParseException {
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	Date parsed = format.parse(nacimiento);
	java.sql.Date date = new java.sql.Date(parsed.getTime());

	return date;
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
		maxId = resultSet.getInt("idPersona");

	} catch (SQLException e) {
	    e.printStackTrace();

	}
	return maxId;
    }

}
