package com.sportyshoes.restapi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="productId")    
	private Long id; //primary key
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "genderId", referencedColumnName="genderId")
	private Gender genderId; //FK: genderId
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "brandId", referencedColumnName="brandId")
	private Brand brandId;	//FK: brandId
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "styleId", referencedColumnName="styleId")
	private Style styleId;	//FK: styleId
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "featureId", referencedColumnName="featureId")
	private Feature featureId;	//FK: featureId
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "occasionId", referencedColumnName="occasionId")
	private Occasion occasionId; 	//FK: occasionId
	private String productName;
	@Lob
	private String productDescription;
	private BigDecimal unitPrice;	
	private Timestamp date_created;
	
	//Default Constructor
	public Product() {}
	
	//Parameterized Constructor	
	public Product(Long id, Gender genderId, Brand brandId, Style styleId, Feature featureId, Occasion occasionId,
			String productName, String productDescription, BigDecimal unitPrice, Timestamp date_created) {
		super();
		this.id = id;
		this.genderId = genderId;
		this.brandId = brandId;
		this.styleId = styleId;
		this.featureId = featureId;
		this.occasionId = occasionId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.unitPrice = unitPrice;
		this.date_created = date_created;
	}

	//Getters & Setters
	public Gender getGenderId() {
		return genderId;
	}

	public void setGenderId(Gender genderId) {
		this.genderId = genderId;
	}

	public Brand getBrandId() {
		return brandId;
	}

	public void setBrandId(Brand brandId) {
		this.brandId = brandId;
	}

	public Style getStyleId() {
		return styleId;
	}

	public void setStyleId(Style styleId) {
		this.styleId = styleId;
	}

	public Feature getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Feature featureId) {
		this.featureId = featureId;
	}

	public Occasion getOccasionId() {
		return occasionId;
	}

	public void setOccasionId(Occasion occasionId) {
		this.occasionId = occasionId;
	}

	public Timestamp getDate_created() {
		return date_created;
	}

	public void setDate_created(Timestamp date_created) {
		this.date_created = date_created;
	}

	public Long getId() {
		return id;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "Product [id=" + id + ", genderId=" + genderId + ", brandId=" + brandId + ", styleId=" + styleId
				+ ", featureId=" + featureId + ", occasionId=" + occasionId + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", unitPrice=" + unitPrice + ", date_created="
				+ date_created + "]";
	}

}//end class
