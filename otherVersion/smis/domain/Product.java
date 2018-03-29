package com.m520it.day2.smis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Long id;
	private String productName;
	private Double salePrice;
	private Double cutoff;
	private Double costPrice;
}
