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

        final Pais pais = paisRepository.findByNome(paisNome).orElseGet(() -> {
            Pais novoPais = new Pais();
            novoPais.setNome(paisNome);
            return paisRepository.save(novoPais);
        });

        final Estado estado = estadoRepository.findByNomeAndPais(estadoNome, pais).orElseGet(() -> {
            Estado novoEstado = new Estado();
            novoEstado.setNome(estadoNome);
            novoEstado.setPais(pais);
            return estadoRepository.save(novoEstado);
        });

        final Cidade cidade = cidadeRepository.findByNomeAndEstado(cidadeNome, estado).orElseGet(() -> {
            Cidade novaCidade = new Cidade();
            novaCidade.setNome(cidadeNome);
            novaCidade.setEstado(estado);
            return cidadeRepository.save(novaCidade);
        });

        final Bairro bairro = bairroRepository.findByNomeAndCidade(bairroNome, cidade).orElseGet(() -> {
            Bairro novoBairro = new Bairro();
            novoBairro.setNome(bairroNome);
            novoBairro.setCidade(cidade);
            return bairroRepository.save(novoBairro);  // ✅ Retorna o bairro salvo
        });

        return enderecoRepository.findByCepAndNumeroAndBairro(cep, numero, bairro.getId()).orElseGet(() -> {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCep(cep);
            novoEndereco.setNumero(numero);
            novoEndereco.setBairro(bairro);
            return enderecoRepository.save(novoEndereco);
        });
    }
}
