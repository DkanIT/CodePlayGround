package GUI;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Background {

    private BufferedImage image;




    //Menu background ımage
    public Background() {
        try {
            image = ImageIO.read(new File("res/others/menubg.gif"));

        } catch (IOException e) {
        }
    }

    public void update() {

    }

   //Back Ground Image Render
    public void render(Graphics g) {

        g.drawImage(image, 0, 0, null);


    }
}
