import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.ConnectionPool;
import br.com.caelum.jdbc.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaInsercaoProduto {

	public static void main(String[] args) throws SQLException {

		Produto mesa = new Produto("Fone JBL", "Fone de ouvido JBL Branco");

		try (Connection con = new ConnectionPool().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(con);
			dao.salva(mesa);

			List<Produto> produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Existe o produto: " + produto);
			}
		}
	}

}
