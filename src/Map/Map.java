package Map;

import javafx.scene.image.Image;

import java.io.File;

public class Map {

    private File file;
    private Image image;

    public Map(){

    }

    public Map(File file) throws Exception{
        this.file = file;
        image = new Image(file.toURI().toString());
    }

    public File getFile(){
        return file;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public Image getImage(){
        return image;
    }
}
