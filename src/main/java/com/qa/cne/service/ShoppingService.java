package com.qa.cne.service;

import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.cne.persistance.domain.ShoppingList;
import com.qa.cne.persistance.repository.ShoppingRepository;

@Service
public class ShoppingService {

	private ShoppingRepository repo;

	@Autowired
	public ShoppingService(ShoppingRepository repo) {
		super();
		this.repo = repo;
	}
	
	
	public ShoppingList create(ShoppingList shoppingList) {
		ShoppingList created = this.repo.save(shoppingList);
		return created;
		
	}
	
	public ShoppingList readById(Long id) {
		ShoppingList thingReadFromDB = this.repo.findById(id).get();
		return thingReadFromDB;
		
	}
	
	public ShoppingList findShoppingListByItemName(String itemName) {
		return this.repo.findShoppingListByItemName(itemName);
	}
	
	public List<ShoppingList> readAll() {
		List<ShoppingList> thingsReadFromDB = this.repo.findAll();
		return thingsReadFromDB;
		
	}
	
	public ShoppingList updateById(Long id, ShoppingList shoppingList) {
		ShoppingList thingToUpdate = this.repo.findById(id).get();
		
		thingToUpdate.setItemName(shoppingList.getItemName());
		thingToUpdate.setPrice(shoppingList.getPrice());
		thingToUpdate.setQuantity(shoppingList.getQuantity());
		
		return this.repo.save(thingToUpdate);
		
	}
	
	public boolean deleteById(Long id) {
		this.repo.deleteById(id);
		
		return !this.repo.existsById(id);
		
	}
}
