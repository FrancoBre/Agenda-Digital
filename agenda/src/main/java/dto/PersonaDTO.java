package dto;

import java.sql.Date;

public class PersonaDTO {
    private int idPersona;
    private String nombrePersona;
    private String telefono;
    private String email;
    private String dominioEmail;
    private Date nacimiento;
    private DomicilioDTO domicilioDTO;
    private TipoContactoDTO tipoContacto;
    private int domicilio;
    private int tipo_contacto;
    private int medio_transporte;

    private String calle;
    private String altura;
    private String piso;
    private String depto;
    private String localidad;
    private String provincia;
    private String pais;
    private String Etiqueta;

    public PersonaDTO(int idPersona, String nombrePersona, String telefono, String email, String dominioEmail, Date nacimiento,
	    String calle, String altura, String piso, String depto, String localidad, String provincia, String pais,
	    String Etiqueta, int medio_transporte) {
	this.idPersona = idPersona;
	this.nombrePersona = nombrePersona;
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
	this.medio_transporte = medio_transporte;
    }

    public PersonaDTO(int idPersona, String nombrePersona, String telefono, String email, String dominioEmail, Date nacimiento,
	    DomicilioDTO domicilioDTO, TipoContactoDTO tipoContacto, int medio_transporte) {
	this.idPersona = idPersona;
	this.nombrePersona = nombrePersona;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.domicilioDTO = domicilioDTO;
	this.tipoContacto = tipoContacto;
	this.medio_transporte = medio_transporte;
    }

    public PersonaDTO(int idPersona, String nombrePersona, String telefono, String email, String dominioEmail, Date nacimiento,
	    DomicilioDTO domicilioDTO, int tipo_contacto, int medio_transporte) {
	this.idPersona = idPersona;
	this.nombrePersona = nombrePersona;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.domicilioDTO = domicilioDTO;
	this.tipo_contacto = tipo_contacto;
	this.medio_transporte = medio_transporte;
    }

    public PersonaDTO(int idPersona, String nombrePersona, String telefono, String email, String dominioEmail, Date nacimiento,
	    int domicilio, int tipo_contacto, int medio_transporte) {
	this.idPersona = idPersona;
	this.nombrePersona = nombrePersona;
	this.telefono = telefono;
	this.email = email;
	this.dominioEmail = dominioEmail;
	this.nacimiento = nacimiento;
	this.setDomicilio(domicilio);
	this.setTipo_contacto(tipo_contacto);
	this.medio_transporte = medio_transporte;
    }

    public int getIdPersona() {
	return this.idPersona;
    }

    public void setIdPersona(int idPersona) {
	this.idPersona = idPersona;
    }

    public String getNombrePersona() {
	return this.nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
	this.nombrePersona = nombrePersona;
    }

    public String getTelefono() {
	return this.telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public DomicilioDTO getdomicilioDTO() {
	return domicilioDTO;
    }

    public void setDomicilioDTO(DomicilioDTO domicilioDTO) {
	this.domicilioDTO = domicilioDTO;
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

    public int getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(int domicilio) {
	this.domicilio = domicilio;
    }

    public int getTipo_contacto() {
	return tipo_contacto;
    }

    public void setTipo_contacto(int tipo_contacto) {
	this.tipo_contacto = tipo_contacto;
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

    public int getMedio_transporte() {
	return medio_transporte;
    }

    public void setMedio_transporte(int medio_transporte) {
	this.medio_transporte = medio_transporte;
    }

}
