package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*import persistencia.conexion.Conexion;*/
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DomicilioDTO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{

	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, email, nacimiento, domicilio, tipo_contacto) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "UPDATE personas SET nombre = ? , telefono = ? , email = ? , nacimiento = ? , domicilio = ?, tipo_contacto = ? WHERE idPersona = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String readMaxId = "SELECT idPersona FROM personas ORDER BY idPersona DESC LIMIT 0, 1;";
		
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setObject(5, persona.getNacimiento());
			statement.setInt(6, persona.getDomicilio());
			statement.setInt(7, persona.getTipoContacto());

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
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
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona_a_editar.getNombre());
			statement.setString(2, persona_a_editar.getTelefono());
			statement.setString(3, persona_a_editar.getEmail());
			statement.setObject(4, persona_a_editar.getNacimiento());
			statement.setInt(5, persona_a_editar.getDomicilio());
			statement.setInt(6, persona_a_editar.getTipoContacto());

			statement.setInt(6, persona_a_editar.getIdPersona());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isUpdateExitoso;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		String email = resultSet.getString("Email");
		String nac = resultSet.getString("Nacimiento");
		int domicilio = resultSet.getInt("Domicilio");
		int tipoContacto = resultSet.getInt("Tipo_contacto");
		
		LocalDate nacimiento = parseNacimiento(nac);
		
		return new PersonaDTO(id, nombre, tel, email, nacimiento, domicilio, tipoContacto);
	}

	// Esto no deberia estar aca otra vez pero bueno
	private LocalDate parseNacimiento(String nacimiento) {
		DateTimeFormatter dateFormatter = 
		        new DateTimeFormatterBuilder()
		            .parseCaseInsensitive()
		            .appendPattern("dd MM uuuu")
		            .toFormatter(Locale.ENGLISH);
		
		LocalDate date = LocalDate.parse(nacimiento, dateFormatter);
		
		return date;
	}
	
	public int readMaxId() {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		int maxId = 0;
		try
		{
			statement = conexion.prepareStatement(readMaxId);
			resultSet = statement.executeQuery();
			if(resultSet.next())
				maxId = resultSet.getInt("idPersona");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		return maxId;
	}

}
