
package br.com.gerlocacao.dal;
import java.sql.*;

public class ModConexao {
    //Estabelecer conexão com o banco
    // Método é chamaco de conect
    //A variável é chamada de conexao
    public static Connection conect(){
        java.sql.Connection conexao = null;
        //drive de conexao
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenar dados
        String url = "jdbc:mysql://localhost:3306/dbgerlocacao";
        String user = "root";
        String password = "101010";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) { 
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }
    
}
