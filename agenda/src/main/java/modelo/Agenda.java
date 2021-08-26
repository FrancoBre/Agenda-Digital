package modelo;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContacto;
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

    public EnumMap<TipoContacto, Integer> obtenerTiposContacto() {
	return this.tipoContacto.readAll();
    }

    /*
     * public ArrayList<String> getNombrePaises() { List<PaisDTO> paises =
     * this.pais.readAll(); ArrayList<String> a = new ArrayList<String>(); for
     * (PaisDTO paisDTO : paises) { a.add(paisDTO.getNombre().toString()); } return
     * a; }
     */

    public List<String> getNombrePaises(List<PaisDTO> paises) {
	List<String> ret = new ArrayList<String>();
	for (PaisDTO pais : paises)
	    ret.add(pais.getNombre());

	return ret;
    }

    public void agregarDomicilio(DomicilioDTO domicilio) {
	this.domicilio.insert(domicilio);
    }

    public ArrayList<String> getNombreProvincia(int idpais) {
	List<ProvinciaDTO> provincias = this.provincia.readByPais(idpais);
	ArrayList<String> a = new ArrayList<String>();
	for (ProvinciaDTO provinciaDTO : provincias) {
	    a.add(provinciaDTO.getNombre());
	}
	return a;
    }

    public ArrayList<String> getNombreLocalidad(String nombreProvincia) {
	List<LocalidadDTO> localidad = this.localidad.readByNombreProvincia(nombreProvincia);
	ArrayList<String> a = new ArrayList<String>();
	for (LocalidadDTO localidadDTO : localidad) {
	    a.add(localidadDTO.getNombre());
	}
	return a;
    }

    /*
     * Lo hice static para poder probarlo
     */
    public static List<String> getNombreTipoContacto(EnumMap<TipoContacto, Integer> tiposContacto) {
	List<String> ret = new ArrayList<String>();
	for (TipoContacto tipo : tiposContacto.keySet()) {
	    ret.add(tipo.toString());
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
