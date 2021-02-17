package Visions;

import Cards.Cards;
import Main.Game;
import java.util.ArrayList;


public class EleonoreV extends Visions {
    
    public EleonoreV(Cards card, float x, float y){
        super(VisionType.EleonoreV,430,card,2);
        this.color = this.card.getColor();
        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;

    }
    @Override
    public ArrayList<Integer> visibleRange(int j) {
        visibleTmp = new ArrayList<>();
        int k = -2;
        for (int i = k * Game.getKS(); i < 3 * Game.getKS(); i += Game.getKS()) {
            for (k = -2; k < 3; k++) {
                visibleTmp.add(j + k + i);
            }
        }
        for(int i=0;i<visibleTmp.size();i++){
            if( visibleTmp.get(i) == j - Game.getKS() - 3 ||
                visibleTmp.get(i) == j - Game.getKS() + 3 ||
                visibleTmp.get(i) == j + Game.getKS() - 3 ||
                visibleTmp.get(i) == j + Game.getKS() + 3)
                visibleTmp.remove(i--);
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }
    
    @Override
    public boolean isLegal(int j, int k) {
        if (Math.abs(k - j) == Game.getKS() - 1
                || Math.abs(k - j) == Game.getKS()
                || Math.abs(k - j) == Game.getKS() + 1
                || Math.abs(k - j) == 1
                || Math.abs(k - j) == Game.getKS() * 2 + 1
                || Math.abs(k - j) == Game.getKS() * 2
                || Math.abs(k - j) == Game.getKS() * 2 - 1
                || Math.abs(k - j) == 2
                || Math.abs(k - j) == Game.getKS() + 2
                || Math.abs(k - j) == Game.getKS() - 2 ) {
            if (Math.abs(j % Game.getKS() - k % Game.getKS()) < 3) {
                return true;
            }
        }
        return false;
    }

}
