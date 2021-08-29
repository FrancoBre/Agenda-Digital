package persistencia.dao.interfaz;

import java.util.List;

import dto.MedioTransporteDTO;

public interface MedioTransporteDAO {
    public boolean insert(MedioTransporteDTO MedioTransporte);

    public boolean update(MedioTransporteDTO MedioTransporte);

    public boolean delete(MedioTransporteDTO MedioTransporte);

    public boolean delete(String MedioTransporte);

    public List<MedioTransporteDTO> readAll();

    public int readIdByNombre(String nombre);

    public String readNombreById(int id);
}
