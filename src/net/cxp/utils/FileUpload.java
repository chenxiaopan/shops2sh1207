package net.cxp.utils;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

import net.cxp.entity.FileImage;

/**
 * 文件上传的实现
 * 
 * @author cxp
 * 
 */

@Component("fileUpload")
public class FileUpload {

	// @Value表示去applicationContext.xml文件中找id="prop"的bean，它是通过注解的方式读取properties配置文件的，然后去相应的配置文件中读取key=filePath的值
	@Value("#{prop.basePath+prop.filePath}")
	private String filePath;

	// 实现文件上传功能，返回上传后新的文件名称
	public String uploadFile(FileImage fileImage) {
		// 获取新的唯一文件名
		String pic = newFileName(fileImage.getFileName());
		try {

			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {

			fileImage.getFile().delete();

		}

	}

	// 1.通过文件名获取扩展名
	private String getFileExt(String fileName) {

		return FilenameUtils.getExtension(fileName);
	}

	// 2.生成UUID随机数，作为新的文件名
	private String newFileName(String fileName) {
         //获得扩展名   
		String ext = getFileExt(fileName);
		System.out.println("fileName---->" + fileName);
		System.out.println("ext---->" + ext);

		return UUID.randomUUID().toString() + "." + ext;
	}

}
