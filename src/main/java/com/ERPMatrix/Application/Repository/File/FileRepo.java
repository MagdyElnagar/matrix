package com.ERPMatrix.Application.Repository.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.File.FileModel;

@Repository
public interface FileRepo extends JpaRepository<FileModel, String> {

	void deleteByFileNameStartingWithAndUserid(String filename, String userid);

	void deleteByUseridAndFileName(String userid, String filename);

	FileModel findByFileName(String filename);

}
