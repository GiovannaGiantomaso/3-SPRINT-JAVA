package com.example.Odontoprev.service;

import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.model.Endereco;
import com.example.Odontoprev.repository.PacienteRepository;
import com.example.Odontoprev.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public void salvar(Paciente paciente) {
        if (paciente.getEndereco() != null) {
            Endereco enderecoExistente = enderecoRepository.save(paciente.getEndereco());
            paciente.setEndereco(enderecoExistente);
        }
        pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}
