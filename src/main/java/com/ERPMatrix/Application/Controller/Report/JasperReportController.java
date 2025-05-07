package com.ERPMatrix.Application.Controller.Report;

import static com.ERPMatrix.Application.Constant.statsicValues.APPLICATION_DETAILS;
import static com.ERPMatrix.Application.Constant.statsicValues.COMPANY_NAME;
import static com.ERPMatrix.Application.Constant.statsicValues.COMPANY_NUMBER;
import static com.ERPMatrix.Application.Constant.statsicValues.OUT_PUT_PATH;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.JasperReport.Model.finance_details_model;
import com.ERPMatrix.Application.Model.Client.client;
import com.ERPMatrix.Application.Model.Jasper_Model.finance_Details;
import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Model.finance.pill.pilldetails;
import com.ERPMatrix.Application.Model.finance.pill.reback.reback_pill;
import com.ERPMatrix.Application.Service.Client.ClientServ;
import com.ERPMatrix.Application.Service.finance.pill.pillDetailsServ;
import com.ERPMatrix.Application.Service.finance.pill.pillServ;
import com.ERPMatrix.Application.Service.finance.pill.reback.reback_pill_service;
import com.ERPMatrix.Application.exception.domain.HandlerException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@Configuration
@RequestMapping(path = { "/", "/JasperReport/" })
@CrossOrigin(origins = "*")
public class JasperReportController {

	private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	@Autowired
	private ClientServ cliServ;
	@Autowired
	private pillDetailsServ pillDetailsServ;
	@Autowired
	private pillServ pillServ;
	@Autowired
	private reback_pill_service reback_serv;

	@PostMapping(value = "/Finance_Pill", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object downloadPill(@RequestParam("pillid") String pillid, @RequestParam("username") String username)
			throws JRException, NumberFormatException, HandlerException, IOException, ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

		pill pill = pillServ.findById(Long.parseLong(pillid));
		client cli = cliServ.findByName(pill.getClientname());
		List<pilldetails> pillDetails = pillDetailsServ.findByPillid(pillid);
		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		Map<String, Object> jasperParameter = new HashMap();
		double dis = pill.getAmountbefordiscount() - pill.getAmountafterdiscont();
		dis = Double.parseDouble(df.format(dis));
		jasperParameter.put("company_name", COMPANY_NAME);
		jasperParameter.put("commercialregister", COMPANY_NUMBER);
		jasperParameter.put("application_info", APPLICATION_DETAILS);
		jasperParameter.put("Pill_Id", pill.getId());
		jasperParameter.put("entrydate", SDF.format(pill.getEntrydate()));
		jasperParameter.put("employee", pill.getEmployee());
		jasperParameter.put("client_name", cli.getName());
		jasperParameter.put("totalAfterDiscount", pill.getAmountafterdiscont());
		jasperParameter.put("totalBefotDiscount", pill.getAmountbefordiscount());
		jasperParameter.put("debit", cli.getDebit());
		jasperParameter.put("discount", dis);
		jasperParameter.put("phone1", cli.getPhone1());
		jasperParameter.put("adress", cli.getAdress());
		jasperParameter.put("trafic", cli.getTrafic());
		jasperParameter.put("delegate", cli.getDelegate());
		JRBeanCollectionDataSource beanController = new JRBeanCollectionDataSource(pillDetails);
		// JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Sans
		// Serif");
		JasperReport JS = JasperCompileManager
				.compileReport(new FileInputStream(OUT_PUT_PATH + "JasperReport/Finance_Pill.jrxml"));

		HashMap<String, Object> map = new HashMap<>();

		JasperPrint report = JasperFillManager.fillReport(JS, jasperParameter, beanController);

		String fileName = OUT_PUT_PATH + "ReportOutPut/" + "Finance Pill_" + pill.getId() + ".pdf";
		JasperExportManager.exportReportToPdfFile(report, fileName);

		Path path = Paths.get(fileName);

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders header = new HttpHeaders();

		header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@PostMapping(value = "/Finance_Details", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object Finance_Details(@RequestParam("clientname") String clientname, @RequestParam("from") String from,
			@RequestParam("to") String to)
			throws JRException, NumberFormatException, HandlerException, IOException, ParseException {

		SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

		String datenow = SDF.format(new Date());
		List<pill> pill = pillServ.findByEntrydateGreaterThanEqualAndEntrydateLessThanEqual(clientname, from, to);
		client cli = cliServ.findByName(clientname);
		List<finance_Details> finance_parameter = new ArrayList<finance_Details>();

		for (int x = 0; x < pill.size(); x++) {

			finance_parameter.get(x).setId(pill.get(x).getId());
			finance_parameter.get(x).setEntrydate(SDF.parse(SDF.format(pill.get(x).getEntrydate())));
			finance_parameter.get(x).setSales(pill.get(x).getAmountafterdiscont());
			finance_parameter.get(x).setReback(getReback(String.valueOf(pill.get(x).getId())));
			
			finance_parameter.get(x).setPaid(null);
			finance_parameter.get(x).setTotal(null);
			
			

		}
       // Collections.sort((List<T>) finance_parameter);

		DecimalFormat df = new DecimalFormat("###.###");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		Map<String, Object> jasperParameter = new HashMap();

		double dis = 0;
		dis = Double.parseDouble(df.format(dis));
		jasperParameter.put("company_info", COMPANY_NAME);
		jasperParameter.put("commercialregister", COMPANY_NUMBER);
		jasperParameter.put("application_info", APPLICATION_DETAILS);
		jasperParameter.put("client_name", cli.getName());
		jasperParameter.put("date_of_details", datenow);

		JRBeanCollectionDataSource beanController = new JRBeanCollectionDataSource(finance_parameter);

		JasperReport JS = JasperCompileManager
				.compileReport(new FileInputStream(OUT_PUT_PATH + "JasperReport\\Finance _Details.jrxml"));

		HashMap<String, Object> map = new HashMap<>();

		JasperPrint report = JasperFillManager.fillReport(JS, jasperParameter, beanController);

		String fileName = OUT_PUT_PATH + "ReportOutPut/" + "Finance Pill_" + cli.getId() + ".pdf";
		JasperExportManager.exportReportToPdfFile(report, fileName);

		Path path = Paths.get(fileName);

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders header = new HttpHeaders();

		header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private double getReback(String pill_id) {

		reback_pill reback = reback_serv.findByPillid(pill_id);
		if (reback == null) {
			return 0;
		} else {
			return reback.getAmount();

		}
	}

	@PostMapping(value = "/Finance_Pills", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object Finance_Pills(@RequestParam String id)
			throws JRException, NumberFormatException, HandlerException, IOException, ParseException {

		String fileName = OUT_PUT_PATH + "ReportOutPut/" + "Finance Search_" + id + ".pdf";

		Path path = Paths.get(fileName);

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders header = new HttpHeaders();

		header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
