package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Component;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class ContaBancaria {
    private int id;
    private int usuarioID;
    private String tipoConta;
    private int numeroConta;
    private int saldo;
    private LocalDate dataAbertura;
    private long codigoTransferencia;
    
    public ContaBancaria(int id, int usuarioID, String tipoConta, int numeroConta, int saldo, LocalDate dataAbertura, long codigo_transferencia) {
        this.id = id;
        this.usuarioID = usuarioID;
        this.tipoConta = tipoConta;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.dataAbertura = dataAbertura;
        this.codigoTransferencia = codigo_transferencia;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsuarioID() {
        return usuarioID;
    }
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
    public String getTipoConta() {
        return tipoConta;
    }
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
    public int getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }
    public int getSaldo() {
        return saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public LocalDate getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public long getCodigoTransferencia() {
        return codigoTransferencia;
    }
    public void setCodigoTransferencia(long codigoTransferencia) {
        this.codigoTransferencia = codigoTransferencia;
    }

    public static float pegarSaldo(int idUsuario, Component parentComponent){
        float saldo = -1;

        try(Connection conn = ConnectionFactory.getConexao()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT saldo FROM conta_bancaria WHERE usuario_id = ?");

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    saldo = rs.getFloat("saldo");
                }
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return saldo;
    }
    
    public static long pegarCodigo(int idUsuario, Component parentComponent){
        long codigoTransferencia = -1;

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT codigo_transferencia FROM conta_bancaria WHERE usuario_id = ?");

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    codigoTransferencia = rs.getLong("codigo_transferencia");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return codigoTransferencia;
    }

    public static int armazenarConta(Component parentComponent, int idUsuario){
        int idConta = -1;

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM conta_bancaria WHERE usuario_id = ?");

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    idConta = rs.getInt("id");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Erro ao armazenar usuário", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return idConta;
    }

    public static LocalDate pegarDataAbertura(Component parentComponent, int idUsuario){
        LocalDate data = null;

        try (Connection conn = ConnectionFactory.getConexao()){
            PreparedStatement stmt = conn.prepareStatement("SELECT data_abertura FROM conta_bancaria WHERE usuario_id = ?");

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    data = rs.getDate("data_abertura").toLocalDate();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Erro ao pegar a data", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
