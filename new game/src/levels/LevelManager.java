package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import utiliz.LoadSave;
import static utiliz.Constants.Resolution.*;

public class LevelManager {
    private Game game;
    private Level levelOne;
    private BufferedImage levelSprite[];

    public LevelManager(Game game){
        this.game=game;
        importOutsideSprites();
        levelOne=new Level(LoadSave.GetLevelData());
    }
	private void importOutsideSprites() {
		BufferedImage img = LoadSave.LoadImage("ground.png");
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
		for (int j = 0; j < 40; j++)
			for (int i = 0; i < 25; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], TILES_SIZE * i, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
			}
	}

    public void update(){

    }
}
