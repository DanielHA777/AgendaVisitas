package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import DAO.DAOResidente;
import DAO.DAOVisitante;
import DAO.DAOVisitas;
import Model.Dias;
import Model.Meses;
import Model.Residente;
import Model.Visita;
import Model.Visitante;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Teste extends JFrame {

	private JPanel contentPane;
	private JLabel lblimg;
	private JLabel lblData;
	private JLabel lblNove;
	private JLabel lblNoveMeia;
	private JLabel lblDez;
	private JLabel lblDezMeia;
	private JLabel lblOnze;
	private JLabel lblOnzeMeia;
	private JLabel lblQuatorze;
	private JLabel lblQuinze;
	private JLabel lblQuinzeMeia;
	private JLabel lblDezesseis;
	private JTextField txtResiQuartHora1;
	private JTextField txtVisitantes1;
	private JTextField txtResi2;
	private JTextField txtResi3;
	private JTextField txtResi4;
	private JTextField txtResi5;
	private DAOVisitante daoVV;
	private JTextField txtResi6;
	private JTextField txtResi10;
	private JTextField txtResi9;
	private JTextField txtResi8;
	private JTextField txtResi7;
	private JTextField txtVisitantes2;
	private JTextField txtvisitantes3;
	private JTextField txtVisitantes4;
	private JTextField txtVisitantes5;
	private JTextField txtVisitantes6;
	private JTextField txtVisitantes7;
	private JTextField txtVisitantes8;
	private JTextField txtVisitantes9;
	private List<Visita> visitas;
	private JTextField txtVisitantes10;
	private DAOVisitas daoV;
	private DAOResidente daoR;
	private JComboBox cbResi;
	private JButton btnBuscaVisi;
	private JComboBox cbV1;
	private boolean insertUnico = false;
	private JComboBox cbV2;
	private JButton btnMais;
	private JComboBox cbV3;
	private JComboBox cbV4;
	private JScrollPane scrollPane;
	private JTextArea taObs;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private Visita visita;
	private JButton btnLimpar;
	private JLabel lblHora;
	private JLabel lblPlay1;
	private JLabel lblPlay1_1;
	private JLabel lblPlay1_2;
	private JLabel lblPlay1_3;
	private JLabel lblPlay1_4;
	private JLabel lblPlay1_5;
	private JLabel lblPlay1_6;
	private JLabel lblPlay1_7;
	private JLabel lblPlay1_8;
	private JLabel lblPlay1_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
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
	public Teste() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Teste.class.getResource("/Images/logoo.png")));
		setResizable(false);
		daoVV = new DAOVisitante();
		daoR = new DAOResidente();
		daoV = new DAOVisitas();
		setBounds(100, 100, 722, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblData = new JLabel("");
		lblData.setBounds(208, 124, 95, 20);
		lblData.setVisible(false);

		lblPlay1 = new JLabel(" >");
		lblPlay1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResiQuartHora1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1.setForeground(new Color(47, 79, 79));
		lblPlay1.setBackground(Color.GREEN);
		lblPlay1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1.setBounds(0, 163, 19, 14);

		lblPlay1_1 = new JLabel(" >");
		lblPlay1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		
				lblOnzeMeia = new JLabel("14:30");
				lblOnzeMeia.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(txtResi6.getText().equals("")) {
							lblOnzeMeia.setBackground(Color.ORANGE);
							lblOnzeMeia.setOpaque(true);
							lblHora.setText("14:30");
						}else if(txtResi6.getText() != null) {
							int botao = JOptionPane.showConfirmDialog(null,
									"J� existe um agendamento nesta data e hor�rio"
									+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (botao == 0) {
								lblOnzeMeia.setBackground(Color.ORANGE);
								lblOnzeMeia.setOpaque(true);
								lblHora.setText("14:30");
							}
						}
					}
				});
				lblOnzeMeia.setFont(new Font("Arial Black", Font.PLAIN, 11));
				lblOnzeMeia.setBounds(29, 364, 46, 14);
				contentPane.add(lblOnzeMeia);
		lblPlay1_1.setForeground(new Color(47, 79, 79));
		lblPlay1_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_1.setBackground(Color.GREEN);
		lblPlay1_1.setBounds(0, 193, 19, 14);
		contentPane.add(lblPlay1_1);

		lblPlay1_8 = new JLabel(" >");
		lblPlay1_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi9.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_8.setForeground(new Color(47, 79, 79));
		lblPlay1_8.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_8.setBackground(Color.GREEN);
		lblPlay1_8.setBounds(0, 429, 19, 14);
		contentPane.add(lblPlay1_8);

		lblPlay1_7 = new JLabel(" >");
		lblPlay1_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi8.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_7.setForeground(new Color(47, 79, 79));
		lblPlay1_7.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_7.setBackground(Color.GREEN);
		lblPlay1_7.setBounds(0, 396, 19, 14);
		contentPane.add(lblPlay1_7);

		lblPlay1_5 = new JLabel(" >");
		lblPlay1_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi6.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_5.setForeground(new Color(47, 79, 79));
		lblPlay1_5.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_5.setBackground(Color.GREEN);
		lblPlay1_5.setBounds(0, 333, 19, 14);
		contentPane.add(lblPlay1_5);

		lblPlay1_3 = new JLabel(" >");
		lblPlay1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi4.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_3.setForeground(new Color(47, 79, 79));
		lblPlay1_3.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_3.setBackground(Color.GREEN);
		lblPlay1_3.setBounds(0, 261, 19, 14);
		contentPane.add(lblPlay1_3);

		lblPlay1_9 = new JLabel(" >");
		lblPlay1_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi10.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_9.setForeground(new Color(47, 79, 79));
		lblPlay1_9.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_9.setBackground(Color.GREEN);
		lblPlay1_9.setBounds(0, 455, 19, 14);
		contentPane.add(lblPlay1_9);

		lblPlay1_2 = new JLabel(" >");
		lblPlay1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_2.setForeground(new Color(47, 79, 79));
		lblPlay1_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_2.setBackground(Color.GREEN);
		lblPlay1_2.setBounds(0, 224, 19, 14);
		contentPane.add(lblPlay1_2);

		lblPlay1_4 = new JLabel(" >");
		lblPlay1_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi5.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_4.setForeground(new Color(47, 79, 79));
		lblPlay1_4.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_4.setBackground(Color.GREEN);
		lblPlay1_4.setBounds(0, 298, 19, 14);
		contentPane.add(lblPlay1_4);

		lblPlay1_6 = new JLabel(" >");
		lblPlay1_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtResi7.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "N�o existe uma visita agendada nesta data para ser inicidada",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				} else {
					crono();
				}
			}
		});
		lblPlay1_6.setForeground(new Color(47, 79, 79));
		lblPlay1_6.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblPlay1_6.setBackground(Color.GREEN);
		lblPlay1_6.setBounds(0, 364, 19, 14);
		contentPane.add(lblPlay1_6);
		ImageIcon fotoPp = new ImageIcon(getClass().getResource("/Images/botao-play.png"));
		lblPlay1.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1.getWidth(), lblPlay1.getHeight()));
		lblPlay1_1.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_1.getWidth(), lblPlay1_1.getHeight()));
		lblPlay1_2.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_2.getWidth(), lblPlay1_2.getHeight()));
		lblPlay1_3.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_3.getWidth(), lblPlay1_3.getHeight()));
		lblPlay1_4.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_4.getWidth(), lblPlay1_4.getHeight()));
		lblPlay1_5.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_5.getWidth(), lblPlay1_5.getHeight()));
		lblPlay1_6.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_6.getWidth(), lblPlay1_6.getHeight()));
		lblPlay1_7.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_7.getWidth(), lblPlay1_7.getHeight()));
		lblPlay1_8.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_8.getWidth(), lblPlay1_8.getHeight()));
		lblPlay1_9.setIcon(util.ImageUtil.redimensiona(fotoPp, lblPlay1_9.getWidth(), lblPlay1_9.getHeight()));

		contentPane.add(lblPlay1);

		contentPane.add(lblData);

		txtVisitantes2 = new JTextField();
		txtVisitantes2.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes2.setColumns(10);
		txtVisitantes2.setBounds(425, 191, 258, 20);
		contentPane.add(txtVisitantes2);

		txtVisitantes8 = new JTextField();
		txtVisitantes8.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes8.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes8.setColumns(10);
		txtVisitantes8.setBounds(425, 394, 258, 20);
		contentPane.add(txtVisitantes8);

		txtvisitantes3 = new JTextField();
		txtvisitantes3.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtvisitantes3.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtvisitantes3.setColumns(10);
		txtvisitantes3.setBounds(425, 222, 258, 20);
		contentPane.add(txtvisitantes3);

		txtVisitantes4 = new JTextField();
		txtVisitantes4.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes4.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes4.setColumns(10);
		txtVisitantes4.setBounds(425, 259, 258, 20);
		contentPane.add(txtVisitantes4);

		txtVisitantes6 = new JTextField();
		txtVisitantes6.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes6.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes6.setColumns(10);
		txtVisitantes6.setBounds(425, 331, 258, 20);
		contentPane.add(txtVisitantes6);

		txtVisitantes9 = new JTextField();
		txtVisitantes9.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes9.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes9.setColumns(10);
		txtVisitantes9.setBounds(425, 427, 258, 20);
		contentPane.add(txtVisitantes9);

		txtVisitantes10 = new JTextField();
		txtVisitantes10.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes10.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes10.setColumns(10);
		txtVisitantes10.setBounds(425, 458, 258, 20);
		contentPane.add(txtVisitantes10);

		txtVisitantes7 = new JTextField();
		txtVisitantes7.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes7.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes7.setColumns(10);
		txtVisitantes7.setBounds(425, 362, 258, 20);
		contentPane.add(txtVisitantes7);

		txtVisitantes5 = new JTextField();
		txtVisitantes5.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes5.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes5.setColumns(10);
		txtVisitantes5.setBounds(425, 296, 258, 20);
		contentPane.add(txtVisitantes5);

		txtResi7 = new JTextField();
		txtResi7.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi7.setColumns(10);
		txtResi7.setBounds(74, 327, 229, 20);
		txtResi7.setBorder(new EmptyBorder(0, 0, 3, 0));
		contentPane.add(txtResi7);

		txtResi10 = new JTextField();
		txtResi10.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi10.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi10.setColumns(10);
		txtResi10.setBounds(74, 458, 229, 20);
		contentPane.add(txtResi10);

		txtResi9 = new JTextField();
		txtResi9.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi9.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi9.setColumns(10);
		txtResi9.setBounds(74, 427, 229, 20);
		contentPane.add(txtResi9);

		txtResi8 = new JTextField();
		txtResi8.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi8.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi8.setColumns(10);
		txtResi8.setBounds(74, 394, 229, 20);
		contentPane.add(txtResi8);

		txtResi3 = new JTextField();
		txtResi3.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi3.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi3.setColumns(10);
		txtResi3.setBounds(74, 222, 229, 20);
		contentPane.add(txtResi3);

		txtVisitantes1 = new JTextField();
		txtVisitantes1.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtVisitantes1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtVisitantes1.setColumns(10);
		txtVisitantes1.setBounds(425, 160, 258, 20);
		contentPane.add(txtVisitantes1);

		txtResi4 = new JTextField();
		txtResi4.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi4.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi4.setColumns(10);
		txtResi4.setBounds(74, 259, 229, 20);
		contentPane.add(txtResi4);

		txtResiQuartHora1 = new JTextField();
		txtResiQuartHora1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResiQuartHora1.setBounds(74, 161, 229, 20);
		txtResiQuartHora1.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResiQuartHora1.setBorder(new LineBorder(Color.BLACK, 0));
		contentPane.add(txtResiQuartHora1);
		txtResiQuartHora1.setColumns(10);

		txtResi2 = new JTextField();
		txtResi2.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi2.setColumns(10);
		txtResi2.setBounds(74, 191, 229, 20);
		contentPane.add(txtResi2);

		txtResi5 = new JTextField();
		txtResi5.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi5.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi5.setColumns(10);
		txtResi5.setBounds(74, 296, 229, 20);
		contentPane.add(txtResi5);

		txtResi6 = new JTextField();
		txtResi6.setBorder(new EmptyBorder(0, 0, 3, 0));
		txtResi6.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtResi6.setColumns(10);
		txtResi6.setBounds(74, 363, 229, 20);
		contentPane.add(txtResi6);

		lblQuinzeMeia = new JLabel("15:30");
		lblQuinzeMeia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi9.getText().equals("")) {
					lblQuinzeMeia.setBackground(Color.ORANGE);
					lblQuinzeMeia.setOpaque(true);
					lblHora.setText("15:30");
				}else if(txtResi9.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblQuinzeMeia.setBackground(Color.ORANGE);
						lblQuinzeMeia.setOpaque(true);
						lblHora.setText("15:30");
					}
				}
			}
		});
		lblQuinzeMeia.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblQuinzeMeia.setBounds(29, 429, 46, 14);
		contentPane.add(lblQuinzeMeia);

		lblOnze = new JLabel("11:00");
		lblOnze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi5.getText().equals("")) {
					lblOnze.setBackground(Color.ORANGE);
					lblOnze.setOpaque(true);
					lblHora.setText("11:00");
				}else if(txtResi5.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblOnze.setBackground(Color.ORANGE);
						lblOnze.setOpaque(true);
						lblHora.setText("11:00");
					}
				}
			}
		});
		lblOnze.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblOnze.setBounds(29, 298, 46, 14);
		contentPane.add(lblOnze);

		lblNove = new JLabel("09:00");
		lblNove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResiQuartHora1.getText().equals("")) {
					lblNove.setBackground(Color.ORANGE);
					lblNove.setOpaque(true);
					lblHora.setText("09:00");
				}else if(txtResiQuartHora1.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblNove.setBackground(Color.ORANGE);
						lblNove.setOpaque(true);
						lblHora.setText("09:00");
					}
				}
			}
		});
		lblNove.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNove.setBounds(29, 164, 46, 14);
		contentPane.add(lblNove);

		lblDez = new JLabel("10:00");
		lblDez.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi3.getText().equals("")) {
					lblDez.setBackground(Color.ORANGE);
					lblDez.setOpaque(true);
					lblHora.setText("10:00");
				}else if(txtResi3.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblDez.setBackground(Color.ORANGE);
						lblDez.setOpaque(true);
						lblHora.setText("10:00");
					}
				}
			}
		});
		lblDez.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDez.setBounds(29, 224, 46, 14);
		contentPane.add(lblDez);

		lblQuatorze = new JLabel("14:00");
		lblQuatorze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi7.getText().equals("")) {
					lblQuatorze.setBackground(Color.ORANGE);
					lblQuatorze.setOpaque(true);
					lblHora.setText("14:00");
				}else if(txtResi7.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblQuatorze.setBackground(Color.ORANGE);
						lblQuatorze.setOpaque(true);
						lblHora.setText("14:00");
					}
				}
			}
		});
		lblQuatorze.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblQuatorze.setBounds(29, 333, 46, 14);
		contentPane.add(lblQuatorze);

		lblQuinze = new JLabel("15:00");
		lblQuinze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi8.getText().equals("")) {
					lblQuinze.setBackground(Color.ORANGE);
					lblQuinze.setOpaque(true);
					lblHora.setText("15:00");
				}else if(txtResi8.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblQuinze.setBackground(Color.ORANGE);
						lblQuinze.setOpaque(true);
						lblHora.setText("15:00");
					}
				}
			}
		});
		lblQuinze.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblQuinze.setBounds(29, 396, 46, 14);
		contentPane.add(lblQuinze);

		lblNoveMeia = new JLabel("09:30");
		lblNoveMeia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi2.getText().equals("")) {
					lblNoveMeia.setBackground(Color.ORANGE);
					lblNoveMeia.setOpaque(true);
					lblHora.setText("09:30");
				}else if(txtResi2.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblNoveMeia.setBackground(Color.ORANGE);
						lblNoveMeia.setOpaque(true);
						lblHora.setText("09:30");
					}
				}
			}
		});
		lblNoveMeia.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblNoveMeia.setBounds(29, 193, 46, 14);
		contentPane.add(lblNoveMeia);

		lblDezesseis = new JLabel("16:00");
		lblDezesseis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtResi10.getText().equals("")) {
					lblDezesseis.setBackground(Color.ORANGE);
					lblDezesseis.setOpaque(true);
					lblHora.setText("16:00");
				}else if(txtResi10.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblDezesseis.setBackground(Color.ORANGE);
						lblDezesseis.setOpaque(true);
						lblHora.setText("16:00");
					}
				}
			}
		});
		lblDezesseis.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDezesseis.setBounds(29, 454, 46, 14);
		contentPane.add(lblDezesseis);

		lblDezMeia = new JLabel("10:30");
		lblDezMeia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 if(txtResi4.getText().equals("")) {
					lblDezMeia.setBackground(Color.ORANGE);
					lblDezMeia.setOpaque(true);
					lblHora.setText("10:30");
				}else if(txtResi4.getText() != null) {
					int botao = JOptionPane.showConfirmDialog(null,
							"J� existe um agendamento nesta data e hor�rio"
							+ ", deseja fazer altera��es?", "CONFIRMAR EXCLUS�O",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (botao == 0) {
						lblDezMeia.setBackground(Color.ORANGE);
						lblDezMeia.setOpaque(true);
						lblHora.setText("10:30");
					}
				}

			}
		});
		lblDezMeia.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDezMeia.setBounds(29, 261, 46, 14);
		contentPane.add(lblDezMeia);

		lblimg = new JLabel("New label");

		lblimg.setBounds(0, 151, 706, 340);
		contentPane.add(lblimg);
		// lblimg.setIcon(new ImageIcon(Menu.class.getResource("/Images/agenda.png")));
		ImageIcon fotoP = new ImageIcon(getClass().getResource("/Images/agenda.png"));
		lblimg.setIcon(util.ImageUtil.redimensiona(fotoP, lblimg.getWidth(), lblimg.getHeight()));

		final JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 0, 205, 153);
		contentPane.add(calendar);

		cbResi = new JComboBox();
		cbResi.setBounds(217, 1, 177, 22);
		contentPane.add(cbResi);

		btnBuscaVisi = new JButton("Buscar");
		btnBuscaVisi.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnBuscaVisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// txtInvisivel.setText(cbResi.getSelectedItem().toString());
					// visitantes = daoV.listar(cbResi.getSelectedItem().toString());
					if (cbResi.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "informe um Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					} else {
						List<Visitante> lista = daoVV.listar(cbResi.getSelectedItem().toString());

						for (Visitante func : lista) {
							cbV1.setSelectedIndex(-1);
							cbV2.setSelectedIndex(-1);
							cbV3.setSelectedIndex(-1);
							cbV4.setSelectedIndex(-1);
							cbV1.addItem(func.getNome().toString());
							cbV2.addItem(func.getNome());
							cbV3.addItem(func.getNome());
							cbV4.addItem(func.getNome());
							// criarTabelaV();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscaVisi.setBounds(215, 28, 179, 23);
		contentPane.add(btnBuscaVisi);

		cbV1 = new JComboBox();
		cbV1.setBounds(217, 62, 177, 22);
		contentPane.add(cbV1);

		cbV2 = new JComboBox();
		cbV2.setBounds(217, 91, 177, 22);
		contentPane.add(cbV2);

		btnMais = new JButton("+");
		btnMais.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnMais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbV3.setVisible(true);
				cbV4.setVisible(true);
			}
		});
		btnMais.setBounds(269, 124, 65, 20);
		contentPane.add(btnMais);

		cbV3 = new JComboBox();
		cbV3.setBounds(404, 0, 172, 22);
		contentPane.add(cbV3);

		cbV4 = new JComboBox();
		cbV4.setBounds(404, 29, 172, 22);
		contentPane.add(cbV4);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(404, 62, 177, 79);
		contentPane.add(scrollPane);

		taObs = new JTextArea();
		taObs.setText("Observa\u00E7\u00E3o");
		scrollPane.setViewportView(taObs);

		btnSalvar = new JButton("Agendar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblHora.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "informe a Hora", "Aviso", JOptionPane.ERROR_MESSAGE);
				} else if (cbResi.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe o Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbResi.requestFocus();
				} else if (cbV1.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe pelo menos um visitante", "Aviso",
							JOptionPane.ERROR_MESSAGE);
					cbV1.requestFocus();
					// arrumar
				}else if (visita == null) {
					visita = new Visita();
					visita.setHora(lblHora.getText().trim());
					visita.setData(lblData.getText());
					visita.setVisitante1(cbV1.getSelectedItem().toString());
					visita.setVisitante2(cbV2.getSelectedItem() + "");
					visita.setVisitante3(cbV3.getSelectedItem() + "");
					visita.setVisitante4(cbV4.getSelectedItem() + "");
					visita.setObservacao(taObs.getText());
					visita.setNomeResi(cbResi.getSelectedItem().toString());

					String s = "<html> <head> <title> <center> <h1>Confirma��o de visita</h1></center> </title></head><br> <body> "
							+ "<h2>- Ol� " + cbV1.getSelectedItem().toString() + " </h2> <br>" + "Sua visita para "
							+ cbResi.getSelectedItem().toString() + ",  esta agendada para " + lblData.getText() + " �s " + lblHora.getText()
							+ "<br> \r\n" + " -�. <br> \r\n"
							+ " - � obrigat�rio o uso de avental fornecido pela .<br> \r\n"
							+ " - � obrigat�rio o uso de touca fornecida pela .<br> \r\n"
							+ "- A visita ter� uma dura��o de 30 minutos.<br> \r\n"
							+ "- A visita ser� acompanhada. <br>" + "</body> </html>";

					JFrame jframe = new JFrame();
					final JLabel jLabel = new JLabel();
					final JLabel jLabell = new JLabel();
					// jframe.getContentPane().setLayout(new FlowLayout(700));
					jframe.setBounds(500, 300, 400, 100);
					jframe.setResizable(false);

					jLabell.setIcon(new ImageIcon(CadVisita.class.getResource("/Images/logoo.png")));
					jframe.setSize(600, 350);
					jLabell.setText(s);
					jframe.getContentPane().add(jLabel);
					jframe.getContentPane().add(jLabell);
					jframe.setVisible(true);

					try {
						if (visita.getId() == 0) {
							daoV.inserir(visita);
						} else {
							daoV.atualizar(visita);
						}
						limpar();
						visitas = daoV.listar();
						// criarTabela();
						if (insertUnico == true) {
							dispose();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnSalvar.setBounds(591, 11, 99, 23);
		contentPane.add(btnSalvar);

		btnExcluir = new JButton("Relat\u00F3rios");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rela frame = new Rela();
				frame.setVisible(true);
			}
		});
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 10));
		btnExcluir.setBounds(591, 62, 99, 23);
		contentPane.add(btnExcluir);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limpar();
			}
		});
		btnLimpar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnLimpar.setBounds(591, 117, 99, 23);
		contentPane.add(btnLimpar);

		lblHora = new JLabel("New label");
		lblHora.setBounds(348, 126, 46, 14);
		lblHora.setVisible(false);
		contentPane.add(lblHora);
		cbV3.setVisible(false);
		cbV4.setVisible(false);

		calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				limpar2();
				limparCores();
				// TODO Auto-generated method stub
				lblData.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getDate()));
				try {
					List<Visita> listaa = visitas = daoV.buscaData(lblData.getText().trim());
					for (Visita func : listaa) {
						if (func.getHora().equals("09:00")) {
							txtResiQuartHora1.setText(func.getNomeResi());
							txtVisitantes1.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
						if(txtResiQuartHora1.getText() != null) {
							lblNove.setBackground(Color.YELLOW);
							lblNove.setOpaque(true);
						}
						} else if (func.getHora().equals("09:30")) {
							txtResi2.setText(func.getNomeResi());
							txtVisitantes2.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi2.getText() != null) {
								lblNoveMeia.setBackground(Color.YELLOW);
								lblNoveMeia.setOpaque(true);
							}
						} else if (func.getHora().equals("10:00")) {
							txtResi3.setText(func.getNomeResi());
							txtvisitantes3.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi4.getText() != null) {
								lblDez.setBackground(Color.YELLOW);
								lblDez.setOpaque(true);
							}
						} else if (func.getHora().equals("10:30")) {
							txtResi4.setText(func.getNomeResi());
							txtVisitantes4.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi4.getText() != null) {
								lblDezMeia.setBackground(Color.YELLOW);
								lblDezMeia.setOpaque(true);
							}
						} else if (func.getHora().equals("11:00")) {
							txtResi5.setText(func.getNomeResi());
							txtVisitantes5.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi5.getText() != null) {
								lblOnze.setBackground(Color.YELLOW);
								lblOnze.setOpaque(true);
							}
						} else if (func.getHora().equals("14:30")) {
							txtResi6.setText(func.getNomeResi());
							txtVisitantes6.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi6.getText() != null) {
								lblOnzeMeia.setBackground(Color.YELLOW);
								lblOnzeMeia.setOpaque(true);
							}
						} else if (func.getHora().equals("14:00")) {
							txtResi7.setText(func.getNomeResi());
							txtVisitantes7.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi7.getText() != null) {
								lblQuatorze.setBackground(Color.YELLOW);
								lblQuatorze.setOpaque(true);
							}
						} else if (func.getHora().equals("15:00")) {
							txtResi8.setText(func.getNomeResi());
							txtVisitantes8.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi8.getText() != null) {
								lblQuinze.setBackground(Color.YELLOW);
								lblQuinze.setOpaque(true);
							}
						} else if (func.getHora().equals("15:30")) {
							txtResi9.setText(func.getNomeResi());
							txtVisitantes9.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi9.getText() != null) {
								lblQuinzeMeia.setBackground(Color.YELLOW);
								lblQuinzeMeia.setOpaque(true);
							}
						} else if (func.getHora().equals("16:00")) {
							txtResi10.setText(func.getNomeResi());
							txtVisitantes10.setText("Visitantes: " + func.getVisitante1() + " " + func.getVisitante2());
							if(txtResi10.getText() != null) {
								lblDezesseis.setBackground(Color.YELLOW);
								lblDezesseis.setOpaque(true);
							}
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

	}

	public void limpar() {
		limparCores();
	 visita = null;
		lblHora.setText(null);
		cbResi.setSelectedIndex(-1);
		cbV1.setSelectedIndex(-1);
		cbV2.setSelectedIndex(-1);
		cbV3.setSelectedIndex(-1);
		cbV4.setSelectedIndex(-1);
		taObs.setText(null);
		txtResiQuartHora1.setText(null);
		txtResi2.setText(null);
		txtResi3.setText(null);
		txtResi4.setText(null);
		txtResi5.setText(null);
		txtResi6.setText(null);
		txtResi7.setText(null);
		txtResi8.setText(null);
		txtResi9.setText(null);
		txtResi10.setText(null);

		txtVisitantes1.setText(null);
		txtVisitantes2.setText(null);
		txtvisitantes3.setText(null);
		txtVisitantes4.setText(null);
		txtVisitantes5.setText(null);
		txtVisitantes6.setText(null);
		txtVisitantes7.setText(null);
		txtVisitantes8.setText(null);
		txtVisitantes9.setText(null);
		txtVisitantes10.setText(null);

	}
	public void limparCores() {
		lblDez.setBackground(null);
		lblDez.setOpaque(false);
		lblDezMeia.setBackground(null);
		lblDezMeia.setOpaque(false);
		lblDezesseis.setBackground(null);
		lblDezesseis.setOpaque(false);
		lblNove.setBackground(null);
		lblNove.setOpaque(false);
		lblNoveMeia.setBackground(null);
		lblNoveMeia.setOpaque(false);
		lblOnze.setBackground(null);
		lblOnze.setOpaque(false);
		lblOnzeMeia.setBackground(null);
		lblOnzeMeia.setOpaque(false);
		lblQuatorze.setBackground(null);
		lblQuatorze.setOpaque(false);
		lblQuinze.setBackground(null);
		lblQuinze.setOpaque(false);
		lblQuinzeMeia.setBackground(null);
		lblQuinzeMeia.setOpaque(false);
		
	
	}

	public void limpar2() {

		txtResiQuartHora1.setText(null);
		txtResi2.setText(null);
		txtResi3.setText(null);
		txtResi4.setText(null);
		txtResi5.setText(null);
		txtResi6.setText(null);
		txtResi7.setText(null);
		txtResi8.setText(null);
		txtResi9.setText(null);
		txtResi10.setText(null);

		txtVisitantes1.setText(null);
		txtVisitantes2.setText(null);
		txtvisitantes3.setText(null);
		txtVisitantes4.setText(null);
		txtVisitantes5.setText(null);
		txtVisitantes6.setText(null);
		txtVisitantes7.setText(null);
		txtVisitantes8.setText(null);
		txtVisitantes9.setText(null);
		txtVisitantes10.setText(null);

	}

	public void crono() {
		JFrame jframe = new JFrame("Contagem regressiva da visita");
		final JLabel jLabel = new JLabel();
		jframe.getContentPane().setLayout(new FlowLayout());
		jframe.setBounds(500, 300, 400, 100);
		jframe.setResizable(false);

		jframe.getContentPane().add(jLabel);
		jframe.setVisible(true);

		final Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 1800;

			public void run() {

				jLabel.setText("Encerre a visita em: " + i);
				i--;

				if (i < 0) {
					timer.cancel();
					jLabel.setText("Encerre a visita");
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
		// clip.loop(Clip.LOOP_CONTINUOUSLY); // Toca continuamente (para o caso de
		// m�sicas)

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JOptionPane.showMessageDialog(null, "Clique pra fechar!");
			}
		});
	}

	private void popular() {
		// lblHora.setText(visita.getHora());
		// txtDia.setText(visita.getDia());
		lblData.setText(visita.getData());

		cbV1.setSelectedItem(visita.getVisitante1());
		cbV2.setSelectedItem(visita.getVisitante2());
		cbV3.setSelectedItem(visita.getVisitante3());
		cbV4.setSelectedItem(visita.getVisitante4());
		taObs.setText(visita.getObservacao());
		cbResi.setSelectedItem(visita.getNomeResi().toString());
		// lblId.setText("ID: " + visita.getId());
	}
}
