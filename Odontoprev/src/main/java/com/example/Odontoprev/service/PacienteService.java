package com.example.Odontoprev.service;

import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void salvar(Paciente paciente) {
        if (paciente.getEndereco() == null || paciente.getEndereco().getId() == null) {
            throw new RuntimeException("❌ Erro: O paciente precisa ter um endereço válido.");
        }

        System.out.println("📌 Verificando e-mail...");
        String resultadoValidacaoEmail = pacienteRepository.validarEmail(paciente.getEmail());
        if (!"OK".equals(resultadoValidacaoEmail)) {
            throw new RuntimeException("❌ E-mail inválido: " + paciente.getEmail());
        }

        System.out.println("📌 Inserindo paciente via procedure...");
        try {
            pacienteRepository.inserirPaciente(
                    paciente.getNome(),
                    new java.sql.Date(paciente.getDataNascimento().getTime()),
                    paciente.getIdGenero(),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getEndereco().getId()
            );
            System.out.println("✅ Paciente inserido com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao chamar a procedure: " + e.getMessage());
            throw new RuntimeException("Erro ao inserir paciente!", e);
        }
    }


    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}
