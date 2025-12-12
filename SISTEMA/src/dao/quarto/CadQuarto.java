package dao.quarto;

import principal.QuartoMain;

import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadQuarto extends JFrame {
    private JFrame quaFrame;
    private JPanel contentPane;
    private JLabel title;
    private JComboBox hotelCBox;
    private JTextField txtNumero;
    private JTextField txtPreco;
    private JTextField txtLotacao;
    private JButton btnCadastra;
    private JButton btnVoltar;
    private JLabel numeroLabel;
    private JLabel precoLabel;
    private JLabel lotacaoLabel;
    private JLabel hotelLabel;


    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadQuarto quarto = new CadQuarto();
                    quarto.showQFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CadQuarto() {
        btnCadastra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "INSERT INTO quarto VALUES('" +
                            hotelCBox.getSelectedItem() + "', " +
                            txtNumero.getText() + ", " +
                            txtPreco.getText() + ", " +
                            txtLotacao.getText() + ");";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnCadastra, "Erro desconhecido.");
                    } else {
                        JOptionPane.showMessageDialog(btnCadastra, "Quarto cadastrado com sucesso!");
                    }
                    connection.close();
                    closeCurrentFrame();
                    QuartoMain.main(null);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnCadastra, "Erro: Verifique se os dados fornecidos estão corretos.");
                    closeCurrentFrame();
                    e1.printStackTrace();
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
    private void closeCurrentFrame() {
        if (quaFrame != null) {
            quaFrame.dispose();
        }
    }
    private void showQFrame() {
        quaFrame = new JFrame("Cadastrar quarto");
        quaFrame.setContentPane(contentPane);
        quaFrame.setLocation(400, 200);
        quaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        quaFrame.setIconImage(icone);
        quaFrame.pack();
        quaFrame.setVisible(true);
    }

}
