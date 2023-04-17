package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Dias;
import Model.Meses;
import Model.Residente;
import Model.Visita;
import Model.Visitante;

public class DAOVisitas implements InterfaceCrud<Visita>{
	private Connection conexao;

	public DAOVisitas() {
		conexao = ConnectionFactory.getConnection();
	}

	@Override
	public void inserir(Visita objeto) throws SQLException, Exception {
		String sql = "insert into visitas( hora, data, visitante1, visitante2, visitante3, visitante4, obs, nomeResi) values( ?, ?,?,?,?,?,?,?)"; 		
		PreparedStatement comando = conexao.prepareStatement(sql /*Statement.RETURN_GENERATED_KEYS*/); 
		
		comando.setString(1, objeto.getHora());
		comando.setString(2, objeto.getData());
		comando.setString(3, objeto.getVisitante1());
		comando.setString(4, objeto.getVisitante2());
		comando.setString(5, objeto.getVisitante3());
		comando.setString(6, objeto.getVisitante4());
		comando.setString(7, objeto.getObservacao());
		comando.setString(8, objeto.getNomeResi());
		comando.execute();
		comando.close();
		
	}

	@Override
	public void atualizar(Visita objeto) throws SQLException {
		String sql = "update visitas set hora = ?, data = ?, visitante1 = ?, visitante2 = ?,"
				+ " visitante3 = ?, visitante4 = ?, obs = ?, nomeResi = ? where id = ?";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, objeto.getHora());
		comando.setString(2, objeto.getData());
		comando.setString(3, objeto.getVisitante1());
		comando.setString(4, objeto.getVisitante2());
		comando.setString(5, objeto.getVisitante3());
		comando.setString(6, objeto.getVisitante4());
		comando.setString(7, objeto.getObservacao());
		comando.setString(8, objeto.getNomeResi());
		comando.setInt(9, objeto.getId());
		comando.execute();
		comando.close();
	}

	@Override
	public void excluir(Visita objeto) throws SQLException {
		String sql = "delete from visitas where id = ?";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, objeto.getId());
		comando.execute();
		comando.close();
	}

	@Override
	public List<Visita> listar() throws SQLException {

		List<Visita> lista = new ArrayList<>(); 
		String sql = "select * from visitas order by meses and dia asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet rs = comando.executeQuery(); 
		while(rs.next()) {
		     Visita b = new Visita();
		     b.setMeses(Meses.values()[rs.getInt("meses")]);
		     b.setHora(rs.getString("hora"));
		     b.setDia(Dias.values() [rs.getInt("dia")]);
		     b.setData(rs.getString("data"));
		     b.setVisitante1(rs.getString("visitante1"));
		     b.setVisitante2(rs.getString("visitante2"));
		     b.setVisitante3(rs.getString("visitante3"));
		     b.setVisitante4(rs.getString("visitante4"));
		     b.setObservacao(rs.getString("obs"));
		     b.setNomeResi(rs.getString("nomeResi"));
			 b.setId(rs.getInt("id"));
			 /*Residente c = new Residente();
			 c.setId(rs.getInt("residente_id"));
			 c.setNome(rs.getString("nome"));
			 b.setResidente(c);
			 Visitante v = new Visitante();
			 v.setNome(rs.getString("nome"));
			 b.setVisitante(v);*/
			 lista.add(b);
		}
		rs.close();
		comando.close();
		return lista;	
	}
	public List<Visita> buscaData(String parametro) throws SQLException {
		List<Visita> lista = new ArrayList<>();
		String sql = "select * from visitas where data like ? ";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + parametro + "%");
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Visita c = new Visita();
			c.setId(rs.getInt("id"));
			c.setData(rs.getString("data"));
			c.setDia(Dias.values() [rs.getInt("dia")]);
			c.setHora(rs.getString("hora"));
			c.setMeses(Meses.values()[rs.getInt("meses")]);
			c.setNomeResi(rs.getString("nomeResi"));
			c.setVisitante1(rs.getString("visitante1"));
			c.setVisitante2(rs.getString("visitante2"));
			c.setVisitante3(rs.getString("visitante3"));
			c.setVisitante4(rs.getString("visitante4"));
			/*Residente r = new Residente();
			r.setId(rs.getInt("id"));
			r.setNome(rs.getString("nome"));
			r.setQuarto(rs.getString("quarto"));
			c.setResidente(r);*/
			lista.add(c);
		}
		rs.close();
		comando.close();
		return lista;
	}
	public List<Visita> buscaDia(String parametro, String mes) throws SQLException {
		List<Visita> lista = new ArrayList<>();
		String sql = "select * from visitas where dia like ? and meses like ? order by hora asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + parametro + "%");
		comando.setString(2, "%" + mes + "%");
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Visita b = new Visita();
			 b.setMeses(Meses.values()[rs.getInt("meses")]);
		     b.setHora(rs.getString("hora"));
		     b.setDia(Dias.values() [rs.getInt("dia")]);
		     b.setData(rs.getString("data"));
		     b.setVisitante1(rs.getString("visitante1"));
		     b.setVisitante2(rs.getString("visitante2"));
		     b.setVisitante3(rs.getString("visitante3"));
		     b.setVisitante4(rs.getString("visitante4"));
		     b.setObservacao(rs.getString("obs"));
		     b.setNomeResi(rs.getString("nomeResi"));
			 b.setId(rs.getInt("id"));
			
			lista.add(b);
		}
		rs.close();
		comando.close();
		return lista;
	}
	public List<Visita> buscaMes(String nomeResi, String mes) throws SQLException {
		List<Visita> lista = new ArrayList<>();
		String sql = "select * from visitas where nomeResi like ? and data like ? order by data asc";
		java.sql.PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, "%" + nomeResi + "%");
		comando.setString(2, "%" + mes + "%");
		ResultSet rs = comando.executeQuery();
		while (rs.next()) {
			Visita b = new Visita();
			 b.setMeses(Meses.values()[rs.getInt("meses")]);
		     b.setHora(rs.getString("hora"));
		     b.setDia(Dias.values() [rs.getInt("dia")]);
		     b.setData(rs.getString("data"));
		     b.setVisitante1(rs.getString("visitante1"));
		     b.setVisitante2(rs.getString("visitante2"));
		     b.setVisitante3(rs.getString("visitante3"));
		     b.setVisitante4(rs.getString("visitante4"));
		     b.setObservacao(rs.getString("obs"));
		     b.setNomeResi(rs.getString("nomeResi"));
			 b.setId(rs.getInt("id"));
			
			lista.add(b);
		}
		rs.close();
		comando.close();
		return lista;
	}
	
}
