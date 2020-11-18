package com.fiap.lejour.model;

import java.time.LocalDate;
import java.time.Month;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class Costumer {

	private Integer id;
	private LocalDate createAt;
	
	
	public String getMonth() {
		Month month = this.createAt.getMonth();
		return month.toString();
	}
}
