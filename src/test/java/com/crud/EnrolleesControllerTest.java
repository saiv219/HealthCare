package com.crud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crud.controller.EnrolleesController;
import com.crud.entity.Enrollees;
import com.crud.services.EnrolleesService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EnrolleesController.class, secure = false)
public class EnrolleesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EnrolleesService enrolleesService;

	Enrollees enrollees=new Enrollees(1, "Arjun", true, "09/09/2002", "955555");

	String jsonData = "{\"name\":\"Arjun\",\"status\":true,\"dob\":\"09/09/2002\",\"phoneNumber\":\"955555\"}";

	
	@Test
	public void retrieveDetailsForEnrollees() throws Exception {

		Mockito.when(
				enrolleesService.getEnrollees(Mockito.anyInt())).thenReturn(enrollees);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/enrolleesService/getEnrollees/1").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":1,\"name\":\"Arjun\",\"status\":true,\"dob\":\"09/09/2002\",\"phoneNumber\":\"955555\"}";

		System.out.println(expected);
		System.out.println(result.getResponse()
				.getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	@Test
	public void createStudentEnrollees() throws Exception {
		Enrollees enrollees=new Enrollees(1, "Arjun", true, "09/09/2002", "955555");

		Mockito.when(
				enrolleesService.createEnrollees(
						Mockito.any(Enrollees.class))).thenReturn(enrollees);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/enrolleesService/addEnrollees")
				.accept(MediaType.APPLICATION_JSON).content(jsonData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String expected = "{\"id\":1,\"name\":\"Arjun\",\"status\":true,\"dob\":\"09/09/2002\",\"phoneNumber\":\"955555\"}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		assertEquals(HttpStatus.OK.value(), response.getStatus());


	}

	@Test
	public void updateEnrollees() throws Exception {
		Enrollees enrollees=new Enrollees(1, "Arjun1", true, "09/09/2002", "584826554");

		Mockito.when(
				enrolleesService.updateEnrollees(Mockito.anyInt(), 
						Mockito.any(Enrollees.class))).thenReturn(enrollees);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/enrolleesService/modifyEnrollees/1")
				.accept(MediaType.APPLICATION_JSON).content(jsonData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String expected = "{\"id\":1,\"name\":\"Arjun1\",\"status\":true,\"dob\":\"09/09/2002\",\"phoneNumber\":\"584826554\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	@Test
	public void deleteEnrollees() throws Exception {

		Mockito.when(
				enrolleesService.deleteEnrollees(Mockito.anyInt())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/enrolleesService/removeEnrollees/1")
				.accept(MediaType.APPLICATION_JSON).content(jsonData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String expected = "{\"id\":1,\"name\":\"Arjun1\",\"status\":true,\"dob\":\"09/09/2002\",\"phoneNumber\":\"584826554\"}";
		assertEquals("Enrollees has been deleted successfully", result.getResponse()
				.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	
}
