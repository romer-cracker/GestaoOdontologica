package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Dentista;

public class DAODentistaController {

	private Connection connection;

	public DAODentistaController() {
		connection = SingleConnectionBanco.getConnection();
	}

	public Dentista gravarDentista(Dentista objeto) throws Exception {

		if (objeto.isNovo()) {

			String sql = "INSERT INTO tbl_dentista(cro, nome, cpf, rg) VALUES(?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getCro());
			statement.setString(2, objeto.getNome());
			statement.setString(3, objeto.getCpf());
			statement.setString(4, objeto.getRg());

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_dentista SET cro=?, nome=?, cpf=?, rg=?  WHERE id = '" + objeto.getId()
					+ "'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getCro());
			statement.setString(2, objeto.getNome());
			statement.setString(3, objeto.getCpf());
			statement.setString(4, objeto.getRg());

			statement.executeUpdate();
			connection.commit();
		}

		return consultarDentista(objeto.getCpf());

	}

	public List<Dentista> consultarDentistaList() throws Exception {

		List<Dentista> retorno = new ArrayList<Dentista>();

		String sql = "select * from tbl_dentista";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Dentista dentista = new Dentista();

			dentista.setId(resultSet.getLong("id"));
			dentista.setCro(resultSet.getString("cro"));
			dentista.setNome(resultSet.getString("nome"));
			dentista.setCpf(resultSet.getString("cpf"));
			dentista.setRg(resultSet.getString("rg"));

			retorno.add(dentista);

		}

		return retorno;
	}

	public List<Dentista> consultarDentistaList(String nome) throws Exception {

		List<Dentista> retorno = new ArrayList<Dentista>();

		String sql = "select * from tbl_dentista where upper(nome) like upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Dentista dentista= new Dentista();

			dentista.setId(resultSet.getLong("id"));
			dentista.setCro(resultSet.getString("cro"));
			dentista.setNome(resultSet.getString("nome"));
			dentista.setCpf(resultSet.getString("cpf"));
			dentista.setRg(resultSet.getString("rg"));

			retorno.add(dentista);

		}

		return retorno;
	}

	public Dentista consultarDentistaID(String id) throws Exception {

		Dentista dentista = new Dentista();

		String sql = "select * from tbl_dentista where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			dentista.setId(resultSet.getLong("id"));
			dentista.setCro(resultSet.getString("cro"));
			dentista.setNome(resultSet.getString("nome"));
			dentista.setCpf(resultSet.getString("cpf"));
			dentista.setRg(resultSet.getString("rg"));


		}

		return dentista;
	}

	public Dentista consultarDentista() throws Exception {

		Dentista dentista = new Dentista();

		String sql = "select * from tbl_dentista";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			dentista.setId(resultSet.getLong("id"));
			dentista.setCro(resultSet.getString("cro"));
			dentista.setNome(resultSet.getString("nome"));
			dentista.setCpf(resultSet.getString("cpf"));
			dentista.setRg(resultSet.getString("rg"));


		}

		return dentista;
	}

	public Dentista consultarDentista(String cpf) throws Exception {

		Dentista dentista = new Dentista();

		String sql = "select * from tbl_dentista where cpf = '" + cpf + "' ";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			dentista.setId(resultSet.getLong("id"));
			dentista.setCro(resultSet.getString("cro"));
			dentista.setNome(resultSet.getString("nome"));
			dentista.setCpf(resultSet.getString("cpf"));
			dentista.setRg(resultSet.getString("rg"));

		}

		return dentista;
	}

	public boolean validarCpf(String cpf) throws Exception {

		String sql = "select count(1) > 0 as existe from tbl_dentista where cpf = '" + cpf + "' ";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		resultSet.next();
		return resultSet.getBoolean("existe");
	}

	public void deletarDentista(String idDentista) throws Exception {

		String sql = "DELETE FROM tbl_paciente WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(idDentista));

		statement.executeUpdate();
		connection.commit();
	}
}
