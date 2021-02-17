package GUI;


import Main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


//Doğukan
public class GameOver {

    public static void render(Graphics g){
        g.setColor(!Game.isIsBlueWon() ? Color.RED : Color.BLUE);
        g.setFont(new Font("Helvetica", Font.BOLD, 200));
        g.drawString(!Game.isIsBlueWon() ? "Red Won!" : "Blue Won!",500,500);
    }

}
