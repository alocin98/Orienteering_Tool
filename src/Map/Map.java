package Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map {
    private BufferedImage map;

    public Map(){
        this.map = null;
    }

    public Map(BufferedImage map){
        setImage(map);
    }

    public Map(String text){
        this.map = null;
    }

    public void loadImage(String filepath) throws IOException {
        map = ImageIO.read(new File(filepath));
        setImage(map);
    }

    public void setImage(BufferedImage map){
        this.map = map;
    }
}
