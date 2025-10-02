package clinicaapp;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import clinicaapp.Conexao;

public class TelaLogin extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public TelaLogin() {
        setTitle("Login - ClínicaApp");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl1 = new JLabel("Usuário:");
        lbl1.setBounds(30,30,80,25);
        add(lbl1);

        txtUser = new JTextField();
        txtUser.setBounds(100,30,150,25);
        add(txtUser);

        JLabel lbl2 = new JLabel("Senha:");
        lbl2.setBounds(30,70,80,25);
        add(lbl2);

        txtPass = new JPasswordField();
        txtPass.setBounds(100,70,150,25);
        add(txtPass);

        btnLogin = new JButton("Entrar");
        btnLogin.setBounds(100,110,100,30);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logar();
            }
        });
    }

    private void logar() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        try (Connection con = Conexao.getConnection()) {
            // Corrigido para usar a coluna "senha"
            PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM usuarios WHERE username=? AND senha=?"
            );
            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
                new TelaMenu().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}


