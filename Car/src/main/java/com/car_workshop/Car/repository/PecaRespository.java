package com.car_workshop.Car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_workshop.Car.model.Peca_Carro;

public interface PecaRespository extends JpaRepository<Peca_Carro, Long> {
}
