package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.EnumMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import modelo.*;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersonaAgregar;
import presentacion.vista.VentanaPersonaEditar;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class Controlador  {
    private Vista vista;
    private VentanaPersonaAgregar ventanaPersonaAgregar;
    private VentanaPersonaEditar ventanaPersonaEditar;
    private Agenda agenda;
    private int[] filasSeleccionadas = null;
    /*
     * Estas listas se hacen static para que puedan ser accedidas desde los otros
     * controladores
     */
    public static List<PersonaDTO> personasEnTabla;
    public static List<PaisDTO> paises;
    public static List<ProvinciaDTO> provincias;
    public static List<LocalidadDTO> localidades;
    public static List<TipoContactoDTO> tiposContacto;
    public static List<DomicilioDTO> domicilios;

    /*
     * public Controlador(Vista vista, Agenda agenda) { this.vista = vista;
     * this.agenda = agenda; this.vista.getBtnAgregar().addActionListener(a ->
     * ventanaAgregarPersona(a)); // this.vista.getBtnEditar().addActionListener(e
     * -> ventanaEditarPersona(e)); // *
     * this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
     * this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
     * 
     * this.ventanaPersonaAgregar = VentanaPersonaAgregar.getInstance(); //
     * this.ventanaPersonaAgregar.getBtnAction().addActionListener(p ->
     * guardarPersona(p));
     * 
     * this.ventanaPersonaEditar = VentanaPersonaEditar.getInstance(); //
     * this.ventanaPersonaEditar.getBtnAction().addActionListener(ep ->
     * editarPersona(ep));
     * 
     * this.ventanaPersonaAgregar.getComboBoxMes().addActionListener(e ->
     * agregarD(e)); this.ventanaPersonaEditar.getComboBoxMes().addActionListener(e
     * -> agregarDEdit(e));
     * 
     * this.ventanaPersonaAgregar.getPaisInput().addActionListener(combobox ->
     * cambioItemsP(combobox)); this.ventanaPersonaAgregar.getProvinciaInput()
     * .addActionListener(comboLocalidad -> cambioItemsL(comboLocalidad));
     * this.ventanaPersonaEditar.getPaisInput().addActionListener(combobox ->
     * cambioItemsPEdit(combobox)); this.ventanaPersonaEditar.getProvinciaInput()
     * .addActionListener(comboLocalidad -> cambioItemsLEdit(comboLocalidad));
     * 
     * addComboboxItems(); }
     */

    public void inicializar() {
	this.refrescarTabla();
	this.cargarDatos();
	this.vista.show();
    }

    private void refrescarTabla() {
	Controlador.personasEnTabla = agenda.obtenerPersonas();
	this.vista.llenarTabla(Controlador.personasEnTabla);
    }

    /*
     * Busca los paises, provincias, localidades y tipos de contactos de la base de
     * datos y los guarda en listas
     */
    private void cargarDatos() {
	Controlador.paises = agenda.obtenerPaises();
	Controlador.provincias = agenda.obtenerProvincias();
	Controlador.localidades = agenda.obtenerLocalidades();
	Controlador.tiposContacto = agenda.obtenerTiposContacto();
	Controlador.domicilios = agenda.obtenerDomicilios();

	Agenda.wire(personasEnTabla, paises, provincias, localidades, tiposContacto, domicilios);
    }

    /*
     * private void addComboboxItems() {
     * addPaisesItems(agenda.getNombrePaises(paises));
     * addTipoContacto(Agenda.getNombreTipoContacto(tiposContacto));
     * addIntegerComboBox(agenda.getYears(), Fecha.getMonths()); llenarDias(31); }
     * 
     * private void ventanaAgregarPersona(ActionEvent a) {
     * this.ventanaPersonaAgregar.mostrarVentana(); }
     * 
     * 
     * private void guardarPersona(ActionEvent p) { if
     * (!ventanaPersonaAgregar.validarRequeridos()) { // crear una ventana con
     * mensaje de campos vacios } else { String nombre =
     * this.ventanaPersonaAgregar.getNombreInput().getText(); String tel =
     * this.ventanaPersonaAgregar.getTelefonoInput().getText(); String email =
     * this.ventanaPersonaAgregar.getEmailInput().getText(); String nacimiento =
     * this.ventanaPersonaAgregar.getNacimientoInput().getText(); String
     * tipoContacto = (String)
     * this.ventanaPersonaAgregar.getTipoContactoInput().getSelectedItem();
     * 
     * String calle = this.ventanaPersonaAgregar.getCalleInput().getText(); String
     * altura = this.ventanaPersonaAgregar.getAlturaInput().getText(); String
     * pisoStr = this.ventanaPersonaAgregar.getPisoInput().getText(); int piso =
     * Integer.parseInt(pisoStr.trim()); String deptoStr =
     * this.ventanaPersonaAgregar.getDeptoInput().getText(); int depto =
     * Integer.parseInt(deptoStr.trim()); String localidad = (String)
     * this.ventanaPersonaAgregar.getLocalidadInput().getSelectedItem();
     * 
     * // Estos datos tendrían que venir de la eleccion del usuario en la vista, y
     * // corresponden al id de cada entidad int idLocalidad =
     * this.agenda.getIdLocalidadByNombre(localidad); int idTipoContacto =
     * this.agenda.getIdTipoContactoByNombre(tipoContacto);
     * 
     * int idDomicilio = this.agenda.getDomicilioMaxId() + 1;
     * 
     * DomicilioDTO nuevoDomicilio = new DomicilioDTO(idDomicilio, calle, altura,
     * piso, depto, idLocalidad);
     * 
     * PersonaDTO nuevaPersona;
     * 
     * nuevaPersona = null;
     * 
     * try { nuevaPersona = new PersonaDTO(this.agenda.getPersonaMaxId() + 1,
     * nombre, tel, email, parseNacimiento(nacimiento), idDomicilio,
     * idTipoContacto); } catch (ParseException e) { e.printStackTrace(); }
     * 
     * this.agenda.agregarDomicilio(nuevoDomicilio);
     * 
     * this.agenda.agregarPersona(nuevaPersona);
     * 
     * } this.refrescarTabla(); this.ventanaPersonaAgregar.cerrar(); }
     * 
     * private java.sql.Date parseNacimiento(String nacimiento) throws
     * ParseException { /* SimpleDateFormat format = new
     * SimpleDateFormat("yyyyMMdd"); Date parsed = format.parse(nacimiento);
     * java.sql.Date date = new java.sql.Date(parsed.getTime());
     * 
     * // nacimiento.replaceAll("\\s+","-"); SimpleDateFormat formato = new
     * SimpleDateFormat("yyyy MM dd");
     * 
     * Date date = formato.parse(nacimiento); java.sql.Date sqlDate = new
     * java.sql.Date(date.getTime());return sqlDate; }
     * 
     * private void ventanaEditarPersona(ActionEvent e) { if
     * (this.vista.getTablaPersonas().getSelectedRows().length == 1) {
     * filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
     * this.ventanaPersonaEditar.setCampos(this.personasEnTabla.get(
     * filasSeleccionadas[0])); this.ventanaPersonaEditar.mostrarVentana(); } }
     * 
     * private void editarPersona(ActionEvent ep) { if
     * (!ventanaPersonaEditar.validarRequeridos()) { // crear una ventana con
     * mensaje de campos vacios } else { int idPersona =
     * this.personasEnTabla.get(filasSeleccionadas[0]).getIdPersona(); int
     * idDomicilio =
     * this.personasEnTabla.get(filasSeleccionadas[0]).getDomicilio().getIdDomicilio
     * ();
     * 
     * String nombre = this.ventanaPersonaEditar.getNombreInput().getText(); String
     * tel = this.ventanaPersonaEditar.getTelefonoInput().getText(); String email =
     * this.ventanaPersonaEditar.getEmailInput().getText(); String nacimiento =
     * this.ventanaPersonaEditar.getNacimientoInput().getText(); String tipoContacto
     * = (String)
     * this.ventanaPersonaEditar.getTipoContactoInput().getSelectedItem();
     * 
     * String calle = this.ventanaPersonaEditar.getCalleInput().getText(); String
     * altura = this.ventanaPersonaEditar.getAlturaInput().getText(); String pisoStr
     * = this.ventanaPersonaEditar.getPisoInput().getText(); int piso =
     * Integer.parseInt(pisoStr.trim()); String deptoStr =
     * this.ventanaPersonaEditar.getDeptoInput().getText(); int depto =
     * Integer.parseInt(deptoStr.trim()); String localidad = (String)
     * this.ventanaPersonaEditar.getLocalidadInput().getSelectedItem();
     * 
     * // unused String pais = (String)
     * this.ventanaPersonaEditar.getPaisInput().getSelectedItem(); String provincia
     * = (String) this.ventanaPersonaEditar.getProvinciaInput().getSelectedItem();
     * 
     * // Estos datos tendrían que venir de la eleccion del usuario en la vista, y
     * // corresponden al id de cada entidad int idLocalidad =
     * this.agenda.getIdLocalidadByNombre(localidad); int idTipoContacto =
     * this.agenda.getIdTipoContactoByNombre(tipoContacto);
     * 
     * DomicilioDTO domicilioEditado = new DomicilioDTO(idDomicilio, calle, altura,
     * piso, depto, idLocalidad);
     * 
     * this.agenda.editarDomicilio(domicilioEditado);
     * 
     * PersonaDTO personaEditada;
     * 
     * personaEditada = null;
     * 
     * try { personaEditada = new PersonaDTO(idPersona, nombre, tel, email,
     * parseNacimiento(nacimiento), idDomicilio, idTipoContacto);
     * 
     * System.out.println(personaEditada.toString()); } catch (ParseException e) {
     * e.printStackTrace(); }
     * 
     * // this.agenda.agregarDomicilio(nuevoDomicilio);
     * 
     * this.agenda.editarPersona(personaEditada); } this.refrescarTabla();
     * this.ventanaPersonaEditar.cerrar(); }
     * 
     * 
     * private void mostrarReporte(ActionEvent r) { ReporteAgenda reporte = new
     * ReporteAgenda(agenda.obtenerPersonas()); reporte.mostrar(); }
     * 
     * public void borrarPersona(ActionEvent s) { filasSeleccionadas =
     * this.vista.getTablaPersonas().getSelectedRows(); for (int fila :
     * filasSeleccionadas) {
     * this.agenda.borrarPersona(Controlador.personasEnTabla.get(fila)); }
     * 
     * this.refrescarTabla(); }
     * 
     * private void agregarD(ActionEvent e) {
     * this.ventanaPersonaAgregar.getComboBoxDia().removeAllItems(); int m =
     * this.ventanaPersonaAgregar.getComboBoxMes()
     * .getItemAt(this.ventanaPersonaAgregar.getComboBoxMes().getSelectedIndex());
     * int y = this.ventanaPersonaAgregar.getComboBoxAnio()
     * .getItemAt(this.ventanaPersonaAgregar.getComboBoxAnio().getSelectedIndex());
     * 
     * int dias = Fecha.numeroDeDiasMes(m, y); llenarDias(dias); }
     * 
     * private void agregarDEdit(ActionEvent e) {
     * this.ventanaPersonaEditar.getComboBoxDia().removeAllItems(); int m =
     * this.ventanaPersonaEditar.getComboBoxMes()
     * .getItemAt(this.ventanaPersonaEditar.getComboBoxMes().getSelectedIndex());
     * int y = this.ventanaPersonaEditar.getComboBoxAnio()
     * .getItemAt(this.ventanaPersonaEditar.getComboBoxAnio().getSelectedIndex());
     * 
     * int dias = Fecha.numeroDeDiasMes(m, y); llenarDias(dias); }
     * 
     * private void llenarDias(int limite) { for (int i = 1; i <= limite; i++) {
     * this.ventanaPersonaAgregar.getComboBoxDia().addItem(i);
     * this.ventanaPersonaEditar.getComboBoxDia().addItem(i); } }
     * 
     * public void addIntegerComboBox(ArrayList<Integer> y, Integer[] m) { for
     * (Integer integer : y) {
     * this.ventanaPersonaAgregar.getComboBoxAnio().addItem(integer);
     * this.ventanaPersonaEditar.getComboBoxAnio().addItem(integer); }
     * this.ventanaPersonaAgregar.getComboBoxMes().setModel(new
     * DefaultComboBoxModel<Integer>(m));
     * this.ventanaPersonaEditar.getComboBoxMes().setModel(new
     * DefaultComboBoxModel<Integer>(m)); }
     * 
     * private void cambioItemsP(ActionEvent c) {
     * this.ventanaPersonaAgregar.getProvinciaInput().removeAllItems(); int id =
     * this.ventanaPersonaAgregar.getPaisInput().getSelectedIndex() + 1;
     * addProvinciasItem(agenda.getNombreProvincia(id)); }
     * 
     * private void cambioItemsL(ActionEvent c) {
     * this.ventanaPersonaAgregar.getLocalidadInput().removeAllItems(); String
     * nombreProvincia = (String)
     * this.ventanaPersonaAgregar.getProvinciaInput().getSelectedItem();
     * addLocalidadItems(agenda.getNombreLocalidad(nombreProvincia)); }
     * 
     * private void cambioItemsPEdit(ActionEvent c) {
     * this.ventanaPersonaEditar.getProvinciaInput().removeAllItems(); int id =
     * this.ventanaPersonaEditar.getPaisInput().getSelectedIndex() + 1;
     * addProvinciasItem(agenda.getNombreProvincia(id)); }
     * 
     * private void cambioItemsLEdit(ActionEvent c) {
     * this.ventanaPersonaEditar.getLocalidadInput().removeAllItems(); String
     * nombreProvincia = (String)
     * this.ventanaPersonaEditar.getProvinciaInput().getSelectedItem();
     * addLocalidadItems(agenda.getNombreLocalidad(nombreProvincia)); }
     * 
     * public void addTipoContacto(List<String> list) { for (String string : list) {
     * this.ventanaPersonaAgregar.getTipoContactoInput().addItem(string);
     * this.ventanaPersonaEditar.getTipoContactoInput().addItem(string); } }
     * 
     * public void addPaisesItems(List<String> list) { for (String string : list) {
     * this.ventanaPersonaAgregar.getPaisInput().addItem(string);
     * this.ventanaPersonaEditar.getPaisInput().addItem(string); } }
     * 
     * public void addProvinciasItem(ArrayList<String> items) { for (String string :
     * items) { this.ventanaPersonaAgregar.getProvinciaInput().addItem(string);
     * this.ventanaPersonaEditar.getProvinciaInput().addItem(string); } }
     * 
     * public void addLocalidadItems(ArrayList<String> items) { for (String string :
     * items) { this.ventanaPersonaAgregar.getLocalidadInput().addItem(string);
     * this.ventanaPersonaEditar.getLocalidadInput().addItem(string); } }
     * 
     * @Override public void actionPerformed(ActionEvent e) { }
     */
}
