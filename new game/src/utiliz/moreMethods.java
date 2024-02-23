package utiliz;

import static utiliz.Constants.Resolution.*;

import java.awt.geom.Rectangle2D;

public class moreMethods {
    public static boolean canMove(float x,float y,int width,int height,int[][] lvlData){
        if(isSolid(x,y,lvlData))return false;
            if(isSolid(x+width,y,lvlData))return false;
                if(isSolid(x,y+height,lvlData))return false;
                    if(isSolid(x+width,y+height,lvlData))return false;
                    
        return true;
    }

    public static boolean isSolid(float x,float y,int[][] lvlData){
        if(x>GAME_WIDTH || x<0 || y>GAME_HEIGHT || y<0){
            return true;
        }

        int value = lvlData[(int)(y/TILES_SIZE)][(int)(x/TILES_SIZE)];
        if(value<=10 || value<0) return true;

        return false;
    }

    public static float moveXNextToWall(Rectangle2D.Float hitbox,float xSpeed){
		int currentTile = (int) (hitbox.x / TILES_SIZE);
		if (xSpeed > 0) {
			int tileXPos = currentTile * TILES_SIZE;
			int xOffset = (int) (TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		} else
			return currentTile * TILES_SIZE;
    }

    public static float moveYAboveGround(Rectangle2D.Float hitbox,float airSpeed){
        int currentTile = (int) (hitbox.y / TILES_SIZE);
		if (airSpeed > 0) {
			int tileYPos = currentTile * TILES_SIZE;
			int yOffset = (int) (TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else
			return currentTile * TILES_SIZE;
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox,int[][]lvlData){
        if(isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height+1, lvlData))return true;
            if(isSolid(hitbox.x, hitbox.y+hitbox.height+1, lvlData))return true;
        return false;
    }
}
