package Visions;

import Cards.Cards;
import Main.Board;
import Main.Game;
import java.util.ArrayList;


public class PoseidonV extends Visions {

    public PoseidonV(Cards card, float x, float y) {
        super(VisionType.PoseidonV, 580, card,8);
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
        for (int i = -5; i < 6; i++) {
            visibleTmp.add(j + i);
            visibleTmp.add(j + i * Game.getKS());
        }
        for(int o = 1;o<5;o++){
            for (int i = j - Game.getKS() * o - (5 - o); i < j - Game.getKS() * o + 1 + 5 - o; i++) {
                visibleTmp.add(i);
                visibleTmp.add(Game.getKS() * (2 * o) + i);
            }
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }
    
    @Override
    public boolean isLegal(int j, int k) {
        if (Math.abs(k - j) <= Game.getKS() * 3 && Math.abs(k % Game.getKS() - j % Game.getKS()) <= 3) {
            if (k % Game.getKS() == j % Game.getKS() && k > j) {
                for (int i = j + Game.getKS(); i < k; i += Game.getKS()) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                        return false;
                    }
                }
                return true;
            } else if (k % Game.getKS() == j % Game.getKS() && k < j) {
                for (int i = k + Game.getKS(); i < j; i += Game.getKS()) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int m = 0; m < Game.getKS() * Game.getKS(); m += Game.getKS()) {
                    if (j < m + Game.getKS() && k < m + Game.getKS() && k >= m && j >= m) {
                        if (k % Game.getKS() != j % Game.getKS() && k > j) {
                            for (int i = j + 1; i < k; i++) {
                                if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                                    return false;
                                }
                            }
                            return true;
                        }
                        if (k % Game.getKS() != j % Game.getKS() && k < j) {
                            for (int i = k + 1; i < j; i++) {
                                if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                                    return false;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
