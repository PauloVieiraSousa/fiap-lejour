package com.fiap.lejour.controller.output.dto;

import java.util.List;
import java.util.Map;

import com.fiap.lejour.model.SaleForCategory;
import com.fiap.lejour.model.SalePerMonth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalsOutputDto {
	
	private double totalSalesAccept;
	private double totalSalesNoAccept;
	private List<SaleForCategory> saleForCategory;
	private List<SalePerMonth> saleForMonth;
	
	TotalsOutputDto(double totalSalesAccept, double totalSalesNoAccept, List<SaleForCategory> saleForCategory, List<SalePerMonth> saleForMonth ){
		this.totalSalesAccept = totalSalesAccept;
		this.totalSalesNoAccept = totalSalesNoAccept;
		this.saleForCategory = saleForCategory;
		this.saleForMonth = saleForMonth;
	}
	
	public static TotalsOutputDto build(double total, double totalNoAccept, List<SaleForCategory> saleForCategory, List<SalePerMonth> saleForMonth ) {
		return new TotalsOutputDto(total, totalNoAccept, saleForCategory, saleForMonth);
	}
	

}
