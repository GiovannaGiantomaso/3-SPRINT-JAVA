package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.cep = :cep AND e.numero = :numero AND e.bairro.id = :idBairro")
    Optional<Endereco> findByCepAndNumeroAndBairro(
            @Param("cep") String cep,
            @Param("numero") String numero,
            @Param("idBairro") Long idBairro
    );
}
