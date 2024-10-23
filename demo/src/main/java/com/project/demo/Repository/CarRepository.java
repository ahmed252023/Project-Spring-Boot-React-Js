package com.project.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.demo.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
