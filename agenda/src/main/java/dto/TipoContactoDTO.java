package dto;

public class TipoContactoDTO {
<<<<<<< HEAD

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

=======
	private int idTipo;
	private String descripcion;
	
	public TipoContactoDTO(int id, String descrip) {
		this.idTipo = id;
		this.descripcion = descrip;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
>>>>>>> branch 'master' of https://github.com/FrancoBre/Agenda-Digital
}
