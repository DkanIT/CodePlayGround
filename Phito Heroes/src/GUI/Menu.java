package GUI;

import Main.Game;
import Main.Game.STATE;

import java.awt.*;
import java.io.File;


public class Menu {

    private Background bg;



    public static int currentChoice = 0;
    public static String[] options = {
            "Start",
            "Credits",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public Menu() {

        //Menu text settings
        try {
            bg = new Background();

            titleColor = Color.decode("#43076b");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/B.ttf")));
            titleFont = new Font("GodOfWar", Font.BOLD, 150);
            font = new Font("GodOfWar", Font.BOLD, 70);
        } catch (Exception e) {
        }
    }

    //Back Ground moves
    public void update() {


    }

    //Menu Text renders
    public void render(Graphics g) {

        bg.render(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("", 500, 250);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.decode("#724604"));
            } else {
                g.setColor(Color.BLUE);
            }
            g.drawString(options[i], 800, 800 + i * 70);
        }
    }

    public static void select() {
        if (currentChoice == 0) {
            Game.setGameState(STATE.Game);
        }
        if(currentChoice == 1){
            Game.setGameState(STATE.Credits);

        }
        if (currentChoice == 2) {
            System.exit(0);
        }
    }


}
