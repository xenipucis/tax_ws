package com.my_impl.model;

public enum AmountType {

	GROSS_AMOUNT("GrossAmount"),
	NET_AMOUNT("NetAmount"),
	VAT_AMOUNT("VatAmount");
	
	private static final String COMMA = ",";
	private String name;
	
	AmountType(String _name) {
		name = _name;
	}
	
	public String getName() {
		return name;
	}
	
	public static AmountType getTypeOfAmountByName(final String name) {
		for (AmountType toa : AmountType.values()) {
			if (toa.getName().equals(name))
				return toa;
		}
		return null;
	}
	
	public static final String getNamesAsString() {
		final StringBuilder sb = new StringBuilder();
		for (AmountType at : AmountType.values()) {
			sb.append(at.getName())
				.append(COMMA);
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
}
