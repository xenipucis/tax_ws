package com.my_impl.soap;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.my_domain.compute_vat_net_gross.GetJobsRequest;
import com.my_domain.compute_vat_net_gross.GetJobsResponse;
import com.my_domain.compute_vat_net_gross.JobType;
import com.my_domain.compute_vat_net_gross.ResponseJobType;
import com.my_impl.model.Job;
import com.my_impl.service.JobsService;


@Endpoint
public class JobsEndpoint {
	private static final String NAMESPACE_URI = "http://my_domain.com/compute_vat_net_gross";

	private JobsService jobsService;

	@Autowired
	public JobsEndpoint(final JobsService jobsService) {
		this.jobsService = jobsService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getJobsRequest")
	@ResponsePayload
	public GetJobsResponse getJobs(@RequestPayload final GetJobsRequest request) {
		
		
		final GetJobsResponse response = new GetJobsResponse();

		response
			.getJob()
			.addAll(
					jobsService
					.computeValuesForMultipleJobs(
							request
							.getJob()
							.parallelStream()
							.map((JobType jt) ->  
									 new Job
									 		.JobBuilder()
									 		.withGrossAmount(jt.getAmount().getGrossAmount())
									 		.withNetAmount(jt.getAmount().getNetAmount())
									 		.withVatAmount(jt.getAmount().getVatAmount())
									 		.withVatRate(jt.getVatRate())
									 		.build()
							).collect(Collectors.toList())
					)
					.parallelStream()
					.map((Job job) -> {
						ResponseJobType responseJobType = new ResponseJobType();
						responseJobType.setGrossAmount(job.getGrossAmount());
						responseJobType.setNetAmount(job.getNetAmount());
						responseJobType.setVatAmount(job.getVatAmount());
						responseJobType.setVatRate(job.getVatRate());
						return responseJobType;
					})
					.collect(Collectors.toList()))
			;
		
		return response;
	}
}
