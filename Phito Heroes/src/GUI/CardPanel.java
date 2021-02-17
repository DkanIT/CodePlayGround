package GUI;

import Cards.*;
import Cards.Cards.CardType;
import Main.*;
import Main.Board.Renk;
import Main.Hint;
import Visions.*;
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



//Murat and Doğukan
public class CardPanel {

    public static ArrayList<Hint> cardBoard;
    public static ArrayList<Hint> visionBoard;

    public static void update() {
    }

    private static final Dimension CARD_PANEL_ORG = new Dimension(56, 393);
    private static final Dimension VISION_PANEL_ORG = new Dimension(56, 184);
    private static final int CARD_PANEL_WIDTH = 336, CARD_PANEL_HEIGHT = 184;
    private static final int VISION_PANEL_WIDTH = 336, VISION_PANEL_HEIGHT = 184;
    private static final int VISION_SIDE = (VISION_PANEL_WIDTH) / 5;
    private static final int CARD_SIDE = (CARD_PANEL_WIDTH) / 5;
    private static int stringHeight = 28;

    public static HashMap<Integer, int[]> cardCoordinates;
    public static HashMap<Integer, int[]> visionCoordinates;
    private static boolean isSelected;
    private static Cards card;
    private static Visions vision;
    public static Renk renk = (Board.isFirst() ? Renk.Blue : Renk.Red);
    private static final int butOrgX = 56;
    private static final int butOrgY = CARD_PANEL_ORG.height + CARD_PANEL_HEIGHT + 224;
    private static final int butW = 336;
    private static final int butH = 75;

