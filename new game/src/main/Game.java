package main;

import java.awt.Graphics;
import levels.*;
import entities.Player;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Player player;
	private Thread gameThread;//new thread that runs the loop existing in the game calls repaint in true while
	private LevelManager levelManager;
	private final int FPS_LIMIT=120;//maximum frame
	private final int UPS_LIMIT=120;

	public Game() {
		levelManager = new LevelManager(this);
		player=new Player(200, 200);//location of player spawn
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);//it sets the window to focusing mode to enable taking input
		gamePanel.requestFocus();
		startGame();
		
	}

	public void startGame(){
		gameThread=new Thread(this);
		gameThread.start();
	}

	public void updateGame(){
		player.update();
	}

	public void render(Graphics g){
		levelManager.draw(g);
		player.render(g);
	}

	public void run(){
		double timePerFrame = 1000000000.0 / FPS_LIMIT;
		double timePerUpdate = 1000000000.0 / UPS_LIMIT;

		//unit of time measuring is nanosecond hence we use 1000000000.0

		int frames = 0, updates = 0 ;
		long lastCheck = System.currentTimeMillis();
		double deltaU = 0, deltaF = 0;
		long previousTime = System.nanoTime();

		while (true) {
			long currentTime = System.nanoTime();

			deltaU+=(currentTime-previousTime)/timePerUpdate;
			deltaF+=(currentTime-previousTime)/timePerFrame;
			previousTime=currentTime;

			if(deltaU>=1){
				updateGame();
				deltaU--;
				updates++;
			}

			if(deltaF>=1){
				gamePanel.repaint();
				deltaF--;
				frames++;
			}
			

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "| UPS: " + updates);
				frames = 0;
				updates = 0;
			}
			//fps displayer
		}
	}
	
	public void resetWhenFocusLost(){
		player.resetBooleans();
	}

	public Player getPlayer(){
		return player;
	}
}