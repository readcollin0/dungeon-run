package com.readcollin0.dungeonrun;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.MatteBorder;

import com.readcollin0.dungeonrun.event.button.ButtonClickEvent;
import com.readcollin0.dungeonrun.event.button.CanvasClickEvent;

public class DungeonRunUI {

	public HashMap<String, JButton> buttons = new HashMap<String, JButton>();
	public JProgressBar progressBar;
	public JLabel lblMessage;
	
	private JFrame frmDungeonRun;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DungeonRunUI window = new DungeonRunUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	DungeonRunUI() {
		initialize();
	}
	
	void start() {
		this.frmDungeonRun.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonRun = new JFrame();
		frmDungeonRun.setTitle("Dungeon Run");
		frmDungeonRun.setBounds(100, 100, 656, 518);
		frmDungeonRun.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonRun.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(335, 366, 295, 102);
		frmDungeonRun.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblHp = new JLabel("HP:");
		lblHp.setBounds(12, 12, 24, 16);
		panel.add(lblHp);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(40, 12, 243, 16);
		panel.add(progressBar);
		this.progressBar = progressBar;
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(12, 50, 271, 16);
		panel.add(lblMessage);
		
		Canvas canvas = new Canvas();
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DungeonRun.EVENT_BUS.fire(new CanvasClickEvent(e.getX(), e.getY()));
			}
		});
		canvas.setBackground(Color.ORANGE);
		canvas.setBounds(10, 10, 620, 350);
		frmDungeonRun.getContentPane().add(canvas);
		
		addButton("Attack", 10, 366, 313, 26);
		addButton("Special", 10, 404, 313, 26);
		addButton("Use Item", 10, 442, 313, 26);
		
	}
	
	private void addButton(String name, int x, int y, int width, int height) {
		addButton(name, name, x, y, width, height);
	}
	
	private void addButton(String name, String text, int x, int y, int width, int height) {
		buttons.put(name, new JButton(text));
		buttons.get(name).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DungeonRun.EVENT_BUS.fire(new ButtonClickEvent(buttons.get(name)));
				System.out.println("ButtonClicked " + name);
			}
		});
		buttons.get(name).setBounds(x, y, width, height);
		frmDungeonRun.getContentPane().add(buttons.get(name));
	}
}
