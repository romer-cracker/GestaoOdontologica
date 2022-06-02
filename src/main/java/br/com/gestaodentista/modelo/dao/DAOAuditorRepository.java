package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Auditor;

public class DAOAuditorRepository {

	private Connection connection;
	
	public DAOAuditorRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public Auditor gravarAuditor(Auditor objeto) throws Exception {

		if (objeto.isNovo()) {

			String sql = "INSERT INTO tbl_auditor(data_criacao, data_ultima_modificacao, modificador_por, criado_por) VALUES(?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, objeto.getDataCriacao());
			statement.setDate(2, objeto.getDataUltimaModificacao());
			statement.setString(3, objeto.getModificadoPor());
			statement.setString(4, objeto.getCriadoPor());

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_auditor SET data_criacao=?, data_ultima_modificacao=?, modificador_por=?, criado_por=?  WHERE id = '" + objeto.getId()
					+ "'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, objeto.getDataCriacao());
			statement.setDate(2, objeto.getDataUltimaModificacao());
			statement.setString(3, objeto.getModificadoPor());
			statement.setString(4, objeto.getCriadoPor());

			statement.executeUpdate();
			connection.commit();
		}

		return consultarAuditor(objeto.getModificadoPor());

	}

	public List<Auditor> consultarAuditorList() throws Exception {

		List<Auditor> retorno = new ArrayList<Auditor>();

		String sql = "select * from tbl_auditor";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Auditor auditor = new Auditor();

			auditor.setId(resultSet.getLong("id"));
			auditor.setDataCriacao(resultSet.getDate("data_criacao"));
			auditor.setDataUltimaModificacao(resultSet.getDate("data_ultima_modificacao"));
			auditor.setModificadoPor(resultSet.getString("modificador_por"));
			auditor.setCriadoPor(resultSet.getString("criado_por"));

			retorno.add(auditor);

		}

		return retorno;
	}

	public List<Auditor> consultarAuditorList(String criadoPor) throws Exception {

		List<Auditor> retorno = new ArrayList<Auditor>();

		String sql = "select * from tbl_auditor where upper(criado_por) like upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + criadoPor + "%");

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Auditor auditor = new Auditor();

			auditor.setId(resultSet.getLong("id"));
			auditor.setDataCriacao(resultSet.getDate("data_criacao"));
			auditor.setDataUltimaModificacao(resultSet.getDate("data_ultima_modificacao"));
			auditor.setModificadoPor(resultSet.getString("modificador_por"));
			auditor.setCriadoPor(resultSet.getString("criado_por"));

			retorno.add(auditor);

		}

		return retorno;
	}

	public Auditor consultarAuditorID(String id) throws Exception {

		Auditor auditor = new Auditor();

		String sql = "select * from tbl_auditor where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			auditor.setId(resultSet.getLong("id"));
			auditor.setDataCriacao(resultSet.getDate("data_criacao"));
			auditor.setDataUltimaModificacao(resultSet.getDate("data_ultima_modificacao"));
			auditor.setModificadoPor(resultSet.getString("modificador_por"));
			auditor.setCriadoPor(resultSet.getString("criado_por"));



		}

		return auditor;
	}

	public Auditor consultarAuditor(String modificadoPor) throws Exception {

		Auditor auditor = new Auditor();

		String sql = "select * from tbl_auditor where upper(modificador_por)= '"+modificadoPor+"' ";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			auditor.setId(resultSet.getLong("id"));
			auditor.setDataCriacao(resultSet.getDate("data_criacao"));
			auditor.setDataUltimaModificacao(resultSet.getDate("data_ultima_modificacao"));
			auditor.setModificadoPor(resultSet.getString("modificador_por"));
			auditor.setCriadoPor(resultSet.getString("criado_por"));



		}

		return auditor;
	}


	public void deletarAuditor(String idAuditor) throws Exception {

		String sql = "DELETE FROM tbl_auditor WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(idAuditor));

		statement.executeUpdate();
		connection.commit();
	}
}
