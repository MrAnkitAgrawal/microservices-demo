package com.spe.demo.orderservice.domain.model;

public class Catalog {
	private Integer itemCode;
	private String itemName;
	private String description;
	private float price;
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
	public String toString() {
		return "Catalog [itemCode=" + itemCode + ", itemName=" + itemName + ", description=" + description + ", price="
				+ price + ", inventory=" + inventory + "]";
	}
}
