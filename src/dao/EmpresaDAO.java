/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Empresa;
import conexao.Conexao;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;






/**
 *
 * @author ferna
 */
public class EmpresaDAO {
    private Conexao conexao;
    private Connection conn;
    
    public EmpresaDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Empresa empresa){
        String sql = "INSERT INTO empresa (Nome, Endereco, Email, CNPJ) VALUES "
                + "(?, ?, ?, ?)";
    try{
    PreparedStatement stmt = this.conn.prepareStatement(sql);
    stmt.setString(1, empresa.getNome());
    stmt.setString(2, empresa.getEndereco());
    stmt.setString(3, empresa.getEmail());
    stmt.setString(4, empresa.getCNPJ());
    stmt.execute();
    
        }
    
    catch (Exception e){
        
        System.out.println("Não foi possivel a conexão!" + e.getMessage());
        }
    }
    
    public void editar(Empresa empresa){
        String sql = "UPDATE empresa SET Nome=?, Endereco=?, Email=?, CNPJ=? WHERE Id=?";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,empresa.getNome());
            stmt.setString(2,empresa.getEndereco());
            stmt.setString(3, empresa.getEmail());
            stmt.setString(4, empresa.getCNPJ());
            stmt.setInt(5, empresa.getId());
            stmt.execute();
            
        } catch(Exception e){
               System.out.println("Erro ao editar empresa" + e.getMessage());
        }
        
    }
    
    public void excluir(int id){
        
        String sql = "DELETE FROM empresa WHERE id= ?";
            try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.execute();
                
            } catch ( Exception e){
                System.out.println("Erro ao excluir empresa :(" + e.getMessage());
            }
    }
        
    
    public Empresa getEmpresa(int id){
        String sql = "SELECT * FROM empresa WHERE id = ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Empresa empresa = new Empresa();
            
            rs.first();
            empresa.setId(id);
            empresa.setNome(rs.getString("Nome"));
            empresa.setEndereco(rs.getString("Endereco"));
            empresa.setEmail(rs.getString("Email"));
            empresa.setCNPJ(rs.getString("CNPJ"));
            return empresa;
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
                
        
    }
    
    
    public List<Empresa> getEmpresa(){
        
        String sql = "SELECT * FROM empresa";
        try{
            PreparedStatement stmt= this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Empresa> ListaEmpresa = new ArrayList<>();
            
            while(rs.next()){
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNome(rs.getString("Nome"));
                empresa.setEndereco(rs.getString("Endereco"));
                empresa.setEmail(rs.getString("Email"));
                empresa.setCNPJ(rs.getString("CNPJ"));
                ListaEmpresa.add(empresa);
                
            }
            return ListaEmpresa;
            
            
            
        
        } catch(Exception e){
            return null;
        }
    }
    
}
