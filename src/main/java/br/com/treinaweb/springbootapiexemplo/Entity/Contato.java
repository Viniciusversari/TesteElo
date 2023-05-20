package br.com.treinaweb.springbootapiexemplo.Entity;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class Contato
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private Long telefone;
    @Email(message = "O e-mail fornecido é inválido")
    private String email;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public Long getTelefone() {
        return telefone;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = Long.valueOf(String.valueOf(Integer.parseInt(telefone)));
    }
}
