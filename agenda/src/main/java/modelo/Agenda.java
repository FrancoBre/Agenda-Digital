package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.DomicilioDTO;
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
	private TipoContactoDAO tipoContacto;
	private PaisDAO pais;
	private ProvinciaDAO provincia;
	private LocalidadDAO localidad;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.domicilio = metodo_persistencia.createDomicilioDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
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
		return this.persona.READALLDATE();		
	}
	
	//TIPO CONTACTO
	
	public void agregarTipo(TipoContactoDTO nuevoTipo) {
		this.tipoContacto.insert(nuevoTipo);
	}
	
	public void editarTipo(TipoContactoDTO editTipo) {
		this.tipoContacto.update(editTipo);
	}
	
	public void borrarTipo(String borrarTipo) {		
		this.tipoContacto.delete(borrarTipo);
	}	
	
	public ArrayList<String> getNombreTipoContacto(){
		List<TipoContactoDTO> tipo = this.tipoContacto.readAll();
		ArrayList<String> a = new ArrayList<String>();
		for (TipoContactoDTO tipoContactoDTO: tipo) {
			a.add(tipoContactoDTO.getDescripcion());
		}
		return a;
	}
		
	// ****************************
	
	
	// ************** ABM PAIS **************

	public void agregarPais(String nuevoPais) {
		this.pais.insert(nuevoPais);
	}

	public void editarPais(PaisDTO editarPais) {
		this.pais.update(editarPais);
	}

	public void borrarPais(int idPais) {
		this.pais.delete(idPais);
	}
	
	public int getIdPaisByNombre(String nombrePais) {
		 return this.pais.getIdPaisByNombre(nombrePais);
	}
	// ****************************
	
	
	// ************** ABM PROVINCIA **************

	public void agregarProvincia(ProvinciaDTO nuevaProvincia) {
		this.provincia.insert(nuevaProvincia);
	}

	public void editarProvincia(ProvinciaDTO editarPais) {
		this.provincia.update(editarPais);
	}

	public void borrarProvincia(int idProvincia) {
		this.provincia.delete(idProvincia);
	}
	
	public int getIdProvinciaByNombre(String nombreProvincia) {
		 return this.provincia.getIdProvinciaByNombre(nombreProvincia);
	}
	// ****************************
	
	public ArrayList<String> getNombrePaises(){
		List<PaisDTO> paises = this.pais.readAll();
		ArrayList<String> a = new ArrayList<String>();
		for (PaisDTO paisDTO : paises) {
			a.add(paisDTO.getNombre().toString());
		}
		return a;
	}
	
	public void agregarDomicilio(DomicilioDTO domicilio) {
		this.domicilio.insert(domicilio);
	}
	
	public ArrayList<String> getNombreProvincia(int idpais){
		List<ProvinciaDTO> provincias = this.provincia.readByPais(idpais);
		ArrayList<String> a = new ArrayList<String>();
		for (ProvinciaDTO provinciaDTO : provincias) {
			a.add(provinciaDTO.getNombre().toString());
		}
		return a;
	}
	
	public ArrayList<String> getNombreLocalidad(String nombreProvincia){
		List<LocalidadDTO>localidad = this.localidad.readByNombreProvincia(nombreProvincia);
		ArrayList<String> a = new ArrayList<String>();
		for (LocalidadDTO localidadDTO: localidad) {
			a.add(localidadDTO.getNombre());
		}
		return a;
	}
	
	
	public ArrayList<Integer> getYears(){
		return Fecha.lastYears(100);
	}
	
	public int getPersonaMaxId() {
		return this.persona.readMaxId();
	}
	
	public int getDomicilioMaxId() {
		return this.domicilio.readMaxId();
	}

	public int getIdLocalidadByNombre(String nombre) {
		return this.localidad.readIdByNombre(nombre);
	}

	public int getIdTipoContactoByNombre(String nombre) {
		return this.tipoContacto.readIdByNombre(nombre);
	}

	public void editarDomicilio(DomicilioDTO domicilioEditado) {
		this.domicilio.update(domicilioEditado);
		
	}
	
}
