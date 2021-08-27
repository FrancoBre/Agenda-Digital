package dto;

public class TipoContactoDTO {

    public enum Tipo {
	Familiar, Amigo, Trabajo, Futbol, Amante
    }

    private int idTipoContacto;
    private Tipo tipoContacto;

    public TipoContactoDTO(int idTipoContacto, Tipo tipoContacto) {
	this.idTipoContacto = idTipoContacto;
	this.tipoContacto = tipoContacto;
    }

    public TipoContactoDTO(int idTipoContacto, String tipoContacto) {
	this.idTipoContacto = idTipoContacto;
	for (Tipo tipo : Tipo.values()) {
	    if(tipoContacto.equalsIgnoreCase(tipo.name())) {
		this.tipoContacto = Tipo.valueOf(tipoContacto);
		return;
	    }	    
	}
	throw new IllegalArgumentException("El valor ingresado para tipoContacto " + 
	"no corresponde a ningun tipo de contacto cargado en la base de datos");
    }

    public TipoContactoDTO() {

    }

    public int getIdTipoContacto() {
	return idTipoContacto;
    }

    public void setIdTipoContacto(int idTipoContacto) {
	this.idTipoContacto = idTipoContacto;
    }

    public Tipo getTipoContacto() {
	return tipoContacto;
    }

    public void setTipoContacto(Tipo tipoContacto) {
	this.tipoContacto = tipoContacto;
    }
}
