package dto;

public class DomicilioDTO {
	private int idDomicilio;
	private String calle;
	private String Altura;
	private int piso;
	private int depto;
	private String Localidad;
	
	public DomicilioDTO(int idDomicilio, String calle, String altura, int piso, int depto, String localidad) {
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		Altura = altura;
		this.piso = piso;
		this.depto = depto;
		Localidad = localidad;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return Altura;
	}

	public void setAltura(String altura) {
		Altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getDepto() {
		return depto;
	}

	public void setDepto(int depto) {
		this.depto = depto;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
}
