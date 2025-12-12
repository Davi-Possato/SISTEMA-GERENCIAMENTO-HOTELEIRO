package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospedaMain extends javax.swing.JFrame{
    private JFrame mainFrame;
    private JButton btnCreate;
    private JButton btnRead;
    private JLabel title;
    private JPanel hPanel;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HospedaMain hosMain = new HospedaMain();
                    hosMain.showMainHFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HospedaMain() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.hospeda.CadHospeda.main(null);
            }
        });
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.hospeda.ExibirHospeda.main(null);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.hospeda.EditarHospeda.main(null);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.hospeda.DeletarHospeda.main(null);
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                principal.Main.main(null);
            }
        });
    }
    private void closeCurrentFrame() {
        if (mainFrame != null) {
            mainFrame.dispose();
        }
    }
    private void showMainHFrame() {
        mainFrame = new JFrame("Menu de Hospedagens");
        mainFrame.setContentPane(hPanel);
        mainFrame.setLocation(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        mainFrame.setIconImage(icone);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}