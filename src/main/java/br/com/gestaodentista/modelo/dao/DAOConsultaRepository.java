package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Consulta;

public class DAOConsultaRepository {

	private Connection connection;
	
	public DAOConsultaRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public Consulta gravarConsulta(Consulta objeto) throws Exception {

		if (objeto.isNovo()) {

			String sql = "INSERT INTO tbl_consulta (fk_dentista, fk_paciente, data_criacao, valor, fk_procedimento) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, objeto.getDentista());
			statement.setLong(2, objeto.getPaciente());
			statement.setDate(3, objeto.getDataCriacao());
			statement.setDouble(4, objeto.getValor());
			statement.setLong(5, objeto.getTratamento());

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_consulta SET fk_dentista=?, fk_paciente=?, data_criacao=?, valor=?, fk_procedimento WHERE id = '" + objeto.getId() + "'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, objeto.getDentista());
			statement.setLong(2, objeto.getPaciente());
			statement.setDate(3, objeto.getDataCriacao());
			statement.setDouble(4, objeto.getValor());
			statement.setLong(5, objeto.getTratamento());

			statement.executeUpdate();
			connection.commit();
		}

		return objeto;

	}

	public List<Consulta> consultarConsultaList() throws Exception {

		List<Consulta> retorno = new ArrayList<Consulta>();

		String sql = "select * from tbl_consulta";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Consulta consulta = new Consulta();

			consulta.setId(resultSet.getLong("id"));
			consulta.setDentista(resultSet.getLong("fk_dentista"));
			consulta.setPaciente(resultSet.getLong("fk_paciente"));
			consulta.setDataCriacao(resultSet.getDate("data_criacao"));
			consulta.setValor(resultSet.getDouble("valor"));
			consulta.setTratamento(resultSet.getLong("fk_procedimento"));
			

			retorno.add(consulta);

		}

		return retorno;
	}

	public List<Consulta> consultarConsultaList(String fk_paciente) throws Exception {

		List<Consulta> retorno = new ArrayList<Consulta>();

		String sql = "select * from tbl_consulta where fk_paciente = '"+fk_paciente+"'";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Consulta consulta = new Consulta();

			consulta.setId(resultSet.getLong("id"));
			consulta.setDentista(resultSet.getLong("fk_dentista"));
			consulta.setPaciente(resultSet.getLong("fk_paciente"));
			consulta.setDataCriacao(resultSet.getDate("data_criacao"));
			consulta.setValor(resultSet.getDouble("valor"));
			consulta.setTratamento(resultSet.getLong("fk_procedimento"));
			

			retorno.add(consulta);

		}

		return retorno;
	}

	public Consulta consultarConsultaID(String id) throws Exception {

		Consulta consulta = new Consulta();

		String sql = "select * from tbl_consulta where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			consulta.setId(resultSet.getLong("id"));
			consulta.setDentista(resultSet.getLong("fk_dentista"));
			consulta.setPaciente(resultSet.getLong("fk_paciente"));
			consulta.setDataCriacao(resultSet.getDate("data_criacao"));
			consulta.setValor(resultSet.getDouble("valor"));
			consulta.setTratamento(resultSet.getLong("fk_procedimento"));			


		}

		return consulta;
	}

	public Consulta consultarConsulta() throws Exception {

		Consulta consulta = new Consulta();

		String sql = "select * from tbl_consulta";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {


			consulta.setId(resultSet.getLong("id"));
			consulta.setDentista(resultSet.getLong("fk_dentista"));
			consulta.setPaciente(resultSet.getLong("fk_paciente"));
			consulta.setDataCriacao(resultSet.getDate("data_criacao"));
			consulta.setValor(resultSet.getDouble("valor"));
			consulta.setTratamento(resultSet.getLong("fk_procedimento"));

		}

		return consulta;
	}


	public void deletarConsulta(String idConsulta) throws Exception {

		String sql = "DELETE FROM tbl_consulta WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setLong(1, Long.parseLong(idConsulta));

		statement.executeUpdate();
		connection.commit();
	}
	
	
}
