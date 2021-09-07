package modelo;

import persistencia.conexion.Conexion;

public class Login {

    public static String USER;
    public static String PASS;
    
    public Login() {
	
    }
    
    public void enviarCredenciales(String user, String pass) {
	
	Login.USER = user;
	Login.PASS = pass;
	
	Conexion.getConexion();
	
    }
    
}
