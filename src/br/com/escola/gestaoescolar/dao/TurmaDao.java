package br.com.escola.gestaoescolar.dao;


import br.com.escola.gestaoescolar.dominio.Curso;
import br.com.escola.gestaoescolar.dominio.Nivel;
import br.com.escola.gestaoescolar.dominio.Periodo;
import br.com.escola.gestaoescolar.dominio.Turma;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TurmaDao {

    private CursoDao cursoDao = new CursoDao();

    public void salvar(Turma turma) {
        var sql = "INSERT INTO turmas(codigo, data_inicio, data_fim, periodo, curso_id)values(?,?,?,?,?)";
        //DEFINE VARIAVEIS QUE SÃO FECHAVEIS (CLOSE)
        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, turma.getCodigo());
            ps.setDate(2, Date.valueOf(turma.getDataInicio()));
            ps.setDate(3, Date.valueOf(turma.getDataFim()));
            ps.setString(4, turma.getPeriodo().name());
            ps.setLong(5, turma.getCurso().getId());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Turma> listar() {
        var turmas =  new ArrayList<Turma>();
        var sql = "select * from turmas";
        try (var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                var id = resultSet.getLong("id");
                var codigo = resultSet.getString("codigo");
                var dataInicio = Instant.ofEpochMilli(resultSet.getDate("data_inicio").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                var dataFim = Instant.ofEpochMilli(resultSet.getDate("data_fim").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                var periodo = Periodo.valueOf(resultSet.getString("periodo"));
                var curso = cursoDao.buscarPorId(resultSet.getLong("curso_id"));

                var turma = new Turma(id, codigo, curso, dataInicio, dataFim, periodo);
                turmas.add(turma);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return turmas;
    }

    public void excluir(String codigo){
        var sql = "DELETE FROM turmas WHERE codigo = ?";

        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, codigo);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar (String codigoTurma, Turma turma) {
        var sql = "UPDATE turmas SET codigo = ?, data_inicio = ?, data_fim = ?, periodo = ?, curso_id = ? WHERE codigo = ?";

        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, turma.getCodigo());
            ps.setDate(2, Date.valueOf(turma.getDataInicio()));
            ps.setDate(3, Date.valueOf(turma.getDataFim()));
            ps.setString(4, turma.getPeriodo().name());
            ps.setLong(5, turma.getCurso().getId());
            ps.setString(6, codigoTurma);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
