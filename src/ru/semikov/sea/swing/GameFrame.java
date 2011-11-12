package ru.semikov.sea.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameFrame extends JFrame {

	private final GameModel model;
	private final GameController controller;

	private JMenuItem mntmNewGame;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;

    private PanelFieldPlayer panelPlayerPlayer;
	private PanelFieldOpponent panelPlayerOpponent;
	private ScoreField panelScore;
	
	public GameFrame(GameModel model) {
		this.model = model;
		buildUI();
		this.model.addListener(panelPlayerPlayer);
		this.model.addListener(panelPlayerOpponent);
		this.model.addListener(panelScore);

		this.controller = new GameController(this, model);
		attachController();
	}

	/**
	 * ���������� ���������� ����� � ���������� �� �� ���������� 
	 */
	private void attachController() {
		mntmAbout.addActionListener(controller);
		mntmNewGame.addActionListener(controller);
		mntmExit.addActionListener(controller);
	}

	/**
	 * ���������� ���������� ������������
	 */
	private void buildUI() {
		this.setTitle("SeaBattle");
		this.setResizable(false);
		this.setBounds(400, 300, 483, 228);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

        panelPlayerPlayer = new PanelFieldPlayer(model.getPlayerFieldPlayer());
        panelPlayerPlayer.setBounds(20, 31, 151, 151);
		this.getContentPane().add(panelPlayerPlayer);

        panelPlayerOpponent = new PanelFieldOpponent(model.getPlayerFieldOpponent(), model);
        panelPlayerOpponent.setBounds(190, 31, 151, 151);
		this.getContentPane().add(panelPlayerOpponent);
		
		panelScore = new ScoreField(model);
		
		panelScore.setBounds(370, 31, 90, 151);
		panelScore.setBackground(new Color(225, 225, 255));
		this.getContentPane().add(panelScore);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 477, 21);
		this.getContentPane().add(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		mntmNewGame = new JMenuItem("New game");
		mnGame.add(mntmNewGame);
		
		mntmExit = new JMenuItem("Exit");
		mnGame.add(mntmExit);

		JMenu mnProperties = new JMenu("Properties");
		menuBar.add(mnProperties);

        mnProperties.add(new JMenuItem(new SetDimensionAction(5, 5, 2, model)));
        mnProperties.add(new JMenuItem(new SetDimensionAction(10, 10, 4, model)));
        mnProperties.add(new JMenuItem(new SetDimensionAction(15, 15, 6, model)));
        mnProperties.add(new JMenuItem(new SetDimensionAction(20, 20, 7, model)));
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}

}
