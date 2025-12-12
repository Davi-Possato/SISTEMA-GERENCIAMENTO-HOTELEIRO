package dao.quarto;

import principal.QuartoMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditarQuarto {
    private JFrame quaFrame;
    private JPanel contentPane;
    private JTextField txtNumero;
    private JLabel numeroLabel;
    private JLabel precoLabel;
    private JTextField txtLotacao;
    private JLabel lotacaoLabel;
    private JTextField txtPreco;
    private JLabel hotelLabel;
    private JComboBox hotelCBox;
    private JButton btnEdita;
    private JLabel title;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    EditarQuarto frame = new EditarQuarto();
                    frame.showQFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public EditarQuarto() {
        btnEdita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "UPDATE quarto SET preco = " + txtPreco.getText() +
                            ", lotacao = " + txtLotacao.getText() +
                            " WHERE hotel = '" + hotelCBox.getSelectedItem() +
                            "' AND numero = " + txtNumero.getText() + ";";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnEdita, "Quarto não existe!");
                    } else {
                        JOptionPane.showMessageDialog(btnEdita, "Quarto editado com sucesso!");
                        closeCurrentFrame();
                        QuartoMain.main(null);
                    }
                    connection.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnEdita, "Erro: Verifique se os dados fornecidos estão corretos.");
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                principal.QuartoMain.main(null);
            }
        });
    }
    private void closeCurrentFrame() {
        if (quaFrame != null) {
            quaFrame.dispose();
        }
    }
    private void showQFrame() {
        quaFrame = new JFrame("Editar Quarto");
        quaFrame.setContentPane(contentPane);
        quaFrame.setLocation(400, 200);
        quaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        quaFrame.setIconImage(icone);
        quaFrame.pack();
        quaFrame.setVisible(true);
    }
}
