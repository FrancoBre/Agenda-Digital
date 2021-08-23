package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.interfaz.PaisDAO;


public class Agenda 
{
	private PersonaDAO persona;
	private DomicilioDAO domicilio;
	private TipoContactoDAO tipo_contacto;
	private PaisDAO pais;
	private ProvinciaDAO provincia;
	private LocalidadDAO localidad;
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipo_contacto = metodo_persistencia.createTipoContactoDAO();
		this.pais = metodo_persistencia.createPaisDAO();
		this.provincia = metodo_persistencia.createProvinciaDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
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
		List<PaisDTO> paises = this.pais.readAll();
		ArrayList<String> a = new ArrayList<String>();
		for (PaisDTO paisDTO : paises) {
			a.add(paisDTO.getNombre().toString());
		}
		return a;
	}
	
	public ArrayList<String> getNombreProvincia(){
		List<ProvinciaDTO> provincias = this.provincia.readByPais(0);
		ArrayList<String> a = new ArrayList<String>();
		for (ProvinciaDTO provinciaDTO : provincias) {
			a.add(provinciaDTO.getNombre().toString());
		}
		return a;
	}
	
	public ArrayList<String> getNombreLocalidad(){
		List<LocalidadDTO>localidad = this.localidad.readByProvincia(0);
		ArrayList<String> a = new ArrayList<String>();
		for (LocalidadDTO localidadDTO: localidad) {
			a.add(localidadDTO.getNombre().toString());
		}
		return a;
	}
	
	public ArrayList<String> getNombreTipoContacto(){
		List<TipoContactoDTO> tipos = this.tipo_contacto.readAll();
		ArrayList<String> a = new ArrayList<String>();
		for (TipoContactoDTO tipoContactoDTO: tipos) {
			a.add(tipoContactoDTO.name().toString());
		}
		return a;
	}
	
	public int getPersonaMaxId() {
		return this.persona.readMaxId();
	}
	
	public int getDomicilioMaxId() {
		return this.domicilio.readMaxId();
	}
	
}
