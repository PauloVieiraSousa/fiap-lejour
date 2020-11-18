package com.fiap.lejour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fiap.lejour.model.Costumer;
import com.fiap.lejour.model.CostumerPerMonth;
import com.fiap.lejour.model.Invoice;
import com.fiap.lejour.model.SalePerMonth;


public class CostumerService {

	private List<Costumer> costumer; 

	public CostumerService(List<Costumer> costumer){
		this.costumer = costumer;
	}
	
	public double totalCostumer(){
		return costumer.size();
	}
	
	
	public List<CostumerPerMonth> totalCostumerPerMonth(){
		List<CostumerPerMonth> result = new ArrayList();
		Map<String, Long> grouped = costumer.stream()
				.collect(Collectors.groupingBy(Costumer::getMonth, Collectors.counting()));

				for (Map.Entry<String, Long> entry : grouped.entrySet()) {
					CostumerPerMonth costumerPerMonth = new CostumerPerMonth(entry.getKey(), entry.getValue());
					result.add(costumerPerMonth);
				}
				return result;
	}
	
}
