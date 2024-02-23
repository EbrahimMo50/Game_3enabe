package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static utiliz.Constants.Resolution.*;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;//created private mouse input obj to unify the object passed for mouse listner and motion listner
    private Game game;

    public GamePanel(Game game){

        setPanelSize();
        //removed set size from frame to get all the value for panel

        this.game=game;

        this.mouseInputs=new MouseInputs(this);
        //instialised with this gamepanel

        addKeyListener(new KeyboardInputs(this));

        addMouseListener(mouseInputs);

        addMouseMotionListener(mouseInputs);

        //3 methods to take input from user inherited from JPanel
        Color lightBlue=new Color(135, 206,235);
        this.setBackground(lightBlue);
        
    }

    private void setPanelSize(){
        Dimension size=new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
    }

    public Game getGame(){
        return game;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);//calls the JPanel and tells it to do the things it should do then i come in
        game.render(g);
    }
}
