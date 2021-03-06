package persistencia.conexion;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import configuracion.Configuracion;
import modelo.Login;

public class Conexion {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost/agenda";

    public static Conexion instancia;
    private Connection conn;
    private Statement stmt;
    private Logger log = Logger.getLogger(Conexion.class);

    private Conexion() {
	try {

	    Class.forName(JDBC_DRIVER);

	    log.info("Conectandose a la base de datos agenda...");

	    conn = DriverManager.getConnection(DB_URL, Login.USER, Login.PASS);
	    
	    Configuracion.conexionRealizada = true;
	    
	    log.info("Conexión abierta");


	} catch (SQLException se) {
	    se.printStackTrace();
	    log.error("Se ha producido un error ");

	} catch (Exception e) {
	    e.printStackTrace();
	    log.error("Error");
	}
    }

    public void cerrarConexion() {

	try {
	    if (stmt != null) {
		conn.close();
	    }
	} catch (SQLException se) {

	}
	try {
	    if (conn != null) {
		conn.close();
	    }
	} catch (SQLException se) {
	    se.printStackTrace();
	    log.error("Error al cerrar la conexion");
	}

	log.info("Conexion cerrada");
    }

    /*
     * Implementa Singleton
     */
    public static Conexion getConexion() {
	if (instancia == null) {
	    instancia = new Conexion();
	}
	return instancia;
    }

    public Connection getSQLConexion() {
	return this.conn;
    }

}
