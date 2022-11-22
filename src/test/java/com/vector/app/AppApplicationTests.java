package com.vector.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vector.app.model.User;
import com.vector.app.repository.UserRepository;
import com.vector.app.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
class AppApplicationTests {
	MockMvc mockMvc;
	@MockBean UserRepository userRepo;
	@MockBean UserService userService;

	@BeforeEach
	void setup(WebApplicationContext wac) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	void testHomePage() throws Exception{
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	void acceptFriendRequestTest() {
		User user = new User("Sumit","Shrestha","meV3cT0R","hello","thisispassword",new Date(),new Date(),"Male");
		User friend = new User("john","cena","johncena","youcantseeme","thisispassword",new Date(),new Date(),"Male");
		when(userRepo.save(user)).thenReturn(user);
		when(userRepo.save(friend)).thenReturn(friend);
	
	}
}
