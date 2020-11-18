package com.fiap.lejour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lejour.controller.output.dto.CostumerOutputDto;
import com.fiap.lejour.controller.output.dto.InvoiceOutputDto;
import com.fiap.lejour.controller.output.dto.TotalsOutputDto;
import com.fiap.lejour.integration.InvoiceRestClient;
import com.fiap.lejour.model.Costumer;
import com.fiap.lejour.model.CostumerPerMonth;
import com.fiap.lejour.model.Invoice;
import com.fiap.lejour.service.CostumerService;

@RequestMapping("api/costumer")
@RestController()
public class CostumerController {

	@Autowired
	InvoiceRestClient invoiceRestClient;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Costumer>> getCostumers(){
		List<Costumer> costumer = invoiceRestClient.getCostumer();
		return ResponseEntity.ok(CostumerOutputDto.costumers(costumer));
	}
	
	@GetMapping(path="/totals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CostumerOutputDto> totals(){
		List<Costumer> costumer = invoiceRestClient.getCostumer();
		
		CostumerService costumerService = new CostumerService(costumer);
		List<CostumerPerMonth> costumerPerMonth = costumerService.totalCostumerPerMonth();
		return ResponseEntity.ok(CostumerOutputDto.buildCostumer(costumer.size(), costumerPerMonth));
	}
}
