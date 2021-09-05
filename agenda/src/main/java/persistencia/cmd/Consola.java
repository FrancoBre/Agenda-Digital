package persistencia.cmd;

import java.io.IOException;

public class Consola {

    /*
     * Se conecta a root desde la base de datos
     */
    public static void connectRoot() {
	ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
		"cd \"C:\\Program Files\\MariaDB 10.2\\bin && mysql -u root -p root; && use agenda;");
	builder.redirectErrorStream(true);

	try {
	    builder.start();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /*
     * Se conecta a user desde la base de datos
     */
    public static void connectUser() {
	ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
		"cd \"C:\\Program Files\\MariaDB 10.2\\bin && mysql -u user -p 1234; && use agenda;");
	builder.redirectErrorStream(true);

	try {
	    builder.start();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
