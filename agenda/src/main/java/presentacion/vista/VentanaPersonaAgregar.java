package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaPersonaAgregar extends VentanaPersona
{
    private static final long serialVersionUID = 1L;
    private JButton btnAgregarPersona;
    private static VentanaPersonaAgregar INSTANCE;
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
        super.setTitle("Agregar Contacto");
                
        btnAgregarPersona = new JButton("Agregar");
        btnAgregarPersona.setBounds(208, 493, 89, 29);
        super.getPanel().add(btnAgregarPersona);
                
        this.setVisible(false);
    }
    
    @Override
    public void addPaises(List<String> paises) {
    	setPaisInput(new JComboBox<String>());
		for (String string : paises) {
			super.getPaisInput().addItem(string);
			
			System.out.println(super.getPaisInput().getItemAt(0)+ "2 2");
		}
	}
    
    public JButton getBtnAgregarPersona() 
    {
        return btnAgregarPersona;
    }
}