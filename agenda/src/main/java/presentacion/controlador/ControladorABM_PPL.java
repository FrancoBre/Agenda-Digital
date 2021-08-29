package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.PaisDTO;
import modelo.*;
import presentacion.vista.VentanaPPL;
import presentacion.vista.Vista;

public class ControladorABM_PPL implements ActionListener
{
		private Vista vista;
		private Agenda agenda;
		private VentanaPPL ventanaPPL;
		private String[] abm = {"Seleccionar","Agregar", "Modificar", "Eliminar"};
		public ControladorABM_PPL(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.agenda = agenda;
			
			this.ventanaPPL = VentanaPPL.getInstance();
			
			this.vista.getBtnAMB_PPL().addActionListener(r->mostrarABM_PPL(r));
			
			this.ventanaPPL.getOpcionesComboBox1().addActionListener(e-> definirCampos1(e));
			this.ventanaPPL.getPaisComboBox().addActionListener(e-> cambioTextFieldPais(e));
			this.ventanaPPL.getBtnActionPAIS().addActionListener(e-> actionbtn_pais(e));
			
			this.ventanaPPL.getOpcionesComboBox2().addActionListener(e-> definirCampos2(e));
			this.ventanaPPL.getProvinciaComboBox().addActionListener(e-> cambioTextFieldProvincia(e));
			this.ventanaPPL.getBtnActionPROVINCIA().addActionListener(e-> actionbtn_provincia(e));
			
//			this.ventanaPPL.getOpcionesComboBox().addActionListener(e -> activoComboBox(e));
//			this.ventanaPPL.getTiposComboBox().addActionListener(e -> cambioTextField(e));
//			this.ventanaPPL.getBtnActionTipo().addActionListener(e -> actionbtn(e));
		}
		
		public void inicializar()
		{
			ventanaPPL.getOpcionesComboBox1().setModel((new DefaultComboBoxModel<String>(abm)));
			ventanaPPL.getOpcionesComboBox2().setModel((new DefaultComboBoxModel<String>(abm)));
			ventanaPPL.getOpcionesComboBox3().setModel((new DefaultComboBoxModel<String>(abm)));
			setABM_default(1);
			this.vista.show();
		}	
		
		public void mostrarABM_PPL(ActionEvent e) {
			ventanaPPL.mostrarVentana();
		}

		
		public void setABM_default(int idPais) {
			this.ventanaPPL.getBtnActionPAIS().setText(abm[0]);
			this.ventanaPPL.getPaisTextField().setEnabled(false);
			this.ventanaPPL.getPaisTextField().setBounds(80, 37, 0, 20);
			this.ventanaPPL.getPaisComboBox().setEnabled(true);
			this.ventanaPPL.getPaisComboBox().setBounds(80, 36, 301, 22);
			this.ventanaPPL.getBtnActionPAIS().setEnabled(false);
			
			this.ventanaPPL.getBtnActionPROVINCIA().setText(abm[0]);
			this.ventanaPPL.getProvinciaTextField().setEnabled(false);
			this.ventanaPPL.getProvinciaTextField().setBounds(80, 77, 0, 20);
			this.ventanaPPL.getProvinciaComboBox().setEnabled(true);
			this.ventanaPPL.getProvinciaComboBox().setBounds(80, 76, 301, 22);
			this.ventanaPPL.getBtnActionPROVINCIA().setEnabled(false);
			
			refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
			refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
		}
		
// previos		
		public void addItems(ArrayList<String> items, JComboBox<String> combobox) {
			for (String item : items) {
				combobox.addItem(item);
			}
		}			
		
		private void refreshComboBox(JComboBox<String> combobox, ArrayList<String> items){
			combobox.removeAllItems();
			addItems(items, combobox);
		}
		
// Pais
				
