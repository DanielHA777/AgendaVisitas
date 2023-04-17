package DAO;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection conexao;
    private static String usuario = "user";
    private static String senha = "root";
   private static String strConexao = "jdbc:mysql://10.121.0.55:3307/visitas?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true"; 
   public static Connection getConnection() {
	   if(conexao == null) {
		   try {
			conexao =  DriverManager.getConnection(strConexao, usuario, senha);
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("ERRO DE CONEXÃO");
		}
	   }
	   return conexao;
	}
  public static void closeConnection() {  
	  if(conexao != null) {
		  try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRO DE CONEXÃO");
		}
	  }
  }}