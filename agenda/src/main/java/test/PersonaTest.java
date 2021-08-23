package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class PersonaTest {
	@Test
	public void parseNacimientoTest() {
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		
		String nacimiento = "13 06 2000";
		LocalDate fecha = LocalDate.of(2000, 06, 13);
		
		assertEquals(fecha, controlador.parseNacimiento(nacimiento));
	}
}
