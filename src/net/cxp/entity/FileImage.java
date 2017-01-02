package net.cxp.entity;

import java.io.File;

public class FileImage {

	private File file;
	private String contentType;
	private String fileName;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getContentType() {
		return contentType;
	}
	public void setFileContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
