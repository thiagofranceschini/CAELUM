package br.com.caelum.cdc.models;

import java.math.BigDecimal;

public class PaymentData {
	private BigDecimal value;

	public PaymentData(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
}
