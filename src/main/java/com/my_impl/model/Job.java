package com.my_impl.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Job {

	public static final int NUMBER_OF_DECIMALS = 5;
	public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_DOWN;
	
	private BigDecimal grossAmount;
	private BigDecimal vatAmount;
	private BigDecimal netAmount;
	private BigDecimal vatRate;
	
	private Job() {
		
	}
	
	private Job(JobBuilder jobBuilder) {
		this.grossAmount = jobBuilder.grossAmount;
		this.netAmount = jobBuilder.netAmount;
		this.vatAmount = jobBuilder.vatAmount;
		this.vatRate = jobBuilder.vatRate;
	}
	
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}
	
	public void setGrossAmount(final BigDecimal _grossAmount) {
		grossAmount = _grossAmount;
	}
	
	public BigDecimal getVatAmount() {
		return vatAmount;
	}
	
	public void setVatAmount(final BigDecimal _vatAmount) {
		vatAmount = _vatAmount;
	}
	
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	
	public void setNetAmount(final BigDecimal _netAmount) {
		netAmount = _netAmount;
	}
	
	public BigDecimal getVatRate() {
		return vatRate;
	}
	
	public void setVatRate(final BigDecimal _vatRate) {
		vatRate = _vatRate;
	}
	
	public static class JobBuilder {
		private BigDecimal grossAmount;
		private BigDecimal vatAmount;
		private BigDecimal netAmount;
		private BigDecimal vatRate;
		
		
		
		public JobBuilder withGrossAmount(BigDecimal grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}
		
		public JobBuilder withNetAmount(BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}
		
		public JobBuilder withVatAmount(BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}
		
		public JobBuilder withVatRate(BigDecimal vatRate) {
			this.vatRate = vatRate;
			return this;
		}
		
		public Job build () {
			return new Job(this);
		}
	}
	
	@Override
	public boolean equals(Object that) {
		if (that == null) { return false; }
		if (that.getClass() != getClass()) {
			return false;
		}
		
		Job job = (Job) that;
		
		return ((this.getGrossAmount().setScale(Job.NUMBER_OF_DECIMALS).subtract(job.getGrossAmount().setScale(Job.NUMBER_OF_DECIMALS)).doubleValue() == 0))
			&& ((this.getNetAmount().setScale(Job.NUMBER_OF_DECIMALS).subtract(job.getNetAmount().setScale(Job.NUMBER_OF_DECIMALS)).doubleValue() == 0))
            && ((this.getVatAmount().setScale(Job.NUMBER_OF_DECIMALS).subtract(job.getVatAmount().setScale(Job.NUMBER_OF_DECIMALS)).doubleValue() == 0))
			&& ((this.getVatRate().setScale(Job.NUMBER_OF_DECIMALS).subtract(job.getVatRate().setScale(Job.NUMBER_OF_DECIMALS)).doubleValue() == 0));		
	}
}
