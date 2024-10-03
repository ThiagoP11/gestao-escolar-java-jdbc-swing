package br.com.escola.gestaoescolar.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Turma {

    private Long id;
    private String codigo;
    private Curso curso;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Periodo periodo;

    public Turma(String codigo, Curso curso, LocalDate dataInicio, LocalDate dataFim, Periodo periodo) {
        this.codigo = codigo;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.periodo = periodo;
    }

    public Turma(Long id, String codigo, Curso curso, LocalDate dataInicio, LocalDate dataFim, Periodo periodo) {
        this(codigo, curso, dataInicio, dataFim, periodo);
        this.id = id;
    }

    public Long getCodigoCurso(){
        return this.curso.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(codigo, turma.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

}
