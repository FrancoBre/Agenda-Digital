package presentacion.vista;

import dto.PersonaDTO;

public class VentanaPersonaEditar extends VentanaPersona
{
    private static final long serialVersionUID = 1L;
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
    
        super.getBtnAction().setText("Editar");
        
        this.setVisible(false);
    }
    

    
    public void setCampos(PersonaDTO p) {
		super.getNombreInput().setText(p.getNombre());
		super.getTelefonoInput().setText(p.getTelefono());
		super.getPaisInput().setSelectedIndex(super.getPaisInput().getSelectedIndex());
	}
}
