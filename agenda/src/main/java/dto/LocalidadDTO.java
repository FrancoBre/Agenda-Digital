package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String nombre;
	private ProvinciaDTO provincia;
	private PaisDTO pais;
	
	public LocalidadDTO(int idLocalidad, String nombre, ProvinciaDTO provincia, PaisDTO pais) {
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

	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}
	
}
