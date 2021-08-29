package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

public class VentanaPersona extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombreInput;
    private JTextField telefonoInput;
    private JTextField leftEmailInput;
    private JTextField rightEmailInput;
    private JTextField domainEmailInput;
    private JComboBox<Integer> comboBoxAnio;
    private JComboBox<Integer> comboBoxMes;
    private JComboBox<Integer> comboBoxDia;
    private JLabel tipoContactoLabel;
    private JTextField calleInput;
    private JTextField alturaInput;
    private JTextField pisoInput;
    private JTextField deptoInput;
    private JComboBox<String> paisInput;
    private JComboBox<String> provinciaInput;
    private JComboBox<String> localidadInput;
    private JComboBox<String> tipoContactoInput;
    private JComboBox<String> medioTransporteInput;
    private static VentanaPersona INSTANCE;
    private JPanel panel;
    private JButton btnAction;
    private JSeparator separator;
    private JSeparator separator_1;
    private JTextField textField;
    private JLabel label_2;
    private JTextField textField_1;

    public static VentanaPersona getInstance() {
	if (INSTANCE == null) {
	    INSTANCE = new VentanaPersona();
	    return new VentanaPersona();
	} else
	    return INSTANCE;
    }

    public VentanaPersona() {
	super();

	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 580, 320);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	panel = new JPanel();
	panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	panel.setBounds(10, 11, 544, 259);
	contentPane.add(panel);
	panel.setLayout(null);

	JLabel datosContactoLabel = new JLabel("Datos Contacto");
	datosContactoLabel.setBounds(10, 11, 264, 14);
	panel.add(datosContactoLabel);

	JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
	lblNombreYApellido.setBounds(10, 52, 99, 14);
	panel.add(lblNombreYApellido);

	JLabel lblTelfono = new JLabel("Telefono");
	lblTelfono.setBounds(10, 77, 113, 14);
	panel.add(lblTelfono);

	nombreInput = new JTextField();
	nombreInput.setBounds(110, 49, 164, 20);
	panel.add(nombreInput);
	nombreInput.setColumns(10);

	telefonoInput = new JTextField();
	telefonoInput.setBounds(110, 74, 164, 20);
	panel.add(telefonoInput);
	telefonoInput.setColumns(10);

	JLabel label = new JLabel("Email");
	label.setBounds(10, 102, 49, 14);
	getPanel().add(label);

	leftEmailInput = new JTextField();
	leftEmailInput.setBounds(110, 99, 69, 20);
	getPanel().add(leftEmailInput);
	leftEmailInput.setColumns(10);

	JLabel nacimientoLabel = new JLabel("Nacimiento");
	nacimientoLabel.setBounds(10, 134, 99, 14);
	getPanel().add(nacimientoLabel);

	comboBoxAnio = new JComboBox<Integer>();
	comboBoxAnio.setBounds(110, 130, 60, 22);
	getPanel().add(comboBoxAnio);

	comboBoxMes = new JComboBox<Integer>();
	comboBoxMes.setBounds(172, 130, 50, 22);
	getPanel().add(comboBoxMes);

	comboBoxDia = new JComboBox<Integer>();
	comboBoxDia.setBounds(224, 130, 50, 22);
	getPanel().add(comboBoxDia);

	tipoContactoLabel = new JLabel("Tipo de contacto");
	tipoContactoLabel.setBounds(10, 160, 99, 14);
	getPanel().add(tipoContactoLabel);

	tipoContactoInput = new JComboBox<String>();
	tipoContactoInput.setBounds(110, 156, 164, 22);
	getPanel().add(tipoContactoInput);

	JLabel datosDomicilioLabel = new JLabel("Datos de domicilio del contacto");
	datosDomicilioLabel.setBounds(307, 11, 224, 14);
	getPanel().add(datosDomicilioLabel);

	JLabel calleLabel = new JLabel("Calle");
	calleLabel.setBounds(307, 52, 49, 14);
	getPanel().add(calleLabel);

	calleInput = new JTextField();
	calleInput.setBounds(367, 49, 164, 20);
	getPanel().add(calleInput);
	calleInput.setColumns(10);

	JLabel alturaLabel = new JLabel("Altura");
	alturaLabel.setBounds(307, 77, 40, 14);
	getPanel().add(alturaLabel);

	alturaInput = new JTextField();
	alturaInput.setBounds(346, 74, 30, 20);
	getPanel().add(alturaInput);
	alturaInput.setColumns(10);

	JLabel pisoLabel = new JLabel("Piso");
	pisoLabel.setBounds(393, 77, 29, 14);
	getPanel().add(pisoLabel);

	pisoInput = new JTextField();
	pisoInput.setBounds(420, 74, 30, 20);
	getPanel().add(pisoInput);
	pisoInput.setColumns(10);

	JLabel deptoLabel = new JLabel("Depto");
	deptoLabel.setBounds(465, 77, 40, 14);
	getPanel().add(deptoLabel);

	deptoInput = new JTextField();
	deptoInput.setBounds(501, 74, 30, 20);
	getPanel().add(deptoInput);
	deptoInput.setColumns(10);

	JLabel paisLabel = new JLabel("Pais");
	paisLabel.setBounds(307, 108, 49, 14);
	getPanel().add(paisLabel);

	paisInput = new JComboBox<String>();
	paisInput.setBounds(367, 104, 164, 22);
	getPanel().add(paisInput);

	JLabel provinciaLabel = new JLabel("Provincia");
	provinciaLabel.setBounds(307, 134, 69, 14);
	getPanel().add(provinciaLabel);

	provinciaInput = new JComboBox<String>();
	provinciaInput.setBounds(367, 130, 164, 22);
	getPanel().add(provinciaInput);

	JLabel localidadLabel = new JLabel("Localidad");
	localidadLabel.setBounds(307, 160, 69, 14);
	getPanel().add(localidadLabel);

	localidadInput = new JComboBox<String>();
	localidadInput.setBounds(367, 156, 164, 22);
	getPanel().add(localidadInput);

	btnAction = new JButton("");
	btnAction.setBounds(367, 219, 164, 29);
	getPanel().add(btnAction);

	separator = new JSeparator();
	separator.setBounds(10, 35, 264, 2);
	panel.add(separator);

	separator_1 = new JSeparator();
	separator_1.setBounds(307, 35, 224, 2);
	panel.add(separator_1);
	
	JLabel label_1 = new JLabel("@");
	label_1.setBounds(182, 102, 12, 15);
	panel.add(label_1);
	
	rightEmailInput = new JTextField();
	rightEmailInput.setBounds(197, 100, 40, 19);
	panel.add(rightEmailInput);
	rightEmailInput.setColumns(10);
	
	label_2 = new JLabel(".");
	label_2.setBounds(240, 102, 5, 15);
	panel.add(label_2);
	
	domainEmailInput = new JTextField();
	domainEmailInput.setBounds(249, 100, 25, 19);
	panel.add(domainEmailInput);
	domainEmailInput.setColumns(10);
	
	JLabel medioTransporteLabel = new JLabel("Medio de transporte preferido");
	medioTransporteLabel.setBounds(10, 186, 99, 15);
	panel.add(medioTransporteLabel);
	
	medioTransporteInput = new JComboBox();
	medioTransporteInput.setBounds(110, 181, 164, 24);
	panel.add(medioTransporteInput);

	this.setVisible(false);
    }

    public JComboBox<Integer> getComboBoxAnio() {
	return comboBoxAnio;
    }

    public void setComboBoxAnio(JComboBox<Integer> comboBoxAnio) {
	this.comboBoxAnio = comboBoxAnio;
    }

    public JComboBox<Integer> getComboBoxMes() {
	return comboBoxMes;
    }

    public void setComboBoxMes(JComboBox<Integer> comboBoxMes) {
	this.comboBoxMes = comboBoxMes;
    }

    public JComboBox<Integer> getComboBoxDia() {
	return comboBoxDia;
    }

    public void setComboBoxDia(JComboBox<Integer> comboBoxDia) {
	this.comboBoxDia = comboBoxDia;
    }

    public void mostrarVentana() {
	this.setVisible(true);
    }

    public JPanel getPanel() {
	return this.panel;
    }

    public void cerrar() {
	this.nombreInput.setText(null);
	this.telefonoInput.setText(null);
	this.dispose();
    }

    public boolean validarRequeridos() {
	if (nombreInput.getText().isBlank()) {
	    return false;
	}
	if (telefonoInput.getText().isBlank()) {
	    return false;
	}
	return true;
    }

    public JTextField getNombreInput() {
	return nombreInput;
    }

    public void setNombreInput(JTextField nombreInput) {
	this.nombreInput = nombreInput;
    }

    public JTextField getTelefonoInput() {
	return telefonoInput;
    }

    public void setTelefonoInput(JTextField telefonoInput) {
	this.telefonoInput = telefonoInput;
    }

    public JTextField getCalleInput() {
	return calleInput;
    }

    public void setCalleInput(JTextField calleInput) {
	this.calleInput = calleInput;
    }

    public JTextField getAlturaInput() {
	return alturaInput;
    }

    public void setAlturaInput(JTextField alturaInput) {
	this.alturaInput = alturaInput;
    }

    public JTextField getPisoInput() {
	return pisoInput;
    }

    public void setPisoInput(JTextField pisoInput) {
	this.pisoInput = pisoInput;
    }

    public JTextField getDeptoInput() {
	return deptoInput;
    }

    public void setDeptoInput(JTextField deptoInput) {
	this.deptoInput = deptoInput;
    }

    public JComboBox<String> getTipoContactoInput() {
	return tipoContactoInput;
    }

    public void setTipoContactoInput(JComboBox<String> tipoContactoInput) {
	this.tipoContactoInput = tipoContactoInput;
    }

    public JComboBox<String> getPaisInput() {
	return paisInput;
    }

    public void setPaisInput(JComboBox<String> paisInput) {
	this.paisInput = paisInput;
    }

    public JComboBox<String> getProvinciaInput() {
	return provinciaInput;
    }

    public void setProvinciaInput(JComboBox<String> provinciaInput) {
	this.provinciaInput = provinciaInput;
    }

    public JComboBox<String> getLocalidadInput() {
	return localidadInput;
    }

    public void setLocalidadInput(JComboBox<String> localidadInput) {
	this.localidadInput = localidadInput;
    }

    public JButton getBtnAction() {
	return btnAction;
    }

    public JTextField getLeftEmailInput() {
        return leftEmailInput;
    }

    public void setLeftEmailInput(JTextField leftEmailInput) {
        this.leftEmailInput = leftEmailInput;
    }

    public JTextField getRightEmailInput() {
        return rightEmailInput;
    }

    public void setRightEmailInput(JTextField rightEmailInput) {
        this.rightEmailInput = rightEmailInput;
    }

    public JTextField getDomainEmailInput() {
        return domainEmailInput;
    }

    public void setDomainEmailInput(JTextField domainEmailInput) {
        this.domainEmailInput = domainEmailInput;
    }

    public JComboBox<String> getMedioTransporteInput() {
        return medioTransporteInput;
    }

    public void setMedioTransporteInput(JComboBox<String> medioTransporteInput) {
        this.medioTransporteInput = medioTransporteInput;
    }
    
}
