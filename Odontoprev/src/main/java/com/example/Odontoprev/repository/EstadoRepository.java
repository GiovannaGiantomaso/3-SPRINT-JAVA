package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Estado;
import com.example.Odontoprev.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNomeAndPais(String nome, Pais pais);
}