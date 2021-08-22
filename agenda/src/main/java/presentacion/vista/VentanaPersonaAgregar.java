package presentacion.vista;

import javax.swing.JButton;

import dto.PersonaDTO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VentanaPersonaAgregar extends VentanaPersona
{
    private static final long serialVersionUID = 1L;
    private JButton btnAgregarPersona;
    private static VentanaPersonaAgregar INSTANCE;
    private JTextField emailInput;
    private JTextField nacimientoInput;
    private JLabel dateFormatLabel;
    private JLabel tipoContactoLabel;
    private JTextField calleInput;
    private JTextField alturaInput;
    private JTextField pisoInput;
    private JTextField deptoInput;
    
    public static VentanaPersonaAgregar getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new VentanaPersonaAgregar();     
            return new VentanaPersonaAgregar();
        }
        else
            return INSTANCE;
    }

    private VentanaPersonaAgregar() 
    {
        super();
        getPanel().setBounds(10, 11, 307, 533);
        
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
        
        nacimientoInput = new JTextField();
        nacimientoInput.setBounds(133, 139, 84, 20);
        getPanel().add(nacimientoInput);
        nacimientoInput.setColumns(10);
        
        dateFormatLabel = new JLabel("DD/MM/YYYY");
        dateFormatLabel.setBounds(227, 142, 70, 14);
        getPanel().add(dateFormatLabel);
        
        tipoContactoLabel = new JLabel("Tipo de contacto");
        tipoContactoLabel.setBounds(10, 193, 99, 14);
        getPanel().add(tipoContactoLabel);
        
        JComboBox tipoContactoInput = new JComboBox();
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
        
        JComboBox paisInput = new JComboBox();
        paisInput.setBounds(133, 364, 164, 22);
        getPanel().add(paisInput);
        
        JLabel provinciaLabel = new JLabel("Provincia");
        provinciaLabel.setBounds(10, 413, 49, 14);
        getPanel().add(provinciaLabel);
        
        JComboBox provinciaInput = new JComboBox();
        provinciaInput.setBounds(133, 409, 164, 22);
        getPanel().add(provinciaInput);
        
        JLabel localidadLabel = new JLabel("Localidad");
        localidadLabel.setBounds(10, 455, 49, 14);
        getPanel().add(localidadLabel);
        
        JComboBox localidadInput = new JComboBox();
        localidadInput.setBounds(133, 451, 164, 22);
        getPanel().add(localidadInput);
        super.setTitle("Editar Contacto");
                
        btnAgregarPersona = new JButton("Agregar");
        btnAgregarPersona.setBounds(208, 493, 89, 29);
        super.getPanel().add(btnAgregarPersona);
                
        this.setVisible(false);
    }
    
    public JButton getBtnAgregarPersona() 
    {
        return btnAgregarPersona;
    }
}