    public static void initCoordinates() {
        cardBoard = new ArrayList<>();
        visionBoard = new ArrayList<>();
        cardCoordinates = new HashMap<>();
        visionCoordinates = new HashMap<>();

        int m = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                visionCoordinates.put(m++, new int[]{VISION_PANEL_ORG.width + (VISION_SIDE) * j, VISION_PANEL_ORG.height + (VISION_SIDE + stringHeight) * i});
            }
        }
        m = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                cardCoordinates.put(m++, new int[]{CARD_PANEL_ORG.width + (CARD_SIDE) * j, CARD_PANEL_ORG.height + (CARD_SIDE + stringHeight) * i});
            }
        }
        Handler.addHints();
    }

    //Render Card Panel Shop and Selections
    public static void render(Graphics g) {
        if (vision != null) {
            g.setColor(Color.BLACK);
            g.fillRect(CARD_PANEL_ORG.width, CARD_PANEL_ORG.height + CARD_PANEL_HEIGHT + 28, 168, 168);
            try {
                BufferedImage bimgcard = ImageIO.read(new File("res/visionart/" + vision.getColor().toString().substring(0, 1) + vision.getVisiontype() + ".gif"));
                Image scaled = bimgcard.getScaledInstance(168, 168, Image.SCALE_SMOOTH);
                g.drawImage(scaled, CARD_PANEL_ORG.width, CARD_PANEL_ORG.height + CARD_PANEL_HEIGHT + 28, null);
            } catch (IOException ex) {
            }

        }
        if (card != null) {
            g.setColor(Color.BLACK);
            g.fillRect(CARD_PANEL_ORG.width + 168, CARD_PANEL_ORG.height + CARD_PANEL_HEIGHT + 28, 168, 168);
            try {
                BufferedImage bimgcard = ImageIO.read(new File("res/cards/" + card.getCardtype() + ".gif"));
                Image scaled = bimgcard.getScaledInstance(168, 168, Image.SCALE_SMOOTH);
                g.drawImage(scaled, CARD_PANEL_ORG.width + 168, CARD_PANEL_ORG.height + CARD_PANEL_HEIGHT + 28, null);
            } catch (IOException ex) {
            }
        }
        if (card != null && vision != null) {
            g.setColor(Color.lightGray);
            g.fillRect(butOrgX, butOrgY, butW, butH);
            g.setColor(Color.BLACK);

            for (int i = 0; i < 4; i++) {
                g.drawRect(butOrgX + i, butOrgY + i, butW - 2 * i, butH - 2 * i);
            }

            g.setFont(new Font("DIOGENES", Font.BOLD, 38));
            if( card.getPrice()  <= (Board.isFirst() ? GameStatus.getFirstPlayersMoney() : GameStatus.getSecondPlayersMoney())) {
                if(vision.getNum()!=card.getNum()) {
                    isSelected=false;
                    g.drawString("CHANGE VISION", butOrgX+20 , butOrgY + 50);

                }else
                    g.drawString("CREATE", butOrgX + 80, butOrgY + 50);

            }

            //Base SPAWN and Card panel settings about gold and choosing system
            if (isSelected && card.getPrice()  <= (Board.isFirst() ? GameStatus.getFirstPlayersMoney() : GameStatus.getSecondPlayersMoney())) {
                int tmp;
                if (Board.isFirst() && GameStatus.getFirstPlayersMoney() >= card.getPrice() ) {
                    Visions tm = whichVision(vision);
                    Cards ca = whichCard(card);
                    ca.setColor(Renk.Blue);
                    tm.setColor(Renk.Blue);
                    tm.setCard(ca);
                    while (true) {
                        tmp = (int) (Math.random() * GameStatus.blueBase.size());
                        if (Board.getBoard()[GameStatus.blueBase.get(tmp)].getVision().getColor() == Renk.Temp) {
                            tm.setX(Board.getCoordinates().get(GameStatus.blueBase.get(tmp))[0]);
                            tm.setY(Board.getCoordinates().get(GameStatus.blueBase.get(tmp))[1]);
                            Board.getBoard()[GameStatus.blueBase.get(tmp)] = new Hint(GameStatus.blueBase.get(tmp), tm);
                            break;
                        }
                    }
                    GameStatus.decreaseFirstPlayersMoney(card.getPrice());
                } else if (!Board.isFirst() && GameStatus.getSecondPlayersMoney() >= card.getPrice()) {
                    Visions tm = whichVision(vision);
                    Cards ca = whichCard(card);
                    ca.setColor(Renk.Red);
                    tm.setColor(Renk.Red);
                    tm.setCard(ca);
                    while (true) {
                        tmp = (int) (Math.random() * GameStatus.redBase.size());
                        if (Board.getBoard()[GameStatus.redBase.get(tmp)].getVision().getColor() == Renk.Temp) {
                            tm.setX(Board.getCoordinates().get(GameStatus.redBase.get(tmp))[0]);
                            tm.setY(Board.getCoordinates().get(GameStatus.redBase.get(tmp))[1]);
                            Board.getBoard()[GameStatus.redBase.get(tmp)] = new Hint(GameStatus.redBase.get(tmp), tm);
                            break;
                        }
                    }
                    GameStatus.decreaseSecondPlayersMoney(card.getPrice() );
                }
                card = null;
                vision = null;
                Game.setCpCardHint(null);
                Game.setCpVisionHint(null);
                Board.makeInvisible();
            } else if(card.getPrice()  > (Board.isFirst() ? GameStatus.getFirstPlayersMoney() : GameStatus.getSecondPlayersMoney())){
                g.drawString("YOU'RE POOR", butOrgX + 10, butOrgY + 60);
            }
            isSelected = false;
        }
        if (Game.getSourceHint() != null && Game.getSourceHint().getVision().getVisiontype() == VisionType.MainBuilding) {
            for (int i = 0; i < visionBoard.size(); i++) {
                g.setColor(Color.BLACK);
                g.fillRect(visionCoordinates.get(i)[0], visionCoordinates.get(i)[1], VISION_SIDE, VISION_SIDE + stringHeight);
                g.setColor(Color.decode("#802684"));
                g.drawRect(visionCoordinates.get(i)[0], visionCoordinates.get(i)[1], VISION_SIDE, VISION_SIDE + stringHeight);
                g.drawRect(visionCoordinates.get(i)[0], visionCoordinates.get(i)[1] + VISION_SIDE, VISION_SIDE, stringHeight);
                if (visionBoard.get(i).getVision().getVisiontype() != VisionType.Bos) {
                    try {
                        BufferedImage bimg = ImageIO.read(new File("res/visionart/" + visionBoard.get(i).getVision().getCard().getColor().toString().substring(0, 1) + visionBoard.get(i).getVision().getVisiontype() + ".gif"));
                        Image scaled = bimg.getScaledInstance(VISION_SIDE, VISION_SIDE, Image.SCALE_SMOOTH);
                        g.drawImage(scaled, visionCoordinates.get(i)[0], visionCoordinates.get(i)[1], null);
                        g.setFont(new Font("DIOGENES", Font.BOLD, 12));
                        g.drawString(visionBoard.get(i).getVision().getVisiontype().name().substring(0,visionBoard.get(i).getVision().getVisiontype().name().length()-1)+"", visionCoordinates.get(i)[0] + 12, visionCoordinates.get(i)[1] - 5 + stringHeight + VISION_SIDE);
                        g.setFont(new Font("DIOGENES", Font.BOLD, stringHeight));
                    } catch (IOException ex) {
                        System.out.println("EXCEPTION CardPrice RENDER");
                    }
                }
                if (Game.getCpVisionHint() == visionBoard.get(i)) {
                    if (Game.getCpVisionHint().getVision().getVisiontype() != VisionType.Bos) {
                        vision = Game.getCpVisionHint().getVision();
                    }
                    g.setColor(Board.isFirst() ? Color.blue : Color.red);
                    for (int j = 0; j < 5; j++) {
                        g.drawRect(visionCoordinates.get(i)[0] + j, visionCoordinates.get(i)[1] + j, VISION_SIDE - 2 * j, VISION_SIDE - 2 * j);
                    }
                }
            }
            for (int i = 0; i < cardBoard.size(); i++) {

                g.setColor(Color.BLACK);
                g.fillRect(cardCoordinates.get(i)[0], cardCoordinates.get(i)[1], CARD_SIDE, CARD_SIDE + stringHeight);
                g.setColor(Color.decode("#802684"));
                g.drawRect(cardCoordinates.get(i)[0], cardCoordinates.get(i)[1], CARD_SIDE, CARD_SIDE + stringHeight);
                g.drawRect(cardCoordinates.get(i)[0], cardCoordinates.get(i)[1] + CARD_SIDE, CARD_SIDE, stringHeight);

                if (cardBoard.get(i).getVision().getCard().getCardtype() != CardType.EmptyCard) {
                    try {
                        BufferedImage bimgcard = ImageIO.read(new File("res/cards/" + cardBoard.get(i).getVision().getCard().getCardtype() + ".gif"));
                        Image scaled = bimgcard.getScaledInstance(CARD_SIDE, CARD_SIDE, Image.SCALE_SMOOTH);
                        g.drawImage(scaled, cardCoordinates.get(i)[0], cardCoordinates.get(i)[1], null);
                        g.drawString("" + cardBoard.get(i).getVision().getCard().getPrice(), cardCoordinates.get(i)[0] + 8, cardCoordinates.get(i)[1] - 4 + stringHeight + CARD_SIDE);
                    } catch (IOException ex) {
                    }
                }
                if (Game.getCpCardHint() == cardBoard.get(i)) {
                    if (Game.getCpCardHint().getVision().getCard().getCardtype() != CardType.EmptyCard) {
                        card = Game.getCpCardHint().getVision().getCard();
                    }
                    g.setColor(Board.isFirst() ? Color.blue : Color.red);
                    for (int j = 0; j < 5; j++) {
                        g.drawRect(cardCoordinates.get(i)[0] + j, cardCoordinates.get(i)[1] + j, CARD_SIDE - 2 * j, CARD_SIDE - 2 * j);
                    }
                }
            }
        }
    }

    public static void setIsSelected(boolean isSelected) {
        CardPanel.isSelected = isSelected;
    }


    //Shows Cards on screen
    public static Cards whichCard(Cards card) {
        switch (card.getCardtype()) {
            case Ares:
                return new Ares(Renk.Temp);
            case Hercules:
                return new Hercules(Renk.Temp);
            case Nasos:
                return new Nasos(Renk.Temp);
            case Zeus:
                return new Zeus(Renk.Temp);
            case Pythia:
                return new Pythia(Renk.Temp);
            case Martialis:
                return new Martialis(Renk.Temp);
            case Poseidon:
                return new Poseidon(Renk.Temp);
            case Eleonore:
                return new Eleonore(Renk.Temp);
            case Hecate:
                return new Hecate(Renk.Temp);
            case Hades:
                return new Hades(Renk.Temp);
        }
        return null;
    }


    //Shows Visions on Screen when shops opened
    public static Visions whichVision(Visions vision) {
        Cards tmp = new EmptyCard(Renk.Temp);
        switch (vision.getVisiontype()) {
            case HecateV:
                return new HecateV(tmp, -1, -1);
            case EleonoreV:
                return new EleonoreV(tmp, -1, -1);
            case ZeusV:
                return new ZeusV(tmp, -1, -1);
            case HerculesV:
                return new HerculesV(tmp, -1, -1);
            case MartialisV:
                return new MartialisV(tmp, -1, -1);
            case PythiaV:
                return new PythiaV(tmp, -1, -1);
            case PoseidonV:
                return new PoseidonV(tmp, -1, -1);
            case HadesV:
                return new HadesV(tmp, -1, -1);
            case AresV:
                return new AresV(tmp, -1, -1);
            case NasosV:
                return new NasosV(tmp, -1, -1);
        }
        return null;
    }

    public static void updateColor() {
        for (int i = 0; i < visionBoard.size(); i++) {
            visionBoard.get(i).getVision().setColor(Board.isFirst() ? Renk.Blue : Renk.Red);
            visionBoard.get(i).getVision().getCard().setColor((Board.isFirst() ? Renk.Blue : Renk.Red));
        }
        for (int i = 0; i < cardBoard.size(); i++) {
            cardBoard.get(i).getVision().setColor((Board.isFirst() ? Renk.Blue : Renk.Red));
            cardBoard.get(i).getVision().getCard().setColor((Board.isFirst() ? Renk.Blue : Renk.Red));
        }
    }


    //Setters and Getter to Card ARTS and Vision ARTS to Render o window
    public static ArrayList<Hint> getCardBoard() {
        return cardBoard;
    }

    public static HashMap<Integer, int[]> getCardCoordinates() {
        return cardCoordinates;
    }

    public static int getVISION_SIDE() {
        return VISION_SIDE;
    }

    public static void setCard(Cards card) {
        CardPanel.card = card;
    }

    public static void setVision(Visions vision) {
        CardPanel.vision = vision;
    }

    public static ArrayList<Hint> getVisionBoard() {
        return visionBoard;
    }

    public static Cards getCard() {
        return card;
    }

    public static Visions getVision() {
        return vision;
    }

    public static int getButOrgX() {
        return butOrgX;
    }

    public static int getButOrgY() {
        return butOrgY;
    }

    public static int getButW() {
        return butW;
    }

    public static int getButH() {
        return butH;
    }
    public static Dimension getVISION_PANEL_ORG() {
        return VISION_PANEL_ORG;
    }

    public static int getVISION_PANEL_WIDTH() {
        return VISION_PANEL_WIDTH;
    }

    public static int getVISION_PANEL_HEIGHT() {
        return VISION_PANEL_HEIGHT;
    }

    public static int getCARD_WIDTH() {
        return CARD_SIDE;
    }

    public static HashMap<Integer, int[]> getVisionCoordinates() {
        return visionCoordinates;
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
}
