package clinicaapp;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TelaPacientes extends JFrame {
    private JTextField txtNome, txtCpf;
    private JButton btnSalvar, btnListar;
    private JTextArea area;

    public TelaPacientes() {
        setTitle("Cadastro de Pacientes");
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl1 = new JLabel("Nome:");
        lbl1.setBounds(30,30,80,25);
        add(lbl1);

        txtNome = new JTextField();
        txtNome.setBounds(100,30,200,25);
        add(txtNome);

        JLabel lbl2 = new JLabel("CPF:");
        lbl2.setBounds(30,70,80,25);
        add(lbl2);

        txtCpf = new JTextField();
        txtCpf.setBounds(100,70,200,25);
        add(txtCpf);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100,110,100,30);
        add(btnSalvar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(210,110,100,30);
        add(btnListar);

        area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30,160,320,150);
        add(scroll);

        btnSalvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                salvarPaciente();
            }
        });

        btnListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                listarPacientes();
            }
        });
    }

    private void salvarPaciente() {
        try (Connection con = Conexao.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO pacientes(nome, cpf) VALUES (?,?)");
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCpf.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Paciente salvo!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void listarPacientes() {
        try (Connection con = Conexao.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pacientes");
            area.setText("");
            while (rs.next()) {
                area.append(rs.getInt("id")+" - "+rs.getString("nome")+" - "+rs.getString("cpf")+"\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}
