package presentacion.vista;

public class VentanaPersonaAgregar extends VentanaPersona
{
    private static final long serialVersionUID = 1L;
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
        
        super.getBtnAction().setText("Agregar");
                
        this.setVisible(false);
    }

}