package persistencia.cmd;

import java.io.IOException;

public class Consola {

    public static boolean baseLevantada = false;

    /*
     * Levanta localmente la base de datos
     */
    private static void connect() {
	if (!baseLevantada) {
	    
	    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
		    "cd \"C:\\Program Files\\MariaDB 10.2\\bin && use agenda;");
	    builder.redirectErrorStream(true);
	    try {
		Process p = builder.start();
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	}

    }

    /*
     * 
     */
    public static void connectRoot() {

    }
}
