package com.project.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Long id;
	
	@Column(name = "car_name")
	private String carName ;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "prix")
	private int prix;

	@Column(name="image_url")
	private String imageUrl;

}
