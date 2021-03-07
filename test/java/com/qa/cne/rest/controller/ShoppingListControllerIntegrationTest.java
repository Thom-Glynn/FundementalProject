package com.qa.cne.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cne.persistance.domain.ShoppingList;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema.sql", "classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ShoppingListControllerIntegrationTest {
	
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper jsonifier;

    private final String URI = "/ShoppingList";
    
    private final ShoppingList TEST_ShoppingList1 = new ShoppingList("Chicken Nuggets", 1, 1.25f);
	private final ShoppingList TEST_ShoppingList2 = new ShoppingList("Scalops", 12, 1.50f);
	private final ShoppingList TEST_ShoppingList3 = new ShoppingList("Zuchini", 4, .75f);
	private List<ShoppingList> ShoppingListS = List.of(TEST_ShoppingList1,TEST_ShoppingList2,TEST_ShoppingList3);
	
	@Test
    void createTest() throws Exception {
        this.mvc.perform(post(URI + "/create").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(this.jsonifier.writeValueAsString(TEST_ShoppingList1)))
                .andExpect(status().isCreated())
                .andExpect(content().json(this.jsonifier.writeValueAsString(TEST_ShoppingList1)));
    }

	@Test
    void readOneTest() throws Exception {
		this.mvc.perform(get(URI + "/read/byId/" + TEST_ShoppingList1.getID()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonifier.writeValueAsString(TEST_ShoppingList1)));
    }
	
	@Test
    void readAllTest() throws Exception {
        this.mvc.perform(get(URI + "/read").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json(this.jsonifier
                        .writeValueAsString(ShoppingListS.stream().collect(Collectors.toList()))));
    }
	
	@Test
    void updateTest() throws Exception {
        this.mvc.perform(put(URI + "/update/" + TEST_ShoppingList1.getID()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(this.jsonifier.writeValueAsString(TEST_ShoppingList1)))
                .andExpect(status().isAccepted())
                .andExpect(content().json(this.jsonifier.writeValueAsString(TEST_ShoppingList1)));
    }
	
//	@Test
//    void findByNameTest() throws Exception {
//        this.mvc.perform(get(URI + "/read/byitem/" + TEST_ShoppingList1.getItemName()).accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(this.jsonifier.writeValueAsString(ShoppingListS.stream()
//                        .filter(e -> e.getItemName().equals(TEST_ShoppingList1.getItemName())).collect(Collectors.toList()))));
//    }
	
	 @Test
	    void deleteTest() throws Exception {
	        this.mvc.perform(delete(URI + "/delete/" + TEST_ShoppingList1.getID())).andExpect(status().isNoContent());
	    }
}
