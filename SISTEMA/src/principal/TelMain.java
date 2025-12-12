package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelMain extends javax.swing.JFrame {
    private JFrame mainFrame;
    private JButton btnCreate;
    private JButton btnRead;
    private JLabel title;
    private JPanel tPanel;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelMain telMain = new TelMain();
                    telMain.showMainTFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelMain() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.tel.CadTel.main(null);
            }
        });
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.tel.ExibirTel.main(null);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.tel.EditTel.main(null);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.tel.KillTel.main(null);
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

    private void closeCurrentFrame() {
        if (mainFrame != null) {
            mainFrame.dispose();
        }
    }

    private void showMainTFrame() {
        mainFrame = new JFrame("Menu de Telefones");
        mainFrame.setContentPane(tPanel);
        mainFrame.setLocation(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        mainFrame.setIconImage(icone);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}