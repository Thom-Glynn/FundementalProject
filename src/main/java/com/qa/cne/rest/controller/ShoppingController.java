package com.qa.cne.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cne.persistance.domain.ShoppingList;
import com.qa.cne.service.ShoppingService;

@CrossOrigin
@RestController
@RequestMapping("/ShoppingList")
public class ShoppingController {

	// passes things to the service
	private ShoppingService service;

	@Autowired
	public ShoppingController(ShoppingService service) {
		super();
		this.service = service;
	}
	// create/
	public ShoppingController() {
		
	}
	@PostMapping("/create")
	public ResponseEntity<ShoppingList> create(@RequestBody ShoppingList shoppingList) {
		ShoppingList createdObject = this.service.create(shoppingList);
		return new ResponseEntity<>(createdObject, HttpStatus.CREATED);
	}

	// read by id
	@GetMapping("/read/byId/{id}")
	public ResponseEntity<ShoppingList> readById(@PathVariable Long id) {
		ShoppingList returnedObject = this.service.readById(id);
		return ResponseEntity.ok(returnedObject);
	}

	// read All
	@GetMapping("/read")
	public ResponseEntity<List<ShoppingList>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// Update by id
	@PutMapping("/update/{id}")
	public ResponseEntity<ShoppingList> updateById(@PathVariable Long id, @RequestBody ShoppingList shoppingList) {
		ShoppingList updatedObject = this.service.updateById(id, shoppingList);
		return new ResponseEntity<>(updatedObject, HttpStatus.ACCEPTED);
	}

	// deleteById
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ShoppingList> deleteById(@PathVariable Long id) {
		if (this.service.deleteById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//find by item name
//	@GetMapping("/read/byitem/{itemName}")
//	public ResponseEntity<ShoppingList> findShoppingListByItemName(@PathVariable String itemName){
//		ShoppingList found = this.service.findShoppingListByItemName(itemName);
//		return ResponseEntity.ok(found);
//	}
}
