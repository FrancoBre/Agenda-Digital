package persistencia.dao.interfaz;

import java.util.List;

import dto.DomicilioDTO;

public interface DomicilioDAO {

    public List<DomicilioDTO> readAll();

    public boolean insert(DomicilioDTO domicilio);

    public boolean update(DomicilioDTO domicilio);

    public int readMaxId();

    /*
     * public String getNombreDomicilioById(int id);
     * 
     * public String getCalleAlturaById(int id);
     */
}
