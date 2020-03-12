package com.daurada;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.daurada.base.BasicController;
import com.daurada.base.BasicEntity;
import com.daurada.base.EntityRepo;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DauradaApp.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
public abstract class BaseTest<T extends BasicEntity> {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private EntityRepo<T> repo;
	
	@Autowired 
	private BasicController<T> controller;
	
	private final T testEntity;
	
	public BaseTest(T testEntity) {
		this.testEntity = testEntity;
	}

	@BeforeEach
	public void setUp() {
		testEntity.setName(getTestName());
		ifTestEntityPresent_delete();
	}
	
	@AfterEach
	public void cleanUp() {
		ifTestEntityPresent_delete();
	}
	
	@Test
	@Order(1)
	public void retrieveAllEntities() throws Exception  {
		insertToRepo();
		
		mvc.perform(MockMvcRequestBuilders
				.get(controller.getAllPath()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].name")
						.value(testEntity.getName()));
	}
	
	@Test
	@Order(2)
	public void retrieveOneEntity() throws Exception  {
		insertToRepo();
		
		mvc.perform(MockMvcRequestBuilders
				.get(controller.getIdPath(testEntity.getId())))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.name")
						.value(testEntity.getName()));
	}
	
	@Test
	@Order(3)
	public void insertEntity_then_201() throws Exception  {
		mvc.perform(MockMvcRequestBuilders
				.post(controller.getAddPath())
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtils.toJsonString(testEntity)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.object.name")
						.value(testEntity.getName()));
	}
	
	@Test
	@Order(4)
	public void updateEntity() throws Exception  {
		repo.saveAndFlush(testEntity);
		
		testEntity.setName(TestUtils.EDITED);
		
		mvc.perform(MockMvcRequestBuilders
				.put(controller.getUpdatePath(testEntity.getId()))
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtils.toJsonString(testEntity)))
				.andExpect(status().isAccepted())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.object.name")
						.value(testEntity.getName()));
	}
	
	@Test
	@Order(5)
	public void deleteEntity() throws Exception  {
		insertToRepo();
		
		mvc.perform(MockMvcRequestBuilders
				.delete(controller.getDeletePath(testEntity.getId())))
				.andExpect(status().isAccepted())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.message").value("Deleted"));
	}
	
	protected void insertToRepo() {
		repo.saveAndFlush(testEntity);
	}

	private void ifTestEntityPresent_delete() {
		repo.findAll().stream()
			.filter(e -> e.getName().equals(getTestName())
					|| e.getName().equals(TestUtils.EDITED))
			.forEach(e -> repo.delete(e));
		repo.flush();
	}
	
	protected T getTestEntity() {
		return testEntity;
	}
	
	private String getTestName() {
		return "Test " + controller.getType() + "!";
	}
}
