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

    public PersonaDTO(int idPersona, String nombre, String telefono) {
	this.idPersona = idPersona;
	this.nombre = nombre;
	this.telefono = telefono;
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

    @Override
    public String toString() {
	return "PersonaDTO [idPersona=" + idPersona + ", nombre=" + nombre + ", telefono=" + telefono + ", email="
		+ email + ", nacimiento=" + nacimiento + ", domicilio=" + domicilio + ", tipoContacto=" + tipoContacto
		+ "]";
    }

}
