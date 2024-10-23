package com.project.demo.Service;

import java.util.List;

import com.project.demo.Dto.CarDto;

public interface CarService {
	CarDto createCar(CarDto carDto);
	CarDto getCarId(Long carid);
	List<CarDto> getAllCars();
	CarDto updateCar(Long carid, CarDto updateCar);
	void deleteById(Long carid);

}
