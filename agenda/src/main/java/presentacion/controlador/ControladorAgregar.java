package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import modelo.*;
import presentacion.vista.VentanaPersonaAgregar;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class ControladorAgregar implements ActionListener {
    private Vista vista;
    private List<PersonaDTO> personasEnTabla;
    private VentanaPersonaAgregar ventanaPersonaAgregar;
    private Agenda agenda;

    public ControladorAgregar(Vista vista, Agenda agenda) {
	this.vista = vista;
	this.agenda = agenda;

	this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));

	this.ventanaPersonaAgregar = VentanaPersonaAgregar.getInstance();

	this.ventanaPersonaAgregar.getBtnAction().addActionListener(p -> {
	    try {
		guardarPersona(p);
	    } catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	});
	this.ventanaPersonaAgregar.getComboBoxMes().addActionListener(e -> agregarDias(e));
	this.ventanaPersonaAgregar.getPaisInput().addActionListener(combobox -> cambioItemsProvincia(combobox));
	this.ventanaPersonaAgregar.getProvinciaInput()
		.addActionListener(comboLocalidad -> cambioItemsLocalidad(comboLocalidad));
	addComboboxItems();
    }

    public void inicializar() {
	this.refrescarTabla();
	this.vista.show();
    }

    private void addComboboxItems() {
	// addPaisesItems(agenda.getNombrePaises()); ¿donde es conveniente que esten los
	// datos de los paises???
	addPaisesItems(Agenda.paises);
	addTipoContacto(Agenda.tiposContacto);
	addIntegerComboBox(agenda.getYears(), Fecha.getMonths());
	llenarDias(31);
    }

    private void ventanaAgregarPersona(ActionEvent a) {
	this.ventanaPersonaAgregar.mostrarVentana();
    }

    private void guardarPersona(ActionEvent p) throws ParseException {
	if (!ventanaPersonaAgregar.validarRequeridos()) {
	    // crear una ventana con mensaje de campos vacios
	} else {
	    String nombre = this.ventanaPersonaAgregar.getNombreInput().getText();
	    String tel = this.ventanaPersonaAgregar.getTelefonoInput().getText();
	    String email = this.ventanaPersonaAgregar.getEmailInput().getText();
	    String tipoContacto = (String) this.ventanaPersonaAgregar.getTipoContactoInput().getSelectedItem();

	    String calle = this.ventanaPersonaAgregar.getCalleInput().getText();
	    String altura = this.ventanaPersonaAgregar.getAlturaInput().getText();
	    String piso = this.ventanaPersonaAgregar.getPisoInput().getText();
	    String depto = this.ventanaPersonaAgregar.getDeptoInput().getText();
	    String localidad = (String) this.ventanaPersonaAgregar.getLocalidadInput().getSelectedItem();

	    int year = (int) this.ventanaPersonaAgregar.getComboBoxAnio().getSelectedItem();
	    int month = (int) this.ventanaPersonaAgregar.getComboBoxMes().getSelectedItem();
	    int date = (int) this.ventanaPersonaAgregar.getComboBoxDia().getSelectedItem();
	    String nacimiento = year + " " + month + " " + date;

	    int idPersona = this.agenda.getPersonaMaxId() + 1;
	    int idDomicilio = this.agenda.getDomicilioMaxId() + 1;
	    
	    DomicilioDTO domicilio = new DomicilioDTO(idDomicilio, calle, altura, piso, depto,
		    this.agenda.getLocalidad(localidad));

	    PersonaDTO persona = new PersonaDTO(idPersona, nombre, tel, email, Fecha.parseNacimiento(nacimiento),
		    domicilio, this.agenda.getTipoContacto(tipoContacto));

	    this.agenda.agregarDomicilio(domicilio);

	    this.agenda.agregarPersona(persona);

	    // ############################################################################
	    /*
	     * String pais = (String)
	     * this.ventanaPersonaAgregar.getPaisInput().getSelectedItem(); String provincia
	     * = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
	     * 
	     * // Estos datos tendrían que venir de la eleccion del usuario en la vista, y
	     * // corresponden al id de cada entidad int idLocalidad =
	     * this.agenda.getIdLocalidadByNombre(localidad); int idTipoContacto =
	     * this.agenda.getIdTipoContactoByNombre(tipoContacto);
	     * 
	     * int idDomicilio = this.agenda.getDomicilioMaxId() + 1;
	     * 
	     * // DomicilioDTO nuevoDomicilio = new DomicilioDTO(idDomicilio, calle, altura,
	     * piso, // depto, idLocalidad);
	     * 
	     * PersonaDTO nuevaPersona;
	     * 
	     * nuevaPersona = null;
	     * 
	     * try { nuevaPersona = new PersonaDTO(this.agenda.getPersonaMaxId() + 1,
	     * nombre, tel, email, Fecha.parseNacimiento(nacimiento), idDomicilio,
	     * idTipoContacto); } catch (ParseException e) { e.printStackTrace(); }
	     * 
	     * // this.agenda.agregarDomicilio(nuevoDomicilio);
	     * 
	     * this.agenda.agregarPersona(nuevaPersona);
	     * 
	     * //
	     * ############################################################################
	     */
	}
	this.refrescarTabla();
	this.ventanaPersonaAgregar.cerrar();
    }

    private void refrescarTabla() {
	this.personasEnTabla = agenda.obtenerPersonas();
	this.vista.llenarTabla(this.personasEnTabla);
    }

    private void agregarDias(ActionEvent e) {
	this.ventanaPersonaAgregar.getComboBoxDia().removeAllItems();
	int m = this.ventanaPersonaAgregar.getComboBoxMes()
		.getItemAt(this.ventanaPersonaAgregar.getComboBoxMes().getSelectedIndex());
	int y = this.ventanaPersonaAgregar.getComboBoxAnio()
		.getItemAt(this.ventanaPersonaAgregar.getComboBoxAnio().getSelectedIndex());

	int dias = Fecha.numeroDeDiasMes(m, y);
	llenarDias(dias);
    }

    private void llenarDias(int limite) {
	for (int i = 1; i <= limite; i++) {
	    this.ventanaPersonaAgregar.getComboBoxDia().addItem(i);
	}
    }

    public void addIntegerComboBox(ArrayList<Integer> y, Integer[] m) {
	for (Integer integer : y) {
	    this.ventanaPersonaAgregar.getComboBoxAnio().addItem(integer);
	}
	this.ventanaPersonaAgregar.getComboBoxMes().setModel(new DefaultComboBoxModel<Integer>(m));
    }

    private void cambioItemsProvincia(ActionEvent c) {
	this.ventanaPersonaAgregar.getProvinciaInput().removeAllItems();

	int idPais = this.ventanaPersonaAgregar.getPaisInput().getSelectedIndex() + 1;
	addProvinciasItem(agenda.getProvincias(idPais));
    }

    private void cambioItemsLocalidad(ActionEvent c) {
	this.ventanaPersonaAgregar.getLocalidadInput().removeAllItems();
	String nombreProvincia = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
	
	addLocalidadItems(agenda.getLocalidades(nombreProvincia));
    }

    public void addTipoContacto(List<TipoContactoDTO> tipos) {
	for (TipoContactoDTO tipo : tipos) {
	    this.ventanaPersonaAgregar.getTipoContactoInput().addItem(tipo.getDescripcion());
	}
    }

    public void addPaisesItems(List<PaisDTO> paises) {
	for (PaisDTO pais : paises) {
	    this.ventanaPersonaAgregar.getPaisInput().addItem(pais.getNombre());
	}
    }

    public void addProvinciasItem(List<ProvinciaDTO> provincias) {
	for (ProvinciaDTO provincia : provincias) {
	    this.ventanaPersonaAgregar.getProvinciaInput().addItem(provincia.getNombre());
	}
    }

    public void addLocalidadItems(List<LocalidadDTO> localidades) {
	for (LocalidadDTO localidad : localidades) {
	    this.ventanaPersonaAgregar.getLocalidadInput().addItem(localidad.getNombre());
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
