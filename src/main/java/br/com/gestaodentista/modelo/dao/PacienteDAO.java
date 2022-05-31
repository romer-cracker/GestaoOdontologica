/**
 * 
 */
package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

			String sql = "UPDATE tbl_paciente SET nome=?, rg=?, cpf=?, sexo=?, endereco=?, bairro=?, municipio=?, cep=?, uf=?, telefone=?, celular=?, email=?, grupo=?, situacao=? WHERE id = '"+paciente.getId()+"'";
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
	
		public List<Paciente> consultarPacienteList()throws Exception {
		
		List<Paciente> retorno = new ArrayList<Paciente>();
		
		String sql = "select * from tbl_paciente limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Paciente paciente = new Paciente();
			
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
			
			retorno.add(paciente);
			
		}
		
		return retorno;
	}
		
	
		public List<Paciente> consultarPacienteList(String nome)throws Exception {
			
			List<Paciente> retorno = new ArrayList<Paciente>();
			
			String sql = "select * from tbl_paciente where upper(nome) like upper(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" +nome+ "%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Paciente paciente = new Paciente();
				
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
				
				retorno.add(paciente);
				
			}
			
			return retorno;
		}
		
		public List<Paciente> consultarPacienteListPaginada(Integer offset)throws Exception {
			
			List<Paciente> retorno = new ArrayList<Paciente>();
			
			String sql = "select * from tbl_paciente where nome = ? order by nome offset "+offset+" limit 5";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Paciente paciente = new Paciente();
				
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
				
				retorno.add(paciente);
				
			}
			
			return retorno;
		}
		
		public List<Paciente> consultarPacienteListOffSet(String nome, int offset)throws Exception {
			
			List<Paciente> retorno = new ArrayList<Paciente>();
			
			String sql = "select * from tbl_paciente where upper(nome) like upper(?) offset "+offset+" limit 5";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + nome + "%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Paciente paciente = new Paciente();
				
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
				
				retorno.add(paciente);
				
			}
			
			return retorno;
		}
		
		public int totalPagina() throws Exception {
			
			String sql = "select count(1) as total from tbl_paciente  where id = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultado = statement.executeQuery();
			
			resultado.next();
			
			Double cadastros = resultado.getDouble("total");
			
			Double porpagina = 5.0;
			
			Double pagina = cadastros / porpagina;
			
			Double resto = pagina % 2;
			
			if (resto > 0) {
				pagina ++;
			}
			
			return pagina.intValue();
			
		}
		
		public int consultarPacienteListTotalPaginaPaginacao(String nome) throws Exception {
			
			
			String sql = "select count(1) as total from tbl_paciente  where upper(nome) like upper(?)";
		
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + nome + "%");
			
			ResultSet resultado = statement.executeQuery();
			
			resultado.next();
			
			Double cadastros = resultado.getDouble("total");
			
			Double porpagina = 5.0;
			
			Double pagina = cadastros / porpagina;
			
			Double resto = pagina % 2;
			
			if (resto > 0) {
				pagina ++;
			}
			
			return pagina.intValue();
			
		}
		
		public Paciente consultarPacienteID(String id)throws Exception {
			
			Paciente paciente = new Paciente();
			
			String sql = "select * from tbl_paciente where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			
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
		
		
		public Paciente consultarPaciente()throws Exception {
			
			Paciente paciente = new Paciente();
			
			String sql = "select * from tbl_paciente";
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
	
	
	
		public Paciente consultarPaciente(String cpf)throws Exception {
		
		Paciente paciente = new Paciente();
		
		String sql = "select * from tbl_paciente where cpf = '"+cpf+"' ";
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
	
	public boolean validarCpf(String cpf)throws Exception {
		
		String sql = "select count(1) > 0 as existe from tbl_paciente where cpf = '"+cpf+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		return resultSet.getBoolean("existe");
	}
	
	public void deletarPaciente(String idPaciente)throws Exception {
		
		String sql = "DELETE FROM tbl_paciente WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(idPaciente));
		
		statement.executeUpdate();
		connection.commit();
	}

}
