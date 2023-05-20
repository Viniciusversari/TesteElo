package com.example.demo;

import br.com.treinaweb.springbootapiexemplo.Entity.Contato;
import br.com.treinaweb.springbootapiexemplo.Entity.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaTest {

    private Pessoa pessoa;

    @BeforeEach
    public void setUp() {
        pessoa = new Pessoa();
    }

    @Test
    public void testSetNome() {
        pessoa.setNome("João");
        Assertions.assertEquals("João", pessoa.getNome());
    }

    @Test
    public void testSetDatanascimento() {
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);
        pessoa.setDatanascimento(dataNascimento);
        Assertions.assertEquals(dataNascimento, pessoa.getDatanascimento());
    }

    @Test
    public void testSetDatanascimentoWithFutureDate() {
        LocalDate dataNascimento = LocalDate.now().plusDays(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pessoa.setDatanascimento(dataNascimento);
        });
    }

    @Test
    public void testSetContatos() {
        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato();
        contato.setNome("Contato 1");
        contatos.add(contato);

        pessoa.setContatos(contatos);

        Assertions.assertEquals(contatos, pessoa.getContatos());
    }

    @Test
    public void testSetContatosWithEmptyList() {
        List<Contato> contatos = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pessoa.setContatos(contatos);
        });
    }

    @Test
    public void testValidarCPF() {
        Assertions.assertTrue(pessoa.validarCPF("12345678909"));
    }

    @Test
    public void testValidarCPFWithInvalidCPF() {
        Assertions.assertFalse(pessoa.validarCPF("12345678910"));
    }

}


