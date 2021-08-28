package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.*;
import presentacion.vista.Vista;

public class Main {
    public static void main(String[] args) {
	Vista vista = new Vista();
	Agenda modelo = new Agenda(new DAOSQLFactory());
	
	modelo.cargarDatos();
	
	ControladorVista controladorVista = new ControladorVista(vista, modelo);
	ControladorAgregar controladorAgregar = new ControladorAgregar(vista, modelo);
	ControladorEditar controladorEditar = new ControladorEditar(vista, modelo);
	ControladorABM_Tipo controladorTipo = new ControladorABM_Tipo(vista, modelo);

	controladorVista.inicializar();
	controladorAgregar.inicializar();
	controladorEditar.inicializar();
	controladorTipo.inicializar();
    }
}
