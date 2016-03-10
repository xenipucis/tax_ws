package com.my_impl.rest;

import java.math.BigDecimal;

import com.my_impl.model.Job;

public class JobDTO {

	private BigDecimal grossAmount;
	private BigDecimal vatAmount;
	private BigDecimal netAmount;
	private BigDecimal vatRate;
	
	public JobDTO(final Job job) {
		grossAmount = job.getGrossAmount();
		vatAmount = job.getVatAmount();
		netAmount = job.getNetAmount();
		vatRate = job.getVatRate();
	}
	
	public BigDecimal getGrossAmount(){
		return grossAmount;
	}
	
	public BigDecimal getVatAmount(){
		return vatAmount;
	}
	
	public BigDecimal getNetAmount(){
		return netAmount;
	}
	
	public BigDecimal getVatRate(){
		return vatRate;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("\nJobDTO(")
				.append("\n netAmount = " + netAmount)
				.append("\n, grossAmount = " + grossAmount)
				.append("\n, vatAmount = " + vatAmount)
				.append("\n, vatRate = " + vatRate)
				.append("\n)").toString();
	}
}
