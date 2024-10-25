package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.awt.Component;
import java.time.LocalDate;

public class SaqueModel {
    private int id;
    private int idConta;
    private float valor;
    private LocalDate dataSaque;
    
    public SaqueModel(int id, int idConta, float valor, LocalDate dataSaque) {
        this.id = id;
        this.idConta = idConta;
        this.valor = valor;
        this.dataSaque = dataSaque;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdConta() {
        return idConta;
    }
    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public LocalDate getDataSaque() {
        return dataSaque;
    }
    public void setDataSaque(LocalDate dataSaque) {
        this.dataSaque = dataSaque;
    }

    public static void imprimirTabelaSaque(DefaultTableModel modelo, int idUsuario, JFrame mostrarTransferencia, Component parentComponent){
        try (Connection conn = ConnectionFactory.getConexao()){ 

            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM saque WHERE conta_id = ?");
            stmt.setInt(1, idConta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor_saque");
                LocalDate dataSaque = rs.getDate("data_saque").toLocalDate();
                modelo.addRow(new Object[]{id, valor, dataSaque});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
