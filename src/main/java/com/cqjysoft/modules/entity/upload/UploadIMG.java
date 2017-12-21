package com.cqjysoft.modules.entity.upload;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JBOS_UploadIMG")
public class UploadIMG{
	private Long id;
	//图片名字
	private String name;
	//图片上传日期
	private Date uploadDate;
	//图片日期
	private Date imgDate;
	//图片标注地址
	private String address;
	//图片标注信息
	private String info;
	//图片点赞数
	private int up;
	private String imgURL;
	private String imgSLURL;
	
	public UploadIMG(String saveName, Date upload, Date date, String savePath, String sLpath) {
		this.name = saveName;
		this.uploadDate = upload;
		this.imgDate = date;
		this.imgURL = savePath;
		this.imgSLURL = sLpath;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "FName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "FUploadDate")
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	@Column(name = "FImgDate")
	public Date getImgDate() {
		return imgDate;
	}
	public void setImgDate(Date imgDate) {
		this.imgDate = imgDate;
	}
	
	@Column(name = "FAddress")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "FInfo")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Column(name = "FUp")
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	
	@Column(name = "FImgURL")
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	@Column(name = "FImgSLURL")
	public String getImgSLURL() {
		return imgSLURL;
	}
	public void setImgSLURL(String imgSLURL) {
		this.imgSLURL = imgSLURL;
	}
}


