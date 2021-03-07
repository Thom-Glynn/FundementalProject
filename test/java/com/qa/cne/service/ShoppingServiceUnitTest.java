package com.qa.cne.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.cne.persistance.domain.ShoppingList;
import com.qa.cne.persistance.repository.ShoppingRepository;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class ShoppingServiceUnitTest {
	// real thing were testing
	@Autowired
	private ShoppingService service;
	// fake repository to bounce requests off
	@MockBean
	private ShoppingRepository repo;

//	@Autowired
//	private ModelMapper mapper;

//	private ShoppingList mapToDTO(ShoppingList shoppingList) {
//		return this.mapper.map(shoppingList, ShoppingList.class);
//	}

	private final ShoppingList TEST_ShoppingList1 = new ShoppingList("Chicken Nuggets", 1, 1.25f);
	private final ShoppingList TEST_ShoppingList2 = new ShoppingList("Scalops", 12, 1.50f);
	private final ShoppingList TEST_ShoppingList3 = new ShoppingList("Zuchini", 4, .75f);
	private List<ShoppingList> ShoppingListS = List.of(TEST_ShoppingList1,TEST_ShoppingList2,TEST_ShoppingList3);

	@Test
	void createTest() throws Exception {
		when(this.repo.save(TEST_ShoppingList1)).thenReturn(TEST_ShoppingList1);
		assertThat(this.service.create(TEST_ShoppingList1)).isEqualTo(TEST_ShoppingList1);
		verify(this.repo, atLeastOnce()).save(TEST_ShoppingList1);
	}

	@Test
	void readAllTest() throws Exception {
		when(this.repo.findAll()).thenReturn(ShoppingListS);
		assertThat(this.service.readAll().isEmpty()).isFalse();
		verify(this.repo, atLeastOnce()).findAll();
	}

//	@Test
//	void updateTest() throws Exception {
//		when(this.repo.findById(TEST_ShoppingList1.getID())).thenReturn(Optional.of(TEST_ShoppingList1));
//		when(this.repo.save(TEST_ShoppingList1)).thenReturn(TEST_ShoppingList1);
//		assertThat(this.service.updateById(this.mapToDTO(TEST_ShoppingList1), TEST_ShoppingList1.getID()))
//				.isEqualTo(this.mapToDTO(TEST_ShoppingList1));
//		verify(this.repo, atLeastOnce()).findById(TEST_ShoppingList1.getID());
//		verify(this.repo, atLeastOnce()).save(TEST_ShoppingList1);
//	}

	@Test
	void deleteTest() throws Exception {
		boolean found = false;
		when(this.repo.existsById(TEST_ShoppingList1.getID())).thenReturn(found);
		assertThat(this.service.deleteById(TEST_ShoppingList1.getID())).isNotEqualTo(found);
		verify(this.repo, atLeastOnce()).existsById(TEST_ShoppingList1.getID());
	}

	@Test
	void findByNameTest() throws Exception {
		when(this.repo.findByName(TEST_ShoppingList1.getItemName())).thenReturn(ShoppingListS);
		assertThat(this.repo.findByName(TEST_ShoppingList1.getItemName())).asList().isEqualTo(ShoppingListS);
		verify(this.repo, atLeastOnce()).findByName(TEST_ShoppingList1.getItemName());
	}
}