		private void definirCampos1(ActionEvent e) {
			String seleccion = (String) this.ventanaPPL.getOpcionesComboBox1().getSelectedItem();
			refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
			if(seleccion== abm[0]) {
				setABM_default(1);
			} 
			else if(seleccion==abm[1]) {
				this.ventanaPPL.getPaisTextField().setText("");
				this.ventanaPPL.getBtnActionPAIS().setText(abm[1]);
				this.ventanaPPL.getPaisTextField().setEnabled(true);
				this.ventanaPPL.getPaisTextField().setBounds(80, 37, 301, 20);
				this.ventanaPPL.getPaisComboBox().setEnabled(false);
				this.ventanaPPL.getPaisComboBox().setBounds(231, 36, 0, 22);
				this.ventanaPPL.getBtnActionPAIS().setEnabled(true);
			} 
			else if(seleccion == abm[2]){
				this.ventanaPPL.getBtnActionPAIS().setText(abm[2]);
				this.ventanaPPL.getPaisTextField().setEnabled(true);
				this.ventanaPPL.getPaisTextField().setBounds(80, 37, 150, 20);
				this.ventanaPPL.getPaisComboBox().setEnabled(true);
				this.ventanaPPL.getPaisComboBox().setBounds(231, 36, 150, 22);
				this.ventanaPPL.getBtnActionPAIS().setEnabled(true);
			} 
			else if(seleccion == abm[3]){
				this.ventanaPPL.getPaisTextField().setText("");
				this.ventanaPPL.getBtnActionPAIS().setText(abm[3]);
				this.ventanaPPL.getPaisTextField().setEnabled(false);
				this.ventanaPPL.getPaisTextField().setBounds(80, 37, 0, 20);
				this.ventanaPPL.getPaisComboBox().setEnabled(true);
				this.ventanaPPL.getPaisComboBox().setBounds(80, 36, 301, 22);
				this.ventanaPPL.getBtnActionPAIS().setEnabled(true);
			}
		}
		
		private void cambioTextFieldPais(ActionEvent e) {
			String pais = (String) this.ventanaPPL.getPaisComboBox().getSelectedItem();
			this.ventanaPPL.getPaisTextField().setText(pais);
			
			int idPais = agenda.getIdPaisByNombre((String) this.ventanaPPL.getPaisComboBox().getSelectedItem());
			refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
		
			
		}
		
		private void actionbtn_pais(ActionEvent e) {
			String opcion = this.ventanaPPL.getOpcionesComboBox1().getSelectedItem().toString();
			String pais = this.ventanaPPL.getPaisComboBox().getSelectedItem().toString();
			String nuevo = this.ventanaPPL.getPaisTextField().getText();
			if(opcion == abm[1]) {
				agenda.agregarPais(nuevo);
				this.ventanaPPL.getPaisTextField().setText("");
			}
			else if(opcion == abm[2]){
				int idPais= agenda.getIdPaisByNombre(pais);
				PaisDTO editarPais = new PaisDTO(idPais, nuevo);
				agenda.editarPais(editarPais);
				refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
			}
			else if(opcion == abm[3]){
				int idPais= agenda.getIdPaisByNombre(pais);
				agenda.borrarPais(idPais);
				refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
			}
		}
		
		
		
// Provincia
		
