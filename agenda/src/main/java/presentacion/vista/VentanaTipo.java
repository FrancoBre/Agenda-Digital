package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;


public class VentanaTipo extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaTipo INSTANCE;
	private JPanel panel;
	private JTextField nuevaEtiquetaTextField;
	private JComboBox<String> opcionesComboBox;
	private JComboBox<String> tiposComboBox;
	private JButton btnActionTipo;

	
	public static VentanaTipo getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaTipo(); 	
			return new VentanaTipo();
		}
		else
			return INSTANCE;
	}

	public VentanaTipo() 
	{
		super();
		super.setTitle("Administrar etiquetas");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 11,  464, 99);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel datosContactoLabel = new JLabel("Tipo de contacto");
		datosContactoLabel.setBounds(10, 11, 264, 14);
		panel.add(datosContactoLabel);
		
		JLabel nuevaEtiquetaLabel = new JLabel("Nombre");
		nuevaEtiquetaLabel.setBounds(10, 36, 46, 14);
		panel.add(nuevaEtiquetaLabel);
		
		opcionesComboBox = new JComboBox<String>();
		opcionesComboBox.setBounds(300, 32, 154, 22);
		panel.add(opcionesComboBox);
		
		nuevaEtiquetaTextField = new JTextField();
		nuevaEtiquetaTextField.setBounds(66, 33, 105, 20);
		panel.add(nuevaEtiquetaTextField);
		nuevaEtiquetaTextField.setColumns(10);
		
		tiposComboBox = new JComboBox<String>();
		tiposComboBox.setBounds(172, 32, 118, 22);
		panel.add(tiposComboBox);
		
		btnActionTipo = new JButton("accion");
		btnActionTipo.setBounds(300, 65, 154, 23);
		panel.add(btnActionTipo);
		
		this.setVisible(false);
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
		this.dispose();
	}

//************* GETTER / SETTER *************
	
	public JComboBox<String> getOpcionesComboBox() {
		return opcionesComboBox;
	}

	public void setOpcionesComboBox(JComboBox<String> opcionesComboBox) {
		this.opcionesComboBox = opcionesComboBox;
	}

	public JComboBox<String> getTiposComboBox() {
		return tiposComboBox;
	}

	public void setTiposComboBox(JComboBox<String> tiposComboBox) {
		this.tiposComboBox = tiposComboBox;
	}

	public JButton getBtnActionTipo() {
		return btnActionTipo;
	}

	public void setBtnActionTipo(JButton btnActionTipo) {
		this.btnActionTipo = btnActionTipo;
	}

	public JTextField getNuevaEtiquetaTextField() {
		return nuevaEtiquetaTextField;
	}

	public void setNuevaEtiquetaTextField(JTextField nuevaEtiquetaTextField) {
		this.nuevaEtiquetaTextField = nuevaEtiquetaTextField;
	}
	
	
}

