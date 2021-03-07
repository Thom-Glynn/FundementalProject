package com.qa.cne.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.cne.persistance.domain.ShoppingList;

@Repository
public interface ShoppingRepository extends JpaRepository<ShoppingList, Long>{
	// SELECT * FROM ShoppingList WHERE itemName = '?' ;
	ShoppingList findShoppingListByItemName(String itemName);
	
	@Query(value = "SELECT * FROM FOOD where item_name = ?1", nativeQuery = true)
		    List<ShoppingList> findByName(String name);
	
	@Query(value = "SELECT * FROM FOOD ORDER BY item_name", nativeQuery = true)
    List<ShoppingList> orderByName();
	
}
