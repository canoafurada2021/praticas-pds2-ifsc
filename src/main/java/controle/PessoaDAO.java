package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pessoa;

public class PessoaDAO {

	public ArrayList<Pessoa> listar() {
		Conexao c = Conexao.getInstancia();

		// instanciando a classe conexão
		Connection con = c.conectar();

		// Criando um bojeto de lista para guardar o que o banco trouxer
		ArrayList<Pessoa> pessoas = new ArrayList<>();

		String query = "SELECT * FROM pessoa";
		try {
			// prepara a query sql acima em um obj java
			PreparedStatement ps = con.prepareStatement(query);

			// faz a execução da query
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idPessoa = rs.getInt("id");

				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");

				Pessoa p = new Pessoa();
				p.setId(idPessoa);
				p.setPrimeiro_nome(nome);
				p.setIdade(idade);
				pessoas.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		c.fecharConexao();

		return pessoas;
	}

	public boolean inserir(Pessoa p) {

		Conexao c = Conexao.getInstancia();

		Connection con = c.conectar();

		String query = "INSERT INTO pessoa " + "(id, nome, idade) " + "VALUES (?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getPrimeiro_nome());
			ps.setInt(3, p.getIdade());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean excluir(Pessoa p) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "DELETE FROM pessoa WHERE id_pessoa = ?";

		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getId());

			ps.executeUpdate();

			c.fecharConexao();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean atualizar(Pessoa p) {

		Conexao c = Conexao.getInstancia();

		Connection con = c.conectar();
		String query = "UPDATE pessoa SET primeiro_nome = ? WHERE id_pessoa = ?";

		try {
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, p.getPrimeiro_nome());
			ps.setInt(2, p.getId());
			ps.executeUpdate();
			c.fecharConexao();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
