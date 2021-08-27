package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.*;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.Vista;
import dto.PersonaDTO;

public class ControladorVista implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private Agenda agenda;
		private int[] filasSeleccionadas = null;
		
		public ControladorVista(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.agenda = agenda;
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));	
			
		}

		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
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

		
		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
