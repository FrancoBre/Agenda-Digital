package dto;

public class TipoContactoDTO {
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
	
}
