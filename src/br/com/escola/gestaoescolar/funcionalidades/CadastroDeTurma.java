package br.com.escola.gestaoescolar.funcionalidades;

import br.com.escola.gestaoescolar.dao.TurmaDao;
import br.com.escola.gestaoescolar.dominio.Curso;
import br.com.escola.gestaoescolar.dominio.Periodo;
import br.com.escola.gestaoescolar.dominio.Turma;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CadastroDeTurma {

    private TurmaDao turmaDao = new TurmaDao();

    public void cadastrar(String codigo, LocalDate dataInicio, LocalDate dataFim, Periodo periodo, String codigoCurso) {
        //validacoes
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("Campo código é obrigatório!");
        }

        if (dataInicio == null) {
            throw new IllegalArgumentException("Campo data início é obrigatório!");
        }

        if (dataFim == null) {
            throw new IllegalArgumentException("Campo data fim é obrigatório!");
        }

        if (periodo == null) {
            throw new IllegalArgumentException("Campo período é obrigatório!");
        }

        if (codigoCurso == null) {
            throw new IllegalArgumentException("Campo código do curso é obrigatório!");
        }

        var turmasCadastradas = listar();
        for (var t : turmasCadastradas) {
            if (t.getCodigo().equals(codigo)) {
                throw new IllegalArgumentException("Código já cadastrado!");
            }
        }

        var cadastroDeCursos = new CadastroDeCurso();
        var curso = cadastroDeCursos.carregarCursoPeloCodigo(codigoCurso);
        if (curso == null) {
            throw new IllegalArgumentException("Código do curso inexistente!");
        }

        var turma = new Turma(codigo, curso, dataInicio, dataFim, periodo);
        this.turmaDao.salvar(turma);
    }

    public List<Turma> listar() {
        return this.turmaDao.listar();
    }

    public void excluir(String codigo) {
        this.turmaDao.excluir(codigo);
    }

    public void atualizar(String codigoTurma, Turma turma) {
        this.turmaDao.atualizar(codigoTurma, turma);
    }

}
