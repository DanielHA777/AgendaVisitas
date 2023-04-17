package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.ConnectionFactory;
import DAO.DAOResidente;
import DAO.DAOVisitante;
import DAO.DAOVisitas;
import Model.JasperService;
import Model.Meses;
import Model.Residente;

import Model.Visita;
import Model.Visitante;
import TableModel.VTableModel;
import TableModel.VisitanteTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class CadVisita extends JFrame {

	// private PainelPizza contentPane;
	private JLabel lblId;
	private JPanel contentPane;
	private boolean insertUnico = false;
	private JComboBox cbResi;
	JFrame confi = new JFrame();
	private Connection conexao;
	private JTextField txtHora;
	private JScrollPane scrollPane;
	private JTable tbAgenda;
	private JComboBox cbMes;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private DAOVisitas daoVi;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	private JLabel lblAtraso;
	private JButton b10;
	private JButton b11;
	private JButton b12;
	private JButton b13;
	private JButton b14;
	private JButton btnNewButton_2_2;
	private JButton b16;
	private JButton b17;
	private JButton b18;
	private JButton b19;
	private JButton b20;
	private JButton b21;
	private JButton b22;
	private JButton b23;
	private JButton b24;
	// private List<Visitante> visitantes;
	private Visitante visitante;
	private JButton b25;
	private JButton b26;
	private DAOResidente daoR;
	private DAOVisitante daoV;
	private JButton b27;
	private JButton b28;
	private JButton b29;
	private JButton b30;
	private JButton b31;
	private JButton btnNewButton;
	private JComboBox cbV1;
	private JComboBox cbV2;
	private JComboBox cbV3;
	private JComboBox cbV4;
	private JButton btnMais;
	private List<Visita> visitas;
	private JLabel lblV3;
	private JLabel lblV4;
	private Visita visita;
	private JButton btnAgendar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTextField txtData;
	private JTextField txtDia;
	private JPanel contentPanee;
	private JTextArea taObs;
	private JButton btnLimpar;
	private JButton btnAviso;
	private JTextField txtData2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVisita frame = new CadVisita();
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
	public CadVisita() {
		setResizable(false);
		daoR = new DAOResidente();
		daoV = new DAOVisitante();
		daoVi = new DAOVisitas();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("\\eclipse-workspace\\Visitas\\src\\Images\\agenda.png"));
		setBounds(100, 100, 929, 494);
		// contentPane = new PainelPizza();
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblId = new JLabel("ID");
		lblId.setBounds(10, 11, 46, 14);
		contentPane.add(lblId);

		cbResi = new JComboBox();
		cbResi.setBounds(475, 46, 224, 22);
		contentPane.add(cbResi);

		JLabel lblNewLabel = new JLabel("Residente");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel.setBounds(367, 47, 98, 14);
		contentPane.add(lblNewLabel);

		cbMes = new JComboBox();
		cbMes.setFont(new Font("Arial Black", Font.PLAIN, 11));
		cbMes.setModel(new DefaultComboBoxModel(Meses.values()));

		cbMes.setBounds(45, 44, 114, 22);
		contentPane.add(cbMes);

		txtHora = new JTextField();
		txtHora.setBounds(252, 44, 105, 20);
		contentPane.add(txtHora);
		txtHora.setColumns(10);

		b1 = new JButton("1");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("01 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("01");
				}
			}
		});
		b1.setForeground(Color.RED);
		b1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b1.setBackground(Color.WHITE);
		b1.setBounds(45, 91, 46, 23);
		contentPane.add(b1);

		b2 = new JButton("2");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("02 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("02");
				}
			}
		});
		b2.setForeground(Color.RED);
		b2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b2.setBackground(Color.WHITE);
		b2.setBounds(91, 91, 46, 23);
		contentPane.add(b2);

		b3 = new JButton("3");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("03 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("03");
				}
			}
		});
		b3.setForeground(Color.RED);
		b3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b3.setBackground(Color.WHITE);
		b3.setBounds(131, 91, 46, 23);
		contentPane.add(b3);

		b4 = new JButton("4");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("04 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("04");
				}
			}
		});
		b4.setForeground(Color.RED);
		b4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b4.setBackground(Color.WHITE);
		b4.setBounds(179, 91, 46, 23);
		contentPane.add(b4);

		b5 = new JButton("5");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("05 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("05");
				}
			}
		});
		b5.setForeground(Color.RED);
		b5.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b5.setBackground(Color.WHITE);
		b5.setBounds(223, 91, 46, 23);
		contentPane.add(b5);

		b6 = new JButton("6");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("06 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("06");
				}
			}
		});
		b6.setForeground(Color.RED);
		b6.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b6.setBackground(Color.WHITE);
		b6.setBounds(263, 91, 46, 23);
		contentPane.add(b6);

		b7 = new JButton("7");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("07 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("07");
				}
			}
		});
		b7.setForeground(Color.RED);
		b7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b7.setBackground(Color.WHITE);
		b7.setBounds(311, 91, 46, 23);
		contentPane.add(b7);

		b8 = new JButton("8");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("08 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("08");
				}
			}
		});
		
		txtData2 = new JTextField();
		txtData2.setBounds(754, 16, 86, 20);
		contentPane.add(txtData2);
		txtData2.setColumns(10);
		
		b8.setForeground(Color.RED);
		b8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b8.setBackground(Color.WHITE);
		b8.setBounds(45, 116, 46, 23);
		contentPane.add(b8);

		b9 = new JButton("9");
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("09 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("09");
				}
			}
		});
		b9.setForeground(Color.RED);
		b9.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b9.setBackground(Color.WHITE);
		b9.setBounds(91, 116, 46, 23);
		contentPane.add(b9);

		b10 = new JButton("10");
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("10 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("10");
				}
			}
		});
		b10.setForeground(Color.RED);
		b10.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b10.setBackground(Color.WHITE);
		b10.setBounds(131, 116, 46, 23);
		contentPane.add(b10);

		b11 = new JButton("11");
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("11 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("11");
				}
			}
		});
		b11.setForeground(Color.RED);
		b11.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b11.setBackground(Color.WHITE);
		b11.setBounds(179, 116, 46, 23);
		contentPane.add(b11);

		b12 = new JButton("12");
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("12 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("12");
				}
			}
		});
		b12.setForeground(Color.RED);
		b12.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b12.setBackground(Color.WHITE);
		b12.setBounds(223, 116, 46, 23);
		contentPane.add(b12);

		b13 = new JButton("13");
		b13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("13 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("13");
				}
			}
		});
		b13.setForeground(Color.RED);
		b13.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b13.setBackground(Color.WHITE);
		b13.setBounds(263, 116, 46, 23);
		contentPane.add(b13);

		b14 = new JButton("14");
		b14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("14 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("14");
				}
			}
		});
		b14.setForeground(Color.RED);
		b14.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b14.setBackground(Color.WHITE);
		b14.setBounds(311, 116, 46, 23);
		contentPane.add(b14);

		btnNewButton_2_2 = new JButton("15");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("15 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("15");
				}
			}
		});
		btnNewButton_2_2.setForeground(Color.RED);
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_2_2.setBackground(Color.WHITE);
		btnNewButton_2_2.setBounds(45, 139, 46, 23);
		contentPane.add(btnNewButton_2_2);

		b16 = new JButton("16");
		b16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("16 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("16");
				}
			}
		});
		b16.setForeground(Color.RED);
		b16.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b16.setBackground(Color.WHITE);
		b16.setBounds(91, 139, 46, 23);
		contentPane.add(b16);

		b17 = new JButton("17");
		b17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("17 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("17");
				}
			}
		});
		b17.setForeground(Color.RED);
		b17.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b17.setBackground(Color.WHITE);
		b17.setBounds(131, 139, 46, 23);
		contentPane.add(b17);

		b18 = new JButton("18");
		b18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("18 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("18");
				}
			}
		});
		b18.setForeground(Color.RED);
		b18.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b18.setBackground(Color.WHITE);
		b18.setBounds(179, 139, 46, 23);
		contentPane.add(b18);

		b19 = new JButton("19");
		b19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("19 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("19");
				}
			}
		});
		b19.setForeground(Color.RED);
		b19.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b19.setBackground(Color.WHITE);
		b19.setBounds(223, 139, 46, 23);
		contentPane.add(b19);

		b20 = new JButton("20");
		b20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("20 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("20");
				}
			}
		});
		b20.setForeground(Color.RED);
		b20.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b20.setBackground(Color.WHITE);
		b20.setBounds(263, 139, 46, 23);
		contentPane.add(b20);

		b21 = new JButton("21");
		b21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("21 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("21");
				}
			}
		});
		b21.setForeground(Color.RED);
		b21.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b21.setBackground(Color.WHITE);
		b21.setBounds(311, 139, 46, 23);
		contentPane.add(b21);

		b22 = new JButton("22");
		b22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("22/01/2022");
					txtDia.setText("22");
				}
			}
		});
		b22.setForeground(Color.RED);
		b22.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b22.setBackground(Color.WHITE);
		b22.setBounds(45, 167, 46, 23);
		contentPane.add(b22);

		b23 = new JButton("23");
		b23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("23 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("23");
				}
			}
		});
		b23.setForeground(Color.RED);
		b23.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b23.setBackground(Color.WHITE);
		b23.setBounds(91, 167, 46, 23);
		contentPane.add(b23);

		b24 = new JButton("24");
		b24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("24 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("24");
				}
			}
		});
		b24.setForeground(Color.RED);
		b24.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b24.setBackground(Color.WHITE);
		b24.setBounds(131, 167, 46, 23);
		contentPane.add(b24);

		b25 = new JButton("25");
		b25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("25 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("25");
				}
			}
		});
		b25.setForeground(Color.RED);
		b25.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b25.setBackground(Color.WHITE);
		b25.setBounds(179, 167, 46, 23);
		contentPane.add(b25);

		b26 = new JButton("26");
		b26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("26 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("26");
				}
			}
		});
		b26.setForeground(Color.RED);
		b26.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b26.setBackground(Color.WHITE);
		b26.setBounds(223, 167, 46, 23);
		contentPane.add(b26);

		b27 = new JButton("27");
		b27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("27 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("27");
				}
			}
		});
		b27.setForeground(Color.RED);
		b27.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b27.setBackground(Color.WHITE);
		b27.setBounds(263, 167, 46, 23);
		contentPane.add(b27);

		b28 = new JButton("28");
		b28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("28 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("28");
				}
			}
		});
		b28.setForeground(Color.RED);
		b28.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b28.setBackground(Color.WHITE);
		b28.setBounds(311, 167, 46, 23);
		contentPane.add(b28);

		b29 = new JButton("29");
		b29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("29 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("29");
				}
			}
		});
		b29.setForeground(Color.RED);
		b29.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b29.setBackground(Color.WHITE);
		b29.setBounds(45, 193, 46, 23);
		contentPane.add(b29);

		b30 = new JButton("30");
		b30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("30 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("30");
				}
			}
		});
		b30.setForeground(Color.RED);
		b30.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b30.setBackground(Color.WHITE);
		b30.setBounds(91, 193, 46, 23);
		contentPane.add(b30);

		b31 = new JButton("31");
		b31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMes.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "informe um m�s primeiro", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} else {
					txtData.setText("31 de " + cbMes.getSelectedItem().toString() + " de 2022 �s " + txtHora.getText());
					txtDia.setText("31");
				}
			}
		});
		b31.setForeground(Color.RED);
		b31.setFont(new Font("Tahoma", Font.PLAIN, 9));
		b31.setBackground(Color.WHITE);
		b31.setBounds(131, 193, 46, 23);
		contentPane.add(b31);

		cbV1 = new JComboBox();
		cbV1.setBounds(475, 92, 224, 22);
		contentPane.add(cbV1);

		JLabel lblVisitante = new JLabel("Visitante 1");
		lblVisitante.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVisitante.setBounds(367, 95, 98, 14);
		contentPane.add(lblVisitante);

		JLabel lblVisitante_3 = new JLabel("Visitante 2");
		lblVisitante_3.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVisitante_3.setBounds(367, 120, 98, 14);
		contentPane.add(lblVisitante_3);

		cbV2 = new JComboBox();
		cbV2.setBounds(475, 117, 224, 22);
		contentPane.add(cbV2);

		lblV3 = new JLabel("Visitante 3");
		lblV3.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblV3.setBounds(367, 171, 98, 19);
		contentPane.add(lblV3);

		cbV3 = new JComboBox();
		cbV3.setBounds(475, 168, 224, 22);
		contentPane.add(cbV3);

		lblV4 = new JLabel("Visitante 4");
		lblV4.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblV4.setBounds(367, 197, 98, 14);
		contentPane.add(lblV4);

		cbV4 = new JComboBox();
		cbV4.setBounds(475, 194, 224, 22);
		contentPane.add(cbV4);

		lblV3.setVisible(false);
		lblV4.setVisible(false);
		cbV3.setVisible(false);
		cbV4.setVisible(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 226, 860, 184);
		contentPane.add(scrollPane);

		tbAgenda = new JTable();
		scrollPane.setViewportView(tbAgenda);

		btnNewButton = new JButton("Buscar Familiares");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// txtInvisivel.setText(cbResi.getSelectedItem().toString());
					// visitantes = daoV.listar(cbResi.getSelectedItem().toString());
					if (cbResi.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "informe um Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					} else {
						List<Visitante> lista = daoV.listar(cbResi.getSelectedItem().toString());
						List<Visita> listaa = visitas = daoVi.buscaData(txtData.getText());
						for (Visita func : listaa) {
							//cbData.setSelectedIndex(-1);
							txtData2.setText(func.getData());
							//cbData.addItem(func.getData().toString());
						}
						// visitantes = daoV.buscaVisi();
						for (Visitante func : lista) {
							cbV1.setSelectedIndex(-1);
							cbV2.setSelectedIndex(-1);
							cbV3.setSelectedIndex(-1);
							cbV4.setSelectedIndex(-1);
							cbV1.addItem(func.getNome());
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
		btnNewButton.setBounds(475, 68, 224, 23);
		contentPane.add(btnNewButton);

		btnMais = new JButton("+");
		btnMais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblV3.setVisible(true);
				lblV4.setVisible(true);
				cbV3.setVisible(true);
				cbV4.setVisible(true);
			}

		});
		btnMais.setBounds(566, 143, 46, 14);
		contentPane.add(btnMais);

		btnAviso = new JButton("");
		btnAviso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAviso.setVisible(false);
			}
		});
		btnAviso.setIcon(new ImageIcon(CadVisita.class.getResource("/Images/logoo.png")));
		btnAviso.setBackground(new Color(245, 255, 250));
		btnAviso.setBounds(262, 10, 615, 386);
		btnAviso.setVisible(false);
		contentPane.add(btnAviso);
        txtData2.setText(null);
        txtData2.setVisible(false);
		btnAgendar = new JButton("Agendar");
		btnAgendar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtHora.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "informe a Hora", "Aviso", JOptionPane.ERROR_MESSAGE);
					txtHora.requestFocus();
				} else if (cbResi.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe o Residente", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbResi.requestFocus();
				} else if (cbV1.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe pelo menos um visitante", "Aviso",
							JOptionPane.ERROR_MESSAGE);
					cbV1.requestFocus();
				} else if (cbMes.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "informe o m�s", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();
				} /*else if (!txtData.getText().isEmpty() && !txtData2.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "J� existe um agendamento para essa data e hor�rio", "Aviso", JOptionPane.ERROR_MESSAGE);
					cbMes.requestFocus();			
				}*/
				else if(visita == null) {
					visita = new Visita();
					
				}
				visita.setMeses((Meses) cbMes.getSelectedItem());
				visita.setHora(txtHora.getText().trim());
				//visita.setDia(txtDia.getText());
				visita.setData(txtData.getText());
				visita.setVisitante1(cbV1.getSelectedItem().toString());
				visita.setVisitante2(cbV2.getSelectedItem() + "");
				visita.setVisitante3(cbV3.getSelectedItem() + "");
				visita.setVisitante4(cbV4.getSelectedItem() + "");
				visita.setObservacao(taObs.getText());
				visita.setNomeResi(cbResi.getSelectedItem().toString());
				
				try {
				
					if (visita.getId() == 0) {
						daoVi.inserir(visita);
					}else{
						daoVi.atualizar(visita);
					}

					String s = "<html> <head> <title> <center> <h1>Confirma��o de visita</h1></center> </title></head><br> <body> "
							+ "<h2>- Ol� " + cbV1.getSelectedItem().toString() + " </h2> <br>" + "Sua visita para "
							+ cbResi.getSelectedItem().toString() + ",  esta agendada para " + txtData.getText()
							+ "<br> \r\n" + " -� obrigat�rio o uso de m�scara. <br> \r\n"
							+ " - � obrigat�rio o uso de avental fornecido.<br> \r\n"
							+ " - � obrigat�rio o uso de touca fornecida.<br> \r\n"
							+ "- A visita ter� uma dura��o de 30 minutos.<br> \r\n"
							+ "- A visita ser� acompanhada(a dist�ncia). <br>"
					// + "<img src=/Images/logoo.png width=\"300\"/> "

							+ "</body> </html>";

					btnAviso.setText(s);
					btnAviso.setVisible(true);

					limpar();
					visitas = daoVi.listar();
					criarTabela();
					if (insertUnico == true) {
						dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}}

			

		});
		btnAgendar.setBounds(48, 421, 89, 23);
		contentPane.add(btnAgendar);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHora.setBounds(190, 44, 70, 14);
		contentPane.add(lblHora);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setEnabled(false);
		txtData.setBounds(330, 8, 261, 20);
		contentPane.add(txtData);
		txtData.setColumns(10);

		txtDia = new JTextField();
		txtDia.setEnabled(false);
		txtDia.setEditable(false);
		txtDia.setBounds(233, 8, 86, 20);
		contentPane.add(txtDia);
		txtDia.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(709, 68, 196, 115);
		contentPane.add(scrollPane_1);

		taObs = new JTextArea();
		scrollPane_1.setViewportView(taObs);

		JLabel lblObservao = new JLabel("Observacao");
		lblObservao.setHorizontalAlignment(SwingConstants.CENTER);
		lblObservao.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblObservao.setBounds(719, 47, 173, 14);
		contentPane.add(lblObservao);

		btnLimpar = new JButton("Limpar ");
		btnLimpar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnLimpar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(461, 421, 89, 23);
		contentPane.add(btnLimpar);
		txtDia.setVisible(false);

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
							daoVi.excluir(visita);
							visitas = daoVi.listar();
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
		btnExcluir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnExcluir.setBounds(179, 421, 89, 23);
		contentPane.add(btnExcluir);
		
		tbAgenda.setShowGrid(false);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("                                     Dias da semana");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(45, 77, 312, 14);
		lblNewLabel_1.setOpaque(true);
		contentPane.add(lblNewLabel_1);
		
		

		// txtDia.setVisible(false);

		try {
			visitas = daoVi.listar();
			criarTabela();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "n�o foi poss�vel carregar as visitas", "erro",
					JOptionPane.ERROR_MESSAGE);
		}
		setLocationRelativeTo(null);
		
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
	

	protected void abrirPontoJasper(String numero) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		JasperService service = new JasperService();
		service.addParams("BuscaData", cbMes.getSelectedIndex());
		service.addParams("BuscaDia", txtDia.getText());
		service.abrirPontoJasper("View\\visitas-" + numero + ".jasper",(com.mysql.jdbc.Connection) conexao);
		conexao.close();
	}
	protected void abrirPonto(String numero) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		JasperService service = new JasperService();
		service.addParams("BuscaData", cbMes.getSelectedIndex());
		service.addParams("BuscaDia", txtDia.getText());
		service.abrirPontoJasper("View\\visitas-" + numero + ".jasper",(com.mysql.jdbc.Connection) conexao);
		conexao.close();
	
	}

	private void criarTabela() {
		VTableModel model = new VTableModel(visitas);
		tbAgenda.setModel(model);
		tbAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbAgenda.getColumnModel().getColumn(0).setPreferredWidth(280);
		model.getLine(2);
		tbAgenda.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tbAgenda.getSelectedRow() >= 0) {
					visita = visitas.get(tbAgenda.getSelectedRow());
					popular();
				}
			}
		});
	}

	public void exportarPdf(String numero, String saida) throws SQLException {
		conexao =  ConnectionFactory.getConnection();
		JasperService service = new JasperService();
		//String numeroo = "01";
		service.exportarPdf("View/visitas-" + numero +".jasper",  (com.mysql.jdbc.Connection) conexao, saida);
		conexao.close();
		
		
	}
	public void GeraRela() {
		try {
			conexao = ConnectionFactory.getConnection();
			Map parametros = new HashMap();
			JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("\Documents\\Organizar\\visitas-03.jasper").getFile(), parametros, (com.mysql.jdbc.Connection) conexao);
					JasperViewer jw = new JasperViewer(jp);
			
			jw.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void popular() {
		txtHora.setText(visita.getHora());
		//txtDia.setText(visita.getDia());
		txtData.setText(visita.getData());
		cbMes.setSelectedItem(visita.getMeses());
		cbV1.setSelectedItem(visita.getVisitante1());
		cbV2.setSelectedItem(visita.getVisitante2());
		cbV3.setSelectedItem(visita.getVisitante3());
		cbV4.setSelectedItem(visita.getVisitante4());
		taObs.setText(visita.getObservacao());
		cbResi.setSelectedItem(visita.getNomeResi().toString());
		lblId.setText("ID: " + visita.getId());
	}

	private void limpar() {
		txtData.setText(null);
		cbResi.setSelectedIndex(-1);
		visitante = null;
		visita = null;
		txtDia.setText(null);
		txtHora.setText(null);
		cbMes.setSelectedIndex(-1);
		cbV1.setSelectedIndex(-1);
		cbV2.setSelectedIndex(-1);
		cbV3.setSelectedIndex(-1);
		cbV4.setSelectedIndex(-1);
		taObs.setText(null);
		cbV3.setVisible(false);
		cbV4.setVisible(false);
		

		// tbVisitante.clearSelection();
		lblId.setText("ID:");
	}
	
}
