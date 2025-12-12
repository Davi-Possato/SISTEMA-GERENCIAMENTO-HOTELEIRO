package dao.relatorio.rel2;

import dao.relatorio.rel1.ExibirRel1;
import principal.RelMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rel2 extends JFrame {
    private JFrame frame;
    private JPanel contentPane;
    private JLabel title;
    private JTextField txtNome;
    private JButton btnCadastra;
    private JButton btnVoltar;


    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Rel2 quarto = new Rel2();
                    quarto.showRFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Rel2() {
        btnCadastra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                ExibirRel2.main(txtNome.getText());
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                RelMain.main(null);
            }
        });
    }
    private void closeCurrentFrame() {
        if (frame != null) {
            frame.dispose();
        }
    }
    private void showRFrame() {
        frame = new JFrame("Relatório 2");
        frame.setContentPane(contentPane);
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);
        frame.pack();
        frame.setVisible(true);
    }

}
