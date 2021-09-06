package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import persistencia.dao.mysql.DAOSQLFactory;
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
		boolean mostrar = false;
		
		mostrar = this.login.enviarCredenciales(user, pass);
		
		this.vistaInicio.cerrarVentana();
		this.vista.mostrarVentana();
		if(mostrar) {
			Agenda modelo = new Agenda(new DAOSQLFactory());
			System.out.println("entro");
			ControladorVista controladorVista = new ControladorVista(vista, modelo);
			ControladorAgregar controladorAgregar = new ControladorAgregar(vista, modelo);
			ControladorEditar controladorEditar = new ControladorEditar(vista, modelo);
			ControladorABM_Tipo controladorTipo = new ControladorABM_Tipo(vista, modelo);
			ControladorABM_PPL controladorPPL = new ControladorABM_PPL(vista, modelo);
	
			
			controladorVista.inicializar();
			controladorAgregar.inicializar();
			controladorEditar.inicializar();
			controladorTipo.inicializar();
			controladorPPL.inicializar();
		}		
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
