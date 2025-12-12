package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame currentFrame;
    private JPanel priPanel;
    private JButton btnHospeda;
    private JButton btnQuarto;
    private JButton btnCliente;
    private JLabel portoBRLabel;
    private JButton btnRelatorio;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main main = new Main();
                    main.showMainFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        btnQuarto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                QuartoMain.main(null);
            }
        });

        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                ClienteMain.main(null);
            }
        });
        btnHospeda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                HospedaMain.main(null);
            }
        });
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                RelMain.main(null);
            }
        });
    }

    private void closeCurrentFrame() {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }

    private void showMainFrame() {
        currentFrame = new JFrame("Menu Principal");
        currentFrame.setContentPane(priPanel);
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        currentFrame.setIconImage(icone);
        currentFrame.setBounds(0, 0, 1500, 800);
        currentFrame.setVisible(true);

    }
}