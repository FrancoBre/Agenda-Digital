package persistencia.dao.interfaz;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaDAO {
	public List<ProvinciaDTO> readByPais(int idPais);
	
	public boolean insert(ProvinciaDTO nuevaProvincia);
	
	public boolean update(ProvinciaDTO editProvincia);
	public boolean delete(int idProvincia);
	public String getNombreById(int idProvincia);

	public int getIdProvinciaByNombre(String nombrePais);
}
