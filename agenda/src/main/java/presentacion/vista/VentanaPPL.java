package presentacion.vista;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;


public class VentanaPPL extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaPPL INSTANCE;
	private JPanel panel;
	private JTextField PaisTextField;
	private JComboBox<String> opcionesComboBox1;
	private JComboBox<String> PaisComboBox;
	private JButton btnActionPAIS;
	private JTextField ProvinciaTextField;
	private JComboBox<String> ProvinciaComboBox;
	private JComboBox<String> opcionesComboBox2;
	private JButton btnActionPROVINCIA;
	private JTextField LocalidadTextField;
	private JComboBox<String> LocalidadComboBox;
	private JComboBox<String> opcionesComboBox3;
	private JButton btnAccionLOCALIDAD;

	
	public static VentanaPPL getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPPL(); 	
			return new VentanaPPL();
		}
		else
			return INSTANCE;
	}

	public VentanaPPL() 
	{
		super();
		super.setTitle("Administrar Paises, Provincias y Localidades");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 754, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 11,  718, 154);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel datosLabel = new JLabel("ABM Paises, Provincias, Localidades");
		datosLabel.setBounds(10, 11, 264, 14);
		panel.add(datosLabel);
		
		JLabel PaisLabel = new JLabel("PAIS");
		PaisLabel.setBounds(10, 40, 46, 14);
		panel.add(PaisLabel);
		
		opcionesComboBox1 = new JComboBox<String>();
		opcionesComboBox1.setBounds(390, 36, 154, 22);
		panel.add(opcionesComboBox1);
		
		PaisTextField = new JTextField();
		PaisTextField.setBounds(80, 37, 150, 20);
		panel.add(PaisTextField);
		PaisTextField.setColumns(10);
		
		PaisComboBox = new JComboBox<String>();
		PaisComboBox.setBounds(231, 36, 150, 22);
		panel.add(PaisComboBox);
		
		btnActionPAIS = new JButton("accion1");
		btnActionPAIS.setBounds(554, 36, 154, 23);
		panel.add(btnActionPAIS);
		
		JLabel ProvinciaLabel = new JLabel("PROVINCIA");
		ProvinciaLabel.setBounds(10, 80, 80, 14);
		panel.add(ProvinciaLabel);
		
		JLabel LocalidadLabel = new JLabel("LOCALIDAD");
		LocalidadLabel.setBounds(10, 120, 70, 14);
		panel.add(LocalidadLabel);
		
		ProvinciaTextField = new JTextField();
		ProvinciaTextField.setColumns(10);
		ProvinciaTextField.setBounds(80, 77, 150, 20);
		panel.add(ProvinciaTextField);
		
		ProvinciaComboBox = new JComboBox<String>();
		ProvinciaComboBox.setBounds(231, 76, 150, 22);
		panel.add(ProvinciaComboBox);
		
		opcionesComboBox2 = new JComboBox<String>();
		opcionesComboBox2.setBounds(390, 76, 154, 22);
		panel.add(opcionesComboBox2);
		
		btnActionPROVINCIA = new JButton("accion2");
		btnActionPROVINCIA.setBounds(554, 76, 154, 23);
		panel.add(btnActionPROVINCIA);
		
		LocalidadTextField = new JTextField();
		LocalidadTextField.setColumns(10);
		LocalidadTextField.setBounds(80, 117, 150, 20);
		panel.add(LocalidadTextField);
		
		LocalidadComboBox = new JComboBox<String>();
		LocalidadComboBox.setBounds(231, 116, 150, 22);
		panel.add(LocalidadComboBox);
		
		opcionesComboBox3 = new JComboBox<String>();
		opcionesComboBox3.setBounds(390, 116, 154, 22);
		panel.add(opcionesComboBox3);
		
		btnAccionLOCALIDAD = new JButton("accion3");
		btnAccionLOCALIDAD.setBounds(554, 116, 154, 23);
		panel.add(btnAccionLOCALIDAD);
		
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
	
	public JTextField getPaisTextField() {
		return PaisTextField;
	}

	public JComboBox<String> getPaisComboBox() {
		return PaisComboBox;
	}

	public JButton getBtnActionPAIS() {
		return btnActionPAIS;
	}

	public JTextField getProvinciaTextField() {
		return ProvinciaTextField;
	}

	public JComboBox<String> getProvinciaComboBox() {
		return ProvinciaComboBox;
	}

	public JButton getBtnActionPROVINCIA() {
		return btnActionPROVINCIA;
	}

	public JTextField getLocalidadTextField() {
		return LocalidadTextField;
	}

	public JComboBox<String> getLocalidadComboBox() {
		return LocalidadComboBox;
	}

	public JButton getBtnAccionLOCALIDAD() {
		return btnAccionLOCALIDAD;
	}

	public JComboBox<String> getOpcionesComboBox1() {
		return opcionesComboBox1;
	}

	public JComboBox<String> getOpcionesComboBox2() {
		return opcionesComboBox2;
	}

	public JComboBox<String> getOpcionesComboBox3() {
		return opcionesComboBox3;
	}
	

}

