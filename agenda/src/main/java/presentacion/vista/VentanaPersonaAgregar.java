package presentacion.vista;

import javax.swing.JButton;

import dto.PersonaDTO;

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
		super.setTitle("Editar Contacto");
				
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 92, 89, 23);
		super.getPanel().add(btnAgregarPersona);
				
		this.setVisible(false);
	}
	
	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
	
}

