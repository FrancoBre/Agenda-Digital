package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String nombre;
	private int provincia;
	private int pais;
	
	public LocalidadDTO(int idLocalidad, String nombre, int provincia, int pais) {
		super();
		this.idLocalidad = idLocalidad;
		this.nombre = nombre;
		this.provincia = provincia;
		this.pais = pais;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getProvincia() {
		return provincia;
	}

	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}

	public int getPais() {
		return pais;
	}

	public void setPais(int pais) {
		this.pais = pais;
	}
	
}
