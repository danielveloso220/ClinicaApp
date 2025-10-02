package clinicaapp;

import javax.swing.*;
import java.awt.event.*;

public class TelaMenu extends JFrame {
    public TelaMenu() {
        setTitle("Menu - ClinicaApp");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Botão Gerenciar Pacientes
        JButton btnPacientes = new JButton("Gerenciar Pacientes");
        btnPacientes.setBounds(100, 50, 200, 40);
        add(btnPacientes);

        // Botão Gerenciar Consultas
        JButton btnConsultas = new JButton("Gerenciar Consultas");
        btnConsultas.setBounds(100, 120, 200, 40);
        add(btnConsultas);

        // Botão Sair
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(150, 200, 100, 30);
        add(btnSair);

        // EVENTOS

        // Abrir tela de pacientes
        btnPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaPacientes().setVisible(true); // <-- Certifique-se que TelaPacientes existe e é JFrame
            }
        });

        // Abrir tela de consultas
        btnConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaConsultas().setVisible(true); // <-- Certifique-se que TelaConsultas existe e é JFrame
            }
        });

        // Sair do programa
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

