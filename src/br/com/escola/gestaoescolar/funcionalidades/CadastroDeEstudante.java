package br.com.escola.gestaoescolar.funcionalidades;

import br.com.escola.gestaoescolar.dao.EstudanteDao;
import br.com.escola.gestaoescolar.dominio.Estudante;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CadastroDeEstudante {

    private EstudanteDao estudanteDao = new EstudanteDao();


    public void cadastrar(Estudante estudante) {
        //validacoes
        if (estudante.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome é obrigatório!");
        }

        if (estudante.getCpf().isBlank()) {
            throw new IllegalArgumentException("Campo cpf é obrigatório!");
        }

        if (estudante.getEmail().isBlank()) {
            throw new IllegalArgumentException("Campo email é obrigatório!");
        }

        if (estudante.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Campo telefone é obrigatório!");
        }

        if (estudante.getEndereco().isBlank()) {
            throw new IllegalArgumentException("Campo endereço é obrigatório!");
        }

        if(estudante.getDataNascimento() == null) {
            throw new IllegalArgumentException("Campo Data de Nascimento é Obrigatório");
        }


        estudanteDao.salvar(estudante);


    }

    public List<Estudante> listar() {
        return estudanteDao.listar();
    }

    public void excluir(String cpf) {
        estudanteDao.excluir(cpf);
    }

    public void atualizar(String cpf, Estudante estudante) {
        estudanteDao.atualizar(cpf, estudante);
    }

}
