package dao.hospeda;

import principal.HospedaMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CadHospeda {
    private JFrame frame;
    private JPanel hPanel;
    private JLabel title;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JComboBox cBoxHotel;
    private JLabel lblHotel;
    private JTextField txtNum;
    private JLabel lblNum;
    private JLabel lblPag;
    private JTextField txtPag;
    private JLabel lblOut;
    private JLabel lblIni;
    private JTextField txtIni;
    private JTextField txtOut;
    private JLabel lblHora;
    private JTextField txtHora;
    private JButton btnCadastra;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CadHospeda cadHospeda = new CadHospeda();
                    cadHospeda.showHFrame();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public CadHospeda() {
        btnCadastra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chIn = txtIni.getText();
                String[] lista1 = chIn.split("/");
                String dia1 = lista1[0];
                String mes1 = lista1[1];
                String ano1 = lista1[2];
                String checkin = ano1 + "-" + mes1 + "-" + dia1;

                String chOut = txtOut.getText();
                String[] lista2 = chOut.split("/");
                String dia2 = lista2[0];
                String mes2 = lista2[1];
                String ano2 = lista2[2];
                String checkout = ano2 + "-" + mes2 + "-" + dia2;

                String time = txtHora.getText();
                String horario = time + ":00";

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortporto", "root", "");
                    String query = "INSERT INTO hospeda VALUES ('"+
                            checkin + "','" +
                            txtCpf.getText() + "','" +
                            cBoxHotel.getSelectedItem() + "'," +
                            txtNum.getText() + "," +
                            txtPag.getText() + ",'" +
                            checkout + "','" +
                            horario + "');";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnCadastra, "Erro desconhecido.");
                    } else {
                        JOptionPane.showMessageDialog(btnCadastra, "Hospedagem cadastrada com sucesso!");
                    }
                    closeCurrentFrame();
                    HospedaMain.main(null);
                    connection.close();
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
                HospedaMain.main(null);
            }
        });
    }
    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }
    private void showHFrame() {
        frame = new JFrame("Cadastrar Hospedagem");
        frame.setContentPane(hPanel);
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);
        frame.pack();
        frame.setVisible(true);
    }
}
