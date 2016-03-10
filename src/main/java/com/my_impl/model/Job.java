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
	
	public Job() {
		
	}
	
	public Job(final BigDecimal _netAmount,
			   final BigDecimal _grossAmount,
			   final BigDecimal _vatAmount,
			   final BigDecimal _vatRate) {
		netAmount = _netAmount;
		grossAmount = _grossAmount;
		vatAmount = _vatAmount;
		vatRate = _vatRate;
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
}
