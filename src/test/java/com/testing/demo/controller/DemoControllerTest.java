package com.testing.demo.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.demo.beans.User;
import com.testing.demo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DemoController.class, secure = false)
public class DemoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void getUser() throws Exception {

		User user = new User();
		user.setId(3);
		user.setName("Shyam");
		user.setEmail("shyam@gmail.com");
		user.setAddress("Agra");

		Mockito.when(userService.getUser()).thenReturn(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{id:3, name:Shyam, email:shyam@gmail.com, address : Agra}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		System.out.println("**************** First Case ********************");
	}

	@Test
	public void AddUser() throws Exception {

		User user = new User();
		user.setId(2);
		user.setName("Ram2");
		user.setEmail("ram2@gmail.com");
		user.setAddress("New Delhi-2");
		String expected = "user added";

		Mockito.when(userService.AddUser(Mockito.any(User.class))).thenReturn(expected);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adduser").content(convertObjectToJsonBytes(user))
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		System.out.println("**************** Second Case ********************");
	}

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
}
