package com.fiap.lejour.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalePerMonth {

	private String month;
	private double value;
	
	public SalePerMonth(String month, double value) {
		this.month = month;
		this.value = value;
	}
}
