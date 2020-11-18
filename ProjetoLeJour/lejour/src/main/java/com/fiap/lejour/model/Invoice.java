package com.fiap.lejour.model;

import java.time.LocalDate;
import java.time.Month;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
	
	private long id;
	private float weddingId;
	private float vendorId;
	private double amount;
	private LocalDate createAt;
	private boolean accepted;
	private String vendorCategory;
	
	
	public String getMonth() {
		Month month = this.createAt.getMonth();
		return month.toString();
	}
	
	
}
