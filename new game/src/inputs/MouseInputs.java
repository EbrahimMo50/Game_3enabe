package inputs;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.GameState;
import main.GamePanel;


public class MouseInputs implements MouseListener,MouseMotionListener{

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.currentState) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;

            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
        
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.currentState) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;

            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
        
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /*
    this is the start of the mouse movment listner.
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    
}
