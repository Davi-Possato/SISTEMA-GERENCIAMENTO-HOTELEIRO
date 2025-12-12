package dao.hospeda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExibirHospeda {
    private JFrame frame;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExibirHospeda hospeda = new ExibirHospeda();
                    hospeda.showHFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    ;

    private static void ExibirDados(DefaultTableModel tableModel) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
            String query = "SELECT * FROM hospeda";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String dataini = resultSet.getString("dataini");
                String cpf = resultSet.getString("cpf");
                String hotel = resultSet.getString("hotel");
                int numero = resultSet.getInt("numero");
                String pagamento = resultSet.getString("pagamento");
                String dataout = resultSet.getString("dataout");
                String horaout = resultSet.getString("horaout");

                String[] lista1 = dataini.split("-");
                String dia1 = lista1[2];
                String mes1 = lista1[1];
                String ano1 = lista1[0];
                String checkin = dia1 + "/" + mes1 + "/" + ano1;

                String[] lista2 = dataout.split("-");
                String dia2 = lista2[2];
                String mes2 = lista2[1];
                String ano2 = lista2[0];
                String checkout = dia2 + "/" + mes2 + "/" + ano2;

                String[] lista3 = horaout.split(":");
                String hor = lista3[0];
                String min = lista3[1];
                String horario = hor + ":" + min;

                tableModel.addRow(new Object[]{cpf, hotel, numero, pagamento, checkin, checkout, horario});
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados.");
        }
    }

    public ExibirHospeda() {
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(Color.darkGray);
        btnVoltar.setForeground(Color.white);
        btnVoltar.setSize(150, 100);
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        btnVoltar.setFont(font);
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                principal.HospedaMain.main(null);
            }
        });
    }

    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }

    private void showHFrame() {
        frame = new JFrame("Lista de Hospedagens");
        frame.setLocation(360, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF vinculado");
        tableModel.addColumn("Hotel");
        tableModel.addColumn("Número do Quarto");
        tableModel.addColumn("Preço Total");
        tableModel.addColumn("Data do Check-in");
        tableModel.addColumn("Data do Checkout");
        tableModel.addColumn("Horário limite");

        table.setModel(tableModel);
        table.setBackground(Color.darkGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        Font font = new Font("Arial", Font.PLAIN, 18);
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.darkGray);
        scrollPane.getViewport().setForeground(Color.white);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnVoltar, BorderLayout.SOUTH);
        frame.setSize(800, 450);
        ExibirDados(tableModel);
        frame.setVisible(true);
    }


}

