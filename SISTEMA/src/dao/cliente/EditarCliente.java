package dao.cliente;
import dao.cliente.tel.EditTel;
import principal.ClienteMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class EditarCliente extends JFrame{
    private JFrame frame;
    private JPanel contentPane;
    private JLabel title;
    private JTextField txtNome;
    private JTextField txtEstado;
    private JTextField txtEmail;
    private JTextField txtCep;
    private JTextField txtDatanasc;
    private JTextField txtRua;
    private JLabel nome_label;
    private JLabel email_label;
    private JLabel datanasc_label;
    private JLabel estado_label;
    private JLabel rua_label;
    private JLabel cpf_label;
    private JTextField txtNum;
    private JTextField txtPais;
    private JTextField txtCpf;
    private JLabel num_label;
    private JLabel cep_label;
    private JLabel pais_label;
    private JButton btnTel;
    private JButton btnEditar;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditarCliente frame = new EditarCliente();
                    frame.showCFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public EditarCliente() {
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nasc = txtDatanasc.getText();
                String[] lista = nasc.split("/");
                String dia = lista[0];
                String mes = lista[1];
                String ano = lista[2];
                String date = ano + "-" + mes + "-" + dia;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "UPDATE cliente SET nome = '" + txtNome.getText() +
                            "', email = '" + txtEmail.getText() +
                            "', datanasc = '" + date +
                            "', pais = '" + txtPais.getText() +
                            "', estado = '" + txtEstado.getText() +
                            "', cep = '" + txtCep.getText() +
                            "', rua = '" + txtRua.getText() +
                            "', num = " + txtNum.getText() +
                            " WHERE cpf = '" + txtCpf.getText() + "';";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnEditar, "Cliente não existe!");
                    } else {
                        JOptionPane.showMessageDialog(btnEditar, "Cliente editado com sucesso!");
                        closeCurrentFrame();
                        ClienteMain.main(null);
                    }
                    connection.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(btnEditar, "Erro: Verifique se os dados fornecidos estão corretos.");
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                ClienteMain.main(null);
            }
        });
        btnTel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTel.main(null);
            }
        });
    }
    public void showCFrame() {
        frame = new JFrame("Editar cliente");
        frame.setContentPane(contentPane);
        frame.setLocation(400, 200);
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
