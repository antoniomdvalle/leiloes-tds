/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        try{
            Connection conn = new conectaDAO().connectDB();
        if (conn == null) {
        JOptionPane.showMessageDialog(null, "Erro: Não foi possível conectar ao banco de dados.");
        return;
    }
        //conn.setAutoCommit(true);
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        
            PreparedStatement prep = conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor().doubleValue());
            prep.setString(3, "A Venda");
            
            
            int rows = prep.executeUpdate();
        
        if (rows > 0) {
            JOptionPane.showMessageDialog(null, "Sucesso! Produto salvo no banco.");
        }
        
        
            prep.executeUpdate();
            prep.close();
            
        }catch(SQLException sex){
            sex.printStackTrace();
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

