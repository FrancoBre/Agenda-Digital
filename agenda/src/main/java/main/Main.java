package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.*;
import presentacion.vista.VistaInicio;

public class Main {
    public static void main(String[] args) {
    VistaInicio vistainicio = new VistaInicio();	
	Agenda modelo = new Agenda(new DAOSQLFactory());
	
	ControladorVistaInicio controladorInicio = new ControladorVistaInicio(vistainicio, modelo);
	ControladorVista controladorVista = new ControladorVista(controladorInicio.vista, modelo);
	ControladorAgregar controladorAgregar = new ControladorAgregar(controladorInicio.vista, modelo);
	ControladorEditar controladorEditar = new ControladorEditar(controladorInicio.vista, modelo);
	ControladorABM_Tipo controladorTipo = new ControladorABM_Tipo(controladorInicio.vista, modelo);
	ControladorABM_PPL controladorPPL = new ControladorABM_PPL(controladorInicio.vista, modelo);
	
	controladorInicio.inicializar();
	controladorVista.inicializar();
	controladorAgregar.inicializar();
	controladorEditar.inicializar();
	controladorTipo.inicializar();
	controladorPPL.inicializar();
    }
}
