package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.Component;
import java.time.LocalDate;

public class Transacao {
    private int id;
    private int id_conta;
    private String tipo_transacao;
    private double valor;
    private LocalDate data_transacao;
    private String descricao;
    
    public Transacao(int id, int id_conta, String tipo_transacao, double valor, LocalDate data_transacao,
            String descricao) {
                
        this.id = id;
        this.id_conta = id_conta;
        this.tipo_transacao = tipo_transacao;
        this.valor = valor;
        this.data_transacao = data_transacao;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_conta() {
        return id_conta;
    }
    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }
    public String getTipo_transacao() {
        return tipo_transacao;
    }
    public void setTipo_transacao(String tipo_transacao) {
        this.tipo_transacao = tipo_transacao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public LocalDate getData_transacao() {
        return data_transacao;
    }
    public void setData_transacao(LocalDate data_transacao) {
        this.data_transacao = data_transacao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static void imprimirTabelaTransferencia(DefaultTableModel modelo, int idUsuario, JFrame mostrarTransferencia, Component parentComponent){
        try (Connection conn = ConnectionFactory.getConexao()){ 

            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transacao WHERE conta_id = ?");
            stmt.setInt(1, idConta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipoTransacao = rs.getString("tipo_transacao");
                double valor = rs.getDouble("valor");
                LocalDate dataTransacao = rs.getDate("data_transacao").toLocalDate();
                String descricao = rs.getString("descricao");
                long destinatario = rs.getLong("destinatario");

                modelo.addRow(new Object[]{id, tipoTransacao, valor, dataTransacao, descricao, destinatario});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
