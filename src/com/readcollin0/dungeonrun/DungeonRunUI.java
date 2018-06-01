package com.readcollin0.dungeonrun;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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
import com.readcollin0.dungeonrun.event.view.CanvasEvent;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateBottomEvent;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateFinishEvent;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateMiddleEvent;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateStartEvent;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateTopEvent;
import com.readcollin0.util.eventbus.Event;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class DungeonRunUI {

	public HashMap<String, JButton> buttons = new HashMap<String, JButton>();
	public JProgressBar progressBar;
	public JLabel lblMessage;
	public Canvas canvas;
	
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
		DungeonRun.EVENT_BUS.subscribeObject(this);
	}
	
	void start() {
		this.frmDungeonRun.setVisible(true);
	}
	
	@SubscribeEvent
	public void onEvent(Event e) {
		if (e instanceof CanvasEvent) return;
		
		updateCanvas();
	}
	
	public void updateCanvas() {
		Graphics g = canvas.getGraphics();
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		DungeonRun.EVENT_BUS.fire(new CanvasUpdateStartEvent(canvas));
		DungeonRun.EVENT_BUS.fire(new CanvasUpdateBottomEvent(canvas));
		DungeonRun.EVENT_BUS.fire(new CanvasUpdateMiddleEvent(canvas));
		DungeonRun.EVENT_BUS.fire(new CanvasUpdateTopEvent(canvas));
		DungeonRun.EVENT_BUS.fire(new CanvasUpdateFinishEvent(canvas));
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
		
		canvas = new Canvas();
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
			}
		});
		buttons.get(name).setBounds(x, y, width, height);
		frmDungeonRun.getContentPane().add(buttons.get(name));
	}
}
