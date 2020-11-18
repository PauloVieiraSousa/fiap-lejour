package com.fiap.lejour.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fiap.lejour.integration.InvoiceRestClient;
import com.fiap.lejour.model.Invoice;
import com.fiap.lejour.model.SaleForCategory;
import com.fiap.lejour.model.SalePerMonth;


public class InvoiceService {

	private List<Invoice> invoices; 
			
	public InvoiceService(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	public double totalSaleAccept(){
		return invoices.stream()
			.filter(inv -> inv.isAccepted())
			.mapToDouble(inv -> inv.getAmount())
			.sum();
	}
	
	public double totalSaleNoAccept(){
		return invoices.stream()
			.filter(inv -> !inv.isAccepted())
			.mapToDouble(inv -> inv.getAmount())
			.sum();	
	}
	
	public  List<SaleForCategory> saleForCategory(){
		List<SaleForCategory> result = new ArrayList();
		
		Map<String, Double> grouped = invoices.stream()
									.filter(inv -> inv.isAccepted())
									.collect(Collectors.groupingBy(Invoice::getVendorCategory, Collectors.summingDouble(Invoice::getAmount)));
									
									for (Map.Entry<String, Double> entry : grouped.entrySet()) {
										SaleForCategory saleForCategory = new SaleForCategory(entry.getKey(), entry.getValue());
										result.add(saleForCategory);
									}
		
		return result;
	}

	
	public List<SalePerMonth> saleForMonth(){
		List<SalePerMonth> result = new ArrayList();	
		Map<String, Double> grouped = invoices.stream()
									.filter(inv -> inv.isAccepted())
									.collect(Collectors.groupingBy(Invoice::getMonth, Collectors.summingDouble(Invoice::getAmount)));
		
									for (Map.Entry<String, Double> entry : grouped.entrySet()) {
										SalePerMonth salesPerMonth = new SalePerMonth(entry.getKey(), entry.getValue());
										result.add(salesPerMonth);
									}		
		return result;
	}

}
