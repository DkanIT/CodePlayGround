package Visions;

import Cards.Cards;
import Main.Board;
import Main.Game;
import java.util.ArrayList;


public class ZeusV extends Visions {

    public ZeusV(Cards card, float x, float y) {
        super(VisionType.ZeusV, 980, card,10);
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
        int k = -4;
        for (int i = k * Game.getKS(); i < 5 * Game.getKS(); i += Game.getKS()) {
            for (k = -4; k < 5; k++) {
                visibleTmp.add(j + k + i);
            }
        }
        for(int i=0;i<visibleTmp.size();i++){
            if(visibleTmp.get(i) == j-4*Game.getKS() || visibleTmp.get(i) == j-4*Game.getKS()+1
            || visibleTmp.get(i) == j-4*Game.getKS()-1 || visibleTmp.get(i) == j-3*Game.getKS()
            || visibleTmp.get(i) == j+4*Game.getKS() || visibleTmp.get(i) == j+4*Game.getKS()+1
            || visibleTmp.get(i) == j+4*Game.getKS()-1 || visibleTmp.get(i) == j+3*Game.getKS()
            || visibleTmp.get(i) == j+4 || visibleTmp.get(i) == j+4-Game.getKS()
            || visibleTmp.get(i) == j+4+Game.getKS() || visibleTmp.get(i) == j+3
            || visibleTmp.get(i) == j-4 || visibleTmp.get(i) == j-4-Game.getKS()
            || visibleTmp.get(i) == j-4+Game.getKS() || visibleTmp.get(i) == j-3){
                visibleTmp.remove(i--);
            }
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }

    @Override
    public boolean isLegal(int j, int k) {
        if (Math.abs(k - j) <= Game.getKS() * 4 && Math.abs(k % Game.getKS() - j % Game.getKS()) < 4) {
            if (k > j && k % Game.getKS() > j % Game.getKS() && k % (Game.getKS() + 1) == j % (Game.getKS() + 1)) {//sağ alt
                for (int i = j + Game.getKS() + 1; i < k; i += Game.getKS() + 1) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                        return false;
                    }
                }
                return true;
            }
            if (k > j && k % Game.getKS() < j % Game.getKS() && k % (Game.getKS() - 1) == j % (Game.getKS() - 1)) {//sol alt
                for (int i = j + Game.getKS() - 1; i < k; i += Game.getKS() - 1) {
                    if (Board.getBoard()[i].getVision().visiontype != VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
                        return false;
                    }
                }
                return true;
            }
            if (k < j && k % Game.getKS() > j % Game.getKS() && k % (Game.getKS() - 1) == j % (Game.getKS() - 1)) {//sağ üst
                for (int i = k + Game.getKS() - 1; i < j; i += Game.getKS() - 1) {
                    if (Board.getBoard()[i].getVision().visiontype !=VisionType.Bos || Board.getBoard()[i].isIsBlocked()) {
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
