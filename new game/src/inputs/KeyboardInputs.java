package inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;


public class KeyboardInputs implements KeyListener{

    private GamePanel gamePanel;
    private long dashTimeLeft;
    private long dashCDLeft;
    private long dashTimeRight;
    private long dashCDRight;

    public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel=gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){

            case KeyEvent.VK_W:
            gamePanel.getGame().getPlayer().setUp(true);
            break;

            case KeyEvent.VK_A:

            gamePanel.getGame().getPlayer().setRight(true);

            if(dashTimeLeft > System.nanoTime()-100000000 && dashCDLeft+1000000000 < dashTimeLeft){
                System.out.println("i dash");
                dashCDLeft = System.nanoTime();
            }

            break;

            case KeyEvent.VK_S:
            gamePanel.getGame().getPlayer().setDown(true);
            break;

            case KeyEvent.VK_D:

            gamePanel.getGame().getPlayer().setLeft(true);

            if(dashTimeRight > System.nanoTime()-100000000 && dashCDRight+1000000000 < dashTimeRight){
                System.out.println("i dash");
                dashCDRight = System.nanoTime();
            }

            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
            gamePanel.getGame().getPlayer().setUp(false);
            break;

            case KeyEvent.VK_A:
            gamePanel.getGame().getPlayer().setRight(false);
            dashTimeLeft=System.nanoTime();
            break;

            case KeyEvent.VK_S:
            gamePanel.getGame().getPlayer().setDown(false);
            break;

            case KeyEvent.VK_D:
            gamePanel.getGame().getPlayer().setLeft(false);
            dashTimeRight=System.nanoTime();
            break;
            
        }
    }
    
}
