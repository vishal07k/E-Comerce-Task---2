package com.ecomerce.ecomerce.dtos;

import java.util.List;

import com.ecomerce.ecomerce.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
	 private Long id;
	    private String name;
	    private Double price;
	    private int qty;
	    private String description;
	    private List<Long> categoryId;
 }

