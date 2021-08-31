package dto;

public class TipoContactoDTO {
	private int idTipo_contacto;
	private String tipo;
	
	public TipoContactoDTO(int idTipo_contacto, String tipo) {
		this.idTipo_contacto = idTipo_contacto;
		this.tipo = tipo;
	}

	public int getIdTipo_contacto() {
		return idTipo_contacto;
	}

	public void setIdTipo_contacto(int idTipo_contacto) {
		this.idTipo_contacto = idTipo_contacto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
