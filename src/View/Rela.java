package View;

import java.awt.BorderLayout; 
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;

import DAO.ConnectionFactory;
import DAO.DAOResidente;
import DAO.DAOVisitas;
import Model.Dias;
import Model.JasperService;
import Model.Meses;
import Model.Residente;
import Model.Visita;
import TableModel.VTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;

public class Rela extends JFrame {

	private JPanel contentPane;
	private JButton btnMeseDia;
	private DAOResidente daoR;
	private DAOVisitas daoV;
	private Visita visita;
	private List<Visita> visitas;
	private List<Residente> residentes;
	private JTable tbAgenda;
	private JButton btnPorMs;
	private JButton btnLimparCampos;
	private JButton btnNewButton;
	private JLabel lblimg;
	private JComboBox cbResi;
	private JLabel lblData;
	private JButton btnBUSCA;
	private JButton btnJasper;
	private Connection conexao;
	private JButton btnEcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rela frame = new Rela();
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
	public Rela() {
		setResizable(false);
		daoR = new DAOResidente();
		daoV = new DAOVisitas();
		setTitle("Relat\u00F3rios");
		setBounds(100, 100, 799, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Residente = new JLabel("Residente");
		Residente.setBounds(226, 11, 73, 14);
		contentPane.add(Residente);

		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 763, 255);
		contentPane.add(scrollPane);

		tbAgenda = new JTable();
		scrollPane.setViewportView(tbAgenda);

		

		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbResi.setSelectedIndex(-1);
				
			}
		});
		btnLimparCampos.setBounds(364, 399, 171, 23);
		contentPane.add(btnLimparCampos);
		
		lblimg = new JLabel("");
		lblimg.setBounds(503, 11, 290, 111);
		lblimg.setIcon(new ImageIcon(CadVisita.class.getResource("/Images/logoo.png")));
		contentPane.add(lblimg);
		
		btnNewButton = new JButton("Gerar PDF");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int count = tbAgenda.getRowCount();
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\recepcao\\Dropbox\\VILA VIDA - ADMINISTRATIVO\\TRANFERENCIAS ARQUIVOS- COVID\\Visitas - Período Covid-19\\Relatórios\\Visitas-" + UUID.randomUUID() +".pdf"));
					document.open();
					PdfPTable tab = new PdfPTable(4);
				
					tab.addCell("DATA");
					tab.addCell("RESIDENTE");
					tab.addCell("VISITANTE 1");
					tab.addCell("VISITANTE 2");
					
					for (int i = 0; i < count; i++) {
						Object obj1 = GetData(tbAgenda, i, 0);
						Object obj2 = GetData(tbAgenda, i, 1);
						Object obj3 = GetData(tbAgenda, i, 2);
						Object obj4 = GetData(tbAgenda, i, 3);
						String value1 = obj1.toString();
						String value2 = obj2.toString();
						String value3 = obj3.toString();
						String value4 = obj4.toString();

						tab.addCell(value1);
						tab.addCell(value2);
						tab.addCell(value3);
						tab.addCell(value4);
					}
					document.addTitle("Relatório de visitas");
					document.add(tab);
					document.close();
					JFrame frame = new JFrame("Relatório de visitas");
					JPanel panel = new JPanel();
					panel.add(tbAgenda);
					frame.getContentPane().add(panel);
					frame.setVisible(true);
					frame.setSize(900, 600);
					frame.setUndecorated(true);
					frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
				} catch (Exception ee) {
				}
				
				JOptionPane.showMessageDialog(null, "PDF gerado com sucesso",
						"Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(251, 399, 103, 23);
		contentPane.add(btnNewButton);
		
		cbResi = new JComboBox();
		cbResi.setBounds(289, 7, 189, 22);
		contentPane.add(cbResi);
		
		try {	
			List<Visita> listaa = visitas = daoV.listar();
			for (Visita func : listaa) {
				cbResi.addItem(func.getNomeResi());
				
				//String classe = func.getData().toString();
				//classe.substring(2,4 );
				//cbData.addItem(classe);
			}
			}catch(SQLException e) {
				
			}
	cbResi.setSelectedIndex(-1);
	
	final JCalendar calendar = new JCalendar();
	calendar.setBounds(10, 0, 205, 131);
	contentPane.add(calendar);
	
	lblData = new JLabel("");
	lblData.setBounds(299, 34, 103, 14);
	contentPane.add(lblData);
	
	btnBUSCA = new JButton("Filtrar por Residente");
	btnBUSCA.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				
				if(lblData.getText().isEmpty() && cbResi.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Escolha uma data no calendário e um residente", "erro",
							JOptionPane.ERROR_MESSAGE);
				}else {
					String mes = lblData.getText().substring(3, 5);	
				List<Visita> listaa = visitas = daoV.buscaMes(cbResi.getSelectedItem().toString(),mes );
				for (Visita func : listaa) {
							criarTabela();
							tbAgenda.setVisible(true);	
				}
					} 
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			
		}
	});
	btnBUSCA.setBounds(20, 399, 171, 23);
	contentPane.add(btnBUSCA);
	
	btnJasper = new JButton("New button");
	btnJasper.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				abrirPontoJasper("01");
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	});
	lblData.setVisible(false);
	
	btnJasper.setBounds(545, 399, 89, 23);
	contentPane.add(btnJasper);
	
	btnEcluir = new JButton("Excluir");
	btnEcluir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (visita == null) {
				JOptionPane.showMessageDialog(null, "Selecione uma visita para excluir", "selecione",
						JOptionPane.WARNING_MESSAGE);
			} else {
				java.awt.Toolkit.getDefaultToolkit().beep();
				int botao = JOptionPane.showConfirmDialog(null,
						"Deseja excluir a Visita para " + visita.getNomeResi() + " ?", "CONFIRMAR EXCLUSÃO",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (botao == 0) {
					try {
						daoV.excluir(visita);
						visitas = daoV.listar();
						// criarTabela();
						//limpar();
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	});
	btnEcluir.setBounds(644, 399, 89, 23);
	contentPane.add(btnEcluir);
	
	calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			lblData.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getDate()));
			try {
				List<Visita> listaa = visitas = daoV.buscaData(lblData.getText().trim());
				for (Visita func : listaa) {
					try {
						
							visitas = daoV.buscaData(lblData.getText());
							criarTabela();
							tbAgenda.setVisible(true);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			} catch (SQLException ee) {
				ee.printStackTrace();
			}

		}
	});
		
	

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

		try {
			visitas = daoV.listar();
			criarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar as visitas", "erro",
					JOptionPane.ERROR_MESSAGE);
		}
		setLocationRelativeTo(null);
		tbAgenda.setVisible(false);
	}

	private void criarTabela() {
		VTableModel model = new VTableModel(visitas);
		tbAgenda.setModel(model);
		tbAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbAgenda.getColumnModel().getColumn(0).setPreferredWidth(280);
		tbAgenda.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbAgenda.getSelectedRow() >= 0) {
					visita = visitas.get(tbAgenda.getSelectedRow());
					// popular();
				}
			}
		});
	}
	public Object GetData(JTable table, int row_index, int col_index) {
		return table.getModel().getValueAt(row_index, col_index);
	}
	public void GeraRela() {
		try {
			conexao = (Connection) ConnectionFactory.getConnection();
			Map parametros = new HashMap();
			JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("C:\\Users\\recepcao\\Documents\\Organizar\\visitas-03.jasper").getFile(), parametros, (com.mysql.jdbc.Connection) conexao);
					JasperViewer jw = new JasperViewer(jp);
			
			jw.setVisible(true);
			//JasperExportManager.exportReportToPdfFile(jp,"/RelReclamacoes.xml");
			//FileWriter arq;
            /*try {
            	  arq = new FileWriter(System.getProperty("user.home") + "/Documents/PIZZARIA_RECLAMACOES.txt", true);
                arq.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void abrirPontoJasper(String numero) throws SQLException {
		java.sql.Connection conexao = ConnectionFactory.getConnection();
		JasperService service = new JasperService();
		service.addParams("BuscaData", lblData.getText());
		//service.addParams("BuscaDia", txtDia.getText());
		service.abrirPontoJasper("View\\visitas-" + numero + ".jasper", (Connection) conexao);
		conexao.close();
	}
}
