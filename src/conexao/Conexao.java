/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ferna
 */
public class Conexao {

    private String url = "jdbc:mysql://localhost:3306/rhworkout";
    private String username = "root";
    private String password = "";

    public Connection getConexao() {
        try {
            //tenta estabelecer conexao
            Connection conn = DriverManager.getConnection(url, username, password); //senha do mysql

            return conn;
        } catch (SQLException e) {
            //se deu erro na hora de conectar
            System.out.println("Erro ao conectar" + e.getMessage());
            return null;
        }
    }
}
