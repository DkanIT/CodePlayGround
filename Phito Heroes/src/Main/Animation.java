package Main;

import static Main.Board.getBoard;

import java.awt.Graphics;


public class Animation {

    public static Graphics g;
    public static boolean isAnimating;

    public static void initGraphic(Graphics gr) {
        g = gr;
    }

    public static void move(int j, int k) {



        //Vision Moves Action
        getBoard()[j].getVision().setVelX(0);
        getBoard()[j].getVision().setVelY(0);

        Board.moveFromTo(j, k);

        Board.makeVisible(k);

        long l = System.currentTimeMillis() / 100;
        isAnimating = false;
        while (true) {
            if (l + 3 < System.currentTimeMillis() / 100) {
                break;
            }
        }
    }
}


