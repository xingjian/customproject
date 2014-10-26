/** @文件名: CommonUtil.java @创建人：邢健  @创建日期： 2012-11-16 上午9:22:32 */
package com.promise.cn.framework.utils;

/**   
 * @类名: CommonUtil.java 
 * @包名: com.promise.cn.framework.utils 
 * @描述: 通用
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2012-11-16 上午9:22:32 
 * @版本: V1.0   
 */
public class CommonUtil {

	/**
	 * 返回javalib 路径
	 * @return
	 */
	public static String GetJavaLibraryPath(){
		return System.getProperty("java.library.path");
	}
	
	
	public static void main(String[] args) {
		String path = CommonUtil.GetJavaLibraryPath();
		String[] array = path.split(";");
		for(String p :array){
			
			
		}
	}

}
