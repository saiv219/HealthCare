package com.crud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crud.controller.DependentsController;
import com.crud.entity.Dependents;
import com.crud.entity.Enrollees;
import com.crud.services.DependentsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DependentsController.class, secure = false)
public class DependentsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DependentsService dependentsService;

	Enrollees enrollees=new Enrollees(1, "postswagger", true, "11/12/2000", "1234567890");
	Dependents dependent=new Dependents(1,"hello","05/05/2000",enrollees);
	String jsonData = "{\"id\":1,\"name\":\"hello\",\"dob\":\"05/05/2000\",\"enrollees\":{\"id\":1,\"name\":\"postswagger\",\"status\":true,\"dob\":\"11/12/2000\",\"phoneNumber\":\"1234567890\"}}";


	@Test
	public void retrieveDetailsForDependents() throws Exception {

		Mockito.when(
				dependentsService.getDependentsFromEnrol(Mockito.anyInt(),Mockito.anyInt())).thenReturn(dependent);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/dependentService/getDependents/1/1").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());

		JSONAssert.assertEquals(jsonData, result.getResponse()
				.getContentAsString(), false);
	}


	@Test
	public void createDependents() throws Exception {

		Mockito.when(
				dependentsService.createDependentsFromEnrol(Mockito.anyInt(),
						Mockito.any(Dependents.class))).thenReturn(dependent);
//		String expected = "{\"id\":1,\"name\":\"Arjun\",\"dob\":\"09/08/2020\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/dependentService/addDependentToEnrol/1")
				.accept(MediaType.APPLICATION_JSON).content(jsonData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
System.out.println("Remove>>>>>"+result.getResponse()
				.getContentAsString());
		MockHttpServletResponse response = result.getResponse();
		JSONAssert.assertEquals(jsonData, result.getResponse()
				.getContentAsString(), false);
		

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());


	}

	@Test
	public void updateEnrollees() throws Exception {

		Mockito.when(
				dependentsService.modifyDePendentFromEnrol(Mockito.anyInt(), 
						Mockito.anyInt(), Mockito.any(Dependents.class))).thenReturn(dependent);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/dependentService/modifyDependentFromEnrol/1/1")
				.accept(MediaType.APPLICATION_JSON).content(jsonData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		JSONAssert.assertEquals(jsonData, result.getResponse()
				.getContentAsString(), false);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	@Test
	public void deleteDependentsFromEnrol() throws Exception {

		Mockito.when(
				dependentsService.deleteDependentsFromEnrol(Mockito.anyInt(),Mockito.anyInt())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/dependentService/removeDependentFromEnrollees/1/1")
				.accept(MediaType.APPLICATION_JSON).content(jsonData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("Dependent from Enrollees has been deleted successfully", result.getResponse()
				.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}


}
