package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuartoMain extends javax.swing.JFrame{
    private JFrame mainFrame;
    private JButton btnCreate;
    private JButton btnRead;
    private JLabel title;
    private JPanel qPanel;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuartoMain quartoMain = new QuartoMain();
                    quartoMain.showMainQFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public QuartoMain() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.quarto.CadQuarto.main(null);
            }
        });
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.quarto.ExibirQuarto.main(null);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.quarto.EditarQuarto.main(null);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.quarto.DeletarQuarto.main(null);
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
    private void showMainQFrame() {
        mainFrame = new JFrame("Menu de Quartos");
        mainFrame.setContentPane(qPanel);
        mainFrame.setLocation(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        mainFrame.setIconImage(icone);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}