package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		Connection connection = new ConnectionPool().getConnection();

		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("SELECT * FROM PRODUTO");
		ResultSet resultSet = statement.getResultSet();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");

			System.out.println("ID: " + id);
			System.out.println("NOME " + nome);
			System.out.println("DESCRIÇÃO: " + descricao);
		}

		statement.close();
		resultSet.close();

		connection.close();

	}
}
