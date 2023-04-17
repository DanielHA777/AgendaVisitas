package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Residente;



public class DAOResidente implements InterfaceCrud<Residente> {
	private Connection conexao;

	public DAOResidente() {
		conexao = ConnectionFactory.getConnection();
	}

	@Override
	public void inserir(Residente objeto) throws SQLException, Exception {
		String sql = "insert into residentes(nome, quarto) values(?, ?)";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, objeto.getNome());
		comando.setString(2, objeto.getQuarto());
		comando.execute();
		comando.close();
	}

	@Override
	public void atualizar(Residente objeto) throws SQLException {
		String sql = "update residentes set nome = ?, quarto = ? where id = ?";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, objeto.getNome());
		comando.setString(2, objeto.getQuarto());
		comando.setInt(3, objeto.getId());
		comando.execute();
		comando.close();
	}

	@Override
	public void excluir(Residente objeto) throws SQLException {
		String sql = "delete from residentes where id = ?";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, objeto.getId());
		comando.execute();
		comando.close();

	}

	@Override
	public List<Residente> listar() throws SQLException {
		List<Residente> lista = new ArrayList<>();
		String sql = "select * from residentes order by nome asc";
		PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Residente c = new Residente();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setQuarto(rs.getString("quarto"));
			
			lista.add(c);
		}
		rs.close();
		comando.close();
		return lista;
	}

	public List<Residente> listar(String parametro) throws SQLException {
		List<Residente> lista = new ArrayList<>();
		String sql = "select * from residentes where nome like ?  order by nome asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + parametro + "%");
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Residente c = new Residente();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setQuarto(rs.getString("quarto"));
			
			lista.add(c);
		}
		rs.close();
		comando.close();
		return lista;
	}

	public Residente buscaNome(String nome) throws SQLException {
		Residente c = null;
		String sql = "select * from residentes where nome = ?";
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, nome);
		ResultSet rs = comando.executeQuery();
		if (rs.next()) {
			c = new Residente();
			//c.getId();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setQuarto(rs.getString("quarto"));
			
		}
		rs.close();
		comando.close();
		return c;
	}
	public List<Residente> buscaResi(){
		  List<Residente> lista = new ArrayList<Residente>();
			try {
				  String sql = "select * from residentes";
				PreparedStatement comando = conexao.prepareStatement(sql);
			//	comando.setString(1, p.getNome());
				ResultSet rs = comando.executeQuery();
				while(rs.next()) {
					Residente p = new Residente();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setQuarto(rs.getString("quarto"));
					
					lista.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return lista;  
	  }

	
}
