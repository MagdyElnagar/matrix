package com.ERPMatrix.Application.Service_implement.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ERPMatrix.Application.Model.File.FileModel;
import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface FileStorageService {

	void deleteFile(String Filename, String userid);

	FileModel getFile(String fileId) throws FileNotFoundException;

	FileModel getFileByName(String Filename) throws FileNotFoundException, IOException;

	File getFontByName(String Filename) throws FileNotFoundException, IOException;

	List<FileModel> getListOfFiles();

	FileModel saveFile(MultipartFile file) throws HandlerException;

	FileModel upFile(String filename, String fileContentType, byte[] fileByte, String userid) throws HandlerException;

	// Image DownloadFile(String fileName) throws FileNotFoundException,
	// IOException, BadElementException;
}
