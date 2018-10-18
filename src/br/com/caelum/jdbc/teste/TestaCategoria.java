package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.banco.ConnectionPool;
import br.com.caelum.jdbc.dao.CategoriaDAO;
import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaCategoria {

	public static void main(String[] args) throws SQLException {
		try (Connection con = new ConnectionPool().getConnection()) {

			List<Categoria> categorias = new CategoriaDAO(con).listaComProdutos();

			for (Categoria categoria : categorias) {
				System.out.println(categoria.getNome()); 
				
				for (Produto produto : new ProdutoDAO(con).busca(categoria)) {
					System.out.println(categoria.getNome() + " - " + produto.getNome());
				}
			}
		}

	}
}
