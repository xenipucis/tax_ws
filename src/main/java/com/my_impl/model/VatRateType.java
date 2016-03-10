package com.my_impl.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

public enum VatRateType {
	VatRate_10_0(new BigDecimal(10.0)),
	VatRate_12_0(new BigDecimal(12.0)),
	VatRate_13_0(new BigDecimal(13.0)),
	VatRate_20_0(new BigDecimal(20.0));
	
	private static final String COMMA = ",";
	private BigDecimal vatRate;
	
	VatRateType(final BigDecimal _rate) {
		vatRate = _rate;
	}
	
	public BigDecimal getVatRate() {
		return vatRate;
	}
	
	public static VatRateType getVatRateTypeByVatRate(final BigDecimal vatRate) {
		for (VatRateType vrt : VatRateType.values()) {
			if (vrt.getVatRate().equals(vatRate))
				return vrt;
		}
		return null;
	}
	
	public static final String getVatRatesAsString() {
		final StringBuilder sb = new StringBuilder();
		for (VatRateType vrt : VatRateType.values()) {
			sb.append(vrt.getVatRate())
				.append(COMMA);
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
	public static final Set<BigDecimal> getVatRatesAsOrderedSet() {
		final Set<BigDecimal> result = new TreeSet<>();
		for (VatRateType vrt : VatRateType.values()) {
			result.add(vrt.getVatRate());
		}
		return result;
	}
	
}
