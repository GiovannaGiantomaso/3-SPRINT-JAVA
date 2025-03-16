package com.example.Odontoprev.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EnderecoProcedureRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long inserirEndereco(String cep, String numero, String bairroNome, String cidadeNome, String estadoNome, String paisNome) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("inserir_endereco_proc")
                .registerStoredProcedureParameter("p_cep", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_numero", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_bairro", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_cidade", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_estado", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_pais", String.class, jakarta.persistence.ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_endereco", Long.class, jakarta.persistence.ParameterMode.OUT)
                .setParameter("p_cep", cep)
                .setParameter("p_numero", numero)
                .setParameter("p_bairro", bairroNome)
                .setParameter("p_cidade", cidadeNome)
                .setParameter("p_estado", estadoNome)
                .setParameter("p_pais", paisNome);

        query.execute();
        return (Long) query.getOutputParameterValue("p_id_endereco");
    }
}
