package com.my_impl.soap;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.my_domain.compute_vat_net_gross.GetJobsRequest;
import com.my_domain.compute_vat_net_gross.JobType;
import com.my_domain.compute_vat_net_gross.TypeOfAmountType;
import com.my_impl.MainApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApplication.class)
@WebIntegrationTest(randomPort = true)
public class SoapJobsEndpointTest {

	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	@Value("${local.server.port}")
	private int port = 0;

	@Before
	public void init() throws Exception {
		marshaller.setPackagesToScan(ClassUtils.getPackageName(GetJobsRequest.class));
		marshaller.afterPropertiesSet();
	}

	@Test
	public void testSendAndReceiveShouldComplete() {
		GetJobsRequest request = new GetJobsRequest();
		JobType jobType = new JobType();
		TypeOfAmountType toat = new TypeOfAmountType();
		toat.setNetAmount(new BigDecimal(1000.0));
		jobType.setAmount(toat);
		jobType.setVatRate(new BigDecimal(12.0));
		request.getJob().add(jobType);
		assertNotNull(new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:"
				+ port + "/ws", request));
	}
	
	@Test
	public void testSendAndReceiveShouldFailBecauseOfMissingParameters() {
		GetJobsRequest request = new GetJobsRequest();
		JobType jobType = new JobType();
		TypeOfAmountType toat = new TypeOfAmountType();
		toat.setNetAmount(new BigDecimal(1000.0));
		jobType.setAmount(toat);
		//jobType.setVatRate(new BigDecimal(12.0));
		request.getJob().add(jobType);
		try {
			new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:"
					+ port + "/ws", request);
		} catch (Exception e) {
			assert(e.getMessage().equals("Validation error"));
		}
		
	}
	
	@Test
	public void testSendAndReceiveShouldFailBecauseOfVatRateNotInEnum() {
		GetJobsRequest request = new GetJobsRequest();
		JobType jobType = new JobType();
		TypeOfAmountType toat = new TypeOfAmountType();
		toat.setNetAmount(new BigDecimal(1000.0));
		jobType.setAmount(toat);
		jobType.setVatRate(new BigDecimal(16.0));
		request.getJob().add(jobType);
		try {
			new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:"
					+ port + "/ws", request);
		} catch (Exception e) {
			assert(e.getMessage().equals("Validation error"));
		}
		
	}
	
	@Test
	public void testSendAndReceiveShouldFailBecauseOfNegativeAmount() {
		GetJobsRequest request = new GetJobsRequest();
		JobType jobType = new JobType();
		TypeOfAmountType toat = new TypeOfAmountType();
		toat.setNetAmount(new BigDecimal(-1000.0));
		jobType.setAmount(toat);
		jobType.setVatRate(new BigDecimal(12.0));
		request.getJob().add(jobType);
		try {
			new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:"
					+ port + "/ws", request);
		} catch (Exception e) {
			assert(e.getMessage().equals("Validation error"));
		}
		
	}

}
