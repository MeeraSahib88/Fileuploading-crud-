package com.example.demo.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDeatislDto {

	private String name;
	
	private BigDecimal phonenumber;
	
	private String email;
	
	private byte[] itemImage;
	

}
