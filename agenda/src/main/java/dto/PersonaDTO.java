package dto;

import java.sql.Date;

public class PersonaDTO {
    private int idPersona;
    private String nombre;
    private String telefono;
    private String email;
    private Date nacimiento;
    private DomicilioDTO domicilio;
    private TipoContactoDTO tipoContacto;
    private int idDomicilio;
    private int idTipoContacto;

    public PersonaDTO(int idPersona, String nombre, String telefono, String email, Date nacimiento,
	    DomicilioDTO domicilio, TipoContactoDTO tipoContacto) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.nacimiento = nacimiento;
		this.domicilio = domicilio;
		this.tipoContacto = tipoContacto;
    }

    public PersonaDTO(int idPersona, String nombre, String telefono, String email, Date nacimiento, int idDomicilio,
	    int idTipoContacto) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.nacimiento = nacimiento;
		this.setIdDomicilio(idDomicilio);
		this.setIdTipoContacto(idTipoContacto);
    }

    public int getIdPersona() {
    	return this.idPersona;
    }

    public void setIdPersona(int idPersona) {
    	this.idPersona = idPersona;
    }

    public String getNombre() {
    	return this.nombre;
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public String getTelefono() {
    	return this.telefono;
    }

    public void setTelefono(String telefono) {
    	this.telefono = telefono;
    }

    public DomicilioDTO getDomicilio() {
    	return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
    	this.domicilio = domicilio;
    }

    public TipoContactoDTO getTipoContacto() {
    	return tipoContacto;
    }

    public void setTipoContacto(TipoContactoDTO tipoContacto) {
    	this.tipoContacto = tipoContacto;
    }

    public String getEmail() {
    	return email;
    }

    public void setEmail(String email) {
    	this.email = email;
    }

    public Date getNacimiento() {
    	return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
    	this.nacimiento = nacimiento;
    }

    public int getIdDomicilio() {
    	return idDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
    	this.idDomicilio = idDomicilio;
    }

    public int getIdTipoContacto() {
    	return idTipoContacto;
    }

    public void setIdTipoContacto(int idTipoContacto) {
    	this.idTipoContacto = idTipoContacto;
    }

}
