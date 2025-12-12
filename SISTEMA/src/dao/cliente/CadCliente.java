package dao.cliente;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CadCliente extends JFrame {
    private JFrame cFrame;
    private JPanel clientePane;
    private JTextField cpf_txt;
    private JTextField email_txt;
    private JLabel cpf_label;
    private JLabel email_label;
    private JTextField datanasc_txt;
    private JTextField pais_txt;
    private JLabel datanasc_label;
    private JLabel pais_label;
    private JTextField estado_txt;
    private JTextField rua_txt;
    private JLabel estado_label;
    private JLabel Rua_label;
    private JTextField cep_txt;
    private JTextField num_txt;
    private JLabel cep_label;
    private JLabel num_label;
    private JButton btncadastra;
    private JLabel nome_label;
    private JTextField nome_txt;
    private JLabel title;
    private JButton btnVoltar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadCliente frame = new CadCliente();
                    frame.showCFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CadCliente() {

        btncadastra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nasc = datanasc_txt.getText();
                String[] lista = nasc.split("/");
                String dia = lista[0];
                String mes = lista[1];
                String ano = lista[2];
                String date = ano + "-" + mes + "-" + dia;

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "INSERT INTO cliente VALUES('" +
                            cpf_txt.getText() + "', '" +
                            nome_txt.getText() + "', '" +
                            email_txt.getText() + "', '" +
                            date + "', '" +
                            pais_txt.getText() + "', '" +
                            estado_txt.getText() + "', '" +
                            cep_txt.getText() + "', '" +
                            rua_txt.getText() + "', " +
                            num_txt.getText() + ");";

                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btncadastra, "Erro desconhecido.");
                    } else {
                        JOptionPane.showMessageDialog(btncadastra, "Cliente cadastrado com sucesso!");
                    }
                    connection.close();
                    closeCurrentFrame();
                    principal.ClienteMain.main(null);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(btncadastra, "Erro: Verifique se os dados fornecidos estão corretos.");
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
    }
    public void closeCurrentFrame() {
        if (cFrame != null) {
            cFrame.dispose();
        }
    }
    public void showCFrame() {
        cFrame = new JFrame("Cadastrar cliente");
        cFrame.setContentPane(clientePane);
        cFrame.setLocation(400, 200);
        cFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        cFrame.setIconImage(icone);
        cFrame.pack();
        cFrame.setVisible(true);
    }
}


