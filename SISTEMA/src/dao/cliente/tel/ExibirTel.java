package dao.cliente.tel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ExibirTel {
    private JFrame frame;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExibirTel tele = new ExibirTel();
                    tele.showTFrame();
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
            String query = "SELECT c.nome, t.cpf, t.telefone FROM telefones t JOIN cliente c ON c.cpf = t.cpf ORDER BY c.nome";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String nome = resultSet.getString("c.nome");
                String cpf = resultSet.getString("t.cpf");
                String tele = resultSet.getString("t.telefone");


                tableModel.addRow(new Object[]{nome, cpf, tele});
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados.");
        }
    }

    public ExibirTel() {
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
                principal.TelMain.main(null);
            }
        });
    }

    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }

    private void showTFrame() {
        frame = new JFrame("Lista de Telefones");
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome do dono");
        tableModel.addColumn("CPF do dono");
        tableModel.addColumn("Telefone");

        table.setModel(tableModel);
        table.setBackground(Color.darkGray);
        table.setForeground(Color.white);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.darkGray);
        scrollPane.getViewport().setForeground(Color.white);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnVoltar, BorderLayout.SOUTH);
        frame.setSize(600, 450);
        ExibirDados(tableModel);
        frame.setVisible(true);
    }


}
