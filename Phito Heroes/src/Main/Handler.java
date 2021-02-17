package Main;

import Cards.*;
import GUI.CardPanel;
import GUI.Info;
import Main.Board.Renk;
import Visions.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

//Doğukan and  smthg
public class Handler {

    private static LinkedList<Hint> hintObject = new LinkedList<>();
    
    public static void update(){
        for(int i = 0; i< hintObject.size(); i++){
                hintObject.get(i).update();
        }
    }
    public static void render(Graphics g){
        for(int i = 0; i< hintObject.size(); i++){
           hintObject.get(i).render(g);
        }
    }
    public static void addObject(Hint object){
        hintObject.add(object);
    } 
    public static void removeObject(Hint object){
        hintObject.remove(object);
    }
    public static void updateCardDirection(){
        for(int i = 0; i< hintObject.size(); i++)
            if(hintObject.get(i).getVision().getColor() != Renk.Temp)
                hintObject.get(i).getVision().getCard().setDirection(hintObject.get(i).getVision().getDirection());
    }
    public static void updateVisionsHint(){
        for(int i = 0; i< hintObject.size(); i++)
            if(hintObject.get(i).getVision().getColor() != Renk.Temp)
                hintObject.get(i).getVision().setHint(hintObject.get(i));
    }

    public static void findDirections(int j, int k) {
        if (k > j && k % Game.getKS() == j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{4, 0});
        } else if (k < j && k % Game.getKS() == j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{0, 4});
        } else if (k / Game.getKS() == j / Game.getKS() && k % Game.getKS() > j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{2, 6});
        } else if (k / Game.getKS() == j / Game.getKS() && k % Game.getKS() < j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{6, 2});
        } else if (k > j && k % Game.getKS() > j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{3, 7});
        } else if (k < j && k % Game.getKS() > j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{1, 5});
        } else if (k > j && k % Game.getKS() < j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{5, 1});
        } else if (k < j && k % Game.getKS() < j % Game.getKS()) {
            Board.getBoard()[j].getVision().setDirection(new int[]{7, 3});
        }
        Handler.updateCardDirection();
    }

    public static int hintFinder(int x, int y) {
        x += 1;
        y += 1;
        int rX = 0;
        int rY = 0;
        int rC;
        for (int i = 0; i <= Game.getKS(); i++) {
            if (x <= Game.getFRAME_DIMENSION().width / Game.getVSK() * i - Game.getORG_X()) {
                rX = i - 1;
                break;
            }
        }
        for (int i = 0; i <= Game.getKS(); i++) {
            if (y <= Game.getFRAME_DIMENSION().width / Game.getVSK() * i - Game.getORG_Y()) {
                rY = i - 1;
                break;
            }
        }
        rC = (rX > 0 ? rX : 0) + (rY > 0 ? rY : 0) * Game.getKS();
        return rC;
    }

    private static final Dimension CARD_PANEL_ORG = new Dimension(56, 396);
    private static final Dimension VISION_PANEL_ORG = new Dimension(56, 184);
    private static final int CARD_PANEL_WIDTH = 336;
    private static final int VISION_PANEL_WIDTH = 336;
    private static final int VISION_SIDE = (VISION_PANEL_WIDTH) / 5;
    private static final int CARD_SIDE = (CARD_PANEL_WIDTH) / 5;
    private static int stringHeight = 25;

    public static int visionHintFinder(int x, int y) {
        x += 1;
        y += 1;
        int rX = 0;
        int rY = 0;
        int rC;
        for (int i = 0; i <= 5; i++) {
            if (x <= VISION_PANEL_ORG.width + (VISION_SIDE) * i) {
                rX = i - 1;
                break;
            }
        }
        for (int i = 0; i <= 2; i++) {
            if (y <= VISION_PANEL_ORG.height + (VISION_SIDE + stringHeight) * i) {
                rY = i - 1;
                break;
            }
        }
        rC = (rX > 0 ? rX : 0) + (rY > 0 ? rY : 0) * 5;
        return rC;
    }

    public static int cardHintFinder(int x, int y) {
        x += 1;
        y += 1;
        int rX = 0;
        int rY = 0;
        int rC;
        for (int i = 0; i <= 5; i++) {
            if (x <= CARD_PANEL_ORG.width + (CARD_SIDE) * i) {
                rX = i - 1;
                break;
            }
        }
        for (int i = 0; i <= 2; i++) {
            if (y <= CARD_PANEL_ORG.height + (CARD_SIDE + stringHeight) * i) {
                rY = i - 1;
                break;
            }
        }
        rC = (rX > 0 ? rX : 0) + (rY > 0 ? rY : 0) * 5;
        return rC;
    }

    public static void addHints() {
        Hint.setIsFromCP(true);
        Visions.setIsFromCP(true);
        Cards.setIsFromCP(true);
        CardPanel.visionBoard.add(new Hint(0, new NasosV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(0)[0], CardPanel.cardCoordinates.get(0)[1])));
        CardPanel.visionBoard.add(new Hint(1, new EleonoreV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(1)[0], CardPanel.cardCoordinates.get(1)[1])));
        CardPanel.visionBoard.add(new Hint(2, new ZeusV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(2)[0], CardPanel.cardCoordinates.get(2)[1])));
        CardPanel.visionBoard.add(new Hint(3, new HecateV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(3)[0], CardPanel.cardCoordinates.get(3)[1])));
        CardPanel.visionBoard.add(new Hint(4, new MartialisV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(4)[0], CardPanel.cardCoordinates.get(4)[1])));
        CardPanel.visionBoard.add(new Hint(5, new AresV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(5)[0], CardPanel.cardCoordinates.get(5)[1])));
        CardPanel.visionBoard.add(new Hint(6, new PythiaV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(6)[0], CardPanel.cardCoordinates.get(6)[1])));
        CardPanel.visionBoard.add(new Hint(7, new PoseidonV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(7)[0], CardPanel.cardCoordinates.get(7)[1])));
        CardPanel.visionBoard.add(new Hint(8, new HerculesV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(8)[0], CardPanel.cardCoordinates.get(8)[1])));
        CardPanel.visionBoard.add(new Hint(9, new HadesV(new EmptyCard(CardPanel.renk), CardPanel.visionCoordinates.get(9)[0], CardPanel.cardCoordinates.get(9)[1])));
        CardPanel.cardBoard.add(new Hint(0, new EmptyVision(new Zeus(CardPanel.renk), CardPanel.cardCoordinates.get(0)[0], CardPanel.cardCoordinates.get(0)[1])));
        CardPanel.cardBoard.add(new Hint(1, new EmptyVision(new Nasos(CardPanel.renk), CardPanel.cardCoordinates.get(1)[0], CardPanel.cardCoordinates.get(1)[1])));
        CardPanel.cardBoard.add(new Hint(2, new EmptyVision(new Hercules(CardPanel.renk), CardPanel.cardCoordinates.get(2)[0], CardPanel.cardCoordinates.get(2)[1])));
        CardPanel.cardBoard.add(new Hint(3, new EmptyVision(new Pythia(CardPanel.renk), CardPanel.cardCoordinates.get(3)[0], CardPanel.cardCoordinates.get(3)[1])));
        CardPanel.cardBoard.add(new Hint(6, new EmptyVision(new Martialis(CardPanel.renk), CardPanel.cardCoordinates.get(6)[0], CardPanel.cardCoordinates.get(6)[1])));
        CardPanel.cardBoard.add(new Hint(7, new EmptyVision(new Poseidon(CardPanel.renk), CardPanel.cardCoordinates.get(7)[0], CardPanel.cardCoordinates.get(7)[1])));
        CardPanel.cardBoard.add(new Hint(8, new EmptyVision(new Eleonore(CardPanel.renk), CardPanel.cardCoordinates.get(8)[0], CardPanel.cardCoordinates.get(8)[1])));
        CardPanel.cardBoard.add(new Hint(9, new EmptyVision(new Hecate(CardPanel.renk), CardPanel.cardCoordinates.get(9)[0], CardPanel.cardCoordinates.get(9)[1])));
        CardPanel.cardBoard.add(new Hint(4, new EmptyVision(new Hades(CardPanel.renk), CardPanel.cardCoordinates.get(4)[0], CardPanel.cardCoordinates.get(4)[1])));
        CardPanel.cardBoard.add(new Hint(5, new EmptyVision(new Ares(CardPanel.renk), CardPanel.cardCoordinates.get(5)[0], CardPanel.cardCoordinates.get(5)[1])));
        Info.cardBoard.add(new Hint(0, new EmptyVision(new Zeus(Info.renk), Info.cardCoordinates.get(0)[0], Info.cardCoordinates.get(0)[1])));
        Info.cardBoard.add(new Hint(1, new EmptyVision(new Nasos(Info.renk), Info.cardCoordinates.get(1)[0], Info.cardCoordinates.get(1)[1])));
        Info.cardBoard.add(new Hint(2, new EmptyVision(new Hercules(Info.renk), Info.cardCoordinates.get(2)[0], Info.cardCoordinates.get(2)[1])));
        Info.cardBoard.add(new Hint(3, new EmptyVision(new Pythia(Info.renk), Info.cardCoordinates.get(3)[0], Info.cardCoordinates.get(3)[1])));
        Info.cardBoard.add(new Hint(6, new EmptyVision(new Martialis(Info.renk), Info.cardCoordinates.get(6)[0], Info.cardCoordinates.get(6)[1])));
        Info.cardBoard.add(new Hint(7, new EmptyVision(new Poseidon(Info.renk), Info.cardCoordinates.get(7)[0], Info.cardCoordinates.get(7)[1])));
        Info.cardBoard.add(new Hint(8, new EmptyVision(new Eleonore(Info.renk), Info.cardCoordinates.get(8)[0], Info.cardCoordinates.get(8)[1])));
        Info.cardBoard.add(new Hint(9, new EmptyVision(new Hecate(Info.renk), Info.cardCoordinates.get(9)[0], Info.cardCoordinates.get(9)[1])));
        Info.cardBoard.add(new Hint(4, new EmptyVision(new Hades(Info.renk), Info.cardCoordinates.get(4)[0], Info.cardCoordinates.get(4)[1])));
        Info.cardBoard.add(new Hint(5, new EmptyVision(new Ares(Info.renk), Info.cardCoordinates.get(5)[0], Info.cardCoordinates.get(5)[1])));
        Hint.setIsFromCP(false);
        Visions.setIsFromCP(false);
        Cards.setIsFromCP(false);
    }

    
}
