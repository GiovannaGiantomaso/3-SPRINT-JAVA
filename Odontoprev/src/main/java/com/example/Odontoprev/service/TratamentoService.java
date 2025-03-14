package com.example.Odontoprev.service;

import com.example.Odontoprev.model.Tratamento;
import com.example.Odontoprev.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    private static final Logger logger = LoggerFactory.getLogger(TratamentoService.class);

    public List<Tratamento> listarTodos() {
        return tratamentoRepository.findAll();
    }

    public Optional<Tratamento> buscarPorId(Long id) {
        return tratamentoRepository.findById(id);
    }

    public Tratamento salvar(Tratamento tratamento) {
        logger.info("Salvando tratamento: Descrição={}, Tipo={}, Custo={}",
                tratamento.getDescricao(), tratamento.getTipo(), tratamento.getCusto());

        if (tratamento.getDescricao() == null || tratamento.getTipo() == null || tratamento.getCusto() == null) {
            logger.error("Erro: Campos obrigatórios não preenchidos!");
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }

        return tratamentoRepository.save(tratamento);
    }

    public void deletar(Long id) {
        tratamentoRepository.deleteById(id);
    }
}
