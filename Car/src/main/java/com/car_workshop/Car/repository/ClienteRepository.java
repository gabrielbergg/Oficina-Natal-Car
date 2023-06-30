package com.car_workshop.Car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_workshop.Car.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
