package com.example.Odontoprev.service;

import com.example.Odontoprev.model.*;
import com.example.Odontoprev.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Transactional
    public Endereco buscarOuCriarEndereco(String cep, String numero, String bairroNome, String cidadeNome, String estadoNome, String paisNome) {

        // 1. Verifica se o país já existe, senão cria um novo
        Optional<Pais> paisOpt = paisRepository.findByNome(paisNome);
        Pais pais = paisOpt.orElseGet(() -> {
            Pais novoPais = new Pais();
            novoPais.setNome(paisNome);
            return paisRepository.save(novoPais);
        });

        // 2. Verifica se o estado já existe, senão cria um novo
        Optional<Estado> estadoOpt = estadoRepository.findByNomeAndPais(estadoNome, pais);
        Estado estado = estadoOpt.orElseGet(() -> {
            Estado novoEstado = new Estado();
            novoEstado.setNome(estadoNome);
            novoEstado.setPais(pais);
            return estadoRepository.save(novoEstado);
        });

        // 3. Verifica se a cidade já existe, senão cria uma nova
        Optional<Cidade> cidadeOpt = cidadeRepository.findByNomeAndEstado(cidadeNome, estado);
        Cidade cidade = cidadeOpt.orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome(cidadeNome);
            novaCidade.setEstado(estado);
            return cidadeRepository.save(novaCidade);
        });

        // 4. Verifica se o bairro já existe, senão cria um novo
        Optional<Bairro> bairroOpt = bairroRepository.findByNomeAndCidade(bairroNome, cidade);
        Bairro bairro = bairroOpt.orElseGet(() -> {
            Bairro novoBairro = new Bairro();
            novoBairro.setNome(bairroNome);
            novoBairro.setCidade(cidade);
            return bairroRepository.save(novoBairro);
        });

        // 5. Verifica se o endereço já existe, senão cria um novo
        Optional<Endereco> enderecoOpt = enderecoRepository.findByCepAndNumeroAndBairro(cep, numero, bairro.getId());

        return enderecoOpt.orElseGet(() -> {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCep(cep);
            novoEndereco.setNumero(numero);
            novoEndereco.setBairro(bairro);
            return enderecoRepository.save(novoEndereco);
        });
    }
}
