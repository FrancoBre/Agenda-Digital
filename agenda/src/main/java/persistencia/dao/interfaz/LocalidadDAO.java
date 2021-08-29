package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {
	public List<LocalidadDTO> readByProvincia(int idProvincia);
		
	public List<LocalidadDTO> readByNombreProvincia(String Provincia);

	public int readIdByNombre(String nombre);
	
	public boolean insert(LocalidadDTO nuevaLocalidad);

	public boolean update(LocalidadDTO editLocalidad);

	public boolean delete(int idLocalidad);

	public int getIdProvinciaByNombre(String nombreLocalidad);
	
	
}
