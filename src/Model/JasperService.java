package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;

import DAO.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperService {
	private Map<String, Object> params = new LinkedHashMap<>();

	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}

	public void abrirJasperViwer(String jrxml, Connection connection ) {
		//connection =  ConnectionFactory.getConnection();
       JasperReport report = compilerJrxml(jrxml);
       try {
    	   JasperPrint print = JasperFillManager.fillReport(report, this.params,  connection);
    	   JasperViewer viewer = new JasperViewer(print);
    	   viewer.setVisible(true);
       }catch(JRException e) {
    	   e.printStackTrace();
       }
	}
	
	public void abrirPontoJasper(String jasperFile, Connection connection) {
		try {
		InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
		JasperPrint print = JasperFillManager.fillReport(is, this.params, connection);
		JasperViewer viwer = new JasperViewer(print);
			viwer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	} 

	public void exportarPdf(String jrxml, Connection connection, String saida) {
		JasperReport report = compilerJrxml(jrxml);
		try {
			OutputStream out = new FileOutputStream(saida);
			JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
		JasperExportManager.exportReportToPdfStream(print, out);
		}catch(JRException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private JasperReport compilerJrxml(String arquivo) {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo);
			return JasperCompileManager.compileReport(is);
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	
	public void GeraRela(String resource, Connection connection) {
		
		try {
			
			//connection = ConnectionFactory.getConnection();
			JasperPrint jp = JasperFillManager.fillReport(resource, this.params, connection);
					JasperViewer jw = new JasperViewer(jp);
			
			jw.setVisible(true);
			//JasperExportManager.exportReportToPdfFile(jp,"/RelReclamacoes.xml");
			//FileWriter arq;
            /*try {
            	  arq = new FileWriter(System.getProperty("user.home") + "/Documents/PIZZARIA_RECLAMACOES.txt", true);
                arq.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
