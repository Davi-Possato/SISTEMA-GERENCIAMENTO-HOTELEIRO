package dao.cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExibirCliente {
    private JFrame frame;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExibirCliente cliente = new ExibirCliente();
                    cliente.showCFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    ;

    private static void ExibirDadoCliente(DefaultTableModel tableModel) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
            String query = "SELECT * FROM cliente ORDER BY nome;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String nasc = resultSet.getString("datanasc");
                String pais = resultSet.getString("pais");
                String estado = resultSet.getString("estado");
                String cep = resultSet.getString("cep");
                String rua = resultSet.getString("rua");
                int numero = resultSet.getInt("num");

                String[] lista = nasc.split("-");
                String ano = lista[0];
                String mes = lista[1];
                String dia = lista[2];
                String date = dia + "/" + mes + "/" + ano;
                tableModel.addRow(new Object[]{cpf, nome, email, date, pais, estado, cep, rua, numero});
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados.");
        }
    }

    public ExibirCliente() {
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(Color.darkGray);
        btnVoltar.setForeground(Color.white);
        btnVoltar.setSize(150, 100);
        Font font = new Font("Segoe UI", Font.PLAIN, 18);
        btnVoltar.setFont(font);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                principal.ClienteMain.main(null);
            }
        });
    }

    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }

    private void showCFrame() {
        frame = new JFrame("Lista de Clientes (Ordem alfabética)");
        frame.setLocation(120, 180);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("Data de Nascimento");
        tableModel.addColumn("País");
        tableModel.addColumn("Estado");
        tableModel.addColumn("CEP");
        tableModel.addColumn("Rua");
        tableModel.addColumn("Numero");

        table.setModel(tableModel);
        table.setBackground(Color.darkGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.darkGray);
        scrollPane.getViewport().setForeground(Color.white);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnVoltar, BorderLayout.SOUTH);
        frame.setSize(1200, 450);
        ExibirDadoCliente(tableModel);
        frame.setVisible(true);
    }


}