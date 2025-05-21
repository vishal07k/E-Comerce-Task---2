package com.ecomerce.ecomerce.dtos;


import java.util.Date;
import java.util.List;

import com.ecomerce.ecomerce.model.Product;
import com.ecomerce.ecomerce.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Long id;
    private Date dateOfOrder;
    private int qty;
    private List<Long> productId;
    private Long userId;    
    
}	
