/**
 * 
 */
package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Paciente;

/**
 * @author romer
 *
 */
public class PacienteDAO {

	private Connection connection;

	public PacienteDAO() {
		connection = SingleConnectionBanco.getConnection();
	}

	public Paciente gravarPaciente(Paciente paciente) throws Exception {

		if (paciente.isNovo()) {

			String sql = "INSERT INTO tbl_paciente(nome, rg, cpf, sexo, endereco, bairro, municipio, cep, uf, telefone, celular, email, grupo, situacao) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, paciente.getNome());
			statement.setString(2, paciente.getRg());
			statement.setString(3, paciente.getCpf());
			statement.setString(4, paciente.getSexo());
			statement.setString(5, paciente.getEndereco());
			statement.setString(6, paciente.getBairro());
			statement.setString(7, paciente.getMunicipio());
			statement.setString(8, paciente.getCep());
			statement.setString(9, paciente.getUf());
			statement.setString(10, paciente.getTelefone());
			statement.setString(11, paciente.getCelular());
			statement.setString(12, paciente.getEmail());
			statement.setString(13, paciente.getGrupo());
			statement.setString(14, paciente.getSituacao());

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_paciente SET nome=?, rg=?, cpf=?, sexo=?, endereco=?, bairro=?, municipio=?, cep=?, uf=?, telefone=?, celular=?, email=?, grupo=?, situacao=? WHERE id = '"
					+ paciente.getId() + "'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, paciente.getNome());
			statement.setString(2, paciente.getRg());
			statement.setString(3, paciente.getCpf());
			statement.setString(4, paciente.getSexo());
			statement.setString(5, paciente.getEndereco());
			statement.setString(6, paciente.getBairro());
			statement.setString(7, paciente.getMunicipio());
			statement.setString(8, paciente.getCep());
			statement.setString(9, paciente.getUf());
			statement.setString(10, paciente.getTelefone());
			statement.setString(11, paciente.getCelular());
			statement.setString(12, paciente.getEmail());
			statement.setString(13, paciente.getGrupo());
			statement.setString(14, paciente.getSituacao());

			statement.executeUpdate();
			connection.commit();
		}
		
		return consultarPaciente(paciente.getCpf());

	}
	
	public Paciente consultarPaciente(String cpf)throws Exception {
		
		Paciente paciente = new Paciente();
		
		String sql = "select * from tbl_paciente where cpf = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			paciente.setId(resultSet.getLong("id"));
			paciente.setNome(resultSet.getString("nome"));
			paciente.setRg(resultSet.getString("rg"));
			paciente.setCpf(resultSet.getString("cpf"));
			paciente.setSexo(resultSet.getString("sexo"));
			paciente.setEndereco(resultSet.getString("endereco"));
			paciente.setBairro(resultSet.getString("bairro"));
			paciente.setMunicipio(resultSet.getString("municipio"));
			paciente.setCep(resultSet.getString("cep"));
			paciente.setUf(resultSet.getString("uf"));
			paciente.setTelefone(resultSet.getString("telefone"));
			paciente.setCelular(resultSet.getString("celular"));
			paciente.setEmail(resultSet.getString("email"));
			paciente.setGrupo(resultSet.getString("grupo"));
			paciente.setSituacao(resultSet.getString("situacao"));
			
		}
		
		return paciente;
	}

}
