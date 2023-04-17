package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.DAOResidente;
import DAO.DAOVisitas;
import Model.Residente;
import Model.Visita;
import Model.Visitante;
import TableModel.VTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Contagem extends JFrame {

	private JPanel contentPane;
	private JTable tbAgenda;
	private List<Visita> visitas;
	private List<Residente> residentes;
	private DAOResidente daoR;
	private DAOVisitas daoV;
	private Visita visita;
	private JComboBox cbData;
	private JButton btnBusca;
	private JLabel lblTempo;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contagem frame = new Contagem();
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
	public Contagem() {
		daoR = new DAOResidente();
		daoV = new DAOVisitas();
		setTitle("Iniciar Visita");
		setResizable(false);
		setBounds(100, 100, 746, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 432, 642, 199);
		contentPane.add(scrollPane);
		
		tbAgenda = new JTable();
		scrollPane.setViewportView(tbAgenda);
		
		cbData = new JComboBox();
		cbData.setBounds(36, 399, 239, 22);
		contentPane.add(cbData);
		
		btnBusca = new JButton("Buscar");
		btnBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					visitas = daoV.buscaData(cbData.getSelectedItem().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				criarTabela();
			}
		});
		btnBusca.setBounds(465, 398, 118, 23);
		contentPane.add(btnBusca);
		
		lblTempo = new JLabel("");
		lblTempo.setBackground(Color.YELLOW);
		lblTempo.setBounds(516, 29, 186, 79);
		contentPane.add(lblTempo);
		
		
		btnNewButton = new JButton("Iniciar ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(cbData.getSelectedIndex() == -1) {
				//	JOptionPane.showMessageDialog(null, "Selecione uma visita primeiro", "erro",	JOptionPane.INFORMATION_MESSAGE);
				//}else {
					crono();	
				//}
			}
		});
		btnNewButton.setBounds(119, 42, 118, 23);
		contentPane.add(btnNewButton);
		
		try {
			visitas = daoV.listar();
			criarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "não foi possível carregar as visitas", "erro",
					JOptionPane.ERROR_MESSAGE);
		}
		setLocationRelativeTo(null);
		
		
		try {	
				List<Visita> listaa = visitas = daoV.listar();
				for (Visita func : listaa) {
					cbData.addItem(func.getData().toString());
				}
				}catch(SQLException e) {
					
				}
		cbData.setSelectedIndex(-1);
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
	public void cronometro() throws InterruptedException {
		Thread T = new Thread();
		
		    for (int c = 20; c >= 0; c--) {
		        T.sleep(1000);               
		        lblTempo.setText(c+"-");
		       // JOptionPane.showMessageDialog(null, c, "erro",
				//		JOptionPane.INFORMATION_MESSAGE);
		          // System.out.println(c);	             
		}
		    
	}
	public void crono() {
		 JFrame jframe = new JFrame();
	        final JLabel jLabel = new JLabel();
	        jframe.getContentPane().setLayout(new FlowLayout());
	        jframe.setBounds(500, 300, 400, 100);

	        jframe.getContentPane().add(jLabel);
	        jframe.setVisible(true);

	        final Timer timer = new Timer();

	        timer.scheduleAtFixedRate(new TimerTask() {
	            int i = 1800;

	            public void run() {

	                jLabel.setText("Tempo Restante: " + i);
	                i--;

	                if (i < 0) {
	                    timer.cancel();
	                    jLabel.setText("Tempo esgotado");
	                    try {
							som();
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedAudioFileException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            }
	        }, 0, 1000);
	}
	public void som() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		
        URL oUrl = new URL("https://www.soundjay.com/buttons/beep-02.wav");
        Clip oClip = AudioSystem.getClip();
        AudioInputStream oStream = AudioSystem.getAudioInputStream(oUrl);
        oClip.open(oStream);

        oClip.loop(10); 

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null, "Clique pra fechar!");
            }
        });
	}
}
