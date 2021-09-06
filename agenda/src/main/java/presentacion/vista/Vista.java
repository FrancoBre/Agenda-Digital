package presentacion.vista;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;

import javax.swing.JButton;

/*import persistencia.conexion.Conexion;*/
import persistencia.conexion.Conexion;

public class Vista extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar; //*
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnABM_tipo;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido","Telefono","Email","Nacimiento","Calle", "Altura", "Piso", "Depto", "Localidad", "Provincia", "Pais", "Etiqueta"};
	private JButton btnABM_PPL;
	private static Vista INSTANCE;
	
	
	public static Vista getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new Vista(); 	
			return new Vista();
		}
		else
			return INSTANCE;
	}
	
	public Vista() 
	{
		super();
		
		this.setTitle("Agenda ");
		this.setBounds(100, 100, 1200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1190, 262);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 1160, 182);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);
		
		btnABM_tipo = new JButton("ABM Tipo");
		btnABM_tipo.setBounds(406, 228, 89, 23);
		panel.add(btnABM_tipo);
		
		btnABM_PPL = new JButton("Administrar Paises");
		btnABM_PPL.setBounds(505, 228, 178, 23);
		panel.add(btnABM_PPL);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}
	
	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public JButton getBtnAMB_tipo() {
		return btnABM_tipo;
	}
	
	public JButton getBtnAMB_PPL() {
		return btnABM_PPL;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla)
		{
			String nombre = p.getNombrePersona();
			String telefono = p.getTelefono();
			String email = p.getEmail();
			String nacimiento = p.getNacimiento().toString();			
			String calle = p.getCalle();
			String altura = p.getAltura();
			String piso = p.getPiso();
			String depto = p.getDepto();
			String localidad = p.getLocalidad();
			String provincia = p.getProvincia();
			String pais = p.getPais();
			String Etiqueta = p.getEtiqueta();
			
			Object[] fila = {nombre, telefono, email, nacimiento, calle, altura, piso, depto, localidad, provincia, pais, Etiqueta};
			this.getModelPersonas().addRow(fila);
		}
	}
}
