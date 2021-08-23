package dto;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private int domicilio;
	private int tipoContacto;

	public PersonaDTO(int idPersona, String nombre, String telefono, int domicilio, int tipoContacto)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.tipoContacto = tipoContacto;
	}
	
	public PersonaDTO(int idPersona, String nombre, String telefono) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public int getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(int domicilio) {
		this.domicilio = domicilio;
	}

	public int getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(int tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
}
