package com.project.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.Dto.CarDto;
import com.project.demo.Service.CarService;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {
	
	private CarService carService;
	@PostMapping
	
	public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
		CarDto savedcar = carService.createCar(carDto);
		return new ResponseEntity<>(savedcar, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CarDto> getCarId(@PathVariable("id") Long carId){
		CarDto cardto = carService.getCarId(carId);
		return ResponseEntity.ok(cardto);
		
	}
	@GetMapping
	public ResponseEntity<List<CarDto>> getAllCars(){
		List<CarDto> cars =carService.getAllCars();
		return ResponseEntity.ok(cars);
	}
	@PutMapping("{id}")
    public ResponseEntity<CarDto>  updatecar(@PathVariable("id") Long carid,@RequestBody CarDto updatecar){
		CarDto carDto = carService.updateCar(carid, updatecar);
    	return ResponseEntity.ok(carDto);
    }
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long carid){
		carService.deleteById(carid);
		return ResponseEntity.ok(" car Deleted Successfully");
	}
	
    	
		


}
