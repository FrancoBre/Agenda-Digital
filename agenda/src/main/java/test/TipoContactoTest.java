package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.Test;

import dto.TipoContactoDTO;
import dto.TipoContactoDTO.Tipo;
import modelo.Agenda;

public class TipoContactoTest {

    @Test
    public void getNombreTipoContactoTest() {
	List<TipoContactoDTO> tiposContacto = new ArrayList<TipoContactoDTO>();
	tiposContacto.add(new TipoContactoDTO(1, Tipo.Familiar));
	tiposContacto.add(new TipoContactoDTO(2, Tipo.Amigo));
	tiposContacto.add(new TipoContactoDTO(3, Tipo.Trabajo));
	tiposContacto.add(new TipoContactoDTO(4, Tipo.Futbol));
	tiposContacto.add(new TipoContactoDTO(5, Tipo.Amante));

	List<String> tiposString = new ArrayList<String>();
	tiposString.add("Familiar");
	tiposString.add("Amigo");
	tiposString.add("Trabajo");
	tiposString.add("Futbol");
	tiposString.add("Amante");

	assertEquals(Agenda.getNombreTipoContacto(tiposContacto), tiposString);
    }

}
