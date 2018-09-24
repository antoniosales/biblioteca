
package biblioteca.modelo;

import java.util.Date;

public class Serialize {

    private int cod_livro; 
    private String titulo; 
    private String autor;
    private String resenha;
    private Date data_insere;
    private int ano;
    private byte[] imagem;

    public int getCod_livro() {
        return cod_livro;
    }

    public void setCod_livro(int cod_livro) {
        this.cod_livro = cod_livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Date getDate() {
        return data_insere;
    }
    
    public void setDate(Date data_insere){
        this.data_insere = data_insere;
    }
     
    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }    
    
}
