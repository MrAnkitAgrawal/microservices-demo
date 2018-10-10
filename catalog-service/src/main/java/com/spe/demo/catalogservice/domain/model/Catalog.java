package com.spe.demo.catalogservice.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="catalog_details", catalog="db_catalog")
public class Catalog implements Serializable {
	@Id
	@Column(name="item_code")
	@NotNull
	private Integer itemCode;
	
	@Column(name="item_name", unique=true, length=20)
	@NotNull
	private String itemName;
	
	@Column(name="description", length=50)
	private String description;
	
	@Column(name="item_price", precision=2)
	@Min(value=0, message="Price cannot be negative")
	private float price;
	
	@Column(name="inventory")
	@Min(value=0, message="Inventory cannot be negative")
	private int inventory;
	
	protected Catalog() {
	}

	public Catalog(Integer itemCode, String itemName, String description, float price, int inventory) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
	}

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + inventory;
		result = prime * result + ((itemCode == null) ? 0 : itemCode.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + Float.floatToIntBits(price);
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
		Catalog other = (Catalog) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (inventory != other.inventory)
			return false;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Catalog [itemCode=" + itemCode + ", itemName=" + itemName + ", description=" + description + ", price="
				+ price + ", inventory=" + inventory + "]";
	}
}
