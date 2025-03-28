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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement stmt;
    ResultSet resultset;

    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        try {
            stmt = conn.prepareStatement("INSERT INTO produtos (nome,valor,status)VALUES(?,?,?)");
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, "A Venda");
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" +ex);
        }
        
    }
    
    public List<ProdutosDTO> listarProdutos(){

        try {
            conn = new conectaDAO().connectDB();
            
            String sql;
            sql = "SELECT * FROM uc11.produtos;";
            stmt = conn.prepareStatement(sql);
            resultset = stmt.executeQuery(sql);
            
            List<ProdutosDTO> lista = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO produtosDTO = new ProdutosDTO();
                produtosDTO.setId(resultset.getInt(1));
                produtosDTO.setNome(resultset.getString(2));
                produtosDTO.setValor(resultset.getInt(3));
                produtosDTO.setStatus(resultset.getString(4));

                lista.add(produtosDTO);
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        }
    }      
}

