package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {

    public List<LocalidadDTO> readAll();

    /*
     * public List<LocalidadDTO> readByProvincia(int idProvincia);
     * 
     * public String getNombreProvincia(int idLocalidad);
     * 
     * public List<LocalidadDTO> readByNombreProvincia(String Provincia);
     * 
     * public int readIdByNombre(String nombre);
     */

}
