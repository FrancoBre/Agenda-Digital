package modelo;

import java.util.ArrayList;
import java.util.EnumMap;
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

public class Agenda {
    private PersonaDAO persona;
    private DomicilioDAO domicilio;
    private TipoContactoDAO tipoContacto;
    private PaisDAO pais;
    private ProvinciaDAO provincia;
    private LocalidadDAO localidad;

    public Agenda(DAOAbstractFactory metodo_persistencia) {
	this.persona = metodo_persistencia.createPersonaDAO();
	this.domicilio = metodo_persistencia.createDomicilioDAO();
	this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
	this.pais = metodo_persistencia.createPaisDAO();
	this.provincia = metodo_persistencia.createProvinciaDAO();
	this.localidad = metodo_persistencia.createLocalidadDAO();
    }

    /*
     * Este metodo se encarga de "cablear" las dependencias entre los objetos, segun
     * los id correspondientes
     */
    public void wire(List<PersonaDTO> personasEnTabla, List<PaisDTO> paises, List<ProvinciaDTO> provincias,
	    List<LocalidadDTO> localidades, List<TipoContactoDTO> tiposContacto) {
	// TODO Auto-generated method stub

    }
    
    public void agregarPersona(PersonaDTO nuevaPersona) {
	this.persona.insert(nuevaPersona);
    }

    public void editarPersona(PersonaDTO persona_a_editar) {
	this.persona.update(persona_a_editar);
    }

    public void borrarPersona(PersonaDTO persona_a_eliminar) {
	this.persona.delete(persona_a_eliminar);
    }

    /*
     * Este metodo se usa para cargar las personas a la ventana principal
     */
    public List<PersonaDTO> obtenerPersonas() {
	return this.persona.readAll();
    }

    /*
     * Estos metodos se usan para cargar los comboboxes correspondientes sin tener
     * que hacer una consulta a la bdd cada vez que se cargue la ventana:
     * obtenerPaises(), obtenerProvincias(), obtenerLocalidades() y
     * obtenerTiposContacto()
     */
    public List<PaisDTO> obtenerPaises() {
	return this.pais.readAll();
    }

    public List<ProvinciaDTO> obtenerProvincias() {
	return this.provincia.readAll();
    }

    public List<LocalidadDTO> obtenerLocalidades() {
	return this.localidad.readAll();
    }

    public List<TipoContactoDTO> obtenerTiposContacto() {
	return this.tipoContacto.readAll();
    }

    public List<String> getNombrePaises(List<PaisDTO> paises) {
	List<String> ret = new ArrayList<String>();
	for (PaisDTO pais : paises)
	    ret.add(pais.getNombre());

	return ret;
    }

    public void agregarDomicilio(DomicilioDTO domicilio) {
	this.domicilio.insert(domicilio);
    }

    // Cambiar
    public ArrayList<String> getNombreProvincia(int idpais) {
	List<ProvinciaDTO> provincias = this.provincia.readByPais(idpais);
	ArrayList<String> a = new ArrayList<String>();
	for (ProvinciaDTO provinciaDTO : provincias) {
	    a.add(provinciaDTO.getNombre());
	}
	return a;
    }

    // Cambiar
    public ArrayList<String> getNombreLocalidad(String nombreProvincia) {
	List<LocalidadDTO> localidad = this.localidad.readByNombreProvincia(nombreProvincia);
	ArrayList<String> a = new ArrayList<String>();
	for (LocalidadDTO localidadDTO : localidad) {
	    a.add(localidadDTO.getNombre());
	}
<<<<<<< HEAD
	return a;
    }

    /*
     * Lo hice static para poder probarlo
     */
    public static List<String> getNombreTipoContacto(List<TipoContactoDTO> tiposContacto) {
	List<String> ret = new ArrayList<String>();
	for (TipoContactoDTO tipo : tiposContacto) {
	    ret.add(tipo.getTipoContacto().name());
=======
	
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
	
	
	public String[] AMB(){
		return new String[]{"Agregar", "Modificar", "Eliminar"};
	}
		
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
			a.add(provinciaDTO.getNombre());
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
	
	public ArrayList<String> getNombreTipoContacto(){
		List<TipoContactoDTO> tipo = this.tipoContacto.readAll();
		ArrayList<String> a = new ArrayList<String>();
		for (TipoContactoDTO tipoContactoDTO: tipo) {
			a.add(tipoContactoDTO.getDescripcion());
		}
		return a;
	}
	
	
	// ****************************
	public ArrayList<Integer> getYears(){
		return Fecha.lastYears(100);
	}
	
	public int getPersonaMaxId() {
		return this.persona.readMaxId();
	}
	
	public int getDomicilioMaxId() {
		return this.domicilio.readMaxId();
>>>>>>> branch 'master' of https://github.com/FrancoBre/Agenda-Digital
	}

	return ret;
    }

    // ****************************
    public ArrayList<Integer> getYears() {
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

    /*
     * public int getIdTipoContactoByNombre(String nombre) { return
     * this.tipoContacto.readIdByNombre(nombre); }
     */

    public void editarDomicilio(DomicilioDTO domicilioEditado) {
	this.domicilio.update(domicilioEditado);
    }

}
