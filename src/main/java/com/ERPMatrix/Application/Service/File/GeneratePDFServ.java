package com.ERPMatrix.Application.Service.File;

import java.io.File;
import java.io.IOException;

import com.ERPMatrix.Application.exception.domain.HandlerException;

public interface GeneratePDFServ {

	File order(String orderid, String username, String compID) throws IOException, HandlerException;

	File Pill(String orderid, String username, String compID) throws IOException, HandlerException;

}
