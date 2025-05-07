/*
 *
 * package com.ERPMatrix.Application.tools.Jasper;
 * 
 * import java.io.FileOutputStream; import java.io.IOException; import
 * java.io.InputStream; import java.sql.Connection; import
 * java.sql.SQLException;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.io.Resource; import
 * org.springframework.core.io.ResourceLoader;
 * 
 * import net.sf.jasperreports.engine.JRException; import
 * net.sf.jasperreports.engine.JasperRunManager;
 * 
 * public class supliserPillReport {
 * 
 * @Autowired DataSource dataSource;
 * 
 * @Autowired ResourceLoader resourceLoader;
 * 
 * void createReport() throws IOException, JRException, SQLException {
 * 
 * Resource resource =
 * resourceLoader.getResource("/report/supliser_pill_report.jasper");
 * 
 * InputStream reportStream = resource.getInputStream(); Connection conn =
 * this.dataSource.getConnection();
 * 
 * byte[] reportByte = JasperRunManager.runReportToPdf(reportStream, null,
 * conn); FileOutputStream report = new FileOutputStream("/report/");
 * 
 * report.write(reportByte); report.close();
 * 
 * }
 * 
 * }
 *
 * 
 */
