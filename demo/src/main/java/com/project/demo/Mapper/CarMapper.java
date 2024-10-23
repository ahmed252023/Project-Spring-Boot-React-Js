package com.project.demo.Mapper;

import com.project.demo.Dto.CarDto;
import com.project.demo.entity.Car;

public class CarMapper {
	public static CarDto mapToCarDto(Car car) {
		return new CarDto(
				car.getId(),
				car.getCarName(),
				car.getStatus(),
				car.getPrix(),
				car.getImageUrl()
				);
		
	}
		public static Car mapToCar(CarDto carDto) {
			return new Car(
					
					carDto.getId(),
					carDto.getCarName(),
					carDto.getStatus(),
					carDto.getPrix(),
					carDto.getImageUrl()
					);
					
		
		
		
	}

}
