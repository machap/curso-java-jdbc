import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.caelum.jdbc.banco.ConnectionPool;
import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class Cadastro extends JFrame {

	private JLabel lbNome;
	private JLabel lbDescricao;
	private JLabel lbCategoria;
	protected JTextField tfNome;
	protected JTextField tfDescricao;
	private JButton btnSalvar;
	private JComboBox cbCategoria;

	public Cadastro() {
		// Definições da tela
		setTitle("Cadastro de Produto");
		setSize(330, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// define a tela sem um Layout pronto
		getContentPane().setLayout(null);

		lbNome = new JLabel("Nome");
		lbNome.setBounds(50, 50, 80, 30);
		getContentPane().add(lbNome);

		tfNome = new JTextField();
		tfNome.setBounds(130, 50, 150, 30);
		getContentPane().add(tfNome);

		lbDescricao = new JLabel("Descrição");
		lbDescricao.setBounds(50, 100, 80, 30);
		getContentPane().add(lbDescricao);

		tfDescricao = new JTextField();
		tfDescricao.setBounds(130, 100, 150, 30);
		getContentPane().add(tfDescricao);

		lbCategoria = new JLabel("Descrição");
		lbCategoria.setBounds(50, 150, 80, 30);
		getContentPane().add(lbCategoria);

		String[] categorias = { "Eletrodoméstico", "Eletrônico", "Móvel" };
		cbCategoria = new JComboBox<String>(categorias);
		cbCategoria.setBounds(130, 150, 150, 30);
		getContentPane().add(cbCategoria);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(120, 200, 80, 30);
		getContentPane().add(btnSalvar);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (tfNome.getText().equals("") && tfDescricao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe os Campos");
				} else {
					Produto produto = new Produto();
					produto.setNome(tfNome.getText());
					produto.setDescricao(tfDescricao.getText());
					produto.setCategoria(cbCategoria.getSelectedIndex());

					try (Connection con = new ConnectionPool().getConnection()) {
						ProdutoDAO dao = new ProdutoDAO(con);

						dao.salva(produto);

					} catch (SQLException e) {
						System.err.println("Erro" + e);
					}
					tfNome.setText("");
					tfDescricao.setText("");
					cbCategoria.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "Salvo");
				}

			}
		});
	}

	public static void main(String[] args) {

		new Cadastro().setVisible(true);
	}
}
