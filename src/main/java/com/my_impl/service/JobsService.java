package com.my_impl.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.my_impl.model.Job;

@Service
public class JobsService {

	public JobsService() {
		
	}
	
	public List<Job> computeValuesForMultipleJobs(final List<Job> jobs) {
		final List<Job> response = new ArrayList<>();
		
		for (Job job : jobs) {
			response.add(computeValues(job));
		}
		
		return response;
	}
	
	public Job computeValues(final Job job) {
		final BigDecimal vatRate = job.getVatRate() != null ? job.getVatRate().setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE) : null;
		final BigDecimal grossAmount = job.getGrossAmount() != null ? job.getGrossAmount().setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE) : null;
		final BigDecimal netAmount = job.getNetAmount() != null ? job.getNetAmount().setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE) : null;
		final BigDecimal vatAmount = job.getVatAmount() != null ? job.getVatAmount().setScale(Job.NUMBER_OF_DECIMALS, Job.DEFAULT_ROUNDING_MODE) : null;
		
		job.setVatRate(vatRate);
		
	    if (grossAmount != null) {
	    	job.setNetAmount(grossAmount
								.multiply(new BigDecimal(100))
								.divide(vatRate.add(new BigDecimal(100)), Job.DEFAULT_ROUNDING_MODE)
				.setScale(Job.NUMBER_OF_DECIMALS -1, Job.DEFAULT_ROUNDING_MODE));
	    	job.setVatAmount(grossAmount
								.subtract(grossAmount
											.multiply(new BigDecimal(100))
											.divide(vatRate.add(new BigDecimal(100))
													, Job.DEFAULT_ROUNDING_MODE))
				.setScale(Job.NUMBER_OF_DECIMALS -1, Job.DEFAULT_ROUNDING_MODE));
	    }
	    else if (netAmount != null) {
	    	job.setVatAmount(netAmount
								.multiply(vatRate)
								.divide(new BigDecimal(100))
				.setScale(Job.NUMBER_OF_DECIMALS -1, Job.DEFAULT_ROUNDING_MODE));
	    	job.setGrossAmount(netAmount
								.add(netAmount
										.multiply(vatRate)
										.divide(new BigDecimal(100)))
				.setScale(Job.NUMBER_OF_DECIMALS -1, Job.DEFAULT_ROUNDING_MODE));

	    }
	    else if (vatAmount != null) {
	    	job.setNetAmount(vatAmount
								.multiply(new BigDecimal(100))
								.divide(vatRate, Job.DEFAULT_ROUNDING_MODE)
				.setScale(Job.NUMBER_OF_DECIMALS -1, Job.DEFAULT_ROUNDING_MODE));
	    	job.setGrossAmount(vatAmount
								.multiply(new BigDecimal(100))
								.divide(vatRate, Job.DEFAULT_ROUNDING_MODE)
								.add(vatAmount)
				.setScale(Job.NUMBER_OF_DECIMALS -1, Job.DEFAULT_ROUNDING_MODE));
	    }
	    
	    return job;
	}    
}
