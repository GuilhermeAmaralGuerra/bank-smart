package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.Timestamp;


public class Emprestimo {

    private int id;
    private int idUsuario;
    private float valor;
    private float valorJuros;
    private LocalDate dataEmprestimo;
    private LocalDate dataPagamento;
    private String status;

    public Emprestimo(int id, int idUsuario, float valor, float valorJuros, LocalDate dataEmprestimo,
            LocalDate dataPagamento, String status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.valor = valor;
        this.valorJuros = valorJuros;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPagamento = dataPagamento;
        this.status = status;
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
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public float getValorJuros() {
        return valorJuros;
    }
    public void setValorJuros(float valorJuros) {
        this.valorJuros = valorJuros;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int pegarIdEmprestimo(int idUsuario, Component parentComponent){

        int idEmprestimo = -1;

        try (Connection conn = ConnectionFactory.getConexao()){
            
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM emprestimo WHERE usuario_id = ? AND status = 'pendente'");

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    idEmprestimo = rs.getInt("id");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Erro ao pegar id do empréstimo", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return idEmprestimo;
    }

    public static float pegarTotalEmprestimosJuros(int idUsuario, Component parentComponent){
        float valorTotalJuros = 0;
        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT valor_juros, data_emprestimo FROM emprestimo WHERE usuario_id = ? AND status = 'pendente';");

            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();
        
            while(rs.next()){
                float valorJuros = rs.getFloat("valor_juros");
                Timestamp dataEmprestimoBD = rs.getTimestamp("data_emprestimo"); 
            
            
                Instant instant = dataEmprestimoBD.toInstant();
                ZoneId idZona = ZoneId.of("America/Sao_Paulo"); 
                LocalDate dataEmprestimo = instant.atZone(idZona).toLocalDate();
                LocalDate dataPagamento = LocalDate.now(); 

                long mesDiferenca = ChronoUnit.MONTHS.between(dataEmprestimo, dataPagamento);

                float jurosAcumulados = valorJuros * (float) Math.pow(1.03, mesDiferenca); 
                valorTotalJuros += jurosAcumulados;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return valorTotalJuros;
    }

    public static void imprimirTabelaEmprestimo(DefaultTableModel modelo, int idUsuario, JFrame mostrarTransferencia, Component parentComponent){
        try (Connection conn = ConnectionFactory.getConexao()){ 

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM emprestimo WHERE usuario_id = ?");
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataPagamento = rs.getDate("data_pagamento").toLocalDate();
                String status = rs.getString("status");
                double valorPagamento = rs.getDouble("pagamento");
                modelo.addRow(new Object[]{id, valor, dataEmprestimo, dataPagamento, status, valorPagamento});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
