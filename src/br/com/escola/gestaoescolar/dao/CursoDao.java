package br.com.escola.gestaoescolar.dao;

import br.com.escola.gestaoescolar.dominio.Curso;
import br.com.escola.gestaoescolar.dominio.Nivel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {


    public void salvar(Curso curso) {
        var sql = "INSERT INTO cursos(nome, codigo, duracao, nivel)values(?,?,?,?)";
        //DEFINE VARIAVEIS QUE SÃO FECHAVEIS (CLOSE)
        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            //connection.setAutoCommit(false);

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getCodigo());
            ps.setInt(3, curso.getCargaHoraria());
            ps.setString(4, curso.getNivel().toString());

            ps.execute();
            //connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Curso> listar() {
        var cursos =  new ArrayList<Curso>();
        var sql = "SELECT * FROM cursos";
        try (var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                var id = resultSet.getLong("id");
                var nome = resultSet.getString("nome");
                var codigo = resultSet.getString("codigo");
                var cargaHoraria = resultSet.getInt("duracao");
                var nivel = resultSet.getString("nivel");

                Curso curso = new Curso(id, codigo, nome, cargaHoraria, Nivel.valueOf(nivel));
                cursos.add(curso);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cursos;
    }

    public void atualizar (String codigoCurso, Curso curso) {
        var sql = "UPDATE cursos SET nome = ?, codigo = ?, duracao = ?, nivel = ? WHERE codigo = ?";

        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, curso.getNome());
            ps.setString(2, curso.getCodigo());
            ps.setInt(3, curso.getCargaHoraria());
            ps.setString(4, curso.getNivel().toString());
            ps.setString(5, codigoCurso);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(String codigo){
        var sql = "DELETE FROM cursos WHERE codigo = ?";

        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, codigo);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Curso buscarPorCodigo(String codigoCurso) {
        Curso curso = null;
        var sql = "SELECT * FROM cursos where codigo = ?";
        try (var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, codigoCurso);
            ResultSet resultSet = ps.executeQuery();

            var cursoEncontrado = resultSet.next();

            if (cursoEncontrado){
                var id = resultSet.getLong("id");
                var nome = resultSet.getString("nome");
                var codigo = resultSet.getString("codigo");
                var cargaHoraria = resultSet.getInt("duracao");
                var nivel = resultSet.getString("nivel");

                curso = new Curso(id, codigo, nome, cargaHoraria, Nivel.valueOf(nivel));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return curso;
    }

    public Curso buscarPorId(Long id) {
        Curso curso = null;
        var sql = "select * from cursos where id = ?";
        try (var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            var cursoEncontrado = resultSet.next();

            if (cursoEncontrado){
                var nome = resultSet.getString("nome");
                var codigo = resultSet.getString("codigo");
                var cargaHoraria = resultSet.getInt("duracao");
                var nivel = resultSet.getString("nivel");

                curso = new Curso(id, codigo, nome, cargaHoraria, Nivel.valueOf(nivel));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return curso;
    }


}
