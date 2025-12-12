package dao.relatorio.rel6;

import principal.RelMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExibirRel6 {
    private JFrame frame;
    private JButton btnVoltar;

    public static void main(String nom) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExibirRel6 quarto = new ExibirRel6();
                    quarto.showRFrame(nom);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void loadData(DefaultTableModel tableModel, String nom
    ) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
            String query = "select hotel, avg(preco) from quarto where hotel = '" + nom + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String nome = resultSet.getString("hotel");
                float preco = resultSet.getFloat("avg(preco)");
                tableModel.addRow(new Object[]{nome, preco});
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    public ExibirRel6() {
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

    private void showRFrame(String nom) {
        frame = new JFrame("Consulta");
        frame.setLocation(400,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Hotel");
        tableModel.addColumn("Média");
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
        loadData(tableModel, nom);
        frame.setVisible(true);
    }
}