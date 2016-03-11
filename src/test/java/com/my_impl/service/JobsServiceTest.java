package com.my_impl.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import com.my_impl.model.Job;

@RunWith(MockitoJUnitRunner.class)
public class JobsServiceTest {

	@InjectMocks 
	private JobsService jobsService;
	private Job job1, job2, job3, job1Result, job2Result, job3Result;
	
	@Before
	public void setup() {
		job1 = new Job
				.JobBuilder()
				.withNetAmount(new BigDecimal(100.0))
				.withVatRate(new BigDecimal(20.0))
				.build();
		
		job2 = new Job
				.JobBuilder()
				.withVatAmount(new BigDecimal(247.0))
				.withVatRate(new BigDecimal(12.0))
				.build();
		
		job3 = new Job
				.JobBuilder()
				.withGrossAmount(new BigDecimal(100.0))
				.withVatRate(new BigDecimal(20.0))
				.build();
		
		job1Result = new Job
				.JobBuilder()
				.withNetAmount(new BigDecimal(100.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatAmount(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withGrossAmount(new BigDecimal(120.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatRate(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.build();
	
		job2Result = new Job
				.JobBuilder()
				.withNetAmount(new BigDecimal(2058.3333).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatAmount(new BigDecimal(247.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withGrossAmount(new BigDecimal(2305.3333).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatRate(new BigDecimal(12.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.build();
	
		job3Result = new Job
				.JobBuilder()
				.withNetAmount(new BigDecimal(83.3333).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatAmount(new BigDecimal(16.6667).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withGrossAmount(new BigDecimal(100.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatRate(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.build();
	}
	
	@Test
	public void testJobsServiceInstantiationShouldComplete() {
		Assert.notNull(jobsService);
	}
	
	@Test
	public void testComputeValuesForMultipleJobsShouldComplete() {
		List<Job> resultList = jobsService.computeValuesForMultipleJobs(Arrays.asList(new Job[]{job1, job2, job3}));
		List<Job> expectedResultList = Arrays.asList(new Job[]{job1Result, job2Result, job3Result});
		
		Assert.isTrue(resultList.equals(expectedResultList));
	}
}
