package com.ERPMatrix.Application.Service.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ERPMatrix.Application.Model.File.FileModel;
import com.ERPMatrix.Application.Repository.File.FileRepo;
import com.ERPMatrix.Application.Service_implement.File.FileStorageService;
import com.ERPMatrix.Application.exception.domain.HandlerException;

@Service
@Transactional
public class FileStorageImpl implements FileStorageService {

	@Autowired
	private FileRepo fileRepo;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public void deleteFile(String Filename, String userid) {
		fileRepo.deleteByFileNameStartingWithAndUserid(Filename, userid);

	}

	/*
	 * @Override public Image DownloadFile(String fileName) throws IOException,
	 * BadElementException {
	 *
	 * FileModel ff = fileRepo.findByFileName(fileName); File convertedFile = new
	 * File(ff.getFileName()); FileOutputStream fos = new
	 * FileOutputStream(convertedFile); fos.write(ff.getFileData()); Image
	 * returnImage = Image.getInstance(ff.getFileData());
	 *
	 * return returnImage; }
	 */

	@Override
	public FileModel getFile(String fileId) throws FileNotFoundException {
		LOGGER.info(fileId);

		return fileRepo.findById(fileId).orElseThrow(() -> new FileNotFoundException(fileId));
	}

	@Override
	public FileModel getFileByName(String Filename) throws IOException {

		FileModel ff = fileRepo.findByFileName(Filename);
		File convertedFile = new File(ff.getFileName());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(ff.getFileData());
		return ff;

	}

	@Override
	public File getFontByName(String Filename) throws FileNotFoundException, IOException {

		FileModel ff = fileRepo.findByFileName(Filename);
		File convertedFile = new File(ff.getFileName());

		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(ff.getFileData());

		return convertedFile;
	}

	@Override
	public List<FileModel> getListOfFiles() {
		// TODO Auto-generated method stub
		return fileRepo.findAll();
	}

	@Override
	public FileModel saveFile(MultipartFile file) throws HandlerException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String userid = "";
		try {

			FileModel model = new FileModel(filename, file.getContentType(), file.getBytes(), userid);
			return fileRepo.save(model);

		} catch (Exception e) {

			throw new HandlerException(e.toString(), "File Storage impl saveFile");
		}
	}

	@Override
	public FileModel upFile(String filename, String fileContentType, byte[] fileByte, String userid)
			throws HandlerException {
		try {

			FileModel model = new FileModel(filename, fileContentType, fileByte, userid);
			return fileRepo.save(model);

		} catch (Exception e) {

			throw new HandlerException(e.toString(), "File Storage impl upFile");
		}
	}

}
