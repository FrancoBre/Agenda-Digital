package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreInput;
	private JTextField telefonoInput;
    private JTextField emailInput;
    private JTextField nacimientoInput;
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
	private static VentanaPersona INSTANCE;
	private JPanel panel;
	private JButton btnAction;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	public VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11,  307, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		nombreInput = new JTextField();
		nombreInput.setBounds(133, 8, 164, 20);
		panel.add(nombreInput);
		nombreInput.setColumns(10);
		
		telefonoInput = new JTextField();
		telefonoInput.setBounds(133, 49, 164, 20);
		panel.add(telefonoInput);
		telefonoInput.setColumns(10);
				
		JLabel label = new JLabel("Email");
        label.setBounds(10, 93, 49, 14);
        getPanel().add(label);
        
        emailInput = new JTextField();
        emailInput.setBounds(133, 93, 164, 20);
        getPanel().add(emailInput);
        emailInput.setColumns(10);
        
        JLabel nacimientoLabel = new JLabel("Nacimiento");
        nacimientoLabel.setBounds(10, 142, 99, 14);
        getPanel().add(nacimientoLabel);
        
		comboBoxAnio = new JComboBox<Integer>();
		comboBoxAnio.setBounds(133, 140, 60, 22);
		getPanel().add(comboBoxAnio);
		
		comboBoxMes = new JComboBox<Integer>();
		comboBoxMes.setBounds(194, 140, 50, 22);
		getPanel().add(comboBoxMes);
		
		comboBoxDia = new JComboBox<Integer>() ;
		comboBoxDia.setBounds(246, 140, 50, 22);
		getPanel().add(comboBoxDia);
        
        tipoContactoLabel = new JLabel("Tipo de contacto");
        tipoContactoLabel.setBounds(10, 193, 99, 14);
        getPanel().add(tipoContactoLabel);
        
        tipoContactoInput = new JComboBox<String>();
        tipoContactoInput.setBounds(133, 189, 164, 22);
        getPanel().add(tipoContactoInput);
        
        JLabel datosDomicilioLabel = new JLabel("Datos de domicilio");
        datosDomicilioLabel.setBounds(10, 242, 123, 14);
        getPanel().add(datosDomicilioLabel);
        
        JLabel calleLabel = new JLabel("Calle");
        calleLabel.setBounds(10, 281, 49, 14);
        getPanel().add(calleLabel);
        
        calleInput = new JTextField();
        calleInput.setBounds(133, 275, 164, 20);
        getPanel().add(calleInput);
        calleInput.setColumns(10);
        
        JLabel alturaLabel = new JLabel("Altura");
        alturaLabel.setBounds(10, 328, 49, 14);
        getPanel().add(alturaLabel);
        
        alturaInput = new JTextField();
        alturaInput.setBounds(69, 325, 58, 20);
        getPanel().add(alturaInput);
        alturaInput.setColumns(10);
        
        JLabel pisoLabel = new JLabel("Piso");
        pisoLabel.setBounds(137, 328, 26, 14);
        getPanel().add(pisoLabel);
        
        pisoInput = new JTextField();
        pisoInput.setBounds(173, 325, 37, 20);
        getPanel().add(pisoInput);
        pisoInput.setColumns(10);
        
        JLabel deptoLabel = new JLabel("Depto");
        deptoLabel.setBounds(220, 328, 37, 14);
        getPanel().add(deptoLabel);
        
        deptoInput = new JTextField();
        deptoInput.setBounds(256, 325, 41, 20);
        getPanel().add(deptoInput);
        deptoInput.setColumns(10);
        
        JLabel paisLabel = new JLabel("Pais");
        paisLabel.setBounds(10, 368, 49, 14);
        getPanel().add(paisLabel);
        
        paisInput = new JComboBox<String>();
        paisInput.setBounds(133, 364, 164, 22);
        getPanel().add(paisInput);        
        
        
        JLabel provinciaLabel = new JLabel("Provincia");
        provinciaLabel.setBounds(10, 413, 49, 14);
        getPanel().add(provinciaLabel);
        
        provinciaInput = new JComboBox<String>();
        provinciaInput.setBounds(133, 409, 164, 22);
        getPanel().add(provinciaInput);
        
        JLabel localidadLabel = new JLabel("Localidad");
        localidadLabel.setBounds(10, 455, 49, 14);
        getPanel().add(localidadLabel);
        
        localidadInput = new JComboBox<String>();
        localidadInput.setBounds(133, 451, 164, 22);
        getPanel().add(localidadInput);

        btnAction = new JButton("");
        btnAction.setBounds(208, 493, 89, 29);
        getPanel().add(btnAction);
        
        
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

	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}

	public void cerrar()
	{
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

	public JTextField getEmailInput() {
		return emailInput;
	}

	public void setEmailInput(JTextField emailInput) {
		this.emailInput = emailInput;
	}

	public JTextField getNacimientoInput() {
		return nacimientoInput;
	}

	public void setNacimientoInput(JTextField nacimientoInput) {
		this.nacimientoInput = nacimientoInput;
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
	
    public JButton getBtnAction() 
    {
        return btnAction;
    }
	
}
