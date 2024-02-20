package entities;

import static utiliz.Constants.PlayerConstants.*;
import static utiliz.Constants.Resolution.SCALE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utiliz.LoadSave.LoadImage;
import static utiliz.moreMethods.*;

public class Player extends Entities{

    private int indexAnimation=0,counter=0;
    private final int aniSpeed=20,playerSpeed=(int)(2*SCALE);
    private BufferedImage animation[][];
    private float x, y;
    private int current_move=IDLE;
    private boolean left,right,up,down,moving=false;
    private int[][] lvlData;
    private final float xOffSet=21*SCALE,yOffSet=15*SCALE;

    public Player(float x, float y, int width, int height){
        super(x,y,width,height);
        this.x=x;
        this.y=y;
        importImage(); 
        initHitBox(x, y, 19 * SCALE, 17 * SCALE);
    }
    
    public void update(){
        updateAnimation();
        updatePosition();
        setAnimation();
        updateHitBox(x, y);
    }
    //update loop that gets updated every UPS in game loop with rate = 120 UPS

    public void render(Graphics g){
        g.drawImage(animation[indexAnimation][current_move], (int) (hitBox.x - xOffSet), (int) (hitBox.y - yOffSet), (int)(64*SCALE), (int)(40*SCALE), null);
        
        //drawHitBox(g);  //this is for dubugging every entity hitbox
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

    public void loadLvlData(int[][] lvlData){
        this.lvlData=lvlData;
    }

    private void updatePosition(){
        moving=false;
        if (!left && !right && !up && !down)
        return;

        if(left && right) return;
        if(up && down) return;

        int xSpeed=0,ySpeed=0;

        if(right && !left){
            xSpeed-=playerSpeed;
        }
        else if(left && !right){
            xSpeed+=playerSpeed;
        }
        
        if(up && !down){
            ySpeed-=playerSpeed;
        }
        else if(down && !up){
            ySpeed+=playerSpeed;
        }

        if(canMove(hitBox.x+xSpeed, hitBox.y+ySpeed, (int)hitBox.width, (int)hitBox.height, lvlData)){
            x+=xSpeed;
            y+=ySpeed;
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

    /* 
        public void drawBackground(Graphics g){
        BufferedImage img=LoadImage("Sky_Bg.png");
        g.drawImage(img, 0, 0, null);
    }
    */

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
