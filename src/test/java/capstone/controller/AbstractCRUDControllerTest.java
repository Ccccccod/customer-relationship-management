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

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import capstone.AbstractTest;
import capstone.entity.BaseEntity;
import capstone.model.Identifiable;

/**
 * AbstractCRUDControllerTest
 * @author Tuna
 *
 */
abstract class AbstractCRUDControllerTest< //
		CreateDto extends Object & Identifiable<ID>, //
		UpdateDto extends Object & Identifiable<ID>, //
		Response extends Object & Identifiable<ID>, //
		Entity extends BaseEntity<ID>, //
		Repository extends JpaRepository<Entity, ID>, //
		Controller extends AbstractCRUDController<CreateDto, UpdateDto, Response, Entity, Repository, ID>, //
		ID extends Serializable
> extends AbstractTest
{
	
	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	protected Controller controller;
	
	@MockBean
	protected Repository repository;
	
	protected String url = url();
	
	protected List<Entity> resources = resources();
	
	protected Entity resource = resource();
	
	protected abstract String url();
	
	protected abstract List<Entity> resources();
	
	protected abstract Entity resource();
	
	protected abstract CreateDto createResource();
	
	private String contentType = "application/json;charset=UTF-8";

	@Test
	public void testGetAll() throws Exception {
		Mockito.when(repository.findAll()).thenReturn(resources);

		MvcResult mvcResult = mockMvc.perform(get(url).contentType(this.contentType)).andExpect(status().isOk())
				.andReturn();
		
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonResponse = mapToJson(resources);

		assertEquals(objectMapper.readTree(expectedJsonResponse), objectMapper.readTree(actualJsonResponse));
	}
	
	@Test
	public void testGetById() throws Exception {
		Mockito.when(repository.findById(any())).thenReturn(Optional.ofNullable(resource));

		MvcResult mvcResult = mockMvc.perform(get(url + "/" + resource.getId())).andExpect(status().isOk())
				.andReturn();
	

		String actualJsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonResponse = mapToJson(resource);

		assertEquals(objectMapper.readTree(expectedJsonResponse), objectMapper.readTree(actualJsonResponse));
	}

	@Test
	public void testCreateUpdate() throws Exception {
		Mockito.when(repository.save(any())).thenReturn(resource);
		
		MvcResult mvcResult = mockMvc.perform(
				post(url).contentType(this.contentType).content(mapToJson(createResource()))
				).andExpect(status().isOk()).andReturn();
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonResponse = mapToJson(resource);

		assertEquals(objectMapper.readTree(expectedJsonResponse), objectMapper.readTree(actualJsonResponse));
	}
	
	@Test
	public void testDelete() throws Exception {
		ID id = resource.getId();
		List<ID> ids = Arrays.asList(id);
		
		Mockito.doNothing().when(repository).deleteAllById(ids);
		
		mockMvc.perform(
				delete(url).contentType(contentType).content(mapToJson(ids))
				).andExpect(status().isOk()).andReturn();
		
	}

	@After
	public void test() throws Exception {
//		Mockito.reset(repository);
	}
}
