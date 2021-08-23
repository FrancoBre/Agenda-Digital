package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
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
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnEditar().addActionListener(e->ventanaEditarPersona(e)); //*
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersonaAgregar = VentanaPersonaAgregar.getInstance();
			this.ventanaPersonaAgregar.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			
			this.ventanaPersonaEditar = VentanaPersonaEditar.getInstance();
			this.ventanaPersonaEditar.getBtnEditarPersona().addActionListener(ep->editarPersona(ep));
			
			this.agenda = agenda;
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
				int piso = Integer.parseInt(pisoStr);
				String deptoStr = this.ventanaPersonaAgregar.getDeptoInput().getText();
				int depto = Integer.parseInt(deptoStr);
				String pais = (String) this.ventanaPersonaAgregar.getPaisInput().getSelectedItem();
				String provincia = (String) this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
				String localidad = (String) this.ventanaPersonaAgregar.getLocalidadInput().getSelectedItem();
				
				int idLocalidad = 0;
				
				DomicilioDTO nuevoDomicilio = new DomicilioDTO(0, calle, altura, piso, depto, idLocalidad);

				PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
				this.agenda.agregarPersona(nuevaPersona);
				
			}
			this.refrescarTabla();
			this.ventanaPersonaAgregar.cerrar();
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
				PersonaDTO personaEditada = new PersonaDTO(id, nombre, tel, id, id);
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
		
		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
