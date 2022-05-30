package br.com.gestaodentista.modelo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.gestaodentista.modelo.dao.PacienteDAO;
import br.com.gestaodentista.modelo.entidade.Paciente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPaciente extends JFrame {
	

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textRg;
	private JTextField textCpf;
	private JTextField textEndereco;
	private JTextField textBairro;
	private JTextField textMunicipio;
	private JTextField textCep;
	private JTextField textTelefone;
	private JTextField textCelular;
	private JTextField textEmail;
	private JTextField textGrupos;
	private JTable table;
	private JTextField textField_12;
	private JTextField textSexo;
	private JTextField textUf;
	private JTextField textSituacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPaciente frame = new ViewPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 22, 804, 624);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(123, 11, 49, 14);
		panel.add(lblNewLabel_1);
		
		textNome = new JTextField();
		textNome.setBounds(116, 36, 309, 20);
		panel.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("RG:");
		lblNewLabel_2.setBounds(438, 11, 49, 14);
		panel.add(lblNewLabel_2);
		
		textRg = new JTextField();
		textRg.setBounds(435, 36, 145, 20);
		panel.add(textRg);
		textRg.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CPF:");
		lblNewLabel_3.setBounds(591, 11, 49, 14);
		panel.add(lblNewLabel_3);
		
		textCpf = new JTextField();
		textCpf.setBounds(590, 36, 204, 20);
		panel.add(textCpf);
		textCpf.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setBounds(10, 67, 49, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Endereço:");
		lblNewLabel_5.setBounds(116, 67, 78, 14);
		panel.add(lblNewLabel_5);
		
		textEndereco = new JTextField();
		textEndereco.setBounds(116, 93, 309, 20);
		panel.add(textEndereco);
		textEndereco.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Bairro:");
		lblNewLabel_6.setBounds(438, 67, 49, 14);
		panel.add(lblNewLabel_6);
		
		textBairro = new JTextField();
		textBairro.setBounds(438, 93, 142, 20);
		panel.add(textBairro);
		textBairro.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Municipio:");
		lblNewLabel_7.setBounds(591, 67, 86, 14);
		panel.add(lblNewLabel_7);
		
		textMunicipio = new JTextField();
		textMunicipio.setBounds(591, 93, 126, 20);
		panel.add(textMunicipio);
		textMunicipio.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("UF:");
		lblNewLabel_8.setBounds(727, 67, 49, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("CEP:");
		lblNewLabel_9.setBounds(10, 125, 49, 14);
		panel.add(lblNewLabel_9);
		
		textCep = new JTextField();
		textCep.setBounds(10, 150, 96, 20);
		panel.add(textCep);
		textCep.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Telefone:");
		lblNewLabel_10.setBounds(116, 125, 78, 14);
		panel.add(lblNewLabel_10);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(116, 150, 118, 20);
		panel.add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Celular:");
		lblNewLabel_11.setBounds(254, 125, 49, 14);
		panel.add(lblNewLabel_11);
		
		textCelular = new JTextField();
		textCelular.setBounds(244, 150, 181, 20);
		panel.add(textCelular);
		textCelular.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Email:");
		lblNewLabel_12.setBounds(438, 125, 49, 14);
		panel.add(lblNewLabel_12);
		
		textEmail = new JTextField();
		textEmail.setBounds(438, 150, 356, 20);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Grupo:");
		lblNewLabel_13.setBounds(10, 181, 49, 14);
		panel.add(lblNewLabel_13);
		
		textGrupos = new JTextField();
		textGrupos.setBounds(10, 206, 96, 20);
		panel.add(textGrupos);
		textGrupos.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Situação:");
		lblNewLabel_14.setBounds(116, 181, 78, 14);
		panel.add(lblNewLabel_14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 290, 774, 260);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identifica\u00E7\u00E3o", "Nome do paciente", "Cpf"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_15 = new JLabel("Pesquisar:");
		lblNewLabel_15.setBounds(10, 252, 72, 14);
		panel.add(lblNewLabel_15);
		
		textField_12 = new JTextField();
		textField_12.setBounds(74, 249, 450, 20);
		panel.add(textField_12);
		textField_12.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setBounds(568, 248, 118, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Novo");
		btnNewButton_1.setBounds(10, 561, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Excluir");
		btnNewButton_3.setBounds(204, 561, 89, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Salvar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Paciente paciente = new Paciente();
				 PacienteDAO pacienteDAO = new PacienteDAO();
				 
				 paciente.setNome(textNome.getText());
				 paciente.setRg(textRg.getText());
				 paciente.setCpf(textCpf.getText());
				 paciente.setSexo(textSexo.getText());
				 paciente.setEndereco(textEndereco.getText());
				 paciente.setBairro(textBairro.getText());
				 paciente.setMunicipio(textMunicipio.getText());
				 paciente.setUf(textUf.getText());
				 paciente.setCep(textCep.getText());
				 paciente.setTelefone(textTelefone.getText());
				 paciente.setCelular(textCelular.getText());
				 paciente.setEmail(textEmail.getText());
				 paciente.setGrupo(textGrupos.getText());
				 paciente.setSituacao(textSituacao.getText());
				 
				 try {
					pacienteDAO.gravarPaciente(paciente);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 
				
			}
		});
		btnNewButton_4.setBounds(105, 561, 89, 23);
		panel.add(btnNewButton_4);
		
		textSexo = new JTextField();
		textSexo.setBounds(10, 92, 49, 20);
		panel.add(textSexo);
		textSexo.setColumns(10);
		
		textUf = new JTextField();
		textUf.setBounds(727, 92, 49, 20);
		panel.add(textUf);
		textUf.setColumns(10);
		
		textSituacao = new JTextField();
		textSituacao.setBounds(116, 206, 96, 20);
		panel.add(textSituacao);
		textSituacao.setColumns(10);
	}
}
