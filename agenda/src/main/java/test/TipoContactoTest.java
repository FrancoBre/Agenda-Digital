package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.Test;

import dto.TipoContacto;
import modelo.Agenda;

public class TipoContactoTest {

    @Test
    public void getNombreTipoContactoTest() {
	EnumMap<TipoContacto, Integer> tiposContacto = new EnumMap<TipoContacto, Integer>(TipoContacto.class);
	tiposContacto.put(TipoContacto.Familiar, 1);
	tiposContacto.put(TipoContacto.Amigo, 2);
	tiposContacto.put(TipoContacto.Trabajo, 3);
	tiposContacto.put(TipoContacto.Futbol, 4);
	tiposContacto.put(TipoContacto.Amante, 5);

	List<String> tiposString = new ArrayList<String>();
	tiposString.add("Familiar");
	tiposString.add("Amigo");
	tiposString.add("Trabajo");
	tiposString.add("Futbol");
	tiposString.add("Amante");

	assertEquals(Agenda.getNombreTipoContacto(tiposContacto), tiposString);
    }

}
