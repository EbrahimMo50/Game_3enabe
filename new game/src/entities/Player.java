package entities;

import static utiliz.Constants.PlayerConstants.*;
import static utiliz.Constants.Resolution.SCALE;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static utiliz.LoadSave.LoadImage;
import static utiliz.moreMethods.*;

public class Player extends Entities{

    private int indexAnimation = 0,counter = 0;
    private final int aniSpeed = 20,playerSpeed=(int)(2*SCALE);
    private BufferedImage animation[][];
    private int current_move = IDLE;

    private boolean left,right,moving;                          //movment bools
    private int[][] lvlData;                                    //temp loading the lvlData here
    private final float xOffSet = 21*SCALE, yOffSet = 15*SCALE; //resolution releated
    
    //jumping / gravity releated
    private boolean airBorned = false, doubleJump = true;
    private float airSpeed = 0;
    private float gravity = 0.05f*SCALE , fallSpeed = 0.5f*SCALE, jumpingSpeed = -2.5f*SCALE;


    public Player(float x, float y, int width, int height){
        super(x,y,width,height);
        this.x=x;
        this.y=y;
        importImage(); 
        initHitBox(x, y, 19 * SCALE, 16 * SCALE);
    }
    
    public void update(){
        updateAnimation();
        updatePosition();
        setAnimation();
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
        if(!isEntityOnFloor(hitBox, lvlData)){
            airBorned=true;
        }
    }

    private void updatePosition(){
        moving=false;
        // if(jump)
        //     jump();
        if (!left && !right && !airBorned)
        return;

        if(!isEntityOnFloor(hitBox, lvlData))
            airBorned=true;
        
        else 
            doubleJump=true;

        int xSpeed=0;

        if(right)
            xSpeed-=playerSpeed;
        if(left)
            xSpeed+=playerSpeed;
        
        if(airBorned){
            if(canMove(hitBox.x, hitBox.y+airSpeed, (int)hitBox.width, (int)hitBox.height, lvlData)){
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updatexPos(xSpeed);
            }
            else{
                y=moveYAboveGround(hitBox,airSpeed);
                if(airSpeed>0){		
                    airBorned = false;
                    airSpeed = 0;
                }
                else{
                    airSpeed=fallSpeed;
                }
                updatexPos(xSpeed);
            }
        }
        else{
            updatexPos(xSpeed);
        }
        moving=true;
    }

    public void jump() {
        if(!airBorned){
            airSpeed=jumpingSpeed;
            airBorned=true;
        }
        else if(doubleJump){
            airSpeed=jumpingSpeed;
            doubleJump=false;
        }
    }

    private void updatexPos(float xSpeed){
        if(canMove(hitBox.x+xSpeed, hitBox.y, (int)hitBox.width, (int)hitBox.height, lvlData)){
            hitBox.x+=xSpeed;
        }
        else{
            hitBox.x=moveXNextToWall(hitBox,xSpeed);
        }
    }

    private void setAnimation(){
        int move=current_move;
        if(moving){
            current_move=RUNNING;
        }
        else{
            current_move=IDLE;
        }
        if(airBorned){
            if(airSpeed>0){
                current_move=FALLING;
            }
            else current_move=JUMPING;
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
    }   //not optimal needs another method that paints ig?
    */

    public void resetBooleans(){
        this.right=false;
        this.left=false;
    }
    //resets booleans when losing focus of the window

    public void setRight(boolean value){
        this.right=value;
    }
    public void setLeft(boolean value){
        this.left=value;
    }
}
