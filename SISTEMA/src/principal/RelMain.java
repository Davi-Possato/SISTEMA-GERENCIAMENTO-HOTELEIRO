package principal;

import dao.relatorio.rel1.Rel1;
import dao.relatorio.rel2.Rel2;
import dao.relatorio.rel3.Rel3;
import dao.relatorio.rel4.Rel4;
import dao.relatorio.rel5.Rel5;
import dao.relatorio.rel6.Rel6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelMain extends JFrame {
    private JFrame mainFrame;
    private JButton btn4;
    private JButton btn1;
    private JLabel title;
    private JPanel cPanel;
    private JButton btn5;
    private JButton btn2;
    private JButton btn6;
    private JButton btn3;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RelMain relMain = new RelMain();
                    relMain.showMainRFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RelMain() {
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                Rel4.main(null);
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                Rel1.main(null);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                Rel2.main(null);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                Rel5.main(null);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                Rel3.main(null);
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                Rel6.main(null);
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

    private void showMainRFrame() {
        mainFrame = new JFrame("Relatórios");
        mainFrame.setContentPane(cPanel);
        mainFrame.setLocation(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        mainFrame.setIconImage(icone);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}