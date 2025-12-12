package dao.cliente.tel;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadTel extends JFrame {
    private JFrame frame;
    private JPanel contentPane;
    private JLabel cpfLabel;
    private JTextField txtTel;
    private JLabel telLabel;
    private JTextField txtCpf;
    private JButton btnCadastra;
    private JButton btnVoltar;
    private JLabel title;

    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadTel telef = new CadTel();
                    telef.showTFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CadTel() {

        btnCadastra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "INSERT INTO telefones VALUES('" +
                            txtCpf.getText() + "', '" +
                            txtTel.getText() + "');";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnCadastra, "Erro desconhecido.");
                    } else {
                        JOptionPane.showMessageDialog(btnCadastra, "Telefone cadastrado com sucesso!");
                    }
                    connection.close();
                    closeCurrentFrame();
                    principal.TelMain.main(null);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnCadastra, "Erro: Verifique se os dados fornecidos estão corretos.");
                    e1.printStackTrace();
                }

            }
        });
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
        frame = new JFrame("Cadastrar telefone");
        frame.setContentPane(contentPane);
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);
        frame.pack();
        frame.setVisible(true);
    }

}