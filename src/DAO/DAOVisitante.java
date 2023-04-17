package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Residente;
import Model.Visitante;


public class DAOVisitante  implements InterfaceCrud<Visitante> {
	private Connection conexao;
	public DAOVisitante() {
		conexao = ConnectionFactory.getConnection();
	}
	
	@Override
	public void inserir(Visitante objeto) throws SQLException, Exception {
		String sql = "insert into visitante( nome, nomeResi) values( ?, ?)"; 		
		PreparedStatement comando = conexao.prepareStatement(sql /*Statement.RETURN_GENERATED_KEYS*/); 
		
		comando.setString(1, objeto.getNome());
		comando.setString(2, objeto.getNomeResi());
		comando.execute();
		comando.close();
	}
	@Override
	public void atualizar(Visitante objeto) throws SQLException {
		String sql = "update visitante set nome = ?, NomeResi = ? where id = ?";  		
		PreparedStatement comando = conexao.prepareStatement(sql/*Statement.RETURN_GENERATED_KEYS*/); 
		comando.setString(1, objeto.getNome());
		comando.setString(2, objeto.getNomeResi());
		comando.setInt(3, objeto.getId());
		comando.execute();
		comando.close();
		
	}
	@Override
	public void excluir(Visitante objeto) throws SQLException {
		String sql = "delete from visitante where id = ?"; 
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, objeto.getId());  	
		comando.execute();
		comando.close();	
		
	}
	
	@Override
	public List<Visitante> listar() throws SQLException {
		
		List<Visitante> lista = new ArrayList<>(); 
		String sql = "select * from visitante order by id asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet rs = comando.executeQuery(); 
		while(rs.next()) {
		     Visitante b = new Visitante();
		     b.setNome(rs.getString("nome"));
		     b.setNomeResi(rs.getString("nomeResi"));
			b.setId(rs.getInt("id"));
			Residente c = new Residente();
			c.setId(rs.getInt("residente_id"));
			c.setNome(rs.getString("nome"));
			b.setResidente(c);;
			lista.add(b);
		}
		rs.close();
		comando.close();
		return lista;		
	}
	public List<Visitante> buscaVisi(){
		  List<Visitante> lista = new ArrayList<Visitante>();
			try {
				  String sql = "select * from visitante";
				PreparedStatement comando = conexao.prepareStatement(sql);
			//	comando.setString(1, p.getNome());
				ResultSet rs = comando.executeQuery();
				while(rs.next()) {
					Visitante p = new Visitante();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setNomeResi(rs.getString("nomeResi"));
					
					lista.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return lista;  
	  }
	public List<Visitante> listar(String parametro) throws SQLException {
		List<Visitante> lista = new ArrayList<>();
		String sql = "select * from visitante where nomeResi like ?";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + parametro + "%");
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Visitante c = new Visitante();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setNomeResi(rs.getString("nomeResi"));
			
			lista.add(c);
		}
		rs.close();
		comando.close();
		return lista;
	}
}
