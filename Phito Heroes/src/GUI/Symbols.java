package GUI;

import Main.Board;
import Main.Game;
import Main.GameStatus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import Visions.Visions;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;


public class Symbols {

    private static Dimension FEATURES_DIMENSION = new Dimension(392, 1024);
    private static final int panelOrgX = 1500;
    private static final int panelOrgY = 28;
    private static final int br = panelOrgY;
    private static final int hpORG_X = panelOrgX + br + 38;
    private static final int hpORG_Y = panelOrgY + 402;
    private static final int hpImageSide = 38;
    private static final int hpBarHeight = 30;
    private static final int hpBarWidth = 336 - hpImageSide - 8;
    public static void update() {

    }


    //HP bar and Attacks defens golds ımages
    public static void render(Graphics g) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/A.ttf")));
            g.setFont(new Font("Helvetica", Font.BOLD, 110));
        } catch (IOException | FontFormatException e) {
        }
        g.setColor(Color.BLACK);
        g.fillRect(panelOrgX + br, panelOrgY + 28, 336, 336);
        g.fillRect(panelOrgX + br, panelOrgY + 392, 336, 56);
        g.fillRect(panelOrgX + br, panelOrgY + 842, 154, 154);
        g.fillRect(panelOrgX + 210, panelOrgY + 842, 154, 154);
        if (Game.getSourceHint() != null && Game.getSourceHint().getVision().getVisiontype() != null
                && Game.getSourceHint().getVision().getCard().getCardtype() != null
                && Game.getSourceHint().getVision().getVisiontype() != Visions.VisionType.Bos
                && ((Game.getSourceHint().getVision().getColor() == Main.Board.Renk.Red && !Board.isFirst())
                || (Game.getSourceHint().getVision().getColor() == Main.Board.Renk.Blue && Board.isFirst()))) {
            try {

                //Visions and cards ımage bottom side
                //right top side
                BufferedImage piece = ImageIO.read(new File("res/visionart/" + Board.getBoard()[Game.getSourceHint().getCoordinate()].getVision().getCard().getColor().toString().substring(0, 1) + Board.getBoard()[Game.getSourceHint().getCoordinate()].getVision().getVisiontype() + ".gif"));
                Image scaledvision = piece.getScaledInstance(154, 154, Image.SCALE_SMOOTH);
                g.drawImage(scaledvision, panelOrgX + br, panelOrgY + 842, null);
                BufferedImage bimgcard = ImageIO.read(new File("res/cards/" + Board.getBoard()[Game.getSourceHint().getCoordinate()].getVision().getCard().getCardtype() + ".gif"));
                Image scaledcard = bimgcard.getScaledInstance(154, 154, Image.SCALE_SMOOTH);
                g.drawImage(scaledcard, panelOrgX + 210, panelOrgY + 842, null);
                g.setColor(Board.isFirst() ? Color.BLUE : Color.red);
                g.drawString("" + Game.getSourceHint().getVision().getCard().getPower(), panelOrgX + br + 145, panelOrgY + 130);
                g.drawString("" + Game.getSourceHint().getVision().getCard().getDefence(), panelOrgX + br + 145, panelOrgY + 270);
                g.setFont(new Font("Helvetica", Font.BOLD, 70));
                g.drawString(Board.isFirst() ? "" + GameStatus.getFirstPlayersMoney() : "" + GameStatus.getSecondPlayersMoney(), panelOrgX + br + 115, panelOrgY + 345);


                //HP BAR
                if (Game.getSourceHint() != null && Game.getSourceHint().getVision().getVisiontype() != null
                        && Game.getSourceHint().getVision().getCard().getCardtype() != null) {
                    if (Game.getSourceHint().getVision().getHP() != 0 && Game.getSourceHint().getVision().getColor() != Main.Board.Renk.Temp) {
                        g.setColor(Color.gray);
                        g.fillRect(hpORG_X, hpORG_Y, hpBarWidth, hpBarHeight);
                        g.setColor(new Color((int) Game.getSourceHint().getVision().getRedValue(), (int) Game.getSourceHint().getVision().getGreenValue(), 0));
                        g.fillRect(hpORG_X + 4, hpORG_Y + 4,
                                (100 * Game.getSourceHint().getVision().getHP() / Game.getSourceHint().getVision().getMaxHP()) * (hpBarWidth - 8) / 100, hpBarHeight - 8);
                        g.setColor(Color.white);
                        for (int i = 0; i < 4; i++) {
                            g.drawRect(hpORG_X + i, hpORG_Y + i, hpBarWidth - 2 * i, hpBarHeight - 2 * i);
                        }
                        g.drawImage(ImageIO.read(new File("res/others/health.gif")).getScaledInstance(hpImageSide, hpImageSide, Image.SCALE_SMOOTH), hpORG_X - hpImageSide, hpORG_Y, null);
                    }
                    g.setFont(new Font("Helvetica", Font.BOLD, 38));
                    if (Game.getSourceHint().getVision().getColor() == Main.Board.Renk.Blue) {
                        g.setColor(Color.decode("#333FF"));
                    }
                    if (Game.getSourceHint().getVision().getColor() == Main.Board.Renk.Red) {
                        g.setColor(Color.decode("#FF3333"));
                    }
                    BufferedImage p = ImageIO.read(new File("res/others/power.gif"));
                    Image ps = p.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                    g.drawImage(ps, panelOrgX + br, panelOrgY + 28, null);
                    BufferedImage p1 = ImageIO.read(new File("res/others/defence.gif"));
                    Image ps1 = p1.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                    g.drawImage(ps1, panelOrgX + br, panelOrgY + 28 + 138, null);
                    BufferedImage p2 = ImageIO.read(new File("res/others/coin.gif"));
                    Image ps2 = p2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    g.drawImage(ps2, panelOrgX + br + 10, panelOrgY + 28 + 240, null);

                    g.setFont(new Font("Helvetica", Font.BOLD, 30));


                   /*g.drawString("MONEY ", panelOrgX + 30, panelOrgY + 5);
                   g.drawString("" + (Board.isFirst() ? + GameStatus.getFirstPlayersMoney() : ""+ GameStatus.getFirstPlayersMoney()), panelOrgX + 30, panelOrgY + 35);
                */}
            } catch (IOException | NumberFormatException | NullPointerException ex) {
                System.out.println("Symbols error");
            }
        }
    }
}
