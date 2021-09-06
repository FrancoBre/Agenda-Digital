package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import modelo.*;
import presentacion.vista.VentanaTipo;
import presentacion.vista.Vista;
import dto.TipoContactoDTO;

public class ControladorABM_Tipo implements ActionListener
{
		private Vista vista;
		private Agenda agenda;
		private VentanaTipo ventanaTipo;
		private String[] abm = {"Agregar", "Modificar", "Eliminar"}; 
		
		public ControladorABM_Tipo(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.agenda = agenda;
			
			this.ventanaTipo = VentanaTipo.getInstance();
			
			this.vista.getBtnAMB_tipo().addActionListener(r->mostrarABM_tipo(r));
			
			this.ventanaTipo.getOpcionesComboBox().addActionListener(e -> activoComboBox(e));
			this.ventanaTipo.getTiposComboBox().addActionListener(e -> cambioTextField(e));
			this.ventanaTipo.getBtnActionTipo().addActionListener(e -> actionbtn(e));
		}

		public void inicializar()
		{
			ventanaTipo.getOpcionesComboBox().setModel((new DefaultComboBoxModel<String>(abm)));
			setABM_default();
		}	
		
		public void mostrarABM_tipo(ActionEvent e) {
			ventanaTipo.mostrarVentana();
		}
		
		public void activoComboBox(ActionEvent e) {
			String seleccion = (String) this.ventanaTipo.getOpcionesComboBox().getSelectedItem();
			refreshListTipos();
			if(seleccion==abm[0]) {
				setABM_default();
			}else if(seleccion == abm[1]){
				setBoundsModificacion();
				setVisible_ComboBoxTipos(true);
				setVisible_TextFieldTipos(true);
				setTextField_selected();
				this.ventanaTipo.getBtnActionTipo().setText(abm[1]);
			}
			else if(seleccion == abm[2]){
				setBoundsEliminar();
				setVisible_ComboBoxTipos(true);
				setVisible_TextFieldTipos(false);
				this.ventanaTipo.getBtnActionTipo().setText(abm[2]);
			}
		}
		
		private void cambioTextField(ActionEvent e) {
			setTextField_selected();
		};
		
		private void actionbtn(ActionEvent e) {
			String opcion = this.ventanaTipo.getOpcionesComboBox().getSelectedItem().toString();
			String seleccion = this.ventanaTipo.getTiposComboBox().getSelectedItem().toString();
			String txt = this.ventanaTipo.getNuevaEtiquetaTextField().getText();
			if(opcion==abm[0]) {
				TipoContactoDTO nuevoTipo = new TipoContactoDTO(0, txt);
				agenda.agregarTipo(nuevoTipo);
				setTextField("");
			}
			else if(opcion == abm[1]){
				int idTipo = agenda.getIdTipoContactoByNombre(seleccion);
				TipoContactoDTO editarTipo = new TipoContactoDTO(idTipo, txt);
				agenda.editarTipo(editarTipo);
				refreshListTipos();
			}
			else if(opcion == abm[2]){
				agenda.borrarTipo(seleccion);
				refreshListTipos();
			}
		}
		
		public void setABM_default() {
			setBoundsAgregar();
			refreshListTipos();
			setVisible_ComboBoxTipos(false);
			setVisible_TextFieldTipos(true);
			setTextField("");
			this.ventanaTipo.getBtnActionTipo().setText(abm[0]);
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
			
		public void setBoundsAgregar() {		
			this.ventanaTipo.getNuevaEtiquetaTextField().setBounds(66, 32, 224, 22);
		}
		
		public void setBoundsModificacion() {
			this.ventanaTipo.getNuevaEtiquetaTextField().setBounds(66, 33, 105, 20);			
			this.ventanaTipo.getTiposComboBox().setBounds(172, 32, 118, 22);
		}
		
		public void setBoundsEliminar() {		
			this.ventanaTipo.getTiposComboBox().setBounds(66, 32, 224, 22);
		}
		

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
