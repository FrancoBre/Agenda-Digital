package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import persistencia.conexion.Conexion;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;

public class VistaInicio extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JFrame frmAgenda;
    private JTextField User_textField;
    private JPasswordField PassField;
    private JLabel lblMensaje;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JButton btnIngreso;
    private JButton btnExit;
    private JCheckBox SHOWCheckBox;
    private char defaultChar;

    public VistaInicio() {
	super();
	initialize();
    }

    private void initialize() {
	frmAgenda = new JFrame();
	frmAgenda.setTitle("Agenda");
	frmAgenda.setBounds(100, 100, 355, 174);
	frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmAgenda.getContentPane().setLayout(null);

	JPanel panel = new JPanel();
	panel.setBounds(0, 0, 339, 135);
	frmAgenda.getContentPane().add(panel);
	panel.setLayout(null);

	lblMensaje = new JLabel("Ingrese sus datos");
	lblMensaje.setBounds(10, 11, 200, 14);
	panel.add(lblMensaje);

	lblUsuario = new JLabel("Usuario");
	lblUsuario.setBounds(10, 36, 95, 14);
	panel.add(lblUsuario);

	lblPassword = new JLabel("Contrase\u00F1a");
	lblPassword.setBounds(10, 61, 95, 14);
	panel.add(lblPassword);

	User_textField = new JTextField();
	User_textField.setBounds(129, 33, 200, 20);
	panel.add(User_textField);
	User_textField.setColumns(10);

	PassField = new JPasswordField();
	PassField.setBounds(129, 58, 200, 20);
	panel.add(PassField);

	btnIngreso = new JButton("Ingresar");
	btnIngreso.setBounds(176, 104, 150, 23);
	panel.add(btnIngreso);

	btnExit = new JButton("Salir");
	btnExit.setBounds(13, 104, 150, 23);
	panel.add(btnExit);

	SHOWCheckBox = new JCheckBox("Mostrar constrase\u00F1a");
	SHOWCheckBox.setBounds(129, 76, 200, 20);

	// El char default
	defaultChar = PassField.getEchoChar();
	SHOWCheckBox.addActionListener(e -> mostrarPass(e));

	panel.add(SHOWCheckBox);

    }

    public void mostrarPass(ActionEvent e) {
	if (SHOWCheckBox.isSelected()) {
	    PassField.setEchoChar((char) 0);
	} else {
	    PassField.setEchoChar(defaultChar);
	}
    }

    public void show() {
	this.frmAgenda.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.frmAgenda.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		int confirm = JOptionPane.showOptionDialog(null, "�Estas seguro que quieres salir de la Agenda?",
			"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
		    Conexion.getConexion().cerrarConexion();
		    System.exit(0);
		}
	    }
	});
	this.frmAgenda.setVisible(true);
    }

    public JTextField getUser_textField() {
	return User_textField;
    }

    public void setUser_textField(JTextField user_textField) {
	User_textField = user_textField;
    }

    public JPasswordField getPassField() {
	return PassField;
    }

    public void setPassField(JPasswordField passField) {
	PassField = passField;
    }

    public JButton getBtnIngreso() {
	return btnIngreso;
    }

    public void setBtnIngreso(JButton btnIngreso) {
	this.btnIngreso = btnIngreso;
    }

    public JButton getBtnExit() {
	return btnExit;
    }

    public void setBtnExit(JButton btnExit) {
	this.btnExit = btnExit;
    }

    public JCheckBox getSHOWCheckBox() {
	return SHOWCheckBox;
    }

    public void setSHOWCheckBox(JCheckBox sHOWCheckBox) {
	SHOWCheckBox = sHOWCheckBox;
    }

    public void cerrarVentana() {
	this.User_textField.setText(null);
	this.PassField.setText(null);
	this.dispose();
	System.out.println("cerrau");
    }

}
