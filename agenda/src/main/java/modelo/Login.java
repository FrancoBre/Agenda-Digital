package modelo;

import persistencia.conexion.Conexion;

public class Login {

    public Login() {
	
    }
    
    public void enviarCredenciales(String user, String pass) {
	
	new Conexion(user, pass);
	
    }
}
