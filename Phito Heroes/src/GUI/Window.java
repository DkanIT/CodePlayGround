package GUI;
import Main.Game;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.*;


public class Window {
    
    public JFrame frame;
    
    public Window(Game game){
        frame = new JFrame("PhitoHeroes");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(Game.getFRAME_DIMENSION());
        frame.setMaximumSize(Game.getFRAME_DIMENSION());
        frame.setMinimumSize(Game.getFRAME_DIMENSION());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
    public static void update(){
        Symbols.update();
    }
    public static void render(Graphics g){
        Symbols.render(g);
    }
    
}
