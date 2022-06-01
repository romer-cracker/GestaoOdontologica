package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.UserAdminFuncionario;

public class DAOUserAdminFuncionariorepository {

	private Connection connection;
	
	public DAOUserAdminFuncionariorepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public UserAdminFuncionario gravarAdmin(UserAdminFuncionario objeto) throws Exception {

		if (objeto.isNovo()) {

			String sql = "INSERT INTO tbl_admin(nome, admissao, login, senha) VALUES(?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getAdmissao());
			statement.setString(3, objeto.getLogin());
			statement.setString(4, objeto.getSenha());
			

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_admin SET nome=?, admissao=?, login=?, senha=?  WHERE id = '"+objeto.getId()+"'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, objeto.getNome());
			statement.setString(2, objeto.getAdmissao());
			statement.setString(3, objeto.getLogin());
			statement.setString(4, objeto.getSenha());

			statement.executeUpdate();
			connection.commit();
		}
		
		return consultarAdmin(objeto.getLogin());

	}
	
		public List<UserAdminFuncionario> consultarAdminList()throws Exception {
		
		List<UserAdminFuncionario> retorno = new ArrayList<UserAdminFuncionario>();
		
		String sql = "select * from tbl_admin";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			UserAdminFuncionario userAdminFuncionario = new UserAdminFuncionario();
			
			userAdminFuncionario.setId(resultSet.getLong("id"));
			userAdminFuncionario.setNome(resultSet.getString("nome"));
			userAdminFuncionario.setAdmissao(resultSet.getString("admissao"));
			userAdminFuncionario.setLogin(resultSet.getString("login"));
			userAdminFuncionario.setSenha(resultSet.getString("senha"));
			
			
			retorno.add(userAdminFuncionario);
			
		}
		
		return retorno;
	}
		
	
		public List<UserAdminFuncionario> consultarAdminList(String nome)throws Exception {
			
			List<UserAdminFuncionario> retorno = new ArrayList<UserAdminFuncionario>();
			
			String sql = "select * from tbl_admin where upper(nome) like upper(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" +nome+ "%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				UserAdminFuncionario userAdminFuncionario = new UserAdminFuncionario();
				
				userAdminFuncionario.setId(resultSet.getLong("id"));
				userAdminFuncionario.setAdmissao(resultSet.getString("admissao"));
				userAdminFuncionario.setNome(resultSet.getString("nome"));
				userAdminFuncionario.setLogin(resultSet.getString("login"));
				userAdminFuncionario.setSenha(resultSet.getString("senha"));
				
				
				retorno.add(userAdminFuncionario);
				
			}
			
			return retorno;
		}
		
		
		
		
		
		
		
		public UserAdminFuncionario consultarAdminID(String id)throws Exception {
			
			UserAdminFuncionario userAdminFuncionario = new UserAdminFuncionario();
			
			String sql = "select * from tbl_admin where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				userAdminFuncionario.setId(resultSet.getLong("id"));
				userAdminFuncionario.setNome(resultSet.getString("nome"));
				userAdminFuncionario.setAdmissao(resultSet.getString("admissao"));
				userAdminFuncionario.setLogin(resultSet.getString("login"));
				userAdminFuncionario.setSenha(resultSet.getString("senha"));
				
			}
			
			return userAdminFuncionario;
		}
		
		
		public UserAdminFuncionario consultarAdmin()throws Exception {
			
			UserAdminFuncionario userAdminFuncionario = new UserAdminFuncionario();
			
			String sql = "select * from tbl_admin";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				userAdminFuncionario.setId(resultSet.getLong("id"));
				userAdminFuncionario.setNome(resultSet.getString("nome"));
				userAdminFuncionario.setAdmissao(resultSet.getString("admissao"));
				userAdminFuncionario.setLogin(resultSet.getString("login"));
				userAdminFuncionario.setSenha(resultSet.getString("senha"));
				
			}
			
			return userAdminFuncionario;
		}
	
	
	
		public UserAdminFuncionario consultarAdmin(String login)throws Exception {
		
		UserAdminFuncionario userAdminFuncionario = new UserAdminFuncionario();
		
		String sql = "select * from tbl_admin where upper(login) = upper('"+login+"') ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			userAdminFuncionario.setId(resultSet.getLong("id"));
			userAdminFuncionario.setNome(resultSet.getString("nome"));
			userAdminFuncionario.setAdmissao(resultSet.getString("admissao"));
			userAdminFuncionario.setLogin(resultSet.getString("login"));
			userAdminFuncionario.setSenha(resultSet.getString("senha"));
			
		}
		
		return userAdminFuncionario;
	}
	
	public boolean validarLogin(String login)throws Exception {
		
		String sql = "select count(1) > 0 as existe from tbl_admin where upper(login) = upper('"+login+"')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		return resultSet.getBoolean("existe");
	}
	
	public void deletarAdmin(String idAdmin)throws Exception {
		
		String sql = "DELETE FROM tbl_paciente WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(idAdmin));
		
		statement.executeUpdate();
		connection.commit();
	}

}

	
	
