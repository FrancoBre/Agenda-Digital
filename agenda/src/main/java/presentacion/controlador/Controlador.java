package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import modelo.*;
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
			this.ventanaPersonaAgregar.getBtnAction().addActionListener(p->guardarPersona(p));
			
			this.ventanaPersonaEditar = VentanaPersonaEditar.getInstance();
			this.ventanaPersonaEditar.getBtnAction().addActionListener(ep->editarPersona(ep));
			
			
			this.ventanaPersonaAgregar.getComboBoxMes().addActionListener(e -> agregarD(e));
			this.ventanaPersonaEditar.getComboBoxMes().addActionListener(e -> agregarDEdit(e));
			
			
			this.ventanaPersonaAgregar.getPaisInput().addActionListener(combobox->cambioItemsP(combobox));
			this.ventanaPersonaAgregar.getProvinciaInput().addActionListener(comboLocalidad -> cambioItemsL(comboLocalidad));
			this.ventanaPersonaEditar.getPaisInput().addActionListener(combobox->cambioItemsPEdit(combobox));
			this.ventanaPersonaEditar.getProvinciaInput().addActionListener(comboLocalidad -> cambioItemsLEdit(comboLocalidad));
			
			
			
			
			addComboboxItems();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void addComboboxItems() {
			addPaisesItems(agenda.getNombrePaises());
			addTipoContacto(agenda.getNombreTipoContacto());
			addIntegerComboBox(agenda.getYears(), Fecha.getMonths());
			llenarDias(31);
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
				String tipoContacto = (String) this.ventanaPersonaAgregar.getTipoContactoInput().getSelectedItem();
				
				String calle = this.ventanaPersonaAgregar.getCalleInput().getText();
				String altura = this.ventanaPersonaAgregar.getAlturaInput().getText();
				String pisoStr = this.ventanaPersonaAgregar.getPisoInput().getText();
				int piso = Integer.parseInt(pisoStr.trim());
				String deptoStr = this.ventanaPersonaAgregar.getDeptoInput().getText();
				int depto = Integer.parseInt(deptoStr.trim());
				String localidad = (String) this.ventanaPersonaAgregar.getLocalidadInput().getSelectedItem();
				
				int year = (int) this.ventanaPersonaAgregar.getComboBoxAnio().getSelectedItem();
				int month = (int) this.ventanaPersonaAgregar.getComboBoxMes().getSelectedItem();
				int date = (int) this.ventanaPersonaAgregar.getComboBoxDia().getSelectedItem();
				String nacimiento = year +""+ month +""+ date;
				
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
		
		private java.sql.Date parseNacimiento(String nacimiento) throws ParseException {
			/*SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        Date parsed = format.parse(nacimiento);
	        java.sql.Date date = new java.sql.Date(parsed.getTime());*/
			//nacimiento.replaceAll("\\s+","-");
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
			
	        Date date = formato.parse(nacimiento);
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			return sqlDate;
		}
		
		private void ventanaEditarPersona(ActionEvent e) {
			if(this.vista.getTablaPersonas().getSelectedRows().length == 1) {
				filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				this.ventanaPersonaEditar.setCampos(this.personasEnTabla.get(filasSeleccionadas[0]));
				this.ventanaPersonaEditar.mostrarVentana();
			}
		}
		
		private void editarPersona(ActionEvent ep) {	
			if (!ventanaPersonaEditar.validarRequeridos()) {
				//crear una ventana con mensaje de campos vacios
			}else {
				int idPersona = this.personasEnTabla.get(filasSeleccionadas[0]).getIdPersona();
				int idDomicilio = this.personasEnTabla.get(filasSeleccionadas[0]).getDomicilio();
				
				String nombre = this.ventanaPersonaEditar.getNombreInput().getText();
				String tel = this.ventanaPersonaEditar.getTelefonoInput().getText();
				String email = this.ventanaPersonaEditar.getEmailInput().getText();
				String tipoContacto = (String) this.ventanaPersonaEditar.getTipoContactoInput().getSelectedItem();
				
				String calle = this.ventanaPersonaEditar.getCalleInput().getText();
				String altura = this.ventanaPersonaEditar.getAlturaInput().getText();
				String pisoStr = this.ventanaPersonaEditar.getPisoInput().getText();
				int piso = Integer.parseInt(pisoStr.trim());
				String deptoStr = this.ventanaPersonaEditar.getDeptoInput().getText();
				int depto = Integer.parseInt(deptoStr.trim());
				String localidad = (String) this.ventanaPersonaEditar.getLocalidadInput().getSelectedItem();
				
				int year = (int) this.ventanaPersonaEditar.getComboBoxAnio().getSelectedItem();
				int month = (int) this.ventanaPersonaEditar.getComboBoxMes().getSelectedItem();
				int date = (int) this.ventanaPersonaEditar.getComboBoxDia().getSelectedItem();
				String nacimiento = year +""+ month +""+ date;
				
				//unused
				String pais = (String) this.ventanaPersonaEditar.getPaisInput().getSelectedItem();
				String provincia = (String) this.ventanaPersonaEditar.getProvinciaInput().getSelectedItem();
				
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
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}		
		
		private void agregarD(ActionEvent e) {
			this.ventanaPersonaAgregar.getComboBoxDia().removeAllItems();
			int m = this.ventanaPersonaAgregar.getComboBoxMes().getItemAt(this.ventanaPersonaAgregar.getComboBoxMes().getSelectedIndex());
			int y = this.ventanaPersonaAgregar.getComboBoxAnio().getItemAt(this.ventanaPersonaAgregar.getComboBoxAnio().getSelectedIndex());
			
			int dias = Fecha.numeroDeDiasMes(m, y);
			llenarDias(dias);
		}
		
		private void agregarDEdit(ActionEvent e) {
			this.ventanaPersonaEditar.getComboBoxDia().removeAllItems();
			int m = this.ventanaPersonaEditar.getComboBoxMes().getItemAt(this.ventanaPersonaEditar.getComboBoxMes().getSelectedIndex());
			int y = this.ventanaPersonaEditar.getComboBoxAnio().getItemAt(this.ventanaPersonaEditar.getComboBoxAnio().getSelectedIndex());
			
			int dias = Fecha.numeroDeDiasMes(m, y);
			llenarDias(dias);
		}
		
		private void llenarDias(int limite) {
			for(int i = 1; i <= limite; i++) {
				this.ventanaPersonaAgregar.getComboBoxDia().addItem(i);
				this.ventanaPersonaEditar.getComboBoxDia().addItem(i);
			}
		}
		
		public void addIntegerComboBox(ArrayList<Integer> y, Integer[] m){
			for (Integer integer : y) {
				this.ventanaPersonaAgregar.getComboBoxAnio().addItem(integer);
				this.ventanaPersonaEditar.getComboBoxAnio().addItem(integer);
			}
			this.ventanaPersonaAgregar.getComboBoxMes().setModel(new DefaultComboBoxModel<Integer>(m));
			this.ventanaPersonaEditar.getComboBoxMes().setModel(new DefaultComboBoxModel<Integer>(m));
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
