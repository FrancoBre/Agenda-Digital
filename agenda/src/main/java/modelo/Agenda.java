package modelo;

import java.util.List;

import dto.PaisDTO;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.PaisDAO;


public class Agenda 
{
	private PersonaDAO persona;
	private LocalidadDAO localidad;
	private PaisDAO paisDAO;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}
	
	public void editarPersona(PersonaDTO persona_a_editar)
	{
		this.persona.update(persona_a_editar);
	}
	
	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}
	
	public String getNombreProvincia(int idLocalidad) {
		return this.localidad.getNombreProvincia(idLocalidad);
	}
	
	public List<PaisDTO> getPaises(){
		return this.paisDAO.readAll();
	}
	
	
}
