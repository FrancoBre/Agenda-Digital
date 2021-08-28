package persistencia.dao.interfaz;

import java.util.List;

import dto.TipoContactoDTO;

public interface TipoContactoDAO {
	
	public boolean insert(TipoContactoDTO tipoContacto);
	
	public boolean update(TipoContactoDTO tipoContacto);
	
	public boolean delete(TipoContactoDTO tipoContacto);
	
	public boolean delete(String tipoContacto);
	
	public List<TipoContactoDTO> readAll();

	public int readIdByNombre(String nombre);
	
	public String readNombreById(int id);
}