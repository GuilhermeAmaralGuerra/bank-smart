package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.Component;
import Model.*;
import View.PrincipalView;

public class PagarEmprestimo {
    public static void pagarParte(int idUsuario, Component parentComponent, JFrame telaPagar, float valorJuros, String pagarParte, JFrame telaPrincipal){
        try (Connection conn = ConnectionFactory.getConexao()){

            float valorPagar = Float.parseFloat(pagarParte);
            float saldo = ContaBancaria.pegarSaldo(idUsuario, parentComponent);
            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);
            int idEmprestimo = Emprestimo.pegarIdEmprestimo(idUsuario, parentComponent);
            LocalDate dataPagamento = LocalDate.now();
            Date dataSQL = Date.valueOf(dataPagamento);

            if (valorPagar == valorJuros) {
                PagarEmprestimo.pagarTudo(idUsuario, parentComponent, telaPagar, valorJuros, telaPrincipal);
            } else if (valorJuros > saldo) {
                JOptionPane.showMessageDialog(parentComponent, "Valor de saldo insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (valorPagar > valorJuros) {
                JOptionPane.showMessageDialog(parentComponent, "Acima do valor", "Erro", JOptionPane.ERROR_MESSAGE);
            }else{
                conn.setAutoCommit(false);

                PreparedStatement stmtIncompleto = conn.prepareStatement("INSERT INTO emprestimo_pagamento_incompleto (conta_id, emprestimo_id, valor_abatido, data_pagamento) VALUES (?, ?, ?, ?)");
                PreparedStatement stmtEmprestimo = conn.prepareStatement("UPDATE emprestimo SET valor_juros = valor_juros - ?, pagamento = pagamento + ? WHERE usuario_id = ? AND status = 'pendente'");
                PreparedStatement stmtContaBancaria = conn.prepareStatement("UPDATE conta_bancaria SET saldo = saldo - ? WHERE id = ?");

                stmtIncompleto.setInt(1, idConta);
                stmtIncompleto.setInt(2, idEmprestimo);
                stmtIncompleto.setFloat(3, valorPagar);
                stmtIncompleto.setDate(4, dataSQL);

                int linhasIncompleto = stmtIncompleto.executeUpdate();

                if (linhasIncompleto > 0) {
                    stmtEmprestimo.setFloat(1, valorPagar); 
                    stmtEmprestimo.setFloat(2, valorPagar);
                    stmtEmprestimo.setInt(3, idUsuario);
                    int linhasAbater = stmtEmprestimo.executeUpdate();

                    stmtContaBancaria.setFloat(1, valorPagar);
                    stmtContaBancaria.setInt(2, idConta);
                    int linhasSaldo = stmtContaBancaria.executeUpdate();

                    if (linhasAbater > 0 && linhasSaldo > 0) {
                        conn.commit();
                        JOptionPane.showMessageDialog(parentComponent, "Pagamento realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        telaPagar.setVisible(false);
                        String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                        telaPrincipal.setVisible(false);
                        PrincipalView.telaBanco(idUsuario, nome);
                    }else{
                        conn.rollback();
                        throw new Exception("Erro ao subtrair saldo da conta remetente.");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void pagarTudo(int idUsuario, Component parentComponent, JFrame telaPagar, float valorJuros, JFrame telaPrincipal){
        try (Connection conn = ConnectionFactory.getConexao()){

            float saldo = ContaBancaria.pegarSaldo(idUsuario, parentComponent);
            int idConta = ContaBancaria.armazenarConta(parentComponent, idUsuario);
            LocalDate dataPagamento = LocalDate.now();
            Date dataSQL = Date.valueOf(dataPagamento);

            if (valorJuros > saldo) {
                JOptionPane.showMessageDialog(parentComponent, "Valor de saldo insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                conn.setAutoCommit(false);

                PreparedStatement stmtEmprestimo = conn.prepareStatement("UPDATE emprestimo SET valor_juros = valor_juros - ?, data_pagamento = ?, status = 'pago', pagamento = pagamento + ? WHERE usuario_id = ? AND status = 'pendente'");
                PreparedStatement stmtSaldo = conn.prepareStatement("UPDATE conta_bancaria SET saldo = saldo - ? WHERE id = ?");

                stmtEmprestimo.setFloat(1, valorJuros);
                stmtEmprestimo.setDate(2, dataSQL);
                stmtEmprestimo.setFloat(3, valorJuros);
                stmtEmprestimo.setInt(4, idUsuario);
                int linhasDeposito = stmtEmprestimo.executeUpdate();

                if (linhasDeposito > 0) {
                    stmtSaldo.setFloat(1, valorJuros);
                    stmtSaldo.setInt(2, idConta);
                    int linhasSaldo = stmtSaldo.executeUpdate();

                    if (linhasSaldo > 0) {
                        conn.commit();
                        JOptionPane.showMessageDialog(parentComponent, "Pagamento realizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        telaPagar.setVisible(false);
                        String nome = UsuarioModel.pegarNome(parentComponent, idUsuario);
                        telaPrincipal.setVisible(false);
                        PrincipalView.telaBanco(idUsuario, nome);
                    } else{
                        conn.rollback();
                        throw new Exception("Erro ao subtrair saldo da conta remetente.");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
