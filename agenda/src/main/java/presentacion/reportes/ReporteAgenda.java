package presentacion.reportes;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteAgenda {
    private JasperReport reporte;
    private JasperViewer reporteViewer;
    private JasperPrint reporteLleno;
    private Logger log = Logger.getLogger(ReporteAgenda.class);
    // Recibe la lista de personas para armar el reporte
    private final String jasperURL = "reporteAgenda2.jasper";

    public ReporteAgenda(Connection conexion) {

	Map<String, Object> parametersMap = new HashMap<String, Object>();
	parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	try {
	    this.reporte = (JasperReport) JRLoader.loadObjectFromFile("reportes" + File.separator + jasperURL);
	    this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
		    conexion);

	    log.info("Se cargó correctamente el reporte");
	} catch (JRException ex) {
	    log.error("Ocurrió un error mientras se cargaba el archivo " + jasperURL, ex);
	}
    }

    public void mostrar() {
	this.reporteViewer = new JasperViewer(this.reporteLleno, false);
	this.reporteViewer.setVisible(true);
    }

}