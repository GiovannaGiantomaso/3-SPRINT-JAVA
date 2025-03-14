package com.example.Odontoprev.repository;

import com.example.Odontoprev.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // ✅ Chama a Procedure correta do pacote no banco Oracle
    @Procedure(procedureName = "PACOTE_GESTAO_SAUDE.INSERIR_PACIENTE_PROC")
    void inserirPaciente(
            String p_nome,
            Date p_data_nascimento,
            Long p_genero,
            String p_telefone,
            String p_email,
            Long p_id_endereco
    );

    // ✅ Busca paciente pelo e-mail para evitar duplicidade
    Paciente findByEmail(String email);

    // ✅ Verificando se a listagem funciona corretamente
    List<Paciente> findAll();
}
