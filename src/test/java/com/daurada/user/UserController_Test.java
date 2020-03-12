package com.daurada.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.daurada.DauradaApp;
import com.daurada.TestUtils;
import com.daurada.auth.Authority;
import com.daurada.dir.DIR;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DauradaApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
public class UserController_Test {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private User_Repo userRepo;
	
	@Autowired 
	private Role_Repo roleRepo;
	
	private static final User TEST_USER = new User(
			"Test User", "test@somemail.com", 
				"testpassword", "testpassword");
	
	@BeforeEach
	public void setUp() {
		userRepo.findByEmail(TEST_USER.getEmail()).ifPresent(
				user -> userRepo.delete(user));
	}
	
	//@Test
//	@Order(1)
	public void signUp_then_findCreatedUser() throws Exception  {
		
		mvc.perform(
				MockMvcRequestBuilders.post(DIR.SIGN_UP)
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtils.toJsonString(TEST_USER)))
				.andExpect(status().isCreated());
		
		userRepo.findByEmail(TEST_USER.getEmail())
				.orElseThrow(() -> new Exception(
						"Test User was not found in the repository!"));
	}
	
	@Test
	@Order(2)
	public void signIn_then_redirectedHome() throws Exception {
		roleRepo.saveAndFlush(new Role(Authority.USER.name()));
		signUp_then_findCreatedUser();
		
		mvc.perform(MockMvcRequestBuilders
				.post(DIR.SIGN_IN)
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.content(TestUtils.toFormUrlEncoded(TEST_USER)))
				.andExpect(status().is(302))
				.andExpect(redirectedUrlPattern(DIR.HOME + "*"));  
	}
	
	@Test
	@Order(3)
	public void signOut_then_redirectSignIn() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.get(DIR.SIGN_OUT))
				.andExpect(status().is(302))
				.andExpect(redirectedUrlPattern(DIR.SIGN_IN + "*"));
	}
}
