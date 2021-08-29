package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
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
			this.ventanaPPL.getPaisComboBox().addActionListener(e-> paisCambioCampos(e));
			this.ventanaPPL.getBtnActionPAIS().addActionListener(e-> actionbtn_pais(e));
			
			this.ventanaPPL.getOpcionesComboBox2().addActionListener(e-> definirCampos2(e));
			this.ventanaPPL.getProvinciaComboBox().addActionListener(e-> provinciaCambioCampos(e));
			this.ventanaPPL.getBtnActionPROVINCIA().addActionListener(e-> actionbtn_provincia(e));
			
			this.ventanaPPL.getOpcionesComboBox3().addActionListener(e -> definirCampos3(e));
			this.ventanaPPL.getLocalidadComboBox().addActionListener(e-> localidadCambioCampos(e));
			this.ventanaPPL.getBtnActionLOCALIDAD().addActionListener(e-> actionbtn_localidad(e));
		
		}

		public void inicializar()
		{
			ventanaPPL.getOpcionesComboBox1().setModel((new DefaultComboBoxModel<String>(abm)));
			ventanaPPL.getOpcionesComboBox2().setModel((new DefaultComboBoxModel<String>(abm)));
			ventanaPPL.getOpcionesComboBox3().setModel((new DefaultComboBoxModel<String>(abm)));
			setABM_default(1, 1);
			this.vista.show();
		}	
		
		public void mostrarABM_PPL(ActionEvent e) {
			ventanaPPL.mostrarVentana();
		}

		
		public void setABM_default(int idPais, int idProvincia) {
			this.ventanaPPL.getBtnActionPAIS().setText(abm[0]);
			this.ventanaPPL.getPaisTextField().setEnabled(false);
			this.ventanaPPL.getPaisTextField().setBounds(80, 37, 0, 20);
			this.ventanaPPL.getPaisComboBox().setEnabled(true);
			this.ventanaPPL.getPaisComboBox().setBounds(80, 36, 301, 22);
			this.ventanaPPL.getBtnActionPAIS().setEnabled(false);
			
			this.ventanaPPL.getOpcionesComboBox2().setSelectedIndex(0);
			this.ventanaPPL.getBtnActionPROVINCIA().setText(abm[0]);
			this.ventanaPPL.getProvinciaTextField().setEnabled(false);
			this.ventanaPPL.getProvinciaTextField().setBounds(80, 77, 0, 20);
			this.ventanaPPL.getProvinciaComboBox().setEnabled(true);
			this.ventanaPPL.getProvinciaComboBox().setBounds(80, 76, 301, 22);
			this.ventanaPPL.getBtnActionPROVINCIA().setEnabled(false);
			
			this.ventanaPPL.getOpcionesComboBox3().setSelectedIndex(0);
			this.ventanaPPL.getBtnActionLOCALIDAD().setText(abm[0]);
			this.ventanaPPL.getLocalidadTextField().setEnabled(false);
			this.ventanaPPL.getLocalidadTextField().setBounds(80, 117, 0, 20);
			this.ventanaPPL.getLocalidadComboBox().setEnabled(true);
			this.ventanaPPL.getLocalidadComboBox().setBounds(80, 116, 301, 22);
			this.ventanaPPL.getBtnActionLOCALIDAD().setEnabled(false);
			
			refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
			refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
			refreshComboBox(this.ventanaPPL.getLocalidadComboBox(), agenda.getNombreLocalidad(idProvincia));
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
				setABM_default(1, 1);
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
			else if(seleccion != abm[0]) {
				this.ventanaPPL.getOpcionesComboBox2().setSelectedIndex(0);
				this.ventanaPPL.getOpcionesComboBox3().setSelectedIndex(0);
			}
		}
		
		private void paisCambioCampos(ActionEvent e) {
			String pais = (String) this.ventanaPPL.getPaisComboBox().getSelectedItem();
			int idPais = agenda.getIdPaisByNombre(pais);
			
			this.ventanaPPL.getPaisTextField().setText(pais);
			refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
		}
		
		private void actionbtn_pais(ActionEvent e) {
			String opcion_seleccionada = this.ventanaPPL.getOpcionesComboBox1().getSelectedItem().toString();
			String pais_seleccionado = this.ventanaPPL.getPaisComboBox().getSelectedItem().toString();
			String nuevoPais_ingresado = this.ventanaPPL.getPaisTextField().getText();
			if(opcion_seleccionada == abm[1]) {
				agenda.agregarPais(nuevoPais_ingresado);
				refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
				this.ventanaPPL.getPaisTextField().setText("");
			}
			else if(opcion_seleccionada == abm[2]){
				int idPais= agenda.getIdPaisByNombre(pais_seleccionado);
				PaisDTO editarPais = new PaisDTO(idPais, nuevoPais_ingresado);
				agenda.editarPais(editarPais);
				refreshComboBox(this.ventanaPPL.getPaisComboBox(), agenda.getNombrePaises());
			}
			else if(opcion_seleccionada == abm[3]){
				int idPais= agenda.getIdPaisByNombre(pais_seleccionado);
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
			else if(seleccion != abm[0]) {
				this.ventanaPPL.getOpcionesComboBox1().setSelectedIndex(0);
			}
		}
		private void provinciaCambioCampos(ActionEvent e) {
			String provincia = (String) this.ventanaPPL.getProvinciaComboBox().getSelectedItem();
			this.ventanaPPL.getProvinciaTextField().setText(provincia);
			int idProvincia = agenda.getIdProvinciaByNombre(provincia);
			refreshComboBox(this.ventanaPPL.getLocalidadComboBox(), agenda.getNombreLocalidad(idProvincia));
		}
		private void actionbtn_provincia(ActionEvent e) {
			String opcion_seleccionada = this.ventanaPPL.getOpcionesComboBox2().getSelectedItem().toString();
			String nuevoNombre_ingresado = this.ventanaPPL.getProvinciaTextField().getText();
			String pais_seleccionado = this.ventanaPPL.getPaisComboBox().getSelectedItem().toString();
			int idPais =  agenda.getIdPaisByNombre(pais_seleccionado);
			if(opcion_seleccionada == abm[1]) {
				ProvinciaDTO nuevaProvincia = new ProvinciaDTO(0, nuevoNombre_ingresado, idPais);
				agenda.agregarProvincia(nuevaProvincia);
				refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
				this.ventanaPPL.getProvinciaTextField().setText("");
			}
			else if(opcion_seleccionada == abm[2]) {
				String provincia_seleccionada = this.ventanaPPL.getProvinciaComboBox().getSelectedItem().toString();
				int idProvincia = agenda.getIdProvinciaByNombre(provincia_seleccionada);
				ProvinciaDTO provinciaEditada = new ProvinciaDTO(idProvincia, nuevoNombre_ingresado, idPais);
				agenda.editarProvincia(provinciaEditada);
				refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
			}
			else if(opcion_seleccionada == abm[3]) {
				String provincia_seleccionada = this.ventanaPPL.getProvinciaComboBox().getSelectedItem().toString();
				int idProvincia = agenda.getIdProvinciaByNombre(provincia_seleccionada);
				agenda.borrarProvincia(idProvincia);
				refreshComboBox(this.ventanaPPL.getProvinciaComboBox(), agenda.getNombreProvincia(idPais));
			}
			
		}
		
// Localidad
		
		private void definirCampos3(ActionEvent e) {
			String seleccion = (String) this.ventanaPPL.getOpcionesComboBox3().getSelectedItem();
			if(seleccion== abm[0]) {
				this.ventanaPPL.getBtnActionLOCALIDAD().setText(abm[0]);
				this.ventanaPPL.getLocalidadTextField().setEnabled(false);
				this.ventanaPPL.getLocalidadTextField().setBounds(80, 77, 0, 20);
				this.ventanaPPL.getLocalidadComboBox().setEnabled(true);
				this.ventanaPPL.getLocalidadComboBox().setBounds(80, 76, 301, 22);
				this.ventanaPPL.getBtnActionLOCALIDAD().setEnabled(false);
			} 
			else if(seleccion== abm[1]) {
				this.ventanaPPL.getLocalidadTextField().setText("");
				this.ventanaPPL.getBtnActionLOCALIDAD().setText(abm[1]);
				this.ventanaPPL.getLocalidadTextField().setEnabled(true);
				this.ventanaPPL.getLocalidadTextField().setBounds(80, 117, 301, 20);
				this.ventanaPPL.getLocalidadComboBox().setEnabled(false);
				this.ventanaPPL.getLocalidadComboBox().setBounds(231, 116, 0, 22);
				this.ventanaPPL.getBtnActionLOCALIDAD().setEnabled(true);
			}
			else if(seleccion== abm[2]) { 
				this.ventanaPPL.getBtnActionLOCALIDAD().setText(abm[2]);
				this.ventanaPPL.getLocalidadTextField().setEnabled(true);
				this.ventanaPPL.getLocalidadTextField().setBounds(80, 117, 150, 20);
				this.ventanaPPL.getLocalidadComboBox().setEnabled(true);
				this.ventanaPPL.getLocalidadComboBox().setBounds(231, 116, 150, 22);
				this.ventanaPPL.getBtnActionLOCALIDAD().setEnabled(true);
			}
			else if(seleccion== abm[3]) {
				this.ventanaPPL.getLocalidadTextField().setText("");
				this.ventanaPPL.getBtnActionLOCALIDAD().setText(abm[3]);
				this.ventanaPPL.getLocalidadTextField().setEnabled(false);
				this.ventanaPPL.getLocalidadTextField().setBounds(80, 117, 0, 20);
				this.ventanaPPL.getLocalidadComboBox().setEnabled(true);
				this.ventanaPPL.getLocalidadComboBox().setBounds(80, 116, 301, 22);
				this.ventanaPPL.getBtnActionLOCALIDAD().setEnabled(true);
			}
		}
		
		
		private void localidadCambioCampos(ActionEvent e) {
			String localidad = (String) this.ventanaPPL.getLocalidadComboBox().getSelectedItem();
			this.ventanaPPL.getLocalidadTextField().setText(localidad);
		}

		private void actionbtn_localidad(ActionEvent e) {
			String opcion_seleccionada = this.ventanaPPL.getOpcionesComboBox3().getSelectedItem().toString();
			String nuevoNombre_ingresado = this.ventanaPPL.getLocalidadTextField().getText();
			String provincia_seleccionada = this.ventanaPPL.getProvinciaComboBox().getSelectedItem().toString();
			String pais_seleccionado = this.ventanaPPL.getPaisComboBox().getSelectedItem().toString();
			int idPais = agenda.getIdPaisByNombre(pais_seleccionado);
			int idProvincia = agenda.getIdProvinciaByNombre(provincia_seleccionada);
			if(opcion_seleccionada == abm[1]) {
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, nuevoNombre_ingresado, idProvincia, idPais);
				agenda.agregarLocalidad(nuevaLocalidad);
				refreshComboBox(this.ventanaPPL.getLocalidadComboBox(), agenda.getNombreLocalidad(idProvincia));
				this.ventanaPPL.getLocalidadTextField().setText("");
			}
			else if(opcion_seleccionada == abm[2]) {
				String localidad_seleccionada = this.ventanaPPL.getLocalidadComboBox().getSelectedItem().toString();
				int idLocalidad = agenda.getIdLocalidadByNombre(localidad_seleccionada);
				LocalidadDTO localidadEditada = new LocalidadDTO(idLocalidad, nuevoNombre_ingresado, idProvincia, idPais);
				agenda.editarLocalidad(localidadEditada);
				refreshComboBox(this.ventanaPPL.getLocalidadComboBox(), agenda.getNombreLocalidad(idProvincia));
			}
			else if(opcion_seleccionada == abm[3]) {
				String localidad_seleccionada = this.ventanaPPL.getLocalidadComboBox().getSelectedItem().toString();
				int idLocalidad = agenda.getIdLocalidadByNombre(localidad_seleccionada);
				agenda.borrarLocalidad(idLocalidad);
				refreshComboBox(this.ventanaPPL.getLocalidadComboBox(), agenda.getNombreLocalidad(idProvincia));
			}
		}
		

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