		private void definirCampos2(ActionEvent e) {
			String seleccion = (String) this.ventanaPPL.getOpcionesComboBox2().getSelectedItem();
			if(seleccion== abm[0]) {
				this.ventanaPPL.getBtnActionPROVINCIA().setText(abm[0]);
				this.ventanaPPL.getProvinciaTextField().setEnabled(false);
				this.ventanaPPL.getProvinciaTextField().setBounds(80, 77, 0, 20);
				this.ventanaPPL.getProvinciaComboBox().setEnabled(true);
				this.ventanaPPL.getProvinciaComboBox().setBounds(80, 76, 301, 22);
				this.ventanaPPL.getBtnActionPROVINCIA().setEnabled(false);
			} 
			else if(seleccion==abm[1]) {
				
				this.ventanaPPL.getProvinciaTextField().setText("");
				this.ventanaPPL.getBtnActionPROVINCIA().setText(abm[1]);
				this.ventanaPPL.getProvinciaTextField().setEnabled(true);
				this.ventanaPPL.getProvinciaTextField().setBounds(80, 77, 301, 20);
				this.ventanaPPL.getProvinciaComboBox().setEnabled(false);
				this.ventanaPPL.getProvinciaComboBox().setBounds(231, 76, 0, 22);
				this.ventanaPPL.getBtnActionPROVINCIA().setEnabled(true);
			} 
			else if(seleccion == abm[2]){
				this.ventanaPPL.getBtnActionPROVINCIA().setText(abm[2]);
				this.ventanaPPL.getProvinciaTextField().setEnabled(true);
				this.ventanaPPL.getProvinciaTextField().setBounds(80, 77, 150, 20);
				this.ventanaPPL.getProvinciaComboBox().setEnabled(true);
				this.ventanaPPL.getProvinciaComboBox().setBounds(231, 76, 150, 22);
				this.ventanaPPL.getBtnActionPROVINCIA().setEnabled(true);
			} 
			else if(seleccion == abm[3]){
				this.ventanaPPL.getProvinciaTextField().setText("");
				this.ventanaPPL.getBtnActionPROVINCIA().setText(abm[3]);
				this.ventanaPPL.getProvinciaTextField().setEnabled(false);
				this.ventanaPPL.getProvinciaTextField().setBounds(80, 77, 0, 20);
				this.ventanaPPL.getProvinciaComboBox().setEnabled(true);
				this.ventanaPPL.getProvinciaComboBox().setBounds(80, 76, 301, 22);
				this.ventanaPPL.getBtnActionPROVINCIA().setEnabled(true);
			}
		}
		private void cambioTextFieldProvincia(ActionEvent e) {
			
		}
		private void actionbtn_provincia(ActionEvent e) {
			
		}
		
		
		
		
		
		
// Localidad
		
		
		
		
		
		
		
		
		
//		public void activoComboBox(ActionEvent e) {
//			String seleccion = (String) this.ventanaTipo.getOpcionesComboBox().getSelectedItem();
//			refreshListTipos();
//			if(seleccion==agenda.AMB()[0]) {
//				setABM_default();
//			}else if(seleccion == agenda.AMB()[1]){
//				setBoundsModificacion();
//				setVisible_ComboBoxTipos(true);
//				setVisible_TextFieldTipos(true);
//				setTextField_selected();
//				this.ventanaTipo.getBtnActionTipo().setText(agenda.AMB()[1]);
//			}
//			else if(seleccion == agenda.AMB()[2]){
//				setBoundsEliminar();
//				setVisible_ComboBoxTipos(true);
//				setVisible_TextFieldTipos(false);
//				this.ventanaTipo.getBtnActionTipo().setText(agenda.AMB()[2]);
//			}
//		}
//		
//		private void cambioTextField(ActionEvent e) {
//			setTextField_selected();
//		};
//		
//		private void actionbtn(ActionEvent e) {
//			String opcion = this.ventanaTipo.getOpcionesComboBox().getSelectedItem().toString();
//			String seleccion = this.ventanaTipo.getTiposComboBox().getSelectedItem().toString();
//			String txt = this.ventanaTipo.getNuevaEtiquetaTextField().getText();
//			if(opcion==agenda.AMB()[0]) {
//				TipoContactoDTO nuevoTipo = new TipoContactoDTO(0, txt);
//				agenda.agregarTipo(nuevoTipo);
//				setTextField("");
//			}
//			else if(opcion == agenda.AMB()[1]){
//				int idTipo = agenda.getIdTipoContactoByNombre(seleccion);
//				TipoContactoDTO editarTipo = new TipoContactoDTO(idTipo, txt);
//				agenda.editarTipo(editarTipo);
//				refreshListTipos();
//			}
//			else if(opcion == agenda.AMB()[2]){
//				agenda.borrarTipo(seleccion);
//				refreshListTipos();
//			}
//		}
//		
//		public void setABM_default() {
//			setBoundsAgregar();
//			refreshListTipos();
//			setVisible_ComboBoxTipos(false);
//			setVisible_TextFieldTipos(true);
//			setTextField("");
//			this.ventanaTipo.getBtnActionTipo().setText(agenda.AMB()[0]);
//		}
//		
//		private void refreshListTipos(){
//			this.ventanaTipo.getTiposComboBox().removeAllItems();
//			addTipoContacto(agenda.getNombreTipoContacto());
//		}	
//		
//		public void addTipoContacto(ArrayList<String> items) {
//			for (String string : items) {
//				this.ventanaTipo.getTiposComboBox().addItem(string);
//			}
//		}
//		
//		private void setTextField_selected() {
//			String t = (String) this.ventanaTipo.getTiposComboBox().getSelectedItem();
//			this.setTextField(t);
//		}
//		
//		public void setTextField(String txt) {
//			this.ventanaTipo.getNuevaEtiquetaTextField().setText(txt);
//		}
//		
//		public void setVisible_ComboBoxTipos(boolean b) {
//			this.ventanaTipo.getTiposComboBox().setEnabled(b);
//			this.ventanaTipo.getTiposComboBox().setVisible(b);
//		}
//		
//		public void setVisible_TextFieldTipos(boolean b) {
//			this.ventanaTipo.getNuevaEtiquetaTextField().setEnabled(b);
//			this.ventanaTipo.getNuevaEtiquetaTextField().setVisible(b);
//		}
//			
//		public void setBoundsAgregar() {		
//			this.ventanaTipo.getNuevaEtiquetaTextField().setBounds(66, 32, 224, 22);
//		}
//		
//		public void setBoundsModificacion() {
//			this.ventanaTipo.getNuevaEtiquetaTextField().setBounds(66, 33, 105, 20);			
//			this.ventanaTipo.getTiposComboBox().setBounds(172, 32, 118, 22);
//		}
//		
//		public void setBoundsEliminar() {		
//			this.ventanaTipo.getTiposComboBox().setBounds(66, 32, 224, 22);
//		}
		

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
