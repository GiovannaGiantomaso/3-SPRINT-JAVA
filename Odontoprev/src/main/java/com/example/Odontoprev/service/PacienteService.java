package com.example.Odontoprev.service;

import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void salvar(Paciente paciente) {
        if (paciente.getIdGenero() == null ||
                (paciente.getIdGenero() != 7 && paciente.getIdGenero() != 8 && paciente.getIdGenero() != 11)) {
            throw new RuntimeException("Gênero inválido. Escolha entre: 7 (Feminino), 8 (Masculino) ou 11 (Outro).");
        }

        if (pacienteRepository.findByEmail(paciente.getEmail()) != null) {
            throw new RuntimeException("Já existe um paciente cadastrado com este e-mail.");
        }

        // ✅ Converte java.util.Date para LocalDate
        LocalDate dataNascimentoLocalDate = paciente.getDataNascimento().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // ✅ Converte LocalDate para java.sql.Date (compatível com Oracle)
        Date dataNascimentoFormatada = Date.valueOf(dataNascimentoLocalDate);

        // ✅ Converte o ID do gênero para Long
        Long idGeneroLong = paciente.getIdGenero().longValue();

        if (paciente.getEndereco() == null || paciente.getEndereco().getId() == null) {
            throw new RuntimeException("Endereço inválido. O paciente deve estar associado a um endereço válido.");
        }

        pacienteRepository.inserirPaciente(
                paciente.getNome(),
                dataNascimentoFormatada,
                idGeneroLong,
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getEndereco().getId()
        );
    }

    public List<Paciente> listarTodos() {
        try {
            return pacienteRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar pacientes: " + e.getMessage());
        }
    }
}
