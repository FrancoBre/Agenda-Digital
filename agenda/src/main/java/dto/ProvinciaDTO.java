package dto;

public class ProvinciaDTO {
	private int idProvincia;
	private String nombreProvincia;
	private int pais;
	
	public ProvinciaDTO(int idProvincia, String nombreProvincia, int pais) {
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.pais = pais;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}
		
	
}
