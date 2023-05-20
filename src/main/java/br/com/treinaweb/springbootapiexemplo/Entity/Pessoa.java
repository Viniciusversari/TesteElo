package br.com.treinaweb.springbootapiexemplo.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;

    private String cpf;

    private LocalDate datanascimento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    @Fetch(FetchMode.JOIN)
    private List<Contato> contatos;

    public Pessoa() {
        contatos = new ArrayList<>();
    }


    public LocalDate getDatanascimento() {
        return datanascimento;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void novoCpf(String cpf) {
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public void setDatanascimento(LocalDate datanascimento) {
        if (datanascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser uma data futura");
        }
        this.datanascimento = datanascimento;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        if (contatos.isEmpty()) {
            throw new IllegalArgumentException("A pessoa deve ter pelo menos um contato");
        }
        this.contatos = contatos;
    }
    public boolean validarCPF(String cpf) {


        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int digito1 = calcularDigitoVerificador(cpf.substring(0, 9), 10);
        int digito2 = calcularDigitoVerificador(cpf.substring(0, 9) + digito1, 11);

        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    private int calcularDigitoVerificador(String cpfParcial, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < cpfParcial.length(); i++) {
            soma += Integer.parseInt(cpfParcial.substring(i, i + 1)) * pesoInicial--;
        }
        int digito = 11 - (soma % 11);
        return digito > 9 ? 0 : digito;
    }


}
