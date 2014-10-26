/** @文件名: PBStringUtil.java @创建人：邢健  @创建日期： 2012-11-23 下午4:05:12 */
package com.promise.cn.common.utils;

/**   
 * @类名: PBStringUtil.java 
 * @包名: com.promise.study.CnToSpell 
 * @描述: TODO 
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2012-11-23 下午4:05:12 
 * @版本: V1.0   
 */
public class PBStringUtil {

	/**
	 * 字符串中不合法字符去掉，只保留字母和数字
	 * @return
	 */
	public static String getAZTo09(String str){
		return str.replaceAll("[^A-Za-z0-9]", "");
	}

}
