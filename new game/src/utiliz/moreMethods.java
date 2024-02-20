package utiliz;

import static utiliz.Constants.Resolution.*;

public class moreMethods {
    public static boolean canMove(float x,float y,int width,int height,int[][] lvlData){
        if(isSolid(x,y,width,height,lvlData))return false;
            if(isSolid(x+width,y,width,height,lvlData))return false;
                if(isSolid(x,y+height,width,height,lvlData))return false;
                    if(isSolid(x+width,y+height,width,height,lvlData))return false;
                    
        return true;
    }

    public static boolean isSolid(float x,float y,int width,int height,int[][] lvlData){
        if(x>GAME_WIDTH || x<=0 || y>GAME_HEIGHT || y<=0){
            return true;
        }


        int value = lvlData[(int)(y/TILES_SIZE)][(int)(x/TILES_SIZE)];
        if(value<=10 || value<0) return true;

        return false;
    }
}
