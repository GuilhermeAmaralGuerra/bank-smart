package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.Component;
import java.time.LocalDate;


public class DepositoModel {
    private int id;
    private int idDeposito;
    private float valor; 
    private LocalDate dataDeposito;
    
    public DepositoModel(int id, int idDeposito, float valor, LocalDate dataDeposito) {
        this.id = id;
        this.idDeposito = idDeposito;
        this.valor = valor;
        this.dataDeposito = dataDeposito;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdDeposito() {
        return idDeposito;
    }
    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public LocalDate getDataDeposito() {
        return dataDeposito;
    }
    public void setDataDeposito(LocalDate dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public static void imprimirTabelaDeposito(DefaultTableModel modelo, int idUsuario, JFrame mostrarTransferencia, Component parentComponent){
        try (Connection conn = ConnectionFactory.getConexao()){ 

            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deposito WHERE conta_id = ?");
            stmt.setInt(1, idConta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor_deposito");
                LocalDate dataDeposito = rs.getDate("data_deposito").toLocalDate();
                modelo.addRow(new Object[]{id, valor, dataDeposito});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
