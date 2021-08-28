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
import presentacion.controlador.Controlador;
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
     * 
     * Lo hice static para poder probarlo
     */
    public static void wire(List<PersonaDTO> personas, List<PaisDTO> paises, List<ProvinciaDTO> provincias,
	    List<LocalidadDTO> localidades, List<TipoContactoDTO> tiposContacto, List<DomicilioDTO> domicilios) {

	for (PaisDTO pais : paises) {
	    for (ProvinciaDTO provincia : provincias) {
		if (provincia.getIdPais() == pais.getIdPais())
		    provincia.setPais(pais);

		for (LocalidadDTO localidad : localidades) {
		    if (localidad.getIdPais() == pais.getIdPais())
			localidad.setPais(pais);

		    if (localidad.getIdProvincia() == provincia.getIdProvincia())
			localidad.setProvincia(provincia);

		}
	    }
	}

	for (PersonaDTO persona : personas) {
	    for (TipoContactoDTO tipo : tiposContacto) {
		if (persona.getIdTipoContacto() == tipo.getIdTipoContacto())
		    persona.setTipoContacto(tipo);
	    }
	    for (DomicilioDTO domicilio : domicilios) {
		if (persona.getIdDomicilio() == domicilio.getIdDomicilio())
		    persona.setDomicilio(domicilio);
	    }
	}
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
	return this.persona.READALLDATE();
    }

    /*
     * Estos metodos se usan para cargar los comboboxes correspondientes sin tener
     * que hacer una consulta a la bdd cada vez que se cargue la ventana:
     * obtenerPaises(), obtenerProvincias(), obtenerLocalidades(),
     * obtenerTiposContacto() y obtenerDomicilios()
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

    public List<DomicilioDTO> obtenerDomicilios() {
	return this.domicilio.readAll();
    }

    public List<String> getNombrePaises(List<PaisDTO> paises) {
	List<String> ret = new ArrayList<String>();
	for (PaisDTO pais : paises)
	    ret.add(pais.getNombre());

	return ret;
    }

    public LocalidadDTO getLocalidadById(int id) {
	return this.localidad.readByID(id);
    }

    public List<ProvinciaDTO> getProvincias(int idpais) {
	List<ProvinciaDTO> ret = new ArrayList<ProvinciaDTO>();
	for (ProvinciaDTO provincia : Controlador.provincias) {
	    if (provincia.getIdPais() == idpais)
		ret.add(provincia);
	}
	return ret;
    }

    public List<LocalidadDTO> getLocalidades(String nombreProvincia) {
	List<LocalidadDTO> ret = new ArrayList<LocalidadDTO>();
	for (LocalidadDTO localidad : Controlador.localidades) {
	    if (localidad.getNombre().equals(nombreProvincia))
		ret.add(localidad);
	}
	return ret;
    }

    /*
     * Lo hice static para poder probarlo
     */
    public static List<String> getNombreTipoContacto(List<TipoContactoDTO> tiposContacto) {
	List<String> ret = new ArrayList<String>();
	for (TipoContactoDTO tipo : tiposContacto)
	    ret.add(tipo.getTipoContacto().name());
	return ret;
    }

    // TIPO CONTACTO

    public void agregarTipo(TipoContactoDTO nuevoTipo) {
	this.tipoContacto.insert(nuevoTipo);
    }

    public void editarTipo(TipoContactoDTO editTipo) {
	this.tipoContacto.update(editTipo);
    }

    public void borrarTipo(String borrarTipo) {
	this.tipoContacto.delete(borrarTipo);
    }

    public String[] AMB() {
	return new String[] { "Agregar", "Modificar", "Eliminar" };
    }

    public ArrayList<String> getNombrePaises() {
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

    // ****************************

    // Pasar al modelo
    public int getIdLocalidadByNombre(String nombre, List<LocalidadDTO> localidades) {
	for (LocalidadDTO localidad : localidades) {
	    if (localidad.getNombre().equals(nombre))
		return localidad.getIdLocalidad();
	}
	return 0;
    }

    public int getIdTipoContactoByNombre(String nombre) {
	return this.tipoContacto.readIdByNombre(nombre);
    }

    public void editarDomicilio(DomicilioDTO domicilioEditado) {
	this.domicilio.update(domicilioEditado);
    }

}
