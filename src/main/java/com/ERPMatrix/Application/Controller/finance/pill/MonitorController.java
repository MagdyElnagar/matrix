package com.ERPMatrix.Application.Controller.finance.pill;

import static com.ERPMatrix.Application.Constant.statsicValues.APPLICATION_DETAILS;
import static com.ERPMatrix.Application.Constant.statsicValues.COMPANY_NAME;
import static com.ERPMatrix.Application.Constant.statsicValues.COMPANY_NUMBER;
import static com.ERPMatrix.Application.Constant.statsicValues.OUT_PUT_PATH;
import static org.springframework.http.HttpStatus.OK;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ERPMatrix.Application.JasperReport.Model.finance_details_model;
import com.ERPMatrix.Application.Model.finance.Accountat.MonitorModel;
import com.ERPMatrix.Application.Model.finance.pill.pill;
import com.ERPMatrix.Application.Service.finance.pill.pillServ;
import com.ERPMatrix.Application.exception.ExceptionHandling;
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
@RequestMapping(path = { "/", "/MonitorController/" })
@CrossOrigin(origins = "*")
public class MonitorController extends ExceptionHandling {
	private org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private pillServ pillServ;

	@PostMapping("findPills")
	public ResponseEntity<List<pill>> findPills(@RequestBody MonitorModel MonitorModel)
			throws HandlerException, ParseException, FileNotFoundException, JRException {

		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

		String dateNow = SDF.format(new Date()).toString();
		String toDate = "";
		String fromDate = "";
		String cli_name = "";
		String cli_id = "";
		boolean delegate = false;
		boolean employee = false;
		boolean from = false;
		boolean to = false;
		boolean mustpay = false;
		boolean pilltime = false;
		boolean store = false;
		boolean rank = false;
		boolean client = false;
		boolean trafic = false;

		Map<String, String> operationMap = new HashMap<>();
		ArrayList<String> List = new ArrayList<String>();
		if (MonitorModel.getClient() == null) {
			client = false;

		} else {
			client = true;
			operationMap.put("client", "true");
			List.add("client");
			cli_name = MonitorModel.getClient();

		}

		if (MonitorModel.getTrafic() == null) {
			trafic = false;

		} else {
			trafic = true;
			operationMap.put("trafic", "true");
			List.add("trafic");
		}

		if (MonitorModel.getStore() == null) {

			store = false;

		} else {
			store = true;
			operationMap.put("store", "true");
			List.add("store");

		}

		if (MonitorModel.getRank() == null) {

			rank = false;

		} else {
			rank = true;
			operationMap.put("rank", "true");
			List.add("rank");
		}

		if (MonitorModel.getDelegate_name() == null) {

			delegate = false;

		} else {
			delegate = true;
			operationMap.put("delegate", "true");
			List.add("delegate");

		}
		if (MonitorModel.getEmployee_name() == null) {

			employee = false;

		} else {
			employee = true;
			operationMap.put("employee", "true");
			List.add("employee");

		}
		if (MonitorModel.getFrom() == null || MonitorModel.getFrom().equals("null")
				|| MonitorModel.getFrom().equals(" ") || MonitorModel.getFrom().equals("")) {

			from = false;

		} else {
			from = true;
			operationMap.put("from", "true");
			List.add("from");
			fromDate = String.valueOf(SDF.parse(MonitorModel.getFrom()));

		}
		if (MonitorModel.getPills_time() == null) {

			pilltime = false;

		} else {
			pilltime = true;
			operationMap.put("pilltime", "true");
			List.add("pilltime");
		}
		if (MonitorModel.getTo() == null || MonitorModel.getTo().equals("null") || MonitorModel.getTo().equals(" ")
				|| MonitorModel.getTo().equals("")) {

			to = false;

		} else {
			to = true;
			operationMap.put("to", "true");
			toDate = String.valueOf(SDF.parse(MonitorModel.getTo()));

		}
		if (MonitorModel.getMust_pay() == null) {

			mustpay = false;

		} else {
			mustpay = true;
			operationMap.put("mustpay", "true");

		}

		List<pill> pill = new ArrayList<pill>();
		LOGGER.info("D" + MonitorModel.getFrom());
		LOGGER.info("D" + MonitorModel.getTo());
		if (from && to) {
			LOGGER.info("Equals True");
			pill = pillServ.findAllByEntrydateBetween(MonitorModel.getFrom(), MonitorModel.getTo());
		} else if (from && !to) {
			LOGGER.info("to Equal false");
			pill = pillServ.findAllByEntrydateBetween(MonitorModel.getFrom(), dateNow);
		} else if(client) {
			LOGGER.info("client true");
			LOGGER.info(MonitorModel.getClient());
			pill = pillServ.findByClientname(MonitorModel.getClient());
		}

		if (store) {

			pill = pill.stream().filter((e) -> e.getStorename().equals(MonitorModel.getStore()))
					.collect(Collectors.toList());

		}
		if (employee) {

			pill = pill.stream().filter((e) -> e.getEmployee().equals(MonitorModel.getEmployee_name()))
					.collect(Collectors.toList());

		}

		if (client) {

			pill = pill.stream().filter((e) -> e.getClientname().equals(MonitorModel.getClient()))
					.collect(Collectors.toList());

		}
		if (delegate) {

			pill = pill.stream().filter((e) -> e.getDelegate().equals(MonitorModel.getDelegate_name()))
					.collect(Collectors.toList());

		}
		if (rank) {

			pill = pill.stream().filter((e) -> e.getClientrank().equals(MonitorModel.getRank()))
					.collect(Collectors.toList());

		}





		return new ResponseEntity<>(pill, OK);
	}

}
