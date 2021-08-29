package persistencia.dao.interfaz;

import java.util.List;

import dto.PaisDTO;

public interface PaisDAO {
	public List<PaisDTO> readAll();
	
	public boolean insert(String nombrePais);
	
	public boolean update(PaisDTO editPais);
	
	public boolean delete(int idPais);

	public int getIdPaisByNombre(String nombrePais);
	
}
