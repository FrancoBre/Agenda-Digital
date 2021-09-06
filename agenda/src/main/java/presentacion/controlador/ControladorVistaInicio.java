package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import presentacion.vista.Vista;
import presentacion.vista.VistaInicio;


public class ControladorVistaInicio implements ActionListener {
    private VistaInicio vistaInicio;
    public Vista vista;
    private Agenda agenda;

    public ControladorVistaInicio(VistaInicio vistaInicio, Agenda agenda) {
		this.agenda = agenda;
		this.vistaInicio = vistaInicio;
		this.vista = Vista.getInstance();
		
		
		this.vistaInicio.getBtnIngreso().addActionListener(e->ingresar(e));
		
    }

    private void ingresar(ActionEvent e) {
    	this.vista.mostrarVentana();

	}

	public void inicializar() {
		
    	this.vistaInicio.show();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
