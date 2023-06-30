package com.car_workshop.Car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_workshop.Car.model.ItemServico;
import com.car_workshop.Car.model.pk.ItemServicoPK;

public interface ItemServicoRepository extends JpaRepository<ItemServico, ItemServicoPK> {
}
