package com.qa.cne.persistance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ShoppingList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@NotNull
	@Column
	private String itemName;
	
	@Min(0)
	@Column
	private int quantity;
	
	@NotNull
	@Min(0)
	@Column
	private float price;

	public ShoppingList(Long iD, @NotNull String itemName, @Min(0) int quantity, @NotNull @Min(0) float price) {
		super();
		ID = iD;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}	
	
	
	public ShoppingList(@NotNull String itemName, @Min(0) int quantity, @NotNull @Min(0) float price) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}

	public ShoppingList(){
		
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	

}
