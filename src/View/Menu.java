package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JButton btnResi;
	private JButton btnVisi;
	private JButton btnVisitas;
	private JButton btnRela;
	private JButton btnIni;
	private JLabel lblImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		
		setBackground(new Color(102, 204, 102));
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 489);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnResi = new JButton("Cadastrar Residente");
		btnResi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadResidente frame = new CadResidente();
				frame.setVisible(true);
			}
		});
		btnResi.setBackground(SystemColor.inactiveCaption);
		btnResi.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnResi.setBounds(21, 89, 204, 123);
		contentPane.add(btnResi);
		
		btnVisi = new JButton("Cadastrar Visitante");
		btnVisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadVisitante frame = new CadVisitante();
				frame.setVisible(true);
			}
		});
		btnVisi.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnVisi.setBackground(SystemColor.inactiveCaption);
		btnVisi.setBounds(253, 89, 204, 123);
		contentPane.add(btnVisi);
		
		btnVisitas = new JButton("Agendar Visitas");
		btnVisitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teste frame = new Teste();
				frame.setVisible(true);
			}
		});
		btnVisitas.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnVisitas.setBackground(SystemColor.inactiveCaption);
		btnVisitas.setBounds(483, 89, 204, 123);
		contentPane.add(btnVisitas);
		
		btnRela = new JButton("Relat\u00F3rios");
		btnRela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rela frame = new Rela();
				frame.setVisible(true);
			}
		});
		btnRela.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnRela.setBackground(SystemColor.inactiveCaption);
		btnRela.setBounds(21, 223, 204, 123);
		contentPane.add(btnRela);
		
		btnIni = new JButton("Iniciar uma visita");
		btnIni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contagem frame = new Contagem();
				frame.setVisible(true);
			}
		});
		btnIni.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnIni.setBackground(SystemColor.inactiveCaption);
		btnIni.setBounds(253, 223, 204, 123);
		contentPane.add(btnIni);
		
		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(Menu.class.getResource("/Images/calendar.jpg")));
		lblImg.setBounds(0, 0, 731, 450);
		
		
		contentPane.add(lblImg);
		
		
	}
}
