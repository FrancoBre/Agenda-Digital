package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.*;
import presentacion.vista.VentanaTipo;
import presentacion.vista.Vista;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class ControladorABM_Tipo implements ActionListener
{
		private Vista vista;
		private Agenda agenda;
		
		private VentanaTipo ventanaTipo;
		
		public ControladorABM_Tipo(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.agenda = agenda;
			
			this.ventanaTipo = VentanaTipo.getInstance();
			
			this.vista.getBtnAMB_tipo().addActionListener(r->mostrarABM(r));
			
			this.ventanaTipo.getOpcionesComboBox().addActionListener(e -> activoComboBox(e));
			this.ventanaTipo.getTiposComboBox().addActionListener(e -> cambioTextField(e));
			this.ventanaTipo.getBtnActionTipo().addActionListener(e -> actionbtn(e));
		}

		public void inicializar()
		{
			ventanaTipo.getOpcionesComboBox().setModel((new DefaultComboBoxModel<String>(agenda.AMB())));
			setABM_default();
			this.vista.show();
		}	
		
		public void mostrarABM(ActionEvent e) {
			ventanaTipo.mostrarVentana();
		}
		
		public void activoComboBox(ActionEvent e) {
			String seleccion = (String) this.ventanaTipo.getOpcionesComboBox().getSelectedItem();
			setTextField("");
			if(seleccion==agenda.AMB()[0]) {
				setABM_default();
			}else if(seleccion == agenda.AMB()[1]){
				setVisible_ComboBoxTipos(true);
				setVisible_TextFieldTipos(true);
				setTextField_selected();
				this.ventanaTipo.getBtnActionTipo().setText(agenda.AMB()[1]);
			}
			else if(seleccion == agenda.AMB()[2]){
				setVisible_ComboBoxTipos(true);
				setVisible_TextFieldTipos(false);
				this.ventanaTipo.getBtnActionTipo().setText(agenda.AMB()[2]);
			}
		}
		
		private void cambioTextField(ActionEvent e) {
			setTextField_selected();
		};
		
		private void actionbtn(ActionEvent e) {
			String seleccion = (String) this.ventanaTipo.getOpcionesComboBox().getSelectedItem();
			String txt = this.ventanaTipo.getNuevaEtiquetaTextField().getText();
			if(seleccion==agenda.AMB()[0]) {
				TipoContactoDTO nuevoTipo = new TipoContactoDTO(0, txt);
				agenda.agregarTipo(nuevoTipo);
			}
			else if(seleccion == agenda.AMB()[1]){
				String tipoSeleccionado = this.ventanaTipo.getTiposComboBox().getSelectedItem().toString();
				int idTipo = agenda.getIdTipoContactoByNombre(tipoSeleccionado);
				TipoContactoDTO editarTipo = new TipoContactoDTO(idTipo, txt);
				agenda.editarTipo(editarTipo);
			}
			else if(seleccion == agenda.AMB()[2]){
				agenda.borrarTipo(txt);
			}
			refreshListTipos();
		}
		
		public void setABM_default() {
			refreshListTipos();
			setVisible_ComboBoxTipos(false);
			setVisible_TextFieldTipos(true);
			setTextField("");
			this.ventanaTipo.getBtnActionTipo().setText(agenda.AMB()[0]);
		}
		
		private void refreshListTipos(){
			this.ventanaTipo.getTiposComboBox().removeAllItems();
			addTipoContacto(agenda.getNombreTipoContacto());
		}	
		
		public void addTipoContacto(ArrayList<String> items) {
			for (String string : items) {
				this.ventanaTipo.getTiposComboBox().addItem(string);
			}
		}
		
		private void setTextField_selected() {
			String t = (String) this.ventanaTipo.getTiposComboBox().getSelectedItem();
			this.setTextField(t);
		}
		
		public void setTextField(String txt) {
			this.ventanaTipo.getNuevaEtiquetaTextField().setText(txt);
		}
		
		public void setVisible_ComboBoxTipos(boolean b) {
			this.ventanaTipo.getTiposComboBox().setEnabled(b);
			this.ventanaTipo.getTiposComboBox().setVisible(b);
		}
		
		public void setVisible_TextFieldTipos(boolean b) {
			this.ventanaTipo.getNuevaEtiquetaTextField().setEnabled(b);
			this.ventanaTipo.getNuevaEtiquetaTextField().setVisible(b);
		}
			

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
