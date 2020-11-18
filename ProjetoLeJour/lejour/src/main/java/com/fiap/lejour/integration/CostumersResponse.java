package com.fiap.lejour.integration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CostumersResponse {
	
	DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yy-MM-dd"); 
	
	@JsonProperty("ID")
	private Integer id;
	
	private LocalDate createAt;
	
	@JsonProperty("CREATED_AT")	
	public void setCreateAt(String createAt) {
		this.createAt = LocalDate.parse(createAt, formatDate);
	}
	
	public LocalDate getCreateAt() {
		return this.createAt;
	}
	
	
	
	
}
