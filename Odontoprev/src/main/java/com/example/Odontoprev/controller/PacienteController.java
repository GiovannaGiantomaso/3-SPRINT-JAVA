package com.example.Odontoprev.controller;

import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String gerenciarPacientes() {
        return "pacientes/gerenciar_pacientes";
    }

    @GetMapping("/listar")
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        return "pacientes/listar_pacientes";
    }

    @GetMapping("/cadastrar")
    public String cadastrarPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/cadastrar_paciente";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.salvar(paciente);
        return "redirect:/pacientes/listar";
    }
}
