package Visions;

import Cards.Cards;
import Main.Board;
import Main.Game;
import java.util.ArrayList;



public class MartialisV extends Visions {

    public MartialisV(Cards card, float x, float y){
        super(VisionType.MartialisV,770,card,6);

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
        for (int i = -4; i < 5; i++) {
            visibleTmp.add(j + i);
            visibleTmp.add(j + i * Game.getKS());
        }
        for(int o = 1;o<4;o++){
            for (int i = j - Game.getKS() * o - (4 - o); i < j - Game.getKS() * o + 1 + 4 - o; i++) {
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
                            for (int i = j + 2; i < k; i++) {
                                if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                                    return false;
                                }
                            }
                            return true;
                        }
                        if (k % Game.getKS() != j % Game.getKS() && k < j) {
                            for (int i = k + 2; i < j; i++) {
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
