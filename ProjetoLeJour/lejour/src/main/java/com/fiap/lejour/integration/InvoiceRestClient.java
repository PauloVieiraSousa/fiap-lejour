package com.fiap.lejour.integration;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fiap.lejour.model.Costumer;
import com.fiap.lejour.model.Invoice;

@Service
public class InvoiceRestClient {

	private String invoiceServiceUrl;
	private RestTemplate restTemplate;
	
	public InvoiceRestClient(RestTemplate restTemplate,
			@Value("${fiap.lejour.integration.invoice.url}") String invoiceServiceUrl ) {
		this.invoiceServiceUrl = invoiceServiceUrl;
		this.restTemplate = restTemplate;
	}
	
	public List<Invoice> getInvoice() {
		ResponseEntity<InvoiceResponse[]> responseEntity = restTemplate.getForEntity(invoiceServiceUrl+"/a91a5071-ffe2-4b1e-bb0e-76650f4eb679", InvoiceResponse[].class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!HttpStatus.OK.equals(statusCode)) {
		      throw new RuntimeException("Status diferente do esperado: " + statusCode);
	    }
		List<Invoice> invoices = this.parseInvoice(responseEntity.getBody());
		return invoices;
	}
	

	public List<Costumer> getCostumer() {
		ResponseEntity<CostumersResponse[]> responseEntity = restTemplate.getForEntity(invoiceServiceUrl+"/a1b37891-b6b8-4423-b542-5270da25590f", CostumersResponse[].class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (!HttpStatus.OK.equals(statusCode)) {
		      throw new RuntimeException("Status diferente do esperado: " + statusCode);
	    }
		List<Costumer> costumer = this.parseCostumer(responseEntity.getBody());
		return costumer;
	}
	
	private List<Costumer> parseCostumer (CostumersResponse[] response){
		List<Costumer> costumers = new ArrayList<Costumer>();
		for(CostumersResponse item : response) {
			Costumer costumer = new Costumer();
			costumer.setId(item.getId());
			costumer.setCreateAt(item.getCreateAt());
			costumers.add(costumer);
		}
		return costumers;
	}

	
	
	private List<Invoice> parseInvoice(InvoiceResponse[] response){
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		for(InvoiceResponse item: response) {
			Invoice invoice = new Invoice();
			invoice.setAccepted(item.getAccepted());
			invoice.setAmount(item.getAmount());
			invoice.setCreateAt(item.getCreateAt());
			invoice.setId(item.getId());
			invoice.setVendorCategory(item.getVendorCategory());
			invoice.setVendorId(item.getVendorId());
			invoice.setWeddingId(item.getWeddingId());
			
			invoices.add(invoice);
		}
		return invoices;
	}
	
}
