package com.ERPMatrix.Application.Model.File;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fileupload_Download")
public class FileModel {


	@Lob
	private byte[] fileData;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String fileId;
	private String fileName;
	private String fileType;
	private String userid;

	public FileModel() {

	}

	public FileModel(String fileName, String fileType) {
		this.fileName = fileName;
		this.fileType = fileType;

	}

	public FileModel(String fileName, String fileType, byte[] fileData, String userid) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileData = fileData;
		this.userid = userid;
	}

	public byte[] getFileData() {
		return fileData;
	}

	/** Getters and Setters **/
	public String getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public String getUserid() {
		return userid;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
