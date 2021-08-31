package dto;

public class MedioTransporteDTO {
    private int idMedioTransporte;
    private String nombreMedioTransporte;

    public MedioTransporteDTO(int idMedioTransporte, String nombreMedioTransporte) {
	super();
	this.idMedioTransporte = idMedioTransporte;
	this.nombreMedioTransporte = nombreMedioTransporte;
    }

    public int getIdMedioTransporte() {
	return idMedioTransporte;
    }

    public void setIdMedioTransporte(int idMedioTransporte) {
	this.idMedioTransporte = idMedioTransporte;
    }

    public String getNombreMedioTransporte() {
	return nombreMedioTransporte;
    }

    public void setNombreMedioTransporte(String nombreMedioTransporte) {
	this.nombreMedioTransporte = nombreMedioTransporte;
    }

}
