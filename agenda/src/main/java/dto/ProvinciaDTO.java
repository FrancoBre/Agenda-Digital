package dto;

public class ProvinciaDTO {
    private int idProvincia;
    private String nombre;
    private PaisDTO pais;
    private int idPais;

    public ProvinciaDTO(int idProvincia, String nombre, PaisDTO pais) {
	this.idProvincia = idProvincia;
	this.nombre = nombre;
	this.pais = pais;
    }

    public ProvinciaDTO(int idProvincia, String nombre, int idPais) {
	this.idProvincia = idProvincia;
	this.nombre = nombre;
	this.setIdPais(idPais);
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

    public PaisDTO getPais() {
	return pais;
    }

    public void setPais(PaisDTO pais) {
	this.pais = pais;
    }

    public int getIdPais() {
	return idPais;
    }

    public void setIdPais(int idPais) {
	this.idPais = idPais;
    }
}
