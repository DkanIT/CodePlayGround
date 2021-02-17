package GUI;

import Main.Board;
import Main.Game;
import Main.GameStatus;
import Main.Hint;

import static Main.Hint.MONEY_DARK;
import static Main.Hint.MONEY_LIGHT;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;


public class Minimap {
    //boyutu
    private static final int mapW = 336, mapH = 336;
    //pozisyonu
    private static int mapOrgX = 1528, mapOrgY = 505;
    private static final int infoOrgX = 56;
    private static final int infoOrgY = 56;
    private static final int infopW = 336;
    private static final int infopH = 100;


    //Screen Buttons and tips
    public static void render(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(infoOrgX, infoOrgY, infopW, infopH);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            g.drawRect(infoOrgX + i, infoOrgY + i, infopW - 2 * i, infopH - 2 * i);
        }
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/A.ttf")));
            g.setFont(new Font("Helvetica", Font.BOLD, 90));
        } catch (IOException | FontFormatException e) {
        }
        g.drawString("INFO", infoOrgX + 60, infoOrgY + 90);

        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("Sound off M on P", infoOrgX +20, infoOrgY +850);
        g.drawString("      Moves        ", infoOrgX +20, infoOrgY +880);
        g.drawString("W - Up   S - Down  ", infoOrgX +20, infoOrgY +910);
        g.drawString("A - Left D - Right ", infoOrgX +20, infoOrgY +940);
        g.setFont(new Font("Roman SD", Font.BOLD, 30));
        g.drawString("Back for ESC", infoOrgX +10, infoOrgY +980);

        //minimap
        int m = 0;
        for (int i = 0; i < Game.getKS(); i++) {
            for (int j = 0; j < Game.getKS(); j++) {
                if ((Board.getBoard()[m].isIsVisible4Red() && !Board.isFirst()) || (Board.getBoard()[m].isIsVisible4Blue() && Board.isFirst())) {
                    switch (Board.getBoard()[m].getVision().getColor()) {
                        case Red:
                            g.setColor(Color.RED);
                            g.fillRect(mapOrgX + mapW / Game.getKS() * j, mapOrgY + mapH / Game.getKS() * i, mapW / Game.getKS(), mapH / Game.getKS());
                            break;
                        case Blue:
                            g.setColor(Color.BLUE);
                            g.fillRect(mapOrgX + mapW / Game.getKS() * j, mapOrgY + mapH / Game.getKS() * i, mapW / Game.getKS(), mapH / Game.getKS());
                            break;
                        default:
                            if (GameStatus.blueBase.contains(m)) {
                                g.setColor(((m % Game.getKS() + m / Game.getKS()) % 2) != 0 ? Hint.BLUE_BASE_DARK : Hint.BLUE_BASE_LIGHT);
                                g.fillRect(mapOrgX + mapW / Game.getKS() * j, mapOrgY + mapH / Game.getKS() * i, mapW / Game.getKS(), mapH / Game.getKS());
                            } else if (GameStatus.redBase.contains(m)) {
                                g.setColor(((m % Game.getKS() + m / Game.getKS()) % 2) == 0 ? Hint.RED_BASE_DARK : Hint.RED_BASE_LIGHT);
                                g.fillRect(mapOrgX + mapW / Game.getKS() * j, mapOrgY + mapH / Game.getKS() * i, mapW / Game.getKS(), mapH / Game.getKS());
                            } else if (GameStatus.moneyMaker.contains(m)) {
                                g.setColor(((m % Game.getKS() + m / Game.getKS()) % 2) == 0 ? MONEY_LIGHT : MONEY_DARK);
                                g.fillRect((int) Board.getCoordinates().get(m)[0] - (int) Game.getORG_X(), (int) Board.getCoordinates().get(m)[1] - (int) Game.getORG_Y(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK(), Game.getFRAME_DIMENSION().width / (int) Game.getVSK());
                            } else {
                                g.setColor(((m % Game.getKS() + m / Game.getKS()) % 2) == 0 ? Game.getColor1() : Game.getColor2());
                                g.fillRect(mapOrgX + mapW / Game.getKS() * j, mapOrgY + mapH / Game.getKS() * i, mapW / Game.getKS(), mapH / Game.getKS());
                            }
                    }
                } else {
                    g.setColor(Color.black);
                    g.fillRect(mapOrgX + mapW / Game.getKS() * j, mapOrgY + mapH / Game.getKS() * i, mapW / Game.getKS(), mapH / Game.getKS());
                }
                m++;
            }
        }
        g.setColor(Color.BLUE);
        g.drawRect(mapOrgX + 98 + (int) Game.getORG_X() * mapH / ((Game.getFRAME_DIMENSION().width / (int) Game.getVSK()) * Game.getKS()), mapOrgY + 5 + ((int) Game.getORG_Y() * mapH) / ((Game.getFRAME_DIMENSION().width / (int) Game.getVSK()) * Game.getKS()), 224, 224);

    }

    public static int getInfopH() {
        return infopH;
    }

    public static int getInfoOrgX() {
        return infoOrgX;
    }

    public static int getInfoOrgY() {
        return infoOrgY;
    }

    public static int getInfopW() {
        return infopW;
    }

}
