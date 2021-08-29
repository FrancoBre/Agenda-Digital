package dto;

public class MedioTransporteDTO {
    private int idMedioTransporte;
    private String nombre;

    public MedioTransporteDTO(int idMedioTransporte, String nombre) {
	super();
	this.idMedioTransporte = idMedioTransporte;
	this.nombre = nombre;
    }

    public int getIdMedioTransporte() {
	return idMedioTransporte;
    }

    public void setIdMedioTransporte(int idMedioTransporte) {
	this.idMedioTransporte = idMedioTransporte;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

}
