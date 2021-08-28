package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

	List<String> tiposString = new ArrayList<String>();
	tiposString.add("Familiar");
	tiposString.add("Amigo");
	tiposString.add("Trabajo");
	tiposString.add("Futbol");

	assertEquals(Agenda.getNombreTipoContacto(tiposContacto), tiposString);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void tipoContactoConstructorConStringFailTest() {
	@SuppressWarnings("unused")
	TipoContactoDTO tipo = new TipoContactoDTO(1, "Zumba");
    }
    
    @Test
    public void tipoContactoConstructorConStringTest1() {
	TipoContactoDTO tipo = new TipoContactoDTO(1, "Amigo");
	assertEquals(tipo.getTipoContacto(), Tipo.Amigo);
    }
    
    @Test
    public void tipoContactoConstructorConStringTest2() {
	TipoContactoDTO tipo = new TipoContactoDTO(1, "Familiar");
	assertEquals(tipo.getTipoContacto(), Tipo.Familiar);
    }

}
