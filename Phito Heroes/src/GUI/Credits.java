package GUI;


import javax.imageio.ImageIO;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Berkay
public class Credits {

    private double x;
    private double y;
    private BufferedImage image;


    //Menu background ımage
    public Credits() {
        try {

            image = ImageIO.read(new File("res/others/credits.gif"));

        } catch (IOException e) {
            System.out.println("Image not found...");
        }

    }


    //Back Ground Menu moves
    public void update() {

    }


    //Back Ground Image Render
    public void render(Graphics g) {

        g.drawImage(image, (int) x, (int) y, null);


    }


}
