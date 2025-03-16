package com.example.Odontoprev.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PacienteProcedureRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void inserirPaciente(String nome, java.sql.Date dataNascimento, int idGenero, String telefone, String email, Long idEndereco) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("inserir_paciente_proc")
                .registerStoredProcedureParameter("p_nome", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_data_nascimento", java.sql.Date.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_genero", Integer.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefone", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_email", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_endereco", Long.class, jakarta.persistence.ParameterMode.IN)
                .setParameter("p_nome", nome)
                .setParameter("p_data_nascimento", dataNascimento)
                .setParameter("p_genero", idGenero)
                .setParameter("p_telefone", telefone)
                .setParameter("p_email", email)
                .setParameter("p_id_endereco", idEndereco);

        query.execute();
    }
}
