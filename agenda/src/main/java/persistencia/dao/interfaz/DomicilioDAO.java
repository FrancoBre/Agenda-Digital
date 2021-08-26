package persistencia.dao.interfaz;

import java.util.List;

import dto.DomicilioDTO;

public interface DomicilioDAO {

    public List<DomicilioDTO> readAll();

    public String getNombreDomicilioById(int id);

    public boolean insert(DomicilioDTO domicilio);

    public boolean delete(DomicilioDTO domicilio);

    public boolean update(DomicilioDTO domicilio);

    public int readMaxId();

    public String getCalleAlturaById(int id);
}
