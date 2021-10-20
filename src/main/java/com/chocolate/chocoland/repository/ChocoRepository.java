package com.chocolate.chocoland.repository;

import com.chocolate.chocoland.entity.Choco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChocoRepository extends JpaRepository<Choco, Long> {

    Optional<Choco> findByName(String name);
}

///responsável por converrsar com nosso banco de dados
