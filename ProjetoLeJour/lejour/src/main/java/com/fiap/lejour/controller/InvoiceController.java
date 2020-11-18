package com.fiap.lejour.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lejour.controller.output.dto.InvoiceOutputDto;
import com.fiap.lejour.controller.output.dto.TotalsOutputDto;
import com.fiap.lejour.controller.output.dto.UserOutputDto;
import com.fiap.lejour.integration.InvoiceResponse;
import com.fiap.lejour.integration.InvoiceRestClient;
import com.fiap.lejour.model.Invoice;
import com.fiap.lejour.model.SaleForCategory;
import com.fiap.lejour.model.SalePerMonth;
import com.fiap.lejour.service.InvoiceService;

@RequestMapping("api/invoice")
@RestController()
public class InvoiceController {

	@Autowired
	InvoiceRestClient invoiceRestClient;
	

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceOutputDto>> getInvoice(){
		List<Invoice> invoices = invoiceRestClient.getInvoice();
		return ResponseEntity.ok(InvoiceOutputDto.listFromInvoice(invoices));
	}
	
	@GetMapping(path="/total", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TotalsOutputDto> totals(){
		InvoiceService invoiceService = new InvoiceService(invoiceRestClient.getInvoice());		
		double totalSalesAccept = invoiceService.totalSaleAccept();
		double totalSalesNoAccept = invoiceService.totalSaleNoAccept();
		List<SaleForCategory> saleForCategory = invoiceService.saleForCategory();
		List<SalePerMonth> saleForMonth = invoiceService.saleForMonth();
		
		return ResponseEntity.ok(TotalsOutputDto.build(totalSalesAccept, totalSalesNoAccept, saleForCategory, saleForMonth));
	}
	
	
}
