/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Funcionario;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ferna
 */
public class FuncionarioDAO {
    
    private Conexao conexao;
    private Connection conn;
    
    public FuncionarioDAO(){
        
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
        
    }
    
    public void inserir(Funcionario funcionario){
       String sql = "INSERT INTO funcionario(nome, email, CPF, empresa) VALUES "
       + "(? , ? , ? , ?)";
               
               
               try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getEmail());
        stmt.setString(3, funcionario.getCPF());
        stmt.setString(4, funcionario.getEmpresa());
        stmt.execute();
               
               }
        
        catch (Exception e){
        System.out.println("erro ao inserir funcionário!");
        }
        
        }
    
       public void editar (Funcionario funcionario){
           String sql = "UPDATE funcionario SET Nome=?, Email=?, CPF=?, Empresa=? WHERE ID=?";
            try {
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getEmail());
                stmt.setString(3, funcionario.getCPF());
                stmt.setString(4, funcionario.getEmpresa());
                stmt.setInt(5, funcionario.getId());
                stmt.execute();
                  
            } catch (Exception e) {
                System.out.println("Erro ao editar funcionário" + e.getMessage());
            }
       }
        
       
       public void excluir(int id){
           
           String sql = "DELETE FROM funcionario WHERE id= ?";
           try{
               PreparedStatement stmt = this.conn.prepareStatement(sql);
               stmt.setInt(1, id);
               stmt.execute();
               
           }catch(Exception e){
               System.out.println("Erro ao excluir funcionário" + e.getMessage());
               
           }
           
       }
    
        public Funcionario getFuncionario(int id){
            String sql = "SELECT * FROM funcionario WHERE id = ?";
            
                try {
                    PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
                    stmt.setInt(1,id);
                    ResultSet rs = stmt.executeQuery();
                    Funcionario funcionario = new Funcionario();
                    
                    rs.first();
                    funcionario.setId(id);
                    funcionario.setNome(rs.getString("Nome"));
                    funcionario.setEmail(rs.getString("Email"));
                    funcionario.setCPF(rs.getString("CPF"));
                    funcionario.setEmpresa(rs.getString("Empresa"));
                    
                    return funcionario;
               
                } catch(Exception e ){
                    return null;
                }
                    
                    
            
        }
    
        public List<Funcionario> getFuncionario(){
            String sql = "SELECT * FROM funcionario";
            try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                List<Funcionario> ListaFuncionario = new ArrayList<>();
                
                while(rs.next()){
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("Nome"));
                    funcionario.setEmail(rs.getString("Email"));
                    funcionario.setCPF(rs.getString("CPF"));
                    funcionario.setEmpresa(rs.getString("Empresa"));
                    ListaFuncionario.add(funcionario);
                }
                return ListaFuncionario;
            } catch (Exception e ){
                return null;
            }
        }
    
    
    }
    

