package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = Database.getConnection()) {
			
			connection.setAutoCommit(false);
			
			String sql = "insert into produto (nome, descricao) values (?, ?)";

			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				adiciona("Notebook", "HP i5 500gb", statement);
				adiciona("Blueray", "HDMI Full", statement);
				
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				System.out.println("Rollback efetuado");
			}

		}
	}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		if (nome.equals("Blueray")) {
			throw new IllegalArgumentException("Problema ocorrido");
		}

		statement.setString(1, nome);
		statement.setString(2, descricao);

		boolean resultado = statement.execute();
		System.out.println(resultado);

		try (ResultSet result = statement.getGeneratedKeys()) {

			while (result.next()) {
				long id = result.getLong("id");
				System.out.println("id gerado: " + id);
			}
		}
	}

}
