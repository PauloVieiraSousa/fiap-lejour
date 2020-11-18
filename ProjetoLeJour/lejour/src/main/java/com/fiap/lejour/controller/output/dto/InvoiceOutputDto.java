package com.fiap.lejour.controller.output.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.lejour.model.Invoice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceOutputDto {
	
	private long id;
	private float weddingId;
	private float vendorId;
	private double amount;
	private String createAt;
	private boolean accepted;
	private String vendorCategory;

	InvoiceOutputDto(Invoice invoice){
		this.id = invoice.getId();
		this.weddingId = invoice.getWeddingId();
		this.vendorId = invoice.getVendorId();
		this.amount = invoice.getAmount();
		this.createAt = invoice.getCreateAt().toString();
		this.accepted = invoice.isAccepted();
		this.vendorCategory = invoice.getVendorCategory();
	}
	
	public static List<InvoiceOutputDto> listFromInvoice(List<Invoice> invoices){
		return invoices.stream().map(InvoiceOutputDto::new)
				.collect(Collectors.toList());
	}

	
}
