package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersonaAgregar;
import presentacion.vista.VentanaPersonaEditar;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private VentanaPersonaAgregar ventanaPersonaAgregar;
		private VentanaPersonaEditar ventanaPersonaEditar;
		private Agenda agenda;
		private int[] filasSeleccionadas = null;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.agenda = agenda;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnEditar().addActionListener(e->ventanaEditarPersona(e)); //*
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));			
			this.ventanaPersonaAgregar = VentanaPersonaAgregar.getInstance();
			this.ventanaPersonaAgregar.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersonaEditar = VentanaPersonaEditar.getInstance();
			this.ventanaPersonaEditar.getBtnEditarPersona().addActionListener(ep->editarPersona(ep));
			this.ventanaPersonaAgregar.getPaisInput().addActionListener(combobox->cambioItemsP(combobox));
			this.ventanaPersonaAgregar.getProvinciaInput().addActionListener(comboLocalidad -> cambioItemsL(comboLocalidad));
			this.ventanaPersonaAgregar.getProvinciaInput().addActionListener(comboLocalidad -> cambioItemsL(comboLocalidad));
			
			addComboboxItems();
		}

		private void cambioItemsP(ActionEvent c) {
			this.ventanaPersonaAgregar.getProvinciaInput().removeAllItems();
			int id = this.ventanaPersonaAgregar.getPaisInput().getSelectedIndex() + 1;
			addProvinciasItem(agenda.getNombreProvincia(id));
		}
		
		private void cambioItemsL(ActionEvent c) {
			this.ventanaPersonaAgregar.getLocalidadInput().removeAllItems();
			String nombreProvincia = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
			addLocalidadItems(agenda.getNombreLocalidad(nombreProvincia));
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersonaAgregar.mostrarVentana();
			
		}
				
		private void guardarPersona(ActionEvent p) {
			if (!ventanaPersonaAgregar.validarRequeridos()) {
				//crear una ventana con mensaje de campos vacios
			}else {
				String nombre = this.ventanaPersonaAgregar.getNombreInput().getText();
				String tel = this.ventanaPersonaAgregar.getTelefonoInput().getText();
				String email = this.ventanaPersonaAgregar.getEmailInput().getText();
				String nacimiento = this.ventanaPersonaAgregar.getNacimientoInput().getText();
				String tipoContacto = (String) this.ventanaPersonaAgregar.getTipoContactoInput().getSelectedItem();
				
				String calle = this.ventanaPersonaAgregar.getCalleInput().getText();
				String altura = this.ventanaPersonaAgregar.getAlturaInput().getText();
				String pisoStr = this.ventanaPersonaAgregar.getPisoInput().getText();
				int piso = Integer.parseInt(pisoStr.trim());
				String deptoStr = this.ventanaPersonaAgregar.getDeptoInput().getText();
				int depto = Integer.parseInt(deptoStr.trim());
				String localidad = (String) this.ventanaPersonaAgregar.getLocalidadInput().getSelectedItem();
				
				//unused
				String pais = (String) this.ventanaPersonaAgregar.getPaisInput().getSelectedItem();
				String provincia = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
				
				//Estos datos tendrían que venir de la eleccion del usuario en la vista, y corresponden al id de cada entidad
				int idLocalidad = this.agenda.getIdLocalidadByNombre(localidad);
				int idTipoContacto = 0;
				
				int idDomicilio = this.agenda.getDomicilioMaxId() + 1;
				
				DomicilioDTO nuevoDomicilio = new DomicilioDTO(idDomicilio, calle, altura, piso,
						depto, idLocalidad);

				PersonaDTO nuevaPersona = new PersonaDTO(this.agenda.getPersonaMaxId() + 1, nombre, tel, email, parseNacimiento(nacimiento),
						idDomicilio, idTipoContacto);

				this.agenda.agregarDomicilio(nuevoDomicilio);
				
				this.agenda.agregarPersona(nuevaPersona);
				
			}
			this.refrescarTabla();
			this.ventanaPersonaAgregar.cerrar();
		}
		
		public LocalDate parseNacimiento(String nacimiento) {
			DateTimeFormatter dateFormatter = 
			        new DateTimeFormatterBuilder()
			            .parseCaseInsensitive()
			            .appendPattern("dd MM uuuu")
			            .toFormatter(Locale.ENGLISH);
			
			LocalDate date = LocalDate.parse(nacimiento, dateFormatter);
			
			return date;
		}

		private void ventanaEditarPersona(ActionEvent e) {
			if(this.vista.getTablaPersonas().getSelectedRows().length == 1) {
				filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				this.ventanaPersonaEditar.setCampos(this.personasEnTabla.get(filasSeleccionadas[0]));
				this.ventanaPersonaEditar.mostrarVentana();
			}
		}
		
		private void editarPersona(ActionEvent ep) {	
			if (!ventanaPersonaAgregar.validarRequeridos()) {
				//crear una ventana con mensaje de campos vacios
			}else {
				int id = this.personasEnTabla.get(filasSeleccionadas[0]).getIdPersona();
				String nombre = this.ventanaPersonaEditar.getNombreInput().getText();
				String tel = ventanaPersonaEditar.getTelefonoInput().getText();
				PersonaDTO personaEditada = new PersonaDTO(id, nombre, tel, null, null, id, id);
				this.agenda.editarPersona(personaEditada);
			}
			this.refrescarTabla();
			this.ventanaPersonaEditar.cerrar();
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}
		
		private void addComboboxItems() {
			addPaisesItems(agenda.getNombrePaises());
		}
			
		public void addPaisesItems(ArrayList<String> items) {
			for (String string : items) {
				this.ventanaPersonaAgregar.getPaisInput().addItem(string);
				this.ventanaPersonaEditar.getPaisInput().addItem(string);
			}
		}
		
		public void addProvinciasItem(ArrayList<String> items) {
			for (String string : items) {
				this.ventanaPersonaAgregar.getProvinciaInput().addItem(string);
				this.ventanaPersonaEditar.getProvinciaInput().addItem(string);
			}
		}
		
		public void addLocalidadItems(ArrayList<String> items) {
			for (String string : items) {
				this.ventanaPersonaAgregar.getLocalidadInput().addItem(string);
				this.ventanaPersonaEditar.getLocalidadInput().addItem(string);
			}
		}
		
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
