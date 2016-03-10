package com.my_impl.rest;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.my_impl.MainApplication;

import com.my_impl.service.JobsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebAppConfiguration
public class RestTaxControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private JobsService jobsService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
	    this.mockMvc = webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testJobsServiceShouldComplete() {
		Assert.notNull(jobsService);
	}
	
	@Test
	public void testSendAndReceiveShouldComplete() throws Exception {
		mockMvc.perform(get("/tax/getTaxIndicators/10.0/NetAmount/100.0"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testSendAndReceiveShouldFailBecauseOfInvalidVatRate() throws Exception {
		mockMvc.perform(get("/tax/getTaxIndicators/18.0/NetAmount/100.0"))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testSendAndReceiveShouldFailBecauseOfInvalidAmountType() throws Exception {
		mockMvc.perform(get("/tax/getTaxIndicators/10.0/NetBUGAmount/100.0"))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testSendAndReceiveShouldFailBecauseOfInvalidAmountValue() throws Exception {
		mockMvc.perform(get("/tax/getTaxIndicators/10.0/NetAmount/-100.0"))
				.andExpect(status().is4xxClientError());
	}
}
