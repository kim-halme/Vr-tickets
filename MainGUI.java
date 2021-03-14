package kim.halme.vrliput;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainGUI{
	
	private Program prog;
	
	private JFrame frame;
	private JTextField tfTime;
	private JTextField tfDate;	
	private JPanel panelTime;
	private JButton btnDone;
	private JCheckBox chckbxSelectTrain;
	
	private JFrame frame2;
	
	private JFrame frame3;
	
	
	public MainGUI(Program prog) {
		this.prog = prog;
		initialize();
	}
	/**
	 * @wbp.parser.entryPoint
	 */

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 20));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 450, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnX = new JButton("X");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnX.setHorizontalAlignment(SwingConstants.RIGHT);
		btnX.setFont(new Font("Calibri", Font.BOLD, 16));
		btnX.setBounds(402, 0, 48, 25);
		btnX.setBorderPainted(false);
		btnX.setFocusPainted(false);
		btnX.setContentAreaFilled(false);
		panel.add(btnX);
		
		ButtonGroup buttons = new ButtonGroup();
		
		JRadioButton rdbtnTreHki = new JRadioButton("TRE --> HKI");
		rdbtnTreHki.setBackground(Color.WHITE);
		rdbtnTreHki.setSelected(true);
		rdbtnTreHki.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnTreHki.setBounds(30, 30, 125, 25);
		rdbtnTreHki.setFocusPainted(false);
		panel.add(rdbtnTreHki);
		
		buttons.add(rdbtnTreHki);
		
		JRadioButton rdbtnHkiTre = new JRadioButton("HKI --> TRE");
		rdbtnHkiTre.setBackground(Color.WHITE);
		rdbtnHkiTre.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnHkiTre.setBounds(30, 55, 125, 25);
		rdbtnHkiTre.setFocusPainted(false);
		panel.add(rdbtnHkiTre);
		
		buttons.add(rdbtnHkiTre);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblTime.setBackground(Color.WHITE);
		lblTime.setBounds(155, 30, 125, 25);
		panel.add(lblTime);
		
		tfTime = new JTextField();
		tfTime.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panelTime.setVisible(true);
				btnDone.setVisible(false);
				chckbxSelectTrain.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				panelTime.setVisible(false);
				btnDone.setVisible(true);
				chckbxSelectTrain.setVisible(true);
			}
		});
		tfTime.setText("0800");
		tfTime.setFont(new Font("Calibri", Font.PLAIN, 20));
		tfTime.setBounds(155, 55, 125, 25);
		panel.add(tfTime);
		tfTime.setColumns(1);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblDate.setBounds(280, 30, 125, 25);
		panel.add(lblDate);
		
		tfDate = new JTextField();
		tfDate.setText("01.01.2018");
		tfDate.setHorizontalAlignment(SwingConstants.LEFT);
		tfDate.setFont(new Font("Calibri", Font.PLAIN, 20));
		tfDate.setBounds(280, 55, 125, 25);
		panel.add(tfDate);
		tfDate.setColumns(1);
		
		chckbxSelectTrain = new JCheckBox("Do you want to automatically select the train?");
		chckbxSelectTrain.setFont(new Font("Calibri", Font.PLAIN, 16));
		chckbxSelectTrain.setBackground(Color.WHITE);
		chckbxSelectTrain.setBounds(30, 125, 350, 25);
		panel.add(chckbxSelectTrain);
		
		btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(rdbtnTreHki.isSelected()) {
					prog.setPlace("Tampere");
					prog.setDestination("Helsinki");
				}else {
					prog.setPlace("Helsinki");
					prog.setDestination("Tampere");
				}
				if(tfTime.getText().length() != 4) {
					tfTime.setText("0800");
					return;
				}
				if(tfDate.getText().length() != 10) {
					tfDate.setText("01.01.2018");
					return;
				}
				
				prog.setTime(tfTime.getText());
				prog.setDate(tfDate.getText());
				prog.setSelectTrain(chckbxSelectTrain.isSelected());
				frame.dispose();
				prog.runProgram();
			}
		});
		btnDone.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnDone.setBackground(Color.WHITE);
		btnDone.setBounds(125, 200, 200, 50);
		btnDone.setFocusPainted(false);
		panel.add(btnDone);
		
		panelTime = new JPanel();
		panelTime.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelTime.setBackground(Color.WHITE);
		panelTime.setBounds(155, 80, 250, 190);
		panelTime.setVisible(false);
		panel.add(panelTime);
		panelTime.setLayout(null);
		
		JPanel panelHours = new JPanel();
		panelHours.setBackground(Color.WHITE);
		panelHours.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelHours.setBounds(10, 10, 120, 170);
		panelTime.add(panelHours);
		panelHours.setLayout(null);
		
		JLabel lblHour1 = new JLabel("1");
		lblHour1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("01" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour1.setBounds(10, 10, 25, 25);
		panelHours.add(lblHour1);
		
		JLabel lblHour2 = new JLabel("2");
		lblHour2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("02" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour2.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour2.setBounds(35, 10, 25, 25);
		panelHours.add(lblHour2);
		
		JLabel lblHour3 = new JLabel("3");
		lblHour3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("03" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour3.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour3.setBounds(60, 10, 25, 25);
		panelHours.add(lblHour3);
		
		JLabel lblHour4 = new JLabel("4");
		lblHour4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("04" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour4.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour4.setBounds(85, 10, 25, 25);
		panelHours.add(lblHour4);
		
		JLabel lblHour5 = new JLabel("5");
		lblHour5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("05" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour5.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour5.setBounds(10, 35, 25, 25);
		panelHours.add(lblHour5);
		
		JLabel lblHour6 = new JLabel("6");
		lblHour6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("06" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour6.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour6.setBounds(35, 35, 25, 25);
		panelHours.add(lblHour6);
		
		JLabel lblHour7 = new JLabel("7");
		lblHour7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("07" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour7.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour7.setBounds(60, 35, 25, 25);
		panelHours.add(lblHour7);
		
		JLabel lblHour8 = new JLabel("8");
		lblHour8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("08" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour8.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour8.setBounds(85, 35, 25, 25);
		panelHours.add(lblHour8);
		
		JLabel lblHour9 = new JLabel("9");
		lblHour9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("09" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour9.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour9.setBounds(10, 60, 25, 25);
		panelHours.add(lblHour9);
		
		JLabel lblHour10 = new JLabel("10");
		lblHour10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("10" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour10.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour10.setBounds(35, 60, 25, 25);
		panelHours.add(lblHour10);
		
		JLabel lblHour11 = new JLabel("11");
		lblHour11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("11" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour11.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour11.setBounds(60, 60, 25, 25);
		panelHours.add(lblHour11);
		
		JLabel lblHour12 = new JLabel("12");
		lblHour12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("12" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour12.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour12.setBounds(85, 60, 25, 25);
		panelHours.add(lblHour12);
		
		JLabel lblHour13 = new JLabel("13");
		lblHour13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("13" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour13.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour13.setBounds(10, 85, 25, 25);
		panelHours.add(lblHour13);
		
		JLabel lblHour14 = new JLabel("14");
		lblHour14.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("14" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour14.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour14.setBounds(35, 85, 25, 25);
		panelHours.add(lblHour14);
		
		JLabel lblHour15 = new JLabel("15");
		lblHour15.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("15" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour15.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour15.setBounds(60, 85, 25, 25);
		panelHours.add(lblHour15);
		
		JLabel lblHour16 = new JLabel("16");
		lblHour16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("16" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour16.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour16.setBounds(85, 85, 25, 25);
		panelHours.add(lblHour16);
		
		JLabel lblHour17 = new JLabel("17");
		lblHour17.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("17" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour17.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour17.setBounds(10, 110, 25, 25);
		panelHours.add(lblHour17);
		
		JLabel lblHour18 = new JLabel("18");
		lblHour18.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("18" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour18.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour18.setBounds(35, 110, 25, 25);
		panelHours.add(lblHour18);
		
		JLabel lblHour19 = new JLabel("19");
		lblHour19.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("19" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour19.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour19.setBounds(60, 110, 25, 25);
		panelHours.add(lblHour19);
		
		JLabel lblHour20 = new JLabel("20");
		lblHour20.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("20" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour20.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour20.setBounds(85, 110, 25, 25);
		panelHours.add(lblHour20);
		
		JLabel lblHour21 = new JLabel("21");
		lblHour21.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("21" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour21.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour21.setBounds(10, 135, 25, 25);
		panelHours.add(lblHour21);
		
		JLabel lblHour22 = new JLabel("22");
		lblHour22.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("22" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour22.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour22.setBounds(35, 135, 25, 25);
		panelHours.add(lblHour22);
		
		JLabel lblHour23 = new JLabel("23");
		lblHour23.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("23" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour23.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour23.setBounds(60, 135, 25, 25);
		panelHours.add(lblHour23);
		
		JLabel lblHour0 = new JLabel("0");
		lblHour0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(tfTime.getText().length() > 3)
					tfTime.setText("00" + tfTime.getText().substring(2, 4));
			}
		});
		lblHour0.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour0.setBounds(85, 135, 25, 25);
		panelHours.add(lblHour0);
		
		JPanel panelMinutes = new JPanel();
		panelMinutes.setBackground(Color.WHITE);
		panelMinutes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelMinutes.setBounds(170, 10, 70, 170);
		panelTime.add(panelMinutes);
		panelMinutes.setLayout(null);
		
		JLabel lblMintute00 = new JLabel("00");
		lblMintute00.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfTime.setText(tfTime.getText().substring(0, 2) + "00");
			}
		});
		lblMintute00.setHorizontalAlignment(SwingConstants.CENTER);
		lblMintute00.setBounds(10, 10, 50, 25);
		panelMinutes.add(lblMintute00);
		
		JLabel lblMintute10 = new JLabel("10");
		lblMintute10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfTime.setText(tfTime.getText().substring(0, 2) + "10");
			}
		});
		lblMintute10.setHorizontalAlignment(SwingConstants.CENTER);
		lblMintute10.setBounds(10, 35, 50, 25);
		panelMinutes.add(lblMintute10);
		
		JLabel lblMintute20 = new JLabel("20");
		lblMintute20.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfTime.setText(tfTime.getText().substring(0, 2) + "20");
			}
		});
		lblMintute20.setHorizontalAlignment(SwingConstants.CENTER);
		lblMintute20.setBounds(10, 60, 50, 25);
		panelMinutes.add(lblMintute20);
		
		JLabel lblMintute30 = new JLabel("30");
		lblMintute30.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfTime.setText(tfTime.getText().substring(0, 2) + "30");
			}
		});
		lblMintute30.setHorizontalAlignment(SwingConstants.CENTER);
		lblMintute30.setBounds(10, 85, 50, 25);
		panelMinutes.add(lblMintute30);
		
		JLabel lblMintute40 = new JLabel("40");
		lblMintute40.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfTime.setText(tfTime.getText().substring(0, 2) + "40");
			}
		});
		lblMintute40.setHorizontalAlignment(SwingConstants.CENTER);
		lblMintute40.setBounds(10, 110, 50, 25);
		panelMinutes.add(lblMintute40);
		
		JLabel lblMintute50 = new JLabel("50");
		lblMintute50.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tfTime.setText(tfTime.getText().substring(0, 2) + "50");
			}
		});
		lblMintute50.setHorizontalAlignment(SwingConstants.CENTER);
		lblMintute50.setBounds(10, 135, 50, 25);
		panelMinutes.add(lblMintute50);
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		
		prog.setGUI(this);
	}
	
	public void drawFrame2() {
		frame2 = new JFrame();
		frame2.setBounds(100, 100, 450, 300);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setUndecorated(true);
		frame2.setAlwaysOnTop(true);
		frame2.getContentPane().setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(0, 0, 450, 300);
		frame2.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JButton btnReady2 = new JButton("Click this when you have selected the train!");
		btnReady2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame2.dispose();
				prog.continueProgram();
			}
		});
		btnReady2.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnReady2.setBounds(50, 75, 350, 50);
		panel2.add(btnReady2);
		
		frame2.setVisible(true);
	}
	

	
	public void drawFrame3() {
		frame3 = new JFrame();
		frame3.setBounds(100, 100, 450, 350);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setUndecorated(true);
		frame3.setAlwaysOnTop(true);
		frame3.getContentPane().setLayout(null);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		panel3.setBounds(0, 0, 450, 350);
		frame3.getContentPane().add(panel3);
		panel3.setLayout(null);
		
		JButton btnReady3 = new JButton("Click this when you have selected the seat.");
		btnReady3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame3.dispose();
				prog.continueProgram2();
			}
		});
		btnReady3.setBounds(50, 60, 352, 60);
		panel3.add(btnReady3);
		
		frame3.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
