package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteMain extends javax.swing.JFrame {
    private JFrame mainFrame;
    private JButton btnCreate;
    private JButton btnRead;
    private JLabel title;
    private JPanel cPanel;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnVoltar;
    private JButton btnTel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClienteMain clienteMain = new ClienteMain();
                    clienteMain.showMainCFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClienteMain() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.CadCliente.main(null);
            }
        });
        btnRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.ExibirCliente.main(null);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.EditarCliente.main(null);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                dao.cliente.DelCliente.main(null);
            }
        });
        btnTel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCurrentFrame();
                principal.TelMain.main(null);
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

    private void showMainCFrame() {
        mainFrame = new JFrame("Menu de Clientes");
        mainFrame.setContentPane(cPanel);
        mainFrame.setLocation(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Image icone = Toolkit.getDefaultToolkit().getImage("src/img/Dadafest.png");
        mainFrame.setIconImage(icone);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}