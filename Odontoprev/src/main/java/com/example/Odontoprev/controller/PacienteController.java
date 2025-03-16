package com.example.Odontoprev.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.example.Odontoprev.model.Paciente;
import com.example.Odontoprev.model.Endereco;
import com.example.Odontoprev.service.PacienteService;
import com.example.Odontoprev.service.EnderecoService;
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

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public String gerenciarPacientes() {
        return "pacientes/gerenciar_pacientes";
    }


    @GetMapping("/cadastrar")
    public String cadastrarPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/cadastrar_paciente";
    }

    @PostMapping("/salvar")
    public String salvarPaciente(@ModelAttribute Paciente paciente,
                                 @RequestParam("dataNascimento") String dataNascimentoStr,
                                 @RequestParam("cep") String cep,
                                 @RequestParam("numero") String numero,
                                 @RequestParam("bairro") String bairro,
                                 @RequestParam("cidade") String cidade,
                                 @RequestParam("estado") String estado,
                                 @RequestParam("pais") String pais) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            paciente.setDataNascimento(sdf.parse(dataNascimentoStr));

            Endereco endereco = enderecoService.buscarOuCriarEndereco(cep, numero, bairro, cidade, estado, pais);
            paciente.setEndereco(endereco);

            System.out.println("📌 Salvando paciente via Procedure...");
            pacienteService.salvar(paciente);
            System.out.println("✅ Paciente salvo com sucesso!");

            return "redirect:/pacientes/listar";
        } catch (ParseException e) {
            System.err.println("❌ Erro ao converter a data de nascimento: " + e.getMessage());
            throw new RuntimeException("Erro ao converter a data de nascimento!", e);
        } catch (Exception e) {
            System.err.println("❌ Erro ao salvar paciente: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar paciente!", e);
        }
    }

    @GetMapping("/teste-inserir")
    @ResponseBody
    public String testeInserirPaciente() {
        try {
            Paciente paciente = new Paciente();
            paciente.setNome("Paciente API");
            paciente.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse("1995-06-15"));
            paciente.setIdGenero(7);
            paciente.setTelefone("11999999999");
            paciente.setEmail("paciente.api@email.com");

            Endereco endereco = new Endereco();
            endereco.setId(1L);
            paciente.setEndereco(endereco);

            pacienteService.salvar(paciente);

            return "✅ Paciente inserido com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Erro ao inserir paciente: " + e.getMessage();
        }
    }
    @GetMapping("/listar")
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        return "pacientes/listar_pacientes";
    }
}
