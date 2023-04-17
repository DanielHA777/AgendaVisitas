package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.DAOResidente;
import DAO.DAOVisitante;
import Model.Residente;
import Model.Visitante;
import TableModel.ResidenteTableModel;
import TableModel.VisitanteTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadVisitante extends JFrame {

	private JPanel contentPane;
	private JLabel lblId;
	private JTextField txtNome;
	private JTable tbVisitante;
	private JTable tbVisitantes;
	private DAOVisitante daoV;
	private Visitante visitante;
	private JButton btnSalvar;
	private Residente residente;
	private JButton btnExcluir;
	private DAOResidente daoR;
	private List<Residente> residentes;
	private List<Visitante> visitantes;
	private JComboBox cbResi;
	private JLabel lblAtraso;
	private JPanel contentPanee;
	private boolean insertUnico = false;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVisitante frame = new CadVisitante();
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
	public CadVisitante() {
		daoV = new DAOVisitante();
		daoR = new DAOResidente();
		setResizable(false);
		setTitle("Cadastro de visitantes");
		setBounds(100, 100, 704, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel.setBounds(43, 69, 95, 14);
		contentPane.add(lblNewLabel);
		
		txtNome = new JTextField();
		txtNome.setBounds(158, 69, 255, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		cbResi = new JComboBox();
		cbResi.setBounds(158, 36, 255, 22);
		contentPane.add(cbResi);
		
		JLabel lblResidente = new JLabel("Residente:");
		lblResidente.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblResidente.setBounds(43, 41, 105, 14);
		contentPane.add(lblResidente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 107, 603, 205);
		contentPane.add(scrollPane);
		
		tbVisitantes = new JTable();
		tbVisitantes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(tbVisitantes);
		
		btnSalvar = new JButton("Salvar ou Atualizar");
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null, "informe o nome", "Aviso", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
				}else if(cbResi.getSelectedIndex() == -1) {   
					JOptionPane.showMessageDialog(null, "informe o Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbResi.requestFocus();
				}
				else {
					if(visitante == null) {
						visitante = new Visitante();
						//residente.setNome(txtNome.getText());
					}
					visitante.setNome(txtNome.getText());
					visitante.setNomeResi(cbResi.getSelectedItem().toString()); 				
						try {
							 if(visitante.getId() == 0) {
							daoV.inserir(visitante);
						}else {
							 daoV.atualizar(visitante); 
						 }				
						limpar();
						visitantes = daoV.listar();
						criarTabela();
						if(insertUnico == true) {
							dispose();
						} }catch (Exception e1) {
							e1.printStackTrace();
						}
				}
			}
		});
		btnSalvar.setBounds(43, 323, 171, 34);
		contentPane.add(btnSalvar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(visitante == null) {
					JOptionPane.showMessageDialog(null, "Selecione um Visitante para excluir", "selecione", JOptionPane.WARNING_MESSAGE); 
				}else {
					java.awt.Toolkit.getDefaultToolkit().beep();   
					int botao = JOptionPane.showConfirmDialog(null, "Deseja excluir o Visitante "+ visitante.getNome()+ " ?" , "CONFIRMAR EXCLUSÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(botao == 0) {
						try {
							daoV.excluir(visitante);
							visitantes = daoV.listar();
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
		
		try {
			
			List<Residente> lista = daoR.buscaResi();
			for (Residente func: lista) {
				cbResi.setSelectedIndex(-1);
				cbResi.addItem(func.getNome());
				//cbResi.setSelectedItem(func.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		btnExcluir.setBounds(234, 323, 156, 34);
		contentPane.add(btnExcluir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnLimpar.setBounds(400, 323, 156, 34);
		contentPane.add(btnLimpar);
		
		try {
			visitantes = daoV.listar();
			criarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar os visitantes", "erro", JOptionPane.ERROR_MESSAGE);
		}
		setLocationRelativeTo(null);
	}
	private void limpar() {
		txtNome.setText(null);
		cbResi.setSelectedIndex(-1);
		visitante = null;
		txtNome.requestFocus();
		//tbVisitante.clearSelection();
		lblId.setText("ID:");
	}
	private void criarTabela() {
		VisitanteTableModel model = new VisitanteTableModel(visitantes);
    	tbVisitantes.setModel(model);
    	tbVisitantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tbVisitantes.getColumnModel().getColumn(0).setPreferredWidth(280);
    	tbVisitantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbVisitantes.getSelectedRow() >= 0) {
					visitante = visitantes.get(tbVisitantes.getSelectedRow());
					popular();
				}
			}
		});
	}
	private void popular() {
		txtNome.setText(visitante.getNome());
		cbResi.setSelectedItem(visitante.getNomeResi());
		lblId.setText("ID: " + visitante.getId());
	}
	
}
