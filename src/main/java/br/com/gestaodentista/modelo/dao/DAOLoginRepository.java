package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Paciente;
import br.com.gestaodentista.modelo.entidade.Usuario;

public class DAOLoginRepository {

	private Connection connection;

	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public boolean validarAutenticacao(Usuario usuario) throws Exception {

		String sql = "select * from tbl_usuario where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, usuario.getLogin());
		statement.setString(2, usuario.getSenha());

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return true;
		}
		return false;
	}

	
}
