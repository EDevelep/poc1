package com.neosoft;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestWebApp extends Poc1ApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	@Test
	public void testUserDetail() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.username").value("jatin")).andExpect(jsonPath("$.emailid").value("jatin@neosoft.com"))
				.andExpect(jsonPath("$.password").value("jatin@123")).andExpect(jsonPath("$.surname").value("sharma"))
				.andExpect(jsonPath("$.pincode").value("203201")).andExpect(jsonPath("$.dateofbirth").value("12/06/1995"))
				.andExpect(jsonPath("$.Joiningdate").value("12/06/2021")).andExpect(jsonPath("$.active").value('Y'));

	}
}
