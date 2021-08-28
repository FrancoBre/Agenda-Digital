package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import modelo.*;
import presentacion.vista.VentanaPersonaEditar;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class ControladorEditar implements ActionListener {
    private Vista vista;
    private List<PersonaDTO> personasEnTabla;
    private VentanaPersonaEditar ventanaPersonaEditar;
    private Agenda agenda;
    private int[] filasSeleccionadas = null;

    public ControladorEditar(Vista vista, Agenda agenda) {
	this.vista = vista;
	this.agenda = agenda;

	this.vista.getBtnEditar().addActionListener(a -> ventanaEditarPersona(a));

	this.ventanaPersonaEditar = VentanaPersonaEditar.getInstance();

	this.ventanaPersonaEditar.getBtnAction().addActionListener(ep -> {
	    try {
		editarPersona(ep);
	    } catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	});
	this.ventanaPersonaEditar.getComboBoxMes().addActionListener(e -> agregarDias(e));
	this.ventanaPersonaEditar.getPaisInput().addActionListener(combobox -> cambioItemsProvincia(combobox));
	this.ventanaPersonaEditar.getProvinciaInput()
		.addActionListener(comboLocalidad -> cambioItemsLocalidad(comboLocalidad));

	addComboboxItems();
    }

    public void inicializar() {
	this.refrescarTabla();
	this.vista.show();
    }

    private void ventanaEditarPersona(ActionEvent e) {
	if (this.vista.getTablaPersonas().getSelectedRows().length == 1) {
	    filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
	    this.ventanaPersonaEditar.setCampos(this.personasEnTabla.get(filasSeleccionadas[0]));
	    this.ventanaPersonaEditar.mostrarVentana();
	}
    }

    private void addComboboxItems() {
	addPaisesItems();
	addTipoContacto();
	addIntegerComboBox(agenda.getYears(), Fecha.getMonths());
	llenarDias(31);
    }

    private void editarPersona(ActionEvent ep) throws ParseException {
	if (!ventanaPersonaEditar.validarRequeridos()) {
	    // crear una ventana con mensaje de campos vacios
	} else {
	    int idPersona = this.personasEnTabla.get(filasSeleccionadas[0]).getIdPersona();
	    int idDomicilio = this.personasEnTabla.get(filasSeleccionadas[0]).getIdDomicilio();

	    String nombre = this.ventanaPersonaEditar.getNombreInput().getText();
	    String tel = this.ventanaPersonaEditar.getTelefonoInput().getText();
	    String email = this.ventanaPersonaEditar.getEmailInput().getText();
	    String tipoContacto = (String) this.ventanaPersonaEditar.getTipoContactoInput().getSelectedItem();

	    String calle = this.ventanaPersonaEditar.getCalleInput().getText();
	    String altura = this.ventanaPersonaEditar.getAlturaInput().getText();
	    String pisoStr = this.ventanaPersonaEditar.getPisoInput().getText();
	    String piso = pisoStr.trim();
	    String deptoStr = this.ventanaPersonaEditar.getDeptoInput().getText();
	    String depto = deptoStr.trim();
	    String localidad = (String) this.ventanaPersonaEditar.getLocalidadInput().getSelectedItem();

	    int year = (int) this.ventanaPersonaEditar.getComboBoxAnio().getSelectedItem();
	    int month = (int) this.ventanaPersonaEditar.getComboBoxMes().getSelectedItem();
	    int date = (int) this.ventanaPersonaEditar.getComboBoxDia().getSelectedItem();
	    String nacimiento = year + " " + month + " " + date;
	    
	    DomicilioDTO domicilio = new DomicilioDTO(idDomicilio, calle, altura, piso, depto, this.agenda.getLocalidad(localidad).getIdLocalidad());
	    PersonaDTO persona = new PersonaDTO(idPersona, nombre, tel, email, Fecha.parseNacimiento(nacimiento), 
		    idDomicilio, this.agenda.getTipoContacto(tipoContacto).getIdTipo());
	    
/*
	    // unused
	    String pais = (String) this.ventanaPersonaEditar.getPaisInput().getSelectedItem();
	    String provincia = (String) this.ventanaPersonaEditar.getProvinciaInput().getSelectedItem();

	    // Estos datos tendrían que venir de la eleccion del usuario en la vista, y
	    // corresponden al id de cada entidad
	    int idLocalidad = this.agenda.getIdLocalidadByNombre(localidad);
	    int idTipoContacto = this.agenda.getIdTipoContactoByNombre(tipoContacto);

	    LocalidadDTO local = this.agenda.getLocalidadById(idLocalidad);

	    DomicilioDTO domicilioEditado = new DomicilioDTO(idDomicilio, calle, altura, piso, depto, local);

	    this.agenda.editarDomicilio(domicilioEditado);

	    PersonaDTO personaEditada;

	    personaEditada = null;
	    System.out.println("Guasu guasol");
	    try {
		personaEditada = new PersonaDTO(idPersona, nombre, tel, email, Fecha.parseNacimiento(nacimiento),
			idDomicilio, idTipoContacto);

	    } catch (ParseException e) {
		e.printStackTrace();
	    }
*/
	    this.agenda.editarDomicilio(domicilio);

	    this.agenda.editarPersona(persona);
	}
	this.refrescarTabla();
	this.ventanaPersonaEditar.cerrar();
    }

    private void refrescarTabla() {
	this.personasEnTabla = agenda.obtenerPersonas();
	this.vista.llenarTabla(this.personasEnTabla);
    }

    private void agregarDias(ActionEvent e) {
	this.ventanaPersonaEditar.getComboBoxDia().removeAllItems();
	int m = this.ventanaPersonaEditar.getComboBoxMes()
		.getItemAt(this.ventanaPersonaEditar.getComboBoxMes().getSelectedIndex());
	int y = this.ventanaPersonaEditar.getComboBoxAnio()
		.getItemAt(this.ventanaPersonaEditar.getComboBoxAnio().getSelectedIndex());

	int dias = Fecha.numeroDeDiasMes(m, y);
	llenarDias(dias);
    }

    private void llenarDias(int limite) {
	for (int i = 1; i <= limite; i++) {
	    this.ventanaPersonaEditar.getComboBoxDia().addItem(i);
	}
    }

    public void addIntegerComboBox(ArrayList<Integer> y, Integer[] m) {
	for (Integer integer : y) {
	    this.ventanaPersonaEditar.getComboBoxAnio().addItem(integer);
	}
	this.ventanaPersonaEditar.getComboBoxMes().setModel(new DefaultComboBoxModel<Integer>(m));
    }

    private void cambioItemsProvincia(ActionEvent c) {
	this.ventanaPersonaEditar.getProvinciaInput().removeAllItems();
	int idPais = this.ventanaPersonaEditar.getPaisInput().getSelectedIndex() + 1;
	addProvinciasItem(agenda.getProvincias(idPais));
    }

    private void cambioItemsLocalidad(ActionEvent c) {
	this.ventanaPersonaEditar.getLocalidadInput().removeAllItems();
	String nombreProvincia = (String) this.ventanaPersonaEditar.getProvinciaInput().getSelectedItem();
	addLocalidadItems(agenda.getLocalidades(nombreProvincia));
    }

    public void addTipoContacto() {
	for (TipoContactoDTO tipo : Agenda.tiposContacto) {
	    this.ventanaPersonaEditar.getTipoContactoInput().addItem(tipo.getDescripcion());
	}
    }

    public void addPaisesItems() {
	for (PaisDTO pais : Agenda.paises) {
	    this.ventanaPersonaEditar.getPaisInput().addItem(pais.getNombre());
	}
    }
    
    /*
     * public void addProvinciasItem() { for (ProvinciaDTO provincia :
     * Controlador.provincias) {
     * this.ventanaPersonaEditar.getProvinciaInput().addItem(provincia.getNombre());
     * } }
     */

    public void addProvinciasItem(List<ProvinciaDTO> provincias) {
	for (ProvinciaDTO provincia : provincias) {
	    this.ventanaPersonaEditar.getProvinciaInput().addItem(provincia.getNombre());
	}
    }

    public void addLocalidadItems(List<LocalidadDTO> localidades) {
	for (LocalidadDTO localidad : localidades) {
	    this.ventanaPersonaEditar.getLocalidadInput().addItem(localidad.getNombre());
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
