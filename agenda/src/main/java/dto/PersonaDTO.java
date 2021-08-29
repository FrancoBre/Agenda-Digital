package dto;

import java.sql.Date;

public class PersonaDTO {
    private int idPersona;
    private String nombre;
    private String telefono;
    private String email;
    private String dominioEmail;
    private Date nacimiento;
    private DomicilioDTO domicilio;
    private TipoContactoDTO tipoContacto;
    private int idDomicilio;
    private int idTipoContacto;
    private int idMedioTransporte;

    private String calle;
    private String altura;
    private String piso;
    private String depto;
    private String localidad;
    private String provincia;
    private String pais;
    private String Etiqueta;

    public PersonaDTO(int idPersona, String nombre, String telefono, String email, String dominioEmail, Date nacimiento,
	    String calle, String altura, String piso, String depto, String localidad, String provincia, String pais,
	    String Etiqueta, int idMedioTransporte) {
	this.idPersona = idPersona;
	this.nombre = nombre;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.calle = calle;
	this.altura = altura;
	this.piso = piso;
	this.depto = depto;
	this.localidad = localidad;
	this.provincia = provincia;
	this.pais = pais;
	this.Etiqueta = Etiqueta;
	this.idMedioTransporte = idMedioTransporte;
    }

    public PersonaDTO(int idPersona, String nombre, String telefono, String email, String dominioEmail, Date nacimiento,
	    DomicilioDTO domicilio, TipoContactoDTO tipoContacto, int idMedioTransporte) {
	this.idPersona = idPersona;
	this.nombre = nombre;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.domicilio = domicilio;
	this.tipoContacto = tipoContacto;
	this.idMedioTransporte = idMedioTransporte;
    }

    public PersonaDTO(int idPersona, String nombre, String telefono, String email, String dominioEmail, Date nacimiento,
	    DomicilioDTO domicilio, int idTipoContacto, int idMedioTransporte) {
	this.idPersona = idPersona;
	this.nombre = nombre;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.domicilio = domicilio;
	this.idTipoContacto = idTipoContacto;
	this.idMedioTransporte = idMedioTransporte;
    }

    public PersonaDTO(int idPersona, String nombre, String telefono, String email, String dominioEmail, Date nacimiento,
	    int idDomicilio, int idTipoContacto, int idMedioTransporte) {
	this.idPersona = idPersona;
	this.nombre = nombre;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.setIdDomicilio(idDomicilio);
	this.setIdTipoContacto(idTipoContacto);
	this.idMedioTransporte = idMedioTransporte;
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

    //

    public String getCalle() {
	return calle;
    }

    public void setCalle(String calle) {
	this.calle = calle;
    }

    public String getAltura() {
	return altura;
    }

    public void setAltura(String altura) {
	this.altura = altura;
    }

    public String getPiso() {
	return piso;
    }

    public void setPiso(String piso) {
	this.piso = piso;
    }

    public String getDepto() {
	return depto;
    }

    public void setDepto(String depto) {
	this.depto = depto;
    }

    public String getLocalidad() {
	return localidad;
    }

    public void setLocalidad(String localidad) {
	this.localidad = localidad;
    }

    public String getProvincia() {
	return provincia;
    }

    public void setProvincia(String provincia) {
	this.provincia = provincia;
    }

    public String getPais() {
	return pais;
    }

    public void setPais(String pais) {
	this.pais = pais;
    }

    public String getEtiqueta() {
	return Etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
	Etiqueta = etiqueta;
    }

    public String getDominioEmail() {
	return dominioEmail;
    }

    public void setDominioEmail(String dominioEmail) {
	this.dominioEmail = dominioEmail;
    }

    public int getIdMedioTransporte() {
	return idMedioTransporte;
    }

    public void setIdMedioTransporte(int idMedioTransporte) {
	this.idMedioTransporte = idMedioTransporte;
    }

}
