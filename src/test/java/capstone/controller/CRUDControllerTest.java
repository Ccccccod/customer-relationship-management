/**
 * 
 */
package capstone.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.BackEndCapstoneApplication;
import capstone.entity.BaseEntity;
import capstone.model.Identifiable;
import capstone.service.AbstractService;

/**
 * CRUDControllerTest
 * @author Tuna
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BackEndCapstoneApplication.class)
@WebAppConfiguration
abstract class CRUDControllerTest< //
		CreateDto extends Object & Identifiable<ID>, //
		UpdateDto extends Object & Identifiable<ID>, //
		Response extends Object & Identifiable<ID>, //
		Entity extends BaseEntity<ID>, //
		Repository extends JpaRepository<Entity, ID>, //
		Service extends AbstractService<CreateDto, UpdateDto, Response, Entity, Repository, ID>, //
		Controller extends CRUDController<CreateDto, UpdateDto, Response, Entity, Repository, Service, ID>, //
		ID extends Serializable //
> {

	@Autowired
	WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;
	
	protected ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objectMapper = new ObjectMapper();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, clazz);
	}
	
	@MockBean
	protected Repository repository;
	
	protected String contentType = "application/json;charset=UTF-8";
	
	protected abstract String url();
	
	protected abstract List<Entity> resources();
	
	protected abstract Entity resource();

	@Test
	public void testGetAll() throws Exception {
		Mockito.when(repository.findAll()).thenReturn(resources());

		MvcResult mvcResult = mockMvc.perform(get(url()).contentType(this.contentType)).andExpect(status().isOk())
				.andReturn();
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonResponse = mapToJson(resources());

		assertEquals(objectMapper.readTree(expectedJsonResponse), objectMapper.readTree(actualJsonResponse));
	}

	@Test
	public void testGetById() throws Exception {
		Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(resource()));

		MvcResult mvcResult = mockMvc.perform(get(url() + "/" + resource().getId())).andExpect(status().isOk())
				.andReturn();
	

		String actualJsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonResponse = mapToJson(resource());

		assertEquals(objectMapper.readTree(expectedJsonResponse), objectMapper.readTree(actualJsonResponse));
	}

	@Test
	public void testCreateUpdate() throws Exception {
		Mockito.when(repository.save(any())).thenReturn(resource());
		
		System.out.println("createResource" + mapToJson(resource()).toString());
		MvcResult mvcResult = mockMvc.perform(
				post(url()).contentType(this.contentType).content(mapToJson(resource()))
				).andExpect(status().isCreated()).andReturn();
		System.out.println("mvcResult" + mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonResponse = mapToJson(resource());

		assertEquals(objectMapper.readTree(expectedJsonResponse), objectMapper.readTree(actualJsonResponse));
	}

	@Test
	public void testDelete() throws Exception {
		ID id = resource().getId();
		List<ID> ids = Arrays.asList(id);
		
		Mockito.doNothing().when(repository).deleteAllById(ids);
		
		mockMvc.perform(
				delete(url()).contentType(contentType).content(mapToJson(ids))
				).andExpect(status().isOk()).andReturn();
		
	}

}
