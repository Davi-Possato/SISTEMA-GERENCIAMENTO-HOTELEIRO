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

public class EditarHospeda {
    public JFrame hFrame;
    private JPanel hPanel;
    private JLabel title;
    private JLabel lblCpf;
    private JTextField txtCpf;
    private JLabel lblHotel;
    private JComboBox cBoxHotel;
    private JLabel lblNum;
    private JTextField txtNum;
    private JTextField txtOut;
    private JTextField txtHora;
    private JButton btnEdita;
    private JButton btnVoltar;
    private JTextField txtPag;
    private JLabel lblIni;
    private JTextField txtIni;
    private JLabel lblHora;
    private JLabel lblOut;
    private JLabel lblPag;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    EditarHospeda eHospeda = new EditarHospeda();
                    eHospeda.showHFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public EditarHospeda() {
        btnEdita.addActionListener(new ActionListener() {
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
                    String query = "UPDATE hospeda SET pagamento = " + txtPag.getText() +
                            ", dataout = '" + checkout +
                            "', horaout = '" + horario +
                            "' WHERE dataini = '" + checkin +
                            "' AND cpf = '" + txtCpf.getText() +
                            "' AND hotel = '" + cBoxHotel.getSelectedItem() +
                            "' AND numero = " + txtNum.getText() + ";";
                    Statement statement = connection.createStatement();
                    int resp = statement.executeUpdate(query);
                    if (resp == 0) {
                        JOptionPane.showMessageDialog(btnEdita, "Essa hospedagem não existe!");
                    } else {
                        JOptionPane.showMessageDialog(btnEdita, "Hospedagem editada com sucesso!");
                        closeCurrentFrame();
                        HospedaMain.main(null);
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
                HospedaMain.main(null);
            }
        });
    }
    public void showHFrame() {
        hFrame = new JFrame("Editar Hospedagem");
        hFrame.setContentPane(hPanel);
        hFrame.setLocation(400, 200);
        hFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        hFrame.setIconImage(icone);
        hFrame.pack();
        hFrame.setVisible(true);
    }
    public void closeCurrentFrame() {
        if (hFrame != null) {
            hFrame.dispose();
        }
    }
}
