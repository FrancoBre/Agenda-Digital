package main;

import configuracion.Configuracion;
import modelo.Agenda;
import modelo.Login;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.*;
import presentacion.vista.VistaInicio;

public class Main {
    public static void main(String[] args) {

	Configuracion.conexionRealizada = false;

	VistaInicio vistainicio = new VistaInicio();
	Login login = new Login();
	ControladorVistaInicio controladorInicio = new ControladorVistaInicio(vistainicio, login);
	controladorInicio.inicializar();

	while (true) {
	    
	    if (Configuracion.conexionRealizada) {

		Agenda modelo = new Agenda(new DAOSQLFactory());
		ControladorVista controladorVista = new ControladorVista(controladorInicio.vista, modelo);
		ControladorAgregar controladorAgregar = new ControladorAgregar(controladorInicio.vista, modelo);
		ControladorEditar controladorEditar = new ControladorEditar(controladorInicio.vista, modelo);
		ControladorABM_Tipo controladorTipo = new ControladorABM_Tipo(controladorInicio.vista, modelo);
		ControladorABM_PPL controladorPPL = new ControladorABM_PPL(controladorInicio.vista, modelo);

		controladorVista.inicializar();
		controladorAgregar.inicializar();
		controladorEditar.inicializar();
		controladorTipo.inicializar();
		controladorPPL.inicializar();
		
		break;
	    }
	}
    }
}
