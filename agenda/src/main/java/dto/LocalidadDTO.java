package dto;

public class LocalidadDTO {
    private int idLocalidad;
    private String nombre;
    private ProvinciaDTO provincia;
    private int idProvincia;
    private PaisDTO pais;
    private int idPais;

    public LocalidadDTO(int idLocalidad, String nombre, ProvinciaDTO provincia, PaisDTO pais) {
	this.idLocalidad = idLocalidad;
	this.nombre = nombre;
	this.provincia = provincia;
	this.pais = pais;
    }

    public LocalidadDTO(int idLocalidad, String nombre, int idProvincia, int idPais) {
	this.idLocalidad = idLocalidad;
	this.nombre = nombre;
	this.setIdProvincia(idProvincia);
	this.setIdPais(idPais);
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

    public int getIdProvincia() {
	return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
	this.idProvincia = idProvincia;
    }

    public int getIdPais() {
	return idPais;
    }

    public void setIdPais(int idPais) {
	this.idPais = idPais;
    }

}
