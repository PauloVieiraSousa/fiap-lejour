package com.fiap.lejour.model;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class CostumerPerMonth {
	
	private String month;
	private Long value;
	
	
	public CostumerPerMonth(String month, Long value) {
		this.month = month;
		this.value = value;
	}
}
