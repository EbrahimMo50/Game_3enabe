package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import entities.Player;
import levels.LevelManager;
import main.Game;
import static gamestates.GameState.*;
import static utiliz.Constants.Resolution.*;

public class Playing extends States implements StateMethods{

    LevelManager lvlManager;
    Player player;

    //dash and double jump related
    private long dashTimeLeft;
    private long dashCDLeft;
    private long dashTimeRight;
    private long dashCDRight;
    private boolean spaceRelease=true;

    public Playing(Game game){
        super(game);
        initClass();
    }

    private void initClass() {
		lvlManager = new LevelManager(game);
		player=new Player(GAME_WIDTH/2, GAME_HEIGHT/2, (int)(SCALE*64), (int)(SCALE*40));//location of player spawn
		player.loadLvlData(lvlManager.getLevel().getLvldata());
    }

    @Override
    public void update() {
        player.update();
        lvlManager.update();
    }

    @Override
    public void render(Graphics g) {
		//player.drawBackground(g);
		lvlManager.draw(g);
		player.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
            player.setRight(true);

            if(dashTimeLeft > System.nanoTime()-100000000 && dashCDLeft+1000000000 < dashTimeLeft){
                System.out.println("i dash");
                dashCDLeft = System.nanoTime();
            }
            break;

            case KeyEvent.VK_D:
            player.setLeft(true);

            if(dashTimeRight > System.nanoTime()-100000000 && dashCDRight+1000000000 < dashTimeRight){
                System.out.println("i dash");
                dashCDRight = System.nanoTime();
            }
            break;

            case KeyEvent.VK_SPACE:
                if(spaceRelease){
                player.jump();
                spaceRelease=false;
                }
                break;
                
            case KeyEvent.VK_ESCAPE:
                currentState=MENU;
                break;
            }
        }
    

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
            player.setRight(false);
            dashTimeLeft=System.nanoTime();
            break;

            case KeyEvent.VK_D:
            player.setLeft(false);
            dashTimeRight=System.nanoTime();
            break;
            
            case KeyEvent.VK_SPACE:
            spaceRelease=true;
            break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        player.setAttack(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        player.setAttack(false);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
}
