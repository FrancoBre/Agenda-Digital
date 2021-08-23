package presentacion.vista;

import javax.swing.JButton;
import dto.PersonaDTO;

public class VentanaPersonaEditar extends VentanaPersona
{
    private static final long serialVersionUID = 1L;
    private JButton btnEditarPersona;
    private static VentanaPersonaEditar INSTANCE;

    
    public static VentanaPersonaEditar getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new VentanaPersonaEditar();     
            return new VentanaPersonaEditar();
        }
        else
            return INSTANCE;
    }

    private VentanaPersonaEditar() 
    {
        super();
        super.setTitle("Editar Contacto");       
    
        btnEditarPersona = new JButton("Editar");
        btnEditarPersona.setBounds(208, 493, 89, 29);
        super.getPanel().add(btnEditarPersona);
                
        this.setVisible(false);
    }
    
    public JButton getBtnEditarPersona() 
    {
        return btnEditarPersona;
    }
    
    public void setCampos(PersonaDTO p) {
		super.getNombreInput().setText(p.getNombre());
		super.getTelefonoInput().setText(p.getTelefono());
	}
}
