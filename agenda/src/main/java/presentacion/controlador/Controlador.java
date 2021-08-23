package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
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
			this.ventanaPersonaEditar.getPaisInput().addActionListener(combobox->cambioItemsPEdit(combobox));
			this.ventanaPersonaEditar.getProvinciaInput().addActionListener(comboLocalidad -> cambioItemsLEdit(comboLocalidad));
			
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
		
		private void cambioItemsPEdit(ActionEvent c) {
			this.ventanaPersonaEditar.getProvinciaInput().removeAllItems();
			int id = this.ventanaPersonaEditar.getPaisInput().getSelectedIndex() + 1;
			addProvinciasItem(agenda.getNombreProvincia(id));
		}
		
		private void cambioItemsLEdit(ActionEvent c) {
			this.ventanaPersonaEditar.getLocalidadInput().removeAllItems();
			String nombreProvincia = (String) this.ventanaPersonaEditar.getProvinciaInput().getSelectedItem();
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
				int idTipoContacto = this.agenda.getIdTipoContactoByNombre(tipoContacto);
				
				int idDomicilio = this.agenda.getDomicilioMaxId() + 1;
				
				DomicilioDTO nuevoDomicilio = new DomicilioDTO(idDomicilio, calle, altura, piso,
						depto, idLocalidad);

				PersonaDTO nuevaPersona;
				
				nuevaPersona = null;

				try {
					nuevaPersona = new PersonaDTO(this.agenda.getPersonaMaxId() + 1, nombre, tel, email, parseNacimiento(nacimiento),
							idDomicilio, idTipoContacto);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				this.agenda.agregarDomicilio(nuevoDomicilio);
				
				this.agenda.agregarPersona(nuevaPersona);
				
			}
			this.refrescarTabla();
			this.ventanaPersonaAgregar.cerrar();
		}
		
		public java.sql.Date parseNacimiento(String nacimiento) throws ParseException {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        Date parsed = format.parse(nacimiento);
	        java.sql.Date date = new java.sql.Date(parsed.getTime());
			
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
				int idPersona = this.personasEnTabla.get(filasSeleccionadas[0]).getIdPersona();
				int idDomicilio = this.personasEnTabla.get(filasSeleccionadas[0]).getDomicilio();
				
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
				int idTipoContacto = this.agenda.getIdTipoContactoByNombre(tipoContacto);
				
				DomicilioDTO domicilioEditado = new DomicilioDTO(idDomicilio, calle, altura, piso,
						depto, idLocalidad);
				
				this.agenda.editarDomicilio(domicilioEditado);

				PersonaDTO personaEditada;
				
				personaEditada = null;
				System.out.println("Guasu guasol");
				try {
					personaEditada = new PersonaDTO(idPersona, nombre, tel, email, parseNacimiento(nacimiento),
							idDomicilio, idTipoContacto);
					
					System.out.println(personaEditada.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				//this.agenda.agregarDomicilio(nuevoDomicilio);
				
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
			addTipoContacto(agenda.getNombreTipoContacto());
		}
			
		public void addTipoContacto(ArrayList<String> items) {
			for (String string : items) {
				this.ventanaPersonaAgregar.getTipoContactoInput().addItem(string);
				this.ventanaPersonaEditar.getTipoContactoInput().addItem(string);
			}
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
