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
import java.util.List;



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
    
public ArrayList<ProdutosDTO> listarProdutos() {
    String sql = "SELECT * FROM produtos";
    Connection conn = new conectaDAO().connectDB();
    
    // Clear the list so you don't double the items when refreshing
    listagem.clear(); 

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            
            // Map database columns to the DTO object
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            // Add the filled object to the list
            listagem.add(produto);
        }
        
        rs.close();
        ps.close();
        conn.close();
        
    } catch (SQLException sex) {
        JOptionPane.showMessageDialog(null, "Erro ao listar: " + sex.getMessage());
    }
    return listagem;
}
    
    public void venderProduto(int id) {

    Connection conn = new conectaDAO().connectDB();


    try {


        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);

        stmt.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }

}
    
    
    public List<ProdutosDTO> listarProdutosVendidos() {

    List<ProdutosDTO> lista = new ArrayList<>();
    Connection conn = new conectaDAO().connectDB();

    ResultSet rs = null;

    try {

        

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        PreparedStatement stmt = conn.prepareStatement(sql);
        
        rs = stmt.executeQuery();

        while(rs.next()){

            ProdutosDTO p = new ProdutosDTO();

            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getInt("valor"));
            p.setStatus(rs.getString("status"));

            lista.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}
    
        
}

