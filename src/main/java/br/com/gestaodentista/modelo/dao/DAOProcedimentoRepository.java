package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Dentista;
import br.com.gestaodentista.modelo.entidade.Procedimento;

public class DAOProcedimentoRepository {

	private Connection connection;

	public DAOProcedimentoRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public Procedimento gravarProcedimento(Procedimento objeto) throws Exception {

		if (objeto.isNovo()) {

			String sql = "INSERT INTO tbl_procedimento (nome, descricao) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getDescricao());

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_procedimento SET nome=?, descricao=? WHERE id = '" + objeto.getId() + "'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getDescricao());

			statement.executeUpdate();
			connection.commit();
		}

		return objeto;

	}

	public List<Procedimento> consultarProcedimentoList() throws Exception {

		List<Procedimento> retorno = new ArrayList<Procedimento>();

		String sql = "select * from tbl_procedimento";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Procedimento procedimento = new Procedimento();

			procedimento.setId(resultSet.getLong("id"));
			procedimento.setNome(resultSet.getString("nome"));
			procedimento.setDescricao(resultSet.getString("descricao"));
			

			retorno.add(procedimento);

		}

		return retorno;
	}

	public List<Procedimento> consultarProcedimentoList(String nome) throws Exception {

		List<Procedimento> retorno = new ArrayList<Procedimento>();

		String sql = "select * from tbl_procedimento where upper(nome) like upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Procedimento procedimento = new Procedimento();

			procedimento.setId(resultSet.getLong("id"));
			procedimento.setNome(resultSet.getString("nome"));
			procedimento.setDescricao(resultSet.getString("descricao"));
			

			retorno.add(procedimento);

		}

		return retorno;
	}

	public Procedimento consultarProcedimentoID(String id) throws Exception {

		Procedimento procedimento = new Procedimento();

		String sql = "select * from tbl_procedimento where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			procedimento.setId(resultSet.getLong("id"));
			procedimento.setNome(resultSet.getString("nome"));
			procedimento.setDescricao(resultSet.getString("descricao"));
			


		}

		return procedimento;
	}

	public Procedimento consultarProcedimento() throws Exception {

		Procedimento procedimento = new Procedimento();

		String sql = "select * from tbl_procedimento";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			procedimento.setId(resultSet.getLong("id"));
			procedimento.setNome(resultSet.getString("nome"));
			procedimento.setDescricao(resultSet.getString("descricao"));
			


		}

		return procedimento;
	}

	public Procedimento consultarProcedimento(String descricao) throws Exception {

		Procedimento procedimento = new Procedimento();

		String sql = "select * from tbl_procedimento where descricao = '" + descricao + "' ";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			procedimento.setId(resultSet.getLong("id"));
			procedimento.setNome(resultSet.getString("nome"));
			procedimento.setDescricao(resultSet.getString("descricao"));
			

		}

		return procedimento;
	}


	public void deletarProcedimento(String idProcedimento) throws Exception {

		String sql = "DELETE FROM tbl_procedimento WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(idProcedimento));

		statement.executeUpdate();
		connection.commit();
	}
}
