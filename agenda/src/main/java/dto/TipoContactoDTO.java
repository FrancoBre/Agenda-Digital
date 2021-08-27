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
