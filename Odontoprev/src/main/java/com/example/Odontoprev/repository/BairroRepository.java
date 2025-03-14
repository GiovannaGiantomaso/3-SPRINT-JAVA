package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Bairro;
import com.example.Odontoprev.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findByNomeAndCidade(String nome, Cidade cidade);
}