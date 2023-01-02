package com.sportyshoes.restapi.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderItems")
public class OrderItem {
	
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemId")    
	private Long id; //primary key	
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "orderId", referencedColumnName="orderId")
	private Order orderId; //foreign key
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "productId", referencedColumnName="productId")
	private Product productId; //foreign key
	
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name = "sizeId", referencedColumnName="sizeId")
	private Size sizeId; //foreign key
	
	private Long quantity;
	private BigDecimal subtotal;
	
	//Default Constructor
	public OrderItem() {}

	//Parameterized Constructor
	public OrderItem(Long id, Order orderId, Product productId, Size sizeId, Long quantity, BigDecimal subtotal) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.sizeId = sizeId;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	
	//Getters & Setters
	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public Size getSizeId() {
		return sizeId;
	}

	public void setSizeId(Size sizeId) {
		this.sizeId = sizeId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Long getId() {
		return id;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", sizeId=" + sizeId
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}

}//end class
