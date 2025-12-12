package dao.relatorio.rel4;

import principal.RelMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rel4 extends JFrame {
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
                    Rel4 quarto = new Rel4();
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
    public Rel4() {
        btnCadastra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                ExibirRel4.main(txtNome.getText());
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
        frame = new JFrame("Relatório 4");
        frame.setContentPane(contentPane);
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        frame.setIconImage(icone);
        frame.pack();
        frame.setVisible(true);
    }

}
