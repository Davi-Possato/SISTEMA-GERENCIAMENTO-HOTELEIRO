package dao.cliente;

import dao.cliente.tel.KillTel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class DelCliente extends JFrame {
    private JFrame frame;
    private JLabel title;
    private JLabel cpfLabel;
    private JTextField txtCpf;
    private JButton btnDel;
    private JPanel contentPane;
    private JButton btnVoltar;
    private JButton btnTel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DelCliente frame = new DelCliente();
                    frame.showCFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public DelCliente(){
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String queryA = "DELETE FROM hospeda WHERE cpf = '" + txtCpf.getText() + "';";
                    String queryB = "DELETE FROM telefones WHERE cpf = '" + txtCpf.getText() + "';";
                    String query = "DELETE FROM cliente WHERE cpf = '" + txtCpf.getText() + "';";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(queryA);
                    statement.executeUpdate(queryB);
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnDel, "Cliente não existe!");
                    } else {
                        JOptionPane.showMessageDialog(btnDel, "Cliente, seus telefones e suas hospedagens excluídos com sucesso!");
                        closeCurrentFrame();
                        principal.ClienteMain.main(null);
                    }
                    connection.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnDel, "Erro: Verifique se os dados fornecidos estão corretos.");
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                principal.ClienteMain.main(null);
            }
        });
        btnTel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KillTel.main(null);
            }
        });
    }
    public void showCFrame() {
        frame = new JFrame("Excluir cliente");
        frame.setContentPane(contentPane);
        frame.setLocation(400,200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);
        frame.pack();
        frame.setVisible(true);
    }
    public void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }
}


