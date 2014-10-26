/** @文件名: FileStreamUtil.java @创建人：邢健  @创建日期： 2012-11-13 下午1:43:44 */
package com.promise.cn.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

/**   
 * @类名: FileStreamUtil.java 
 * @包名: com.promise.cn.framework.utils 
 * @描述: 读取文件帮助类
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2012-11-13 下午1:43:44 
 * @版本: V1.0   
 */
public class FileStreamUtil {

	public static final int BUFFER_SIZE = 4096;
	/**
	 * 保存文件到指定目录
	 * @param mutipartFile
	 * @param path
	 * @param fileName
	 */
	public static void SaveFileFromInputStream(MultipartFile mutipartFile,String path,String fileName){
		try {
			InputStream is = mutipartFile.getInputStream();
			File file=new File(path+"\\"+fileName);
	        if(!file.exists()){
	        	file.createNewFile();
	        }
	        FileOutputStream out=new FileOutputStream(file);
	        int c;
	        byte buffer[]=new byte[BUFFER_SIZE];
	        while((c=is.read(buffer))!=-1){
	           out.write(buffer);
	        }
	        out.flush();
	        out.close();
	        is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件拷贝
	 * 输入流和输出流
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void CopyFileStream(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		}
		finally {
			try {
				in.close();
			}
			catch (IOException ex) {
			}
			try {
				out.close();
			}
			catch (IOException ex) {
			}
		}
	}

}
