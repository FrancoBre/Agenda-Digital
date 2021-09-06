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
import dto.PersonaDTO;

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
		this.ventanaPersonaAgregar.getBtnAction().addActionListener(p -> guardarPersona(p));
		this.ventanaPersonaAgregar.getComboBoxMes().addActionListener(e -> agregarDias(e));
		this.ventanaPersonaAgregar.getPaisInput().addActionListener(combobox -> cambioItemsProvincia(combobox));
		this.ventanaPersonaAgregar.getProvinciaInput()
		.addActionListener(comboLocalidad -> cambioItemsLocalidad(comboLocalidad));
		addComboboxItems();
    }

    public void inicializar() {
    	this.refrescarTabla();
//    	this.vista.show();
    }

    private void addComboboxItems() {
		addPaisesItems(agenda.getNombrePaises());
		addTipoContacto(agenda.getNombreTipoContacto());
		addMedioTransporteItems(agenda.getNombreMedioTransporte());
		addIntegerComboBox(agenda.getYears(), Fecha.getMonths());
		llenarDias(31);
    }

    private void ventanaAgregarPersona(ActionEvent a) {
    	this.ventanaPersonaAgregar.mostrarVentana();
    }

    private void guardarPersona(ActionEvent p) {
		if (!ventanaPersonaAgregar.validarRequeridos()) {
		    // crear una ventana con mensaje de campos vacios
		} else {
		    String nombre = this.ventanaPersonaAgregar.getNombreInput().getText();
		    String tel = this.ventanaPersonaAgregar.getTelefonoInput().getText();
	
		    StringBuilder emailSB = new StringBuilder();
		    emailSB.append(this.ventanaPersonaAgregar.getLeftEmailInput().getText());
		    emailSB.append("@");
		    emailSB.append(this.ventanaPersonaAgregar.getRightEmailInput().getText());
		    emailSB.append(".");
		    emailSB.append(this.ventanaPersonaAgregar.getDomainEmailInput().getText());
		    String email = emailSB.toString();
	
		    String dominioEmail = this.ventanaPersonaAgregar.getRightEmailInput().getText();
	
		    String tipoContacto = (String) this.ventanaPersonaAgregar.getTipoContactoInput().getSelectedItem();
		    String medioTransporte = (String) this.ventanaPersonaAgregar.getMedioTransporteInput().getSelectedItem();
	
		    String calle = this.ventanaPersonaAgregar.getCalleInput().getText();
		    String altura = this.ventanaPersonaAgregar.getAlturaInput().getText();
		    String pisoStr = this.ventanaPersonaAgregar.getPisoInput().getText();
		    String piso = pisoStr.trim();
		    String deptoStr = this.ventanaPersonaAgregar.getDeptoInput().getText();
		    String depto = deptoStr.trim();
		    String localidad = (String) this.ventanaPersonaAgregar.getLocalidadInput().getSelectedItem();
	
		    int year = (int) this.ventanaPersonaAgregar.getComboBoxAnio().getSelectedItem();
		    int month = (int) this.ventanaPersonaAgregar.getComboBoxMes().getSelectedItem();
		    int date = (int) this.ventanaPersonaAgregar.getComboBoxDia().getSelectedItem();
		    String nacimiento = year + " " + month + " " + date;
	
		    // unused
		    String pais = (String) this.ventanaPersonaAgregar.getPaisInput().getSelectedItem();
		    String provincia = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
	
		    // Estos datos tendrían que venir de la eleccion del usuario en la vista, y
		    // corresponden al id de cada entidad
		    int idLocalidad = this.agenda.getIdLocalidadByNombre(localidad);
		    int idTipoContacto = this.agenda.getIdTipoContactoByNombre(tipoContacto);
		    int idMedioTransporte = this.agenda.getIdMedioTransporteByNombre(medioTransporte);
	
		    int idDomicilio = this.agenda.getDomicilioMaxId() + 1;
	
		    DomicilioDTO nuevoDomicilio = new DomicilioDTO(idDomicilio, calle, altura, piso, depto, idLocalidad);
	
		    PersonaDTO nuevaPersona;
	
		    nuevaPersona = null;
	
		    try {
			nuevaPersona = new PersonaDTO(0, nombre, tel, email, dominioEmail, Fecha.parseNacimiento(nacimiento),
				idDomicilio, idTipoContacto, idMedioTransporte);
		    } catch (ParseException e) {
			e.printStackTrace();
		    }
	
		    this.agenda.agregarDomicilio(nuevoDomicilio);
	
		    this.agenda.agregarPersona(nuevaPersona);
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
	int id = this.ventanaPersonaAgregar.getPaisInput().getSelectedIndex() + 1;
	addProvinciasItem(agenda.getNombreProvincia(id));
    }

    private void cambioItemsLocalidad(ActionEvent c) {
	this.ventanaPersonaAgregar.getLocalidadInput().removeAllItems();
	String nombreProvincia = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
	addLocalidadItems(agenda.getNombreLocalidad(nombreProvincia));
    }

    public void addTipoContacto(List<String> list) {
	for (String string : list) {
	    this.ventanaPersonaAgregar.getTipoContactoInput().addItem(string);
	}
    }

    public void addPaisesItems(ArrayList<String> items) {
	for (String string : items) {
	    this.ventanaPersonaAgregar.getPaisInput().addItem(string);
	}
    }

    public void addMedioTransporteItems(List<String> medios) {
	for (String medio : medios) {
	    this.ventanaPersonaAgregar.getMedioTransporteInput().addItem(medio);
	}
    }

    public void addProvinciasItem(ArrayList<String> items) {
	for (String string : items) {
	    this.ventanaPersonaAgregar.getProvinciaInput().addItem(string);
	}
    }

    public void addLocalidadItems(ArrayList<String> items) {
	for (String string : items) {
	    this.ventanaPersonaAgregar.getLocalidadInput().addItem(string);
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
