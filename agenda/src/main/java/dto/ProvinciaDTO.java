package dto;

public class ProvinciaDTO {
	private int idProvincia;
	private String nombre;
	private int pais;
	
	public ProvinciaDTO(int idProvincia, String nombre, int pais) {
		this.idProvincia = idProvincia;
		this.nombre = nombre;
		this.pais = pais;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}
}
