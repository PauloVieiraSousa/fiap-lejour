package com.fiap.lejour.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleForCategory {
	private String category;
	private double value;
	
	public SaleForCategory(String category, double value) {
		this.category = category;
		this.value = value;
	}
}
