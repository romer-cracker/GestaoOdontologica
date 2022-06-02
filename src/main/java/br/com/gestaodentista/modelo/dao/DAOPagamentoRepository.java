package br.com.gestaodentista.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.gestaodentista.modelo.conexao.SingleConnectionBanco;
import br.com.gestaodentista.modelo.entidade.Pagamento;

public class DAOPagamentoRepository {

	private Connection connection;
	
	public DAOPagamentoRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public Pagamento gravarPagamento(Pagamento objeto) throws Exception {

		if (objeto.isNovo()) {

			String sql = "INSERT INTO tbl_pagamento(data_pagamento, data_vencimento, valor, juros, valor_total, descricao) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, objeto.getDataPagamento());
			statement.setDate(2, objeto.getDataVencimento());
			statement.setDouble(3, objeto.getValor());
			statement.setDouble(4, objeto.getJuros());
			statement.setDouble(5, objeto.getValorTotal());
			statement.setString(6, objeto.getDescricao());
			

			statement.execute();
			connection.commit();

		} else {

			String sql = "UPDATE tbl_pagamento SET data_pagamento=?, data_vencimento=?, valor=?, juros=?, valor_total=?, decricao=?  WHERE id = '"+objeto.getId()+"'";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setDate(1, objeto.getDataPagamento());
			statement.setDate(2, objeto.getDataVencimento());
			statement.setDouble(3, objeto.getValor());
			statement.setDouble(4, objeto.getJuros());
			statement.setDouble(5, objeto.getValorTotal());
			statement.setString(6, objeto.getDescricao());

			statement.executeUpdate();
			connection.commit();
		}
		
		return consultarPagamento(objeto.getDescricao());

	}
	
		public List<Pagamento> consultarPagamentoList()throws Exception {
		
		List<Pagamento> retorno = new ArrayList<Pagamento>();
		
		String sql = "select * from tbl_pagamento";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			Pagamento pagamento = new Pagamento();
			
			pagamento.setId(resultSet.getLong("id"));
			pagamento.setDataPagamento(resultSet.getDate("data_pagamento"));
			pagamento.setDataVencimento(resultSet.getDate("data_vencimento"));
			pagamento.setValor(resultSet.getDouble("valor"));
			pagamento.setJuros(resultSet.getDouble("juros"));
			pagamento.setValorTotal(resultSet.getDouble("valor_total"));
			pagamento.setDescricao(resultSet.getString("descricao"));
			
			
			retorno.add(pagamento);
			
		}
		
		return retorno;
	}
		
	
		public List<Pagamento> consultarPagamentoList(String descricao)throws Exception {
			
			List<Pagamento> retorno = new ArrayList<Pagamento>();
			
			String sql = "select * from tbl_pagamento where upper(descricao) like upper(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" +descricao+ "%");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Pagamento pagamento = new Pagamento();
				
				pagamento.setId(resultSet.getLong("id"));
				pagamento.setDataPagamento(resultSet.getDate("data_pagamento"));
				pagamento.setDataVencimento(resultSet.getDate("data_vencimento"));
				pagamento.setValor(resultSet.getDouble("valor"));
				pagamento.setJuros(resultSet.getDouble("juros"));
				pagamento.setValorTotal(resultSet.getDouble("valor_total"));
				pagamento.setDescricao(resultSet.getString("descricao"));
				
				
				retorno.add(pagamento);
				
			}
			
			return retorno;
		}
		
		
		
		
		
		
		
		public Pagamento consultarPagamentoID(String id)throws Exception {
			
			Pagamento pagamento = new Pagamento();
			
			String sql = "select * from tbl_pagamento where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				pagamento.setId(resultSet.getLong("id"));
				pagamento.setDataPagamento(resultSet.getDate("data_pagamento"));
				pagamento.setDataVencimento(resultSet.getDate("data_vencimento"));
				pagamento.setValor(resultSet.getDouble("valor"));
				pagamento.setJuros(resultSet.getDouble("juros"));
				pagamento.setValorTotal(resultSet.getDouble("valor_total"));
				pagamento.setDescricao(resultSet.getString("descricao"));
				
				
				
			}
			
			return pagamento;
		}
		
		
		public Pagamento consultarPagamento(String descricao)throws Exception {
			
			Pagamento pagamento = new Pagamento();
			
			String sql = "select * from tbl_pagamento where upper(descricao) = '"+descricao+"'";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				pagamento.setId(resultSet.getLong("id"));
				pagamento.setDataPagamento(resultSet.getDate("data_pagamento"));
				pagamento.setDataVencimento(resultSet.getDate("data_vencimento"));
				pagamento.setValor(resultSet.getDouble("valor"));
				pagamento.setJuros(resultSet.getDouble("juros"));
				pagamento.setValorTotal(resultSet.getDouble("valor_total"));
				pagamento.setDescricao(resultSet.getString("descricao"));
				
				
				
			}
			
			return pagamento;
		}
	
	
	public void deletarPagamento(String idPagamento)throws Exception {
		
		String sql = "DELETE FROM tbl_pagamento WHERE id = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(idPagamento));
		
		statement.executeUpdate();
		connection.commit();
	}
}
