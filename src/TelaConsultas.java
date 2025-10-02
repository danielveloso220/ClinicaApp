package clinicaapp;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TelaConsultas extends JFrame {
    private JTextField txtPaciente, txtData;
    private JButton btnAgendar, btnListar;
    private JTextArea area;

    public TelaConsultas() {
        setTitle("Consultas");
        setSize(400,400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl1 = new JLabel("Paciente:");
        lbl1.setBounds(30,30,80,25);
        add(lbl1);

        txtPaciente = new JTextField();
        txtPaciente.setBounds(100,30,200,25);
        add(txtPaciente);

        JLabel lbl2 = new JLabel("Data:");
        lbl2.setBounds(30,70,80,25);
        add(lbl2);

        txtData = new JTextField();
        txtData.setBounds(100,70,200,25);
        add(txtData);

        btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(100,110,100,30);
        add(btnAgendar);

        btnListar = new JButton("Listar");
        btnListar.setBounds(210,110,100,30);
        add(btnListar);

        area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30,160,320,150);
        add(scroll);

        btnAgendar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                agendarConsulta();
            }
        });

        btnListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                listarConsultas();
            }
        });
    }

    private void agendarConsulta() {
        try (Connection con = Conexao.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO consultas(paciente, data) VALUES (?,?)");
            pst.setString(1, txtPaciente.getText());
            pst.setString(2, txtData.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Consulta agendada!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void listarConsultas() {
        try (Connection con = Conexao.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM consultas");
            area.setText("");
            while (rs.next()) {
                area.append(rs.getInt("id")+" - "+rs.getString("paciente")+" - "+rs.getString("data")+"\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}
