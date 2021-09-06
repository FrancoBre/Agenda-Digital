package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import presentacion.vista.Vista;
import presentacion.vista.VistaInicio;

public class ControladorVistaInicio implements ActionListener {
    private VistaInicio vistaInicio;
    public Vista vista;
    private Login login;

    public ControladorVistaInicio(VistaInicio vistaInicio, Login login) {
	this.vistaInicio = vistaInicio;
	this.vista = Vista.getInstance();
	this.login = login;

	this.vistaInicio.getBtnIngreso().addActionListener(e -> enviarCredenciales(e));

    }

    public void inicializar() {

	this.vistaInicio.show();
    }

    public void enviarCredenciales(ActionEvent s) {
	String user = vistaInicio.getUser_textField().getText();
	char[] passArr = vistaInicio.getPassField().getPassword();
	String pass = String.valueOf(passArr);
	
	this.login.enviarCredenciales(user, pass);
	
	this.vistaInicio.cerrarVentana();
	this.vista.mostrarVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
