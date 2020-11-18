package com.fiap.lejour.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InvoiceResponse {

	@JsonProperty("ID")
	private long id;
	
	@JsonProperty("WEDDING_ID")
	private float weddingId;
	
	@JsonProperty("VENDOR_ID")
	private float vendorId;
	
	@JsonProperty("AMOUNT")
	private double amount;
	
	
	private boolean accepted;
	
	private LocalDate createAt;
	DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yy-MM-dd"); 
	
	
	@JsonProperty("VENDOR_CATEGORY")
	private String vendorCategory;

	
	public InvoiceResponse() {}

	@JsonProperty("ACCEPTED")
	public void setAccepted(String accepted) {
		this.accepted = (accepted.equals("TRUE"));
	}
	
	boolean getAccepted(){
		return this.accepted;
	}
	
	@JsonProperty("CREATED_AT")	
	public void setCreateAt(String createAt) {
		this.createAt = LocalDate.parse(createAt, formatDate);
	}
	
	public LocalDate getCreateAt() {
		return this.createAt;
	}
	
}
