package dao.cliente.tel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class KillTel extends JFrame {
    private JFrame frame;
    private JPanel contentPane;
    private JLabel cpfLabel;
    private JTextField txtTel;
    private JLabel telLabel;
    private JTextField txtCpf;
    private JButton btnKill;
    private JButton btnVoltar;
    private JLabel title;
    private JTextField txtNTel;

    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KillTel telef = new KillTel();
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
    public KillTel() {

        btnKill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "DELETE FROM telefones WHERE cpf = '" +
                            txtCpf.getText() + "' AND telefone = '" +
                            txtTel.getText() + "';";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnKill, "Cliente ou telefone não existe!");
                    } else {
                        JOptionPane.showMessageDialog(btnKill, "Telefone excluído com sucesso!");
                        closeCurrentFrame();
                        principal.TelMain.main(null);
                    }
                    connection.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnKill, "Erro: Verifique se os dados fornecidos estão corretos.");
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