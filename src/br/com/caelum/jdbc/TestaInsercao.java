package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		Connection connection = Database.getConnection();
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute(
				"insert into produto (nome, descricao) values ('Notebook', 'Notebook i5')",
				Statement.RETURN_GENERATED_KEYS);
		ResultSet generatedKeys = statement.getGeneratedKeys();
		
		while (generatedKeys.next()) {
			long id = generatedKeys.getLong("id");
			System.out.println("id gerado: " + id);
		}
		generatedKeys.close();
		statement.close();
		connection.close();
	}

}
