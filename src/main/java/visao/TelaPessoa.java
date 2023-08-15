package visao;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.PessoaDAO;
import modelo.Pessoa;

import javax.swing.JComboBox;
import javax.swing.JTable;

public class TelaPessoa extends JFrame {

	private JPanel contentPane;
	 JTable tabela;
	    JScrollPane barraRolagem;
		private static DefaultTableModel modelo;

	    String [] colunas = {"Nome", "Telefone", "Email"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPessoa frame = new TelaPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPessoa() {

		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1, 1));
	      
		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Idade"});
		tabela = new JTable();
		
		tabela.setModel(modelo);

		barraRolagem = new JScrollPane(tabela);
	        contentPane.add(barraRolagem);

	        getContentPane().add(contentPane);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(500, 120);
	        setVisible(true);
	        
	        listar();

	}

	private void listar() {
		PessoaDAO dao = new PessoaDAO();
		ArrayList<Pessoa> pessoas = dao.listar();
		modelo.getDataVector().removeAllElements();

		for (int i = 0; i < pessoas.size(); i++) {
			Pessoa pessoa = pessoas.get(i);
			modelo.addRow(new Object[] { pessoa.getId(),  pessoa.getPrimeiro_nome(), pessoa.getIdade(), });
		}
	}
}
