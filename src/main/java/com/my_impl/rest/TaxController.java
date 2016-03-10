package com.my_impl.rest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import com.my_impl.exceptions.InvalidAmountValueException;
import com.my_impl.exceptions.InvalidTypeValueException;
import com.my_impl.exceptions.InvalidVatRateException;
import com.my_impl.model.AmountType;
import com.my_impl.model.Job;
import com.my_impl.model.VatRateType;
import com.my_impl.service.JobsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tax")
public class TaxController {

	public static final String COMMA = ",";
	
    @Autowired
    JobsService jobsService;
    
    public TaxController() {
    	
    }
    
    @RequestMapping(value="/getTaxIndicators/{vatRate}/{typeOfAmount}/{valueOfAmount}", method=RequestMethod.GET, produces="application/json")
    @ApiOperation(value = "Get all of indicators (Gross Amount, Net Amount, Vat Amount) based on Vat Rate and one of those indicators", notes = "Need to provide a \"Value Of Amount\" field value greater than 0 for avoiding an error message.", response = JobDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful retrieval of all tax indicators", response = JobDTO.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public JobDTO getJob(@ApiParam(value = "VatRate", allowableValues="10.0,12.0,13.0,20.0", defaultValue="10.0") @PathVariable BigDecimal vatRate,
    					 @ApiParam(value = "TypeOfAmount", allowableValues="NetAmount,GrossAmount,VatAmount", defaultValue="NetAmount") @PathVariable String typeOfAmount,
    					 @PathVariable BigDecimal valueOfAmount) throws Exception {
    
    	
    	if (!VatRateType.getVatRatesAsOrderedSet().contains(vatRate)) {
    		throw new InvalidVatRateException("The Vat Rate must be in [" + VatRateType.getVatRatesAsString() + "] set.");
    	}
    	
    	
    	if (!Arrays.asList(AmountType.values()).parallelStream().map((AmountType at) -> at.getName()).collect(Collectors.toList()).contains(typeOfAmount)) {
    		throw new InvalidTypeValueException("The Type of Amount must be in [" + AmountType.getNamesAsString() + "] set.");
    	}
    	
    	if (valueOfAmount.compareTo(new BigDecimal(0)) <= 0)
    	{
    		throw new InvalidAmountValueException();
    	}
    	
    	final Job job = new Job();
    	job.setVatRate(vatRate);
    	if (typeOfAmount.equals(AmountType.GROSS_AMOUNT.getName())) {
    		job.setGrossAmount(valueOfAmount);
    	} else if (typeOfAmount.equals(AmountType.NET_AMOUNT.getName())) {
    		job.setNetAmount(valueOfAmount);
    	} else if (typeOfAmount.equals(AmountType.VAT_AMOUNT.getName())) {
    		job.setVatAmount(valueOfAmount);
    	}
    		
    	
    	return new JobDTO(jobsService.computeValues(job));
    }
}
