package main;

import java.awt.Graphics;
import levels.*;
import entities.Player;
import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;
import static gamestates.GameState.*;
import static utiliz.Constants.Resolution.*;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel; private LevelManager levelManager;
	private Player player;
	private Thread gameThread;//new thread that runs the loop existing in the game calls repaint in true while

	private final int FPS_LIMIT=120;//maximum frame
	private final int UPS_LIMIT=120;

	private Menu menu;
	private Playing playing;
	public Game() {
		initClass();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);//it sets the window to focusing mode to enable taking input
		gamePanel.requestFocus();
		startGame();
		
	}

	private void initClass(){
		menu = new Menu(this);
		playing = new Playing(this);
	}

	public void startGame(){
		gameThread=new Thread(this);
		gameThread.start();
	}

	public void updateGame(){
		switch(GameState.currentState){
			case PLAYING:
				playing.update();
				break;

			case MENU:
				menu.update();
				break;

			default:
				break;
		}
	}

	public void render(Graphics g){
		switch(GameState.currentState){
			case PLAYING:
				playing.render(g);
				break;

			case MENU:
				menu.render(g);
				break;

			default:
				break;
		}
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

	public Menu getMenu(){
		return menu;
	}

	public Playing getPlaying(){
		return playing;
	}
}