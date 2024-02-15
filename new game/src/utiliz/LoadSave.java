package utiliz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.Game;

public class LoadSave {
    public static BufferedImage LoadImage(String imgName){
        InputStream is = LoadSave.class.getResourceAsStream("../res/"+imgName);
        BufferedImage img=null;

        try {
		img = ImageIO.read(is);
		} 
        catch (IOException e) {
			e.printStackTrace();
        }
        return img;
    }

    public static int[][] GetLevelData() {
		int[][] lvlData = new int[40][27];
		BufferedImage img = LoadImage("level_one_data.png");
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 12;
				lvlData[j][i] = value;
			}
		return lvlData;
	}
}
