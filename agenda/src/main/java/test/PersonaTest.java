package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;

public class PersonaTest {

    @Test
    public void wireTest() {
	List<PersonaDTO> personas = new ArrayList<PersonaDTO>();  
	List<PaisDTO> paises = new ArrayList<PaisDTO>(); 
	List<ProvinciaDTO> provincias = new ArrayList<ProvinciaDTO>();
	List<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
	List<TipoContactoDTO> tiposContacto = new ArrayList<TipoContactoDTO>();
	
	DomicilioDTO domicilio = new DomicilioDTO(1, "Plus Ultra", "6271", "1", "1", 1);
	
	personas.add(new PersonaDTO(1, "Franco", "1135760584", "franco@gmail.com", null, domicilio, 1));
	paises.add(new PaisDTO(1, "Argentina"));
	provincias.add(new ProvinciaDTO(1, "Buenos Aires", 1));
	localidades.add(new LocalidadDTO(1, "Moreno", 1, 1));
	tiposContacto.add(new TipoContactoDTO(1, "Amigo"));
	
	Agenda.wire(personas, paises, provincias, localidades, tiposContacto);
	
	assertEquals(provincias.get(0).getPais(), paises.get(0));
	assertEquals(localidades.get(0).getPais(), paises.get(0));
	assertEquals(localidades.get(0).getProvincia(), provincias.get(0));
	assertEquals(personas.get(0).getTipoContacto(), tiposContacto.get(0));
    }

}
