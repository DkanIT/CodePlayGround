package GUI;

import Cards.Cards.CardType;
import Main.Board;
import Main.Board.Renk;
import Main.Game;
import Main.Hint;
import Visions.Visions.VisionType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;


public class Info {

    public static ArrayList<Hint> cardBoard;


    private static final Dimension CARD_PANEL_ORG = new Dimension(56, 56);
    private static final int CARD_PANEL_WIDTH = 1920 - 112, CARD_PANEL_HEIGHT = 1080 / 2 - 112;
    private static final int CARD_SIDE = (CARD_PANEL_WIDTH) / 10;

    public static HashMap<Integer, int[]> cardCoordinates;


    public static Renk renk = (Board.isFirst() ? Renk.Blue : Renk.Red);

    public static void initCoordinates() {
        cardBoard = new ArrayList<>();
        cardCoordinates = new HashMap<>();


        int m = 0;
        for (int j = 0; j < 10; j++) {
            cardCoordinates.put(m++, new int[]{CARD_PANEL_ORG.width + (CARD_SIDE) * j, CARD_PANEL_ORG.height});
        }
    }

    public static int hintFinder(int x, int y) {
        int rC = 0;
        if (Game.getSourceHint() != null && Game.getSourceHint().getVision().getCard().getCardtype() != CardType.EmptyCard) {
            x += 1;
            y += 1;
            int rX = 0;
            int rY = 0;
            for (int i = 0; i <= 10; i++) {
                if (x <= CARD_PANEL_ORG.width + (CARD_SIDE) * i) {
                    rX = i - 1;
                    break;
                }
            }
            for (int i = 0; i <= 1; i++) {
                if (y <= CARD_PANEL_ORG.height + (CARD_SIDE) * i) {
                    rY = i - 1;
                    break;
                }
            }
            rC = (rX > 0 ? rX : 0) + (rY > 0 ? rY : 0) * 10;
        }
        return rC;
    }

    public static void render(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(10, 0, 0, 0);
        g.setFont(new Font("Roman SD", Font.BOLD, 30));
        g.drawString("Back for ESC",30,1020);

        for (int i = 0; i < cardBoard.size(); i++) {

            g.setColor(Color.BLACK);
            g.fillRect(cardCoordinates.get(i)[0], cardCoordinates.get(i)[1], CARD_SIDE, CARD_SIDE);


            if (cardBoard.get(i).getVision().getCard().getCardtype() != CardType.EmptyCard) {
                try {
                    BufferedImage bimgcard = ImageIO.read(new File("res/cards/" + cardBoard.get(i).getVision().getCard().getCardtype() + ".gif"));
                    Image scaled = bimgcard.getScaledInstance(CARD_SIDE, CARD_SIDE, Image.SCALE_SMOOTH);
                    g.drawImage(scaled, cardCoordinates.get(i)[0], cardCoordinates.get(i)[1], null);
                } catch (IOException ex) {
                }
            }
        }
        if (Game.getSourceHint() != null) {
            g.setFont(new Font("Helvetica", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            if (Game.getSourceHint().getVision().getVisiontype() == VisionType.Bos) {
                try {
                    BufferedImage bimgcard = ImageIO.read(new File("res/cards/" + Game.getSourceHint().getVision().getCard().getCardtype() + ".gif"));
                    Image scaled = bimgcard.getScaledInstance(CARD_SIDE, CARD_SIDE, Image.SCALE_SMOOTH);
                    g.drawImage(scaled, 810, 300, null);
                    g.drawString(Game.getSourceHint().getVision().getCard().info, 40, 600);


                } catch (IOException ex) {
                }
            }
        }
    }

    public static void updateColor() {
        for (int i = 0; i < cardBoard.size(); i++) {
            cardBoard.get(i).getVision().setColor((Board.isFirst() ? Renk.Blue : Renk.Red));
            cardBoard.get(i).getVision().getCard().setColor((Board.isFirst() ? Renk.Blue : Renk.Red));
        }
    }

    public static Dimension getCARD_PANEL_ORG() {
        return CARD_PANEL_ORG;
    }

    public static int getCARD_PANEL_WIDTH() {
        return CARD_PANEL_WIDTH;
    }

    public static int getCARD_PANEL_HEIGHT() {
        return CARD_PANEL_HEIGHT;
    }

    public static int getCARD_SIDE() {
        return CARD_SIDE;
    }
}
