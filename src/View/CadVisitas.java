package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.DAOResidente;
import DAO.DAOVisitante;
import DAO.DAOVisitas;
import Model.Dias;
import Model.Meses;
import Model.Residente;
import Model.Visita;
import Model.Visitante;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CadVisitas extends JFrame {

	private JPanel contentPane;
	private JButton btnBuscar;
	private JComboBox cbv1;
	private JComboBox cbv2;
	private JComboBox cbv3;
	private JComboBox cbv4;
	private JButton btnMais;
	private JComboBox cbResi;
	private DAOVisitas daoV;
	private JLabel lblDia;
	private JComboBox cbDia;
	private List<Visita> visitas;
	private JComboBox cbMes;
	private JTextField txtHora;
	private JTextField txtData;
	private boolean insertUnico = false;
	private DAOResidente daoR;
	private DAOVisitante daoVV;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnLimpar;
	private JButton btnAviso;
	private JLabel lblv3;
	private Visita visita;
	private JLabel lblv4;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JTextArea taObs;
	private JTextField txtHora2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVisitas frame = new CadVisitas();
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
	public CadVisitas() {
		daoR = new DAOResidente();
		daoV = new DAOVisitas();
		daoVV = new DAOVisitante();
				
		setResizable(false);
		setTitle("Visitas ");
		setBounds(100, 100, 690, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Residente:");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel.setBounds(38, 42, 85, 14);
		contentPane.add(lblNewLabel);
		
		cbResi = new JComboBox();
		cbResi.setBounds(133, 38, 212, 22);
		contentPane.add(cbResi);
		
		btnBuscar = new JButton("Buscar visitantes");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// txtInvisivel.setText(cbResi.getSelectedItem().toString());
					// visitantes = daoV.listar(cbResi.getSelectedItem().toString());
					if (cbResi.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "informe um Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					} else {
						List<Visitante> lista = daoVV.listar(cbResi.getSelectedItem().toString());
						List<Visita> listaa = visitas = daoV.buscaData(txtData.getText());
						for (Visita func : listaa) {
							//cbData.setSelectedIndex(-1);
							txtData.setText(func.getData() + " " + func.getData());
							txtHora2.setText(func.getHora());
							//cbData.addItem(func.getData().toString());
						}
						
						List<Visitante> listay = daoVV.listar(cbResi.getSelectedItem().toString());
						
						// visitantes = daoV.buscaVisi();
						for (Visitante func : listay) {
							cbv1.setSelectedIndex(-1);
							cbv2.setSelectedIndex(-1);
							cbv3.setSelectedIndex(-1);
							cbv4.setSelectedIndex(-1);
							cbv1.addItem(func.getNome());
							cbv2.addItem(func.getNome());
							cbv3.addItem(func.getNome());
							cbv4.addItem(func.getNome());
							// criarTabelaV();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(133, 64, 212, 23);
		contentPane.add(btnBuscar);
		
		cbv1 = new JComboBox();
		cbv1.setBounds(133, 98, 212, 22);
		contentPane.add(cbv1);
		
		cbv2 = new JComboBox();
		cbv2.setBounds(133, 130, 212, 22);
		contentPane.add(cbv2);
		
		cbv3 = new JComboBox();
		cbv3.setBounds(133, 196, 212, 22);
		contentPane.add(cbv3);
		
		cbv4 = new JComboBox();
		cbv4.setBounds(133, 226, 212, 22);
		contentPane.add(cbv4);
		
		btnMais = new JButton("+");
		btnMais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblv3.setVisible(true);
				lblv4.setVisible(true);
				cbv3.setVisible(true);
				cbv4.setVisible(true);
			}
		});
		btnMais.setBounds(194, 162, 89, 23);
		contentPane.add(btnMais);
		
		JLabel lblVisitante = new JLabel("Visitante 1:");
		lblVisitante.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblVisitante.setBounds(38, 102, 85, 14);
		contentPane.add(lblVisitante);
		
		JLabel lblVisitante_2 = new JLabel("Visitante 2:");
		lblVisitante_2.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblVisitante_2.setBounds(38, 134, 85, 14);
		contentPane.add(lblVisitante_2);
		
		lblv3 = new JLabel("Visitante 3:");
		lblv3.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblv3.setBounds(38, 200, 85, 14);
		contentPane.add(lblv3);
		
		lblv4 = new JLabel("Visitante 4:");
		lblv4.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblv4.setBounds(38, 230, 85, 14);
		contentPane.add(lblv4);
		
		lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblDia.setBounds(372, 44, 85, 14);
		contentPane.add(lblDia);
		
		cbDia = new JComboBox();
		cbDia.setBounds(427, 40, 124, 22);
		contentPane.add(cbDia);
		
		JLabel lblMs = new JLabel("M\u00EAs:");
		lblMs.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblMs.setBounds(372, 68, 85, 14);
		contentPane.add(lblMs);
		
		cbMes = new JComboBox();
		cbMes.setBounds(427, 66, 124, 22);
		contentPane.add(cbMes);
		
		JLabel lblData = new JLabel("Hora:");
		lblData.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblData.setBounds(372, 93, 85, 14);
		contentPane.add(lblData);
		
		txtHora = new JTextField();
		txtHora.setBounds(427, 93, 124, 20);
		contentPane.add(txtHora);
		txtHora.setColumns(10);
		
		txtData = new JTextField();
		txtData.setBounds(447, 13, 86, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);
		
		btnSalvar = new JButton("Agendar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtHora.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "informe a Hora", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtHora.requestFocus();
				} else if (cbResi.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe o Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbResi.requestFocus();
				} else if (cbv1.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe pelo menos um visitante", "Aviso",
							JOptionPane.ERROR_MESSAGE);
					cbv1.requestFocus();
				} else if (cbMes.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe o m�s", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else if (!txtData.getText().isEmpty() && !txtData.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(null, "J� existe um agendamento para essa data e hor�rio", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();			
				}
				else if(visita == null) {
					visita = new Visita();
					
				}
				visita.setMeses((Meses) cbMes.getSelectedItem());
				visita.setHora(txtHora.getText().trim());
				visita.setDia((Dias) cbDia.getSelectedItem());
				visita.setData(txtData.getText());
				visita.setVisitante1(cbv1.getSelectedItem().toString());
				visita.setVisitante2(cbv2.getSelectedItem() + "");
				visita.setVisitante3(cbv3.getSelectedItem() + "");
				visita.setVisitante4(cbv4.getSelectedItem() + "");
				visita.setObservacao(taObs.getText());
				visita.setNomeResi(cbResi.getSelectedItem().toString());
				
				try {
				
					if (visita.getId() == 0) {
						daoV.inserir(visita);
					}else{
						daoV.atualizar(visita);
					}

					String s = "<html> <head> <title> <center> <h1>Confirma��o de visita</h1></center> </title></head><br> <body> "
							+ "<h2>- Ol� " + cbv1.getSelectedItem().toString() + " </h2> <br>" + "Sua visita para "
							+ cbResi.getSelectedItem().toString() + ",  esta agendada para " + txtData.getText()
							+ "<br> \r\n" + " -� obrigat�rio o uso de m�scara. <br> \r\n"
							+ " - � obrigat�rio o uso de avental.<br> \r\n"
							+ " - � obrigat�rio o uso de touca.<br> \r\n"
							+ "- A visita ter� uma dura��o de 30 minutos.<br> \r\n"
							+ "- A visita ser� acompanhada (a dist�ncia). <br>"
					// + "<img src=/Images/logoo.png width=\"300\"/> "

							+ "</body> </html>";

					btnAviso.setText(s);
					btnAviso.setVisible(true);

					limpar();
					visitas = daoV.listar();
					//criarTabela();
					if (insertUnico == true) {
						dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}}

			
		});
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnSalvar.setBounds(38, 279, 99, 32);
		contentPane.add(btnSalvar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (visita == null) {
					JOptionPane.showMessageDialog(null, "Selecione uma visita para excluir", "selecione",
							JOptionPane.WARNING_MESSAGE);
				} else {
					java.awt.Toolkit.getDefaultToolkit().beep();
					int botao = JOptionPane.showConfirmDialog(null,
							"Deseja excluir a Visita para " + visita.getNomeResi() + " ?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						try {
							daoV.excluir(visita);
							visitas = daoV.listar();
							//criarTabela();
							limpar();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnExcluir.setBounds(194, 279, 99, 32);
		contentPane.add(btnExcluir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
				
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnLimpar.setBounds(341, 279, 99, 32);
		contentPane.add(btnLimpar);
		
		cbDia.setModel(new DefaultComboBoxModel(Dias.values()));
		cbDia.setSelectedIndex(-1);
		cbMes.setModel(new DefaultComboBoxModel(Meses.values()));
		cbMes.setSelectedIndex(-1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(372, 146, 179, 102);
		contentPane.add(scrollPane);
		
		taObs = new JTextArea();
		scrollPane.setViewportView(taObs);
		
		lblNewLabel_1 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(405, 132, 115, 14);
		contentPane.add(lblNewLabel_1);
		
		txtHora2 = new JTextField();
		txtHora2.setBounds(556, 13, 86, 20);
		contentPane.add(txtHora2);
		txtHora2.setColumns(10);
		
		btnAviso = new JButton("");
		btnAviso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAviso.setVisible(false);
			}
		});
		btnAviso.setIcon(new ImageIcon(CadVisita.class.getResource("/Images/logoo.png")));
		btnAviso.setBackground(new Color(245, 255, 250));
		btnAviso.setBounds(48, 13, 615, 386);
		btnAviso.setVisible(false);
		contentPane.add(btnAviso);
		//txtData.setVisible(false);
		
		cbv3.setVisible(false);
		cbv4.setVisible(false);
		lblv3.setVisible(false);
		lblv4.setVisible(false);
		
		try {
			List<Residente> lista = daoR.buscaResi();
			for (Residente func : lista) {
				cbResi.setSelectedIndex(-1);
				cbResi.addItem(func.getNome().toString());
				// cbResi.setSelectedItem(func.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limpar() {
		cbDia.setSelectedIndex(-1);
		cbMes.setSelectedIndex(-1);
		cbResi.setSelectedIndex(-1);
		cbv1.setSelectedIndex(-1);
		cbv2.setSelectedIndex(-1);
		cbv3.setSelectedIndex(-1);
		cbv4.setSelectedIndex(-1);
		txtData.setText(null);
		txtHora.setText(null);
		taObs.setText(null);
	}
}
