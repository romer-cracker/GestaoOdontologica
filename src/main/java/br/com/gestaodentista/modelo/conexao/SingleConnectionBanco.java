/**
 * 
 */
package br.com.gestaodentista.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author romer
 *
 */
public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://ec2-3-234-131-8.compute-1.amazonaws.com:5432/deat23hu1p2rr8?sslmode=require&autoReconnect=true";
	
	private static String user = "rdlzctktoutxvw";
	
	private static String senha = "3f4465ea5ca1c569f3dea78b74f590251385089f9519311789dd9e5bcb577912";
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}
	
	public static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
