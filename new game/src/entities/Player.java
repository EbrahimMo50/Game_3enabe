package entities;

import static utiliz.Constants.PlayerConstants.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utiliz.LoadSave.LoadImage;

public class Player extends Entities{

    private int indexAnimation=0,counter=0;
    private final int aniSpeed=20,playerSpeed=2;
    private BufferedImage animation[][];
    private int x=100, y=100;
    private int current_move=IDLE;
    private boolean left,right,up,down,moving=false;

    public Player(float x, float y){
        super(x,y);
        importImage();
    }

    
    public void update(){
        updateAnimation();
        updatePosition();
        setAnimation();
    }
    //update loop that gets updated every UPS in game loop with rate = 120 UPS

    public void render(Graphics g){
        g.drawImage(animation[indexAnimation][current_move], (int) x, (int) y, 128, 80, null);
    }
    //render loop that gets updated every FPS in game loop with rate = 120 FPS

    private void importImage(){
		BufferedImage img = LoadImage("player_3enab.png");
        animation=new BufferedImage[8][8];
        for(int i=0;8>i;++i){
            for(int j=0;8>j;++j){
                animation[i][j]=img.getSubimage(64*i,40*j,64,40);
            }
        }
    }
    //method that loads the array of images with the called img

    private void updateAnimation(){
        counter++;
        if(counter>=aniSpeed){
            if(indexAnimation+1>=setMaxFrame(current_move)){
                indexAnimation=-1;
            }
            indexAnimation++;
            counter=0;
        }
    }

    private void updatePosition(){
        moving=false;
        if(right && !left){
            x-=playerSpeed;
            moving=true;
        }

        else if(left && !right){
            x+=playerSpeed;
            moving=true;
        }
        
        if(up && !down){
            y-=playerSpeed;
            moving=true;
        }
        
        else if(down && !up){
            y+=playerSpeed;
            moving=true;
        }
    }
    
    public void setAnimation(){
        int move=current_move;
        if(moving){
            current_move=RUNNING;
        }
        else{
            current_move=IDLE;
        }
        if(current_move!=move){
            indexAnimation=0;
            counter=0;
            //we start the indexs from the start if we start another move to get the new animation from the start
        }
    }

    public void resetBooleans(){
        this.up=false;
        this.right=false;
        this.down=false;
        this.left=false;
    }
    //resets booleans when losing focus of the window

    public void setUp(boolean value){
        this.up=value;
    }
    public void setRight(boolean value){
        this.right=value;
    }
    public void setDown(boolean value){
        this.down=value;
    }
    public void setLeft(boolean value){
        this.left=value;
    }

}
