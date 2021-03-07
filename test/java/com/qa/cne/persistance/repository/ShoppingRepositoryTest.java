package com.qa.cne.persistance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.cne.persistance.domain.ShoppingList;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ShoppingRepositoryTest {
	@Autowired
	private ShoppingRepository repo;

	private final ShoppingList TEST_ShoppingList1 = new ShoppingList("Chicken Nuggets", 1, 1.25f);
	private final ShoppingList TEST_ShoppingList2 = new ShoppingList("Scalops", 12, 1.50f);
	private final ShoppingList TEST_ShoppingList3 = new ShoppingList("Zuchini", 4, .75f);

	private final List<ShoppingList> DATA_SET = List.of(TEST_ShoppingList1, TEST_ShoppingList2, TEST_ShoppingList3);

	@BeforeAll
	void init() {
		this.repo.saveAll(DATA_SET);
	}

	@Test
	void findByNameTest() throws Exception {
		assertThat(this.repo.findByName(TEST_ShoppingList1.getItemName()).stream().map(e -> e.getItemName())
				.collect(Collectors.toList()))
						.isEqualTo(DATA_SET.stream().filter(e -> e.getItemName().equals(TEST_ShoppingList1.getItemName()))
								.map(e -> e.getItemName()).collect(Collectors.toList()));
	}
//
}
