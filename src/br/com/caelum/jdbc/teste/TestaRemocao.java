package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.banco.ConnectionPool;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionPool().getConnection();
		
		Statement statement = connection.createStatement();
		statement.executeUpdate("delete from Produto where id > 3");
		
		int count = statement.getUpdateCount();
		
		System.out.println(count + " Linhas atualizadas");
	}

}
