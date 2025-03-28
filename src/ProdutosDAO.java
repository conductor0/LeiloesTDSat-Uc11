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
        }finally {
            
            try {
                if (resultset != null) {
                    resultset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }    
    }
    
    public void venderProduto(int pesquisa){
        try {
            conn = new conectaDAO().connectDB();
            
            String sql_2;
            sql_2 = "update uc11.produtos SET status = 'Vendido' WHERE id = ?";
                    
            stmt = conn.prepareStatement(sql_2);
            stmt.setInt(1,pesquisa);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

