package com.codedecode.demo.controller;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.PostingRepository;
import com.codedecode.demo.service.PostingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(HomePageController.class)
public class PostingControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostingService postingService;
	
	@MockBean
	private PostingRepository postingRepository;
	
	@Test
	public void addNewPostingTest() throws Exception {
		Posting posting = Posting.builder().recruiterName("FPT Software").phoneNumber("0123456789").emailContact("ericnguyen1606@gmail.com").build();
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/home/add/posting")
                .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		String expected = mapper.writeValueAsString(posting);
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(expected);
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
