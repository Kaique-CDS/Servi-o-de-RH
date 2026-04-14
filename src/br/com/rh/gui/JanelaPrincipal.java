package br.com.rh.gui;

import br.com.rh.model.Funcionario;
import br.com.rh.util.MergeSort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JanelaPrincipal extends JFrame {

    private List<Funcionario> listaFuncionarios;

    private JTextField txtNome;
    private JTextField txtCargo;
    private JTextField txtProducao;
    private JTextArea txtAreaResultado;

    public JanelaPrincipal() {
        super("Sistema de RH - Ranking de Produção (Merge Sort)");
        listaFuncionarios = new ArrayList<>();

        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        // Painel Superior (Formulário de Cadastro)
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        painelFormulario.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("Nome do Candidato/Funcionário:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Cargo:"));
        txtCargo = new JTextField();
        painelFormulario.add(txtCargo);

        painelFormulario.add(new JLabel("Produção (Ex: 100):"));
        txtProducao = new JTextField();
        painelFormulario.add(txtProducao);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(40, 167, 69));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });
        painelFormulario.add(new JLabel("")); // célula vazia
        painelFormulario.add(btnCadastrar);

        add(painelFormulario, BorderLayout.NORTH);

        // Centro (Área de Texto e Título)
        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBorder(new EmptyBorder(0, 10, 10, 10));
        
        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
        
        painelCentro.add(new JLabel("Lista / Ranking:"), BorderLayout.NORTH);
        painelCentro.add(scrollPane, BorderLayout.CENTER);

        add(painelCentro, BorderLayout.CENTER);

        // Painel Inferior (Botões de Ação)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnMostrarLista = new JButton("Mostrar Lista Atual");
        btnMostrarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarLista();
            }
        });

        JButton btnGerarRanking = new JButton("Gerar Ranking (Merge Sort)");
        btnGerarRanking.setBackground(new Color(0, 123, 255));
        btnGerarRanking.setForeground(Color.WHITE);
        btnGerarRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRanking();
            }
        });

        painelBotoes.add(btnMostrarLista);
        painelBotoes.add(btnGerarRanking);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void cadastrarFuncionario() {
        String nome = txtNome.getText().trim();
        String cargo = txtCargo.getText().trim();
        String producaoStr = txtProducao.getText().trim();

        if (nome.isEmpty() || cargo.isEmpty() || producaoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int producao = Integer.parseInt(producaoStr);
            Funcionario f = new Funcionario(nome, cargo, producao);
            listaFuncionarios.add(f);

            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            
            // Limpa os campos
            txtNome.setText("");
            txtCargo.setText("");
            txtProducao.setText("");
            txtNome.requestFocus();

            mostrarLista();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo de produção deve ser um número inteiro válido.", "Erro numérico", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarLista() {
        StringBuilder sb = new StringBuilder();
        if (listaFuncionarios.isEmpty()) {
            sb.append("Nenhum funcionário cadastrado.\n");
        } else {
            sb.append("--- LISTA DE FUNCIONÁRIOS (Sem Ordenação) ---\n\n");
            for (int i = 0; i < listaFuncionarios.size(); i++) {
                sb.append((i + 1)).append(". ").append(listaFuncionarios.get(i).toString()).append("\n");
            }
        }
        txtAreaResultado.setText(sb.toString());
    }

    private void gerarRanking() {
        if (listaFuncionarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cadastre alguns funcionários primeiro para gerar o ranking.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Chama o MergeSort para ordenar a lista (decrescente por produção)
        MergeSort.ordenarPorProducaoDecrescente(listaFuncionarios);

        StringBuilder sb = new StringBuilder();
        sb.append("=== RANKING DE PRODUÇÃO (Classificado via Merge Sort) ===\n\n");
        for (int i = 0; i < listaFuncionarios.size(); i++) {
            sb.append(i + 1).append("º Lugar: ").append(listaFuncionarios.get(i).toString()).append("\n");
        }
        txtAreaResultado.setText(sb.toString());
    }
}
