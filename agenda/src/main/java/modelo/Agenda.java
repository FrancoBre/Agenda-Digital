package modelo;

import java.util.ArrayList;
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
		this.paisDAO = metodo_persistencia.createPaisDAO();
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
	
	public ArrayList<String> getNombrePaises(){
		List<PaisDTO> paises = this.paisDAO.readAll();
		ArrayList<String> a = new ArrayList<String>();
		for (PaisDTO paisDTO : paises) {
			a.add(paisDTO.getNombre().toString());
		}
		return a;
	}
	
	
}
