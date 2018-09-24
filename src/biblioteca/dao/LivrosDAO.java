/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;


import biblioteca.conexao.Conexao;
import biblioteca.modelo.Serialize;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvimento
 */
public class LivrosDAO {
        
    private static ResultSet rs;
        
        public static boolean consultar_login (String lg, String sn) throws  ClassNotFoundException, SQLException{  
        // Manda como parametro para ele duas variaveis para ele procurar no banco de dados!
            
            boolean autenticado = false;
            String sql;  
            Conexao conexao = new Conexao();
            Statement stm = null;
            stm = conexao.getConnection().createStatement();
            sql = "select cod_usuario from usuarios_pdv where login = '"+lg+"' and senha ='"+sn+"'";
            rs = stm.executeQuery(sql);
            while(rs.next()){
               //serial.setUsuario(rs.getString("cod_usuario"));
               autenticado = true; 
            }
            rs.close(); 
            return autenticado;  
        }      
    
public Boolean Inserir(Serialize s) throws ClassNotFoundException{
    Boolean retorno = false;
    Conexao conexao = new Conexao();
    try { 

        String sql = "INSERT INTO livros (titulo, autor, ano_edicao, resenha, imagem) VALUES(?, ?, ?, ?, ?)";
        
        PreparedStatement pst = conexao.getPreparedStatement(sql);
        pst.setString(1, s.getTitulo());
        pst.setString(2, s.getAutor());
        pst.setInt(3, s.getAno());
        pst.setString(4, s.getResenha());
        pst.setBytes(5, s.getImagem());
        pst.executeUpdate();
        retorno = true;
        
    } catch (SQLException e) {
        
    }
    return retorno;
    
    
}    
   
public boolean Editar(Serialize s) throws ClassNotFoundException{
    boolean retorno = false;
    Conexao conexao = new Conexao();
    try {
        
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano_edicao = ?, resenha = ?, imagem = ? WHERE  cod_livro ='" + s.getCod_livro() + "'";
        PreparedStatement pst = conexao.getPreparedStatement(sql);
        pst.setString(1, s.getTitulo());
        pst.setString(2, s.getAutor());
        pst.setInt(3, s.getAno());
        pst.setString(4, s.getResenha());
        pst.setBytes(5, s.getImagem());
        pst.executeUpdate();        
        
        retorno = true;
        
    } catch (SQLException e) {
        
    }
    return retorno;
}

public boolean Excluir(int cod) throws ClassNotFoundException{
    boolean retorno = false;
    Conexao conexao = new Conexao();
    try {  
        Statement stmt = conexao.getConnection().createStatement();
        String sql = "DELETE from livros WHERE cod_livro ='" + cod + "'";
        int r = stmt.executeUpdate(sql);
        if(r > 0){
         return true;   
        }
    } catch (SQLException e) {
        
    }
    return false;
}


    public List<Serialize> Read(int cod) throws ClassNotFoundException{
       
       List<Serialize> livros = new ArrayList<>();
       
       ResultSet rs            = null;
       Conexao conexao = new Conexao();
       
       try{
       
       Statement stmt = conexao.getConnection().createStatement();
       String where = "";
       if(cod > 0){
           where = " where cod_livro = " + cod;
       }
       String sql ="SELECT * from livros " + where;
       rs = stmt.executeQuery(sql);
       while(rs.next()){    
           Serialize serial = new Serialize();
           serial.setCod_livro(rs.getInt("cod_livro"));
           serial.setTitulo(rs.getString("titulo"));
           serial.setAutor(rs.getString("autor"));
           serial.setAno(rs.getInt("ano_edicao"));
           serial.setResenha(rs.getString("resenha"));
           serial.setDate(rs.getDate("data_insere"));
           serial.setImagem(rs.getBytes("imagem"));
           livros.add(serial);
       }
       }catch(SQLException ex){
           Logger.getLogger(LivrosDAO.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
           
       }
        return livros;
    }
    
    
}
