package dao.quarto;

import principal.QuartoMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletarQuarto {
    private JFrame quaFrame;
    private JLabel title;
    private JLabel hotelLabel;
    private JComboBox hotelCBox;
    private JLabel numeroLabel;
    private JTextField txtNumero;
    private JButton btnDeleta;
    private JPanel contentPane;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DeletarQuarto frame = new DeletarQuarto();
                    frame.showQFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public DeletarQuarto() {
        btnDeleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String queryA = "DELETE FROM hospeda WHERE hotel = '" + hotelCBox.getSelectedItem() +
                            "' AND numero = " + txtNumero.getText() + ";";
                    String query = "DELETE FROM quarto WHERE hotel = '" + hotelCBox.getSelectedItem() +
                            "' AND numero = " + txtNumero.getText() + ";";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(queryA);
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnDeleta, "Quarto não existe!");
                    } else {
                        JOptionPane.showMessageDialog(btnDeleta, "Quarto e suas hospedagens excluídos com sucesso!");
                        closeCurrentFrame();
                        QuartoMain.main(null);
                    }
                    connection.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnDeleta, "Erro: Verifique se os dados fornecidos estão corretos.");
                    e1.printStackTrace();
                    closeCurrentFrame();
                    QuartoMain.main(null);
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

    public void showQFrame() {
        quaFrame = new JFrame("Excluir Quarto");
        quaFrame.setContentPane(contentPane);
        quaFrame.setLocation(400, 200);
        quaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        quaFrame.setIconImage(icone);
        quaFrame.pack();
        quaFrame.setVisible(true);
    }
    public void closeCurrentFrame() {
        if (quaFrame != null) {
            quaFrame.dispose();
        }
    }
}
