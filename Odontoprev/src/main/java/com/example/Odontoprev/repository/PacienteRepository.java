package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(value = "CALL PACOTE_GESTAO_SAUDE.INSERIR_PACIENTE_PROC(:p_nome, :p_data_nascimento, :p_genero, :p_telefone, :p_email, :p_id_endereco)", nativeQuery = true)
    void inserirPaciente(
            @Param("p_nome") String p_nome,
            @Param("p_data_nascimento") Date p_data_nascimento,
            @Param("p_genero") Integer p_genero,
            @Param("p_telefone") String p_telefone,
            @Param("p_email") String p_email,
            @Param("p_id_endereco") Long p_id_endereco
    );

    Paciente findByEmail(String email);

    List<Paciente> findAll();
}


