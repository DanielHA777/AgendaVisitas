package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.DAOResidente;
import Model.Residente;
import TableModel.ResidenteTableModel;


public class CadResidente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtQuarto;
	private List<Residente> residentes;
	private JLabel lblId;
	private JTable tbResi;
	private DAOResidente daoR;
	private boolean insertUnico = false;
	private JScrollPane scrollPane;
	private JButton btnbuscar;
	private JTextField txtBuscar;
	private JButton btnSalvar;
	private JButton Excluir;
	private Residente residente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadResidente frame = new CadResidente();
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
	public CadResidente() {
		daoR = new DAOResidente();
		setResizable(false);
		setTitle("Cadastro de Residentes");
		setBounds(100, 100, 556, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblNewLabel_1 = new JLabel("Nome: ");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(71, 71, 91, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBounds(182, 70, 319, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Quarto:");
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(71, 97, 91, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtQuarto = new JTextField();
		txtQuarto.setColumns(10);
		txtQuarto.setBounds(182, 96, 319, 20);
		contentPane.add(txtQuarto);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 158, 461, 175);
		contentPane.add(scrollPane);
		
		tbResi = new JTable();
		scrollPane.setViewportView(tbResi);
		
		btnbuscar = new JButton("Buscar");
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					residentes = daoR.listar(txtBuscar.getText().trim());
					criarTabela();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
			}
		});
		btnbuscar.setBounds(40, 133, 89, 23);
		contentPane.add(btnbuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(129, 134, 372, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnSalvar = new JButton("Salvar ou Atualizar");
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null, "informe o nome", "Aviso", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
				}else if(txtQuarto.getText().trim().isEmpty()) {   
					JOptionPane.showMessageDialog(null, "informe o quarto", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtQuarto.requestFocus();
				}
				else {
					if(residente == null) {
						residente = new Residente();
						//residente.setNome(txtNome.getText());
					}
					residente.setNome(txtNome.getText());
					residente.setQuarto(txtQuarto.getText()); 				
						try {
							 if(residente.getId() == 0) {
							daoR.inserir(residente);
						}else {
							 daoR.atualizar(residente); 
						 }				
						limpar();
						residentes = daoR.listar();
						criarTabela();
						if(insertUnico == true) {
							dispose();
						} }catch (Exception e1) {
							e1.printStackTrace();
						}
				}
			}
			
		});
		btnSalvar.setBounds(50, 344, 163, 23);
		contentPane.add(btnSalvar);
		
		Excluir = new JButton("Excluir");
		Excluir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(residente == null) {
					JOptionPane.showMessageDialog(null, "Selecione um residente para excluir", "selecione", JOptionPane.WARNING_MESSAGE); 
				}else {
					java.awt.Toolkit.getDefaultToolkit().beep();   
					int botao = JOptionPane.showConfirmDialog(null, "Deseja excluir o Residente "+ residente.getNome()+ " ?" , "CONFIRMAR EXCLUSÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(botao == 0) {
						try {
							daoR.excluir(residente);
							residentes = daoR.listar();
							criarTabela();
							limpar();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		Excluir.setBounds(223, 344, 89, 23);
		contentPane.add(Excluir);
		
		try {
			residentes = daoR.listar();
			criarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar os residentes", "erro", JOptionPane.ERROR_MESSAGE);
		}
		setLocationRelativeTo(null);
	}
	private void limpar() {
		txtNome.setText(null);
		txtQuarto.setText(null);
		residente = null;
		txtNome.requestFocus();
		tbResi.clearSelection();
		lblId.setText("ID:");
	}
	
	private void criarTabela() {
		ResidenteTableModel model = new ResidenteTableModel(residentes);
    	tbResi.setModel(model);
		tbResi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbResi.getColumnModel().getColumn(0).setPreferredWidth(280);
		tbResi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbResi.getSelectedRow() >= 0) {
					residente = residentes.get(tbResi.getSelectedRow());
					popular();
				}
			}
		});
	}
	private void popular() {
		txtNome.setText(residente.getNome());
		//txtNome.setText(residente.getNome().toString());
		txtQuarto.setText(residente.getQuarto());
		lblId.setText("ID: " + residente.getId());
	}
}
