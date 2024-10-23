package com.project.demo.Service.imple;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.annotations.Collate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.project.demo.Dto.CarDto;
import com.project.demo.Mapper.CarMapper;
import com.project.demo.Repository.CarRepository;
import com.project.demo.Service.CarService;
import com.project.demo.entity.Car;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
	
	private CarRepository carRepository;

	@Override
	public CarDto createCar(CarDto carDto) {
		Car car = CarMapper.mapToCar(carDto);
		Car savedCar = carRepository.save(car);
		return CarMapper.mapToCarDto(savedCar);
		
	}

	@Override
	public CarDto getCarId(Long carid) {
		Car user = carRepository.findById(carid)
		.orElseThrow(() ->
		new ResourceAccessException("User is not exist with given id : " + carid));
		return CarMapper.mapToCarDto(user);
				
	}

	@Override
	public List<CarDto> getAllCars() {
		List<Car> cars = carRepository.findAll();
		return cars.stream().map((car) -> CarMapper.mapToCarDto(car))
           .collect(Collectors.toList());
		// TODO Auto-generated method stub
	}

	@Override
	public CarDto updateCar(Long carid, CarDto updateCar) {
		Car car = carRepository.findById(carid).orElseThrow(() ->
				  new ResourceAccessException("User is not exist with given id : " + carid));
				
				
		car.setCarName(updateCar.getCarName());
		car.setStatus(updateCar.getStatus());
		car.setPrix(updateCar.getPrix());
		
		Car updateCarObj = carRepository.save(car);
		return CarMapper.mapToCarDto(updateCarObj);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long carid) {
		
		Car car = carRepository.findById(carid).orElseThrow(() ->
		  new ResourceAccessException("User is not exist with given id : " + carid));
		
		carRepository.deleteById(carid);
		
	}
	
	
	
	
	

	
	
	
	
	
	
	
	

}
