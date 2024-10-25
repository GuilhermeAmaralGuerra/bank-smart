package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import javax.swing.JOptionPane;

public class Endereco {
    private int id;
    private int idUsuario;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    
    public Endereco(int id, int idUsuario, String rua, String numero, String complemento, String cidade, String estado,
            String cep) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public static Endereco pegarTodasInfo(int idUsuario, Component parentComponent){
        Endereco endereco = null;

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM endereco WHERE usuario_id = ?");

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            int id = rs.getInt("id");
            String rua = rs.getString("rua");
            String numero = rs.getString("numero");
            String complemento = rs.getString("complemento");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String cep = rs.getString("cep");

            endereco = new Endereco(id, idUsuario, rua, numero, complemento, cidade, estado, cep);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return endereco;
    }
}
