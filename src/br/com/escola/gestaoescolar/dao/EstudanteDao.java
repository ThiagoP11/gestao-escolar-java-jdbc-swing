package br.com.escola.gestaoescolar.dao;

import br.com.escola.gestaoescolar.dominio.Curso;
import br.com.escola.gestaoescolar.dominio.Estudante;
import br.com.escola.gestaoescolar.dominio.Nivel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDao {

    public void salvar(Estudante estudante) {
        var sql = "INSERT INTO estudantes(nome, data_nascimento, email, cpf, telefone, endereco)values(?,?,?,?,?,?)";
        //DEFINE VARIAVEIS QUE SÃO FECHAVEIS (CLOSE)
        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, estudante.getNome());
            ps.setDate(2, Date.valueOf(estudante.getDataNascimento()));
            ps.setString(3, estudante.getEmail());
            ps.setString(4, estudante.getCpf());
            ps.setString(5, estudante.getTelefone());
            ps.setString(6, estudante.getEndereco());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Estudante> listar() {
        var estudantes =  new ArrayList<Estudante>();
        var sql = "SELECT * FROM estudantes";
        try (var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                var id = resultSet.getLong("id");
                var nome = resultSet.getString("nome");
                var dataNascimento = Instant.ofEpochMilli(resultSet.getDate("data_nascimento").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                var email = resultSet.getString("email");
                var cpf = resultSet.getString("cpf");
                var telefone = resultSet.getString("telefone");
                var endereco = resultSet.getString("endereco");

                var estudante = new Estudante(id, nome, telefone,endereco, cpf, email,dataNascimento);


                estudantes.add(estudante);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return estudantes;
    }

    public void excluir(String cpf){
        var sql = "DELETE FROM estudantes WHERE cpf = ?";

        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cpf);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar (String cpf, Estudante estudante) {
        var sql = "UPDATE estudantes SET nome = ?, data_nascimento = ?, email = ?, cpf = ?, telefone = ?, endereco = ? WHERE cpf = ?";

        try(var connection = ConnectionFactory.criaConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, estudante.getNome());
            ps.setDate(2, Date.valueOf(estudante.getDataNascimento()));
            ps.setString(3, estudante.getEmail());
            ps.setString(4, estudante.getCpf());
            ps.setString(5, estudante.getTelefone());
            ps.setString(6, estudante.getEndereco());
            ps.setString(7, cpf);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
