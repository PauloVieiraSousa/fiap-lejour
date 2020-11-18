package com.fiap.lejour.controller.output.dto;

import java.util.List;

import com.fiap.lejour.model.Costumer;
import com.fiap.lejour.model.CostumerPerMonth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CostumerOutputDto {
	
	private Integer totalCostumer;
	private List<CostumerPerMonth> costumerPerMonth;
	
	CostumerOutputDto(Integer totalCostumer,  List<CostumerPerMonth> costumerPerMonth){
		this.totalCostumer = totalCostumer;
		this.costumerPerMonth = costumerPerMonth;
	}
	
	public static CostumerOutputDto buildCostumer(Integer totalCostumer, List<CostumerPerMonth> costumerPerMonth ) {
		return new CostumerOutputDto(totalCostumer, costumerPerMonth);
	}
	
	public static List<Costumer> costumers(List<Costumer> costumers) {
		return costumers;
	}
	
	
}
