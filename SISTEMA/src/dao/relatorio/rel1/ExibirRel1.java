package dao.relatorio.rel1;

import principal.RelMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExibirRel1 {
    private JFrame frame;
    private JButton btnVoltar;

    public static void main(String num) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExibirRel1 quarto = new ExibirRel1();
                    quarto.showRFrame(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void loadQuartosData(DefaultTableModel tableModel, String num) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
            String query = "SELECT * FROM quarto WHERE preco < " + num + "; ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String hotel = resultSet.getString("hotel");
                String numero = resultSet.getString("numero");
                double preco = resultSet.getDouble("preco");
                int lotacao = resultSet.getInt("lotacao");

                tableModel.addRow(new Object[]{hotel, numero, preco, lotacao});
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    public ExibirRel1() {
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
                RelMain.main(null);
            }
        });
    }

    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }

    private void showRFrame(String num) {
        frame = new JFrame("Consulta");
        frame.setLocation(400,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Hotel");
        tableModel.addColumn("Número");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Lotação");

        table.setModel(tableModel);
        table.setBackground(Color.darkGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.darkGray);
        scrollPane.getViewport().setForeground(Color.white);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnVoltar, BorderLayout.SOUTH);
        frame.setSize(700, 450);
        loadQuartosData(tableModel, num);
        frame.setVisible(true);
    }
}