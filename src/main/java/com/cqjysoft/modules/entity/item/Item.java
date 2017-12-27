package com.cqjysoft.modules.entity.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JBOS_Item")
public class Item {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FId")
	private Long id;
	
	@Column(name="FCode")
	private String code;
	
	@Column(name="FName")
	private String name;
	
	@Column(name="FModel")
	private String model;
	
	@Column(name="FProperty")
	private String property;
	
	@Column(name="FClassify")
	private String classify;//分类
	
	@Column(name="FUnit")
	private String unit;
	
	@Column(name="FBrand")
	private String brand;//品牌
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
