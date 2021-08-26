package persistencia.dao.interfaz;

import java.util.EnumMap;

import dto.TipoContacto;

public interface TipoContactoDAO {
    public EnumMap<TipoContacto, Integer> readAll();

    /*
     * public int readIdByNombre(String nombre);
     * 
     * public String readNombreById(int id);
     */
}
