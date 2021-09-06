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
	char[] pass = vistaInicio.getPassField().getPassword();

	System.out.println("user: " + user + " pass: " + pass);
	this.login.enviarCredenciales(user, String.valueOf(pass));
	
	this.vistaInicio.cerrarVentana();
	this.vista.mostrarVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
