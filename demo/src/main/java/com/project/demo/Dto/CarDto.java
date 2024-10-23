package com.project.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	private Long id;
	private String carName;
	private String status;
	private int prix;
	private String imageUrl;
	

}
