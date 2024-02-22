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

    private boolean spaceRelease=true;
    public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel=gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_Z:
            System.out.println("help");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
            gamePanel.getGame().getPlayer().setRight(true);

            if(dashTimeLeft > System.nanoTime()-100000000 && dashCDLeft+1000000000 < dashTimeLeft){
                System.out.println("i dash");
                dashCDLeft = System.nanoTime();
            }
            break;

            case KeyEvent.VK_D:
            gamePanel.getGame().getPlayer().setLeft(true);

            if(dashTimeRight > System.nanoTime()-100000000 && dashCDRight+1000000000 < dashTimeRight){
                System.out.println("i dash");
                dashCDRight = System.nanoTime();
            }
            break;

            case KeyEvent.VK_SPACE:
                if(spaceRelease){
                gamePanel.getGame().getPlayer().jump();
                spaceRelease=false;
                break;
                }
            }
        }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
            gamePanel.getGame().getPlayer().setRight(false);
            dashTimeLeft=System.nanoTime();
            break;

            case KeyEvent.VK_D:
            gamePanel.getGame().getPlayer().setLeft(false);
            dashTimeRight=System.nanoTime();
            break;
            
            case KeyEvent.VK_SPACE:
            spaceRelease=true;
            break;
        }
    }
    
}
