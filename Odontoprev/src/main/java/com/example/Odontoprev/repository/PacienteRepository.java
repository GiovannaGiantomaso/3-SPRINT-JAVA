package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Transactional
    @Procedure(procedureName = "RM553369.PACOTE_GESTAO_SAUDE.INSERIR_PACIENTE_PROC")
    void inserirPaciente(  // <-- ALTERADO DE "Integer" PARA "void"
                           @Param("p_nome") String p_nome,
                           @Param("p_data_nascimento") Date p_data_nascimento,
                           @Param("p_genero") Integer p_genero,
                           @Param("p_telefone") String p_telefone,
                           @Param("p_email") String p_email,
                           @Param("p_id_endereco") Long p_id_endereco
    );



    @Query(value = "SELECT VALIDAR_EMAIL(:email) FROM dual", nativeQuery = true)
    String validarEmail(@Param("email") String email);

    Paciente findByEmail(String email);

    List<Paciente> findAll();
}
