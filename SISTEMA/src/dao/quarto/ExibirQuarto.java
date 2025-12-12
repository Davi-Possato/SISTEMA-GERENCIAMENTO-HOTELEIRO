package dao.quarto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;

public class ExibirQuarto {
    private JFrame frame;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExibirQuarto quarto = new ExibirQuarto();
                    quarto.showQFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void loadQuartosData(DefaultTableModel tableModel) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
            String query = "SELECT * FROM quarto";
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
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados.");
        }
    }

    public ExibirQuarto() {
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
                principal.QuartoMain.main(null);
            }
        });
    }

    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }

    private void showQFrame() {
        frame = new JFrame("Lista de Quartos");
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
        loadQuartosData(tableModel);
        frame.setVisible(true);
    }
}