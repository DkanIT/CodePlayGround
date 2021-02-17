package Visions;

import Cards.Cards;
import Main.Game;
import java.util.ArrayList;



public class HerculesV extends Visions {

    public HerculesV(Cards card, float x, float y) {
        super(VisionType.HerculesV, 850, card,5);
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
        for (int i = -3; i < 4; i++) {
            visibleTmp.add(j + i);
            visibleTmp.add(j + i * Game.getKS());
        }
        for(int o = 1;o<4;o++){
            for (int i = j - Game.getKS() * o - (4 - o); i < j - Game.getKS() * o + 5 - o; i++) {
                visibleTmp.add(i);
                visibleTmp.add(Game.getKS() * (2 * o) + i);
            }
        }
        cleanOut(j,visibleTmp);
        return visibleTmp;
    }

    @Override
    public boolean isLegal(int j, int k) {
        visibleTmp = new ArrayList<>();
        visibleTmp.add(j);
        for(int i=1;i<4;i++){
            visibleTmp.add(j - i - i * Game.getKS());
            visibleTmp.add(j + i - i * Game.getKS());
            visibleTmp.add(j - i + i * Game.getKS());
            visibleTmp.add(j + i + i * Game.getKS());
        }
        cleanOut(j,visibleTmp);
        return visibleTmp.contains(k);
    }

}