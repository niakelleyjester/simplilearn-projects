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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity //One order can have many order items
@Table(name="orders")
public class Order {
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderId")    
	private Long id; //primary key
	
	//FK: userId
	@OneToOne(cascade = CascadeType.ALL)	
	//@JoinColumn(name = "userId", referencedColumnName="userId")
	@JoinColumn(name = "username", referencedColumnName="username")
	//private User userId; //foreign key
	private User username; //foreign key
	
	private String orderNum;
	private BigDecimal shippingCost;
	private BigDecimal orderTotal;
	private Timestamp orderDate;
	private Timestamp date_created;
	
	//Default Constructor
	public Order() {}
	
	//Parameterized Constructor
	public Order(Long id, User username, String orderNum, BigDecimal shippingCost, BigDecimal orderTotal,
			Timestamp orderDate, Timestamp date_created) {
		this.id = id;
		this.username = username;
		this.orderNum = orderNum;
		this.shippingCost = shippingCost;
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
		this.date_created = date_created;
	}
	
	//Getters & Setters
	public User getUserId() {
		return username;
	}

	public void setUserId(User userId) {
		this.username = userId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
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
	
	//Other Methods
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + username + ", orderNum=" + orderNum + ", shippingCost=" + shippingCost
				+ ", orderTotal=" + orderTotal + ", orderDate=" + orderDate + ", date_created=" + date_created + "]";
	}	

}//end class
