package com.spe.demo.orderservice.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_details", catalog="db_order")
public class Order implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="order_id")
	private Integer orderId;

	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="item_code")
	private Integer itemCode;
	
	@Column(name="item_units")
	private Integer itemUnits;
	
	@Column(name="order_date")
	private LocalDateTime orderDate;
	
	@Column(name="order_status")
	private OrderStatus orderStatus;

	protected Order() {
	}

	public Order(Integer customerId, Integer itemCode, Integer itemUnits, LocalDateTime orderDate,
			OrderStatus orderStatus) {
		this.customerId = customerId;
		this.itemCode = itemCode;
		this.itemUnits = itemUnits;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}
	
	public Integer getItemUnits() {
		return itemUnits;
	}

	public void setItemUnits(Integer itemUnits) {
		this.itemUnits = itemUnits;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", itemCode=" + itemCode + ", itemUnits="
				+ itemUnits + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result + ((itemUnits == null) ? 0 : itemUnits.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (itemUnits == null) {
			if (other.itemUnits != null)
				return false;
		} else if (!itemUnits.equals(other.itemUnits))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		return true;
	}
}
