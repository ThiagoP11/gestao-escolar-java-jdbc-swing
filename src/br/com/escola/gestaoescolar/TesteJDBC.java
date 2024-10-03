package br.com.escola.gestaoescolar;

import br.com.escola.gestaoescolar.dao.CursoDao;
import br.com.escola.gestaoescolar.dominio.Curso;
import br.com.escola.gestaoescolar.dominio.Nivel;

import java.sql.Connection;
import java.sql.DriverManager;

public class TesteJDBC {

    public static void main(String[] args) throws Exception{

        CursoDao cursoDao = new CursoDao();
        Curso curso = new Curso("0","thiago",10, Nivel.AVANCADO);
        cursoDao.salvar(curso);
    }
}
