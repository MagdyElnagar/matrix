package com.ERPMatrix.Application.Controller.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ERPMatrix.Application.Model.File.FileModel;
import com.ERPMatrix.Application.Model.compare.compareModel1;
import com.ERPMatrix.Application.Repository.File.compare1;
import com.ERPMatrix.Application.Response.FileResponse;
import com.ERPMatrix.Application.Service_implement.File.FileStorageService;
import com.ERPMatrix.Application.exception.ExceptionHandling;
import com.ERPMatrix.Application.exception.domain.HandlerException;


@RestController
@Configuration

@RequestMapping(path = { "/", "/fileStorage/" })
@CrossOrigin(origins = "*")

public class FileController extends ExceptionHandling {

	private FileStorageService FileStorageService;

	@Autowired
	public FileController(FileStorageService fileStorageService) {
		super();
		FileStorageService = fileStorageService;

	}

	@PostMapping(value = "/download", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object downloadFile(@RequestParam("id") String orderid, @RequestParam("username") String username,
			@RequestParam("compID") String compID) throws IOException, HandlerException {

		File file = null;// GeneratePDFImpl.order(orderid, username, compID);
		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders header = new HttpHeaders();

		header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + file.getName() + ".pdf");

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		/*
		 * return ResponseEntity.ok() .headers(header) .contentLength(file.length())
		 * .contentType(MediaType.parseMediaType("application/pdf")) .body(resource);
		 */

		/*
		 * System.out.println(file.getName()); return
		 * ResponseEntity.ok().contentLength(resource.getByteArray().length).header(
		 * "Content-type", "application/pdf") .header("Content-disposition",
		 * "attachment; filename=\"" + file.getName() + "\"").body(resource);
		 */
	}

	@PostMapping(value = "/downloadPill", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object downloadPill(@RequestParam("id") String orderid, @RequestParam("username") String username,
			@RequestParam("compID") String compID) throws IOException, HandlerException {

		File file = null;// GeneratePDFImpl.Pill(orderid, username, compID);
		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders header = new HttpHeaders();

		header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + file.getName() + ".pdf");

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		/*
		 * return ResponseEntity.ok() .headers(header) .contentLength(file.length())
		 * .contentType(MediaType.parseMediaType("application/pdf")) .body(resource);
		 */

		/*
		 * System.out.println(file.getName()); return
		 * ResponseEntity.ok().contentLength(resource.getByteArray().length).header(
		 * "Content-type", "application/pdf") .header("Content-disposition",
		 * "attachment; filename=\"" + file.getName() + "\"").body(resource);
		 */
	}

	@GetMapping(value = "/download/Report/{order}/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object downloadReportFile(@RequestParam("id") String id, @RequestParam("order") String order)
			throws IOException, HandlerException {

		String fileName = order + "_" + id + ".pdf";
		System.out.println(fileName);

		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			System.out.println(fileInputStream);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		/*
		 * return ResponseEntity.ok() .headers(header) .contentLength(file.length())
		 * .contentType(MediaType.parseMediaType("application/pdf")) .body(resource);
		 */

		/*
		 * System.out.println(file.getName()); return
		 * ResponseEntity.ok().contentLength(resource.getByteArray().length).header(
		 * "Content-type", "application/pdf") .header("Content-disposition",
		 * "attachment; filename=\"" + file.getName() + "\"").body(resource);
		 */
	}

	@GetMapping("/Allfiles")
	public List<FileModel> getListFiles(Model model) {
		List<FileModel> fileDetails = FileStorageService.getListOfFiles();

		return fileDetails;
	}

	@PostMapping("/Upload")
	public FileResponse uploadFile(@RequestParam("file") MultipartFile file) throws HandlerException {
		System.out.println(file.getName());

		FileModel model = FileStorageService.saveFile(file);

		String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(model.getFileId())
				.toUriString();
		return new FileResponse(model.getFileName(), model.getFileType(), fileUri);
	}
	
	@PostMapping("/uploadEx")
	public FileResponse uploadEx(@RequestParam("file") File file) throws HandlerException, InvalidFormatException, IOException {
		System.out.println(file.getName());
		
	
		
		
		List<compareModel1> List = new ArrayList<>();
		
		String path="C:\\Book2.xlsx";
		  XSSFWorkbook WB = new XSSFWorkbook(file);
	        DataFormatter DF = new DataFormatter();
	        XSSFFormulaEvaluator.evaluateAllFormulaCells(WB);
	        for (int x = 0; x < WB.getNumberOfSheets(); x++) {
	            XSSFSheet Sheet = WB.getSheetAt(x);
	            XSSFRow row;
	            XSSFRow roW = Sheet.getRow(2);
	            Iterator<org.apache.poi.ss.usermodel.Row> somerow = Sheet.iterator();
	            int col = 0;
	            col = Sheet.getColumnOutlineLevel(0);
	            for (int i = 0; i <= roW.getRowNum(); i++) {
	                for (int j = 0; j <= col; j++) {
	                    XSSFCell CellData = roW.getCell(j);
	                    System.out.println(CellData);
	                }
	            }
	        }
		System.out.println("Done");
		return null;

		
	}

}
