package br.com.escola.gestaoescolar.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Estudante {

    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Estudante(String nome, String telefone, String endereco, String cpf, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Estudante(Long id, String nome, String telefone, String endereco, String cpf, String email, LocalDate dataNascimento){
        this(nome, telefone, endereco, cpf, email, dataNascimento);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudante estudante = (Estudante) o;
        return Objects.equals(cpf, estudante.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

}
