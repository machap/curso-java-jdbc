package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class CategoriaDAO {

	private final Connection con;

	public CategoriaDAO(Connection con) {
		this.con = con;
	}

	public List<Categoria> lista() throws SQLException {

		List<Categoria> categorias = new ArrayList<>();

		String sql = "select * from Produto";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();
			
			transformaResultadoEmProdutos(categorias, stmt);
		}
		return categorias;
	}

	private void transformaResultadoEmProdutos(List<Categoria> categorias, PreparedStatement stmt) throws SQLException {
		try (ResultSet rs = stmt.getResultSet()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				
				Categoria categoria = new Categoria(id, nome);

				categorias.add(categoria);
			}
		}
	}
}
