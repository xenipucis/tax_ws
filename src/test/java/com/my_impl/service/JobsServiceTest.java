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
				.withNetAmount(new BigDecimal(100.0))
				.withVatRate(new BigDecimal(20.0))
				.build();
		
		job3 = new Job
				.JobBuilder()
				.withNetAmount(new BigDecimal(100.0))
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
				.withNetAmount(new BigDecimal(100.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatAmount(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withGrossAmount(new BigDecimal(120.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatRate(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.build();
	
		job3Result = new Job
				.JobBuilder()
				.withNetAmount(new BigDecimal(100.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatAmount(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withGrossAmount(new BigDecimal(120.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.withVatRate(new BigDecimal(20.0).setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE))
				.build();
	}
	
	@Test
	public void testJobsServiceInstantiationShouldComplete() {
		Assert.notNull(jobsService);
	}
	
	@Test
	public void testComputeValuesForMultipleJobsShouldComplete() {
		List<Job> resultList = jobsService.computeValuesForMultipleJobs(Arrays.asList(new Job[]{job1/*, job2, job3*/}));
		List<Job> expectedResultList = Arrays.asList(new Job[]{job1Result/*, job2Result, job3Result*/});
		
		
		//System.out.println();
		//Assert.isTrue(resultList.equals(expectedResultList));
		
		//System.out.println("GrossAmount " + resultList.get(0).getGrossAmount() + ", " + expectedResultList.get(0).getGrossAmount());
		
		//Assert.isTrue(resultList.get(0).getGrossAmount().equals(expectedResultList.get(0).getGrossAmount()));
		//Assert.isTrue(resultList.get(0).getNetAmount().equals(expectedResultList.get(0).getNetAmount()));
		//Assert.isTrue(resultList.get(0).getVatAmount().equals(expectedResultList.get(0).getVatAmount()));
		//Assert.isTrue(resultList.get(0).getVatRate().equals(expectedResultList.get(0).getVatRate()));
		
		/*System.out.println(resultList.get(0).getVatAmount());
		System.out.println(expectedResultList.get(0).getVatAmount());
		
		System.out.println(resultList.get(0).getVatRate());
		System.out.println(expectedResultList.get(0).getVatRate());*/
		
		Assert.isTrue(resultList.equals(expectedResultList));
	}
}
