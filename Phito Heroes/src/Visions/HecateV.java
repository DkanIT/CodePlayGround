package Visions;

import Cards.Cards;
import Main.Board;
import Main.Game;
import java.util.ArrayList;


public class HecateV extends Visions {

    public HecateV(Cards card, float x, float y) {
        super(VisionType.HecateV, 690, card, 4);
        this.color = this.card.getColor();
        this.velX = 0;
        this.velY = 0;
        this.x = x;
        this.y = y;
    }

    @Override
    public ArrayList<Integer> visibleRange(int j) {
        visibleTmp = new ArrayList<>();
        visibleTmp.add(j);
        for (int i = -7; i < 7; i++) {
            visibleTmp.add(j + i);
            visibleTmp.add(j + i * Game.getKS());
        }
        for (int o = 0; o < 7; o++) {
            for (int i = j - Game.getKS() * o - (7 - o); i < j - Game.getKS() * o + 8 - o; i++) {
                visibleTmp.add(i);
                visibleTmp.add(Game.getKS() * (2 * o) + i);
            }
        }
        for (int i = 0; i < visibleTmp.size(); i++) {
            if (visibleTmp.get(i) == j - 7 * Game.getKS()
                    || visibleTmp.get(i) == j + 7 * Game.getKS()
                    || visibleTmp.get(i) == j - 7
                    || visibleTmp.get(i) == j + 7) {
                visibleTmp.remove(i--);
            }
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }

    @Override
    public boolean isLegal(int j, int k) {
        if (Math.abs(k % Game.getKS() - j % Game.getKS()) <= 4) {
            if (k > j && k % Game.getKS() < j % Game.getKS() && k % (Game.getKS() - 1) == j % (Game.getKS() - 1)) {//sol alt
                for (int i = j + Game.getKS() - 1; i < k; i += Game.getKS() - 1) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()
                            && k % Game.getKS() - j % Game.getKS() > 3) {
                        return false;
                    }
                }
                return true;
            }
            if (k < j && k % Game.getKS() > j % Game.getKS() && k % (Game.getKS() - 1) == j % (Game.getKS() - 1)) {//sağ üst
                for (int i = k + Game.getKS() - 1; i < j; i += Game.getKS() - 1) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()
                            && k % Game.getKS() - j % Game.getKS() > 3) {
                        return false;
                    }
                }
                return true;
            }
        }
        if (Math.abs(k % Game.getKS() - j % Game.getKS()) <= 4) {
            if (k > j && k % Game.getKS() > j % Game.getKS() && k % (Game.getKS() + 1) == j % (Game.getKS() + 1)) {//sağ alt
                for (int i = j + Game.getKS() + 1; i < k; i += Game.getKS() + 1) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                        return false;
                    }
                }
                return true;
            }
            if (k < j && k % Game.getKS() < j % Game.getKS() && k % (Game.getKS() + 1) == j % (Game.getKS() + 1)) {//sol üst
                for (int i = k + Game.getKS() + 1; i < j; i += Game.getKS() + 1) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }
}
