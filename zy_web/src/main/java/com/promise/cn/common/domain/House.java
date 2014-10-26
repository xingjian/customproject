/** @文件名: House.java @创建人：邢健  @创建日期： 2013-10-14 上午11:11:42 */
package com.promise.cn.common.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @类名: House.java 
 * @包名: com.promise.cn.common.domain 
 * @描述: TODO 
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2013-10-14 上午11:11:42 
 * @版本: V1.0   
 */
@SuppressWarnings("all")		
@Entity
@Table(name = "house")
public class House {

	private String id;
	private String name;
	private String code;
	private Police police;
	private String fzMessage;
	private String remark;
	private String zlzMessage;
	private String houseQMessage;
	private String houseQImage;
	private String houseHMessage;
	private String houseHImage;
	private String firstFloorImage;
	private String secondFloorImage;
	private String thirdFloorImage;
	private String address;
	private String fzphone;
	private String zlzphone;
	private double x;
	private double y;
	private int active;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@ManyToOne(targetEntity = Police.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "policeid")
	public Police getPolice() {
		return police;
	}
	public void setPolice(Police police) {
		this.police = police;
	}
	public String getFzMessage() {
		return fzMessage;
	}
	public void setFzMessage(String fzMessage) {
		this.fzMessage = fzMessage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getZlzMessage() {
		return zlzMessage;
	}
	public void setZlzMessage(String zlzMessage) {
		this.zlzMessage = zlzMessage;
	}
	public String getHouseQMessage() {
		return houseQMessage;
	}
	public void setHouseQMessage(String houseQMessage) {
		this.houseQMessage = houseQMessage;
	}
	public String getHouseQImage() {
		return houseQImage;
	}
	public void setHouseQImage(String houseQImage) {
		this.houseQImage = houseQImage;
	}
	public String getHouseHMessage() {
		return houseHMessage;
	}
	public void setHouseHMessage(String houseHMessage) {
		this.houseHMessage = houseHMessage;
	}
	public String getHouseHImage() {
		return houseHImage;
	}
	public void setHouseHImage(String houseHImage) {
		this.houseHImage = houseHImage;
	}
	public String getFirstFloorImage() {
		return firstFloorImage;
	}
	public void setFirstFloorImage(String firstFloorImage) {
		this.firstFloorImage = firstFloorImage;
	}
	public String getSecondFloorImage() {
		return secondFloorImage;
	}
	public void setSecondFloorImage(String secondFloorImage) {
		this.secondFloorImage = secondFloorImage;
	}
	public String getThirdFloorImage() {
		return thirdFloorImage;
	}
	public void setThirdFloorImage(String thirdFloorImage) {
		this.thirdFloorImage = thirdFloorImage;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFzphone() {
		return fzphone;
	}
	public void setFzphone(String fzphone) {
		this.fzphone = fzphone;
	}
	public String getZlzphone() {
		return zlzphone;
	}
	public void setZlzphone(String zlzphone) {
		this.zlzphone = zlzphone;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
}
